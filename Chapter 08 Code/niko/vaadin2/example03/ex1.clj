(ns ex1
  (:import [com.vaadin.ui UI Notification VerticalLayout Label]))

;;;
; Websocket and serverside push !
; http://stackoverflow.com/questions/27964642/vaadin-server-push-from-another-thread
;;; 
(def async-ui-update 
		   (reify Runnable
		   	(run [_] 
              (Notification/show "Let's sleep"))))

(defn runme []
	(reify Runnable
            (run [_]
               (Thread/sleep 3000)
			   ; (Notification/show "Does not work"))))               
               (-> (UI/getCurrent) (.access async-ui-update)))))

(defn main[ui]

	(.setPushMode 
		(.getPushConfiguration ui) 
		(com.vaadin.shared.communication.PushMode/AUTOMATIC))

	(let [layout  (VerticalLayout.)]
		 (.addComponent layout (Label. "I am getting sleepy"))
		 (.start (Thread. (runme)))
		 layout
		 ))