(ns om-next-example.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [cljs-time.local :as l]
            [cljs-time.format :as f]))
;;=> nil            

(enable-console-print!)
;;=> nil

(defn get-local-now-string []
  (f/unparse   
   (f/formatters :date-time-no-ms)
   (l/local-now)))
;;=> #'om-next-example.core/get-local-now-string

(get-local-now-string)
;;=> "2016-07-04T00:25:31+09:00"

(def app-state-date (atom {:date (get-local-now-string)}))
;;=> #'om-next-example.core/app-state-date

(defui DateDisplay
  Object
  (render [this]
    (let [{:keys [date]} (om/props this)]
      (dom/div nil
               (dom/button
                #js {:onClick
                     (fn [e]
                       (swap! app-state-date update-in [:date] get-local-now-string))}
                "Get current time")
               (dom/h2 nil (str "Date : " date))
               ))))
;;=> #object[Function "function (this__41347__auto__,writer__41348__auto__,opt__41349__auto__){
;;=> return cljs.core._write.call(null,writer__41348__auto__,"om-next-example.core/DateDisplay");
;;=> }"]

(def reconciler-date
  (om/reconciler {:state app-state-date}))
;;=> #'om-next-example.core/reconciler-date

(om/add-root! reconciler-date
              DateDisplay (gdom/getElement "app")) 
;;=> #object[om-next-example.core.DateDisplay]

(def app-state-date2 (atom {:date (get-local-now-string)}))
;;=> #'om-next-example.core/app-state-date

(defn read-date [{:keys [state] :as env} key params]
  (let [st @state]
        {:value (get st key :not-found)}))
;;=> #'om-next-example.core/read-date

(defn mutate-date [{:keys [state] :as env} key params]
  (if (= 'get-current-time key)
    {:value {:keys [:date]}
     :action #(swap! state update-in [:date] get-local-now-string)}
    {:value :not-found}))
;;=> #'om-next-example.core/mutate-date

(defui DateDisplay2
  static om/IQuery
  (query [this] [:date])
  Object
  (render [this]
          (let [{:keys [date]} (om/props this)]
            (dom/div nil
               (dom/button
                #js {:onClick
                     (fn [e] (om/transact! this '[(get-current-time)]))
                     } "Get current time")
               (dom/h2 nil (str "Date: " date))))))
;;=> #object[Function "function (this__41642__auto__,writer__41643__auto__,opt__41644__auto__)
;;=> {return cljs.core._write.call(null,writer__41643__auto__,"om-next-example.core/DateDisplay2";}"]

(def date-reconciler2
  (om/reconciler
   {:state app-state-date2
     :parser (om/parser {:read read-date :mutate mutate-date})}))
;;=> #'om-next-example.core/date-reconciler2

(om/add-root! date-reconciler2
  DateDisplay2 (gdom/getElement "app"))






