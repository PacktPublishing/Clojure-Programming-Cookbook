(ns chapter06.concurrency
   [:require [clojure.core.reducers :as r]
    ]
  )
;; atom & ref

(def x (atom 1))
;;=? #'chapter06.concurrency/x

x
;;=> #<Atom@541e8f8d: 1>

(deref x)
;;=> 1
@x
;;=> 1

(swap! x inc)
;;=> 2
@x
;;=> 2

(swap! x (partial + 1))
;;=> 3

(reset! x 1)
;;=> 1
x
;;=> #<Atom@541e8f8d: 1>

;; Using validator

(def y (atom 1 :validator (partial > 5)))
;;=> #'chapter06.concurrency/y

(swap! y (partial + 2))
;;=> 3

(swap! y (partial + 2))
;;=> IllegalStateException Invalid reference state  clojure.lang.ARef.validate (ARef.java:33)


;; Using CAS operation

(defn cas-test! [my-atom interval]
  (let [v @my-atom u (inc @my-atom)]
    (println "current value = " v ", updated value = " u)
    (Thread/sleep interval)
    (println "updated " (compare-and-set! my-atom v u))))
;;=> #'chapter06.concurrency/cas-test!


(do
  (cas-test! x 20)
  (cas-test! x 30))
;;=> current value =  1 , updated value =  2
;;=> updated  true
;;=> current value =  2 , updated value =  3
;;=> updated  true
;;=> nil

(do
  (def x (atom 1))
  (future (cas-test! x 20))
  (future (cas-test! x 30)))
;;=> current value =  1 , updated value =  2
;;=> current value =  1 , updated value =  2
;;=> updated  true
;;=> updated  false

(def makoto-account (ref {:name "Makoto Hashimoto" :amount 1000}))
;;=> #'chapter06.concurrency/makoto-account
(def nico-account (ref {:name "Nicolas Modrzyk" :amount 2000}))
;;=> #'chapter06.concurrency/nico-account

(defn transfer! [from to amount]
  (dosync
   (println "transfer money from " (:name @from) " to " (:name @to) " amount = " amount " begins")
   (alter from assoc :amount (- (:amount @from) amount))
   (Thread/sleep 500)
   (alter to assoc :amount (+ (:amount @to) amount))
   (println "Now, " (:name @from) " amount is  " (:amount @from) " and " (:name @to) " amount is " (:amount @to))
      ))
;;=> #'chapter06.concurrency/transfer!

(transfer!  makoto-account  nico-account 10)
;;=> transfer money from  Makoto  to  Nicolas  amount =  10  begins
::=> Now,  Makoto  amount is   990  and  Nicolas  amount is  2010
;;=> nil
@nico-account
;;=> {:name "Nicolas Modrzyk", :amount 2010}
(deref makoto-account)
;;=> {:name "Makoto Hashimoto", :amount 990}

(do
    (future
       (transfer!  makoto-account nico-account 200))
    (future
      (transfer!   makoto-account nico-account 300)))
;;=> transfer money from  Makoto Hashimoto  to  Nicolas Modrzyk  amount =  200  begins
;;=> #<Future@473dad05: :pending>
;;=> transfer money from  Makoto Hashimoto  to  Nicolas Modrzyk  amount =  300  begins
;;=> transfer money from  Makoto Hashimoto  to  Nicolas Modrzyk  amount =  300  begins
;;=> transfer money from  Makoto Hashimoto  to  Nicolas Modrzyk  amount =  300  begins
;;=> transfer money from  Makoto Hashimoto  to  Nicolas Modrzyk  amount =  300  begins
;;=> transfer money from  Makoto Hashimoto  to  Nicolas Modrzyk  amount =  300  begins
;;=> Now,  Makoto Hashimoto  amount is   790  and  Nicolas Modrzyk  amount is  2210
;;=> transfer money from  Makoto Hashimoto  to  Nicolas Modrzyk  amount =  300  begins
;;=> Now,  Makoto Hashimoto  amount is   490  and  Nicolas Modrzyk  amount is  2510

(defn ensure-transfer! [from to amount]
  (dosync
   (ensure from)
   (println "transfer money from " (:name @from)
            " to " (:name @to) " amount = " amount " begins")
   (alter from assoc :amount (- (:amount @from) amount))
   (Thread/sleep 500)
   (alter to assoc :amount (+ (:amount @to) amount))
   (println "Now, " (:name @from) " amount is  " (:amount @from)
            " and " (:name @to) " amount is " (:amount @to))))
