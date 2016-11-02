(ns todo.core
  (:require [functional-vaadin.core :refer :all]
            [functional-vaadin.rx.observers :as obs]
            [functional-vaadin.rx.operators :as ops])
  (:import (com.vaadin.ui Alignment CheckBox Table$Align Label Table)
           (com.vaadin.shared.ui.label ContentMode)
           (com.vaadin.ui.themes ValoTheme)
           (com.vaadin.server FontAwesome)
           (com.vaadin.data Container$Filter)
           (com.vaadin.data.util IndexedContainer)
           (org.eclipse.jetty.server Server)
           (org.eclipse.jetty.servlet ServletContextHandler)
           (com.vaadin.server VaadinServlet))
  )

(deftype ToDoFilter [cmp-fn]
  Container$Filter
  (passesFilter [this itemId item]
    (cmp-fn (.getValue (.getValue (.getItemProperty item "check-box")))))
  (appliesToProperty [this propertyId] (= propertyId "check-box"))
  )

(defn ->CompletedFilter "Return a filter that shows only completed items" []
  (->ToDoFilter (fn [v] v)))

(defn ->PendingFilter "Return a filter that shows only pending items" []
  (->ToDoFilter (fn [v] (not v))))

(defn data-row-for [item]
  (let [cb (check-box)
        lbl (label item ContentMode/HTML)]
    (->> (obs/value-changes cb)
      (ops/consume-for lbl
        (fn [l {:keys [source event]}]
          (.setValue l (if (.getValue source) (str "<s style=\"color: #a3a3a3\">" item "</s>") item)))))
    (object-array [cb lbl])))

(def max-page-length 10)

(defn set-item-count [ui table n]
  (.setValue
    (componentNamed :item-count ui)
    (str n " item" (if (> n 1) "s")))
  (.setVisible (.getParent table) (not (zero? n)))
  )

(defn inc-item-count [ui table]
  (let [n (.incItemCount ui)]
    (.setPageLength table (min n max-page-length))
    (set-item-count ui table n))
  )

(defn dec-item-count [ui table]
  (let [n (.decItemCount ui)]
    (.setPageLength table (min n max-page-length))
    (set-item-count ui table n)))

(defn add-todo-item [table {:keys [action sender target]}]
  (let [ui (.getUI table)]
    (.addItem table (data-row-for (.getValue target)) nil)
    (.clear target)
    (inc-item-count ui table)
    ))

(defn remove-completed [table]
  (let [^IndexedContainer data-source (.getContainerDataSource table)
        filters (.getContainerFilters data-source)
        ui (.getUI table)]
    (.removeAllContainerFilters data-source)
    (doseq [itemId (.getItemIds data-source)]
      (when (.getValue (.getValue (.getContainerProperty data-source itemId "check-box")))
        (dec-item-count ui table)
        (.removeItem data-source itemId)))
    (doseq [f filters] (.addContainerFilter data-source f)))
  )

(defn select-items [source table filter]
  (let [ui (.getUI table)]
    (.removeAllContainerFilters (.getContainerDataSource table))
    (if filter (.addContainerFilter (.getContainerDataSource table) filter))
    (doseq [b-id [:button-all :button-complete :button-todo]]
      (.setEnabled (componentNamed b-id ui) (not= b-id (keyword (.getId source))))))
  )

(defn animate [ui]
  ;; Enter adds an item
  (->>
    (obs/with-action-events (componentNamed :text-panel ui) [{:name "Enter" :keycode 13}])
    (ops/consume-for (componentNamed :todo-list ui) add-todo-item))

  ;; Selection button observers for all three selection buttons
  (let [table (componentNamed :todo-list ui)]
    (->>
      (obs/button-clicks (componentNamed :button-all ui))
      (ops/consume-for table
        (fn [t {:keys [source]}] (select-items source t nil))))
    (->>
      (obs/button-clicks (componentNamed :button-complete ui))
      (ops/consume-for table
        (fn [t {:keys [source]}] (select-items source t (->CompletedFilter)))))
    (->>
      (obs/button-clicks (componentNamed :button-todo ui))
      (ops/consume-for table
        (fn [t {:keys [source]}] (select-items source t (->PendingFilter)))))

    ; Clear completed items
    (->>
      (obs/button-clicks (componentNamed :button-clear ui))
      (ops/consume-for table
        (fn [t {:keys [source]}] (remove-completed t))))

    ; Header click to mark all
    (->>
      (obs/header-clicks table)
      (ops/consume-for table
        (fn [t {:keys [source event propertyId]}]
          (when (= propertyId "check-box")
            (select-items (componentNamed :button-all (.getUI t)) t nil)
            (doseq [itemId (.getItemIds t)]
              (.setValue (.getValue (.getContainerProperty t itemId "check-box")) true))))))
    ))

(defn button-panel []
  (horizontal-layout {:width "99%" :height "50px" :alignment Alignment/MIDDLE_CENTER}
    (label "" {:id :item-count :alignment Alignment/MIDDLE_LEFT})
    (horizontal-layout {:spacing true :alignment Alignment/MIDDLE_CENTER}
      (button "All" {:id :button-all :styleName ValoTheme/BUTTON_TINY :enabled false})
      (button "Completed" {:id :button-complete :styleName ValoTheme/BUTTON_TINY})
      (button "To Do" {:id :button-todo :styleName ValoTheme/BUTTON_TINY })
      )
    (button "Clear completed" {:id :button-clear
                               :styleName ValoTheme/BUTTON_TINY :alignment Alignment/MIDDLE_RIGHT
                               })))

(defn todo-ui-spec [ui]
  (defui ui
    (vertical-layout {:width "100%" :margin [:top]}
      (label {:value "ToDo Niko 17" :alignment Alignment/MIDDLE_CENTER :widthUndefined [] :height "70px"})
      (panel {:id :text-panel :alignment Alignment/MIDDLE_CENTER :width "50%"}
        (text-field {:id :entry-field
                     :immediate true :sizeFull []
                     :inputPrompt "Enter something to do"}))
      (vertical-layout {:id :table-panel :heightUndefined [] :width "50%"
                        ;:margin [:left :right]
                        :visible false
                        :alignment Alignment/MIDDLE_CENTER}
        (table {:id :todo-list
                :alignment Alignment/MIDDLE_CENTER :width "99%" :pageLength 0
                :sortDisabled true}
          (table-column "check-box" {:width 60 :header "" :icon FontAwesome/CHEVRON_DOWN :type CheckBox :alignment Table$Align/CENTER})
          (table-column "list-item" {:type Label :header ""}))
        (button-panel)
        )))
  (animate ui)
  )

(defn run-jetty [ui-name bg?]
  (let [server (Server. 8080)
        ^ServletContextHandler context (ServletContextHandler. ServletContextHandler/SESSIONS)]
    (.setContextPath context "/")
    (.setInitParameter context "UI" ui-name)
    (.setInitParameter context "legacyPropertyToString" "true")
    (.setResourceBase context "dev-resources/public")

    (.setHandler server context)
    (.addServlet context VaadinServlet "/*")

    (.start server)
    (if bg?
      server
      (.join server)
      )))
