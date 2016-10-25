(ns chapter05.calling-from-java
  (:require [clojure.math.numeric-tower :as math])
  (:require [clojure.reflect :refer [reflect]])
  )

(defn get-instance-method[obj]
    (->> (reflect obj)
       :members       
     )
  )

(gen-class
 :name cljbook.chapter05.Hello
 :methods [^:static [hello [String] void]])

(defn- -hello
  [s]
  (println (str "Hello " s)))

;;(cljbook.chapter05.Hello/hello "makoto")

(gen-class
 :name cljbook.chapter05.Person
 :state state
 :init init
 :prefix "-"
 :main false
 :constructors {
                [][]
                [String] []
                [String String] []}
 :methods [
           [setCountry [String] void]
           [getCountry [] String]                
           [setName [String] void]
           [getName [] String]])

(defn -init
  ([]  [[] (atom {:name nil :country nil})])
  ([name]  [[] (atom {:name name :country nil})])
  ([name country]  [[] (atom {:name name :country country})]))
;;=> #'chapter05.calling-from-java/-init

(defn set-value
  [this key value]
  (swap! (.state this) into {key value}))
;;=> #'chapter05.calling-from-java/set-value

(defn get-value
  [this key]
  (@(.state this) key))

(defn -setName [this name]
  (set-value this :name name))

(defn  -getName
  [this]
  (get-value this :name))

(defn -setCountry [this country]
  (set-value this :country country))

(defn  -getCountry
  [this]
  (get-value this :country))

#(
(compile 'chapter05.calling-from-java)
;;=> chapter05.calling-from-java
  
  )

(def p1 (cljbook.chapter05.Person.))
;;=> #'chapter05.calling-from-java/p1

(.getName p1)
;;=> makoto
(.getCountry p1)
;;=> Japan


(def p2 (cljbook.chapter05.Person. "Nico" "France"))
;;=> #'chapter05.calling-from-java/p2
(.getName p2)
;;=> Nico
(.getCountry p2)
;;=> France

(defprotocol IPoint
  "A simple protocol for flying"
  (move [self ^double x ^double y])
  (^double distance [self])
  )

;;=> IPoint
(defrecord Point [^double x ^double y])


(extend-protocol IPoint
  Point
  (move [self ^double x ^double y]
    (->Point
     (+ (.-x self) x)(+ (.-y self) y)))
  (distance [self]
    (math/sqrt (* (* (.-x self) (.-x self))
                    (* (.-y self) (.-y self)))     
                 )))
;;=> nil


(def pos1 (Point. 1 2))
;;=> #'chapter03.protocols/pos1
[(.-x pos1)(.-y pos1)]
;;=> [1 2]
(distance pos1)
;;=> 2.23606797749979
(distance pos1)
(def pos2 (move pos1 1.0 2.0))
;;=> #'chapter03.protocols/pos2
[(.-x pos2)(.-y pos2)]
;;=> [2 4]
(distance pos2)


;;=> 4.47213595499958

(deftype Point3D [^double x y z])

(extend-protocol IPoint
  Point3D
      (move [self delta]
    (->Point3D
     (+ (.-x self) (delta 0))(+ (.-y self) (delta 1))(+ (.-z self) (delta 2))))
          (distance [self]
      (math/sqrt
       (+
        (* (.-x self) (.-x self))
        (* (.-y self) (.-y self))
        (* (.-z self) (.-z self))))))


(definterface MyInterface
  (^int add[^int v])
  (^int sub[^int v])
  )


(defprotocol MyProtocol1
  (add[self v])
  (sub[self v])
  )


(def h1 (java.util.HashMap.))

(get-instance-method h1)

(definterface Calculation
  (^long getX [])
  (^void setX [^long value])
  (^long add [^long x])
  (^long sub [^long x]))
;;=> chapter05.calling_from_java.Calculation
(deftype MyInt [^long x]
  Calculation
  (getX [this] (.x this))
  (setX [this val] (set!  (.x this) val))  
  (add [this x] (+ (.x this) x))
  (sub [this x] (- (.x this) x)))
;;=> chapter05.calling_from_java.MyInt
(def myInt (MyInt. 15))
(.add myInt 30)
;;=> 45
(.sub myInt 11)
;;=> 4
(.getX myInt)
;;=> 15
(.setX myInt 10)
;;=> nil
(.getX myInt)
;;=> 10

(get-instance-method (MyInt. 1))

(extend-protocol MyProtocol1
  MyInt
  (add[self  v] (+ (.x self) v))
  (sub[self  v] (- (.x self) v))
  )


(extend-protocol MyProtocol1
   (Class/forName "[J")
  (add[self  v] (+ (.x self) v))
  (sub[self  v] (- (.x self) v))
  )
(def i1 (MyInt. 1))

(.x i1)
(add i1 1)
(MyInt. 1)



(class (int-array [1 2]))
