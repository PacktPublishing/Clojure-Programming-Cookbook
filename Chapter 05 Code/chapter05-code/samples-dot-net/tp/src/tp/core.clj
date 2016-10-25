(ns tp.core
	(:require [tesser.simple :as t])
	(:require [clojure.core.reducers :as r])
	(:gen-class)
	)

(defn new-reduce [fn initial-value coll]
        (loop [i 0
               answer-so-far initial-value]
          (if (< i (count coll))
            (recur (inc i) (fn answer-so-far (get coll i)))
            answer-so-far)))

(defn exp [x n]
  (r/reduce *' 1 (doall (repeat n x))))

(defn -main [& args]
	(spit
    (str (java.util.Date.) ".txt")
    (- (exp 2 (BigInteger. (first args))) 1)))



;(time (-main "100000000"))
