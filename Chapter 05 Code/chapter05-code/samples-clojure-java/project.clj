(defproject clojure_java "0.1.0-SNAPSHOT"
  :description "Clojure and Java Together"
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :java-source-paths ["src/java"]
  :aot :all
  :prep-tasks [["compile" ":all"] "javac" "compile"]
  :profiles {:uberjar {:aot :all}}
  ; :eval-in :nrepl
  :main clojure_java.progress
  ; :main Java4
  )