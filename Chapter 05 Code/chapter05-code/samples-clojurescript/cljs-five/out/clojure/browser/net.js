// Compiled by ClojureScript 1.7.228 {}
goog.provide('clojure.browser.net');
goog.require('cljs.core');
goog.require('goog.Uri');
goog.require('goog.net.WebSocket');
goog.require('goog.net.EventType');
goog.require('goog.json');
goog.require('goog.net.xpc.CfgFields');
goog.require('goog.net.XhrIo');
goog.require('clojure.browser.event');
goog.require('goog.net.xpc.CrossPageChannel');
clojure.browser.net._STAR_timeout_STAR_ = (10000);
clojure.browser.net.event_types = cljs.core.into.call(null,cljs.core.PersistentArrayMap.EMPTY,cljs.core.map.call(null,(function (p__9998){
var vec__9999 = p__9998;
var k = cljs.core.nth.call(null,vec__9999,(0),null);
var v = cljs.core.nth.call(null,vec__9999,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.keyword.call(null,k.toLowerCase()),v], null);
}),cljs.core.merge.call(null,cljs.core.js__GT_clj.call(null,goog.net.EventType))));

/**
 * @interface
 */
clojure.browser.net.IConnection = function(){};

clojure.browser.net.connect = (function clojure$browser$net$connect(var_args){
var args10000 = [];
var len__7860__auto___10006 = arguments.length;
var i__7861__auto___10007 = (0);
while(true){
if((i__7861__auto___10007 < len__7860__auto___10006)){
args10000.push((arguments[i__7861__auto___10007]));

var G__10008 = (i__7861__auto___10007 + (1));
i__7861__auto___10007 = G__10008;
continue;
} else {
}
break;
}

var G__10002 = args10000.length;
switch (G__10002) {
case 1:
return clojure.browser.net.connect.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return clojure.browser.net.connect.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
case 3:
return clojure.browser.net.connect.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
case 4:
return clojure.browser.net.connect.cljs$core$IFn$_invoke$arity$4((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args10000.length)].join('')));

}
});

clojure.browser.net.connect.cljs$core$IFn$_invoke$arity$1 = (function (this$){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IConnection$connect$arity$1 == null)))){
return this$.clojure$browser$net$IConnection$connect$arity$1(this$);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.connect[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$);
} else {
var m__7458__auto____$1 = (clojure.browser.net.connect["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$);
} else {
throw cljs.core.missing_protocol.call(null,"IConnection.connect",this$);
}
}
}
});

clojure.browser.net.connect.cljs$core$IFn$_invoke$arity$2 = (function (this$,opt1){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IConnection$connect$arity$2 == null)))){
return this$.clojure$browser$net$IConnection$connect$arity$2(this$,opt1);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.connect[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$,opt1);
} else {
var m__7458__auto____$1 = (clojure.browser.net.connect["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$,opt1);
} else {
throw cljs.core.missing_protocol.call(null,"IConnection.connect",this$);
}
}
}
});

clojure.browser.net.connect.cljs$core$IFn$_invoke$arity$3 = (function (this$,opt1,opt2){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IConnection$connect$arity$3 == null)))){
return this$.clojure$browser$net$IConnection$connect$arity$3(this$,opt1,opt2);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.connect[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$,opt1,opt2);
} else {
var m__7458__auto____$1 = (clojure.browser.net.connect["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$,opt1,opt2);
} else {
throw cljs.core.missing_protocol.call(null,"IConnection.connect",this$);
}
}
}
});

clojure.browser.net.connect.cljs$core$IFn$_invoke$arity$4 = (function (this$,opt1,opt2,opt3){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IConnection$connect$arity$4 == null)))){
return this$.clojure$browser$net$IConnection$connect$arity$4(this$,opt1,opt2,opt3);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.connect[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$,opt1,opt2,opt3);
} else {
var m__7458__auto____$1 = (clojure.browser.net.connect["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$,opt1,opt2,opt3);
} else {
throw cljs.core.missing_protocol.call(null,"IConnection.connect",this$);
}
}
}
});

