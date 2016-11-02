(ns mutant7.core
  (:gen-class)
  (:require [immutant.web :as web]
            [immutant.messaging :as msg]
            [immutant.web.middleware :as me]
            [immutant.web.async :as async]))

(def callbacks
  { :on-message (fn [ch msg]
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
