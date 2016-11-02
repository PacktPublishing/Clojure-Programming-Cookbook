(ns liberator-example.using-db
  (:require
   [ring.adapter.jetty :refer [run-jetty]]
   [liberator.core :refer [resource defresource]]
   [ liberator.dev :refer [wrap-trace]]
   
   [ring.middleware.params :refer [wrap-params]]
   [compojure.route :as route]
   
   [compojure.core :refer
    [defroutes ANY GET PUT POST DELETE OPTIONS]]

   [clj-http.client :as client]
   [cheshire.core :refer [parse-string generate-string]]
   
   [clojure.java.jdbc :as j]))
;;=> nil

(def db-path "/tmp/db/products")
;;=> #'liberator-example.using-db/db-path

(def db {
         :classname "org.apache.derby.jdbc.EmbeddedDriver"
         :subprotocol "derby"
         :subname db-path
         :create true})
;;=> #'liberator-example.using-db/db

(j/db-do-commands db
                  (j/create-table-ddl
                   :product
                   [[:id :int "primary key"
                     "generated always as identity 
                (start with 1, increment by 1)"]
                    [:name "varchar(20)" "not null"]
                    [:maker "varchar(10)" "not null"]
                    [:description "varchar(100)" ]
                    [:clock :float]
                    [:ram :int]
                    [:price "decimal(7,2)"]]))
;;=> (0)

(defn insert-product! [db product]
  (j/insert! db :product product))
;;=> #'liberator-example.using-db/insert-product!

(defn update-product! [db id product] 
  (j/update! db :product product ["id = ?" id]))
;;=> #'liberator-example.using-db/update-product!

(defn delete-product! [db id] 
  (j/delete! db :product  ["id = ?" id]))
;;=> #'liberator-example.using-db/delete-product!

(defn select-product-by-id [db id]
  (first (j/query db ["select * from product where id = ?" id])))
;;=> #'liberator-example.using-db/select-product-by-id

(defn make-query-string [m]
  (str (apply str
              (interpose " like ? and "
                         (map name (keys m)))) " like ? "))
;;=> #'liberator-example.using-db/make-query-string

(make-query-string {:var-1 "a" :var-2 "b" :var-3 "c"})
;;=> "var-1 like ? and var-2 like ? and var-3 like ? "

(defn select-products [db & params]
  (let [qs
        (if-not params
           "select * from product"
           (str "select * from product where " (make-query-string (first params))))
        v (vals (first params))]
    (println (vec (cons qs v)))
    (j/query db (vec (cons qs v)))))
;;=> #'liberator-example.using-db/select-products

(insert-product! db
                 {:name "macbook air 11.6" :maker "Apple"
                  :description "Apple MacBook Air MJVM2LL/A 11.6-Inch laptop"
                  :clock 1.6 :ram 4 :price 797.00})
;;=> ({:1 1M})

(insert-product! db
                 {:name "thinkpad x260" :maker "Lenovo"
               :description "Lenovo ThinkPad X260 20F6005HUS 12.5 Ultrabook"
                  :clock 2.3 :ram 8 :price 1009.00})
;;=> ({:1 2M})

(select-product-by-id db 1)
;;=> {:id 1, :name "macbook air 11.6", :maker "Apple", :description "Apple MacBook Air MJVM2LL/A 11.6-Inch laptop", :clock 1.6, :ram 4, :price 797.00M}

(select-products db)
;;=> ({:id 11, :name "macbook air 11.6", :maker "Apple", :description "Apple MacBook Air MJVM2LL/A 11.6-Inch laptop", :clock 1.6, :ram 4, :price 797.00M} {:id 12, :name "thinkpad x260", :maker "Lenovo", :description "Lenovo ThinkPad X260 20F6005HUS 12.5 Ultrabook", :clock 2.3, :ram 8, :price 1009.00M})

(select-products db {:name "%a%" :description "%M%"})
;;=> ({:id 11, :name "macbook air 11.6", :maker "Apple", :description "Apple MacBook Air MJVM2LL/A 11.6-Inch laptop", :clock 1.6, :ram 4, :price 797.00M})

(select-products db {:maker "Apple"})
;;=> ({:id 11, :name "macbook air 11.6", :maker "Apple", :description "Apple MacBook Air MJVM2LL/A 11.6-Inch laptop", :clock 1.6, :ram 4, :price 797.00M})

(defn to-keyword-map [m]
  (into {}  (map #(vector (keyword (first %))  (str "%" (second %) "%")) m)))
;;=> #'liberator-example.using-db/to-keyword-map

(defresource product-list
  :allowed-methods [:get :post]
  :available-media-types ["application/json"]
  :exists? (fn [ctx]
             (let [params (-> ctx
                              (get-in [:request :params])
                              to-keyword-map)
                   result (if (empty? params) (select-products db) (select-products db params))]
               (if-not (empty? result)
                 {::entry result})))
    :post!  (fn [ctx]
            (let [body (parse-string (slurp (get-in ctx [:request :body])) true)]              
              (insert-product! db body)))
  :handle-ok ::entry
  :handle-not-found {:error "no product is not found"})
;;=> #'liberator-example.using-db/product-list

(defresource product-entity [id]
  :allowed-methods [:get :put :delete]
  :available-media-types ["application/json"]
  :exists? (fn [_]
             (let [result (select-product-by-id db id)]
               (if-not (empty? result)
                 {::entry result})))
  :put!  (fn [ctx]
           (let [body (parse-string (slurp (get-in ctx [:request :body])) true)]
             (update-product! db id body)))
  :delete!  (fn [_]
              (delete-product! db id))
  :existed? (fn [_] (empty? (select-product-by-id db id)))
  :new? (fn [_] (empty? (select-product-by-id db id)))
  :handle-ok ::entry
  :handle-not-found {:error "id is not found"})
#'liberator-example.using-db/product-entity

(defroutes product
  (ANY "/product" [] product-list)
  (ANY "/product/:id" [id] (product-entity id)))
#'liberator-example.using-db/product

(def handler 
  (-> product
      wrap-params))
;;=> #'liberator-example.using-db/handler

(def server (run-jetty handler {:port 3000 :join? false}))
;;=> #'liberator-example.using-db/server

(client/post "http://localhost:3000/product"
             {:body
              (generate-string
               {:name "ASUS VivoBook" :maker "ASUS"
               :description "ASUS VivoBook E200HA-US01-GD Portable 11.6"
                :clock 1.8 :ram 2 :price 199.99})})
;;=> {:status 201, :headers {"Date" "Fri, 01 Jul 2016 00:38:27 GMT", "Content-Type" "application/json", 
;;=> "Vary" "Accept", "Connection" "close", "Server" "Jetty(9.2.10.v20150310)"}, :body "", 
;;=> :request-time 34, :trace-redirects ["http://localhost:3000/product"], :orig-content-encoding nil}

(client/post "http://localhost:3000/product"
             {:body
              (generate-string
               {:name "Dell Inspiron" :maker "DELL"
                :description "Dell Inspiron 11.6-Inch 2 in 1 Convertible Touchscreen Laptop"
                :clock 2.16 :ram 4 :price 279.99})})               
;;=> {:status 201, :headers {"Date" "Fri, 01 Jul 2016 00:38:36 GMT", "Content-Type" "application/json", 
;;=> "Vary" "Accept", "Connection" "close", "Server" "Jetty(9.2.10.v20150310)"}, :body "", 
;;=> :request-time 25, :trace-redirects ["http://localhost:3000/product"], :orig-content-encoding nil}

(-> (client/get "http://localhost:3000/product") :body (parse-string true) count)
;;=> 4

(-> (client/get "http://localhost:3000/product/1") :body (parse-string true))
;;=> {:id 1, :name "macbook air 11.6", :maker "Apple", 
;;=>  :description "Apple MacBook Air MJVM2LL/A 11.6-Inch laptop", :clock 1.6, :ram 4, :price 797.0}

(client/put "http://localhost:3000/product/4"
               {:body
                (generate-string
                 {:price 259.99})})
;;=> {:status 204, :headers {"Date" "Fri, 01 Jul 2016 12:52:01 GMT", "Content-Type" "application/json", "Vary" "Accept", "Connection" "close", "Server" "Jetty(9.2.10.v20150310)"}, :body nil, :request-time 33, :trace-redirects ["http://localhost:3000/product/4"], :orig-content-encoding nil}

(-> (client/get "http://localhost:3000/product/4") :body (parse-string true) :price )
;;=> 259.99

(client/delete "http://localhost:3000/product/4")
;;=> {:status 204, :headers {"Date" "Fri, 01 Jul 2016 12:52:19 GMT", "Content-Type" "application/json", "Vary" "Accept", "Connection" "close", "Server" "Jetty(9.2.10.v20150310)"}, :body nil, :request-time 26, :trace-redirects ["http://localhost:3000/product/4"], :orig-content-encoding nil}

(-> (client/get "http://localhost:3000/product") :body (parse-string true) count)
;;=> 3





