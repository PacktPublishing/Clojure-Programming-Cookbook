(ns chapter03.protocols
  (:require
   [clojure.math.numeric-tower :as math]
   ))


(defrecord Author
    [name
     born
     died
     occupation
     nationality
     citizenship
     genre
     notable-works
     spouse
     no-of-children
     ])
;;=> chapter03.protocols.Author

(def doyle
  (->Author
   "Arthur Ignatius Conan Doyle"
   "22-May-1859"
   "7-July-1930"
  ["novelist" "short story writer" "poet" "physician"]
   "scottish"
      "United Kingdom"
  ["Detective fiction", "fantasy", "science fiction", "historical novels", "non-fiction"]
  ["Stories of Sherlock Holmes" "The Lost World"]
  ["Louisa Hawkins" "Jean Leckie"]
   5
   ))
;;=> #'chapter03.protocols/doyle

(def christie
    (map->Author {
                  :name
                  "Agatha Mary Clarissa Miller"
                  :born
                  "15-September-1890"
                  :died
                  "12-January-1976"
                  :occupation
                  ["novelist" "short story writer" "playwright" "poetnovelist"]
                  :nationality
                  "england"
                  :citizenship
                "United Kingdom"
                  :genre
                  ["murder mystery" "thriller" "crime fiction" "detective" "romance"]
                  :notable-works
                  ["Murder on the Orient Express"
                 "The Murder of Roger Ackroyd"
                 "Death on the Nile"
                 "The Murder at the Vicarage"
                 "Partners In Crime"
                 "The ABC Murders"
                 "And Then There Were None"
                 "The Mousetrap"]
                  :spouse
                  ["Archibald Christie" "Sir Max Mallowan"]
                  :no-of-children
                  1
                }                
               )
  )
;;=> #'chapter03.protocols/christie

(:name doyle)
;;=> "Arthur Ignatius Conan Doyle"
(:name christie)
;;=> "Agatha Mary Clarissa Miller"
(:genre doyle)
;;=> ["Detective fiction" "fantasy" "science fiction" "historical novels" "non-fiction"]
(:genre christie)
;;=>["murder mystery" "thriller" "crime fiction" "detective" "romance"]

(deftype Point [x y])
;;=> chapter03.protocols.Point
(def pos1 (Point. 1 2))
;;=> #'chapter03.protocols/pos1
(.x pos1)
;;=> 1
(.y pos1)
;;=> 2

(defrecord Point [x y])
;;=> chapter03.protocols.Point


(defprotocol IPoint
  "A simple protocol for flying"
  (move [self delta])
  (distance [self])
  )
;;=> IPoint
(extend-protocol IPoint
  Point
  (move [self delta]
    (->Point
     (+ (.-x self) (delta 0))
     (+ (.-y self) (delta 1))))
    (distance [self]
      (math/sqrt
       (+
        (* (.-x self) (.-x self))
        (* (.-y self) (.-y self)))
       )))
;;=> nil
(def pos1 (Point. 1 2))
;;=> #'chapter03.protocols/pos1
[(.-x pos1)(.-y pos1)]
;;=> [1 2]
(distance pos1)
;;=> 2.23606797749979
(def pos2 (move pos1 [1 2]))
;;=> #'chapter03.protocols/pos2
[(.-x pos2)(.-y pos2)]
;;=> [2 4]
(distance pos2)
;;=> 4.47213595499958

(deftype Point3D [x y z]
  IPoint
    (move [self delta]
      (Point3D.
       (+ (.-x self) (delta 0))
       (+ (.-y self) (delta 1))
       (+ (.-z self) (delta 2))))
    (distance [self]
      (math/sqrt
       (+
        (* (.-x self) (.-x self))
        (* (.-y self) (.-y self))
        (* (.-z self) (.-z self))))))
;;=> chapter03.protocols.Point3D
(def pos3 (Point3D. 1 2 3))
;;=> #'chapter03.protocols/pos3
[(.-x pos3)(.-y pos3)(.-z pos3)]
;;=> [1 2 3]
(def pos4 (move pos3 [1 1 1]))
;;=> #'chapter03.protocols/pos4
[(.-x pos4)(.-y pos4)(.-z pos4)]
;;=> [2 3 4]
(distance pos4)
;;=> 5.385164807134504

(class Author)
;;=> java.lang.Class
(class Point)
;;=> java.lang.Class

(.-occupation christie)
;;=> ["novelist" "short story writer" "playwright" "poetnovelist"]
(.-x (Point. 10 2))
;;=> 10
    (.y (Point. 10 2))
;;=> 2
(.nationality doyle)
;;=> "scottish"

(supers Author)
;;=> #{clojure.lang.IKeywordLookup clojure.lang.ILookup clojure.lang.Seqable java.lang.Object java.lang.Iterable clojure.lang.IMeta clojure.lang.IHashEq clojure.lang.IPersistentMap clojure.lang.Counted java.io.Serializable java.util.Map clojure.lang.IPersistentCollection clojure.lang.IObj clojure.lang.Associative clojure.lang.IRecord}
(supers Point3D)
#{clojure.lang.IHashEq clojure.lang.Associative java.lang.Object clojure.lang.Counted clojure.lang.IPersistentMap clojure.lang.ILookup clojure.lang.IPersistentCollection clojure.lang.IObj clojure.lang.IMeta clojure.lang.IRecord clojure.lang.Seqable clojure.lang.IKeywordLookup java.io.Serializable java.util.Map java.lang.Iterable}
(def x (Point. 1 1))
;;=> #'chapter03.protocols/x
(def y (Point. 1 1))
;;=> #'chapter03.protocols/y
(= x y)
;;=> true

(defrecord PointRecord [x y])
;;=> chapter03.protocols.PointRecord
(= (PointRecord. 10 5)
   (PointRecord. 10 5)
   )
;;=> true
(deftype PointType [x y])
;;=> chapter03.protocols.PointType
(= (PointType. 10 5)
   (PointType. 10 5)
   )
;;=> false

(defprotocol IEditPoint
  (getX [this])
  (setX! [this val])
  (getY [this])
  (setY! [this val])
  )
;;=> IEditPoint

(deftype MutablePoint [^:volatile-mutable x ^:volatile-mutable y]
  IEditPoint
  (getX [this] (. this x))
  (setX! [this val] (set! x val))
  (getY [this] (. this y))
  (setY! [this val] (set! y val))
  )
;;=> chapter03.protocols.MutablePoint
(def mutable-point (MutablePoint. 5 3))
;;=> #'chapter03.protocols/mutable-point
(.getX mutable-point)
;;=> 5
(.getY mutable-point)
;;=> 3
(.setX! mutable-point 10)
;;=> 10
(.setY! mutable-point 1)
;;=> 1
(.getX mutable-point)
;;=> 10
(.getY mutable-point)
;;=> 1
