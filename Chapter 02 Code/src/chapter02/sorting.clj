(ns chapter02.sorting)

(class <)

(sort [2 10 3 4 7])
(sort ["a" "b" "e" "d" ])

(def to-sort
  (take 10
               (repeatedly
                   #(rand-int 1000))))

(sort to-sort)
(sort > to-sort)

; using sort
(sort > [2 10 3 4 7])

(sort < [2 10 3 4 7])

(sort <
       '(10 3 2 9))
(sort < #{10 3 2 9})


(sort
#(< %1 %2)
 [1 2 3 0 -1])

(sort
 (fn[_ _] -1)
 [1 2 3 0 -1])

(sort
 <
  (take 5
        (repeatedly
           #(rand-int 1000))))

(sort-by
 count
 ["this is the first string"
  "this is the second string"
  "another one"])

(sort-by
 #(clojure.string/index-of % "a")
 ["ca" "bba" "al"])

; sorted set
(sorted-set 2 1 3)
(apply sorted-set #{2 1 3})
(apply sorted-set '(1 3 2 7 6 ))

(apply
 sorted-set-by
 >
 #{2 1 3})

(into {} [[:a :b] [:c :d]])

(into (sorted-set)  [3 2 9])

(sorted? [ 1 2 3])

; subseq
(subseq
 (apply sorted-set
         (take 10
               (repeatedly
                   #(rand-int 1000))))
  > 500)

; reverse subseq
 (rsubseq
   (apply sorted-set
         (take 10
               (repeatedly
                   #(rand-int 1000))))
  > 500)

 ; using sort-by
(sort-by
 count
 ["this is the first string"
  "this is the second string"
  "another one"])

(sort-by
 #(clojure.string/index-of % "a")
 ["ca" "bba" "al"])


; sorting map. on keys
(sorted-map :a 2 :b 3)

(into (sorted-map) {:a 2 :b 3})

(into (sorted-map) {2 :b 3 :a})

(sort {:c 2 :b 3})


(defrecord Person [first-name])
(sort-by
      :first-name
 [(->Person "nico")
  (->Person "Makoto")
  (->Person "Princess Peach")])


; sort maps

(let [to-sort {:a 3 :b 5 :c 7 :d 2 :e 10 :f 1}]
  (into
   (sorted-map-by
                         #(compare
                                  (get to-sort %1)
                                  (get to-sort %2)))
        to-sort))

(sort-by
 :price
 >
 [{:price  10 :product :apple}
  {:price 30 :product :tomato}
  {:price 5 :product :peanuts}])

(sort-by
   (juxt :price :quantity)
  [ {:price  10 :quantity 2}
   {:price 10 :quantity 1}])

(  (juxt :price :quantity)    {:price  10 :quantity 2})

(sort-by
 (juxt :price :quantity)
 [{:price  10 :quantity 2}
  {:price 10 :quantity 1}
  {:price 5 :quantity 10}])

; working with files

(->>
     (slurp "project.clj")
     clojure.string/split-lines
     (sort #(compare (count %1) (count %2)))
     (clojure.string/join "\n")
     (spit "target/buffer.out"))

(sorted?
  (sorted-map :a 2 :b 3))

(sorted? [1 2 3])
