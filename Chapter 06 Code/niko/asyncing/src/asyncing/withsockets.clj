(ns asyncing.withsockets
  (:require [com.gearswithingears.async-sockets :refer :all]
            [clojure.core.async :as async]))


(defn c-to-f [c] 
  (+ (* 1.8 (Integer/parseInt c)) 32))

; SERVER 
(defn handle-connection [socket]
  (async/go-loop []
    (when-let [line (async/<! (:in socket))]
      (async/>! (:out socket) (str "CELCIUS TO FAHRENHEIT: " (c-to-f line)))
      (recur))))

(let [server (socket-server 12346)
  in-connections (:connections server)]
  (async/go-loop []
    (when-let [connection (async/<! in-connections)] 
      (handle-connection connection)
      (recur))))

; CLIENT
(ns asyncing.core
  (:require [com.gearswithingears.async-sockets :refer :all]
            [clojure.core.async :as async]))

(def socket (socket-client 12346 "localhost"))
(async/>!! (:out socket) "10")
(println (async/<!! (:in socket)))
; CELCIUS TO FAHRENHEIT: 50.0

(close-socket-client socket)