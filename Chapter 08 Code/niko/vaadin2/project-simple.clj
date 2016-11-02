(defproject vaadin2 "0.1.0-SNAPSHOT"
  :dependencies [
     [org.clojure/clojure "1.8.0"]

		 [com.vaadin/vaadin-server "7.6.6"]
     [com.vaadin/vaadin-client-compiled "7.6.6"]
     [com.vaadin/vaadin-themes "7.6.6"]

     [javax.servlet/javax.servlet-api "3.1.0"]]

  ; :resource-paths ["example00"]
  ; :resource-paths ["example01-calendar"]
  ; :resource-paths ["example01-button"]
  :resource-paths ["example02"]
  :plugins [[lein-servlet "0.4.1"]]
  :servlet {:deps [[lein-servlet/adapter-jetty8 "0.4.1"]]
            :config {:engine :jetty :host "localhost"}
            :webapps {"/"
                       {:public "resources" :web-xml "src/main/webapp/WEB-INF/web.xml"}}})
