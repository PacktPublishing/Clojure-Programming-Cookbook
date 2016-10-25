(ns chapter04.files)

(spit "/tmp/hello.txt" "Hello World Nico!" )
;;=> nil

(spit "/tmp/hello.txt" "\nHello World Makoto!" :append true)
;;=> nil

(slurp "/tmp/hello.txt")
;;=> "Hello World Nico!\nHello World Makoto!"

(-> (slurp "/tmp/hello.txt") 
    (clojure.string/split #"\n")
    )
;;=> ["Hello World Nico!" "Hello World Makoto!"]

(with-open [rdr (clojure.java.io/reader "/etc/passwd")]
  (count (line-seq rdr)) 
  )
;;=> 38
(with-open [w (clojure.java.io/writer "/tmp/test.txt")]
  (doseq [x (range 1 11)]
    (.write w (str "Hello no" x " !\n"))))
;;=> nil
(print (slurp "/tmp/test.txt"))
;;=> Hello no1 !
;;=> Hello no2 !
;;=> Hello no3 !
;;=> Hello no4 !
;;=> Hello no5 !
;;=> Hello no6 !
;;=> Hello no7 !
;;=> Hello no8 !
;;=> Hello no9 !
;;=> Hello no10 !
;;=> nil

(let [f "/tmp/foo/test.txt"]
  (clojure.java.io/make-parents f)
  (spit f "Hello!")
  )
;;=> nil
(slurp "/tmp/foo/test.txt")
;;=> "Hello!"

(clojure.java.io/copy
 (clojure.java.io/file "/tmp/foo/test.txt")
  (clojure.java.io/file "/tmp/test.txt")
  )
;;=> nil

(clojure.java.io/delete-file "/tmp/foo")
;;=> IOException Couldn't delete /tmp/foo  
;;=> clojure.java.io/delete-file (io.clj:426)

(clojure.java.io/delete-file "/tmp/foo/test.txt")
;;=> true
(clojure.java.io/delete-file "/tmp/foo")
;;=> true

(doseq [x (.listFiles (clojure.java.io/file "."))]
  (if (.isDirectory x) (print "[D] ")(print "[F] "))
     (println (.getPath x))
     )
;;=> [F] ./CHANGELOG.md
;;=> [D] ./images
;;=> [F] ./.nrepl-port
;;=> [F] ./README.md
;;=> [D] ./test
;;=> [F] ./project.clj
;;=> [F] ./chapter04.md
;;=> [D] ./src
;;=> [D] ./doc
;;=> [D] ./dev-resources
;;=> [F] ./.gitignore
;;=> [D] ./target
;;=> [F] ./.hgignore
;;=> [D] ./resources
;;=> [F] ./LICENSE
;;=> nil

(doseq [x (file-seq (clojure.java.io/file "."))]
  (if (.isDirectory x) (print "[D] ")(print "[F] "))
     (println (.getPath x))
     )
;;=> [D] .
;;=> [F] ./CHANGELOG.md
;;=> [D] ./images
;;=> [F] ./images/rabbitmq.png
;;=> [F] ./images/kafka-topic.png
;;=> [F] ./images/kafka.png
;;=> [F] ./images/zookeeper.png
;;=> [F] ./.nrepl-port
;;=> [F] ./README.md
;;=> [D] ./test
;;=> [D] ./test/chapter04
;;=> [F] ./test/chapter04/core_test.clj
;;=> [F] ./project.clj
;;=> [F] ./chapter04.md
;;=> [D] ./src
;;=> [D] ./src/chapter04
;;=> [F] ./src/chapter04/kafka.clj
;;=> [F] ./src/chapter04/core.clj
;;=> [F] ./src/chapter04/files.clj
;;=> [F] ./src/chapter04/rabbitmq.clj

(macroexpand-1
 '(with-open [rdr (clojure.java.io/reader "/etc/passwd")]
    (count (line-seq rdr)) 
    ))
;;=> (clojure.core/let
;;=>    [rdr (clojure.java.io/reader "/etc/passwd")]
;;=>   (try
;;=>     (clojure.core/with-open [] (count (line-seq rdr)))
;;=>     (finally (. rdr clojure.core/close))))

