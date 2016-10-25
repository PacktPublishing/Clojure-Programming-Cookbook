(ns chapter04.generate)

(use 'clojure.data.xml)
(use '[clojure.java.io :only [writer output-stream]])


(comment

    (with-open [o (writer "test_1.xml")]
      (doseq [i (range 1 10)]
                  (.write o (str (element :friend {"age" "23"} (str "Friend" i))))
                  (.write o "\n")))

    (with-open [o (writer "test.xml")]
      (.write o "<friends>\n")
      (doseq [i (range 1 10000000)]
                  (.write o (str (element :friend {"age" "23"} (str "Friend" i))))
                  (.write o "\n"))
      (.write o "</friends>\n"))


(with-open [o (writer "test_1.xml")]
  (doall
   (pmap
   #(.write o (str
                         (element :friend {"age" "23"} (str "Friend" %)) "\n"))
   (range 1 1000000))))

)

(with-open [o (writer "test_2.xml")]
(.write o "<friends>\n")
(doseq [i (range 1 10)]
  (.write o
          (str
           (element :friend
                    {"age" "23"}
                    (str "Friend" i))
           "\n")))
  (.write o "</friends>\n"))



(with-open [o (writer "test_0.xml")]
  (doall
   (pmap
   #(.write o (str
                         (element :friend {"age" "23"} (str "Friend" %)) "\n"))
   (range 1 5))))
