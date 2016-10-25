(ns flambing.core
	(:require [flambo.api :as f]
            [flambo.tuple :as ft]
            [clojure.string :as s]
	[flambo.conf :as conf]))

(def c (-> (conf/spark-conf)
           (conf/master "local")
           (conf/app-name "flame_princess")))
(def sc (f/spark-context c))

(defn word-frequencies [text-file]
	(-> (f/text-file sc text-file)
		f/count))