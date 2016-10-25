(defproject quasarland "0.1.0-SNAPSHOT"
  :java-agents [[co.paralleluniverse/quasar-core "0.7.5"]]
    :jvm-opts [
    		 ;"-server"
             ;"-Dclojure.compiler.disable-locals-clearing=true"
             ;; ForkJoin wants these:
             ;"-XX:-UseBiasedLocking"
             ;"-XX:+UseCondCardMark"
             ]
  :dependencies [
  	[org.clojure/clojure "1.8.0"]
  	[co.paralleluniverse/quasar-core "0.7.5"]
	  [co.paralleluniverse/pulsar "0.7.5"]
  ])
