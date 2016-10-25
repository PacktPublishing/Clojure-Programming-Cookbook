(use 'clojure.test)

(def result (atom 0))

(Given #"^I add (\d+) and (\d+)$" [a b]
       (reset! result (+ (bigdec a) (bigdec b))))

(Then #"^the result is (\d+)$" [expected]
      (assert (= (bigdec expected) @result)))