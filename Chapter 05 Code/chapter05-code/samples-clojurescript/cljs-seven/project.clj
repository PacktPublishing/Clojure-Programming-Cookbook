(defproject cljsbuild-example-advanced "1.1.2"
  :description "An advanced example of how to use lein-cljsbuild"
  :source-paths ["src-clj"]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 ]
  :plugins [
    [lein-cljsbuild "1.1.2"]
    [lein-simpleton "1.3.0"]
  ]
  :hooks [leiningen.cljsbuild]
  :cljsbuild {
    :builds {
      :dev
      {:source-paths ["src-cljs"]
       :jar true
       :compiler {:output-to "out/seven.js"
                  :output-dir "out"
                  :optimizations :none
                  :pretty-print true}}}})
