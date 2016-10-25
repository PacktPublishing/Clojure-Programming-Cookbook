(ns clojure-scala.core)
;;=> nil

(import 'chapter05.Calculation)

;;=> chapter05.Calculation

(.add calc 10 2)
;;=> 12

(import 'scala.math.BigInt)
;;=> scala.math.BigInt

(import 'java.math.BigInteger)
;;=> java.math.BigInteger

(.factorial calc (BigInt. (BigInteger. "25")))
;;=> 15511210043330985984000000

(import 'chapter05.Main)
;;=> chapter05.Main

(Main/hello "Makoto")
;;=> "hello from Scala: Makoto !"

(import 'chapter05.MyTuple)
;;=> chapter05.MyTuple

(def tuple1 (.tuple (MyTuple.) 1 5))
;;=> #'clojure-scala.core/tuple1

(._1 tuple1)
;;=> 1
(._2 tuple1)
;;=> 5

(import 'chapter05.Person)
;;=> chapter05.Person

(import 'chapter05.Student)
;;=> chapter05.Student

(def s (Student.))
;;=> #'clojure-scala.core/s

(.name_$eq s "Nico")
;;=> nil

(.name s)
;;=> "Nico"

(def p (Person.))
;;=> #'clojure-scala.core/person

(.setName p "Makoto")
;;=> nil

(.getName p)
;;=> "Makoto"

(.address_$eq p "Japan")
;;=> nil

(.address p)
;;=> "Japan"


