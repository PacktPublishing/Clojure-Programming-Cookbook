(defproject mutant3 "0.1.0-SNAPSHOT"
  :jvm-opts ["-Dactivemq.netty.host=0.0.0.0"]
  :dependencies [
  [org.clojure/clojure "1.8.0"]
  [org.immutant/immutant "2.1.4"
    :exclusions [org.projectodd.wunderboss/wunderboss-messaging-hornetq]]
  [org.projectodd.wunderboss/wunderboss-artemis "0.2.0"]
  [ring/ring-devel "1.5.0"]
  [compojure "1.5.0"]])
