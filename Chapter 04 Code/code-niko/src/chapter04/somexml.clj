(use 'clojure.data.xml)

; parsing
(def input-xml
  (slurp "http://w1.weather.gov/xml/current_obs/KSFO.rss"))

(def xml
(parse (java.io.StringReader. input-xml)))

(->> xml
 (:content)
 (filter #(= (:tag % :channel)))
 first
 (:content)
 (map #(:content %))
 first)


; generating

(let [tags
      (element :friends {}
      (element :friend {"age" 23} "Chris")
      (element :friend {"age" 32} "Nick")
      (element :friend {"age"  45} "Ray"))]
  (println (indent-str tags)))

;;   (with-open [out-file (java.io.FileWriter. "/tmp/foo.xml")]
    ;(emit tags *out*)

    (element :friend {"age" 23} "Chris")

(print (indent-str (element :friends {:foo "foo value"}
                     (element :close {:bar "bar value"}
                       (element :baz {} "The baz value1")
                       (element :baz {} "The baz value2")
                       (element :baz {} "The baz value3")))))

; parsing namespaces
(declare-ns "myns" "http://www.w3.org/1999/xhtml")
(parse-str "<?xml version=\"1.0\" encoding=\"UTF-8\"?>
            <foo:html xmlns:foo=\"http://www.w3.org/1999/xhtml\">
              <foo:head>
                <foo:title>Example title</foo:title>
              </foo:head>
              <foo:body>
                <foo:p>Example Paragraph</foo:p>
              </foo:body>
            </foo:html>")

