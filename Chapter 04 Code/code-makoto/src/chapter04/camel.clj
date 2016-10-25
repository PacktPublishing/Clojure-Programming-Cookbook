(ns chapter04.camel
  (:import [org.apache.camel.impl DefaultCamelContext]
           [org.apache.camel.builder RouteBuilder]
           [org.apache.camel.main Main]
           [org.apache.camel Exchange]
           [org.apache.camel Processor]           
           )
  (:gen-class))
;;=> nil

(def ctx
  (let [context (DefaultCamelContext.)]
    (.addRoutes context (proxy [RouteBuilder] []
                          (configure [] (.. this
                                            (from "file:/tmp/in?noop=false")
                                            (to "file:/tmp/out")))))
    context
    ))
#'chapter04.camel/ctx

(.start ctx)
;;=> nil

(.stop ctx)
;;=> nil

(defn file-to-file []
  (let [context (DefaultCamelContext.)]
    (add-routes context
           (from "file:/tmp/in?noop=false")
           (to "file:/tmp/out")
           )
    context
    ))
;;=> #'chapter04.camel/file-to-file

(def ctx (file-to-file))
;;=> #'chapter04.camel/ctx
(.start ctx)
;;=> nil
(.stop ctx)
;;=> nil

(defn get-payload-as-string[ex]
  (-> ex .getIn (.getBody (class ""))))
;;=> #'chapter04.camel/get-payload-as-string

(def simple-processor 
  (proxy [Processor][]
    (process [exchange]
      (let [payload
            (get-payload-as-string exchange)            
            ]        
        (println payload)
        (-> exchange .getOut (.setBody (str "Hello " payload)))))))
;;=> #'chapter04.camel/simple-processor

(defn file-to-file-with-processor []
  (let [context (DefaultCamelContext.)]
    (add-routes context
           (from "file:/tmp/in?noop=false")
           (process simple-processor)
           (to "file:/tmp/out"))
    context))
;;=> #'chapter04.camel/processor

(def ctx (file-to-file-with-processor))
#'chapter04.camel/ctx

(.start ctx)
;;=> nil





(defn create-builder [builder]
  (proxy [RouteBuilder] [] (configure [] (builder this))))
;;=> #'chapter04.camel/create-builder

(defmacro add-routes [ctx & body]
  `(.addRoutes ~ctx (create-builder (fn [~'this] (.. ~'this ~@body)))))
;;=>#'chapter04.camel/add-routes


#_(

(def ctx
  (let [context (DefaultCamelContext.)]
    (.addRoutes context (proxy [RouteBuilder] []
                          (configure [] (.. this
                                            (from "file:/tmp/in?noop=false")
                                            (to "file:/tmp/out")))))
    context
    ))
(.start ctx)
;;=> nil
(.stop ctx)
(.stop x)
)

 #_(
(let [context (DefaultCamelContext.)]
  (.addRoutes context (proxy [RouteBuilder] []
                        (configure [] (.. this
                                          (from "file:/tmp/from?noop=false")
                                          (to "file:/tmp?fileName=out.txt")))))
  (.start context))

(let [context (DefaultCamelContext.)
      builder
      (proxy [RouteBuilder] []
                        (configure [] (.. this
                                          (from "file:/tmp/from?noop=false")
                                          (to "file:/tmp/to"))))
      
      ])
  
    
  
  )


(defn get-headers[ex]
  (-> ex .getIn .getHeaders))

(defn get-payload-as-string[ex]
  (-> ex .getIn (.getBody (class ""))))
;;=> #'chapter04.camel/get-payload-as-string

   
(def processor 
  (proxy [Processor][]
    (process [exchange]
      (let [payload
            (get-payload-as-string exchange)
            headers (get-headers exchange)]
        (println payload)
        (-> exchange .getOut (.setBody (str "Hello " payload)))))))


(defn file-to-file-with-processor [in out]
  (let [context (DefaultCamelContext.)]
    (add-routes context
                (from (str "file:" in "?noop=false"))
                (process processor)
                (to (str "file:" out)))
    context))
;;=> #'chapter04.camel/processor


(defn http-to-file-with-processor []
  (let [context (DefaultCamelContext.)]
    (add-routes context
                (from "jetty:http://0.0.0.0:8080/myapp?matchOnUriPrefix=true")
                (to "file:/tmp/out"))
    context))

(def ctx (http-to-file-with-processor))
(.start ctx)
#_(
(def ctx (file-to-file-with-processor))
   (.start ctx)
(.stop ctx)

      )   


<from uri="jetty:http://0.0.0.0:8080/myapp?matchOnUriPrefix=true"/>
  <to uri="jetty:http://realserverhostname:8090/myapp?bridgeEndpoint=true&amp;throwExceptionOnFailure=false"/>
