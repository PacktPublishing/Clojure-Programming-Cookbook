// Comment to get more information during initialization
//logLevel := Level.Debug
logLevel := Level.Info

//lazy val root = project.in(file(".")).dependsOn(clojurePlugin)

//lazy val clojurePlugin = file("../../sbt-clojure")

addSbtPlugin("com.unhandledexpression" % "sbt-clojure" % "0.1")

