(ns blazarland.core
  (:require
    [co.paralleluniverse.pulsar.core :as pc]
    [blazar.http.server :as bs]))

(defn -main []
	(pc/join 
	(pc/spawn-fiber 
		#(bs/start-fiber-ring-adapter 
			"0.0.0.0" 
			8080
			(fn [req] "Hello fiber world!")))))