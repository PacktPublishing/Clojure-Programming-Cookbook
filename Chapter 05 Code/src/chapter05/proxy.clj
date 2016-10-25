(ns chapter05.proxy
  (:use clojure.pprint)
  (:require [clojure.reflect :refer [reflect]])
  )

(def t
  (Thread.
        (proxy [java.lang.Runnable] []
          (run [] (println "Thread is called ..."))
          )))
;;=> #'chapter05.proxy/t
(.start t)
;;=> Thread is called ...
;;=> nil

(def m
  (proxy [java.util.HashMap][0.75 10]    
    (put [k v] (println "key=" k "value=" v " is goint to be inserted") (proxy-super put k v))
    )
  )
;;=> #'chapter05.proxy/m
(.put m 1 1)
    (doto (java.util.HashMap.)
      (.put 1 1)
      )
;;=> key= 1 value= 1  is goint to be inserted
;;=> [1 1]

(def t
  (Thread.
        (proxy [java.lang.Runnable] []
          (run [] (println "Thread is called ..."))
          )))
;;=> #'chapter05.proxy/t
(.start t)
;;=> Thread is called ...
;;=> nil

(def m
  (proxy [java.util.HashMap][0.75 10] 
  (put [k v] (println "key=" k "value=" v " is goint to be inserted")
  (proxy-super put k v))))
;;=> #'chapter05.proxy/m
(.put m 1 1)
    (doto (java.util.HashMap.)
      (.put 1 1)
      )
;;=> key= 1 value= 1  is goint to be inserted
;;=> [1 1]

(def t
  (Thread.
  (reify
    java.lang.Runnable
    (run [this]
      (println "Thread is called ...")
      ))))
;;=> #'chapter05.proxy/t
(.start t)
;;=> Thread is called ...
;;=> nil

(defn list-sort [li]
  (java.util.Collections/sort li
   (reify java.util.Comparator
     (compare [this x y]
       (Integer/compare x y)))))
;;=> #'chapter05.proxy/list-sort
(def l1 (java.util.ArrayList. [3 1 5 2 4]))
;;=> #'chapter05.proxy/l1
(clojure.pprint/pprint l1)
;;=> [3 1 5 2 4]
;;=> nil
(list-sort l1)
;;=> nil
(clojure.pprint/pprint l1)
;;=> [1 2 3 4 5]
;;=> nil

(import [com.fasterxml.jackson.databind ObjectMapper])
;;=> com.fasterxml.jackson.databind.ObjectMapper
(import [com.fasterxml.jackson.annotation JsonCreator JsonProperty])
;;=> com.fasterxml.jackson.annotation.JsonProperty
(deftype ^{JsonProperty {}} 
    Employee
    [^{:tag String JsonProperty "name"} name
     ^{:tag String JsonProperty "dept"} dept
     ^{:tag long JsonProperty "salary"} salary
     ]
  )
;;=> chapter05.proxy.Employee

(def makoto (->Employee "Makoto" "Development" 10000))
;;=> #'chapter05.proxy/makoto


