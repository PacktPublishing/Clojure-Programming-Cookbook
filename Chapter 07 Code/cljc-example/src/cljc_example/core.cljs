(ns cljc-example.core
      (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(js/alert "Hello!")
(println "This text is printed from src/cljc-example/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(def app-state1 (atom {:text "Hello world!!!"}))

(om/root
  (fn [data owner]
    (reify om/IRender
      (render [_]
        (dom/h1 nil (:text data)))))
  app-state1
  {:target (. js/document (getElementById "app"))})


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
