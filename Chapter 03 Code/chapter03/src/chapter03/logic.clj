(ns chapter03.logic
  (:use [clojure.core.logic])
  (:require [clojure.core.logic.fd :as fd]))

(run* [q]
  (== q 1))

 ; only suceeds if q == (1 2 3)
  (run* [q]
   (conso 1 [2 3] q))

  (run* [q]
   (resto [1 2 3 4] q))

  (run* [q]
     (membero 7 [1 3 8 q]))

 (run* [q]
   (membero q [1 2 3])
   (membero q [2 3 4]))

 (run* [q]
      (!= q 2)
      (membero q [1 2 3]))


   (run* [q] (appendo [1 2] [3 4] q))

   (run* [q] (appendo [1 2] q [1 2 3 4]))



 (run* [q]
      (conde
        [(== q 1)]
        [(== q 2)]
       ))


 (run 1 [q]
              (fresh [a b]
                (== 4 a)
                (== b 5)
                (== [a b] q)))

 (run* [q]
         (fresh [x]
           (featurec x {:foo q})
           (== x {:foo 1})))


 (run* [q]
  (fresh [a]
    (== a [1 2 3 4 5])
    (matche [a]
            ([ [1 _ . q ]]))))

 (run* [q]
  (fresh [a]
    (== a [1 2 3 4 5])
    (matche [a]
            ([ [1 2 . [3 4 5] ]]
             (== q "first"))
            ([ [1 . _] ]
             (== q "second")))))

 (run* [q]
   (fresh [r]
     (fd/in q (fd/domain 1 2 3 4 5))
     (fd/in r (fd/domain 20 25))
     (fd/* q q r)))

 (run* [q]
   (fresh [r]
     (fd/in q (fd/domain 1 2 3 4 5))
     (membero r (range 1 10))
     (fd/* q q r)))




 ; https://rawgit.com/dedeibel/clojure-core-logic-cheatsheets/
 ; master/out/cheatsheet-use-title-attribute-no-cdocs-summary.html

 (let [vars (repeatedly 2 lvar)]
   (run* [q]
      (== q vars)
      (everyg
       #(fd/in % (fd/interval 0 5))
       vars)))

 (let [vars (repeatedly 2 lvar)]
   (run* [q]
     (fresh [a]
      (== q vars)
      (appendo [1] a q)
      (everyg
       #(fd/in % (fd/interval 0 5))
       vars))))

  (run* [q]
    (fresh [a]
      (fd/in a (fd/interval 0 5))
      (conso a [2 3] q)))

 (let[vars (list (lvar) (lvar))]
   (run* [q]
         (fresh [a]
                (fd/in a (fd/interval 0 2))
         (conso 0 [a 3] q)
      (everyg
       #(fd/in % (fd/interval 0 5))
       vars))))

 ; http://stackoverflow.com/questions/12168267/factorial-in-clojure-core-logic
(defne factorialo [n m]
  ([0 1])
  ([n m]
     (fresh [n1 m1]
       (project [n]
         (== n1 (- n 1)))
       (factorialo n1 m1)
       (project [n m1]
         (== m (* n m1))))))

(run 1 [q]
  (factorialo 5 q))

 (run* [q]
     (fresh [a b]
       (fd/in a b (fd/interval 0 10))
       (fd/eq (= 4 (* (- a b) 2)))
       (== q [a b])))

 (run* [q]
           (fresh [a b]
             (fd/in a b (fd/interval 0 10))
             (fd/eq (= 2 (- a b)))
             (== q [a b])))

(run 1 [q]
  (fresh [a b]
    (fd/in a (fd/interval 0 10000))
    (fd/eq (= 4 (* a a)))
    (== q [a])))

; SUDOKU building block

; reminder
(map #(hash-map %1 %2)
     (take 5 (range 0 10))
     (range 1 10))

  (run* [c1 c2 c3 c4 c5 c6 c7 c8 c9 :as vs] ; define lvars
    (everyg #(fd/in % (fd/interval 1 9)) vs) ; each lvar is in the interval 1 9
    (fd/distinct vs) ; every lvar is different
    (fd/eq
     (= (- c4 c6) c7) ; c4 - c6 = c7
     (= (* c1 c2 c3) (+ c8 c9)) ; c1 * c2 * c3 = c8 + c9
     (< (+ c2 c3 c6) c8) ; c2 + c3 + c6 < c8
     (< c9 c8)) ; c9 < c8
        )

  (run* [c1 c2 c3 c4 c5 c6 c7 c8 c9 :as vs] ; define lvars
    (everyg #(fd/in % (fd/interval 1 9)) vs) ; each lvar is in the interval 1 9
    (fd/distinct vs) ; every lvar is different
    (fd/eq
     (= (- c4 c6) c7) ; c4 - c6 = c7
     (= (* c1 c2 c3) (+ c8 c9)) ; c1 * c2 * c3 = c8 + c9
     (< (+ c2 c3 c6) c8) ; c2 + c3 + c6 < c8
     (< c9 c8)) ; c9 < c8

     (project [vs]
      (everyg
       (fn [[v n]] (fd/!= v n) )
        (map
         vector
         vs
         (range 1 10))
       )))

  (run* [c1 c2 c3 c4 c5 c6 c7 c8 c9 :as vs] ; define lvars
    (everyg #(fd/in % (fd/interval 1 9)) vs) ; each lvar is in the interval 1 9
    (fd/distinct vs) ; every lvar is different

    (fd/eq
     (= (- c4 c6) c7) ; c4 - c6 = c7
     (= (* c1 c2 c3) (+ c8 c9)) ; c1 * c2 * c3 = c8 + c9
     (< (+ c2 c3 c6) c8) ; c2 + c3 + c6 < c8
     (< c9 c8)) ; c9 < c8

    (project [c1]
     (fd/== 4 c1)))

(run 5 [c1 c2 c3]
      (fd/in c1 c2 c3 (fd/interval 1 20))
      (fd/eq
        (= (- c2 c1) c3)
        (= c3 5)
       ))

; breaks !
(run* [c1 c2 c3]
      (fd/in c1 c2 c3 (fd/interval 1 20))
      (fd/eq
       (= (+ c1 c3) 5)))

(run 5 [c1 c2 c3]
      (fd/in c1 c2 c3 (fd/interval 1 20))
      (fd/distinct [c1 c2 c3])
      (fd/eq
        (= (- c2 c1) c3)
        (= c3 5)
       ))

(run* [a b c]
  (fd/in a b c (fd/interval 0 3))
  (fd/distinct [a b c])
  (project [a]
    (== 2 a)))

; count different elements in a list
; http://stackoverflow.com/questions/19018637/clojure-core-logic-counting-elements-in-a-set
(defn count-different-elements-in-list [coll]
  (count (set coll)))
(def interval  (range 1 4))
(run* [a b c]
  (membero a interval)
  (membero b interval)
  (membero c interval)
  (project [a b c]
    (== 2 (count-different-elements-in-list (list a b c)))))

(run* [a b c]
  (fd/in a b c (fd/interval 1 10))
  (fd/distinct [a b c])
  (project [a c]
    (== 2 a)))

(run* [a b c]
  (fd/in a b c (fd/interval 1 10))
  (fd/distinct [a b c])
  (fd/eq
    (= 2 a)))


(def interval_ (range 5))
(defn test-me [ x ]
   (* x x))

(run* [c1 c2 c3]
  (membero c1 interval_)
  (membero c2 interval_)
  (membero c3 interval_)
  (fd/eq
    (= (- c2 c1) c3))
  (project [c3]
     (conde
        [(== (test-me c3) 16)]
        [(== (test-me c3) 9)]
           )))

; results from database
; http://stackoverflow.com/questions/14529965/how-do-i-connect-clojure-core-logic-to-a-database?rq=1

(use '[clojure.string :only (split)])

(defn parse-rows
  [file]
  (let [rows (split (slurp file) #"\n" )]
    (map (fn[x]
       (map #(Integer/parseInt %)
         (split x #","))) rows)))

(defn query [q file]
  (fn [b]
    (to-stream
      (map (fn [result] (unify b q result))
           (parse-rows file)))))

(run* [q]
      (fresh [a]
      (query a "resources/logic-rows.csv")
      (conso 1 q a)
      ))

; NQUEENS

; nqueens

(defne safe-queens [q others]
  ([_ ()])
  ([[x1 y1] [[x2 y2] . t]]
   (!= x1 x2)
   (!= y1 y2)
   (project [x1 x2 y1 y2]
            (!= (- x1 x2) (- y2 y1))
            (!= (- x2 y2) (- x2 y1)))
   (safe-queens [x1 y1] t)))

(defne nqueens-solves [l n]
  ([() _])
  ([[[x y] . t] _]
   (nqueens-solves t n)
   (membero x (range n))
   (safe-queens [x y] t)))

(defn queens [n]
  (run* [q]
        (== q (map vector (repeatedly lvar) (range n )))
        (nqueens-solves q n)))

 (first (queens 4))


 (defne match2 [x y]
  ([[a b . tail] [b a . tail]]))

(run* [r] (match2 '(a b c d) r)) ;; => ((b a c d))
