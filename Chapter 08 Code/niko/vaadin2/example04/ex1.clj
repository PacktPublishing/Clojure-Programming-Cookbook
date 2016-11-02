(ns ex1
	(:import 
		[com.vaadin.data Property$ValueChangeListener]
		[com.vaadin.ui
     VerticalLayout
     Label
     UI
     Notification
     TextField
     Notification]))

;;;
;	Homemade Reactive Vaadin 
;;;

(def last-value 
	(atom ""))

(defn runme [msg]
	(let [async-ui-update 
		(proxy [Runnable] [] (run [] (Notification/show msg)))]
       (-> (UI/getCurrent) (.access async-ui-update))))

(defn my-layout [layout]
  (.addComponent layout (Label. "Write something"))
  
  (let [first-name (TextField. )]
    (.addComponent layout first-name)

    (.addValueChangeListener first-name 
    	(proxy [Property$ValueChangeListener] []
    	  (valueChange [event]
    	  	(dosync (reset! last-value 
    		 (-> event (.getSource) (.getValue)))))))))

(defn main [ui]
	(.setPushMode (.getPushConfiguration ui) (com.vaadin.shared.communication.PushMode/AUTOMATIC))

	(let [base (VerticalLayout.)]
    (my-layout base)

    (add-watch last-value :listener
      (fn [key atom old-state new-state]
        (runme new-state)))

    base))