clojure.browser.net.connect.cljs$lang$maxFixedArity = 4;

clojure.browser.net.transmit = (function clojure$browser$net$transmit(var_args){
var args10003 = [];
var len__7860__auto___10010 = arguments.length;
var i__7861__auto___10011 = (0);
while(true){
if((i__7861__auto___10011 < len__7860__auto___10010)){
args10003.push((arguments[i__7861__auto___10011]));

var G__10012 = (i__7861__auto___10011 + (1));
i__7861__auto___10011 = G__10012;
continue;
} else {
}
break;
}

var G__10005 = args10003.length;
switch (G__10005) {
case 2:
return clojure.browser.net.transmit.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
case 3:
return clojure.browser.net.transmit.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
case 4:
return clojure.browser.net.transmit.cljs$core$IFn$_invoke$arity$4((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]));

break;
case 5:
return clojure.browser.net.transmit.cljs$core$IFn$_invoke$arity$5((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]),(arguments[(4)]));

break;
case 6:
return clojure.browser.net.transmit.cljs$core$IFn$_invoke$arity$6((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]),(arguments[(4)]),(arguments[(5)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args10003.length)].join('')));

}
});

clojure.browser.net.transmit.cljs$core$IFn$_invoke$arity$2 = (function (this$,opt){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IConnection$transmit$arity$2 == null)))){
return this$.clojure$browser$net$IConnection$transmit$arity$2(this$,opt);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.transmit[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$,opt);
} else {
var m__7458__auto____$1 = (clojure.browser.net.transmit["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$,opt);
} else {
throw cljs.core.missing_protocol.call(null,"IConnection.transmit",this$);
}
}
}
});

clojure.browser.net.transmit.cljs$core$IFn$_invoke$arity$3 = (function (this$,opt,opt2){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IConnection$transmit$arity$3 == null)))){
return this$.clojure$browser$net$IConnection$transmit$arity$3(this$,opt,opt2);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.transmit[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$,opt,opt2);
} else {
var m__7458__auto____$1 = (clojure.browser.net.transmit["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$,opt,opt2);
} else {
throw cljs.core.missing_protocol.call(null,"IConnection.transmit",this$);
}
}
}
});

clojure.browser.net.transmit.cljs$core$IFn$_invoke$arity$4 = (function (this$,opt,opt2,opt3){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IConnection$transmit$arity$4 == null)))){
return this$.clojure$browser$net$IConnection$transmit$arity$4(this$,opt,opt2,opt3);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.transmit[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$,opt,opt2,opt3);
} else {
var m__7458__auto____$1 = (clojure.browser.net.transmit["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$,opt,opt2,opt3);
} else {
throw cljs.core.missing_protocol.call(null,"IConnection.transmit",this$);
}
}
}
});

clojure.browser.net.transmit.cljs$core$IFn$_invoke$arity$5 = (function (this$,opt,opt2,opt3,opt4){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IConnection$transmit$arity$5 == null)))){
return this$.clojure$browser$net$IConnection$transmit$arity$5(this$,opt,opt2,opt3,opt4);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.transmit[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$,opt,opt2,opt3,opt4);
} else {
var m__7458__auto____$1 = (clojure.browser.net.transmit["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$,opt,opt2,opt3,opt4);
} else {
throw cljs.core.missing_protocol.call(null,"IConnection.transmit",this$);
}
}
}
});

clojure.browser.net.transmit.cljs$core$IFn$_invoke$arity$6 = (function (this$,opt,opt2,opt3,opt4,opt5){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IConnection$transmit$arity$6 == null)))){
return this$.clojure$browser$net$IConnection$transmit$arity$6(this$,opt,opt2,opt3,opt4,opt5);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.transmit[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$,opt,opt2,opt3,opt4,opt5);
} else {
var m__7458__auto____$1 = (clojure.browser.net.transmit["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$,opt,opt2,opt3,opt4,opt5);
} else {
throw cljs.core.missing_protocol.call(null,"IConnection.transmit",this$);
}
}
}
});

