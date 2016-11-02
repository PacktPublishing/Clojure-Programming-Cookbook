(defproject vaadin2 "0.1.0-SNAPSHOT"
  :dependencies [
     [org.clojure/clojure "1.8.0"]
		 [com.vaadin/vaadin-server "7.6.6"]
     [com.vaadin/vaadin-client-compiled "7.6.6"]
     [com.vaadin/vaadin-themes "7.6.6"]

     [com.vaadin/vaadin-push "7.6.6"] ; for example 03
     [im.chit/hara.io.scheduler "2.3.6"] ; for example 05

     [morse "0.1.1"] ; for example 06

     [javax.servlet/javax.servlet-api "3.1.0"]]

  :resource-paths ["example03"]
  ; :resource-paths ["example04"]
  ; :resource-paths ["example05"]
  ; :resource-paths ["example06"]

  :aot [vaadin2.vaadin2ui]
  :plugins [[lein-servlet "0.4.1"]]
  :servlet {:deps [[lein-servlet/adapter-jetty8 "0.4.1"]]
            :config {:engine :jetty
                     :host "0.0.0.0"
                     :dev true
                     :port 3000}
            :webapps {"/"
                       {
                        :servlets {
                          "/*" [com.vaadin.server.VaadinServlet {:UI "vaadin2.vaadin2ui"}]
                        }
                        ; :web-xml "src/main/webapp/WEB-INF/web.xml"
                        :public "resources"}}})
