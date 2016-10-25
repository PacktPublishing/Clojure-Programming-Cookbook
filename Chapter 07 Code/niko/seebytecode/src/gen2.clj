(ns gen2
	(:gen-class [:main true]))

(defn hello [b]
  (let[ a 1]
    (+ b a)
    )
  )

(defn -main[]
	(println (hello 1)))
