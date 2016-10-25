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
var seq__11912_11926 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__11913_11927 = null;
var count__11914_11928 = (0);
var i__11915_11929 = (0);
while(true){
if((i__11915_11929 < count__11914_11928)){
var f_11930 = cljs.core._nth.call(null,chunk__11913_11927,i__11915_11929);
cljs.core.println.call(null,"  ",f_11930);

var G__11931 = seq__11912_11926;
var G__11932 = chunk__11913_11927;
var G__11933 = count__11914_11928;
var G__11934 = (i__11915_11929 + (1));
seq__11912_11926 = G__11931;
chunk__11913_11927 = G__11932;
count__11914_11928 = G__11933;
i__11915_11929 = G__11934;
continue;
} else {
var temp__4657__auto___11935 = cljs.core.seq.call(null,seq__11912_11926);
if(temp__4657__auto___11935){
var seq__11912_11936__$1 = temp__4657__auto___11935;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__11912_11936__$1)){
var c__7605__auto___11937 = cljs.core.chunk_first.call(null,seq__11912_11936__$1);
var G__11938 = cljs.core.chunk_rest.call(null,seq__11912_11936__$1);
var G__11939 = c__7605__auto___11937;
var G__11940 = cljs.core.count.call(null,c__7605__auto___11937);
var G__11941 = (0);
seq__11912_11926 = G__11938;
chunk__11913_11927 = G__11939;
count__11914_11928 = G__11940;
i__11915_11929 = G__11941;
continue;
} else {
var f_11942 = cljs.core.first.call(null,seq__11912_11936__$1);
cljs.core.println.call(null,"  ",f_11942);

var G__11943 = cljs.core.next.call(null,seq__11912_11936__$1);
var G__11944 = null;
var G__11945 = (0);
var G__11946 = (0);
seq__11912_11926 = G__11943;
chunk__11913_11927 = G__11944;
count__11914_11928 = G__11945;
i__11915_11929 = G__11946;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_11947 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__6802__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__6802__auto__)){
return or__6802__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_11947);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_11947)))?cljs.core.second.call(null,arglists_11947):arglists_11947));
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
var seq__11916 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__11917 = null;
var count__11918 = (0);
var i__11919 = (0);
while(true){
if((i__11919 < count__11918)){
var vec__11920 = cljs.core._nth.call(null,chunk__11917,i__11919);
var name = cljs.core.nth.call(null,vec__11920,(0),null);
var map__11921 = cljs.core.nth.call(null,vec__11920,(1),null);
var map__11921__$1 = ((((!((map__11921 == null)))?((((map__11921.cljs$lang$protocol_mask$partition0$ & (64))) || (map__11921.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__11921):map__11921);
var doc = cljs.core.get.call(null,map__11921__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__11921__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__11948 = seq__11916;
var G__11949 = chunk__11917;
var G__11950 = count__11918;
var G__11951 = (i__11919 + (1));
seq__11916 = G__11948;
chunk__11917 = G__11949;
count__11918 = G__11950;
i__11919 = G__11951;
continue;
} else {
var temp__4657__auto__ = cljs.core.seq.call(null,seq__11916);
if(temp__4657__auto__){
var seq__11916__$1 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__11916__$1)){
var c__7605__auto__ = cljs.core.chunk_first.call(null,seq__11916__$1);
var G__11952 = cljs.core.chunk_rest.call(null,seq__11916__$1);
var G__11953 = c__7605__auto__;
var G__11954 = cljs.core.count.call(null,c__7605__auto__);
var G__11955 = (0);
seq__11916 = G__11952;
chunk__11917 = G__11953;
count__11918 = G__11954;
i__11919 = G__11955;
continue;
} else {
var vec__11923 = cljs.core.first.call(null,seq__11916__$1);
var name = cljs.core.nth.call(null,vec__11923,(0),null);
var map__11924 = cljs.core.nth.call(null,vec__11923,(1),null);
var map__11924__$1 = ((((!((map__11924 == null)))?((((map__11924.cljs$lang$protocol_mask$partition0$ & (64))) || (map__11924.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__11924):map__11924);
var doc = cljs.core.get.call(null,map__11924__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__11924__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__11956 = cljs.core.next.call(null,seq__11916__$1);
var G__11957 = null;
var G__11958 = (0);
var G__11959 = (0);
seq__11916 = G__11956;
chunk__11917 = G__11957;
count__11918 = G__11958;
i__11919 = G__11959;
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
