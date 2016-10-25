(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'hello-world.core
   :output-to "out/hello_world.js"
   :output-dir "out"})
