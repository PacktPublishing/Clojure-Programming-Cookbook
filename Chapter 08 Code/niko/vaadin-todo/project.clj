(defproject com.prajnaic/todo "0.1.0"
  :description "Canonical ToDoMVC app"
  :url "https://github.com/wizardpb/todo"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.prajnainc/functional-vaadin "0.1.1"]
                 [com.vaadin/vaadin-server "7.6.5"]
                 [com.vaadin/vaadin-client-compiled "7.6.5"]
                 [com.vaadin/vaadin-themes "7.6.5"]
                 [org.eclipse.jetty/jetty-server "9.3.8.v20160314"]
                 [org.eclipse.jetty/jetty-servlet "9.3.8.v20160314"]]
  :main todo.ToDo
  :aot [todo.ToDo]
  )
