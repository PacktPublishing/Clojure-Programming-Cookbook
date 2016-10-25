

(use 'clojure.data.xml)
(use '[clojure.java.io :only [writer reader input-stream output-stream]])


(with-open [in (clojure.java.io/reader
    (clojure.java.io/input-stream
     "test_2.xml"))]
(doall
(filter
 #(= "Friend3" (first (:content %))) (:content (clojure.data.xml/parse in)))))

(with-open [in (reader (input-stream "test_1.xml"))]
 (doall
 (filter
  #(= "Friend1"
    (first (:content (parse
     (java.io.StringReader. %)))))
  (line-seq in))))

