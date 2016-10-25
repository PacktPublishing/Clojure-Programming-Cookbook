(require '[clj-yaml.core :as yaml])

(println
(yaml/generate-string
  [{:name "John", :age 33}
   {:name "Nick", :age 27}]))


(yaml/parse-string "
- {name: John, age: 33}
- name: Nick
  age: 27
")
