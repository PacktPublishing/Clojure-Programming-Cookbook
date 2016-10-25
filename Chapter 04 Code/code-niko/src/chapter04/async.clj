(ns chapter04.async
  (:require [clojure.core.async :as async]))

(defn process [line]
  line)

(def stdin-reader
  (java.io.BufferedReader. *in*))

(def in-chan (async/chan))
(def out-chan (async/chan))

(defn start-async-consumers
  "Start num-consumers threads that will consume work
  from the in-chan and put the results into the out-chan."
  [num-consumers]
  (dotimes [_ num-consumers]
    (async/thread
      (while true
        (let [line (async/<!! in-chan)
              data (process line)]
          (async/>!! out-chan data))))))

(defn start-async-aggregator
  "Take items from the out-chan and print it."
  []
  (async/thread
    (while true
      (let [data (async/<!! out-chan)]
        (println data)))))

(defn -main[]
  (do
  (start-async-consumers 16)
  (start-async-aggregator)
  (doseq [line (line-seq stdin-reader)]
    (async/>!! in-chan line))))
