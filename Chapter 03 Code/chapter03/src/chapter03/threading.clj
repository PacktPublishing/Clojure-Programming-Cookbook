(->  (rand-int 10)
     (+ 1))


(-> 2
    (+ 1)
    (str))

(-> 2
    (+ 1)
    str)

(->> 2
     (- 1))

(-> 2
    (- 1))


(->> 2
     (+ 1 2)
     list
     (into []))

(->> [1 2 3]
     (map inc)
     (into [])
     )

(macroexpand-1
   `(-> 2
        (+ 1)
        (+ 2)))

(macroexpand-1
   `(->> 5
        (- 1)
        (- 2)))

 (let [speed 110]
         (cond
           (>= speed 130) "Too fast for love"
           (>= speed 100) "How many miles to babylon ..."
           (>= speed 80) "Your grand ma is calling"
           (>= speed 50) "City center"
           :else "Falling asleep"))

  (let [speed 110 count 0]
         (cond-> count
           (>= speed 130) inc
           (>= speed 100) inc
           (>= speed 80) inc
           (>= speed 50) inc
                 ))

  (let [speed 110 count 0]
         (cond->> count
           (>= speed 130) inc
           (>= speed 100) inc
           (>= speed 80) inc
           (>= speed 50) inc
                 ))


(defn average [coll]
  (/ (reduce + coll) (count coll)))

 (defn compute
  [speeds {:keys [apply-fn limit-to]}]
         (cond->> speeds
           limit-to (take limit-to)
           apply-fn average
                 ))

 (compute
  [110 90 150 10]
  {:apply-fn average})


 (compute
  [110 90 150 10]
           {:apply-fn average :limit-to 2})


 (some-> {:speed [50 90]}
         :speed
         count)

  (some-> {:speed [50 90]}
         :speed
          )

  (some->>
        {:speed [50 90 100]}
         :speed
         (take 2)
         (map inc)
         (filter (fn[x] (> x 60)))
         average
         )


(macroexpand-1 '  (some-> {:speed [50 90]}
         :speed
          ))

(as-> 0 n
      (inc n)
      (dec n))


(as-> {:speed [50 70 100]} s
      (get s :speed)
      (take 2 s)
      (average s)
      )


(as-> [1 2 3] $
      (conj $ 5)
      (map inc $)
      (into [] $)
      )


(defn split-words [text]
 "split text into a list of words"
 (re-seq #"\w+" text))

(defn calculate-frequencies [words]
 "convert list of words to a word-frequency hash"
 (reduce (fn [words word] (assoc words word (inc (get words word 0))))
  {}
  words))

(->
    (slurp "LICENSE")
    (split-words)
    (count))

