(use 'slack-rtm.core)

;; slack to file
(def token "xoxb-64790720018-RhD3YOsLWJ4yc13GlX0CDeQe")
(defn rcv-msg[evt]
	(spit "evt.log" (:message evt) :append true)
	)
(def rtm-conn (connect token :on-receive rcv-msg))
