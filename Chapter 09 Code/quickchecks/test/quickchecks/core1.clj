(ns quickchecks.core1
  (:require    [clojure.test.check.properties :as prop])
  (:require    [clojure.test.check.generators :as gen])
  (:require [clojure.test.check.clojure-test :refer [defspec]]))

(def tested sort)

(def sort-idempotent-prop
  (prop/for-all [v (gen/vector gen/int)]
    (= (tested v) (tested (tested v)))))

(defspec sorting-stays-sorted
  10
  sort-idempotent-prop)