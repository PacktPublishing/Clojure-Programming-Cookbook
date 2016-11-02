(ns om-next-example.todo
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))
;;=> nil

(enable-console-print!)
;;=> nil

(def task-state
  (atom {:task
         [{:id 1 :task "Writing the om.next  ui code" :due-date "12/May" :finished false}
          {:id 2 :task "Testing the om.next  ui code" :due-date "10/Jun" :finished false}
          {:id 3 :task "Writing the liberator server code"  :due-date "25/Jun" :finished false}
          {:id 4 :task "Testing the liberator server code" :due-date "01/July" :finished false}
          {:id 5 :task "Integrate test with the ui and server" :due-date "15/Aug" :finished false}]}))
;;=> #'om-next-example.task/task-state

(defmulti read-task om/dispatch)
;;=> nil

(defmethod read-task :task
  [env key params]
  (let [state (:state env)]
    {:value (:task @state)}))
;;=> #object[cljs.core.MultiFn]

(defmulti mutate-task om/dispatch)
;;=> nil

(defn get-next-id [task]
  (->>
   (map :id task) (apply max)  inc))
;;=> #'om-next-example.task/get-next--id

(get-next-id (@task-state :task))
;;=> 6

(defmethod mutate-task 'task/new [env key params]
  (let [
        state (:state env)
        id (get-next-id (:task @state))
        new-task (assoc params :id id)]
    {:action
     (fn []
       (swap! state update :task conj new-task))}))
;;=> #object[cljs.core.MultiFn]

(defn get-index-for-id
  [id task]
  (-> (for [[index task] (map-indexed vector task)
            :when (= id (:id task))]  index)  first))
;;=> #'om-next-example.task/get-index-for-id

(defmethod mutate-task 'task/toggle
 [env key params]
  (let [state (:state env)
        id (:id params)
        index (get-index-for-id id (:task @state))]
    {:action
     (fn []
       (swap! state update-in [:task index :finished] not))}))
;;=> #object[cljs.core.bMultiFn]

(defn delete-task
 [task id]
  (-> (remove #(= id (:id %)) task) vec))
;;=> #'om-next-example.task/delete-task

(defmethod mutate-task 'task/delete
 [env key params]
  (let [state (:state env)
        id (:id params)
        task (delete-task (:task @state) id)]
    {:action
     (fn []
       (swap! state assoc :task task))}))
;;=> #object[cljs.core.MultiFn]

(defui ListView
  static om/IQuery
  (query [this]
         [:id :task :finished :due-date])
  Object
  (render [this]
          (let [{:keys [id task finished due-date]} (om/props this)]
            (dom/tr nil
                    (dom/th nil
                            (dom/input #js
                                        {:type "checkbox"
                                         :className "toggle"
                                         :checked (and finished "checked")
                                         :onChange #(om/transact! this `[(task/toggle {:id ~id})])}))      
                     (dom/th nil
                             (dom/span  #js
                              {:className "id" :style #js {:color (if finished "red" "black")}}
                              (if finished (dom/s  nil id)  id)))
                     (dom/th nil
                             (dom/span  #js
                              {:className "task" :style #js {:color (if finished "red" "black")}}
                                        (if finished (dom/s  nil task)  task)))
                     (dom/th nil
                             (dom/span  #js 
                              {:className "due-date" :style #js {:color (if finished "red" "black")}}
                                        (if finished (dom/s  nil due-date)  due-date)))
                     (dom/th nil
                             (dom/button #js
                                         {:onClick #(om/transact! this `[(task/delete {:id ~id}) :task])}
                                         "delete"))))))
;;=> #object[Function "function (this__47196__auto__,writer__47197__...

(def list-view (om/factory ListView))
;;=> #'om-next-example.task/list-view

(defn set-element-value-by-id!  [id  value]
  (set! (.-value (. js/document (getElementById id))) value))
;;=> #'om-next-example.task/set-element-value-by-id!

(defn get-element-value-by-id [id]
  (.-value (. js/document (getElementById id))))
;;=> #'om-next-example.task/get-element-value-by-id

(defn not-white-space? [s]
  (not (clojure.string/blank? s)))
;;=> #'om-next-example.task/not-white-space?

(defn handle-new-task!  [component _]
  (let [new-task (get-element-value-by-id "new-task")
        due-date (get-element-value-by-id "due-date")]
    (when (and (not-white-space? new-task) (not-white-space? due-date))
      (om/transact! component
                    `[(task/new ~{:task new-task :due-date due-date})])
      (set-element-value-by-id! "due-date" "")
      (set-element-value-by-id! "new-task" ""))))
;;=> #'om-next-example.task/handle-new-task!

(defui RootView
  static om/IQuery
  (query [this]
         (let [subquery (om/get-query ListView)]
           [{:task subquery}]))
  Object
  (render [this]
          (let [props (om/props this)
                task (:task props)]
            (dom/div nil
                     (dom/label nil "Your task =>  ")
                     (dom/input #js {:className "new-task" :id "new-task"
                                     :placeholder "Enter your new task"})
                     " / "
                     (dom/input #js {:className "due-date" :id "due-date"
                                     :placeholder "Due date"})
                     (dom/button #js
                                 {:onClick #(handle-new-task! this %)}
                                 "new")
                     (dom/hr nil)
                     (apply dom/table #js {:style #js {:text-align "left"}}
                            (map list-view task))
                     (dom/hr nil)))))
;;=> #object[Function "function (this__47196__auto__,)...

(def task-reconciler
  (om/reconciler {:state task-state
                  :parser (om/parser {:read read-task :mutate mutate-task})}))
;;=> #'om-next-example.task/task-reconciler

(om/add-root! task-reconciler
              RootView
              (gdom/getElement "app"))
;;=> #object[om-next-example.task.RootView]
