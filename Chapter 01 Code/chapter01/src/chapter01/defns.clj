(ns chapter01.src.chapter01.defns)

(defn hello [s]
  (str "Hello world " s " !"))
;;=> #'chapter01.src.chapter01.defns/hello
(hello "Nico")
;;=> "Hello world Nico !"
(hello "Makoto")
;;=> "Hello world Makoto !"
(defn simple-adder [x y]
  (+ x y)
  )
;;=> #'chapter01.src.chapter01.defns/simple-adder

(simple-adder  2 3)
;;=> 5
(defn advanced-adder [x & rest]
  (apply + (conj rest x))
  )
;;=> #'chapter01.src.chapter01.defns/advanced-adder

(advanced-adder 1 2 3 4 5)
;;=> 15
(defn multi-arity-hello
  ([] (hello "you"))
  ([name] (str "Hello World " name " !")))
;;=> #'chapter01.src.chapter01.defns/multi-arity-hello
(multi-arity-hello)
;;=> Hello World you !
(multi-arity-hello "Nico")
  ;;=> Hello World Nico !
(defn make-product
  [serial &
   {:keys [product-name price description]
    :or [product-name "" price nil description "no description"]}   
   ]
   {:serial-no serial :product-name product-name
    :price price :description description}
  )
;;=> #'chapter01.src.chapter01.defns/make-product
(make-product "0000-0011"
              :product-name "personal-computer"
              :price 1000
              :description "This is a high performance note book"
              )
;;=> {:serial-no "0000-0011",
;;=>  :product-name "personal-computer",
;;=>  :price 1000,
;;=>  :description "This is a high performance note book"}
(make-product "0000-0011"              
              :price 1000              
              )
;;=> {:serial-no "0000-0011", :product-name "", :price 1000, :description "no description !"}
(require '[clojure.math.numeric-tower :as math])
;;=> nil
(math/sqrt -10)
;;=> NaN
(defn pre-and-post-sqrt [x]
  {:pre  [(pos? x)]
   :post [(< % 10)]}
  (math/sqrt x))
;;=> #'chapter01.src.chapter01.defns/pre-and-post-sqrt
(pre-and-post-sqrt 10)
;;=> 3.1622776601683795
(pre-and-post-sqrt -10)
;;=> AssertionError Assert failed: (pos? x)  user/pre-and-post-sqrt (form-init2377591389478394456.clj:1)
(pre-and-post-sqrt 120)
;;=> AssertionError Assert failed: (< % 10)  user/pre-and-post-sqrt (form-init2377591389478394456.clj:1)

(defn make-triangle
  [no & {:keys [char] :or {char "*"}}]
  (loop [x 1]
    (when (<= x no) 
      (dotimes 
          [n (- no x)] (print " "))
      (dotimes 
          [n 
           (if (= x 1) 
             1    
             (dec (* x 2)))]
        (print char))
      (print "\n")
      (recur (inc x))
      )
    )
  )
;;=> #'chapter01.src.chapter01.defns/make-triangle
(make-triangle 5)    
;;=>     *
;;=>    ***
;;=>   *****
;;=>  *******
;;=> *********
;;=> nil
(make-triangle 6 :char "x")
;;=>      x
;;=>     xxx
;;=>    xxxxx
;;=>   xxxxxxx
;;=>  xxxxxxxxx
;;=> xxxxxxxxxxx
(defn pow-py-defn [x] (* x x))
;;=> #'chapter01.src.chapter01.defns/pow-py-defn
(def pow-by-def (fn [x] (* x x)))
;;=> #'chapter01.src.chapter01.defns/pow-by-def
(pow-py-defn 10)
;;=> 100
(pow-by-def 10)
;;=> 100
(require 'clojure.string)
;;=> nil
(clojure.repl/dir clojure.string)
;;=> blank?
;;=> capitalize
;;=> escape
;;=> join
;;=> lower-case
;;=> re-quote-replacement
;;=> replace
;;=> replace-first
;;=> reverse
;;=> split
;;=> split-lines
;;=> trim
;;=> trim-newline
;;=> triml
;;=> trimr
;;=> upper-case
;;=> nil
(clojure.repl/doc clojure.string/trim)
;;=> -------------------------
;;=> clojure.string/trim
;;=> ([s])
;;=>   Removes whitespace from both ends of string.
;;=> nil
(clojure.repl/apropos "defn")
;;=> (clojure.core/defn
;;=>  clojure.core/defn-
;;=>  deps.compliment.v0v2v4.compliment.sources.local-bindings/defn-like-forms
;;=>  deps.compliment.v0v2v4.deps.defprecated.v0v1v2.defprecated.core/de
