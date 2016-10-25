(ns spyglasbvs-example.core
  (:require [clojurewerkz.spyglass.client :as c]))
;;=> nil

(def tc (c/text-connection
         "172.17.0.3:11212 172.17.0.4:11212 172.17.0.5:11212"))
;;=> #'spyglass-example.core/tc

(c/set tc "key-1" 0 "Hello !")
;;=> #clojurewerkz.spyglass.OperationFuture[{:status :ready, :val true} 0x50b6f787]
(c/set tc "key-2" 0 1)
;;=> #clojurewerkz.spyglass.OperationFuture[{:status :ready, :val true} 0x81bbd8a]
(c/set tc "key-3" 0  [1 2 3])
;;=> #clojurewerkz.spyglass.OperationFuture[{:status :ready, :val true} 0x2e6b1c50]

(c/get tc "key-1")
;;=> "Hello !"
(c/get tc "key-2")
;;=> 1
(c/get tc "key-3")
;;=> [1 2 3]

(let [tc (c/text-connection
          "172.17.0.3:11212 172.17.0.4:11212 172.17.0.5:11212")]
  (c/set tc "key-1" 3  "Hello !")
  (println (c/get tc "key-1"))
  (println "sleeping ....")
  (Thread/sleep 3100)
  (println (c/get tc "key-1"))
  (c/shutdown tc))
;;=> Hello !
;;=> sleeping ....
;;=> nil

(c/shutdown tc)
;;=> nil

(def tc (c/text-connection
         "172.17.0.3:11212 172.17.0.4:11212 172.17.0.5:11212"))
#'spyglasbvs-example.core/tc

(c/get tc "key-1")
;;=> "test!"

(c/set tc "key-2" 100 "Hello !")
;;=> #clojurewerkz.spyglass.OperationFuture[{:status :ready, :val true} 0x171ca0f1]
;;=> 1

(.shutdown tc)
;;=> nil

(def tc (c/text-connection
         "172.17.0.3:11212 172.17.0.4:11212 172.17.0.5:11212"))
#'spyglasbvs-example.core/tc

(doseq [x (range 1 11)]
    (c/set tc (str "key-" x) 300 (str "Hello " x " !")))
;;=> nil

(def x
  (c/get-multi tc (mapv #(str "key-" %) (range 1 11))))
;;=> #'spyglasbvs-example.core/x

(println x)
;;=> {key-2 Hello 2 !, key-9 Hello 9 !, key-8 Hello 8 !, key-3 Hello 3 !, key-4 Hello 4 !, key-5 Hello 5 !, key-1 Hello 1 !, key-7 Hello 7 !, key-6 Hello 6 !}
;;=> nil

(c/set tc "key-1" 300 "Hello !")
;;=> #clojurewerkz.spyglass.OperationFuture[{:status :ready, :val true} 0x3a7b844e]

(def x (c/async-get tc "key-1"))
;;=> #'spyglasbvs-example.core/x

(c/replace  tc "key-1" 300 "Good bye !")
;;=> #clojurewerkz.spyglass.OperationFuture[{:status :ready, :val true} 0xf0bd936]

(def x (c/async-get tc "key-1"))
;;=> #'spyglasbvs-example.core/x

@x
;;=> Good bye!


;; In REPL-1
(require '(clojurewerkz.spyglass [client :as c]))
;;=> nil
(defn apply-fn [tc k fn]
  (let [ret (c/get tc k)
        v (fn ret)]
    (c/replace tc k 0 v) ))
;;=> #'spyglasbvs-example.core/apply-fn
(def tc (c/text-connection
         "172.17.0.3:11212 172.17.0.4:11212 172.17.0.5:11212"))
;;=> #'spyglass-example.core/tc

;; Also in REPL-2
(require '(clojurewerkz.spyglass [client :as c]))
;;=> nil
(defn apply-fn [tc k fn]
  (let [ret (c/get tc k)
        v (fn ret)]
    (c/replace tc k 0 v) ))
;;=> #'spyglasbvs-example.core/apply-fn
(def tc (c/text-connection
         "172.17.0.3:11212 172.17.0.4:11212 172.17.0.5:11212"))
;;=> #'spyglass-example.core/tc

;; In REPL-1
(c/set tc "key-1" 0 0)
;;=> nil

(time
 (dotimes [_ 10000]
   (apply-fn tc "key-1"  inc)
   ))
;;=> "Elapsed time: 2472.73951 msecs"
;;=> nil

;; In REPL-2
(time
 (dotimes [_ 10000]
   (apply-fn tc "key-1"  inc)))
;;=> "Elapsed time: 2467.011652 msecs"
;;=> nil

:: either in REPL-1 or REPL-2. 
(c/get tc "key-1")
;;=> 17258

(c/set tc "key-1" 0 0)
;;=> #clojurewerkz.spyglass.OperationFuture[{:status :ready, :val true} 0x7e0e69e5]


;; In REPL-1
(defn apply-fn-with-cas [tc k fn]
  (loop []
    (let [ret (c/gets tc k)
          v (fn (:value ret))]
      (when
          (= (c/cas tc k (:cas ret) v)  :exists)
        (recur)))))
;;=> #'spyglasbvs-example.core/apply-fn-with-cas

;; In REPL-2
(defn apply-fn-with-cas [tc k fn]
  (loop []
    (let [ret (c/gets tc k)
          v (fn (:value ret))]
      (when
          (= (c/cas tc k (:cas ret) v)  :exists)
        (recur)))))
;;=> #'spyglasbvs-example.core/apply-fn-with-cas

;; - In REPL-1

(time
 (dotimes [_ 10000]
   (apply-fn-with-cas tc "key-1"  inc)))
;;=> "Elapsed time: 4287.262212 msecs"
;;=> nil

;; In REPL-2


(time
 (dotimes [_ 10000]
   (apply-fn-with-cas tc "key-1"  inc)))
;;=> "Elapsed time: 4559.814013 msecs"
;;=> nil

(c/get tc "key-1")
;;=> 20000

(c/set tc "key-1" 0 0)
;;=> #clojurewerkz.spyglass.OperationFuture[{:status :ready, :val true} 0x3d31696e]

(c/get tc "key-1")
;;=> 0

(def cas-value (:cas (c/gets tc "key-1")))
;;=> 80010
#'user/cas-value

(c/cas tc "key-1" cas-value 1)
;;=> ok

(c/get tc "key-1")
;;=> 1

(c/cas tc "key-1" cas-value 1)
;;=> :exists


