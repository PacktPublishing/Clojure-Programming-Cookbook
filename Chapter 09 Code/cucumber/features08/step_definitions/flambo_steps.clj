(use 'clojure.test)
(use 'flambing.core) 
(require '[clojure.java.shell :as sh])

(def text-file 
	(atom ""))
(def lines 
	(atom 0))

(Given #"^the following text file: \"(.*?)\"$" [file]
	(reset! text-file file)
	(reset! lines 0))

(Given #"^we count the number of lines using shell$" []
	(reset! 
		lines 
		(Long/parseLong 
			(clojure.string/trim 
				(:out 
					(sh/sh "bash" "-c" 
						(str "wc -l " @text-file " | awk '{print $1}'")))))))

(Then #"^we wanna check the number of lines is the same$" []
	(let [res (word-frequencies @text-file)] 
	(assert (= @lines res))))