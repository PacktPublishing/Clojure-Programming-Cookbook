(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns simpleform
  (:import [System.Windows.Forms MessageBox])
  (:gen-class))

(defn -main
  [& args]
  (MessageBox/Show (str "Hi from clojure-clr!" (.Date DateTime/Now)) "Clojure-CLR Dialog" ))