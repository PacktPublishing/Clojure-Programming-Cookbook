(ns okku-client.core
  (:require  [okku.core :as okku]))
;;=> nil

(defn tell [actor message]
  (.tell actor message nil))
;;=> #'okku-client.core/tell

(def actor-system
  (okku/actor-system "actor-system-2" :hostname "localhost" :port 2553))
;;=> #'okku-client.core/actor-system

(okku/spawn
 (okku/actor
    (okku/onReceive [m]
                    (println m)))
   :in actor-system :name "hello-actor-2")

(def actor-ref (okku/look-up
         "akka.tcp://actor-system-1@localhost:2552/user/hello-actor"
         :in actor-system))
;;=> #'okku-client.core/actor-ref

(tell actor-ref "hello remote akka !")
;;=> nil

(def actor-ref2 (okku/look-up
         "akka.tcp://actor-system-2@localhost:2553/user/hello-actor-2"
         :in actor-system))

(tell actor-ref2 "hello local akka !")

(.shutdown actor-system)
;;=> nil

(declare stock-service-actor)
;;=> #'chapter06.akka/stock-service-actor

(def stock-service-requester
  (okku/actor
   (okku/onReceive [{t :type m :message}]
                   (okku/dispatch-on t
                                     :request
                                     (tell stock-service-actor m)
                                     :reply
                                     (do
                                       (println (str "price of "  (:company m) " is " (:price m))))))))
;;=> #'chapter06.akka/stock-service-requester

(def stock-service-requester-actor
  (okku/spawn
   stock-service-requester
   :in actor-system))
;;=> #'chapter06.akka/stock-service-requester-actor

(def stock-service
  (let [stocks {"google" 10.0 "amazon" 200.0 "facebook" 10.0 "twitter" 20.0 "inkedin"  5.0}]
    (okku/actor
     (okku/onReceive [{company :company}]
                     (okku/! stock-service-requester-actor
                             {:type :reply :message {:company company :price (stocks company)}})))))
;;=> #'chapter06.akka/stock-service

(def stock-service-actor
  (okku/spawn
   stock-service
   :in actor-system))
;;=> #'chapter06.akka/stock-service-actor

(tell stock-service-requester-actor {:type :request :message {:company "google"}})
;;=> price of google is 10.0
;;=> nil

(tell stock-service-requester-actor {:type :request :message {:company "amazon"}})
;;=> price of amazon is 200.0
;;=> nil

