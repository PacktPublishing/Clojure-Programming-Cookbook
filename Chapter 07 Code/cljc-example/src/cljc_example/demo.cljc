(ns cljc-example.demo
  #?(:clj
       (:require [clojure.tools.logging :as log])
     )
  #?(:clj
      (:import [javax.swing JLabel JFrame JPanel JButton]))
    #?(:clj
     (:require [schema.core :as s])
     :cljs
       (:require [schema.core :as :include-macros true])
       )
        #?(:clj
       (:require [quil.core :as q])
       :cljs
       (:require [quil.core :as q :include-macros true]))
  #?(:cljs
       (:require [om.core :as om :include-macros true]
                 [om.dom :as dom :include-macros true])
        )
    )

(def app-state2 (atom {:text "Hello world!!!!!"}))

#?(
   :cljs
   (om/root
  (fn [data owner]
    (reify om/IRender
      (render [_]
        (dom/h1 nil (:text data)))))
  app-state2
  {:target (. js/document (getElementById "app"))})
   )

#?(:clj
   "Hello Clojure !"
   :cljs "Hello Clojurescript !"
   )

(try
  (/ 1 0)
  (catch
      #?(
         :clj java.lang.Exception 
         :cljs ;default
            ) e
      (.getMessage e)))


(defn check-range [v l u]
  (if-not (and (>= v l) (<= v u))
    #?(:clj (throw (Exception. "out of range"))
       :cljs (throw (js/Error. "out of range")
       )
       v)))
;;=> #'cljc-example.demo/check-range

(try
  (check-range 0 1 10)
  (catch
      #?(
         :clj Exception
         :cljs js/Error
         ) e
    #?(:clj (println (.getMessage e))
       :cljs (println (.-message e))
       )))






