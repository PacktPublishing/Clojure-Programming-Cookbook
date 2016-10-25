(ns quickchecks.core6
	(:require    [clojure.test.check.clojure-test :refer [defspec]])
	(:require 
		[clojure.test.check :as tc]
	    [clojure.test.check.generators :as gen]
	    [clojure.test.check.properties :as prop]
        [miner.herbert.generators :as hg]
        [miner.herbert :as h]
	    [clojure.test :refer :all]))

(defspec hello-herbert
	100
	(hg/property 
		integer?
		10)

	)

(defspec hello-again
	10
	(hg/property 
		(fn [m] (== (get-in m [:a 1 :int]) 0))
          '{:a (vec kw {:int 0} kw) :b str}))