(defproject chapter04 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
  [org.clojure/clojure "1.8.0-RC4"]

  ; MQTT
  [clojurewerkz/machine_head "1.0.0-beta9"]

  [info.hoetzel/clj-nio2 "0.1.1"]

  [org.clojure/core.async "0.2.374"]
  [http-kit "2.1.18"]

  [clj-yaml "0.4.0"]
  [cheshire "5.5.0"]
  [org.clojure/data.xml "0.1.0-beta1"]

  [com.cognitect/transit-clj "0.8.285"]

  [org.clojure/tools.reader "1.0.0-alpha2"]
  [clojure-msgpack "1.1.2"]
  [clojurewerkz/balagan "1.0.5"]
  ])
