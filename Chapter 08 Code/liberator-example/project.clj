(defproject liberator-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :ring {:handler liberator-example.example/handler}
  :dependencies [
                 [org.clojure/clojure "1.9.0-alpha7"]
                 [liberator "0.14.1"]
                 ;;[compojure "1.3.4"]
                 [compojure "1.5.0"]
                 [ring "1.5.0-RC1"]
                 [ring/ring-core "1.5.0-RC1"]
                 [buddy "0.13.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [com.taoensso/timbre "4.4.0"]
                 [clj-http "2.2.0"]
                 [cheshire "5.6.1"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.apache.derby/derby "10.12.1.1"]]
  :plugins [[lein-ring "0.9.7"]]
  
  
  )

