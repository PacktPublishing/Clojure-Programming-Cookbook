(ns onerepltorulethemall.core)


; (use '[clojure.tools.nrepl.server :only (start-server stop-server default-handler)])
; (use '[inspector.middleware :only (wrap-inspect)])

; (defonce server 
;   (start-server
;     :port 4006
;     :handler (default-handler #'wrap-inspect)))

(use '[clojure.tools.nrepl.server :only (start-server stop-server)])
(defonce server (start-server :port 7888))