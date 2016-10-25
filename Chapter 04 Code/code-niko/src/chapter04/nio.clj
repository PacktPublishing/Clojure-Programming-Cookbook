(ns chapter04.nio
  (:use clojure.core.async)
  (:use clojure.java.io nio2.io nio2.watch nio2.files))

(defn tail [n p]
  "Print the last n lines of path p to stdout"
  (with-open [rdr (reader p)]
    (doseq [l (take-last n (line-seq rdr))]
      (println l))
    (doseq [e (watch-seq (parent (real-path p)) :modify)]
      (when (= (real-path (:path e)) (real-path p))
        (while (.ready rdr) (println (.readLine rdr)))))))

(def a (thread
    (tail 10 (path "test-file"))))

(spit "test-file" "hello2" :append true)

