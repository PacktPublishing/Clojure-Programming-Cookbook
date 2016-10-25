(ns chapter02.accessing)

(nth [1 2 3 4 5] 1)
;;=> 2
(nth '("a" "b" "c" "d" "e") 3)
;;=> "d"
(nth [1 2 3] 3)
;;=> IndexOutOfBoundsException   clojure.lang.PersistentVector.arrayFor (PersistentVector.java:153)
(nth [1 2 3] 3 nil)
;;=> nil

(get {:a 1 :b 2 :c 3 :d 4 :e 5} :c)
;;=> 3
(get {:a 1 :b 2 :c 3 :d 4 :e 5} :f)
;;=> nil
(get {:a 1 :b 2 :c 3 :d 4 :e 5} :f :not-found)
;;=> :not-found

(get #{:a :b :c} :c)
;;=> :c
(get #{:a :b :c} :d)
;;=> nil
(get #{:a :b :c} :d :not-found)
;;=> :not-found

({:a 1 :b 2 :c 3 :d 4 :e 5} :c)
;;=> 3
({:a 1 :b 2 :c 3 :d 4 :e 5} :f)
;;=> nil
({:a 1 :b 2 :c 3 :d 4 :e 5} :f :not-found)
;;=> :not-found

(:c {:a 1 :b 2 :c 3 :d 4 :e 5})
;:=> 3
(:f {:a 1 :b 2 :c 3 :d 4 :e 5})
;;=> nil
(:f {:a 1 :b 2 :c 3 :d 4 :e 5} :not-found)
;;=> :not-found

(get #{:banana :apple :strawberry :orange :melon} :orange)
;;=> :orange
(get #{:banana :apple :strawberry :orange :melon} :grape)
;;=> nil
(get #{:banana :apple :strawberry :orange :melon} :grape :not-found)
;;=> :not-found

(#{:banana :apple :strawberry :orange :melon} :orange)
;;=> :orange
(#{:banana :apple :strawberry :orange :melon} :grape)
;;=> nil
(:orange #{:banana :apple :strawberry :orange :melon})
;;=> :orange
( :grape #{:banana :apple :strawberry :orange :melon})
;;=> nil

(second [1 2 3 4 5])
;;=> 2
(second '())
;;=> nil

(next [1])
;;=> nil
(rest [1])
;;=> ()

(ffirst [[1 2 3] 4 [3 5 6]])
;;=> 1
(ffirst {:a 1 :b 2})
;;=> :a

(first (first [[1 2 3] 4 [3 5 6]]))
;;=> 1
(first (first {:a 1 :b 2}))
;;=> :a
(update {:a 1 :b 2 :c 3} :a inc)
;;=> {:a 2, :b 2, :c 3}

(ifn? +)
;;=> true
(ifn? [])
;;=> true
(ifn? {})
;;=> true
(ifn? #{})
;;=> true
(ifn? :a)
;;=> true
(ifn? '())
;;=> false
(ifn? 1)
;;=> false

(get ["a" "b" "c" "d" "e"] 3)
;;=> "d"
(get ["a" "b" "c" "d" "e"] 5)
;;=> nil
(get ["a" "b" "c" "d" "e"] 5 :not-found)
;;=> :not-found

(["a" "b" "c" "d" "e"] 3)
;;=> "d"
(["a" "b" "c" "d" "e"] 5)
;;=> IndexOutOfBoundsException   clojure.lang.PersistentVector.arrayFor (PersistentVector.java:153)
(def location
  {{:x 1 :y 1} "Nico" {:x 1 :y 2} "John" {:x 2 :y 1} "Makoto" 
   {:x 2 :y 2} "Tony"}
  )
;;=> #'chapter02.accessing/location
(location {:x 2 :y 2})
;;=> "Tony"
(def biography-of-konan-doyle
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
  )
;;=> #'chapter02.accessing/biography-of-konan-doyle
(get-in biography-of-konan-doyle [:genre 2])
;;=> "science fiction"
;;=> ["Detective fiction" "fantasy" "science fiction" "historical novels" "non-fiction"]
(get (get biography-of-konan-doyle :genre) 2)
;;=> "science fiction"
(assoc-in {:a {:b 1 :c 2} :d 3} [:a :c] 1)
;;=> {:a {:b 1, :c 1}, :d 3}
(assoc-in {:a {:b 1 :c 2} :d 3} [:a :d] 1)
;;=> {:a {:b 1, :c 2, :d 1}, :d 3}
(update-in {:a {:b 1 :c 2} :d 3} [:a :c] inc)
;;=> {:a {:b 1, :c 3}, :d 3}
(update-in {:a {:b 1 :c 2} :d 3} [:a :c] (constantly 10))
;;=> {:a {:b 1, :c 10}, :d 3}
