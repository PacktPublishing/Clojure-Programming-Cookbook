(require '[com.keminglabs.jetty7-websockets-async.core :refer [configurator]]
         '[clojure.core.async :refer [chan go >! <!]]
         '[ring.adapter.jetty :refer [run-jetty]])

(defn http-handler
  [req]
  {:response 200 :body "HTTP hello" :headers {}})

(def c (chan))

(def ws-configurator
  (configurator c {:path "/ws"}))

(def server
  (run-jetty http-handler {:configurator ws-configurator
                           :port 8888, :join? false}))

(defn websocket-echo-handler [in out]
  (println "new websocket connection...")
  (go (loop []
      (when-let [msg (<! out)]
        (println "MSG:" msg)
        (>! in "ECHO: " msg)
        (recur)))))

(go (loop []
      (let [ws-req (<! c)]
        (websocket-echo-handler (:in ws-req) (:out ws-req))
        (recur))))  