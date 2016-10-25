(ns freacting.core
  (:require [freactive.core :refer [atom cursor lens-cursor rx]]))


(def people (atom 0))
(def total (atom 0))
(def last-person (atom ""))

(def getter identity)

(defn setter 
	[my-atom-state new-cursor-state]
  (swap! total inc)
  new-cursor-state)

(def my-lens 
	(lens-cursor people getter setter))

(defn i-meet[person]
	(reset! last-person person)
    (swap! my-lens inc))