(ns chapter01.primitives)
;;
;; Working with primitive data types
;;
(+ 1 2)
;;=> 3
(+ 1 2 3)
;;=> 6
(+ 1.2 3.5)
;;=> 4.7
(- 3 2)
;;=> 1
(- 1 2 3)
;;=> -4
(- 1)
;;=> -1
(* 5 2)
;;=> 10 
(* 1 2 3 4 5)
;;=> 120
(/ 10 5)
;;=> 2 
(/ 10 3)
;;=> 10/3
(float (/ 10 3))
;;=> 3.3333333
(double (/ 10 3))
;;=> 3.333333333333333
(quot 10 3)
;;=> 3
(rem 10 3)
;;=> 1
(bigdec (/ 10 2))
;;=> 5M
(bigdec (/ 10 3))
;; ArithmeticException Non-terminating decimal expansion; no exact representable decimal result.  ;; java.math.BigDecimal.divide (BigDecimal.java:1690)
(= (bigint 10) 10N)
;;=> true
"Hello world ! "
;;=> "Hello world ! "
(str "Hello " "world !" " Clojure")
;;=> "Hello world ! Clojure"
(.length "Hello world !")
;;=> 13
(clojure.string/blank? "   ")
;;=> true
(clojure.string/trim "  Hello ")
;;=> "Hello"
(clojure.string/upper-case "clojure")
;;=> "CLOJURE"
(clojure.string/capitalize "clojure")
;;=> "Clojure"
(clojure.string/lower-case "REPL")
;;=> "repl"
\a
;;=> \a
(int \a)
;;=> 97
(char 97)
;;=> \a
(seq "Hello world!")
;;=> (\H \e \l \l \o \space \w \o \r \l \d \!)
(str \C \l \o \j \u \r \e)
;;=> "Clojure"
true
;;=> true
false
;;=> false
(= 1 1)
;;=> true
(= 1 2)
;;=> false
(= "Hello" "Hello")
;;=> true
(= "Hello" "hello")
;;=> false
(not true)
;;=> false
(not false)
;;=> true
(not= 1 2)
;;=> true
(not true)
;;=> false
(true? (= 1 1))
;;=> true
(false? (= 1 1))
;;=> false
(if nil true false)
;;=> false
(seq [])
;;=> nil
(get {:a 1 :b 2} :c)
;;=> nil
(def pi 3.14159265359)
;;=> #'user/pi
pi
;;=> 3.14159265359
pi-not-bind
;;=> CompilerException java.lang.RuntimeException: Unable to resolve symbol:
;;    pi-not-bind in this context, compiling:(/tmp/form-init1426260352520034213.clj:1:7266)
:key1
;;=> :key1
(def person {:name "John McCarthy" :country "USA"})
;;=>#'living-clojure.core/person
(class 1)
;;=> java.lang.Long
(class 1.0)
;;=> java.lang.Double
(class (float 1.0))
;;=> java.lang.Float
(class 5.5M)
;;=> java.math.BigDecimal
(class 1N)
;;=> clojure.lang.BigInt
(class (/ 10 3))
;;=> clojure.lang.Ratio
(class "Hello world ! ")
;;=> java.lang.String
(class \a)
;;=> java.lang.Character
(class true)
;;=> java.lang.Boolean
(class false)
;;=> java.lang.Boolean
(class :key)
;;=> clojure.lang.Keyword
(class (quote a))
;;=> clojure.lang.Symbol
(class nil)
;;=> nil
0xff
;;=> 255
0400
;;=> 256
2r11111
;;=> 31
16rff
;;=> 255
8r11000
;;=> 4608
7r111
;;=>57
\u0031\u0032\u0061\u0062
;;=> \1
;;=> \2
;;=> \a
;;=> \b
+
;;=> #function[clojure.core/+]
(quote pi-not-bind)
;;=> pi-not-bind
(require '[clojure.math.numeric-tower :as math])
(math/expt 2 10)
;;=> 1024
(math/sqrt 10)
;;=> 3.1622776601683795

;;
;;Using bindings of vars, conditions, loops and error handlings
;;

(def x 100)
;;=> #'living-clojure.core/x
x
;;=> 100

(let [x 3 y 2]
  (println "x = " x ", y = " y)
  (* x y)
  )
;;=> x =  3 , y =  2
;;=> 6
(let [x 10]
  (if (> x 0)  x  (- x))
  )
;;=> 10
(let [x -10]
  (if (> x 0)  x  (- x))
  )
;;=> 10
(let [x -10]
  (if (> x 0)  x))
;;=> nil
(if-not true true false)
;;=> false
(let [x 10]
  (when (> x 0)
    (println "x = " x)
    (* x x)))
;;=> x = 10
;;=> 100
(let [x -10]
  (when (> x 0)
    (println "x = " x)
    (* x x)))
;;=> nil
(let [x 10]
  (when-not (<= x 0)
    (println "x = " x)
    (* x x)))
;;=>x = 10
;;=> 100
(let [x -10]
  (when-not (<= x 0)
    (println "x = " x)
    (* x x)))
;;=> nil
(let [x 2]
  (case x
    1 "one"
    2 "two"
    3 "three"
    "otherwise"
        ))
;;=> "two"
(let [x 4]
  (case x
    1 "one"
    2 "two"
    3 "three"
    "otherwise"
        ))
;;=> "otherwise"
(let [x 10]
  (cond
    (= x 1) "one"
    (= x 1) "two"
    (= x 3) "three"
    :else "otherwise"
        )
  )
(def x 10)
;;=> #'living-clojure.core/x
(do
  (println "x = " x)
  (+ x 1))
;;=> x = 10
;;=> 11
(dotimes [x 5]
  (println "square : " (* x x)))
;;=> square :  0
;;=> square :  1
;;=> square :  4
;;=> square :  9
;;=> square :  16
;;=> nil
(loop [x 1]
  (when (< x 5)
    (println "x = " x)
    (recur (inc x))
    ))
;;=> x =  1
;;=> x =  2
;;=> x =  3
;;=> x =  4
;;=> nil
(loop [x 1 ret 0]
  (if (> x 10)
    ret
    (recur (inc x) (+ ret x)) 
    )
)
;;=> 55

(try
  (do
    (println "Let's test try ... catch ... finally")
      (nth "Clojure" 7)
    )
  (catch Exception e
      (str "exception occured: " (.getMessage e)))
  (finally (println "test finished"))
  )
;;=> Let's test try ... catch ... finally
;;=> test finished
;;=> "exception occured: String index out of range: 7"
(let [x 3 y 2]
  (let [x 10 y 10]
    (println "inside : " (* x y))
    )
  (println "outside : " (* x y)) 
  )
;;=> inside :  100
;;=> outside :  6
;;=>  nil
(def x 1)
;;=> #'living-clojure/x
(println "global x = " x)
;;=> global x =  1
;;=> nil
(let [x 10] (println "local x = " x))
;;=>local x =  10
;;=> nil
(println "global x = " x)
;;=> global x =  1
;;=> nil
(macroexpand
  '(when (> x 0)
    (println "x = " x)
    (* x x)))
