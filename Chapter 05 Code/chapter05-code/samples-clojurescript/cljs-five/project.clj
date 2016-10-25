(defproject cljs-five "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 ]
  :plugins [
  	[lein-cljsbuild "1.1.2"]
    [lein-simpleton "1.3.0"] 
  ]
  :cljsbuild {
    :builds [{:source-paths ["src"]
              :compiler {
                :output-to "out/one.js"
                :output-dir "out"}}]})