;;=> #'chapter06.concurrency/ensure-transfer!

(do
    (future
      (ensure-transfer!  nico-account makoto-account 100))
    (future
      (ensure-transfer! nico-account makoto-account 200)))
;;=> transfer money from  Nicolas Modrzyk  to  Makoto Hashimoto  amount =  100  begins
;;=> #<Future@53673cd8: :pending>
;;=> Now,  Nicolas Modrzyk  amount is   2410  and  Makoto Hashimoto  amount is  590
;;=> transfer money from  Nicolas Modrzyk  to  Makoto Hashimoto  amount =  200  begins
;;=> Now,  Nicolas Modrzyk  amount is   2210  and  Makoto Hashimoto  amount is  790

(defn add-watcher [ref]
  (add-watch ref :watcher
             (fn [_ _ old-state new-state]
               (prn "---- ref changed --- " old-state " => " new-state)
               )))
;;=> #'chapter06.concurrency/add-watcher
(add-watcher makoto-account)
;;=> #ref[{:status :ready, :val {:name "Makoto Hashimoto", :amount 1000}} 0x52f5973a]
(add-watcher nico-account)
;;=> #ref[{:status :ready, :val {:name "Nicolas Modrzyk", :amount 2000}} 0x7ecca962]

(defn refined-transfer! [from to amount]
  (dosync
   (ensure from)
   (if (<=  (- (:amount @from) amount) 0)
     (throw (Exception. "insuficiant amount")))
   (alter from assoc :amount (- (:amount @from) amount))
   (Thread/sleep 500)
   (alter to assoc :amount (+ (:amount @to) amount))))
;;=> #'chapter06.concurrency/refined-transfer!

(do
  (ref-set makoto-account {:name "Makoto Hashimoto" :amount 1000})
  (ref-set nico-account {:name "Nicolas Modrzyk" :amount 2000}))
;;=> IllegalStateException No transaction running  clojure.lang.LockingTransaction.getEx (LockingTransaction.java:208)
;;=> IllegalStateException No transaction running  clojure.lang.LockingTransaction.getEx (LockingTransaction.java:208)


(dosync
  (ref-set makoto-account {:name "Makoto Hashimoto" :amount 1000})
  (ref-set nico-account {:name "Nicolas Modrzyk" :amount 2000})   
  )
;;=> "---- ref changed --- " {:name "Nicolas Modrzyk", :amount 2000} " => " {:name "Nicolas Modrzyk", :amount 2000}
;;=> "---- ref changed --- " {:name "Makoto Hashimoto", :amount 1000} " => " {:name "Makoto Hashimoto", :amount 1000}
;;=> {:name "Nicolas Modrzyk", :amount 2000}

(dosync
  (ref-set makoto-account {:name "Makoto Hashimoto" :amount 1000})
  (ref-set nico-account {:name "Nicolas Modrzyk" :amount 2000})   
  )

(do
    (future
       (refined-transfer!  makoto-account nico-account 100))
    (future
      (refined-transfer!  nico-account makoto-account 300))
    )
;;=> #<Future@4c041c9: :pending>
;;=> "---- ref changed --- " {:name "Nicolas Modrzyk", :amount 2000} " => " {:name "Nicolas Modrzyk", :amount 2100}
;;=> "---- ref changed --- " {:name "Makoto Hashimoto", :amount 1000} " => " {:name "Makoto Hashimoto", :amount 900}
;;=> "---- ref changed --- " {:name "Nicolas Modrzyk", :amount 2100} " => " {:name "Nicolas Modrzyk", :amount 1800}
;;=> "---- ref changed --- " {:name "Makoto Hashimoto", :amount 900} " => " {:name "Makoto Hashimoto", :amount 1200}

(refined-transfer!  makoto-account nico-account 1500)
;;=> Exception insuficiant bound  chapter06.concurrency/transfer!/fn--20708 (form-init8938610057865502322.clj:87)

(defn alter-add! [var val]
  (dosync
   (Thread/sleep 500)
   (alter var  (partial + val))))
;;=> #'chapter06.concurrency/alter-add!

(do
  (def v1 (ref 10))
  (time
   (doseq
       [x [
           (future (alter-add! v1 10))
           (future (alter-add! v1 10))
           (future (alter-add! v1 10))
           (future (alter-add! v1 10))
           (future (alter-add! v1 10))
           ]]
     @x))
  (println @v1)
  )
;;=> "Elapsed time: 2504.52562 msecs"
;;=> 60
;;=> nil

