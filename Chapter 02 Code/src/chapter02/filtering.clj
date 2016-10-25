(ns chapter02.filtering)

; filter
(filter
 #(= 0 (rem % 3))
 (range 1 10))


(filter
 {:b 2 :c 3} [:a :b])

 (filter #(not (nil? %))
         '(nil 1 2 3))


(take 5
      (remove odd? (repeatedly #(rand-int 100))))

(filter even?(range 1 10))

(keep false? '(true false true))

(count
 (filter even? (range 0  100)))


(keep even? (range 10  20))

(defn mien [ index item ]
  (if (even? index)
    (inc item)
    nil)
  )

(keep-indexed
 mien
 (range 10 20))

; leep

(keep {:a 1, :b 2, :c 3} [:a :b :d])

(keep #{0 1 2 3} #{2 3 4 5})

(filter {:b 2 :c 3} [:a :b])

; transducers

(transduce
 (filter even?)  conj (range 5))

(transduce (map inc) conj (range 5))

; core.async

(require '[clojure.core.async :as async])
(def c (async/chan 1 (filter  odd?)))
(async/onto-chan c (range))

(async/<!! c) ; => 1
(async/<!! c) ; => 3
(async/<!! c) ; => 5
