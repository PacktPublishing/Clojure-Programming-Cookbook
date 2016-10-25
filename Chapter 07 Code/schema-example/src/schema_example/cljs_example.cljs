(ns schema-example.cljs-example
  (:require [schema.core :as s :include-macros true]
            [schema.coerce :as coerce :include-macros true]
            [schema-generators.complete :as c :include-macros true]
            [schema-generators.generators :as g :include-macros true]
            [schema-generators.complete :as gc :include-macros true]            
            ))
;;=> nil

(s/defschema RequestOrder
  { :id s/Str
   :product s/Str
   :qty s/Int
   :unit-price s/Num
   :product2 s/Str
   :qty2 s/Int
   :unit-price2 s/Num})
;;=> #'schema-example.cljs-example/RequestOrder


(s/defschema ReplyOrder
  { :order-id s/Str
   :ordered-product [[(s/one s/Str "s")  (s/one s/Int "s")(s/one s/Num "s")]]   
   })
;;=> #'schema-example.cljs-example/ReplyOrder


(s/defschema ReplyOrder2
  { :order-id s/Str
   :ordered-product
   [{(s/required-key :product-name) s/Str
     (s/required-key :qty) s/Int
     (s/required-key :unit-price) s/Num}]})
;;=> #'schema-example.cljs-example/ReplyOrder2

(defn RequestOrder->ReplyOrder
  [{:keys [id product qty unit-price product2 qty2 unit-price2]  :as request-params}]
  {:order-id id :ordered-product [[product qty unit-price][product2 qty2 unit-price2]]})
;;=> #'schema-example.cljs-example/RequestOrder->ReplyOrder

(defn RequestOrder->ReplyOrder2
  [{:keys [id product qty unit-price product2 qty2 unit-price2]  :as request-params}]
  {:order-id id
   :ordered-product [
                     {:product-name product :qty qty :unit-price unit-price}
                     {:product-name product2 :qty qty2 :unit-price unit-price2}]})
;;=> #'schema-example.cljs-example/RequestOrder->ReplyOrder2


(def order-coercer
  (coerce/coercer ReplyOrder
                  {ReplyOrder RequestOrder->ReplyOrder}))
;;=> #'schema-example.cljs-example/order-coercer

(def order-coercer2
  (coerce/coercer ReplyOrder2
                  {ReplyOrder2 RequestOrder->ReplyOrder2}))
;;=> #'schema-example.cljs-example/order-coercer2

(order-coercer
 {:id "10" :product "pencil" :qty 5 :unit-price 1.0 :product2 "note" :qty2 5 :unit-price2 3.0})
;;=> {:order-id "10", :ordered-product [["pencil" 5 1] ["note" 5 3]]}


(order-coercer2
 {:id "10" :product "pencil" :qty 5 :unit-price 1.0 :product2 "note" :qty2 5 :unit-price2 3.0})
;;=> {:order-id "10", :ordered-product [{:product-name "pencil", :qty 5, :unit-price 1} {:product-name "note", :qty 5, :unit-price 3}]}

(def Book
  {
   :name s/Str
   :author [s/Str]
   :keyword #{s/Keyword}
   :publisher s/Str
   :category (s/enum :it :romance :business-money)
   })

(g/generate Book)

