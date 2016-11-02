(ns mutant7.core
  (:gen-class)
  (:require [immutant.web :as web]))

(defn app [request]
  {:status 200
   :body "Hello world!"})

(defn -main []
  (web/run-dmc app))