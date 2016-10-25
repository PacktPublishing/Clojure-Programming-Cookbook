(ns chapter02.merging)

;;
;;Merging and splitting collections
;;

(merge {:a 1 :b 2 :c 3} {:c 4 :d 5 :e 6})
;;=> {:a 1, :b 2, :c 4, :d 5, :e 6}
(merge {:a 1 :b 2 :c 3} {:c 4 :d 5 :e 6} {:c 7 :f 4 :g 6})
;;=> {:a 1, :b 2, :c 7, :d 5, :e 6, :f 4, :g 6}


(def nicos-fruits
  {:apple 10 :melon 15 :orange 2 :pear 12}
  )
;;=> #'chapter02.merging/nicos-fruits

(def makotos-fruits
  {:apple 10 :orange 2 :lemon 8 :lime 9}
  )
;;=> #'chapter02.merging/makotos-fruits

(merge-with + nicos-fruits makotos-fruits)
;;=> {:apple 20, :melon 15, :orange 4, :pear 12, :lemon 8, :lime 9}

(defn summarize [& fruits]
  (apply merge-with +  fruits) 
  )
;;=> #'chapter02.merging/summarize

(summarize nicos-fruits makotos-fruits)
;;=> {:apple 20, :melon 15, :orange 4, :pear 12, :lemon 8, :lime 9}

(zipmap [:a :b :c :d :e] [1 2 3 4 5])
;;=> {:a 1, :b 2, :c 3, :d 4, :e 5}

(apply assoc {} (interleave [:a :b :c :d :e] [1 2 3 4 5]))
;;=> {:a 1, :b 2, :c 3, :d 4, :e 5}

(split-at 5 (range 10))
;;=> [(0 1 2 3 4) (5 6 7 8 9)]
(split-with (partial > 5) (range 10))
;;=> [(0 1 2 3 4) (5 6 7 8 9)]

(defn make-random-vars [n]
  (apply assoc {}
         (take (* n 2)
               (interleave (map #(keyword (str "x" (inc %))) (range))
                                  (repeatedly #(float (rand 100)))
                                  ))
        )
  )
;;=> #'chapter02.merging/make-random-vars


(make-random-vars 5)
;;=> {:x1 72.97348, :x2 38.836014, :x3 96.239944, :x4 95.13157, :x5 81.230804}
(make-random-vars 10)
;;=> {:x9 85.312256, :x10 27.16199, :x4 10.890139, :x6 33.993263, :x7 61.26995, :x3 96.727, :x1 34.886852, :x8 60.126255, :x5 87.26598, :x2 30.690058}

(group-by :company
'({:name "John" :company "facebook"}
  {:name "Tony" :company "twitter"}
  {:name "Andy" :company "google"}
  {:name "Sally" :company "twitter"}
  {:name "Peter" :company "google"}
  {:name "Sara" :company "facebook"}
    {:name "Linda" :company "twitter"}
  )
)
;;=> {"facebook"
 [{:name "John", :company "facebook"}
  {:name "Sara", :company "facebook"}],
 "twitter"
 [{:name "Tony", :company "twitter"}
  {:name "Sally", :company "twitter"}
  {:name "Linda", :company "twitter"}],
 "google"
 [{:name "Andy", :company "google"}
 {:name "Peter", :company "google"}]}



