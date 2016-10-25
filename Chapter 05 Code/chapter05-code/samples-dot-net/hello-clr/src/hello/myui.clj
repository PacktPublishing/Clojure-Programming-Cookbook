(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns hello.myui
  (:use [hello.net])
  (:import [System.Windows.Forms Button ComboBox GroupBox CheckedListBox Form TableLayoutPanel MessageBox PaintEventHandler Label TextBox])
  (:import [System.Drawing Size Point Font FontStyle])
  (:gen-class))

(defn -main
  [& args]

 (let [form (Form.)
  load-btn (Button.)
  title-lbl (Label.)
  db-lbl (Label.)
  db-txt (TextBox.)
  migrate-btn (Button.)
  btn-2 (Button.)
  combo (ComboBox.)
  title-str "Rob's Table Migration Tool ClojureCLR style!"
  ]

   ; Title Label
    (doto title-lbl
      (.set_Text title-str)
      (.set_Location (Point. 12 12))
      (.set_Size (Size. 360 22))
      ;(.set_Font (Font. "Microsoft Sans Serif" 12.0 FontStyle/Bold GraphicsUnit/Point 0))
      )

    (doto combo
      (.set_Location (Point. 12 178))
      (.set_Size (Size. 360 22)))

    (.AddRange 
      (.get_Items combo)
      (into-array["osaka" "Tokyo" "Sapporo"]))

    (.add_SelectedIndexChanged combo
      (gen-delegate EventHandler [sender args]
        (let [city  (str (.Text combo)) ]
        (MessageBox/Show (str "sunset time in " city  " : " (hello.net/get-sunset city "jp") ))
      )))
    
    ; CheckedListBox
    ; (doto chkd-list
    ;   (.set_Location (Point. 4 20))
    ;   (.set_Name "tableCheckedList")
    ;   (.set_Size (Size. 345 304)))

    (doto btn-2
      (.set_Name "loadButton")
      (.set_Location (Point. 12 278))
      (.set_Text "Button 2")
      (.set_Size (Size. 360 22))
      (.add_Click
       (gen-delegate EventHandler [sender args]
          (.set_Text title-lbl "clicked! button2 ")
          (MessageBox/Show "hello2")
          (System.Console/WriteLine "Now we use Console Writeline"))
          ))

    ; Button
    (doto load-btn
      (.set_Name "loadButton")
      (.set_Location (Point. 12 378))
      (.set_Text "Button 1")
      (.set_Size (Size. 360 22))
      (.add_Click
       (gen-delegate EventHandler [sender args]
          (.set_Text title-lbl "clicked button1 !")
          ;(MessageBox/Show "hello")
          (System.Console/WriteLine "Now we use Console Writeline"))
          ))
      

(doto (.Controls form)
  (.Add title-lbl)
  (.Add db-lbl)
  (.Add db-txt)
  (.Add combo)
  (.Add load-btn)
  (.Add migrate-btn)
  (.Add btn-2)
  )

(doto form
  (.set_Text title-str)
  (.set_Size (Size. 400 460))
  .ShowDialog))

)