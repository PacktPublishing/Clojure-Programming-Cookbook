(defproject clojure-scala "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.scala-lang/scala-library "2.11.7"]                
                 ]
  :plugins [[io.tomw/lein-scalac "0.1.2"]]
  :scala-source-path "src/scala"
  ;;:scala-source-paths ["src/scala"] ;; This should work, but ...
  :scala-version "2.11.7"
  :prep-tasks ["scalac"]
  
  :main clojure-scala.core
  )
