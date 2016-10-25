(ns clojure_java.progress)

(defn -main[& args]
	(let [bar (bar.ProgressBar. 50)]
		(dotimes [i 11]
			(Thread/sleep 100)
			(.printProcessBar bar (* i 10))
			)
		(println)
		))