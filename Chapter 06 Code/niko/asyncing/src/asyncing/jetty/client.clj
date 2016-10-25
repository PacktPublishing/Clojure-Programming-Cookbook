(require '[com.keminglabs.jetty7-websockets-async.core :refer [connect!]]
         '[clojure.core.async :refer [chan go >! <! >!! <!!]])

(def c (chan))

(connect! c "ws://localhost:8888/ws")

(go 
	(loop []
      (when-let [ws-req (<! c)]
        (>! (:in ws-req) "Hello remote websocket server!")
        (recur))))

(def c (chan))

(connect! c "ws://localhost:8888/ws")

(def in (atom nil))
(def out (atom nil))

(go 
	(let [ws-req (<! c)]
        (reset! in (:in ws-req))
        (reset! out (:out ws-req))))

(defn printer
  [in]
  (go (while true (println (<! in)))))

(printer @out)
(>!! @in "sushi please")