(ns quickchecks.core0
  (:require    [clojure.test.check.properties :as prop])
  (:require    [clojure.test.check.generators :as gen])
  (:require [clojure.test.check.clojure-test :refer [defspec]]))

(defspec first-element-is-min-after-sorting 
 3
 (prop/for-all [v (gen/not-empty (gen/vector gen/int))]
   (= (apply min v)
      (first (sort v)))))
