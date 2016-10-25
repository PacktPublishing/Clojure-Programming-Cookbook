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
var seq__9717_9731 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__9718_9732 = null;
var count__9719_9733 = (0);
var i__9720_9734 = (0);
while(true){
if((i__9720_9734 < count__9719_9733)){
var f_9735 = cljs.core._nth.call(null,chunk__9718_9732,i__9720_9734);
cljs.core.println.call(null,"  ",f_9735);

var G__9736 = seq__9717_9731;
var G__9737 = chunk__9718_9732;
var G__9738 = count__9719_9733;
var G__9739 = (i__9720_9734 + (1));
seq__9717_9731 = G__9736;
chunk__9718_9732 = G__9737;
count__9719_9733 = G__9738;
i__9720_9734 = G__9739;
continue;
} else {
var temp__4657__auto___9740 = cljs.core.seq.call(null,seq__9717_9731);
if(temp__4657__auto___9740){
var seq__9717_9741__$1 = temp__4657__auto___9740;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__9717_9741__$1)){
var c__7508__auto___9742 = cljs.core.chunk_first.call(null,seq__9717_9741__$1);
var G__9743 = cljs.core.chunk_rest.call(null,seq__9717_9741__$1);
var G__9744 = c__7508__auto___9742;
var G__9745 = cljs.core.count.call(null,c__7508__auto___9742);
var G__9746 = (0);
seq__9717_9731 = G__9743;
chunk__9718_9732 = G__9744;
count__9719_9733 = G__9745;
i__9720_9734 = G__9746;
continue;
} else {
var f_9747 = cljs.core.first.call(null,seq__9717_9741__$1);
cljs.core.println.call(null,"  ",f_9747);

var G__9748 = cljs.core.next.call(null,seq__9717_9741__$1);
var G__9749 = null;
var G__9750 = (0);
var G__9751 = (0);
seq__9717_9731 = G__9748;
chunk__9718_9732 = G__9749;
count__9719_9733 = G__9750;
i__9720_9734 = G__9751;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_9752 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__6705__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__6705__auto__)){
return or__6705__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_9752);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_9752)))?cljs.core.second.call(null,arglists_9752):arglists_9752));
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
var seq__9721 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__9722 = null;
var count__9723 = (0);
var i__9724 = (0);
while(true){
if((i__9724 < count__9723)){
var vec__9725 = cljs.core._nth.call(null,chunk__9722,i__9724);
var name = cljs.core.nth.call(null,vec__9725,(0),null);
var map__9726 = cljs.core.nth.call(null,vec__9725,(1),null);
var map__9726__$1 = ((((!((map__9726 == null)))?((((map__9726.cljs$lang$protocol_mask$partition0$ & (64))) || (map__9726.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__9726):map__9726);
var doc = cljs.core.get.call(null,map__9726__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__9726__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__9753 = seq__9721;
var G__9754 = chunk__9722;
var G__9755 = count__9723;
var G__9756 = (i__9724 + (1));
seq__9721 = G__9753;
chunk__9722 = G__9754;
count__9723 = G__9755;
i__9724 = G__9756;
continue;
} else {
var temp__4657__auto__ = cljs.core.seq.call(null,seq__9721);
if(temp__4657__auto__){
var seq__9721__$1 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__9721__$1)){
var c__7508__auto__ = cljs.core.chunk_first.call(null,seq__9721__$1);
var G__9757 = cljs.core.chunk_rest.call(null,seq__9721__$1);
var G__9758 = c__7508__auto__;
var G__9759 = cljs.core.count.call(null,c__7508__auto__);
var G__9760 = (0);
seq__9721 = G__9757;
chunk__9722 = G__9758;
count__9723 = G__9759;
i__9724 = G__9760;
continue;
} else {
var vec__9728 = cljs.core.first.call(null,seq__9721__$1);
var name = cljs.core.nth.call(null,vec__9728,(0),null);
var map__9729 = cljs.core.nth.call(null,vec__9728,(1),null);
var map__9729__$1 = ((((!((map__9729 == null)))?((((map__9729.cljs$lang$protocol_mask$partition0$ & (64))) || (map__9729.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__9729):map__9729);
var doc = cljs.core.get.call(null,map__9729__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__9729__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__9761 = cljs.core.next.call(null,seq__9721__$1);
var G__9762 = null;
var G__9763 = (0);
var G__9764 = (0);
seq__9721 = G__9761;
chunk__9722 = G__9762;
count__9723 = G__9763;
i__9724 = G__9764;
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