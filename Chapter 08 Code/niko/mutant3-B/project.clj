(defproject mutant3 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
  [org.clojure/clojure "1.8.0"]
  
  [org.immutant/immutant "2.1.4"
    :exclusions [org.projectodd.wunderboss/wunderboss-messaging-hornetq]]
  [org.immutant/wildfly "2.1.4"
    :exclusions [org.projectodd.wunderboss/wunderboss-messaging-hornetq]]
  [org.projectodd.wunderboss/wunderboss-artemis "0.2.0"]

  [ring/ring-devel "1.5.0"]
  [compojure "1.5.0"]
  ])
