(ns chapter03.functional
    (:require
   [clojure.math.numeric-tower :as math]
   )
  )

(map inc [1 2 3 4 5])
;;=> (2 3 4 5 6)

(take 10 (map + (range) (range) (range) (range)(range)))
;;=> (0 5 10 15 20 25 30 35 40 45)

(map + (range) (range) (range 10) (range)(range))
;;=> (0 5 10 15 20 25 30 35 40 45)

(reduce + [1 2 3 4 5])
;;=> 15

(+ (+ (+ (+ 1 2) 3) 4) 5)
;;=> 15

(map (fn [x] (+ x 2)) [1 2 3 4 5])
;;=> (3 4 5 6 7)


(defn add2 [x]
  (+ x 2)
  )
  ;;=> #'chapter03.functional/add2
  (map add2 [1 2 3 4 5])
;;=> (3 4 5 6 7)

(def my-add2 (fn [x] (+ x 2)))
;;=> #'chapter03.functional/my-add2
(my-add2 3)
;;=> 5

(fn? add2)
;;=> true
(fn? my-add2)
;;=> true

((fn [x y & rest] (apply * x y rest)) 1 2 3 4 5)
;;=> 120

(map #(+ % 2) [1 2 3 4 5])
;;=> (3 4 5 6 7)

(#(+ % %2 %3) 1 2 3)
;;=> 6

(map (constantly 0) [1 2 3 4 5])
;;=> (0 0 0 0 0)

(def not-empty?
  (complement empty?))
;;=> #'chapter03.functional/not-empty?
(not-empty? [])
;;=> false
(not-empty? [1 2 3])
;;=> true

(set (take 15 (repeatedly #(rand-int 30))))
;;=> #{7 20 4 21 29 28 25 3 2 14 18 8}

(defn larger-than-15? [n] (> n 15))
;;=> #'chapter03.functional/larger-than-15?
(let [x (set (take 15 (repeatedly #(rand-int 100))))]
  (= x
     (set (clojure.set/union
           (filter larger-than-15? x)
           (filter (complement larger-than-15?) x)
           )
          ))
  )
;;=> true
(filter (partial < 10) [1 21 34 12 2])
;;=> (21 34 12)
(filter #(< 10 %) [1 21 34 12 2])
;;=> (21 34 12)

(map (comp - inc) [ 1 2 3 4 5])
;;=> (-2 -3 -4 -5 -6)

((every-pred odd? #(< % 12)  #(> % 2))  3  5 7)
;;=> true

((every-pred odd? #(< % 12)  #(> % 2)) 1 2 3)
;;=> false

(filter (every-pred odd? #(< % 12)  #(> % 2)) [1 2 3 4 5])
;;=> (3 5)

((some-fn even? #(= % 10) #(= % 1))  11 10 1)
;;=> true
((some-fn even? #(= % 10) #(= % 1))  3 5 7)
;;=> false
(filter (some-fn even? #(= % 10) #(= % 1)) [11 10 1 2 3])
;;=> (10 1 2)
(+ 1 2 3)
;;=> 6
(+ 1 2 3)
;;=> 6
(+ 1 2 3)
;;=> 6
(rand 10)
;;=> 1.4931178951826118
(rand 10)
;;=> 1.40702455780493
(rand 10)
;;=> 7.135082921360361

(time (reduce + (range 10000000)))
;;=> "Elapsed time: 386.814518 msecs"
;;=> 49999995000000
(time (apply + (range 10000000)))
;;=> "Elapsed time: 392.471789 msecs"
;=> 49999995000000

(defn factorial [n]
  (if (= n 1) 1 (* n (factorial (dec n))))
  )
;;=> #'chapter03.functional/factorial
(time (factorial 200000N))
;;=> StackOverflowError   clojure.lang.Numbers.toBigInt (Numbers.java:252)
(defn factorial-recur[n]
  (loop [c n ret 1]
    (if (< c 2)
      ret
      (recur  (dec c) (* ret c))
      )))
;;=>#'chapter03.functional/factorial-recur
(factorial-recur 20000N)
;;=> 181920632023034513482...

