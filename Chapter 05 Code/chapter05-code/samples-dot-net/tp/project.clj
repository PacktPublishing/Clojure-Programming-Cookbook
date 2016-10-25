(defproject tp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :main tp.core
  :profiles {
  	:uberjar {
  		:aot :all
  	}
  }
  :uberjar-name "tp.jar"
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [com.climate/claypoole "1.1.2"]
    [tesser.core "1.0.1"]
  ])
