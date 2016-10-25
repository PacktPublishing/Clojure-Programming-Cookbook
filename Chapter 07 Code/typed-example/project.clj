(defproject typed-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.typed "0.3.21"]]
  :repositories {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
    :injections [(require 'clojure.core.typed)
               (clojure.core.typed/install)]
  )
  