clojure.browser.net.transmit.cljs$lang$maxFixedArity = 6;

clojure.browser.net.close = (function clojure$browser$net$close(this$){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IConnection$close$arity$1 == null)))){
return this$.clojure$browser$net$IConnection$close$arity$1(this$);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.close[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$);
} else {
var m__7458__auto____$1 = (clojure.browser.net.close["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$);
} else {
throw cljs.core.missing_protocol.call(null,"IConnection.close",this$);
}
}
}
});

goog.net.XhrIo.prototype.clojure$browser$net$IConnection$ = true;

goog.net.XhrIo.prototype.clojure$browser$net$IConnection$transmit$arity$2 = (function (this$,uri){
var this$__$1 = this;
return clojure.browser.net.transmit.call(null,this$__$1,uri,"GET",null,null,clojure.browser.net._STAR_timeout_STAR_);
});

goog.net.XhrIo.prototype.clojure$browser$net$IConnection$transmit$arity$3 = (function (this$,uri,method){
var this$__$1 = this;
return clojure.browser.net.transmit.call(null,this$__$1,uri,method,null,null,clojure.browser.net._STAR_timeout_STAR_);
});

goog.net.XhrIo.prototype.clojure$browser$net$IConnection$transmit$arity$4 = (function (this$,uri,method,content){
var this$__$1 = this;
return clojure.browser.net.transmit.call(null,this$__$1,uri,method,content,null,clojure.browser.net._STAR_timeout_STAR_);
});

goog.net.XhrIo.prototype.clojure$browser$net$IConnection$transmit$arity$5 = (function (this$,uri,method,content,headers){
var this$__$1 = this;
return clojure.browser.net.transmit.call(null,this$__$1,uri,method,content,headers,clojure.browser.net._STAR_timeout_STAR_);
});

goog.net.XhrIo.prototype.clojure$browser$net$IConnection$transmit$arity$6 = (function (this$,uri,method,content,headers,timeout){
var this$__$1 = this;
this$__$1.setTimeoutInterval(timeout);

return this$__$1.send(uri,method,content,headers);
});

goog.net.XhrIo.prototype.clojure$browser$event$IEventType$ = true;

goog.net.XhrIo.prototype.clojure$browser$event$IEventType$event_types$arity$1 = (function (this$){
var this$__$1 = this;
return cljs.core.into.call(null,cljs.core.PersistentArrayMap.EMPTY,cljs.core.map.call(null,((function (this$__$1){
return (function (p__10014){
var vec__10015 = p__10014;
var k = cljs.core.nth.call(null,vec__10015,(0),null);
var v = cljs.core.nth.call(null,vec__10015,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.keyword.call(null,k.toLowerCase()),v], null);
});})(this$__$1))
,cljs.core.merge.call(null,cljs.core.js__GT_clj.call(null,goog.net.EventType))));
});
clojure.browser.net.xpc_config_fields = cljs.core.into.call(null,cljs.core.PersistentArrayMap.EMPTY,cljs.core.map.call(null,(function (p__10016){
var vec__10017 = p__10016;
var k = cljs.core.nth.call(null,vec__10017,(0),null);
var v = cljs.core.nth.call(null,vec__10017,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.keyword.call(null,k.toLowerCase()),v], null);
}),cljs.core.js__GT_clj.call(null,goog.net.xpc.CfgFields)));
/**
 * Returns an XhrIo connection
 */
clojure.browser.net.xhr_connection = (function clojure$browser$net$xhr_connection(){
return (new goog.net.XhrIo());
});

/**
 * @interface
 */
clojure.browser.net.ICrossPageChannel = function(){};

