(ns chapter02.collections)

'("A Study in Scarlet"
"The Sign of the Four"
"The Hound of the Baskervilles"
"The Valley of Fear")
;;=> ("A Study in Scarlet" "The Sign of the Four" "The Hound of the Baskervilles" "The Valley of Fear")
'()
;;=> ()
(list)
;;=> ()
(first '(1 2 3 4 5))
;;=> 1
(first '())
;;=> nil
(first nil)
;;=> nil
(rest '(1 2 3 4 5))
;;=> (2 3 4 5)
(rest '())
;;=> ()
(rest nil)
;;=> ()
(conj '(2 3 4 5) 1)
;;=> (1 2 3 4 5)
(conj '("d" "e" "f") "c" "b" "a")
;;=> ("a" "b" "c" "d" "e" "f")
["A Study in Scarlet"
"The Sign of the Four"
"The Hound of the Baskervilles"
"The Valley of Fear"]
;;=> ["A Study in Scarlet" "The Sign of the Four" "The Hound of the Baskervilles" "The Valley of Fear"]
[]
;;=> []
(vector)
;;=> []
(first [1 2 3 4 5])
;;=> 1
(first [])
;;=> nil
(rest [1 2 3 4 5])
;;=> (2 3 4 5)
(class (rest [1 2 3 4 5]))
;;=> clojure.lang.PersistentVector$ChunkedSeq
(conj [1 2 3 4] 5)
;;=> [1 2 3 4 5]
{:name "Arthur Ignatius Conan Doyle"
 :born "22-May-1859"
 :died "7-July-1930"
 :occupation ["novelist" "short story writer" "poet" "physician"]
 :nationality "scotish"
 :citizenship "United Kingdom"
 :genre ["Detective fiction", "fantasy", "science fiction", "historical novels", "non-fiction"]
 :notable-works ["Stories of Sherlock Holmes" "The Lost World"]
 :spouse ["Louisa Hawkins" "Jean Leckie"]
 :no-of-children 5
 }
;;=> {:genre ["Detective fiction" "fantasy" "science fiction" "historical novels" "non-fiction"], :occupation ["novelist" "short story writer" "poet" "physician"], :name "Arthur Ignatius Conan Doyle", :no-of-children 5, :nationality "scotish", :died "7-July-1930", :spouse ["Louisa Hawkins" "Jean Leckie"], :notable-works ["Stories of Sherlock Holmes" "The Lost World"], :citizenship "United Kingdom", :born "22-May-1859"}
{}
;;=> {}
(hash-map)
;;=> {}
(first {:a 1, :b 2, :c 3 :d 4 :e 5})
;;=> [:a 1]
(rest {:a 1, :b 2, :c 3 :d 4 :e 5})
;;=> ([:b 2] [:c 3] [:d 4] [:e 5])
(conj {:a 1, :b 2, :c 3 :d 4 } [:e 5])
;;=> {:a 1, :b 2, :c 3, :d 4, :e 5}
(conj {:a 1, :b 2, :c 3 :d 4 } [:d 5] [:e 6])
;;=> {:a 1, :b 2, :c 3, :d 5, :e 6}
(conj {:a 1, :b 2, :c 3 :d 4 } [:d 5])
;;=> {:a 1, :b 2, :c 3, :d 5}
(get {:a 1, :b 2, :c 3 :d 4 :e 5} :c)
;;=> 3
(get {:a 1, :b 2, :c 3 :d 4 :e 5} :f)
;;=> nil
(get {:a 1, :b 2, :c 3 :d 4 :e 5} :f :not-found)
;;=> :not-found
(assoc {:a 1, :b 2, :c 3 :d 4} :e 5)
;;=> {:a 1, :b 2, :c 3, :d 4, :e 5}
(assoc {:a 1, :b 2, :c 3 :d 4} :c 5)
;;=> {:a 1, :b 2, :c 5, :d 4}
(assoc {:a 1, :b 2, :c 3 :d 4} :e 5 :f 6)
;;=> {:a 1, :b 2, :c 3, :d 4, :e 5, :f 6}
(dissoc {:a 1, :b 2, :c 3 :d 4} :c)
;;=> {:a 1, :b 2, :d 4}
(dissoc {:a 1, :b 2, :c 3 :d 4} :c)
;;=> {:a 1, :b 2}
(dissoc {:a 1, :b 2, :c 3 :d 4} :e)
;;=> {:a 1, :b 2, :c 3, :d 4}
#{"apple" "orange" "banana" "peach" "strawberry"}
;;=> #{"peach" "apple" "banana" "orange" "strawberry"}
#{}
;;=> #{}
(hash-set)
;;=> #{}
(first #{:a :b :c :d :e})
;;=> :e
(rest #{:a :b :c :d :e})
;;=> (:c :b :d :a)
(conj #{:a :b :c :d} :e)
;;=> #{:e :c :b :d :a}
(conj #{:a :b :c :d :e} :e)
;;=> #{:e :c :b :d :a}
(contains? #{:a :b :c :d :e} :c)
;=> true
(contains? #{:a :b :c :d :e} :f)
;;=> false
(disj #{:a :b :c :d :e} :c)
;;=> #{:e :b :d :a}
(disj #{:a :b :c :d :e} :c :d)
;;=> #{:e :b :a}
(conj '(2 3 4 5) 1)
;;=> (1 2 3 4 5)
(conj [1 2 3 4] 5)
;;=> [1 2 3 4 5]
(let [n 1000000 iter 100
      l (into () (repeat n "a"))
      v (into [] (repeat n "a"))
      ]
  (time (dotimes [_ iter](nth l (dec n) )))
  (time (dotimes [_ iter](nth v (dec n) )))
  )
;;=> "Elapsed time: 532.623841 msecs"
;;=> "Elapsed time: 0.167739 msecs"

(do
  (time (reverse (into '() (range 100000))))
  (time (reverse (into [] (range 100000))))
  nil
  )
;;=> "Elapsed time: 60.67435 msecs"
;;=> "Elapsed time: 26.431954 msecs"
;;=>nil
(def l '(2 3 4 5))
;;=> #'collection.core/l
(conj '(2 3 4 5) 1)
;;=> (1 2 3 4 5)
l
;;=> (2 3 4 5)
(into [] '(1 2 3 4 5))
;;=> [1 2 3 4 5]
(into '() [1 2 3 4 5])
;;=> (5 4 3 2 1)
(into {} [[:a 1][:b 2] [:c 3][:d 4][:e 5]])
;;=> {:a 1, :b 2, :c 3, :d 4, :e 5}
(into [] {:a 1 :b 2 :c 3 :d 4 :e 5})
;;=> [[:a 1] [:b 2] [:c 3] [:d 4] [:e 5]]
(into [] #{:a :b :c :d :e})
;;=> [:e :c :b :d :a]
(into #{} [:a :b :c :d :e])
#{:e :c :b :d :a}
