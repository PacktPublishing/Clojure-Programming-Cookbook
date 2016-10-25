(require '[clojure.tools.reader.edn :as edn])
;=> nil
(edn/read-string "1")
;=> 1
(edn/read-string "#inst \"2010-11-12T13:14:15.666\"")
;=> #inst "2010-11-12T13:14:15.666-00:00"
(let [my-unknown (fn [tag val] {:unknown-tag tag :value val})]
   (edn/read-string {:default my-unknown} "#foo bar"))
;=> {:unknown-tag foo, :value bar}
(edn/read-string {:readers {'foo (constantly 1)}} "#foo bar")
;=> 1

(edn/read-string "{:friends [{:friend {:name \"Nick\" :age 23}}]}")

(read-string "{:friends [{:friend {:name \"Nick\" :age 23}}]}")

(edn/read-string
 (str
 {:friends [{:friend {:name "Nick" :age 23}}]}))
