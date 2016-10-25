(ns riemaning.core)


(require '[riemann.client :as r])
(def c (riemann.client/tcp-client {:host "riemann-host"}))
(-> c (riemann.client/send-event {:service "first-service" :state "ok"}))

(require '[riemann.client :as r])
(def c (r/tcp-client {:host "riemann-host"}))
(-> c (r/send-event {:service "foo" :state "ok"})
      (deref 5000 ::timeout))
@(r/query c "state = \"ok\"")


