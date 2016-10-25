(System.Reflection.Assembly/LoadWithPartialName "System.Xml")

 (ns hello.net
  (:import [System.Net WebClient])
  (:import [System.Xml XmlReader])
  (:import [System.IO StringReader])
  (:import [System.Text])
  (:import [System.Text Encoding])
  (:require [clojure.core])
  (:gen-class 
  	:methods [[#^{:static true} getsunset [String] String]])
  )

(defn api-uri [city country]
  (str "https://query.yahooapis.com/v1/public/yql?q=select%20astronomy.sunset%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"
    city 
    "%2C%20"
    country
    "%22)&format=xml&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"))

(defn get-sunset [city country]
  (let [
    uri (api-uri city country) 
    res (.GetString Encoding/UTF8 (.DownloadData (WebClient.) uri))
    reader (XmlReader/Create (StringReader. res))
  ]
  (doto reader
    (.ReadToFollowing "astronomy" "http://xml.weather.yahoo.com/ns/rss/1.0")
    (.MoveToAttribute "sunset"))
  (str (.Value reader))))

(defn -getsunset [this city]
	(get-sunset city "jp")
	)

(defn -main[& args]
  (println (get-sunset (first args) "jp")))
 
 ; (get-sunset "tokyo" "jp")
 ; (get-sunset "osaka" "jp")
 ; (get-sunset "sapporo" "jp")
 
