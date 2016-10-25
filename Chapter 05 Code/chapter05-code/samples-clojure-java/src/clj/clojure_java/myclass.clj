(ns clojure_java.myclass
  (:gen-class
    :name MyClass
    :methods [[doSomethin [String] String]]))

(defn -doSomethin
  [_ input]
  (str input " went through Clojure"))