clojure.browser.net.register_service = (function clojure$browser$net$register_service(var_args){
var args10018 = [];
var len__7860__auto___10021 = arguments.length;
var i__7861__auto___10022 = (0);
while(true){
if((i__7861__auto___10022 < len__7860__auto___10021)){
args10018.push((arguments[i__7861__auto___10022]));

var G__10023 = (i__7861__auto___10022 + (1));
i__7861__auto___10022 = G__10023;
continue;
} else {
}
break;
}

var G__10020 = args10018.length;
switch (G__10020) {
case 3:
return clojure.browser.net.register_service.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
case 4:
return clojure.browser.net.register_service.cljs$core$IFn$_invoke$arity$4((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args10018.length)].join('')));

}
});

clojure.browser.net.register_service.cljs$core$IFn$_invoke$arity$3 = (function (this$,service_name,fn){
if((!((this$ == null))) && (!((this$.clojure$browser$net$ICrossPageChannel$register_service$arity$3 == null)))){
return this$.clojure$browser$net$ICrossPageChannel$register_service$arity$3(this$,service_name,fn);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.register_service[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$,service_name,fn);
} else {
var m__7458__auto____$1 = (clojure.browser.net.register_service["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$,service_name,fn);
} else {
throw cljs.core.missing_protocol.call(null,"ICrossPageChannel.register-service",this$);
}
}
}
});

clojure.browser.net.register_service.cljs$core$IFn$_invoke$arity$4 = (function (this$,service_name,fn,encode_json_QMARK_){
if((!((this$ == null))) && (!((this$.clojure$browser$net$ICrossPageChannel$register_service$arity$4 == null)))){
return this$.clojure$browser$net$ICrossPageChannel$register_service$arity$4(this$,service_name,fn,encode_json_QMARK_);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.register_service[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$,service_name,fn,encode_json_QMARK_);
} else {
var m__7458__auto____$1 = (clojure.browser.net.register_service["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$,service_name,fn,encode_json_QMARK_);
} else {
throw cljs.core.missing_protocol.call(null,"ICrossPageChannel.register-service",this$);
}
}
}
});

clojure.browser.net.register_service.cljs$lang$maxFixedArity = 4;

goog.net.xpc.CrossPageChannel.prototype.clojure$browser$net$ICrossPageChannel$ = true;

goog.net.xpc.CrossPageChannel.prototype.clojure$browser$net$ICrossPageChannel$register_service$arity$3 = (function (this$,service_name,fn){
var this$__$1 = this;
return clojure.browser.net.register_service.call(null,this$__$1,service_name,fn,false);
});

goog.net.xpc.CrossPageChannel.prototype.clojure$browser$net$ICrossPageChannel$register_service$arity$4 = (function (this$,service_name,fn,encode_json_QMARK_){
var this$__$1 = this;
return this$__$1.registerService(cljs.core.name.call(null,service_name),fn,encode_json_QMARK_);
});

goog.net.xpc.CrossPageChannel.prototype.clojure$browser$net$IConnection$ = true;

goog.net.xpc.CrossPageChannel.prototype.clojure$browser$net$IConnection$connect$arity$1 = (function (this$){
var this$__$1 = this;
return clojure.browser.net.connect.call(null,this$__$1,null);
});

goog.net.xpc.CrossPageChannel.prototype.clojure$browser$net$IConnection$connect$arity$2 = (function (this$,on_connect_fn){
var this$__$1 = this;
return this$__$1.connect(on_connect_fn);
});

goog.net.xpc.CrossPageChannel.prototype.clojure$browser$net$IConnection$connect$arity$3 = (function (this$,on_connect_fn,config_iframe_fn){
var this$__$1 = this;
return clojure.browser.net.connect.call(null,this$__$1,on_connect_fn,config_iframe_fn,document.body);
});

