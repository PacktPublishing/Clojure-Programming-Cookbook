(ns mutant3.core
  (:require [immutant.web :as web]
            [immutant.messaging :as msg]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(def mycontext
  (msg/context
    :host "192.168.1.6" :port 5445 :reconnect-attempts 30))

(def queue2
  (msg/queue "hello2" :context mycontext))

(defroutes app
  (GET "/req" [] @(msg/request queue2 "world")))

(defn -main []
  (web/run-dmc app :host "0.0.0.0" :port 8090))
