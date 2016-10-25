// Compiled by ClojureScript 1.7.228 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
cljs.repl.print_doc = (function cljs$repl$print_doc(m){
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,[cljs.core.str((function (){var temp__4657__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__4657__auto__)){
var ns = temp__4657__auto__;
return [cljs.core.str(ns),cljs.core.str("/")].join('');
} else {
return null;
}
})()),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Protocol");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m))){
var seq__9874_9888 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__9875_9889 = null;
var count__9876_9890 = (0);
var i__9877_9891 = (0);
while(true){
if((i__9877_9891 < count__9876_9890)){
var f_9892 = cljs.core._nth.call(null,chunk__9875_9889,i__9877_9891);
cljs.core.println.call(null,"  ",f_9892);

var G__9893 = seq__9874_9888;
var G__9894 = chunk__9875_9889;
var G__9895 = count__9876_9890;
var G__9896 = (i__9877_9891 + (1));
seq__9874_9888 = G__9893;
chunk__9875_9889 = G__9894;
count__9876_9890 = G__9895;
i__9877_9891 = G__9896;
continue;
} else {
var temp__4657__auto___9897 = cljs.core.seq.call(null,seq__9874_9888);
if(temp__4657__auto___9897){
var seq__9874_9898__$1 = temp__4657__auto___9897;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__9874_9898__$1)){
var c__7605__auto___9899 = cljs.core.chunk_first.call(null,seq__9874_9898__$1);
var G__9900 = cljs.core.chunk_rest.call(null,seq__9874_9898__$1);
var G__9901 = c__7605__auto___9899;
var G__9902 = cljs.core.count.call(null,c__7605__auto___9899);
var G__9903 = (0);
seq__9874_9888 = G__9900;
chunk__9875_9889 = G__9901;
count__9876_9890 = G__9902;
i__9877_9891 = G__9903;
continue;
} else {
var f_9904 = cljs.core.first.call(null,seq__9874_9898__$1);
cljs.core.println.call(null,"  ",f_9904);

var G__9905 = cljs.core.next.call(null,seq__9874_9898__$1);
var G__9906 = null;
var G__9907 = (0);
var G__9908 = (0);
seq__9874_9888 = G__9905;
chunk__9875_9889 = G__9906;
count__9876_9890 = G__9907;
i__9877_9891 = G__9908;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_9909 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__6802__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__6802__auto__)){
return or__6802__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_9909);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_9909)))?cljs.core.second.call(null,arglists_9909):arglists_9909));
}
} else {
}
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"special-form","special-form",-1326536374).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Special Form");

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.contains_QMARK_.call(null,m,new cljs.core.Keyword(null,"url","url",276297046))){
if(cljs.core.truth_(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/"),cljs.core.str(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))].join(''));
} else {
return null;
}
} else {
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/special_forms#"),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Macro");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"REPL Special Function");
} else {
}

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
var seq__9878 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__9879 = null;
var count__9880 = (0);
var i__9881 = (0);
while(true){
if((i__9881 < count__9880)){
var vec__9882 = cljs.core._nth.call(null,chunk__9879,i__9881);
var name = cljs.core.nth.call(null,vec__9882,(0),null);
var map__9883 = cljs.core.nth.call(null,vec__9882,(1),null);
var map__9883__$1 = ((((!((map__9883 == null)))?((((map__9883.cljs$lang$protocol_mask$partition0$ & (64))) || (map__9883.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__9883):map__9883);
var doc = cljs.core.get.call(null,map__9883__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__9883__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__9910 = seq__9878;
var G__9911 = chunk__9879;
var G__9912 = count__9880;
var G__9913 = (i__9881 + (1));
seq__9878 = G__9910;
chunk__9879 = G__9911;
count__9880 = G__9912;
i__9881 = G__9913;
continue;
} else {
var temp__4657__auto__ = cljs.core.seq.call(null,seq__9878);
if(temp__4657__auto__){
var seq__9878__$1 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__9878__$1)){
var c__7605__auto__ = cljs.core.chunk_first.call(null,seq__9878__$1);
var G__9914 = cljs.core.chunk_rest.call(null,seq__9878__$1);
var G__9915 = c__7605__auto__;
var G__9916 = cljs.core.count.call(null,c__7605__auto__);
var G__9917 = (0);
seq__9878 = G__9914;
chunk__9879 = G__9915;
count__9880 = G__9916;
i__9881 = G__9917;
continue;
} else {
var vec__9885 = cljs.core.first.call(null,seq__9878__$1);
var name = cljs.core.nth.call(null,vec__9885,(0),null);
var map__9886 = cljs.core.nth.call(null,vec__9885,(1),null);
var map__9886__$1 = ((((!((map__9886 == null)))?((((map__9886.cljs$lang$protocol_mask$partition0$ & (64))) || (map__9886.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__9886):map__9886);
var doc = cljs.core.get.call(null,map__9886__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__9886__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__9918 = cljs.core.next.call(null,seq__9878__$1);
var G__9919 = null;
var G__9920 = (0);
var G__9921 = (0);
seq__9878 = G__9918;
chunk__9879 = G__9919;
count__9880 = G__9920;
i__9881 = G__9921;
continue;
}
} else {
return null;
}
}
break;
}
} else {
return null;
}
}
});

//# sourceMappingURL=repl.js.map