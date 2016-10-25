(defproject chapter05 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :java-source-paths ["src/java"]
  :profiles {:uberjar {:aot :all}}
  :prep-tasks [
               ["compile" "chapter05.calling-from-java"]
               "javac" "compile" ]
  :main chapter05.Test1
    :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.apache.bcel/bcel "5.2"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [com.fasterxml.jackson.core/jackson-core "2.7.0"]
                 [com.fasterxml.jackson.core/jackson-databind "2.7.0"]
                 [com.fasterxml.jackson.core/jackson-annotations "2.7.0"]
                 ])

