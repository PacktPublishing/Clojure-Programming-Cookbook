(ns quickchecks.core3
  (:require    [clojure.test.check.properties :as prop])
  (:require    [clojure.test.check.generators :as gen])
  (:require    [clojure.test.check.random :as random])
  (:require    [clojure.test.check.clojure-test :refer [defspec]])

  (:require    [com.gfredericks.test.chuck.properties :as prop'])
  (:require    [com.gfredericks.test.chuck.generators :as gen'])
  (:require    [com.gfredericks.test.chuck :as chuck]))

(defspec allows-empty-bindings
	10
  (prop'/for-all []
 	true))

(defspec for-all-destructured-args-work-correctly 
	10
  (prop'/for-all [[a b] (gen/tuple gen/int gen/int)]
	(+ a b)))


(defspec bowling
	100
	(prop'/for-all [ [len bools]

		; relationship between len and bools
	    (gen'/for [len gen/nat bools (gen/vector gen/boolean len)]
	    	[len bools]
	    	)]

	    ; assertion here
	    (= len (count bools))

	    ))

(defn my-len [n]
	(println n)
	(count (str n)))

(defspec split-n-spec 
	10
  (prop'/for-all [ [ele ele2] (gen/tuple gen/int gen/int)]
     		(< 0 (my-len (+ ele ele2)))
     	))