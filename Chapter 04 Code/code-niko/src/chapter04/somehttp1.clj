(ns chapter04.somehttp1
  (:require [org.httpkit.client :as http]))


(def url
  (str
  "http://api.openweathermap.org/data/2.5/box/city?"
   "bbox=12,32,15,37,10"
   "&"
   "cluster=yes"
   "&"
   "appid=2de143494c0b295cca9337e1e96b00e0"))

(let [{:keys [status headers body error] :as resp} @(http/get url)]
  (println status))

(time
 (let [resp @(http/get url {:keepalive 60000})]
  (println (:opts resp))))


(let [{:keys [status headers body error] :as resp}
      @(http/post "http://requestb.in/tp6d3jtp" {:keepalive 120000 :query-params {:a 1}})]
  (println body))




(let [{:keys [status headers body error] :as resp}
      @(http/get "http://requestb.in/tp6d3jtp"
                 {
                  :multipart [
                           {:name "file" :content (clojure.java.io/file "resources/test1.json") :filename "test1.json"}]
                  :keepalive 120000})]
  (println body))

; ASYNC

(http/get "http://requestb.in/tp6d3jtp"
{:keepalive 120000 :query-params {:a 1}}
(fn [{:keys [status headers body error opts]}]
  (println body)))
