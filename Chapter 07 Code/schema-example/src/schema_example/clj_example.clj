(ns schema-example.clj-example
  (:require [schema.core :as s]
            [schema.coerce :as coerce :include-macros true]
            [schema-generators.complete :as c :include-macros true]
            [schema-generators.generators :as g  :include-macros true]
            [schema-generators.complete :as gc]
            ))

;;=> nil

(def Book
  {
   :name s/Str
   :author [s/Str]
   :category #{s/Keyword}
   :publisher s/Str})
;;=> #'schema-example.clj-example/Book

(s/validate
 Book
  {:name "Clojure Programming CookBook"
  :author ["Makoto Hashimoto" "Nicolas Modrzyk"]
  :category #{:programming :lisp :functional}
   :publisher "Packt Publishing"})
;;=> {:name "Clojure Programming CookBook", :author ["Makoto Hashimoto" "Nicolas Modrzyk"], :category #{:programming :lisp :functional}, :publisher "Packt Publishing"}

(s/validate
 Book
 {:name "Clojure Programming CookBook"
  :author ["Makoto Hashimoto" "Nicolas Modrzyk"]
  :category [:programming :lisp :functional]
  :publisher "Packt Publishing"})
;;=> ExceptionInfo Value does not match schema: {:category (not (set? a-clojure.lang.PersistentVector))}  schema.core/validator/fn--38164 (core.clj:155)

(s/validate s/Str "Makoto")
;;=> Makoto
(s/validate s/Int 100)
;;=> 100
(s/validate s/Num 100.0)
;;=> 100.0
(s/validate s/Bool true)
;;=> true
(s/validate s/Bool nil)
;;=> ExceptionInfo Value does not match schema: (not (instance? java.lang.Boolean nil))  schema.core/validator/fn--38164 (core.clj:155)

(s/validate [s/Str] ["Makoto Hashimoto" "Nicolas Modrzyk"])
;; => ["Makoto Hashimoto" "Nicolas Modrzyk"]

(s/validate {s/Keyword s/Int} {:year 2016})
;; => {:year 2016}

(s/validate #{s/Keyword} #{:lisp :functional})
;; => #{:lisp :functional}

(s/validate s/Any "Makoto")
;;=> "Makoto"

(s/validate s/Any 1.0)
;;=> 1.0

(s/validate s/Regex  #".*")
;;=> #".*"

(s/validate (s/enum "01" "02" "03") "01")
;;=> "01"

(s/validate (s/enum "01" "02" "03") "05")
;;=> ExceptionInfo Value does not match schema: (not (#{"03" "02" "01"} "05"))  schema.core/validator/fn--38164 (core.clj:155)

(s/defrecord Order
    [name :- s/Str
     price :- s/Num
     qty :- s/Int])
ll=> #'schema-example.clj-example/strict-map->Order

(s/explain Order)
;;=> (record schema_example.clj_example.Order {:name Str, :price Num, :qty Int})

(s/validate Order (map->Order {:name "Clojure Programming Cookbook" :price 32.00 :qty 10}))
;;=> #schema_example.clj_example.Order{:name "Clojure Programming Cookbook", :price 32.0, :qty 10}

(s/validate (s/pred #(and (< 0 %) (> 10 %))) 5)
;;=> 5

(s/validate (s/maybe s/Keyword) :price)
;;=> :price

(s/validate (s/maybe s/Keyword) nil)
;;=> nil

(s/validate (s/maybe s/Keyword) 1)
;;=> ExceptionInfo Value does not match schema: (not (keyword? 1))  schema.core/validator/fn--38164 (core.clj:155)

(def NumMapOrSetOrList (s/conditional map? {s/Keyword s/Num} set? #{s/Num} :else [s/Num]))
;;=> #'schema-example.clj-example/NumMapOrSetOrList

(s/validate NumMapOrSetOrList {:qty 1.0})
;;=> {:qty 1.0}

(s/validate NumMapOrSetOrList #{1 2 3})
;;=> #{1 3 2}

(s/validate NumMapOrSetOrList [1 2 3])
;;=> [1 2 3]

(def SmallInt (s/constrained long #(and (<= -32768 %) (>= 32767 %))))
;;=> #'schema-example.clj-example/SmallInt

(s/validate SmallInt 10)
;;=> 10

(s/validate SmallInt 500000)
;;=> ExceptionInfo Value does not match schema: (not (schema-example.clj-example/fn--39713 500000))  schema.core/validator/fn--38164 (core.clj:155)

(def Recursive {:key s/Int :value s/Str :children [(s/recursive #'Recursive)]})
;;=> #'schema-example.clj-example/Recursive

(s/validate Recursive
            {:key 1 :value "test", :children
             [{:key 2 :value "test2", :children [] }
              {:key 3 :value "test3", :children [{:key 4 :value "test4" :children []}]}]})
;;=> {:key 100, :value "test", :children [{:key 2, :value "test2", :children []} {:key 3, :value "test3", :children [{:key 4, :value "test4", :children []}]}]}


(s/validate Recursive
            {:key 1 :value "test", :children
             [{:key 2 :value "test2", :children [] }
              {:key 3 :value "test3", :children [{:key 4 :value "test4" :children []}]}]})
;;=> {:key 100, :value "test", :children [{:key 2, :value "test2", :children []} {:key 3, :value "test3", :children [{:key 4, :value "test4", :children []}]}]}

(s/defn int-add :- s/Int
  [& x :- [s/Int]]
  (reduce + x))
;;=> #'schema-example.clj-example/int-add

(int-add 1 1 2 3)
;;=> 7

(int-add 1 1.0 4)
;;=> 6.0

(s/set-fn-validation! true)
;;=> nil

(int-add 1 1.0 4)
;;=> ExceptionInfo Input to int-add does not match schema: [nil (not (integer? 1.0))]  schema-example.clj-example/eval49222/int-add--49227 (form-init3277965317664434982.clj:262)

(s/set-fn-validation! false)
;;=> nil

(int-add 1 1.0 4)
;;=> 6.0

(s/defn ^:always-validate wrong-int-add :- s/Int
    [& x :- [s/Int]]
  (bigdec (reduce + x)))
;;=> #'schema-example.clj-example/wrong-int-add

(wrong-int-add 1 1)
;;=> ExceptionInfo Output of wrong-int-add does not match schema: (not (integer? 2M))  schema-example.clj-example/eval49476/wrong-int-add--49481 (form-init3277965317664434982.clj:284)

(time
 (dotimes [n 100000]
   (s/with-fn-validation
     (int-add 1 2))))
;;"Elapsed time: 587.858912 msecs"
;;=> nil

(time
 (dotimes [n 100000]
   (s/without-fn-validation
    (int-add 1 2))))
;;=> "Elapsed time: 150.847882 msecs"
;;=> nil

(s/set-fn-validation! true)
;;=> nil

(time
 (dotimes [n 100000]
   (int-add 1 2)))
;;=> "Elapsed time: 140.624018 msecs"
;;=> nil

(s/set-fn-validation! false)
;;=> nil

(time
 (dotimes [n 100000]
   (int-add 1 2)))
;;=> "Elapsed time: 28.948555 msecs"
;;=> nil

(time
 (dotimes [n 100000]
   (+ 1 2)))
;;=> "Elapsed time: 7.058001 msecs"
;;=> nil

(time
 (dotimes [n 1000000]
   (apply int-add (range 100))))
"Elapsed time: 3387.52923 msecs"
;;=> nil

(time
 (dotimes [n 1000000]
   (apply + (range 100))))
"Elapsed time: 3681.930561 msecs"
;;=> nil

(def Book
  {
   :name s/Str
   :author [s/Str]
   :keyword #{s/Keyword}
   :publisher s/Str
   :category (s/enum :it :romance :business-money)})
;;=> #'schema-example.clj-example/Book

(g/generate Book)
;;=> {:name "gWTFZ>'", :author ["ECg5Q" " i>Y8z\\2"], :keyword #{}, :publisher "s@", :category :it}

(g/sample 100 Book)
;;=> ({:name "", :author [], :keyword #{}, :publisher "", :category :business-money}
;;=>  {:name "", :author [""], :keyword #{:Wt}, :publisher ">", :category :it}
;;=>  {:name "z", :author [], :keyword #{:j}, :publisher "jK", :category :business-money}
;;=>    ......

(g/sample 10 s/Str)
;;=> ("" "'" "##" "r8" "" "'Uqn3" "" "F86O2Bl" "AR~2," "")

(g/sample 10 s/Int)
;;=> (-1 -1 -2 1N 0N -9 -1N 23 2 -6)

(g/sample 10 s/Num)
;;=> (0 0.75 0 1 -1 2.0 -4 -2 2.0 0.5)

(gc/complete {:name "foo" :keyword #{:bar}} Book)
;;=> {:name "foo", :author ["ft?" "yY31"], :keyword #{:bar}, :publisher "kw", :category :romance}


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
   :ordered-product [[(s/one s/Str "s")(s/one s/Int "s")(s/one s/Num "s")]]
   })
;;=> #'schema-example.cljs-example/ReplyOrder

(defn RequestOrder->ReplyOrder
  [{:keys [id product qty unit-price product2 qty2 unit-price2]  :as request-params}]
  {:order-id id :ordered-product [[product qty unit-price][product2 qty2 unit-price2]]})
;;=> #'schema-example.cljs-example/RequestOrder->ReplyOrder

(def order-coercer
  (coerce/coercer ReplyOrder
                  {ReplyOrder RequestOrder->ReplyOrder}))
;;=> #'schema-example.cljs-example/order-coercer                  

(order-coercer
 {:id "10" :product "pencil" :qty 5 :unit-price 1.0 :product2 "note" :qty2 5 :unit-price2 3.0})
 ;;=> {:order-id "10", :ordered-product [["pencil" 5 1] ["note" 5 3]]}

(s/defschema ReplyOrder2
  { :order-id s/Str
   :ordered-product
   [{(s/required-key :product-name) s/Str
     (s/required-key :qty) s/Int
     (s/required-key :unit-price) s/Num}]})
;;=> #'schema-example.cljs-example/RequestOrder     

(defn RequestOrder->ReplyOrder2
  [{:keys [id product qty unit-price product2 qty2 unit-price2]  :as request-params}]
  {:order-id id
   :ordered-product [
                     {:product-name product :qty qty :unit-price unit-price}
                     {:product-name product2 :qty qty2 :unit-price unit-price2}]})
;;=> #'schema-example.cljs-example/RequestOrder->ReplyOrder2

(def order-coercer2
  (coerce/coercer ReplyOrder2
                  {ReplyOrder2 RequestOrder->ReplyOrder2}))
;;=> #'schema-example.clj-example/order-coercer2

(order-coercer2
 {:id "10" :product "pencil" :qty 5 :unit-price 1.0 :product2 "note" :qty2 5 :unit-price2 3.0})
;;=> {:order-id "10", :ordered-product [{:product-name "pencil", :qty 5, :unit-price 1} {:product-name "note", :qty 5, :unit-price 3}]}

