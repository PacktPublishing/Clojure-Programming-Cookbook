// Compiled by ClojureScript 1.7.228 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
cljs.repl.print_doc = (function cljs$repl$print_doc(m){
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,[cljs.core.str((function (){var temp__4425__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__4425__auto__)){
var ns = temp__4425__auto__;
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
var seq__7651_7665 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__7652_7666 = null;
var count__7653_7667 = (0);
var i__7654_7668 = (0);
while(true){
if((i__7654_7668 < count__7653_7667)){
var f_7669 = cljs.core._nth.call(null,chunk__7652_7666,i__7654_7668);
cljs.core.println.call(null,"  ",f_7669);

var G__7670 = seq__7651_7665;
var G__7671 = chunk__7652_7666;
var G__7672 = count__7653_7667;
var G__7673 = (i__7654_7668 + (1));
seq__7651_7665 = G__7670;
chunk__7652_7666 = G__7671;
count__7653_7667 = G__7672;
i__7654_7668 = G__7673;
continue;
} else {
var temp__4425__auto___7674 = cljs.core.seq.call(null,seq__7651_7665);
if(temp__4425__auto___7674){
var seq__7651_7675__$1 = temp__4425__auto___7674;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__7651_7675__$1)){
var c__6597__auto___7676 = cljs.core.chunk_first.call(null,seq__7651_7675__$1);
var G__7677 = cljs.core.chunk_rest.call(null,seq__7651_7675__$1);
var G__7678 = c__6597__auto___7676;
var G__7679 = cljs.core.count.call(null,c__6597__auto___7676);
var G__7680 = (0);
seq__7651_7665 = G__7677;
chunk__7652_7666 = G__7678;
count__7653_7667 = G__7679;
i__7654_7668 = G__7680;
continue;
} else {
var f_7681 = cljs.core.first.call(null,seq__7651_7675__$1);
cljs.core.println.call(null,"  ",f_7681);

var G__7682 = cljs.core.next.call(null,seq__7651_7675__$1);
var G__7683 = null;
var G__7684 = (0);
var G__7685 = (0);
seq__7651_7665 = G__7682;
chunk__7652_7666 = G__7683;
count__7653_7667 = G__7684;
i__7654_7668 = G__7685;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_7686 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__5794__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__5794__auto__)){
return or__5794__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_7686);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_7686)))?cljs.core.second.call(null,arglists_7686):arglists_7686));
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
var seq__7655 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__7656 = null;
var count__7657 = (0);
var i__7658 = (0);
while(true){
if((i__7658 < count__7657)){
var vec__7659 = cljs.core._nth.call(null,chunk__7656,i__7658);
var name = cljs.core.nth.call(null,vec__7659,(0),null);
var map__7660 = cljs.core.nth.call(null,vec__7659,(1),null);
var map__7660__$1 = ((((!((map__7660 == null)))?((((map__7660.cljs$lang$protocol_mask$partition0$ & (64))) || (map__7660.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__7660):map__7660);
var doc = cljs.core.get.call(null,map__7660__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__7660__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__7687 = seq__7655;
var G__7688 = chunk__7656;
var G__7689 = count__7657;
var G__7690 = (i__7658 + (1));
seq__7655 = G__7687;
chunk__7656 = G__7688;
count__7657 = G__7689;
i__7658 = G__7690;
continue;
} else {
var temp__4425__auto__ = cljs.core.seq.call(null,seq__7655);
if(temp__4425__auto__){
var seq__7655__$1 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__7655__$1)){
var c__6597__auto__ = cljs.core.chunk_first.call(null,seq__7655__$1);
var G__7691 = cljs.core.chunk_rest.call(null,seq__7655__$1);
var G__7692 = c__6597__auto__;
var G__7693 = cljs.core.count.call(null,c__6597__auto__);
var G__7694 = (0);
seq__7655 = G__7691;
chunk__7656 = G__7692;
count__7657 = G__7693;
i__7658 = G__7694;
continue;
} else {
var vec__7662 = cljs.core.first.call(null,seq__7655__$1);
var name = cljs.core.nth.call(null,vec__7662,(0),null);
var map__7663 = cljs.core.nth.call(null,vec__7662,(1),null);
var map__7663__$1 = ((((!((map__7663 == null)))?((((map__7663.cljs$lang$protocol_mask$partition0$ & (64))) || (map__7663.cljs$core$ISeq$))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__7663):map__7663);
var doc = cljs.core.get.call(null,map__7663__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists = cljs.core.get.call(null,map__7663__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__7695 = cljs.core.next.call(null,seq__7655__$1);
var G__7696 = null;
var G__7697 = (0);
var G__7698 = (0);
seq__7655 = G__7695;
chunk__7656 = G__7696;
count__7657 = G__7697;
i__7658 = G__7698;
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