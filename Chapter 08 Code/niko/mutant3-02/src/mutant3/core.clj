(ns mutant3.core
  (:require [immutant.web :as web]
            [immutant.messaging :as msg]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(def mycontext
  (msg/context
    :host "192.168.1.3" :port 5445 :reconnect-attempts 30))

(def queue
  (msg/queue "hello" :context mycontext))

(defroutes app
  (GET "/" [] (msg/publish queue "world") "OK"))

(defn -main []
  (web/run-dmc app :host "0.0.0.0" :port 8090))
