(ns helloworld)

(gen-class
 :name cljbook.chapter05.HelloWorld
 :methods [#^{:static true} [sayHello [] void]])

(defn -sayHello []
  (println "Hello world from Clojure!"))

(gen-class
   :name cljbook.chapter05.Calculator
   :methods
   [#^{:static false} [add [long long] long]
    #^{:static false} [sub [long long] long]])

(defn -add [this a b]
  (+ a b))

(defn -sub [this a b]
  (- a b))
