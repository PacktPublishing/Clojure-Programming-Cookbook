(ns mutant1.core
  (:require [immutant.web :as web]))

(defn app [request]
  {:status 200
   :body "Hello world"})

(defn -main []
  (web/run-dmc app))

; (defn -main []
;   (web/run app)
;   )
