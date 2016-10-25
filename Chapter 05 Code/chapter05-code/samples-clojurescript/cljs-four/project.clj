(defproject cljs-four "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [rm-hull/monet "0.2.2"]
                 ]
  :plugins [
  	[lein-cljsbuild "1.1.2"]
  ]
  :cljsbuild {
    :builds [{:source-paths ["src"]
              :compiler {
                :output-to "out/one.js"
                :output-dir "out"}}]})
