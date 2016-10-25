(ns example.hello
  (:require
    [example.crossover.shared :as shared]))

(js/alert (take 5 shared/fib-seq))

