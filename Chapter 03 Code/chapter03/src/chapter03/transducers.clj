(into [] (range 10))

(into []
      (filter odd?)
      (range 10))

(into (sorted-set)  [1 3 8 0 2])

(into
 (sorted-set)
 (map rand-int)
 (range 50))


(class (filter odd?))

(filter odd? [1 2 3])

(sequence (filter odd?) [1 2 3])

(class (sequence (filter odd?) [1 2 3]))
(class (into []
      (filter odd?)
      (range 10)))


(reduce
 +
 (range 5))

(transduce
 (filter odd?)
 +
 (range 5))

(reduce
 +
 (filter odd? (range 5)))


(sequence
 (comp
  (map inc)
  (map dec))
 [1 2 3 4])

(sequence
 (comp
  (map inc)
  (interpose 1)
  )
 [1 2 3 4])


(defn my-transfilter [rf]
      (fn
        ; init
        ([] (rf))
        ; step
        ([result input]
           (if (odd? input)
             (rf result input)
             result))
        ; last
        ([result] (rf result))

        ))


(transduce
  my-transfilter
  +
 [ 1 3 8 7])


 (defn my-transfilter [rf]
      (fn
        ; init
        ([] (rf))
        ; step
        ([result input]
           (reduced 1))
        ; last
        ([result] (rf result))

        ))

(transduce
  my-transfilter
  +
 [ 1 3 8 7])


(reduce
 (fn [result new-value]
   (if (< 5 new-value)
     (reduced result)
     (+ result new-value)))
 (range 10))


 (+ 5 (+ 4 (+ 3 (+ 2 (+ 1 (+))))))

; with core.async

(require '[clojure.core.async :refer (chan buffer pipeline put! take!)])

(defn apply-transducer
  [in xf]
  (let [
        out (chan (buffer 8 ))
        thread-count 2
        ]
    (pipeline thread-count out xf in)
    out))

(def in (chan))
(def out (apply-transducer in (filter odd?)))

(put! in 3)
(take! out (fn [x] (println x)))



