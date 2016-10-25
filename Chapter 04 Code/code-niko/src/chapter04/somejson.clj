(require '[cheshire.core :refer :all])

(def url
"http://api.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&cluster=yes&appid=2de143494c0b295cca9337e1e96b00e0")
(def slurped
  (slurp url))

(-> slurped
parse-string
(get "list"))

(generate-string {:friends {:friend "nico" :age 15}})

(parse-smile
 (generate-smile {:foo "bar" :baz 5}))


(parse-smile
 (generate-smile {:friends {:friend "nico" :age 15}}))


(println
 (generate-string {:friends {:friend "nico" :age 15}} {:pretty true}))

(parse-string "{\"friends\":{\"friend\":\"nico\",\"age\":15}}" (fn [k] (keyword (.toUpperCase k))))
