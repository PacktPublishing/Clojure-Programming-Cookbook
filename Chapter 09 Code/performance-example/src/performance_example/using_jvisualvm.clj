(ns performance-example.using-jvisualvm)

(time (reduce + (range 100000000)))
;;=> "Elapsed time: 3662.597422 msecs"
;;=> 4999999950000000

(defn calculate-pi
  [iter]
  (let [n (filter odd? (iterate inc 1))]
    (* 4.0
       (apply + (map / (cycle [1 -1]) (take iter n))))))
;;=> #'performance-example.using-jvisualvm/calculate-pi

(.start (Thread. (fn [] (Thread/sleep 10000))))
;;=> nil

