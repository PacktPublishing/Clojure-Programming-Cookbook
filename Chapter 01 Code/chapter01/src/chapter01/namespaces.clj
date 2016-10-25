(in-ns 'hello)

(def a 1)
; check the new definition is located in the hello namespace
; #'hello/a

(def b (clojure.core/fn[c] (clojure.core/inc c)))
(b 2)

(find-ns 'hello)
; #object[clojure.lang.Namespace 0x6156e60b "hello"]

(.getMappings (find-ns 'hello))
; [primitives-classnames ....


(ns-imports 'helloagain)

; use the function from the clojure namespace
(println
     (clojure.string/reverse (str (.getName *ns*))))
; use the imported java object
 (println (Random.))

(reverse
 (reverse "helloagain"))

(require 'potemkin)
(potemkin/import-vars [clojure.string reverse])
(user/reverse "hello")


