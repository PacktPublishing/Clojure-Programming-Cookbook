; Blazar: straightforward, lightning-fast Client/Server fiber-blocking
; HTTP and WebSocket APIs for Clojure, plus a fiber-blocking ring adapter.
;
; Copyright (C) 2014 Fabio Tudone. All rights reserved.
;
; This program and the accompanying materials are dual-licensed under
; either the terms of the Eclipse Public License v1.0 as published by
; the Eclipse Foundation
;
;   or (per the licensee's choosing)
;
; under the terms of the GNU Lesser General Public License version 3.0
; as published by the Free Software Foundation.

; TODO Review for compliance with https://github.com/bbatsov/clojure-style-guide

(ns ^{ :author "circlespainter" :internal true } blazar.utils-profile
  "Profile-specific internal Blazar utilities"
  (:use ns-tracker.core))

; Auto-reload support, development mode
(defn start-nstracker-profile! [thread check-namespace-changes]
  (if (not @thread)
    (let
      [track (ns-tracker ["src" "checkouts"])
       t (Thread. #(while true (check-namespace-changes track)))]
      (dosync
        (doto t (.setDaemon true) (.start))
        (alter thread (fn [ov] t))))))