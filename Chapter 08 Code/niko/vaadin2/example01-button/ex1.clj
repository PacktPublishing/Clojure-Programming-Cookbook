(ns ex1
  (:import [com.vaadin.ui
     VerticalLayout
     Label
     TextField
     Button
     Button$ClickListener]))
 
(defn main [ui]
	(let [layout (VerticalLayout.)
        first-name (TextField.)
        submit-button (Button. "Submit")]
    (.addComponent layout (Label. "First Name"))
    (.addComponent layout first-name)
    (.addComponent layout submit-button)
    (.addListener submit-button 
        (proxy [Button$ClickListener] []
          (buttonClick [event]
            (println (.getValue first-name)))))
    layout))
