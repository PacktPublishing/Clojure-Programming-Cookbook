(use 'clojure.test)

(def total (atom (long 0)))

(defn sum-table[table]
	(reduce + (map #(bigdec (second %)) table)))

(Given #"^the following cukes:$" [table]
	   (reset! total (sum-table (.raw table))))

(Then #"^the total should be (\d+).$" [result]
	(assert (= (bigdec result) @total)))

;
; CSV 

(require '[environ.core :refer [env]])
(require '[clojure.data.csv :as csv]
         '[clojure.java.io :as io])

(defn load-table [file]
 (with-open [in-file (io/reader (str "fixtures/" file))]
   (doall
    (csv/read-csv in-file))))


(defn load-table-from-env []
	(load-table  (env :fixtures)))

(Given #"^data from file \"(.*?)\":$" [file]
	(reset! total (sum-table (load-table file))))

(Given #"^data from environment$" []
	(reset! total (sum-table (load-table-from-env))))
