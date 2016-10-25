(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns hello.c3
	(:import [System.Windows.Forms MessageBox])
	(:gen-class)
	)

(defn -main[& args]
  (MessageBox/Show "Hello world")) 