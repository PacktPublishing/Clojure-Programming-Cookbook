(ns asyncing.withmapv
	(:require [clojure.core.async :as async]))

; waiting for n channels
http://stackoverflow.com/questions/31858846/waiting-for-n-channels-with-core-async

(def c1 (async/chan))
(def c2 (async/chan))

(def out
  (async/thread
    (loop [cs [c1 c2] vs []]
      (let [[v p] (async/alts!! cs)
            cs (filterv #(not= p %) cs)
            vs (conj vs v)]
        (if (seq cs)
          (recur cs vs)
          vs)))))

(async/>!! c1 :foo)
(async/>!! c2 :bar)

(async/<!! out)


(<!! (a/map vector [ch1 ch2 ch3]))