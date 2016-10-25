(ns hello.c2)

(defn -main[& args]
	(System.Console/WriteLine "Now we use Console Writeline")
	(let [
		filename "test.txt"
		file  (System.IO.StreamWriter. filename)]
		(.WriteLine file "===Hello Clojure ===")
		(.WriteLine file (str args))
		(.Close file)
		(println (slurp filename)))
	)