(ns quasarland.pingpong2
  (:use [co.paralleluniverse.pulsar core actors])
  (:refer-clojure :exclude [promise await]))

(defsfn ping []
  (if (>= (rand-int 10) 9)
    (do
      (! :pong :finished)
      (println "Missed the ball. Ping finished"))
    (do
      (! :pong [:ping @self])
      (receive
        :pong (println "Ping"))
      (recur))))

(defsfn pong []
  (receive
    :finished (println "Pong finished")
    [:ping ping] (do
                   (println "Pong")
                   (! ping :pong)
                   (recur))))


(register! :pong (spawn pong))
(spawn pong)
(spawn ping )