goog.net.xpc.CrossPageChannel.prototype.clojure$browser$net$IConnection$connect$arity$4 = (function (this$,on_connect_fn,config_iframe_fn,iframe_parent){
var this$__$1 = this;
this$__$1.createPeerIframe(iframe_parent,config_iframe_fn);

return this$__$1.connect(on_connect_fn);
});

goog.net.xpc.CrossPageChannel.prototype.clojure$browser$net$IConnection$transmit$arity$3 = (function (this$,service_name,payload){
var this$__$1 = this;
return this$__$1.send(cljs.core.name.call(null,service_name),payload);
});

goog.net.xpc.CrossPageChannel.prototype.clojure$browser$net$IConnection$close$arity$1 = (function (this$){
var this$__$1 = this;
return this$__$1.close();
});
/**
 * When passed with a config hash-map, returns a parent
 *   CrossPageChannel object. Keys in the config hash map are downcased
 *   versions of the goog.net.xpc.CfgFields enum keys,
 *   e.g. goog.net.xpc.CfgFields.PEER_URI becomes :peer_uri in the config
 *   hash.
 * 
 *   When passed with no args, creates a child CrossPageChannel object,
 *   and the config is automatically taken from the URL param 'xpc', as
 *   per the CrossPageChannel API.
 */
clojure.browser.net.xpc_connection = (function clojure$browser$net$xpc_connection(var_args){
var args10025 = [];
var len__7860__auto___10033 = arguments.length;
var i__7861__auto___10034 = (0);
while(true){
if((i__7861__auto___10034 < len__7860__auto___10033)){
args10025.push((arguments[i__7861__auto___10034]));

var G__10035 = (i__7861__auto___10034 + (1));
i__7861__auto___10034 = G__10035;
continue;
} else {
}
break;
}

var G__10027 = args10025.length;
switch (G__10027) {
case 0:
return clojure.browser.net.xpc_connection.cljs$core$IFn$_invoke$arity$0();

break;
case 1:
return clojure.browser.net.xpc_connection.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args10025.length)].join('')));

}
});

clojure.browser.net.xpc_connection.cljs$core$IFn$_invoke$arity$0 = (function (){
var temp__4657__auto__ = (new goog.Uri(window.location.href)).getParameterValue("xpc");
if(cljs.core.truth_(temp__4657__auto__)){
var config = temp__4657__auto__;
return (new goog.net.xpc.CrossPageChannel(goog.json.parse(config)));
} else {
return null;
}
});

clojure.browser.net.xpc_connection.cljs$core$IFn$_invoke$arity$1 = (function (config){
return (new goog.net.xpc.CrossPageChannel(cljs.core.reduce.call(null,(function (sum,p__10028){
var vec__10029 = p__10028;
var k = cljs.core.nth.call(null,vec__10029,(0),null);
var v = cljs.core.nth.call(null,vec__10029,(1),null);
var temp__4655__auto__ = cljs.core.get.call(null,clojure.browser.net.xpc_config_fields,k);
if(cljs.core.truth_(temp__4655__auto__)){
var field = temp__4655__auto__;
var G__10030 = sum;
(G__10030[field] = v);

return G__10030;
} else {
return sum;
}
}),{},config)));
});

clojure.browser.net.xpc_connection.cljs$lang$maxFixedArity = 1;

/**
 * @interface
 */
clojure.browser.net.IWebSocket = function(){};

clojure.browser.net.open_QMARK_ = (function clojure$browser$net$open_QMARK_(this$){
if((!((this$ == null))) && (!((this$.clojure$browser$net$IWebSocket$open_QMARK_$arity$1 == null)))){
return this$.clojure$browser$net$IWebSocket$open_QMARK_$arity$1(this$);
} else {
var x__7457__auto__ = (((this$ == null))?null:this$);
var m__7458__auto__ = (clojure.browser.net.open_QMARK_[goog.typeOf(x__7457__auto__)]);
if(!((m__7458__auto__ == null))){
return m__7458__auto__.call(null,this$);
} else {
var m__7458__auto____$1 = (clojure.browser.net.open_QMARK_["_"]);
if(!((m__7458__auto____$1 == null))){
return m__7458__auto____$1.call(null,this$);
} else {
throw cljs.core.missing_protocol.call(null,"IWebSocket.open?",this$);
}
}
}
});