;;=> (if (> x 0) (do (println "x = " x) (* x x)))
(macroexpand '(if-not true true false))
;;=> (if (clojure.core/not true) true false)

;;
;; Using and defining functions
;;

(defn hello [s]
  (str "Hello world " s " !"))
;;=> #'living-clojure.core/hello
(hello "Nico")
;;=> "Hello world Nico !"
(hello "Makoto")
;;=> "Hello world Makoto !"
(defn simple-adder [x y]
  (+ x y)
  )
;;=> #'living-clojure.core/simple-adder
(simple-adder  2 3)
;;=> 5
(defn advanced-adder [x & rest]
  (apply + (conj rest x))
  )
;;=> #'living-clojure.core/advanced-adder
(advanced-adder 1 2 3 4 5)
;;=> 15
(defn multi-arity-hello
  ([] (hello "you"))
  ([name] (str "Hello World " name " !")))
;;=> #'living-clojure.core/multi-artiy-hello
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
;;=> #'living-clojure.core/make-product
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
;;=> #'living-clojure.core/pre-and-post-sqrt
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
;;=> #'living-clojure.core/make-triangle
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
;;=> #'living-clojure/pow-py-defn
(def pow-by-def (fn [x] (* x x)))
;;=> #'living-clojure/pow-by-def
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
