(defrecord Friend [name])
(def friends
 	[(Friend. "Nico")
       (Friend. "Makoto")
       (Friend. "Nick")])

(pr-str friends)
(read-string (pr-str friends))
(def read-friends (read-string (pr-str friends)))
(type (first read-friends))
(first read-friends)


(spit "target/text.txt" (pr-str friends))
(read-string (slurp "target/text.txt"))
