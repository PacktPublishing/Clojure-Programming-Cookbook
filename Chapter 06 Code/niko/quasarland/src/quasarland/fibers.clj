(ns quasarland.fibers
  (:use [co.paralleluniverse.pulsar core actors])
  (:refer-clojure :exclude [promise await]))


(spawn-fiber (fn [] (sleep 1000) (println "slept well")))

(let [
    ch (channel)
    fiber (spawn-fiber
            (fn []
              (let [m1 (rcv ch)
                    m2 (rcv ch)
                    m3 (rcv ch)]
                (+ m1 m2 m3))))]
(sleep 20)
(snd ch 10)
(sleep 20)
(snd ch 10)
(sleep 20)
(snd ch 20)
(join fiber))


(let [ch (channel 0 :block true true)
    f #(rcv ch)
    fiber1 (spawn-fiber f)
    fiber2 (spawn-fiber f)]
(snd ch "m1")
(snd ch "m2")
(close! ch)
#{(join fiber1) (join fiber2)})
; #{"m1" "m2"}))


(let [ch (channel)
      fiber (spawn-fiber #(rcv-into [] ch 1000))]
        (snd-seq ch (range 200))
        (close! ch)
        (join fiber))
; [0 1 2 3 4 ...])
