(use 'clojure.data.xml)
(use '[clojure.java.io :only [writer reader input-stream output-stream]])
(require '[clojure.core.async :as async])


(def in-chan (async/chan))
(def out-chan (async/chan))

(async/thread
  (while true
    (let [data (async/<!! out-chan)]
      (println data))))

(async/thread
  (while true
    (let [data (async/<!! in-chan)]
      (if  (= "Friend1"(first (:content (parse  (java.io.StringReader. data)))))
      (async/>!! out-chan data)))))

(with-open [in (reader (input-stream "test_0.xml"))]
(doall
 (pmap
 #(async/>!! in-chan %)
  (line-seq in))))
