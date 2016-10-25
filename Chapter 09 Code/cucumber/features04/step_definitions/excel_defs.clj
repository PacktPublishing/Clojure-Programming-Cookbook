(use 'calculator.core) 
(use 'clojure.test)
(use 'dk.ative.docjure.spreadsheet)

(def result (atom 0))
(def expected (atom 0))

(defn load-list [sheet] 
	(let [
  		book (load-workbook "fixtures/data.xlsx")
		mm 
		(map  #(:price %) (rest 
    		(->> book
       			(select-sheet sheet)
       			(select-columns {:A :name, :B :price}))))
		results (->> book (select-sheet "Results") (select-columns {:A :Name, :B :Total}))
		_expected (:Total (first (filter #(= sheet (:Name %)) results)))]
	(reset! result (apply + mm))
	(reset! expected _expected)))

(Given #"^I use sheet \"(.*?)\"$" [sheet]
       (load-list sheet))

(Then #"^the result is validated$" []
	(= @result @expected))