goog.net.WebSocket.prototype.clojure$browser$net$IWebSocket$ = true;

goog.net.WebSocket.prototype.clojure$browser$net$IWebSocket$open_QMARK_$arity$1 = (function (this$){
var this$__$1 = this;
return this$__$1.isOpen(cljs.core.List.EMPTY);
});

goog.net.WebSocket.prototype.clojure$browser$net$IConnection$ = true;

goog.net.WebSocket.prototype.clojure$browser$net$IConnection$connect$arity$2 = (function (this$,url){
var this$__$1 = this;
return clojure.browser.net.connect.call(null,this$__$1,url,null);
});

goog.net.WebSocket.prototype.clojure$browser$net$IConnection$connect$arity$3 = (function (this$,url,protocol){
var this$__$1 = this;
return this$__$1.open(url,protocol);
});

goog.net.WebSocket.prototype.clojure$browser$net$IConnection$transmit$arity$2 = (function (this$,message){
var this$__$1 = this;
return this$__$1.send(message);
});

goog.net.WebSocket.prototype.clojure$browser$net$IConnection$close$arity$1 = (function (this$){
var this$__$1 = this;
return this$__$1.close(cljs.core.List.EMPTY);
});

goog.net.WebSocket.prototype.clojure$browser$event$IEventType$ = true;

goog.net.WebSocket.prototype.clojure$browser$event$IEventType$event_types$arity$1 = (function (this$){
var this$__$1 = this;
return cljs.core.into.call(null,cljs.core.PersistentArrayMap.EMPTY,cljs.core.map.call(null,((function (this$__$1){
return (function (p__10037){
var vec__10038 = p__10037;
var k = cljs.core.nth.call(null,vec__10038,(0),null);
var v = cljs.core.nth.call(null,vec__10038,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.keyword.call(null,k.toLowerCase()),v], null);
});})(this$__$1))
,cljs.core.merge.call(null,cljs.core.js__GT_clj.call(null,goog.net.WebSocket.EventType))));
});
clojure.browser.net.websocket_connection = (function clojure$browser$net$websocket_connection(var_args){
var args10039 = [];
var len__7860__auto___10042 = arguments.length;
var i__7861__auto___10043 = (0);
while(true){
if((i__7861__auto___10043 < len__7860__auto___10042)){
args10039.push((arguments[i__7861__auto___10043]));

var G__10044 = (i__7861__auto___10043 + (1));
i__7861__auto___10043 = G__10044;
continue;
} else {
}
break;
}

var G__10041 = args10039.length;
switch (G__10041) {
case 0:
return clojure.browser.net.websocket_connection.cljs$core$IFn$_invoke$arity$0();

break;
case 1:
return clojure.browser.net.websocket_connection.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return clojure.browser.net.websocket_connection.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args10039.length)].join('')));

}
});

clojure.browser.net.websocket_connection.cljs$core$IFn$_invoke$arity$0 = (function (){
return clojure.browser.net.websocket_connection.call(null,null,null);
});

clojure.browser.net.websocket_connection.cljs$core$IFn$_invoke$arity$1 = (function (auto_reconnect_QMARK_){
return clojure.browser.net.websocket_connection.call(null,auto_reconnect_QMARK_,null);
});

clojure.browser.net.websocket_connection.cljs$core$IFn$_invoke$arity$2 = (function (auto_reconnect_QMARK_,next_reconnect_fn){
return (new goog.net.WebSocket(auto_reconnect_QMARK_,next_reconnect_fn));
});

clojure.browser.net.websocket_connection.cljs$lang$maxFixedArity = 2;

//# sourceMappingURL=net.js.map