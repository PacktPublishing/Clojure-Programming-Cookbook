(ns clojure-java.core
  (:require [clojure.pprint :as pp])
  (:import Java1)
  (:gen-class))

(defn -main [& args]
  (pp/pprint (iterator-seq (Java1. 5))))