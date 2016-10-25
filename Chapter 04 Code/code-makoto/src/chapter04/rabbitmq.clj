(ns chapter04.rabbitmq
  (:require [langohr.core :as core]
            [langohr.channel :as channel]
            [langohr.queue :as queue]
            [langohr.consumers :as consumers]
            [langohr.basic :as basic]
            [langohr.exchange :as exchange]
            ))
;;=> nil

;; Simple Consumer

(def *host* "172.17.0.2")

(defn simple-producer [qname payload]
 (with-open
   [conn (core/connect {:host *host*})
    ch (channel/open conn)]
   (println "producing message to exchange " qname " payload = " payload)
   (queue/declare ch qname {:exclusive false :auto-delete false})
   (basic/publish
    ch "" qname payload {:content-type "text/plain" :type "message"})))
;;=> #'chapter04.rabbitmq/simple-producer

(defn message-handler
 [ch {:keys [content-type delivery-tag type] :as meta} ^bytes payload]
 (println
  (str "Received a message payload : " (String. payload "UTF-8")  " , tag : "  delivery-tag)))
;;=> #'chapter04.rabbitmq/message-handler

(defn simple-consumer [qname]
  (with-open
    [conn (core/connect {:host *host*})
     ch (channel/open conn)]
    (println (str "simple-consumer is connected with id: "(.getChannelNumber ch)))
    (consumers/subscribe ch qname message-handler {:auto-ack true})))
;;=> #'chapter04.rabbitmq/simple-consumer

(simple-producer "queue1.cookbook" "Hello !")
;;=> producing message to exchange  queue1.cookbook  payload =  Hello !
;;=> nil

(simple-consumer "queue1.cookbook")
;;=> simple-consumer is connected with id: 1
;;=> Received a message payload : Hello ! , tag : 1
;;=> "amq.ctag-Bedi_3OZAA4OVwmkKbLxrw"

;; Blocking Consumer

;;=> #'chapter04.rabbitmq/blocking-consumer

(defn blocking-consumer [qname]
  (with-open
    [conn (core/connect {:host *host*})
     ch (channel/open conn)]
    (println (str "simple-consumer is connected with id: "(.getChannelNumber ch)))
    (consumers/blocking-subscribe ch qname message-handler {:auto-ack true})))
;;=> #'chapter04.rabbitmq/blocking-consumer


(def f (future (blocking-consumer "queue1.cookbook")))
;;=> simple-consumer is connected with id: 1
;;=> #'chapter04.rabbitmq/f

(simple-producer "queue1.cookbook" "Hello !")
;;=> producing message to exchange  queue1.cookbook  payload =  Hello !
;;=> Received a message payload : Hello ! , tag : 1
;;=> nil

(simple-producer "queue1.cookbook" "Hello !")
;;=> producing message to exchange  queue1.cookbook  payload =  Hello !
;;=> Received a message payload : Hello ! , tag : 2
;;=> nil

(simple-producer "queue1.cookbook" "Hello !")
;;=> producing message to exchange  queue1.cookbook  payload =  Hello !
;;=> Received a message payload : Hello ! , tag : 3
;;=> nil

(future-cancel f)
;;=> true
;;----------------------------------------------------------------------------------------------------------------

(defn fanout-producer [ex  payload]
  (with-open
    [conn (core/connect {:host *host*})
     ch (channel/open conn)]
    (println "producing message to exchange " ex  " payload = " payload)
    (exchange/declare ch ex "fanout" {:durable false :auto-delete false})
    (basic/publish ch ex "" payload)))
;;=> #'chapter04.rabbitmq/fanout-publisher

(defn fanout-consumer [ex qname & {:keys [num] :or {num 1}}]
  (let [handler
        (fn [ch {:keys [delivery-tag]} payload]
          (println (format " [%d-%d] %s" (.getChannelNumber ch) delivery-tag (String. payload "UTF-8"))))]
    (with-open [conn (core/connect  {:host *host*})
                ch (channel/open conn num)]
      (println "starting consumer ...")
      (exchange/declare ch ex "fanout" {:durable false :auto-delete false})
      (queue/declare ch qname {:durable false :auto-delete false})
      (queue/bind ch qname ex)
      (consumers/blocking-subscribe ch qname handler
                                    {:auto-ack true}))))
