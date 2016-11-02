(ns mutant5.core
  (:require [immutant.web :as web]
            [immutant.messaging :as msg]
            [immutant.web.middleware :as me]
            [immutant.web.async :as async]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(def mycontext
  (msg/context
    :host "192.168.1.3" :port 5445 :reconnect-attempts 30))

(def queue
  (msg/queue "hello" :context mycontext))

(def callbacks
  { :on-open (fn [ch] (msg/publish queue "open!"))
    :on-message (fn [ch msg]
         (msg/publish queue msg)
         (dotimes [msg 10]
          (async/send! ch (str msg) {:close? (= msg 9)})
          (Thread/sleep 100)))})

(defn app [request]
  (async/as-channel request callbacks))

(defn -main []
  (web/run-dmc (-> app
    (me/wrap-websocket callbacks))
    :host "0.0.0.0"
    :port 8000))
