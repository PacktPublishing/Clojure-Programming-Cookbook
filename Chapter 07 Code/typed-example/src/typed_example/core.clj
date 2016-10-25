(ns typed-example.core
  {:lang :core.typed}
  (:require [clojure.core.typed :as t]))
;;=> nil

(t/ann my-add [Number Number -> Number])
;;=> nil

(defn my-add [a b] (+ a b))
;;=> #'typed-example.core/my-add

(my-add 1 2)
;;=> 3

(my-add 1 "a")
;;=> Type Error (*cider-repl typed-example:49585*:459:20) Function add could not be applied to arguments:
;;=> Domains:
;;=>     Number Number
;;=> Arguments:
;;=>     (t/Val 1) (t/Val "a")
;;=> Ranges:
;;=>     Number
;;=> in: (my-add 1 "a")
;;=> ExceptionInfo Type Checker: Found 1 error  clojure.core/ex-info (core.clj:4617)

(t/ann d-value BigDecimal)
;;=> nil

(def d-value 10.0M)
;;=> #'typed-example.core/d-value

(def d-value 1)
;;=> Type Error (*cider-repl typed-example:49585*:584:20) Type mismatch:
;;=> Expected:     BigDecimal
;;=> Actual:     (t/Val 1)
;;=> in: 1
;;=> ExceptionInfo Type Checker: Found 1 error  clojure.core/ex-info (core.clj:4617)
