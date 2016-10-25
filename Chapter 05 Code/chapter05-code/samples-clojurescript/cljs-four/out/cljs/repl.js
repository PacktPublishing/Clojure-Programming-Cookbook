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
var seq__8015_8029 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__8016_8030 = null;
var count__8017_8031 = (0);
var i__8018_8032 = (0);
while(true){
if((i__8018_8032 < count__8017_8031)){
var f_8033 = cljs.core._nth.call(null,chunk__8016_8030,i__8018_8032);
cljs.core.println.call(null,"  ",f_8033);

var G__8034 = seq__8015_8029;
var G__8035 = chunk__8016_8030;
var G__8036 = count__8017_8031;
var G__8037 = (i__8018_8032 + (1));
seq__8015_8029 = G__8034;
chunk__8016_8030 = G__8035;
count__8017_8031 = G__8036;
i__8018_8032 = G__8037;
continue;
} else {
var temp__4657__auto___8038 = cljs.core.seq.call(null,seq__8015_8029);
if(temp__4657__auto___8038){
var seq__8015_8039__$1 = temp__4657__auto___8038;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__8015_8039__$1)){
var c__6959__auto___8040 = cljs.core.chunk_first.call(null,seq__8015_8039__$1);
var G__8041 = cljs.core.chunk_rest.call(null,seq__8015_8039__$1);
var G__8042 = c__6959__auto___8040;
var G__8043 = cljs.core.count.call(null,c__6959__auto___8040);
var G__8044 = (0);
seq__8015_8029 = G__8041;
chunk__8016_8030 = G__8042;
count__8017_8031 = G__8043;
i__8018_8032 = G__8044;
continue;
} else {
var f_8045 = cljs.core.first.call(null,seq__8015_8039__$1);
cljs.core.println.call(null,"  ",f_8045);

var G__8046 = cljs.core.next.call(null,seq__8015_8039__$1);
var G__8047 = null;
var G__8048 = (0);
var G__8049 = (0);
seq__8015_8029 = G__8046;
chunk__8016_8030 = G__8047;
count__8017_8031 = G__8048;
i__8018_8032 = G__8049;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_8050 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__6156__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__6156__auto__)){
return or__6156__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_8050);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_8050)))?cljs.core.second.call(null,arglists_8050):arglists_8050));
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
var seq__8019 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__8020 = null;
var count__8021 = (0);
var i__8022 = (0);
while(true){
if((i__8022 < count__8021)){
var vec__8023 = cljs.core._nth.call(null,chunk__8020,i__8022);
var name = cljs.core.nth.call(null,vec__8023,(0),null);
var map__8024 = cljs.core.nth.call(null,vec__8023,(1),null);
var map__8024__$1 = ((((!((map__8024 == null)))?((((map__8024.cljs$lang$protocol_mask$partition0$ & (64))) || (map__8024.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__8024):map__8024);
var doc = cljs.core.get.call(null,map__8024__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__8024__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__8051 = seq__8019;
var G__8052 = chunk__8020;
var G__8053 = count__8021;
var G__8054 = (i__8022 + (1));
seq__8019 = G__8051;
chunk__8020 = G__8052;
count__8021 = G__8053;
i__8022 = G__8054;
continue;
} else {
var temp__4657__auto__ = cljs.core.seq.call(null,seq__8019);
if(temp__4657__auto__){
var seq__8019__$1 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__8019__$1)){
var c__6959__auto__ = cljs.core.chunk_first.call(null,seq__8019__$1);
var G__8055 = cljs.core.chunk_rest.call(null,seq__8019__$1);
var G__8056 = c__6959__auto__;
var G__8057 = cljs.core.count.call(null,c__6959__auto__);
var G__8058 = (0);
seq__8019 = G__8055;
chunk__8020 = G__8056;
count__8021 = G__8057;
i__8022 = G__8058;
continue;
} else {
var vec__8026 = cljs.core.first.call(null,seq__8019__$1);
var name = cljs.core.nth.call(null,vec__8026,(0),null);
var map__8027 = cljs.core.nth.call(null,vec__8026,(1),null);
var map__8027__$1 = ((((!((map__8027 == null)))?((((map__8027.cljs$lang$protocol_mask$partition0$ & (64))) || (map__8027.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__8027):map__8027);
var doc = cljs.core.get.call(null,map__8027__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__8027__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__8059 = cljs.core.next.call(null,seq__8019__$1);
var G__8060 = null;
var G__8061 = (0);
var G__8062 = (0);
seq__8019 = G__8059;
chunk__8020 = G__8060;
count__8021 = G__8061;
i__8022 = G__8062;
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