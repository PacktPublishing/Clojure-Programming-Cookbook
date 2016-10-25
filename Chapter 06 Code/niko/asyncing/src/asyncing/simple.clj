(ns asyncing.simple
    (:require [clojure.core.async :refer :all]))

(defn c-to-f [c] 
  (+ (* 1.8 c) 32))

(defn c-to-fer
  [in]
  (let [out (chan)]
    (go (while true (>! out (c-to-f (<! in)))))
    out))

(def in-chan (chan))
(def c-to-fer-out (c-to-fer in-chan))


(defn printer
  [in]
  (go (while true (println (<! in)))))

(printer c-to-fer-out)

(>!! in-chan 30)
; 86
