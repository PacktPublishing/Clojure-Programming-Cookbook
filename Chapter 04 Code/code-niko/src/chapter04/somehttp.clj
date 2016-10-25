(ns chapter04.async
  (:require [org.httpkit.client :as http])
  (:require [clojure.core.async :as async]))

(defn async-get [url result]
  (http/get url {:keepalive 60000}
  #(async/go (async/>! result %))))

(time
   (let [c (async/chan) res (atom [])]

     ;; send requests
     (doseq [i (range 1 3)]
       (async-get
        (format "http://requestb.in/tp6d3jtp?requestnumber=%d" i) c))

     ;; gather results
     (doseq [_ (range 1 3)]
       (swap! res conj (async/<!! c)))
     @res
     ))
