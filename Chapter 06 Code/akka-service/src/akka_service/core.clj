(ns akka-service.core
  (:require  [okku.core :as okku]))

(def actor-system (okku/actor-system "actor-system-1"
                                     :hostname "localhost" :port 2552))

(defn -main [& args]
  (println "launching akka service ...")
  (okku/spawn
   (okku/actor
    (okku/onReceive [m]
                    (println m)))
   :in actor-system :name "hello-actor")
  )

