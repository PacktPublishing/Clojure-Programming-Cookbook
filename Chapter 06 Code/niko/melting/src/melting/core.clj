(ns melting.core)

(require '[clojurewerkz.meltdown.reactor :as mr])
; create a reactive point
(def reactor (mr/create))

(require '[clojurewerkz.meltdown.selectors :refer [$]])

; create a reactive function on a reactive point
(mr/on reactor
	($ "key")
		(fn [event]
    	  (println event)))

; send a message
(mr/notify reactor "key" {:my "payload"})

; {:data {:my payload}, :reply-to nil, :headers {}, :key key, :id #uuid "0438d5e1-f648-11e5-7467-a807d2534884"}


(mr/receive-event reactor ($ "hello-key") (fn [_]
  (Thread/sleep 2000)
  "response"
  ))

;; Sends "data" to "hello-key" and waits for handler to call back
(mr/send-event reactor "hello-key" "data" (fn [event]
                                            (println (:data event))
                                            (println "done")
                                            ))


; selectors

(require '[clojurewerkz.meltdown.selectors :as s :refer [R]])

(mr/on reactor (R "USA.*") (fn [event] (println "USA:" event)))
(mr/on reactor (R "Europe.*") (fn [event] (println "Europe:" event)))

(mr/notify reactor "USA.EastCoast" {:teh :payload}) ;; will fire USA.*
(mr/notify reactor "Europe.Germany" {:das :payload}) ;; will fire Europe.*
(mr/notify reactor "Europe.Sweden" {:das :payload}) ;; will Europe.Sw* and Europe.* handlers
(mr/notify reactor "Europe.Switzerland" {:das :payload}) ;; will Europe.Sw* and Europe.* handlers
(mr/notify reactor "Asia.China" {:teh :payload}) ;; will fire none


; streams
(require '[clojurewerkz.meltdown.streams :as ms :refer [create consume accept map*]])

(def channel (ms/create))
(def incremented-values (map* inc channel))
(def incremented-twice (map* inc incremented-values))
;(def squared-values (map* (fn [i] (* i i)) incremented-values))

(consume incremented-twice (fn [i] (println "Incremented twice: " i)))
(accept channel 1)
(accept channel 2)

(ms/flush channel)

; streams and reduce

(require '[clojurewerkz.meltdown.streams :as ms :refer [create consume accept reduce*]])

(def channel2 (ms/create))
(def res (atom nil))
(def sum (reduce* #(+ %1 %2) 0 channel2))
(ms/consume sum #(reset! res %))

(ms/accept channel2 1)
(ms/accept channel2 2)
(ms/accept channel2 3)
(ms/flush channel2)

@res
; 6

; custom streams

(defn every-fifth-stream
  [upstream]
  (let [counter (atom 0)]
    (ms/custom-stream
     (fn [event downstream]
       (swap! counter inc)
       (when (= 5 @counter)
         (reset! counter 0)
         (accept downstream event)))
     upstream)))

(let [channel (create)
      res (atom nil)
      incrementer (map* inc channel)
      inst-every-fifth-stream (every-fifth-stream incrementer)]
  (consume inst-every-fifth-stream #(reset! res %))
  (accept channel 1)
  (println @res)
  (accept channel 2)
  (println @res)
  (accept channel 3)
  (println @res)
  (accept channel 4)
  (println @res)
  (accept channel 5)
  @res)

(defn alert-stream [upstream]
  (ms/custom-stream
    (fn [event downstream]
      (if (< 10 event)
       (accept downstream event)))
      upstream))

(def channel (ms/create))
(consume (alert-stream channel) (fn [e] (println e)))
(accept channel 1)
; doesn't print
(accept channel 11)
; 12

; graph, combine operation
(require '[clojurewerkz.meltdown.stream-graph :as sg])
(require '[clojurewerkz.meltdown.streams :as ms])

(def res (atom 0))
(def channel (graph  (create)
                     (map* inc
                     (reduce* #(+ %1 %2) 0
					 (consume #(reset! res %)　
					 	)))))
(accept channel 1)
(accept channel 2)
(accept channel 3)

(ms/flush channel)
@res


(def temperature-channel (ms/create))

(defn toohot-stream [upstream]
  (ms/custom-stream
    (fn [event downstream]
      (if (< 40 event)
       (ms/accept downstream event)))
      upstream))

(ms/consume (toohot-stream temperature-channel) (fn[_] (println "hello")))
(ms/accept temperature-channel 1)
(ms/accept temperature-channel 45)
