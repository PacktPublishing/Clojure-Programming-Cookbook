(ns aws-examples.lambda-example1
  (:gen-class
   :methods [^:static [-handler [String] String]]))
;;=> nil

(defn -handler [s]
  (println (str "Hello," s)))

;;=> #'aws-examples.lambda-example1/-handler


