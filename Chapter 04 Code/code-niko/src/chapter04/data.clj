 (ns chapter04.data
   (:require [cheshire.core :refer :all]))

(generate-string
 {:foo "bar"}
 {:key-fn (fn [k] (.toUpperCase (name k)))})


; check memory usage
(def a
  (parse-stream (clojure.java.io/reader "resources/test1.json")))

