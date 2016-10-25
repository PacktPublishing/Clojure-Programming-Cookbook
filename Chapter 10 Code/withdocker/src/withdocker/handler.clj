(ns withdocker.handler
  (:gen-class)
  (:require 
  			[ring.adapter.jetty :refer [run-jetty]]
  			[compojure.core :refer :all]
        [compojure.route :as route]
        [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] "Hello Remote Docker World")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main []
  (run-jetty app {:port 8080}))