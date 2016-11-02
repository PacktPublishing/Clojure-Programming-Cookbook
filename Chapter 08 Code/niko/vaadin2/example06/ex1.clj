(ns ex1
	(:require [telegram :as telegram :refer :all])
	(:import
		[com.vaadin.shared.communication PushMode]
		[com.vaadin.ui VerticalLayout Label UI TextField Notification]))

(defn runme [base msg]
	(let [async-ui-update
		(proxy [Runnable] [] (run []
			(.setValue base msg)))]
       	  (-> 
       		(UI/getCurrent) 
       		(.access async-ui-update))))

(defn main [ui]
	(.setPushMode
		(.getPushConfiguration ui)
		(PushMode/AUTOMATIC))
	(let [base (VerticalLayout.) cpu-label (Label. "--")]
		
	(doto base
	  (.addComponent (Label. "Telegram Fun"))
		(.addComponent cpu-label))

		(add-watch telegram/last-value :listener
		  (fn [key atom old-state new-state]
		  	(println new-state)
		  	(runme cpu-label new-state)))

    base))
