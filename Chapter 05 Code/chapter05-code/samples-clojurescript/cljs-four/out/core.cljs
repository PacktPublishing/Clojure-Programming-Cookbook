(ns core
	(:require 
		[clojure.browser.repl :as repl]
    ))

; (def canvas-dom 
; 	(.getElementById js/document "canvas"))

; (def monet-canvas 
; 	(canvas/init canvas-dom "2d"))

; (canvas/add-entity monet-canvas :background
;  (canvas/entity {:x 0 :y 0 :w 600 :h 600} ; val
;   nil                       ; update function
;   (fn [ctx val]             ; draw function
;     (-> ctx
;         (canvas/fill-style "#ccff55")
;         (canvas/fill-rect val)))))

(repl/connect "http://localhost:9000/repl")
