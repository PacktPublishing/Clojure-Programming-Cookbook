(ns performance-example.performance-tips
  (:require [criterium.core :refer
             [bench with-progress-reporting]]            
            )
  )
  

(def str "This is a String")
;;=> #'performance-example.performance-tips/str

(time (dotimes [_ 100000]
        (.length str)))
;;=> "Elapsed time: 660.444886 msecs"
;;=> nil

(def ^String str "This is a String")
;;=> #'performance-example.performance-tips/s

(time (dotimes [_ 100000]
        (.length str)))
;;=> "Elapsed time: 14.516152 msecs"
;;=> nil

(defn my-concat [s1 s2]
  (.concat s1 s2))
;;=> #'performance-example.performance-tips/my-concat

(time
 (dotimes [_ 100000]
   (my-concat "Hello " "World !")))
;;=> "Elapsed time: 653.67418 msecs"
;;=> nil

(defn my-concat2 [^String s1 ^String s2]
  (.concat s1 s2))
;;=> #'performance-example.performance-tips/my-concat2

(time
 (dotimes [_ 100000]
   (my-concat2 "Hello " "World !")))
;;=> "Elapsed time: 20.369515 msecs"
;;=> nil

(set! *warn-on-reflection* true)
;;=> true

(def str "This is a String")
;;=> #'performance-example.performance-tips/str

(-> str .toString .toLowerCase)
;;=> Reflection warning, /home/makoto/clojure/clojure-packt-book/chapter09/performance-example/src/performance_example/performance_tips.clj:59:0 - reference to field toString can't be resolved.
;;=> Reflection warning, /home/makoto/clojure/clojure-packt-book/chapter09/performance-example/src/performance_example/performance_tips.clj:59:0 - reference to field toLowerCase can't be resolved.
;;=> "This is a String"

(-> ^String str .toUpperCase .toLowerCase)
;;=> "this is a string"

(def x (into [] (repeat 1000000 "Clojure Programming Cookbook")))
;;=> #'performance-example.performance-tips/x

(time (first x))
"Elapsed time: 0.093379 msecs"
;;=> "Clojure Programming Cookbook"

(time (nth x 999999))
;;=> "Elapsed time: 0.157437 msecs"
;;=> "Clojure Programming Cookbook"

(time (x 999999))
;;=> "Elapsed time: 0.134643 msecs"
;;=> "Clojure Programming Cookbook"

(time (last x))
"Clojure Programming Cookbook"
;;=> "Elapsed time: 59.058957 msecs"
;;=> 999999

(def y (range 1000000))
;;=> #'performance-example.performance-tips/y

(time (nth y 999999))
;;=> "Elapsed time: 23.200696 msecs"
;;=> "Clojure Programming Cookbook"

(time (last y))
;;=> "Elapsed time: 48.545212 msecs"
;;=> "Clojure Programming Cookbook"

(defn my-last [s]
  (nth s (dec (count s))))
;;=> #'performance-example.performance-tips/my-last

(time (my-last x))
;;=> "Elapsed time: 0.210762 msecs"
;;=> "Clojure Programming Cookbook"

(time (my-last y))
;;=> "Elapsed time: 23.701789 msecs"
;;=> "Clojure Programming Cookbook"

(defrecord RecordLocation [x y z])
;;=> performance_example.performance_tips.RecordLocation

(deftype TypeLocation [x y z])
;;=> performance_example.performance_tips.TypeLocation

(def map-location {:x 0.0 :y 0.0 :z 0.0})
;;=> #'performance-example.performance-tips/map-location

(def record-location (->RecordLocation 0.0 0.0 0.0))
;;=> #'performance-example.performance-tips/record-location

(def type-location (->TypeLocation 0.0 0.0 0.0))
;;=> #'performance-example.performance-tips/type-location

(defprotocol ILocation
  (move [self x y z]))
;;=> ILocation

(extend-protocol ILocation
  clojure.lang.PersistentArrayMap
  (move [self x y z]
    {:x (+ (map-location :x) x)
     :y (+ (map-location :y) y)
     :z (+ (map-location :z) z)})
    RecordLocation  
        (move [self x y z]
        (->RecordLocation
         (+ (:x self) x)(+ (:y self) y)(+ (:z self) z)))
    TypeLocation
    (move [self x y z]
        (->TypeLocation
         (+ (.-x self) x)(+ (.-y self) y)(+ (.-z self) z))))
;=> nil

(with-progress-reporting
  (bench   (move map-location 2.0 3.0 2.0)))
;=> Evaluation count : 386864340 in 60 samples of 6447739 calls.
;=>              Execution time mean : 147.959163 ns
;=>     Execution time std-deviation : 6.447521 ns
;=>    Execution time lower quantile : 139.499802 ns ( 2.5%)
;=>    Execution time upper quantile : 162.371977 ns (97.5%)
;=>                    Overhead used : 9.727097 ns

(with-progress-reporting
  (bench (move record-location 2.0 3.0 2.0)))
;=> Evaluation count : 455571960 in 60 samples of 7592866 calls.
;=>              Execution time mean : 121.687435 ns
;=>     Execution time std-deviation : 3.484611 ns
;=>    Execution time lower quantile : 117.866370 ns ( 2.5%)
;=>    Execution time upper quantile : 128.063522 ns (97.5%)
;=>                    Overhead used : 9.727097 ns

(with-progress-reporting
  (bench (move type-location 2.0 3.0 2.0)))
;=> Evaluation count : 590518980 in 60 samples of 9841983 calls.
;=>              Execution time mean : 93.307679 ns
;=>     Execution time std-deviation : 2.608460 ns
;=>    Execution time lower quantile : 90.346986 ns ( 2.5%)
;=>    Execution time upper quantile : 99.742274 ns (97.5%)
;=>                    Overhead used : 9.727097 ns

(def array1 (into [] (repeatedly 1000 #(rand 10))))
;;=> #'performance-example.performance-tips/array1

(defn my-calc1[ar]
  (reduce +
          (map (fn [x] (* x x))
               ar)))
;;=> #'performance-example.performance-tips/my-calc1

(def array2 (double-array (repeatedly 1000 #(rand 10))))
;;=> #'performance-example.performance-tips/array2

(alength ^doubles array2)
;;=> 1000

Try to get the first element.
(aget ^doubles array2 0)
;;=> 7.069889039765091

(defn my-calc2[ar]
  (let [xs
        (amap ^doubles ar
              idx ret
              (let [x (aget ^doubles ar idx)]
                (* x x)))]
    (areduce xs i ret (double 0)
             (+ ret (aget ^doubles xs i)))))
#'performance-example.performance-tips/my-calc2

(bench (my-calc1 array1))
;;=> ....................

(bench (my-calc2 array2))
;;=> ....................


(defn factorial [num]
  (if (= num 1)
    num
    (* num (factorial (dec num)))))
;;=> #'performance-example.performance-tips/factorial

(def memoize-factorial (memoize factorial))
;;=> #'performance-example.performance-tips/memoize-factorial

(time (factorial 3000N))
;;=> "Elapsed time: 11.111595 msecs"
;;=> 4149359603......

(time (factorial 3000N))
"Elapsed time: 11.260317 msecs"
;;=> 4149359603......

(time (memoize-factorial 3000N))
"Elapsed time: 11.258368 msecs"
;;=> 4149359603......
(time (memoize-factorial 3000N))
"Elapsed time: 0.084887 msecs"
;;=> 4149359603......
