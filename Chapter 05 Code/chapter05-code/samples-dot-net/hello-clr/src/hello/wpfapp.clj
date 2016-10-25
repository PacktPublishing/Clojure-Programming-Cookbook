
(assembly-load-with-partial-name "PresentationFramework")
(assembly-load-with-partial-name "PresentationCore")
(assembly-load-with-partial-name "WindowsBase")
(assembly-load-with-partial-name "System.Xml")
(assembly-load-with-partial-name "System")

(ns hello.wpfapp 
  (:import (System.Windows Application Window))
  (:import (System.Windows.Threading Dispatcher DispatcherPriority))
  (:import (System.Threading ApartmentState ThreadStart Thread AutoResetEvent))
  (:import (System.IO File))
  (:import (System.Xml XmlReader))
  (:import (System.Windows.Markup XamlReader))
  (:import (System))
  (:gen-class))

(defn -hello[this]

  )
        
(defn run[& args]
  (let [app (new Application)
        win (-> "MyWpfUI.xaml"
              File/OpenText
              XmlReader/Create
              XamlReader/Load)]
       (pr "my running app!")
       (.Run app win)))

(defn sta-thread [func]
  (let [delegate (gen-delegate ThreadStart [] (func))
        thread (doto (new Thread delegate)
                 (.SetApartmentState ApartmentState/STA)
                 (.Start))]
    thread))

(defn -main [& args]
  (let [uithread (sta-thread (partial run args))])) 