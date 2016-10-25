(ns quasarland.blazar
  (:require
    [co.paralleluniverse.pulsar.core :as pc]
    [blazar.http.server :as bs])
  (:gen-class)
  )

(defn -main 
	(pc/join 
	(pc/spawn-fiber 
		#(bs/start-fiber-ring-adapter 
			"localhost" 
			8080 
			(fn [req] "Hello world!")))))