(defn commute-add! [var val]
  (dosync
   (Thread/sleep 500)
   (commute var  (partial + val))))
;;=> #'chapter06.concurrency/commute-add!

(do
  (def v1 (ref 10))
  (time
   (doseq
       [x [(future (commute-add! v1 10))
            (future (commute-add! v1 10))
            (future (commute-add! v1 10))
            (future (commute-add! v1 10))
            (future (commute-add! v1 10))]]
     @x))
  (println @v1)
  )
;;=> "Elapsed time: 503.967362 msecs"
;;=> 60

(def simple-agent (agent 0))
;;=> #'chapter06.concurrency/simple-agent
simple-agent
;;=> #agent[{:status :ready, :val 0} 0x3aa1d3cc]

(send simple-agent inc)
;;=> #agent[{:status :ready, :val 0} 0x3aa1d3cc]
@simple-agent
;;=> 1

(def makoto-agent (agent {:name "Makoto" :location [100 200]}))
;;=> #'chapter06.concurrency/makoto-agent

(defn move [a dx dy t]
  (println "moving takes " t "msecs")
  (Thread/sleep t)
  (assoc a :location
         [(+ ((:location a) 0) dx)
          (+ ((:location a) 1) dy)]))
;;=> #'chapter06.concurrency/move

(do
  (send makoto-agent move 10 20 1000)
  (println makoto-agent)
  (await makoto-agent)
  (println makoto-agent))
;;=> moving takes  1000 msecs
;;=> #agent[{:status :ready, :val {:name Makoto, :location [100 200]}} 0x34a8e8f]
;;=> #agent[{:status :ready, :val {:name Makoto, :location [110 220]}} 0x34a8e8f]

(def x (promise))
;;=> #'chapter06.concurrency/x
(def y (promise))
;;=> #'chapter06.concurrency/y
(def z (promise))

(future
  (do (deliver z (+ @x @y))
      (println "z value : " @z)))
;;=> #future[{:status :pending, :val nil} 0x5524e673]

(realized? z)
;;=> false

(deliver x 1)
;;=> #promise[{:status :ready, :val 1} 0x2f5df579]
(deliver y 1)
;;=> #promise[{:status :ready, :val 1} 0x1614985]
;;=> z value :  2
;;=> #<Promise@1b7f5e3c: 1>

@z 
;;=> 2

(defrecord Order [name price qty])
;; => chapter06.concurrency.Order

(defn merge-products [m1 m2]
  {:total-price
   (+ (* (.price x) (.qty x)) (* (.price y) (.qty y)))} 
  [m1 m2])
;; => #'chapter06.concurrency/merge-products

(defn ship-products [x y z]
  (deliver z (merge-products @x @y))
  (println "We can ship products  " @z))
;; => #'chapter06.concurrency/ship-products

(defn deliver-product [p name price]
  (deliver p [name price]))
;; => #'chapter06.concurrency/deliver-product

(def product-a (promise))
;; => #'chapter06.concurrency/product-a
(def product-b (promise))
;; => #'chapter06.concurrency/product-b
(def shipping-ab (promise))
;; => #'chapter06.concurrency/shipping-ab

(future (ship-products product-a product-b shipping-ab))
;; => #future[{:status :pending, :val nil} 0x347af059]

(deliver product-a (->Order "book" 10.1 5))
;; => #promise[{:status :ready, :val #chapter06.concurrency.Order{:name "book", :price 10.1, :qty 5}} 0x1a198bfe]
(deliver product-b (->Order "pencil" 2.1 10))
;; => #promise[{:status :ready, :val #chapter06.concurrency.Order{:name "pencil", :price 2.1, :qty 10}} 0x458b44f6]
;;=> We can ship products  [#chapter06.concurrency.Order{:name book, :price 10.1, :qty 5} #chapter06.concurrency.Order{:name pencil, :price 2.1, :qty 10}]


(time
 (println
      (apply pcalls
             [(fn [] (reduce + (range 10000000)))
              (fn [] (reduce + (range 10000000 20000000))) 
            ])))
;;=> (49999995000000 149999995000000)
;;=> "Elapsed time: 454.398538 msecs"
;;=> nil

(time
 (println
  (let [fn1 (fn [num-list] (reduce + num-list))]
    (pvalues (fn1 (range 0 10000000)) (fn1 (range 10000000 20000000))))))
;;=> (49999995000000 149999995000000)
;;=> "Elapsed time: 578.047634 msecs"
;;=> nil
