(ns chapter04.mqtt-pub
  (:require [clojurewerkz.machine-head.client :as mh]))


(let [id (mh/generate-id)
      c  (mh/connect "tcp://192.168.100.103:1883" id)]
      (mh/publish c "topic/hello" "hello from Clojure!")
      (mh/disconnect c))
