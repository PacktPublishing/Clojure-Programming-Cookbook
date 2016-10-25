; 1- Using tools.timer

; import the third party library
(use 'ruiyun.tools.timer)

; run something every five seconds
(run-task! #(println "Say hello every 5 seconds.") :period 5000)

; cancels the timer
(cancel! *1)


; 2- using puget
; require the second third party library
(require '[puget.printer :as puget])

(puget/pprint #{'x :a :z 3 1.0})
; see console for output
(puget/cprint #{'x :a :z 3 1.0})
; see console for color print

; 3- Using adzerk/env
(require '[adzerk.env :as env])
(env/env)
; combine with previous library

(puget/pprint (env/env))

; 4- using pomegranate
(require '[cemerick.pomegranate :refer [add-dependencies]])
(add-dependencies
                 :coordinates '[[active-quickcheck "0.3.0"]]
                 :repositories  {"clojars" "http://clojars.org/repo"})
; check the below dependency is not in the project.clj
; but we can use it
(use 'active.quickcheck)

(quickcheck (property [a integer
                   b integer]
           (= (+ a b) (+ b a))))

