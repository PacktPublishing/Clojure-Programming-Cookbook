(defproject riemaning "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :java-source-paths ["java"]
  :repositories [
    ["sonatype" {:url "https://oss.sonatype.org/content/repositories/snapshots"}]]
  :dependencies [
  [edu.cmu.sphinx/sphinx4-core "5prealpha-20160218.203526-7"]
  [edu.cmu.sphinx/sphinx4-data "5prealpha-20160218.203535-7"]
  [riemann-clojure-client "0.4.2"]
  [slack-rtm "0.1.3"]
  [org.clojure/clojure "1.8.0"]])