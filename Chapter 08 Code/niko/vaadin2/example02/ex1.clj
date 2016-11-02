(ns ex1
  (:require :reload-all [buttons :as b])
  (:import [com.vaadin.ui VerticalLayout Label]))

(defn- main-layout []
  (doto (VerticalLayout.)
    (.addComponent (Label. "Hello Clojure!"))
    (.addComponent (b/button "Press me" b/show-message))))
          
(defn main [ui]
  (main-layout))