; define a new namespace
; import some java class
; require some other clojure namespace
(ns helloagain
      (:import [java.util Random])
      (:require [clojure.string :refer :all]))

(println
  (clojure.string/reverse (str (.getName *ns*))))

(defn only-one []
  (println "only one"))

(defn only-two []
  (println "only two"))

(defn only-two-two []
  (println "only three"))
