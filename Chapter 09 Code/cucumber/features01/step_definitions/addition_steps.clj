(use 'calculator.core) 
(use 'clojure.test)

(def world (atom {:inputs []
                  :actual-result nil}))

(Given #"^I have entered (\d+) into the calculator$" [input]
       (swap! world update-in [:inputs] conj (bigdec input)))

(When #"^I press add$" []
      (swap! world assoc :actual-result (reduce add (:inputs @world))))

(Then #"^the result should be (\d+) on the screen$" [result]
      (assert (= (bigdec result) (:actual-result @world))))