(ns mutant3.core
  (:require [immutant.web :as web]
            [immutant.messaging :as msg]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(def hornetq
  (msg/context :remote-type :hornetq-standalone))

(def mycontext
  (msg/context :host "localhost" :port 5445))

(def queue 
  (msg/queue "hello" :context mycontext))

(msg/listen queue
  (fn[msg]
    (println msg)))

(defroutes app
  (GET "/" [] "<h1>Hello Compojure World</h1>")
  (GET "/msg" [] (msg/publish queue "world") "OK")
  (route/not-found "<h1>Page not found</h1>"))

(defn -main []
  (web/run-dmc app :host "0.0.0.0" :port 8080))
