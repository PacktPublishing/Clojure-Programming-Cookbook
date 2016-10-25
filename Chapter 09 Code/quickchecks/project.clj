(defproject quickchecks "0.1.0-SNAPSHOT"
  :profiles {:dev {:dependencies [
  [midje "1.8.3"]
  ]
    
  :plugins [[lein-midje "3.2"]]}}
  :dependencies [
  [org.clojure/clojure "1.8.0"]
  [compojure "1.5.1"]

  [org.clojure/test.check "0.9.0"]
  [com.velisco/herbert "0.7.0"]   
  [com.gfredericks/test.chuck "0.2.6"]
  ])
