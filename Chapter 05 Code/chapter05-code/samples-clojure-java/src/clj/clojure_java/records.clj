(ns clojure_java.records
  (:import (clojure.lang Keyword)))

(defrecord 
	Item 
	[^Keyword type ^String value])