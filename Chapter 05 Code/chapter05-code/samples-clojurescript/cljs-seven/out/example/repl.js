// Compiled by ClojureScript 1.7.228 {}
goog.provide('example.repl');
goog.require('cljs.core');
goog.require('clojure.browser.repl');
example.repl.connect = (function example$repl$connect(){
return clojure.browser.repl.connect.call(null,"http://localhost:9000/repl");
});
goog.exportSymbol('example.repl.connect', example.repl.connect);

//# sourceMappingURL=repl.js.map