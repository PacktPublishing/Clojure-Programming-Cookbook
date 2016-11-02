(ns vaadin2.vaadin2ui
    (:gen-class
     :name vaadin2.vaadin2ui
     :extends com.vaadin.ui.UI))

(defn -init
  [main-ui request]
  (clojure.lang.RT/loadResourceScript "ex1.clj")
  (.setContent 
    main-ui 
    (.invoke (clojure.lang.RT/var "ex1" "main") main-ui)))