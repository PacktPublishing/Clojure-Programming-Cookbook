(defproject blazarland "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :java-agents
    [[co.paralleluniverse/quasar-core "0.7.5"]]
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
            :profiles {
    :dev {
      :resource-paths ["resources" "profiles/dev/resources"]
      :source-paths ["src" "profiles/dev/src"]}}
  :dependencies [[org.clojure/clojure "1.8.0"]
  [blazar "0.1.1"]
  [co.paralleluniverse/pulsar "0.7.5"]
  ])
