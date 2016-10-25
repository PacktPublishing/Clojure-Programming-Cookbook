; (System.Reflection.Assembly/LoadWithPartialName "ClassLibrary1")
(System.Reflection.Assembly/LoadFrom ".\\ClassLibrary1.dll")

(ns hello-clr2.core
	(:import [ClassLibrary1 Class1])
	(:gen-class)
	)

(defn -main
  [& args]
  (println  (.compute (Class1.) (first args))))