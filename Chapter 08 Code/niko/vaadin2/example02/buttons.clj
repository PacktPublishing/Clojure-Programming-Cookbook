(ns buttons
	(:import [com.vaadin.ui Button Button$ClickListener Notification]))

(defn button-click-listener
  [action]
  (reify Button$ClickListener
            (buttonClick [_ evt] (action))))

(defn listener [button action]
  (.addListener button (button-click-listener action)))

(defn show-message []
  (Notification/show "clicked again!"))

(defn button [caption action]
  (doto (Button. caption) (listener action)))