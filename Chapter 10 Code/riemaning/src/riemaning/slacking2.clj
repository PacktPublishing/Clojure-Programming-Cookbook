(use 'slack-rtm.core)
(require '[riemann.client :as r])

; slack to  riemann
(def c (atom (r/tcp-client {:host "riemann-host"})))

(def token "xoxb-64790720018-RhD3YOsLWJ4yc13GlX0CDeQe")
(defn rcv-msg[evt]
	(-> @c (r/send-event {:service "slack" :description (:message evt)})))
(def rtm-conn (connect token :on-receive rcv-msg))