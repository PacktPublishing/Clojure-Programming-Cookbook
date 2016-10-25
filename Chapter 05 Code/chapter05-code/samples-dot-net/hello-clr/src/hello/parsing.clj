(System.Reflection.Assembly/LoadWithPartialName "System.Xml")

(ns hello.parsing
  (:import [System.Xml XmlReader])
  (:import [System.IO StringReader])
  (:import [System.Text]))

(def xml "<bookstore>
        <book genre='autobiography' publicationdate='1981-03-22' ISBN='1-861003-11-0'>
            <title>The Autobiography of Benjamin Franklin</title>
            <author>
                <first-name>Benjamin</first-name>
                <last-name>Franklin</last-name>
            </author>
            <price>8.99</price>
        </book>
    </bookstore>")

(defn -main[& args]
(let [reader (XmlReader/Create (StringReader. xml))]
  (.ReadToFollowing reader "title")
  (println 
  (.ReadElementContentAsString reader))))