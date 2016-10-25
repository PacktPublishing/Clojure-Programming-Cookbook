(ns custom-repl.client
	(:import [java.net  Socket]
        [java.io InputStreamReader OutputStreamWriter]
        [clojure.lang LineNumberingPushbackReader]))

(def client 
	(new Socket "localhost" 13579))

(def rdr (new LineNumberingPushbackReader 
              (new InputStreamReader (. client (getInputStream)))))
(def wtr (new OutputStreamWriter (. client (getOutputStream))))

(binding [*out* wtr]
  (prn '(+ 1 2 3))
  (flush)
  (read rdr))

(. client (close))
