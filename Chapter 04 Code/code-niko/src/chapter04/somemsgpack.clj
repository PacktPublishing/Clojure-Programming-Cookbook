(ns chapter04.somemsgpack
  (:require [msgpack.core :as msg])
  (:require msgpack.clojure-extensions))

(msg/pack {:friends {:friend "Nick" :age 15}})
; => #<byte[] [B@60280b2e>

(msg/unpack (msg/pack {:friends {:friend "Nick" :age 15}}))
; => {:schema 0, :compact true}

(use 'clojure.java.io)
(import '(java.io.DataOutputStream) '(java.io.DataInputStream))

(with-open [o (output-stream "test.dat")]
  (let [data-output (java.io.DataOutputStream. o)]
    (msg/pack-stream {:friends {:friend "Nick" :age 15}} data-output)))

(with-open [i (input-stream "test.dat")]
  (let [data-input (java.io.DataInputStream. i)]
    (msg/unpack-stream data-input)))
; => {:schema 0, :compact true}

(require '[msgpack.macros :refer [extend-msgpack]])

(defrecord Person [name])
(extend-msgpack
  Person
  100
  [p] (.getBytes (:name p))
  [bytes] (->Person (String. bytes)))

(msg/unpack (msg/pack [(->Person "Nick") 5 "test"]))
; => (#user.Person{:name "bob"} 5 "test")
