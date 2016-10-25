(ns custom-repl.async
  (:require [com.gearswithingears.async-sockets :refer :all]
            [clojure.core.async :as async]))

(defn eval-everything [socket]
  (async/go-loop []
    (when-let [line (async/<! (:in socket))]
      (async/>! (:out socket) (eval (read-string line)))
      (recur))))

(let [server (socket-server 12349)]
  (async/go-loop []
    (when-let [connection (async/<! (:connections server))] 
      (eval-everything connection)
      (recur))))