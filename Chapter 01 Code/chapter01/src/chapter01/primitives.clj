(ns chapter01.primitives)

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
;;=> #'chapter01.primitives/pi
pi
;;=> 3.14159265359
pi-not-bind
;;=> CompilerException java.lang.RuntimeException: Unable to resolve symbol:
;;    pi-not-bind in this context, compiling:(/tmp/form-init1426260352520034213.clj:1:7266)
:key1
;;=> :key1
(def person {:name "John McCarthy" :country "USA"})
;;=> #'chapter01.primitives/person
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
