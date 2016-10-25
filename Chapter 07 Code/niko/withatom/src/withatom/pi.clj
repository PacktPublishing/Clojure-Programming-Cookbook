(use '[clojure.java.shell])
(defn set-led [n] (sh "sh" "-c" (str "echo " n " > /sys/class/leds/led0/brightness")))

(dotimes [_ 10]
  (set-led 0)
  (Thread/sleep 500)
  (set-led 1)
  (Thread/sleep 500))
