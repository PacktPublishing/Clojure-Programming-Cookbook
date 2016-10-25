// Compiled by ClojureScript 1.7.228 {}
goog.provide('example.crossover.shared');
goog.require('cljs.core');
example.crossover.shared.fib_seq = (function example$crossover$shared$rfib(a,b){
return (new cljs.core.LazySeq(null,(function (){
return cljs.core.cons.call(null,a,example$crossover$shared$rfib.call(null,b,(a + b)));
}),null,null));
}).call(null,(0),(1));

//# sourceMappingURL=shared.js.map