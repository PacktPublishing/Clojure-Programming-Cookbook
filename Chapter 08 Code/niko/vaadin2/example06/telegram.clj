(ns telegram
	(:require [morse.polling :as p])
	(:require [morse.handlers :refer :all]))

(def token "226640999:AAGu_1_QvZWQ72hJEbNfPLpIUJaJbZUoWoc")
	
(def last-value
	(atom ""))

(defhandler bot-api
  (message 
  	message 
  	(dosync (reset! last-value (:text message)))))

(def channel
	(p/start token bot-api))