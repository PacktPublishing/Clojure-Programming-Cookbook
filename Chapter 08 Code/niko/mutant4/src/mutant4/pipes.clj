(ns mutant4.pipes
  (:require [immutant.messaging.pipeline :as pl]))


(defn barme [m] 
	(map inc m))

(defonce bar-pipeline
 (pl/pipeline "bar"
   (pl/step barme)
   (pl/step barme)
   :concurrency 2))

(deref
  (bar-pipeline (take 1000 (repeat 1))))
