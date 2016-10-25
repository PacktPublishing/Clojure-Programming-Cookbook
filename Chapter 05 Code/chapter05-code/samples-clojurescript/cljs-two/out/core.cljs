(ns core)

(enable-console-print!)

(defn click-fn[]
	(println "Clicked!"))

(def $ js/jQuery)

(defn click-slide-fn[]
	(if (.is ($ "div:first") ":hidden")
		(. ($ "div") slideDown "slow")
		(. ($ "div") hide)))

(.click 
	($ "body")
	click-slide-fn)