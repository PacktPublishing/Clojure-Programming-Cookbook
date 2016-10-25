; https://github.com/clojure/core.async/wiki/Pub-Sub

(require '[clojure.core.async :refer :all])
; (put! our-pub {:msg-type :burger :text "hello"})
; doesn't work

; publish
(def input-chan (chan))
(def our-pub (pub input-chan :food))

; subscribe
(def burger-chan (chan))
(sub our-pub :burger burger-chan)

(def sushi-chan (chan))
(sub our-pub :sushi sushi-chan)


(defn print-loop [channel prefix]
  (go-loop []
   (let [{:keys [option]} (<! channel)]
    (println prefix " " option)
    (recur))))
(print-loop sushi-chan :sushi)
(print-loop burger-chan :burger)

(>!! input-chan {:food :burger :option "with cheese"})
(>!! input-chan {:food :burger :option "with avocado"})

(thread 
  (>!! input-chan {:food :sushi :option "salmon"}))
