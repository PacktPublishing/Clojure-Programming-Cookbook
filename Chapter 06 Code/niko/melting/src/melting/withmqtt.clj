(ns melting.withmqtt
	(:require 
	[clojurewerkz.machine-head.client :as mh]
	[clojurewerkz.meltdown.reactor :as mr]
	[clojurewerkz.meltdown.selectors :as sel]))

; connect to mqtt
(def conn 
	(mh/connect "tcp://127.0.0.1:1883" 
		(mh/generate-id)))

; create a reactive point
(def reactor 
	(mr/create))

; function to proxy messages from mqtt to 
; the above created reactive point
(defn proxy-to-meltdown 
	[^String topic _ ^bytes payload]	
	(mr/notify reactor topic (String. payload)))

; subcribe to mqtt messages and proxy
; all message to the function defined
(mh/subscribe 
	conn 
	["#"] 
	proxy-to-meltdown)

; lastly, when the reactor receives any message
; print the event
(mr/on reactor 
	(sel/match-all)
	(fn [event]
	  	(println event)
	  	;(println (eval (read-string (event :data))))
	  	))