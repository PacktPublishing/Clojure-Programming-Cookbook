(ns quickchecks.repl0)


(require '[clojure.test.check.generators :as gen])

(gen/sample (gen/return 1))
; (1 1 1 1 1 1 1 1 1 1)

(gen/sample (gen/return 1) 5)

(gen/sample (gen/return 1) 20)
; (1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1)

(gen/sample (gen/elements (range 10)))
; (6 2 6 1 0 0 0 5 2 7)

(gen/sample (gen/not-empty (gen/vector gen/int)) 5)
; ([-2 -2] [1] [-1 2 0] [2 0] [5 -5])

(gen/sample (gen/tuple gen/int (gen/not-empty gen/string)) 3)
; ([0 "1"] [0 "©"] [1 "8"])

(require    '[com.gfredericks.test.chuck.generators :as gen'])

(gen/sample (gen/not-empty (gen'/string-from-regex #"(B(A|O)M)+")) 1)
 ; ("BOM")

(require '[miner.herbert.generators :as hg])

(hg/sample '[int*] 5)
; ([] () [2 0] () (-4 -2))

(hg/sample '{:a int :b [sym+] :c str} 3)
; ({:c "", :b (*mt), :a 0} 
; {:c "", :b [_... !.Z _B3.], :a 0} 
; {:c ")", :b (.14 .), :a 9223372036854775807})

(hg/sample '{:a num :b pos :c neg :d (vec int+ zero+)} 1)
; ({:c -2.0, :b 1, :d [9223372036854775807 0], :a 1.5E-5})