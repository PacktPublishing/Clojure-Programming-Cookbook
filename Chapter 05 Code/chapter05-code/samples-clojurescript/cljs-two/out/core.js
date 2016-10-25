// Compiled by ClojureScript 1.7.228 {}
goog.provide('core');
goog.require('cljs.core');
cljs.core.enable_console_print_BANG_.call(null);
core.click_fn = (function core$click_fn(){
return cljs.core.println.call(null,"Clicked!");
});
core.$ = jQuery;
core.click_slide_fn = (function core$click_slide_fn(){
if(cljs.core.truth_(core.$.call(null,"div:first").is(":hidden"))){
return core.$.call(null,"div").slideDown("slow");
} else {
return core.$.call(null,"div").hide();
}
});
core.$.call(null,"body").click(core.click_slide_fn);

//# sourceMappingURL=core.js.map