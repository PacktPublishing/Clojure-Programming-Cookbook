(ns todo.ToDo
  (:require [todo.core :refer :all])
  (:gen-class :name ^{com.vaadin.annotations.Theme "valo"} todo.ToDo
              :extends com.vaadin.ui.UI
              :state state
              :init initUI
              :methods [
                        [getItemCount [] Long]
                        [incItemCount [] Long]
                        [decItemCount [] Long]
                        ]
              )
  (:import (com.vaadin.ui UI Alignment)
           (com.vaadin.server Sizeable)))

(defn -initUI []
  [[] (atom {:item-count 0})])

(defn -init [this request]
  (todo-ui-spec this)
  )

(defn -getItemCount [this]
  (:item-count @(.state this)))

(defn -incItemCount [this]
  (:item-count (swap! (.state this) #(assoc %1 :item-count (inc (:item-count %1))))
    ))

(defn -decItemCount [this]
  (:item-count (swap! (.state this) #(assoc %1 :item-count (dec (:item-count %1))))))

(defn -main
  ([bg?] (run-jetty "todo.ToDo" bg?))
  ([] (-main false))
  )
