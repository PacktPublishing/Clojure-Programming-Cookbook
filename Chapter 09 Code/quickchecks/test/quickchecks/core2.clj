(ns quickchecks.core2
  (:require    [clojure.test.check.properties :as prop])
  (:require    [clojure.test.check.generators :as gen])
  (:require    [clojure.test.check.clojure-test :refer [defspec]]))

;
; Example with always the same value
;
;
(defspec some-ones
	5
	(prop/for-all [v (gen/return 1)]
		(= v 1)))

; (defspec some-ones-and-twos
; 	1
; 	(prop/for-all [v (gen/elements [1 1])]
; 		(= v 1)))

;
; Example generate a list
; 
;
(defspec some-g
	5
	(prop/for-all [v (gen/list (gen/elements [1 2 3]))] 
	 	(or (empty? v)
	 		(< (first v) 5))))

;
; Example using a custom generator
; and a function to test
;
(def my-g (gen/elements [1 2 3 4 5]))
(defn my-f[a b c]
	;(println a " " b " " c)
	(+ a b c))

(defspec tuppling
	1000
	(prop/for-all [v (gen/tuple my-g my-g my-g)]
		(>= 20 (apply my-f v))))

;
; tan = (/ sin cos)
;
; 
(defn my-eq [a b] 
	(> 1e10 (- b a)))
(defn my-tan [v]
	(/ (Math/sin v) (Math/cos v))
	)
(defspec cosintan
	1000
	(prop/for-all [v (gen/elements (range 1 10000))]
		 (my-eq (Math/tan v) (my-tan v) )))


;
; using fmap
;
;
(defspec some-fmap 
	10
	(prop/for-all [ v (gen/fmap dec (gen/elements (range 1 10)))]
		(and (<= 0 v) (<= v 10))))

