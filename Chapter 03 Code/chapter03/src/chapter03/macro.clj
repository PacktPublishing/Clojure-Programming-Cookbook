; First macro
(defmacro my-very-first-macro []
  (list println "FIRST"))

(list println "FIRST")

(eval (list println "FIRST"))

(my-very-first-macro)

(eval `(println "FIRST"))


; Second macro

(defmacro x3 [ arg ]
 (list * 3 arg ))

(x3 2)

(defmacro x3 [ arg ]
  `(* 3 ~arg ))

; (x3 3)

; macro expand

(quote (* 3 3))
(str '(* 3 3))

(macroexpand-1 (quote (my-very-first-macro)))

(eval (macroexpand-1 '(x3 2)))
(eval '(x3 2))
(x3 2)

(defmacro w
  [test & body]
  (list 'if test (cons 'do body)))

(w true (println 1))

(macroexpand-1 '(when true (println 1) (println 2)))


(defmacro hello[x]
  (println (str "hello" x)))

;(hello 2)

(defmacro hello2a[]
  '(+ 1 1))

(defmacro hello2b[]
 (+ 1 1))

(hello2a)
(hello2b)

(macroexpand-1 '(hello2a))
(macroexpand-1 '(hello2b))

(defmacro hello2c [arg]
  '(+ 1 arg))

(hello2c 3)

(macroexpand-1 '(hello2c 3))

(defmacro hello2d [arg]
  `(+ 1 ~arg))

(macroexpand-1 '(hello2d 3))
(hello2d 3)

(defmacro hello2e [& body ]
  `(apply str  ~body))

(defmacro hello2f [& body ]
  `(apply str  ~@body))


(macroexpand-1 '(hello2e "1" "2" "3"))
(macroexpand-1 '(hello2f  "1" "2" "3"))

(hello2e "1" "2""3")

; SECOND RECIPIE


(defmacro rev [statement]
  (list (second statement) (first statement)))

(rev [(println "2") (println 1)])

(defmacro infix
  "Use this macro when you pine for the notation of your childhood"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))

; (infix (2 + 2  3))


(when true
  (println 1)
  (println 2))

(if true
  (println 1)
  (println 2))

;(defmacro div2 [ x ]

(defmacro macro-1 []
  `(+ 1 1))

(macroexpand-1
 '(macro-1))

(defmacro defs []
  (def a 1)
  (def b 2)
  nil
  )

(defs)

(defmacro myreverse [ s ]
  (list reverse s))

(myreverse "hello")

(defmacro inc2-quoted [arg]
  `(+ 2 ~arg))

(inc2-quoted 3)


(defmacro define-x-hygenically []
  `(do
     (def x# 2)
     (list x#)))

(define-x-hygenically)

(defn inline-2-helper
  [[arg1 & ops-and-args]]
  (let [ops (partition 2 ops-and-args)]
    (reduce apply-arg (clean-arg arg1) ops)))



(defmacro when
  "Evaluates test. If logical true, evaluates body in an implicit do."
  {:added "1.0"}
  [test & body]
  (list 'if test (cons 'do body)))

(defmacro my-when
  [test & body]
  `(if ~test (do ~@body)))

 (macroexpand-1
 '(my-when (odd? (rand-int 2))
  (println "hello")))

 (defmacro random-when
   [& body]

   (if (odd? (rand-int 2))
     `(do ~@body)
     (list)
     ))

(macroexpand-1
 '(random-when
    (println "Hello")))

(defmacro track-init[]
  `(do
  (def init (System/currentTimeMillis))
  (def up (fn [] (- (System/currentTimeMillis) init)))
  ))

(track-init)

init
(up)


(def LOG_LEVEL "INFO")

(defmacro log [msg]
  (if (= LOG_LEVEL "DEBUG")
    `(println ~msg)))

(log "hello3")

(def LOG_LEVEL "DEBUG")

(log "hello3")

(macroexpand-1
 '(log "hello3"))

(defmacro my-for-loop [[sym init check change :as params] & steps]
      `(loop [~sym ~init value# nil]
         (if ~check
           (let [new-value# (do ~@steps)]
             (recur ~change new-value#))
           value#)))

(gensym "hello")

(my-for-loop
  [ i 0 (< i 10) (inc i)]

  (println
   (str "- " (rand-int i))))

(macroexpand-1
 '
(my-for-loop
  [ i 0 (< i 10) (inc i)]

  (println
   (str "- " (rand-int i)))))


(require '[clojure.test :refer :all])

(deftest  my-test []
  (is (= 1 1)))

 (run-tests)
