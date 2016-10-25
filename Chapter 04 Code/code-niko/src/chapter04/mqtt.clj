(ns chapter04.mqtt
  (:require [clojurewerkz.machine-head.client :as mh]))

(def id (mh/generate-id))
(def c  (mh/connect "tcp://127.0.0.1:1883" id))
(def i  (ref 0))

(def s  (mh/subscribe c {"topic/hello" 0}
      (fn [^String topic meta ^bytes payload]
        (println "-> " (String. payload))
        (dosync (alter i inc)))))

 (println "messages count " @i)


(mh/disconnect c)
