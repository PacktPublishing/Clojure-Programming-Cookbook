(ns melting.withmqttalerts
	(:require 
	[clojurewerkz.machine-head.client :as mh]
	[clojurewerkz.meltdown.reactor :as mr]
	[clojurewerkz.meltdown.streams :as ms]
	[clojurewerkz.meltdown.selectors :as sel]))


(def conn (mh/connect "tcp://127.0.0.1:1883" (mh/generate-id)))
; (def reactor 
; 	(mr/create 
; 		:dispatcher-type :ring-buffer 
; 		:event-routing-strategy :broadcast))

(def reactor 
	(mr/create))

(defn proxy-to-meltdown 
	[^String topic _ ^bytes payload]	
	(mr/notify reactor topic (String. payload)))

(mh/subscribe 
	conn 
	["#"] 
	proxy-to-meltdown)

(mr/on reactor 
	(sel/match-all)
	(fn [event]
	  	(println event)))	

; (def res (atom nil))
; (def sum (reduce* #(+ %1 %2) 0 channel2))
; (ms/consume sum #(reset! res %))
