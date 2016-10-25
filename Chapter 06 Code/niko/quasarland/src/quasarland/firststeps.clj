(ns quasarland.firststeps
  (:use [co.paralleluniverse.pulsar core actors])
  (:refer-clojure :exclude [promise await]))


(defsfn relaxing-afternoon []
  (println "I am relaxing ..."))

(relaxing-afternoon)
; I am relaxing ...

(spawn relaxing-afternoon)

(defsfn relaxing-afternoon-2 []
  (println "I am relaxing ...")
  (sleep 2000)
  (println "I am waking up ...")
  )

(spawn relaxing-afternoon-2)

(defsfn relaxing-afternoon-3 []
  (println "I am relaxing ...")
  (receive
    :wakeup (println "Hey ! Someone woke me up!")))


(def me 
	(spawn relaxing-afternoon-3))

(! me :wakeup)

(defsfn relaxing-afternoon-4 []
  (println "I am relaxing ...")
  (receive
    :bump (do (println "Hey ! Stop bumping me!") (recur))))


(def me 
	(spawn relaxing-afternoon-4))

(! me :bump)
(! me :bump)

(defsfn relaxing-afternoon-5 []
  (println "I am relaxing ...")
  (sleep 1000)
  (! @self :bump)
  (receive
    :bump
    (println "bumping myself!")))

(spawn relaxing-afternoon-5)


(defsfn relaxing-afternoon-6 []
  (println "I am relaxing ...")
  (sleep 1000)
  (! @self [:bump @self])
  (receive
    [:bump who]
    (println who " is bumping!")))

(spawn relaxing-afternoon-6)


(defsfn sleeping-man [] 
  (println "I keep on sleeping ...")
  (receive
    [:wakeup who]
    (do 
    	(println "Someone is telling me to wakeup!")
    	(println "but ...")
    	(recur))))

(defsfn someone [man]
	(sleep 1000)
	(! man [:wakeup @self]))

(def man-asleep (spawn sleeping-man))
(spawn someone man-asleep)
