(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns hello.simpleui
  (:import [System.Windows.Forms Button Form Label])
  (:import [System.Drawing Size Point]))

(defn -main
  [& args]

 (let [
  form (Form.)
  load-btn (Button.)
  title-label (Label.)
  title-string "A very simple UI! "
  ]

; Title Label
(doto title-label
  (.set_Text title-string)
  (.set_Location (Point. 12 12))
  (.set_Size (Size. 360 22)))

; Button
(doto load-btn
  (.set_Name "loadButton")
  (.set_Location (Point. 12 78))
  (.set_Text "Button 1")
  (.set_Size (Size. 360 22))
  (.add_Click
   (gen-delegate EventHandler [sender args]
      (.set_Text title-label "clicked button1 !")
      (System.Console/WriteLine "Now we use Console Writeline"))))
      

(doto (.Controls form)
  (.Add title-label)
  (.Add load-btn))

(doto form
  (.set_Text title-string)
  (.set_Size (Size. 400 200))
  .ShowDialog))

)