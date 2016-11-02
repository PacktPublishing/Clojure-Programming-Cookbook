(ns ex1
	(:require [cpu :refer :all :as cpu])
	(:import
		[com.vaadin.shared.communication PushMode]
		[com.vaadin.ui VerticalLayout Label UI TextField Notification]))

(defn runme [lbl msg]
	(let [async-ui-update
		(reify Runnable 
			(run [_]
			 (.setValue lbl msg)))]
       	  	(doall 
       	  	    (println ".")
       	  		(-> 
       		    (UI/getCurrent) 
       		    (.access async-ui-update)))))

(defn main [ui]
	(.setPushMode
		(.getPushConfiguration ui)
		(PushMode/AUTOMATIC))

	(let [
		base (VerticalLayout.) 
		cpu-label (Label. "--")]
	(doto base
	  (.addComponent (Label. "CPU Monitoring"))
	  (.addComponent cpu-label))

	(add-watch cpu/last-value :listener
	  (fn [key atom old-state new-state]
	  	(runme cpu-label new-state)))

    base))
