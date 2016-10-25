(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns hello.simpleui2
  (:use [hello.net])
  (:import [System.Windows.Forms ComboBox MessageBox Button Form Label])
  (:import [System.Drawing Size Point]))

(defn -main
  [& args]

 (let [
  form (Form.)
  load-btn (Button.)
  title-label (Label.)
  combo (ComboBox.)
  title-string "A very simple UI! (part 2) "
  ]

; Title Label
(doto title-label
  (.set_Text title-string)
  (.set_Location (Point. 12 12))
  (.set_Size (Size. 360 22)))

(.AddRange 
  (.get_Items combo)
  (into-array ["osaka" "Tokyo" "Sapporo"]))

(doto combo
  (.set_Location (Point. 12 78))
  (.set_Size (Size. 360 22))
  (.add_SelectedIndexChanged 
    (gen-delegate EventHandler [sender args]
      (let [city  (str (.Text combo)) ]
      (MessageBox/Show (str "sunset time in " city  " : " (hello.net/get-sunset city "jp") ))))))

(doto (.Controls form)
  (.Add title-label)
  (.Add combo))

(doto form
  (.set_Text title-string)
  (.set_Size (Size. 400 200))
  .ShowDialog))

)