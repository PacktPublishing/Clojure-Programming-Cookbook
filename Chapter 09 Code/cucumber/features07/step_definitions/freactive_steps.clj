(use 'freacting.core) 
(use 'clojure.test)

(Given #"^It is a new day$"[]
	(reset! my-lens  0)
	(reset! total 0)
	(reset! last-person ""))

(Given #"^I met (.*?)$" [person]
	(i-meet person))

(Given #"^I meet those people:$" [table]
	(doseq [p (.raw table)]
		(i-meet (first p))))

(Then #"^I have met (.*?) people and the last person was (.*?)$" [_total _last]
	; (prn {:_total _total :_last _last :total @total :last @last-person})
	(assert (= @total (Integer/parseInt _total)))
	(assert (= @last-person _last))
	; check freactive original atom
	(assert (= @people (Integer/parseInt _total))))