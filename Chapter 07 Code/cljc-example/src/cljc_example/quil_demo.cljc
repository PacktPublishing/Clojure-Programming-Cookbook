(ns cljc-example.quil-demo
  (:require
   #?@(
       :clj
       [[quil.core :as q]
        [quil.middleware :as m]]
       :cljs
       [[quil.core :as q :include-macros true]
        [quil.middleware :as m]
        [om.core :as om :include-macros true]
        [om.dom :as dom :include-macros true]]
       ))
  #?(:clj (:gen-class)))

#?(
   :cljs
   (defn on-js-reload []      
     ))

#?(
   :cljs
       (def app-state (atom {:text "Hello quil demo !"})))

#?(
   :cljs
   (enable-console-print!))

#?(:clj
   (println "Using Clojure !")
   :cljs
   (println "Using Clojurescript !"))

#?(
   :cljs
   (om/root
    (fn [data owner]
      (reify om/IRender
        (render [_] (dom/canvas #js {:id "my-sketch" :height 400 :width 400}))
        ))
    app-state
    {:target (. js/document (getElementById "app"))}))


(defn setup []
  (q/frame-rate 60)
  (q/color-mode :hsb)
  {:color 0 :angle 0})

(defn update-state [state]
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.1)})

(defn draw-state [state]
  (q/background 240)
  (q/fill (:color state) 255 255)
  (let [angle (:angle state)
        x (* 150 (q/cos angle))
        y (* 150 (q/sin angle))]    
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]      
      (q/ellipse x y 50 50))))

(defn run[]
  (q/defsketch my-sketch
    :host "my-sketch"
    :size [500 500]
    :setup setup
    :update update-state
    :draw draw-state
    :middleware [m/fun-mode]))

#?(
   :clj
   (defn -main[& args](run)
     )
   :cljs (run)
   )
