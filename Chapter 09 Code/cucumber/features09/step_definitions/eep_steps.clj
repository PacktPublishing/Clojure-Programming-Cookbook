(use 'clojure.test)
(require '[clojurewerkz.eep.emitter :as eem])


(def emitter 
 	(eem/create {})) ;; create the emitter

(When #"^the emitter is created$" [])

(When #"^the aggregator is created$" []
  (eem/defaggregator
  emitter ;; the emitter
  :accumulator ;; the event type to attach to
  (fn [acc i] (+ acc i)) ;; the function to apply to the stream
  0) ;; the initial state
  )

(When #"^some data is emitted to the aggregator$" []
    ;; send 0-9 down the stream
	(doseq [i (range 10)]
	  (eem/notify emitter :accumulator i)))


(Then #"^the state of the aggreagtor should be (\d+)$" [arg1]
	;; state is 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9
	(assert (= 45 
	  (eem/state (eem/get-handler emitter :accumulator)))))