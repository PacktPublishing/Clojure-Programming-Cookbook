(defproject withdocker "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [ring/ring-defaults "0.2.1"]]
  :main withdocker.handler                
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler withdocker.handler/app}
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})