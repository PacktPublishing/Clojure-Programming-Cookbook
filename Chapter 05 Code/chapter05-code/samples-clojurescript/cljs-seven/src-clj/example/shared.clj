(ns example.shared)

(def fib-seq 
  ((fn rfib [a b] 
     (lazy-seq (cons a (rfib b (+ a b)))))
   0 1))
