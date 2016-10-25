(ns chapter05.calling-java)

(.concat "Hello " "Makoto")
;;=> "Hello Makoto"

(Float/valueOf "1000.0")
;;=> 1000.0
(. Float valueOf "1000.0")
;;=> 1000.0

(import java.util.TimeZone)
;;=> java.util.TimeZone
(def time-zone (TimeZone/getTimeZone "Asia/Tokyo" ))
;;=> #'chapter05.calling-java/time-zone
(.getDisplayName time-zone (java.util.Locale. "en_US"))
;;=> "Japan Standard Time"

(import java.awt.Point)
;;=> java.awt.Point
(def p (Point. 100 200))
;;=> #'chapter05.calling-java/p
(. p x)
;;=> 100
(. p y)
;;=> 200

(set! (.x p) 300)
;;=> 300
(. p x)
;;=> 300

Math/E
;;=> 2.718281828459045

(def t (Thread.))
;;=> #'chapter05.calling-java/t
(.getState t)
;;=> #object[java.lang.Thread$State 0xc736e3e "NEW"]
(= (.getState t) Thread$State/NEW)
;;=> true
(.start t)
;;=> nil
(= (.getState t) Thread$State/TERMINATED)

(Thread$State/values)
;;=> #object["[Ljava.lang.Thread$State;" 0x3efd737c "[Ljava.lang.Thread$State;@3efd737c"]
(Thread$State/valueOf "NEW")
;=> #object[java.lang.Thread$State 0xc736e3e "NEW"]

String
;;=> java.lang.String
(= String (Class/forName "java.lang.String"))
;;=> true
(= java.lang.String (Class/forName "java.lang.String"))
;;=> true

(def str-array (into-array '("a" "b" "c" "d" "e")))
;;=> #'chapter05.calling-java/str-array

(aget str-array 0)
;;=> "a"

(aset str-array 0 "x")
;;=> "x"
(aget str-array 0)
;;=> "x"


(time
 (let [n 10000000
       a (long-array (range n))]
   (reduce + a)))
;;=> "Elapsed time: 1339.504072 msecs"
;;=> 49999995000000
(time
 (let [n 10000000
       a (into-array (range n))]
   (reduce + a)))
;;=> "Elapsed time: 6101.370939 msecs"
;;=> 49999995000000

(byte-array (map int [\C \l \o \j \o \u \r \e]))
;;=> #object["[B" 0x590f9b92 "[B@590f9b92"]
(String. (byte-array (map int [\C \l \o \j \o \u \r \e])))
;;=> "Clojoure"

(def mat1 (make-array Double/TYPE  10 10))
;;=> #'chapter05.calling-java/mat1
(aget mat1 9 9)
;;=> 0.0

(int-array [1 2])
;;=> #object["[I" 0x4d307a58 "[I@4d307a58"]

(make-array Double/TYPE  10 10)
;;=> #object["[[D" 0x77570b3c "[[D@77570b3c"]

(.. System getenv (get "JAVA_HOME"))
;;=> "/usr/local/oss/jdk1.8.0_65"

(macroexpand-1 '(.. System getenv (get "JAVA_HOME")))
;;=> (.. (. System getenv) (get "JAVA_HOME"))

(doto (java.util.HashMap.)
  (.put "key1" "value1")
  (.put "key2" "value2")
  (.put "key3" "value3")
  )
;;=> {"key1" "value1", "key2" "value2", "key3" "value3"}

(let [hash (java.util.HashMap.)]
    (.put hash "key1" "value1")
    (.put hash "key2" "value2")
    (.put hash "key3" "value3")
    hash
    )
;;=> {"key1" "value1", "key2" "value2", "key3" "value3"}

(use 'clojure.reflect)
;;=>nil
(use 'clojure.pprint)
;;=>nil
(->> (clojure.reflect/reflect java.lang.String)
     :members
     (filter #(.startsWith (str (:name %)) "get"))
     (clojure.pprint/pprint))
;;=>({:name getChars,
;;=>  :return-type void,
;;=>  :declaring-class java.lang.String,
;;=>  :parameter-types [int int char<> int],
;;=>  :exception-types [],
;;=>  :flags #{:public}}
;;=> {:name getBytes,
;;=>  :return-type byte<>,
;;=>  :declaring-class java.lang.String,
;;=>  :parameter-types [],
;;=>  :exception-types [],
;;=>  :flags #{:public}}
;;=> ....