;;=> #'chapter04.rabbitmq/fanout-consumer

(def f1 (future (fanout-consumer "fanout.exchange" "my.queue1" :num 1)))
;;=> starting consumer ...
;;=> #'chapter04.rabbitmq/f1

(def f2 (future (fanout-consumer "fanout.exchange" "my.queue2" :num 2)))
;;=> starting consumer ...
;;=> #'chapter04.rabbitmq/f2

(def f3 (future (fanout-consumer "fanout.exchange" "my.queue3" :num 3)))
;;=> starting consumer ...
;;=> #'chapter04.rabbitmq/f3

(fanout-producer "fanout.exchange" "hello world !")
;;=> producing message to exchange  my.exchange  payload =  hello world !
;=>  [1-1] hello world !
;=>  [2-1] hello world !
;=>  [3-1] hello world !
;;=> nil

(for [x [f1 f2 f3]]
  (future-cancel x))
;;=> [true true true]

(defn topic-producer [ex key payload]
  (with-open
    [conn (core/connect {:host *host*})
     ch (channel/open conn)]
    (println "producing message to exchange " ex "/"  key " payload = " payload)
    (exchange/declare ch ex "topic" {:durable false :auto-delete false})
    (basic/publish ch ex key payload)))
;;=> #'chapter04.rabbitmq/topic-publisher

(defn topic-consumer [ex topic qname & {:keys [num] :or {num 1}}]
  (let [handler
        (fn [ch {:keys [delivery-tag]} payload]
          (println (format " [%d-%d] %s"
                           (.getChannelNumber ch) delivery-tag (String. payload "UTF-8"))))]
    (with-open [conn (core/connect {:host *host*})
                ch (channel/open conn num)]
      (println "starting consumer ...")
      (queue/declare ch qname {:durable false :auto-delete false})
      (exchange/declare ch ex "topic" {:durable false :auto-delete false})
      (queue/bind ch qname ex {:routing-key topic})
      (consumers/blocking-subscribe ch qname handler
                                    {:auto-ack true}))))
;;=> #'chapter04.rabbitmq/topic-consumer

(def f1 (future (topic-consumer "topic.exchange1" "p1.group1.jp" "my.queue1" :num 1)))
;;=> starting consumer ...
;;=> #'chapter04.rabbitmq/f1

(def f2 (future (topic-consumer "topic.exchange1" "p2.group1.jp" "my.queue2" :num 2)))
;;=> starting consumer ...
;;=> #'chapter04.rabbitmq/f2

(def f3 (future (topic-consumer "topic.exchange1" "#.group1.jp" "my.queue3" :num 3)))
;;=> starting consumer ...
;;=> #'chapter04.rabbitmq/f3

(def f4 (future (topic-consumer "topic.exchange1" "#.group2.jp" "my.queue4" :num 4)))
;;=> starting consumer ...
;;=> #'chapter04.rabbitmq/f4

(topic-producer "topic.exchange1" "p1.group1.jp" "hello p1.group1.jp")
;;=> producing message to exchange  topic.exchange1 / p1.group1.jp  payload =  hello p1.group1.jp
;;=>  [3-1] hello p1.group1.jp
;;=>  [1-1] hello p1.group1.jp

(topic-producer "topic.exchange1" "p2.group1.jp" "hello p2.group1.jp 1")
;;=> producing message to exchange  topic.exchange1 / p2.group1.jp  payload =  hello p2.group1.jp 1
;;=>  [3-2] hello p2.group1.jp 1
;;=>  [2-2] hello p2.group1.jp 1

(topic-producer "topic.exchange1" "p3.group1.jp" "hello p3.group1.jp 1")
;;=> producing message to exchange topic.exchange1 / p3.group1.jp  payload =  hello p1.group2.jp 1
;;=>  [3-3] hello p3.group1.jp 1

(topic-producer "topic.exchange1" "p4.group2.jp" "hello p1.group2.jp 1")
;;=> producing message to exchange  topic.exchange1 / p4.group2.jp  payload =  hello p1.group2.jp 1
;;=>  [4-2] hello p1.group2.jp 1

(for [x [f1 f2 f3 f4]]
  (future-cancel x))
;;=> [true true true true]
