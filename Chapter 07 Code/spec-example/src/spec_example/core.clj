(ns spec-example.core
  (:require [clojure.spec :as s]
            [clojure.spec.test :as stest]
            )
  )

(s/def ::seq-of-names (s/* string?))
;;=> :spec-example.core/seq-of-names

(s/describe ::seq-of-names)
;;=> (* string?)

(s/conform ::seq-of-names ["Makoto Hashimoto" "Nicolas Modrzyk"])
;;=> ["Makoto Hashimoto" "Nicolas Modrzyk"]

(s/conform ::seq-of-names [:Makoto-Hashimoto "Nicolas Modrzyk"])
;;=> :clojure.spec/invalid

(s/def ::date (s/map-of keyword? integer?))
;;=> :spec-example.core/date

(s/conform ::date {:year 2016})
;;=> {:year 2016}

(s/conform ::date {:year 2016 :date 10 :month 7})
;;=> {:year 2016, :date 10, :month 7}

(s/valid? #{"01" "02" "03"} "01")
;;=> true

(s/valid? #{"01" "02" "03"} "04")
;;=> false

(s/valid? (s/nilable string?) "test")
;;=> true

(s/valid? (s/nilable string?) nil)
;;=> true

(s/valid? (s/nilable string?) 1)
;;=> false

(s/def ::small-int #(<= -32768 % 32767))
;;=> :spec-example.core/small-int

(s/conform ::small-int 10)
;;=> 10

(s/conform ::small-int 50000)
;;=> :clojure.spec/invalid

(defn positive-int-add [x  y]
  (+ x y))
;;=> #'spec-example.core/positive-int-add

(s/fdef positive-int-add
        :args (s/and (s/cat :x integer? :y integer?)
                     #(and (< 0 (:x %) )(< 0 (:y %) )))
        :ret integer?)
;;=> spec-example.core/positive-int-add

(positive-int-add 1 10)
;;=> 11

(positive-int-add 1 -20)
;;=> -19

(stest/instrument 'positive-int-add)
;;=> #'spec-example.core/positive-int-add

(positive-int-add 1 -20)

;;=> ExceptionInfo Call to #'spec-example.core/positive-int-add did not conform to spec:
;;=> val: {:x 1, :y -20} fails at: [:args] predicate: (and (< 0 (:x %)) (< 0 (:y %)))
;;=> :clojure.spec/args  (1 -20)
;;=>   clojure.core/ex-info (core.clj:4703)
