(ns mutant3.core
  (:require [immutant.web :as web]
            [immutant.messaging :as msg]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(def hornetq
  (msg/context :remote-type :hornetq-standalone))

(def mycontext
  (msg/context :host "localhost" :port 5445))

(def queue2 (msg/queue "hello2" :context mycontext))

(msg/respond 
  queue2 
  (fn[msg] (str "hello "  msg)))

(defroutes app
  (GET "/" [] "<h1>Hello Compojure World</h1>"))

(defn -main []
  (web/run-dmc app :host "0.0.0.0" :port 8080))
