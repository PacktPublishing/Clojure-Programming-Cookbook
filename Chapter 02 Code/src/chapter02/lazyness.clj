(ns chapter02.lazyness)

(defn triple [n]
  (*' n 3))

(def c  (with-open [rdr (clojure.java.io/reader "project.clj")]
             (apply str  (interleave "\n" (line-seq rdr)))))

(println c)

(interleave (repeat "a") [1 2 3 4 5 6 7])

(interleave (cycle [:a :b]) [1 2 3 4 5 6 7])

(map
 #(Math/cos %)
 (iterate inc 1))

(cycle [1 2 3])
(line-seq (java.io.File. "project.clj"))


  (defn map-2
  ([f coll]
   (lazy-seq
    (when-let [s (seq coll)]
      (cons (f (first s)) (map f (rest s)))))))

; lazy first sample

(def hello-2
  (lazy-seq
   (do (println "." ) (vector 1))))
hello-2


(def hello-3
  (do (println "." ) (vector 1)))
hello-3

(def hello
  (map-2
  #(do (println %) (inc %))
  [0 1 2 3]))

hello

; What's next

; take while  and lazy ness chunks
; https://dzone.com/articles/clojure-not-so-lazy-sequences

; eager evaluation
; https://en.wikipedia.org/wiki/Lazy_evaluation
(macroexpand-1 '(iterate inc 10))


; build sequences

(defn fib [a b] (cons a (lazy-seq (fib b (+ b a)))))
(take 5 (fib 1 1))


(defn sum-last-2
   ([] (sum-last-2 1 2))
   ([n m] (cons n (lazy-seq (sum-last-2 m (+ n m))))))
 (take 6 (sum-last-2))

 (defn sieve [s]
  (cons (first s)
        (lazy-seq (sieve (filter #(not= 0 (mod % (first s)))
                                 (rest s))))))

 (last (take 10000 (sieve (iterate inc 2))))


;;  (defn multi-filter [filters coll]
;;   (let [c (count filters)
;;         ignore (Object.)]
;;     (map
;;       (fn [i]
;;         (remove #(= % ignore)
;;           (take-nth c
;;             (drop i
;;               (mapcat #(map (fn [p e] (if (p e) e ignore)) filters (repeat c %)) coll)))))
;;       (range c))))

 (defn multi-filter [filters coll]
  (let [c (count filters)
        ignore (Object.)
        merged-seq
          (mapcat
            (fn [k]
              (map
                (fn [p e]
                  (if (p e) e ignore))
                filters (repeat c k)))
            coll)]
    (map
      (fn [i]
        (remove #(= % ignore)
          (take-nth c
            (drop i
              merged-seq))))
      (range c))))


 (multi-filter [even? odd? even?] (range 10))

  (let [[a b c] (multi-filter [even? odd? even?] (range 10000))] [(count a) (count b) (count c)])





;   If you’re using lazy sequences, make sure everything is truly lazy (or small). If you’re in a non-lazy loop, don’t build up a lazy result.


 (macroexpand-1
  (iterate inc 0))

   (first (filter #(= 9999 %) (range)))

 (first (filter #(= 9999 %) (vec (take 10000 (range)))))
 (some #(when (= 9999 %) %) (range))

  (defn rand-seq [n ]
    (lazy-seq (cons (rand-int n) (rand-seq n))))

   (defn rand-seq [] (lazy-seq (cons (rand) (rand-seq))))


  (take 10 (rand-seq))


  (take 10 (filter #(do (print \.) (even? %)) (range 0 100)))


(take 10  (iterate dec 0))


(take 10 (iterate #(*' 2 %) 1))
