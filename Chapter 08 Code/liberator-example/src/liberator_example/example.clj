(ns liberator-example.example
  (:require
  [liberator.core :refer [resource defresource]]
  [ring.adapter.jetty :refer [run-jetty]]
  [ring.middleware.params :refer [wrap-params]]
  [compojure.core :refer
   [defroutes ANY GET PUT POST DELETE OPTIONS]]
  [clj-http.client :as client]
  [cheshire.core :refer [parse-string generate-string]]))

(defroutes app
  (ANY "/hello" []
       (resource :available-media-types ["text/html"]
                 :handle-ok "<html><h1>Hello Clojure !</h1></html>"))
  (route/not-found "Not Found !"))
;;=> #'liberator-example.example/app

(def handler 
  (-> app
      wrap-params))
;;=> #'liberator-example.example/handler

(def server (run-jetty handler {:port 3000 :join? false}))
;;=> #'liberator-example.example/server

(.stop server)
;;=> nil

(defresource hello :available-media-types ["text/html"]
          :handle-ok "<html><h1>Hello Clojure from defresource !</h1></html>")
;;=> #'liberator-example.example/hello

(defroutes app
  (ANY "/hello" [] hello)
  (route/not-found "Not Found !"))
;;=> #'liberator-example.example/app

(def handler 
  (-> app
      wrap-params))
;;=> #'liberator-example.example/handler

(def server (run-jetty handler {:port 3000 :join? false}))
;;=> #'liberator-example.example/server

(.stop server)
;;=> nil

(defresource parameterized-hello [x]
  :available-media-types ["text/html"]
  :handle-ok (format "<html><h1>Hello Clojure from %s !</h1></html>" x)
  )
;;=> #'liberator-example.example/parameterized-hello

(defroutes parameterized-hello-app
  (ANY "/hello/:x" [x] (parameterized-hello x))
  (route/not-found "Not Found !"))
;;=> #'liberator-example.example/parameterized-hello-app

(def handler
  (-> parameterized-hello-app
      wrap-params))
;;=> #'liberator-example.example/handler

(def server (run-jetty handler {:port 3000 :join? false}))
;;=> #'liberator-example.example/server

(.stop server)
;;=> nil

(def customer-ref
  (ref
  {:makoto {:name "Makoto Hashimoto" :country "Japan"}
   :nico {:name "Nicolas Modrzyk" :country "France"}}))
;;=> #'liberator-example.example/customer-ref

(defresource customer-list
  :allowed-methods [:get :post]
  :available-media-types ["application/json"]
  :exists? (fn [_]
             (let [result @customer-ref]
               (if-not (nil? result)
                 {::entry result})))
  :post!  (fn [ctx]
            (let [body (parse-string (slurp (get-in ctx [:request :body])) true)]              
              (dosync
               (alter customer-ref assoc (keyword (:id body)) (dissoc body :id)))))
  :handle-ok ::entry
  :handle-not-found {:error "id is not found"})
;;=> #'liberator-example.example/customer-list

(defresource customer-entity [id]
  :allowed-methods [:get :put :delete]
  :available-media-types ["application/json"]
  :exists? (fn [_]
             (let [result ( get @customer-ref (keyword id))]
               (info "get ======>" (keyword id))
               (if-not (nil? result)
                 {::entry result})))
  :put!  (fn [ctx]
            (let [body (parse-string (slurp (get-in ctx [:request :body])) true)]              
              (dosync
               (alter customer-ref assoc (keyword id) (dissoc body :id)))))  
  :delete!  (fn [_]
              (dosync
               (alter customer-ref dissoc (keyword id))))
  :existed? (fn [_] (nil? (get @customer-ref id ::sentinel)))
  :new? (fn [_] (nil? (get @customer-ref id ::sentinel)))
  :handle-ok ::entry
  :handle-not-found {:error "id is not found"}))
;;=> #'liberator-example.example/customer-entity

(defroutes customer
  (ANY "/customer" [] customer-list)
  (ANY "/customer/:id" [id] (customer-entity id)))
;;=> #'liberator-example.example/app

(def handler 
  (-> customer
      wrap-params))
;;=> #'liberator-example.example/handler

(def server (run-jetty handler {:port 3000 :join? false}))
;;=> #'liberator-example.example/server

(client/get "http://localhost:3000/customer")
;;=> {:status 200, :headers {"Date" "Sun, 26 Jun 2016 20:14:34 GMT",
;;=> "Content-Type" "application/json;charset=UTF-8", "Vary" "Accept",
;;=> "Connection" "close", "Server" "Jetty(9.2.10.v20150310)"},
;;=> :body "{\"makoto\":{\"name\":\"Makoto Hashimoto\",\"country\":\"Japan\"},
;;=> \"nico\":{\"name\":\"Nicolas Modrzyk\",\"country\":\"France\"}}",
;;=> :request-time 6, :trace-redirects ["http://localhost:3000/customer"],
;;=> :orig-content-encoding nil}

(client/post "http://localhost:3000/customer"
             {:body (generate-string {:id "rh" :name "Rich Hickey" :country "United States"})})
;;=> {:status 201, :headers {"Date" "Sun, 26 Jun 2016 14:33:28 GMT",
;;=> "Content-Type" "application/json", "Vary" "Accept", "Connection" "close",
;;=> "Server" "Jetty(9.2.10.v20150310)"}, :body "", :request-time 8,
;;=> :trace-redirects ["http://localhost:3000/customer"], :orig-content-encoding nil}

(->
 (client/get "http://localhost:3000/customer/rh" )  :body (parse-string true))
;;=> {:name "Rich Hickey", :country "United States"}

(client/put "http://localhost:3000/customer/rh"
             {:body (generate-string {:name "Rich Hickey" :country "USA"})})
;;=> {:status 204, :headers {"Date" "Sun, 26 Jun 2016 22:04:35 GMT",
;;=> "Content-Type" "application/json", "Vary" "Accept", "Connection" "close",
;;=> "Server" "Jetty(9.2.10.v20150310)"}, :body nil, :request-time 9,
;;=> :trace-redirects ["http://localhost:3000/customer/rh"], :orig-content-encoding nil}

(->
 (client/get "http://localhost:3000/customer/rh")  :body (parse-string true))
;;=> {:name "Rich Hickey", :country "USA"}

(client/delete "http://localhost:3000/customer/rh") 
;;=> {:status 204, :headers {"Date" "Sun, 26 Jun 2016 14:38:58 GMT",
;;=> "Content-Type" "application/json", "Vary" "Accept", "Connection" "close",
;;=> "Server" "Jetty(9.2.10.v20150310)"}, :body nil, :request-time 8,
;;=> :trace-redirects ["http://localhost:3000/customer/rh"], :orig-content-encoding nil}

(.stop server)
;;=> nil

(def handler 
  (-> customer
      wrap-params
      (wrap-trace :header :ui)))
;;=> #'liberator-example.example/handler

(def server (run-jetty handler {:port 3000 :join? false}))
;;=> #'liberator-example.example/server

(client/get "http://localhost:3000/customer")
