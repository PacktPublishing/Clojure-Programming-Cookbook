(ns example.hello
  (:require
    [example.shared :as shared]))

(js/alert (take 5 shared/fib-seq))

