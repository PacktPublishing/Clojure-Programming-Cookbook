
(require '[clojure.core.reducers :as r])
(use 'clojure.data.xml)
(use '[clojure.java.io :only [writer reader input-stream output-stream]])


(time
 (with-open [in (reader (input-stream "test_0.xml"))]
  (into []
    (r/filter
    (fn[x]
      (= "Friend1"
      (first (:content (parse
       (java.io.StringReader. x))))))
    (line-seq in)))))
