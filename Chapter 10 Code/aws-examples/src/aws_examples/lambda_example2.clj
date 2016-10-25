(ns aws-examples.lambda-example2
  (:gen-class
   :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler])
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]
            [clojure.string :as str]))

(defn -handleRequest [this is os context]
  (let [w (io/writer os)
        parameters (json/read (io/reader is) :key-fn keyword)]
    (println "Lambda Hello Stream Output ")
    (println "this class: " (class this))
    (println "is class:" (class is))
    (println "os class:" (class os))
    (println "context class:" (class context))
    (println "Parameters are " parameters)
  (.flush w)))