(clojure.java.io/delete-file "/tmp/foo")
;;=> IOException Couldn't delete /tmp/foo  clojure.java.io/delete-file (io.clj:426)

(clojure.java.io/delete-file "/tmp/foo/test.txt")
;;=> true
(clojure.java.io/delete-file "/tmp/foo")
;;=> true

(slurp "http://clojuredocs.org")
;;=> <!DOCTYPE html>
;;=> <html><head><meta content="width=device-width, maximum-scale=1.0" name="viewport">
;;=> <meta content="yes" name="apple-mobile-web-app-capable">
;;=> ......
;;=> </script></body></html>

(with-open [rdr (clojure.java.io/reader "http://clojuredocs.org")]
  (->> (line-seq rdr)
       (map (fn [s] (inc (count (.getBytes s "UTF-8")))) )
       (reduce +)))
;;=> 40261

(re-pattern (str ".*\\." "clj"))
;;=> #".*\.clj"
(re-matches #".*\.clj" "foo.clj")
;;=> "foo.clj"
(re-matches #".*\.clj" "foo.java")
;;=> nil

(defn get-source-files [path & {:keys [ext] :or {ext "clj"}}]
  (let [f (file-seq (clojure.java.io/file path))
        regex (re-pattern (str ".*\\." ext))]
    (->> f
         (filter #(comp not (.isHidden %)))
         (filter #(.isFile %))
         (filter #(re-matches regex (.getPath %) ))
         )
    )
  )
;;=> #'chapter04.files/get-source-files

(get-source-files ".")
;;=> (#object[java.io.File "0x19c23dcd" "./test/chapter04/core_test.clj"]
;;=>  ...
;;=>  #object[java.io.File "0x4f085b88" "./src/chapter04/rabbitmq.clj"])

(defn count-lines [fname]
  (with-open [rdr (clojure.java.io/reader fname)]
    (count (line-seq rdr)))
  )
;;=> #'chapter04.files/count-lines

(count-lines "./src/chapter04/rabbitmq.clj")
;;=> 32

(defn get-source-lines [p & {:keys [ext] :or {ext "clj"}}]
  (map
   (fn [x]
     (let [p (.getPath x)]
      [p (count-lines p)]
       )) (get-source-files p :ext ext))
  )
;;=> #'chapter04.files/get-source-lines

(get-source-lines ".")
;;=> (["./project.clj" 18] ["./src/chapter04/kafka.clj" 235]
;;=> ...
;;=> ["./src/chapter04/camel.clj" 103] ["./src/chapter04/rabbitmq.clj" 296])


(get-source-lines "./clojure")
;;=> (["./clojure/test/clojure/test_clojure/pprint.clj" 20]
;;=>  ["./clojure/test/clojure/test_clojure/multimethods.clj" 234]
;;=>  ["./clojure/test/clojure/test_clojure/macros.clj" 113]
;;=>  ["./clojure/test/clojure/test_clojure/data_structures.clj" 1303]
;;=>  .....
;;=>  ["./clojure/test/clojure/test_clojure/protocols.clj" 676]
;;=>  ["./clojure/test/clojure/test_clojure/fn.clj" 55]
;;=>  ["./clojure/test/clojure/test_clojure/other_functions.clj" 355]
;;=>  .....

(reduce + (map second (get-source-lines "./clojure")))
;;=> 36584


(get-source-lines "./clojure" :ext "java")
;;=> (["./clojure/test/java/reflector/IBar.java" 20]
;;=>  ["./clojure/test/java/clojure/test/ReflectorTryCatchFixture.java" 22]
;;=>  ["./clojure/test/java/compilation/TestDispatch.java" 15]
;;=>  ["./clojure/src/jvm/clojure/main.java" 39]
;;=>  ;=>  .....
;;=>  ["./clojure/src/jvm/clojure/java/api/Clojure.java" 100]
;;=>  ["./clojure/src/jvm/clojure/asm/Opcodes.java" 358]

 

(reduce + (map second (get-source-lines "./clojure" :ext "java")))
;;=> 58676


(reduce + (map second (get-source-lines "./clojure" :ext "(java|clj)")))
;;=> 95260
