(use 'calculator.core) 
(use 'clojure.test)
(use 'qbits.jet.client.http)
(use 'clojure.core.async)
(require '[cheshire.core :refer :all])

(require '[monger.core :as mg]
         '[monger.collection :as mc]
         '[monger.operators :refer :all])

(def cl (client))
(def conn (mg/connect))
(def db (mg/get-db conn "local"))

(def data (atom nil))

(defn endpoint [city]
	(str 
		"http://api.openweathermap.org/data/2.5/weather?q="
		city
		"&APPID=9220dd1ac43dcaf7091d8c07a8a46efb&units=metric"))

(defn load-weather-data [city]
	(-> (get cl (endpoint city) {:as :json})
    		<!!
    		:body
    		<!!))

(defn store-data-to-mongo [city]
	(mc/insert 
		db
		"weather"
		(load-weather-data city)))

(When #"I load weather data for the following cities:$" [table]
	(mc/drop db "weather")
	(doseq [city (.raw table)]
		(store-data-to-mongo (first city))))

(Then #"^I expect the average to be lower than (\d+)$" [maximum]
	(let [
		coll "weather"
		average 
		  (:avg (first (mc/aggregate db coll [
		  	{$group {:_id "total" :avg {$avg "$main.temp"}}}])))
		]
		(assert (< average (bigdec maximum) ))))
