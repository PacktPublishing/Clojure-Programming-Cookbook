(ns mutant4.core
  (:require [immutant.web :as web]
            [immutant.messaging :as msg]
            [immutant.scheduling :as sh]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(def mycontext
  (msg/context
    :host "192.168.1.3" :port 5445 :reconnect-attempts 30))

(def queue
  (msg/queue "hello" :context mycontext))

(defroutes app)

(sh/schedule #(msg/publish queue (java.util.Date.))
  (->
    (sh/limit 5)
    (sh/in 5 :seconds)
    (sh/every 3 :seconds)))

(defn -main []
  (web/run-dmc app :host "0.0.0.0" :port 8091))
