(ns simple
	(:gen-class
   	:methods [[#^{:static true} one [] String ]]))

(defn -one [this]
	"1"
	)