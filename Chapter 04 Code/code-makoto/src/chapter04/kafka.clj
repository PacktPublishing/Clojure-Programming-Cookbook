(ns chapter04.kafka
  (:require
   [clj-kafka.producer :as p]
   [clj-kafka.core :as core]
   [clj-kafka.consumer.zk :as zk]
   [clj-kafka.new.producer :as new]
   [clj-kafka.consumer.simple :as simple]
   [clj-kafka.offset :as offset]
   [clj-kafka.admin :as admin]
   [clojure.core.async :as async :refer :all]
   )
        (:import (kafka.consumer Consumer ConsumerConfig KafkaStream)
           (kafka.producer KeyedMessage ProducerConfig)
           (kafka.javaapi.producer Producer)
           (java.util Properties)
           (java.util.concurrent Executors))
  )
;;=> nil

(def producer-config
  {"metadata.broker.list" "localhost:9092"
   "serializer.class" "kafka.serializer.DefaultEncoder"
   "partitioner.class" "kafka.producer.DefaultPartitioner"}
   )
;;=> #'chapter04.kafka/producer-config

(p/send-message (p/producer producer-config)
    (p/message "test" (.getBytes "Hi hello !")))
;;=> nil
(p/send-message (p/producer producer-config)
    (p/message "test" (.getBytes "Hi hello !")))
;;=> nil


(def consumer-config {
                      "zookeeper.connect" "localhost:2181"
                      "group.id" "test-consumer-group1"
                      "auto.offset.reset" "smallest"
                      "zookeeper.connection.timeout.ms" "5000"
                      "consumer.timeout.ms" "5000"
                      "auto.commit.enable" "true"
             })
;;=> #'chapter04.kafka/consumer-config

(def x (core/with-resource [c (zk/consumer consumer-config)]
         zk/shutdown
         (doall (take 1 (zk/messages c "test")))
         ))
;=> #'chapter04.kafka/x

(zk/consumer consumer-config)

(core/with-resource [c (zk/consumer consumer-config)]
  zk/shutdown
  (doall (take 1 (zk/messages c "test"))))

(with-open [zk (admin/zk-client "127.0.0.1:2181")]
  (let [topic "test-topic"]
  (if-not (admin/topic-exists? zk topic)
    (admin/create-topic zk topic
                        {:partitions 1
                         :replication-factor 1
                         :config {"cleanup.policy" "compact"}}))
    )
)
;;=> nil
