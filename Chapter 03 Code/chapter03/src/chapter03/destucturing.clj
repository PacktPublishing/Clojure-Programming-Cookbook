(ns chapter03.destucturing
  (:require
   [clojure.math.numeric-tower :as math])
  )

(let [[x y z] [1 2 3]]
 [x y z]
  )
;;=> [1 2 3]

(let [[x y z] [1 2 3] [a b c] ["a" "b" "c"]] 
      [x y z a b c] 
      )
;;=> [1 2 3 "a" "b" "c"]

(let [[x y & z] [1 2 3 4]]
 [x y z]
  )
:;=> [1 2 (3 4)]

(let [[x _ z] [1 2 3]]
 [x z]
  )
;;=> [1 3]
(let
    [{x :x y :y z :z} {:x 1 :y 2 :z 3}]
    [x y z])
;;=> [1 2 3]
(let
   [{:strs [x y z]} {"x" 1 "y" 2 "z" 3}]
 [x y z])
  ;;=> [1 2 3]
(let
   [{:syms [x y z]} {'x 1 'y 2  'z  3}]
 [x y z])
;;=> [1 2 3]
(let
    [{:keys [x y z] :or {x 0 y 0 z 0}}
     {:x 1}
     ]
  [x y z])
  ;;=> [1 0 0]
(let
    [{:keys [x y z] :or {x 0 y 0 z 0} :as all} {:x 1 :a 2 :b 3}
     ]
  [x y z all])
;;=> [1 0 0 {:x 1, :a 2, :b 3}]

(let
    [{x :x y :y {a :a b :b} :z}
     {:x 1 :y 2 :z {:a 3 :b 4}}
     ]
  [x y a b])
;;=> [1 2 3 4]

(let
    [{:keys [x y] {:keys [a b]} :z}
     {:x 1 :y 2 :z {:a 3 :b 4}}
     ]
  [x y a b])
:;=> [1 2 3 4]

(defn func1 [[x y z]]
  (+ x y z)
  )
;;=> #'chapter03.destucturing/func1
(func1 [1 2 3])
;;=> 6
(defn func2 [col]
  (+ (first col) (second col) (nth col 2))
  )
;;=> #'chapter03.destucturing/func2
(func2 [1 2 3])
;;=> 6
(defn func3 [{:keys [x y z]}]
  (+ x y z)
  )
;;=> #'chapter03.destucturing/func3
(func3 {:x 1 :y 2 :z 3})
;;=> 6
(defn func4 [col]
  (+ (:x col) (:y col) (:z col))
  )#'chapter03.destucturing/func4
;;=> #'chapter03.destucturing/func4
(func4 {:x 1 :y 2 :z 3})
;;=> 6

(let [[a _ b _ c ] "Clojure"]
 [a b c])
;;=> [\C \o \u]

(let [[_ _ _ & x] "Clojure"]
  (apply str x))
;;=> "jure"

(require '[clojure.core.match :refer [match]])
;;=> nil

(let [x false y true z true]
  (match [x y z]
         [false false false] 1
         [false false true]  2
         [false true true]   3
         :else 5))
;;=> 3
(let [x {:a 3 :b 2 :c 1}]
  (match [x]
         [{:a 1 :b b}]  {:b1 b}
         [({:a 3 :b a} :only [:a :b])] {:a2 a}
         [{:a a :b b}] {:a3 b}
         :else nil))
;;=> {:a3 2}
(require '[clojure.math.numeric-tower :as math])
(defn distance [x y]
    (match [x y]
          [[x1 y1][x2 y2]]
           (math/sqrt
            (+ (math/expt (- x2 x1) 2) (math/expt (- y2 y1) 2)))
          [[x1 y1 z1][x2 y2 z2]]
           (+ (math/expt (- x2 x1) 2) (math/expt (- y2 y1) 2)(math/expt (- z2 z1) 2))
           :else :error
           )
  )
;;=> #'chapter03.destucturing/distance
(distance [0 0] [1 1])
;;=> 1.4142135623730951
(distance [0 0 0] [1 1 1])
;;=> 3
(distance [0 0 0] [1 1 1 1])
:error
