(ns quasarland.pingpong 
  (:use [co.paralleluniverse.pulsar core actors])
  (:refer-clojure :exclude [promise await]))

(defsfn ping [pong]
  (if (>= (rand-int 10) 9)
    (do
      (! pong :finished)
      (println "Missed the ball. Ping finished"))
    (do
      (! pong [:ping @self])
      (receive
        :pong (println "Ping"))
      (recur pong))))

(defsfn pong []
  (receive
    :finished (println "Pong finished")
    [:ping ping] (do
                   (println "Pong")
                   (! ping :pong)
                   (recur))))

(let [a1 (spawn pong)
      b1 (spawn ping a1)])
  