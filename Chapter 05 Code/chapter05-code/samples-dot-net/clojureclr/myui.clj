(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

(ns myui
  (:import [System.Windows.Forms Button GroupBox CheckedListBox Form TableLayoutPanel MessageBox PaintEventHandler Label TextBox])
  (:import [System.Drawing Size Point Font FontStyle]))

(defn -main
  [& args]

 (let [form (Form.)
  load-btn (Button.)
  title-lbl (Label.)
  db-lbl (Label.)
  db-txt (TextBox.)
  migrate-btn (Button.)
  grp-box (GroupBox.)
  chkd-list (CheckedListBox.)
  title-str "Rob's Table Migration Tool ClojureCLR style!"
  ]

   ; Title Label
    (doto title-lbl
      (.set_Text title-str)
      (.set_Location (Point. 12 12))
      (.set_Size (Size. 360 22))
      ;(.set_Font (Font. "Microsoft Sans Serif" 12.0 FontStyle/Bold GraphicsUnit/Point 0))
      )
    
    ; GroupBox
    (doto grp-box
      (.set_Text "Database Tables")
      (.set_Location (Point. 12 42))
      (.set_Size (Size. 360 333)))

    ; CheckedListBox
    (doto chkd-list
      (.set_Location (Point. 4 20))
      (.set_Name "tableCheckedList")
      (.set_Size (Size. 345 304)))

    ; Button
    (doto load-btn
      (.set_Name "loadButton")
      (.set_Location (Point. 12 378))
      (.set_Text "Load Table Names")
      (.add_Click
       (gen-delegate EventHandler [sender args]
          (.set_Text title-lbl "clicked!")
          (MessageBox/Show "hello")
          (System.Console/WriteLine "Now we use Console Writeline"))
          ))
      

(doto (.Controls form)
  (.Add title-lbl)
  (.Add db-lbl)
  (.Add db-txt)
  (.Add load-btn)
  (.Add migrate-btn)
  (.Add grp-box))

(doto form
  (.set_Text title-str)
  (.set_Size (Size. 400 460))
  .ShowDialog))

)