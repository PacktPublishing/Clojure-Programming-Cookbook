(ns ex1
  (:import [com.vaadin.ui VerticalLayout Label]))

;;;
; Simple Example with a Label and some text
;;;

(defn main[ui]
	(let [
		layout  (VerticalLayout.)
		label   (Label. "Hello World!")]
		(.addComponent layout label)))