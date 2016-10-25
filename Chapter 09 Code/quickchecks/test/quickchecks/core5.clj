(ns quickchecks.core5
  (:require    [clojure.test.check.properties :as prop])
  (:require    [clojure.test.check.generators :as gen])
  (:require    [clojure.test.check.clojure-test :refer [defspec]])

  (:require    [com.gfredericks.test.chuck.properties :as prop'])
  (:require    [com.gfredericks.test.chuck.generators :as gen'])

  (:use clojure.test)
   (:use [compojure.core]
        [compojure.route   :as route]))

(def my-routes
  (routes
   (GET "/:id" [] "Hello Foo")))

(defn request [resource web-app & params]
  (web-app {:request-method :get 
            :headers {"content-type" "text/plain"}
            :uri resource 
            :params params}))

(defspec ringing
	10 
	(prop/for-all [a gen/int]
		(= "Hello Foo" (:body (request (str "/" a) my-routes [])))))

(defspec ringing-2
	100
	(prop'/for-all [a gen/int]
		(let [local-routes
  			(routes	(GET "/:id" [] (str "Hello Foo" a)))]
		(= (str "Hello Foo" a) (:body (request (str "/" a) local-routes []))))))

(defspec ringing-3
	10
	(prop/for-all [
			my-str (gen/vector 
				(gen'/string-from-regex #"([☃-♥]{3}|B(A|OO)M)*") 5)
		]
		(<= 0 (count my-str))))

(defspec ringing-5
	10
	(prop/for-all [
			my-str (gen/vector 
				(gen'/string-from-regex #"([☃-♥]{3}|B(A|OO)M)*") 5)
		]
	(let [local-routes
  			(routes	(GET "/:id" [] (str "Hello " (first my-str))))]
		(= (str "Hello " (first my-str)) (:body (request (str "/" 0) local-routes []))))))