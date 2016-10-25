(ns withatom.quotes
  (:require [cheshire.core :refer :all]))

(defn get-quotes
  [label start end]
  (let [query
    (str
    "https://query.yahooapis.com/v1/public/yql"
    "?q=select%20*%20from%20yahoo.finance.historicaldata%20"
    "where%20symbol%20%3D%20%22"
    label
    "%22%20and%20startDate%20%3D%20%22"
    start
    "%22%20and%20endDate%20%3D%20%22"
    end
    "%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    ]
  (reverse
    (get-in
    (parse-string
      (slurp query))
     ["query" "results" "quote"]))))

(let[ result
  (get-quotes "MSFT" "2016-02-10" "2016-03-20")]
  (prc/line-chart
   "APPL"
   {
    "quote" (map #(% "Open") result)
  }
   {:labels (map #(% "Date") result)}))
