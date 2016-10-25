(ns quasarland.others
  (:use [co.paralleluniverse.pulsar core actors])
  (:refer-clojure :exclude [promise await]))


(let [actor1 (spawn #(Fiber/sleep 1000))
      actor2 (spawn
              (fn []
               (let [w (watch! actor1)]
               (receive
                [:exit w actor reason] (println "bye .." actor)))))])

 (let [actor 
        (spawn #(do
           (set-state! (System/currentTimeMillis))
           (set-state! (- (receive) @state))
           @state))]
               (sleep 2000)
               (! actor (System/currentTimeMillis))
               (join actor))