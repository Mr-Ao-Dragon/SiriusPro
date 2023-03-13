import{g as ee,b2 as So,n as ye,aM as de,m as W,t as $,v as G,e as E,x as h,Q as ae,y as Ze,w as re,o as T,c as Z,C as Ie,u as c,F as te,G as O,J as Qe,I as me,A as k,B as K,_ as V,D as Ee,a5 as Xe,q as Ue,a6 as B,a9 as xe,U as ko,z as le,H as Re,j as eo,L as Pe,M as Fe,Z as Ce,f as fe,aa as se,ak as _e,a$ as Oo,am as ue,X as ie,bL as No,ag as Ae,bM as Bo,aw as ce,r as D,a as x,af as Ro,b0 as Po,ad as oo,ah as ve,Y as Fo,K as Be,N as Me,ba as be,b9 as Se,av as Ao,aj as zo,an as L,b7 as je,h as Lo,bN as Do,R as Ko}from"./index-ca2c307a.js";import{n as no,d as to,e as Go,T as lo,c as Vo,t as we,v as Ho,f as Uo}from"./error-3b89c669.js";import{U as ze,C as jo}from"./event-9519ab40.js";import{c as Le,u as Yo,b as Wo}from"./index-d834ceac.js";import{E as so,T as Jo}from"./index-a3e72b8c.js";import{t as ne,l as qo,u as Ye,E as De,O as Zo,r as ao,x as We,F as Qo,y as ge,f as Xo}from"./index-b29154a5.js";import{E as xo}from"./index-ff8ad0f0.js";import{E as C,m as en}from"./index-c3904ec7.js";import{u as on}from"./index-d4919497.js";const ro=Symbol("radioGroupKey"),nn=ee({size:{type:[Number,String],values:So,default:"",validator:e=>ye(e)},shape:{type:String,values:["circle","square"],default:"circle"},icon:{type:de},src:{type:String,default:""},alt:String,srcSet:String,fit:{type:W(String),default:"cover"}}),tn={error:e=>e instanceof Event},ln=["src","alt","srcset"],sn=$({name:"ElAvatar"}),an=$({...sn,props:nn,emits:tn,setup(e,{emit:n}){const t=e,o=G("avatar"),s=E(!1),f=h(()=>{const{size:r,icon:p,shape:u}=t,m=[o.b()];return ae(r)&&m.push(o.m(r)),p&&m.push(o.m("icon")),u&&m.push(o.m(u)),m}),a=h(()=>{const{size:r}=t;return ye(r)?o.cssVarBlock({size:Ze(r)||""}):void 0}),i=h(()=>({objectFit:t.fit}));re(()=>t.src,()=>s.value=!1);function l(r){s.value=!0,n("error",r)}return(r,p)=>(T(),Z("span",{class:K(c(f)),style:Ie(c(a))},[(r.src||r.srcSet)&&!s.value?(T(),Z("img",{key:0,src:r.src,alt:r.alt,srcset:r.srcSet,style:Ie(c(i)),onError:l},null,44,ln)):r.icon?(T(),te(c(me),{key:1},{default:O(()=>[(T(),te(Qe(r.icon)))]),_:1})):k(r.$slots,"default",{key:2})],6))}});var rn=V(an,[["__file","/home/runner/work/element-plus/element-plus/packages/components/avatar/src/avatar.vue"]]);const Ut=Ee(rn),io=ee({size:Xe,disabled:Boolean,label:{type:[String,Number,Boolean],default:""}}),un=ee({...io,modelValue:{type:[String,Number,Boolean],default:""},name:{type:String,default:""},border:Boolean}),uo={[ze]:e=>ae(e)||ye(e)||Ue(e),[jo]:e=>ae(e)||ye(e)||Ue(e)},co=(e,n)=>{const t=E(),o=B(ro,void 0),s=h(()=>!!o),f=h({get(){return s.value?o.modelValue:e.modelValue},set(p){s.value?o.changeEvent(p):n&&n(ze,p),t.value.checked=e.modelValue===e.label}}),a=xe(h(()=>o==null?void 0:o.size)),i=ko(h(()=>o==null?void 0:o.disabled)),l=E(!1),r=h(()=>i.value||s.value&&f.value!==e.label?-1:0);return{radioRef:t,isGroup:s,radioGroup:o,focus:l,size:a,disabled:i,tabIndex:r,modelValue:f}},dn=["value","name","disabled"],cn=$({name:"ElRadio"}),pn=$({...cn,props:un,emits:uo,setup(e,{emit:n}){const t=e,o=G("radio"),{radioRef:s,radioGroup:f,focus:a,size:i,disabled:l,modelValue:r}=co(t,n);function p(){Ce(()=>n("change",r.value))}return(u,m)=>{var d;return T(),Z("label",{class:K([c(o).b(),c(o).is("disabled",c(l)),c(o).is("focus",c(a)),c(o).is("bordered",u.border),c(o).is("checked",c(r)===u.label),c(o).m(c(i))])},[le("span",{class:K([c(o).e("input"),c(o).is("disabled",c(l)),c(o).is("checked",c(r)===u.label)])},[Re(le("input",{ref_key:"radioRef",ref:s,"onUpdate:modelValue":m[0]||(m[0]=y=>eo(r)?r.value=y:null),class:K(c(o).e("original")),value:u.label,name:u.name||((d=c(f))==null?void 0:d.name),disabled:c(l),type:"radio",onFocus:m[1]||(m[1]=y=>a.value=!0),onBlur:m[2]||(m[2]=y=>a.value=!1),onChange:p},null,42,dn),[[no,c(r)]]),le("span",{class:K(c(o).e("inner"))},null,2)],2),le("span",{class:K(c(o).e("label")),onKeydown:m[3]||(m[3]=to(()=>{},["stop"]))},[k(u.$slots,"default",{},()=>[Pe(Fe(u.label),1)])],34)],2)}}});var mn=V(pn,[["__file","/home/runner/work/element-plus/element-plus/packages/components/radio/src/radio.vue"]]);const fn=ee({...io,name:{type:String,default:""}}),vn=["value","name","disabled"],bn=$({name:"ElRadioButton"}),gn=$({...bn,props:fn,setup(e){const n=e,t=G("radio"),{radioRef:o,focus:s,size:f,disabled:a,modelValue:i,radioGroup:l}=co(n),r=h(()=>({backgroundColor:(l==null?void 0:l.fill)||"",borderColor:(l==null?void 0:l.fill)||"",boxShadow:l!=null&&l.fill?`-1px 0 0 0 ${l.fill}`:"",color:(l==null?void 0:l.textColor)||""}));return(p,u)=>{var m;return T(),Z("label",{class:K([c(t).b("button"),c(t).is("active",c(i)===p.label),c(t).is("disabled",c(a)),c(t).is("focus",c(s)),c(t).bm("button",c(f))])},[Re(le("input",{ref_key:"radioRef",ref:o,"onUpdate:modelValue":u[0]||(u[0]=d=>eo(i)?i.value=d:null),class:K(c(t).be("button","original-radio")),value:p.label,type:"radio",name:p.name||((m=c(l))==null?void 0:m.name),disabled:c(a),onFocus:u[1]||(u[1]=d=>s.value=!0),onBlur:u[2]||(u[2]=d=>s.value=!1)},null,42,vn),[[no,c(i)]]),le("span",{class:K(c(t).be("button","inner")),style:Ie(c(i)===p.label?c(r):{}),onKeydown:u[3]||(u[3]=to(()=>{},["stop"]))},[k(p.$slots,"default",{},()=>[Pe(Fe(p.label),1)])],38)],2)}}});var po=V(gn,[["__file","/home/runner/work/element-plus/element-plus/packages/components/radio/src/radio-button.vue"]]);const hn=ee({id:{type:String,default:void 0},size:Xe,disabled:Boolean,modelValue:{type:[String,Number,Boolean],default:""},fill:{type:String,default:""},label:{type:String,default:void 0},textColor:{type:String,default:""},name:{type:String,default:void 0},validateEvent:{type:Boolean,default:!0}}),yn=uo,In=["id","aria-label","aria-labelledby"],wn=$({name:"ElRadioGroup"}),En=$({...wn,props:hn,emits:yn,setup(e,{emit:n}){const t=e,o=G("radio"),s=Le(),f=E(),{formItem:a}=Yo(),{inputId:i,isLabeledByFormItem:l}=Wo(t,{formItemContext:a}),r=u=>{n(ze,u),Ce(()=>n("change",u))};fe(()=>{const u=f.value.querySelectorAll("[type=radio]"),m=u[0];!Array.from(u).some(d=>d.checked)&&m&&(m.tabIndex=0)});const p=h(()=>t.name||s.value);return se(ro,_e({...Oo(t),changeEvent:r,name:p})),re(()=>t.modelValue,()=>{t.validateEvent&&(a==null||a.validate("change").catch(u=>Go()))}),(u,m)=>(T(),Z("div",{id:c(i),ref_key:"radioGroupRef",ref:f,class:K(c(o).b("group")),role:"radiogroup","aria-label":c(l)?void 0:u.label||"radio-group","aria-labelledby":c(l)?c(a).labelId:void 0},[k(u.$slots,"default")],10,In))}});var mo=V(En,[["__file","/home/runner/work/element-plus/element-plus/packages/components/radio/src/radio-group.vue"]]);const jt=Ee(mn,{RadioButton:po,RadioGroup:mo}),Yt=ue(mo),Wt=ue(po),Cn=$({name:"ElCollapseTransition"}),_n=$({...Cn,setup(e){const n=G("collapse-transition"),t={beforeEnter(o){o.dataset||(o.dataset={}),o.dataset.oldPaddingTop=o.style.paddingTop,o.dataset.oldPaddingBottom=o.style.paddingBottom,o.style.maxHeight=0,o.style.paddingTop=0,o.style.paddingBottom=0},enter(o){o.dataset.oldOverflow=o.style.overflow,o.scrollHeight!==0?(o.style.maxHeight=`${o.scrollHeight}px`,o.style.paddingTop=o.dataset.oldPaddingTop,o.style.paddingBottom=o.dataset.oldPaddingBottom):(o.style.maxHeight=0,o.style.paddingTop=o.dataset.oldPaddingTop,o.style.paddingBottom=o.dataset.oldPaddingBottom),o.style.overflow="hidden"},afterEnter(o){o.style.maxHeight="",o.style.overflow=o.dataset.oldOverflow},beforeLeave(o){o.dataset||(o.dataset={}),o.dataset.oldPaddingTop=o.style.paddingTop,o.dataset.oldPaddingBottom=o.style.paddingBottom,o.dataset.oldOverflow=o.style.overflow,o.style.maxHeight=`${o.scrollHeight}px`,o.style.overflow="hidden"},leave(o){o.scrollHeight!==0&&(o.style.maxHeight=0,o.style.paddingTop=0,o.style.paddingBottom=0)},afterLeave(o){o.style.maxHeight="",o.style.overflow=o.dataset.oldOverflow,o.style.paddingTop=o.dataset.oldPaddingTop,o.style.paddingBottom=o.dataset.oldPaddingBottom}};return(o,s)=>(T(),te(lo,ie({name:c(n).b()},No(t)),{default:O(()=>[k(o.$slots,"default")]),_:3},16,["name"]))}});var he=V(_n,[["__file","/home/runner/work/element-plus/element-plus/packages/components/collapse-transition/src/collapse-transition.vue"]]);he.install=e=>{e.component(he.name,he)};const fo=he,Jt=fo,Mn=$({inheritAttrs:!1});function Tn(e,n,t,o,s,f){return k(e.$slots,"default")}var $n=V(Mn,[["render",Tn],["__file","/home/runner/work/element-plus/element-plus/packages/components/collection/src/collection.vue"]]);const Sn=$({name:"ElCollectionItem",inheritAttrs:!1});function kn(e,n,t,o,s,f){return k(e.$slots,"default")}var On=V(Sn,[["render",kn],["__file","/home/runner/work/element-plus/element-plus/packages/components/collection/src/collection-item.vue"]]);const vo="data-el-collection-item",bo=e=>{const n=`El${e}Collection`,t=`${n}Item`,o=Symbol(n),s=Symbol(t),f={...$n,name:n,setup(){const i=E(null),l=new Map;se(o,{itemMap:l,getItems:()=>{const p=c(i);if(!p)return[];const u=Array.from(p.querySelectorAll(`[${vo}]`));return[...l.values()].sort((d,y)=>u.indexOf(d.ref)-u.indexOf(y.ref))},collectionRef:i})}},a={...On,name:t,setup(i,{attrs:l}){const r=E(null),p=B(o,void 0);se(s,{collectionItemRef:r}),fe(()=>{const u=c(r);u&&p.itemMap.set(u,{ref:u,...l})}),Ae(()=>{const u=c(r);p.itemMap.delete(u)})}};return{COLLECTION_INJECTION_KEY:o,COLLECTION_ITEM_INJECTION_KEY:s,ElCollection:f,ElCollectionItem:a}},Nn=ee({style:{type:W([String,Array,Object])},currentTabId:{type:W(String)},defaultCurrentTabId:String,loop:Boolean,dir:{type:String,values:["ltr","rtl"],default:"ltr"},orientation:{type:W(String)},onBlur:Function,onFocus:Function,onMousedown:Function}),{ElCollection:Bn,ElCollectionItem:Rn,COLLECTION_INJECTION_KEY:Ke,COLLECTION_ITEM_INJECTION_KEY:Pn}=bo("RovingFocusGroup"),Ge=Symbol("elRovingFocusGroup"),go=Symbol("elRovingFocusGroupItem"),Fn={ArrowLeft:"prev",ArrowUp:"prev",ArrowRight:"next",ArrowDown:"next",PageUp:"first",Home:"first",PageDown:"last",End:"last"},An=(e,n)=>{if(n!=="rtl")return e;switch(e){case C.right:return C.left;case C.left:return C.right;default:return e}},zn=(e,n,t)=>{const o=An(e.key,t);if(!(n==="vertical"&&[C.left,C.right].includes(o))&&!(n==="horizontal"&&[C.up,C.down].includes(o)))return Fn[o]},Ln=(e,n)=>e.map((t,o)=>e[(o+n)%e.length]),Ve=e=>{const{activeElement:n}=document;for(const t of e)if(t===n||(t.focus(),n!==document.activeElement))return},Je="currentTabIdChange",qe="rovingFocusGroup.entryFocus",Dn={bubbles:!1,cancelable:!0},Kn=$({name:"ElRovingFocusGroupImpl",inheritAttrs:!1,props:Nn,emits:[Je,"entryFocus"],setup(e,{emit:n}){var t;const o=E((t=e.currentTabId||e.defaultCurrentTabId)!=null?t:null),s=E(!1),f=E(!1),a=E(null),{getItems:i}=B(Ke,void 0),l=h(()=>[{outline:"none"},e.style]),r=I=>{n(Je,I)},p=()=>{s.value=!0},u=ne(I=>{var _;(_=e.onMousedown)==null||_.call(e,I)},()=>{f.value=!0}),m=ne(I=>{var _;(_=e.onFocus)==null||_.call(e,I)},I=>{const _=!c(f),{target:H,currentTarget:R}=I;if(H===R&&_&&!c(s)){const J=new Event(qe,Dn);if(R==null||R.dispatchEvent(J),!J.defaultPrevented){const N=i().filter(P=>P.focusable),Y=N.find(P=>P.active),S=N.find(P=>P.id===c(o)),q=[Y,S,...N].filter(Boolean).map(P=>P.ref);Ve(q)}}f.value=!1}),d=ne(I=>{var _;(_=e.onBlur)==null||_.call(e,I)},()=>{s.value=!1}),y=(...I)=>{n("entryFocus",...I)};se(Ge,{currentTabbedId:Bo(o),loop:ce(e,"loop"),tabIndex:h(()=>c(s)?-1:0),rovingFocusGroupRef:a,rovingFocusGroupRootStyle:l,orientation:ce(e,"orientation"),dir:ce(e,"dir"),onItemFocus:r,onItemShiftTab:p,onBlur:d,onFocus:m,onMousedown:u}),re(()=>e.currentTabId,I=>{o.value=I??null}),Vo(a,qe,y)}});function Gn(e,n,t,o,s,f){return k(e.$slots,"default")}var Vn=V(Kn,[["render",Gn],["__file","/home/runner/work/element-plus/element-plus/packages/components/roving-focus-group/src/roving-focus-group-impl.vue"]]);const Hn=$({name:"ElRovingFocusGroup",components:{ElFocusGroupCollection:Bn,ElRovingFocusGroupImpl:Vn}});function Un(e,n,t,o,s,f){const a=D("el-roving-focus-group-impl"),i=D("el-focus-group-collection");return T(),te(i,null,{default:O(()=>[x(a,Ro(Po(e.$attrs)),{default:O(()=>[k(e.$slots,"default")]),_:3},16)]),_:3})}var jn=V(Hn,[["render",Un],["__file","/home/runner/work/element-plus/element-plus/packages/components/roving-focus-group/src/roving-focus-group.vue"]]);const Yn=$({components:{ElRovingFocusCollectionItem:Rn},props:{focusable:{type:Boolean,default:!0},active:{type:Boolean,default:!1}},emits:["mousedown","focus","keydown"],setup(e,{emit:n}){const{currentTabbedId:t,loop:o,onItemFocus:s,onItemShiftTab:f}=B(Ge,void 0),{getItems:a}=B(Ke,void 0),i=Le(),l=E(null),r=ne(d=>{n("mousedown",d)},d=>{e.focusable?s(c(i)):d.preventDefault()}),p=ne(d=>{n("focus",d)},()=>{s(c(i))}),u=ne(d=>{n("keydown",d)},d=>{const{key:y,shiftKey:I,target:_,currentTarget:H}=d;if(y===C.tab&&I){f();return}if(_!==H)return;const R=zn(d);if(R){d.preventDefault();let N=a().filter(Y=>Y.focusable).map(Y=>Y.ref);switch(R){case"last":{N.reverse();break}case"prev":case"next":{R==="prev"&&N.reverse();const Y=N.indexOf(H);N=o.value?Ln(N,Y+1):N.slice(Y+1);break}}Ce(()=>{Ve(N)})}}),m=h(()=>t.value===c(i));return se(go,{rovingFocusGroupItemRef:l,tabIndex:h(()=>c(m)?0:-1),handleMousedown:r,handleFocus:p,handleKeydown:u}),{id:i,handleKeydown:u,handleFocus:p,handleMousedown:r}}});function Wn(e,n,t,o,s,f){const a=D("el-roving-focus-collection-item");return T(),te(a,{id:e.id,focusable:e.focusable,active:e.active},{default:O(()=>[k(e.$slots,"default")]),_:3},8,["id","focusable","active"])}var Jn=V(Yn,[["render",Wn],["__file","/home/runner/work/element-plus/element-plus/packages/components/roving-focus-group/src/roving-focus-item.vue"]]);const qn=ee({trigger:qo.trigger,effect:{...Ye.effect,default:"light"},type:{type:W(String)},placement:{type:W(String),default:"bottom"},popperOptions:{type:W(Object),default:()=>({})},id:String,size:{type:String,default:""},splitButton:Boolean,hideOnClick:{type:Boolean,default:!0},loop:{type:Boolean,default:!0},showTimeout:{type:Number,default:150},hideTimeout:{type:Number,default:150},tabindex:{type:W([Number,String]),default:0},maxHeight:{type:W([Number,String]),default:""},popperClass:{type:String,default:""},disabled:{type:Boolean,default:!1},role:{type:String,default:"menu"},buttonProps:{type:W(Object)},teleported:Ye.teleported}),ho=ee({command:{type:[Object,String,Number],default:()=>({})},disabled:Boolean,divided:Boolean,textValue:String,icon:{type:de}}),Zn=ee({onKeydown:{type:W(Function)}}),Qn=[C.down,C.pageDown,C.home],yo=[C.up,C.pageUp,C.end],Xn=[...Qn,...yo],{ElCollection:xn,ElCollectionItem:et,COLLECTION_INJECTION_KEY:ot,COLLECTION_ITEM_INJECTION_KEY:nt}=bo("Dropdown"),Te=Symbol("elDropdown"),{ButtonGroup:tt}=so,lt=$({name:"ElDropdown",components:{ElButton:so,ElButtonGroup:tt,ElScrollbar:xo,ElDropdownCollection:xn,ElTooltip:De,ElRovingFocusGroup:jn,ElOnlyChild:Zo,ElIcon:me,ArrowDown:oo},props:qn,emits:["visible-change","click","command"],setup(e,{emit:n}){const t=ve(),o=G("dropdown"),{t:s}=on(),f=E(),a=E(),i=E(null),l=E(null),r=E(null),p=E(null),u=E(!1),m=[C.enter,C.space,C.down],d=h(()=>({maxHeight:Ze(e.maxHeight)})),y=h(()=>[o.m(N.value)]),I=Le().value,_=h(()=>e.id||I);function H(){R()}function R(){var w;(w=i.value)==null||w.onClose()}function J(){var w;(w=i.value)==null||w.onOpen()}const N=xe();function Y(...w){n("command",...w)}function S(){}function U(){const w=c(l);w==null||w.focus(),p.value=null}function q(w){p.value=w}function P(w){u.value||(w.preventDefault(),w.stopImmediatePropagation())}function b(){n("visible-change",!0)}function v(w){(w==null?void 0:w.type)==="keydown"&&l.value.focus()}function g(){n("visible-change",!1)}return se(Te,{contentRef:l,role:h(()=>e.role),triggerId:_,isUsingKeyboard:u,onItemEnter:S,onItemLeave:U}),se("elDropdown",{instance:t,dropdownSize:N,handleClick:H,commandHandler:Y,trigger:ce(e,"trigger"),hideOnClick:ce(e,"hideOnClick")}),{t:s,ns:o,scrollbar:r,wrapStyle:d,dropdownTriggerKls:y,dropdownSize:N,triggerId:_,triggerKeys:m,currentTabId:p,handleCurrentTabIdChange:q,handlerMainButtonClick:w=>{n("click",w)},handleEntryFocus:P,handleClose:R,handleOpen:J,handleBeforeShowTooltip:b,handleShowTooltip:v,handleBeforeHideTooltip:g,onFocusAfterTrapped:w=>{var Q,X;w.preventDefault(),(X=(Q=l.value)==null?void 0:Q.focus)==null||X.call(Q,{preventScroll:!0})},popperRef:i,contentRef:l,triggeringElementRef:f,referenceElementRef:a}}});function st(e,n,t,o,s,f){var a;const i=D("el-dropdown-collection"),l=D("el-roving-focus-group"),r=D("el-scrollbar"),p=D("el-only-child"),u=D("el-tooltip"),m=D("el-button"),d=D("arrow-down"),y=D("el-icon"),I=D("el-button-group");return T(),Z("div",{class:K([e.ns.b(),e.ns.is("disabled",e.disabled)])},[x(u,{ref:"popperRef",role:e.role,effect:e.effect,"fallback-placements":["bottom","top"],"popper-options":e.popperOptions,"gpu-acceleration":!1,"hide-after":e.trigger==="hover"?e.hideTimeout:0,"manual-mode":!0,placement:e.placement,"popper-class":[e.ns.e("popper"),e.popperClass],"reference-element":(a=e.referenceElementRef)==null?void 0:a.$el,trigger:e.trigger,"trigger-keys":e.triggerKeys,"trigger-target-el":e.contentRef,"show-after":e.trigger==="hover"?e.showTimeout:0,"stop-popper-mouse-event":!1,"virtual-ref":e.triggeringElementRef,"virtual-triggering":e.splitButton,disabled:e.disabled,transition:`${e.ns.namespace.value}-zoom-in-top`,teleported:e.teleported,pure:"",persistent:"",onBeforeShow:e.handleBeforeShowTooltip,onShow:e.handleShowTooltip,onBeforeHide:e.handleBeforeHideTooltip},Fo({content:O(()=>[x(r,{ref:"scrollbar","wrap-style":e.wrapStyle,tag:"div","view-class":e.ns.e("list")},{default:O(()=>[x(l,{loop:e.loop,"current-tab-id":e.currentTabId,orientation:"horizontal",onCurrentTabIdChange:e.handleCurrentTabIdChange,onEntryFocus:e.handleEntryFocus},{default:O(()=>[x(i,null,{default:O(()=>[k(e.$slots,"dropdown")]),_:3})]),_:3},8,["loop","current-tab-id","onCurrentTabIdChange","onEntryFocus"])]),_:3},8,["wrap-style","view-class"])]),_:2},[e.splitButton?void 0:{name:"default",fn:O(()=>[x(p,{id:e.triggerId,role:"button",tabindex:e.tabindex},{default:O(()=>[k(e.$slots,"default")]),_:3},8,["id","tabindex"])])}]),1032,["role","effect","popper-options","hide-after","placement","popper-class","reference-element","trigger","trigger-keys","trigger-target-el","show-after","virtual-ref","virtual-triggering","disabled","transition","teleported","onBeforeShow","onShow","onBeforeHide"]),e.splitButton?(T(),te(I,{key:0},{default:O(()=>[x(m,ie({ref:"referenceElementRef"},e.buttonProps,{size:e.dropdownSize,type:e.type,disabled:e.disabled,tabindex:e.tabindex,onClick:e.handlerMainButtonClick}),{default:O(()=>[k(e.$slots,"default")]),_:3},16,["size","type","disabled","tabindex","onClick"]),x(m,ie({id:e.triggerId,ref:"triggeringElementRef"},e.buttonProps,{role:"button",size:e.dropdownSize,type:e.type,class:e.ns.e("caret-button"),disabled:e.disabled,tabindex:e.tabindex,"aria-label":e.t("el.dropdown.toggleDropdown")}),{default:O(()=>[x(y,{class:K(e.ns.e("icon"))},{default:O(()=>[x(d)]),_:1},8,["class"])]),_:1},16,["id","size","type","class","disabled","tabindex","aria-label"])]),_:3})):Be("v-if",!0)],2)}var at=V(lt,[["render",st],["__file","/home/runner/work/element-plus/element-plus/packages/components/dropdown/src/dropdown.vue"]]);const rt=$({name:"DropdownItemImpl",components:{ElIcon:me},props:ho,emits:["pointermove","pointerleave","click","clickimpl"],setup(e,{emit:n}){const t=G("dropdown"),{role:o}=B(Te,void 0),{collectionItemRef:s}=B(nt,void 0),{collectionItemRef:f}=B(Pn,void 0),{rovingFocusGroupItemRef:a,tabIndex:i,handleFocus:l,handleKeydown:r,handleMousedown:p}=B(go,void 0),u=ao(s,f,a),m=h(()=>o.value==="menu"?"menuitem":o.value==="navigation"?"link":"button"),d=ne(y=>{const{code:I}=y;if(I===C.enter||I===C.space)return y.preventDefault(),y.stopImmediatePropagation(),n("clickimpl",y),!0},r);return{ns:t,itemRef:u,dataset:{[vo]:""},role:m,tabIndex:i,handleFocus:l,handleKeydown:d,handleMousedown:p}}}),it=["aria-disabled","tabindex","role"];function ut(e,n,t,o,s,f){const a=D("el-icon");return T(),Z(Me,null,[e.divided?(T(),Z("li",ie({key:0,role:"separator",class:e.ns.bem("menu","item","divided")},e.$attrs),null,16)):Be("v-if",!0),le("li",ie({ref:e.itemRef},{...e.dataset,...e.$attrs},{"aria-disabled":e.disabled,class:[e.ns.be("menu","item"),e.ns.is("disabled",e.disabled)],tabindex:e.tabIndex,role:e.role,onClick:n[0]||(n[0]=i=>e.$emit("clickimpl",i)),onFocus:n[1]||(n[1]=(...i)=>e.handleFocus&&e.handleFocus(...i)),onKeydown:n[2]||(n[2]=(...i)=>e.handleKeydown&&e.handleKeydown(...i)),onMousedown:n[3]||(n[3]=(...i)=>e.handleMousedown&&e.handleMousedown(...i)),onPointermove:n[4]||(n[4]=i=>e.$emit("pointermove",i)),onPointerleave:n[5]||(n[5]=i=>e.$emit("pointerleave",i))}),[e.icon?(T(),te(a,{key:0},{default:O(()=>[(T(),te(Qe(e.icon)))]),_:1})):Be("v-if",!0),k(e.$slots,"default")],16,it)],64)}var dt=V(rt,[["render",ut],["__file","/home/runner/work/element-plus/element-plus/packages/components/dropdown/src/dropdown-item-impl.vue"]]);const Io=()=>{const e=B("elDropdown",{}),n=h(()=>e==null?void 0:e.dropdownSize);return{elDropdown:e,_elDropdownSize:n}},ct=$({name:"ElDropdownItem",components:{ElDropdownCollectionItem:et,ElRovingFocusItem:Jn,ElDropdownItemImpl:dt},inheritAttrs:!1,props:ho,emits:["pointermove","pointerleave","click"],setup(e,{emit:n,attrs:t}){const{elDropdown:o}=Io(),s=ve(),f=E(null),a=h(()=>{var d,y;return(y=(d=c(f))==null?void 0:d.textContent)!=null?y:""}),{onItemEnter:i,onItemLeave:l}=B(Te,void 0),r=ne(d=>(n("pointermove",d),d.defaultPrevented),We(d=>{var y;e.disabled?l(d):(i(d),d.defaultPrevented||(y=d.currentTarget)==null||y.focus())})),p=ne(d=>(n("pointerleave",d),d.defaultPrevented),We(d=>{l(d)})),u=ne(d=>{if(!e.disabled)return n("click",d),d.type!=="keydown"&&d.defaultPrevented},d=>{var y,I,_;if(e.disabled){d.stopImmediatePropagation();return}(y=o==null?void 0:o.hideOnClick)!=null&&y.value&&((I=o.handleClick)==null||I.call(o)),(_=o.commandHandler)==null||_.call(o,e.command,s,d)}),m=h(()=>({...e,...t}));return{handleClick:u,handlePointerMove:r,handlePointerLeave:p,textContent:a,propsAndAttrs:m}}});function pt(e,n,t,o,s,f){var a;const i=D("el-dropdown-item-impl"),l=D("el-roving-focus-item"),r=D("el-dropdown-collection-item");return T(),te(r,{disabled:e.disabled,"text-value":(a=e.textValue)!=null?a:e.textContent},{default:O(()=>[x(l,{focusable:!e.disabled},{default:O(()=>[x(i,ie(e.propsAndAttrs,{onPointerleave:e.handlePointerLeave,onPointermove:e.handlePointerMove,onClickimpl:e.handleClick}),{default:O(()=>[k(e.$slots,"default")]),_:3},16,["onPointerleave","onPointermove","onClickimpl"])]),_:3},8,["focusable"])]),_:3},8,["disabled","text-value"])}var wo=V(ct,[["render",pt],["__file","/home/runner/work/element-plus/element-plus/packages/components/dropdown/src/dropdown-item.vue"]]);const mt=$({name:"ElDropdownMenu",props:Zn,setup(e){const n=G("dropdown"),{_elDropdownSize:t}=Io(),o=t.value,{focusTrapRef:s,onKeydown:f}=B(Qo,void 0),{contentRef:a,role:i,triggerId:l}=B(Te,void 0),{collectionRef:r,getItems:p}=B(ot,void 0),{rovingFocusGroupRef:u,rovingFocusGroupRootStyle:m,tabIndex:d,onBlur:y,onFocus:I,onMousedown:_}=B(Ge,void 0),{collectionRef:H}=B(Ke,void 0),R=h(()=>[n.b("menu"),n.bm("menu",o==null?void 0:o.value)]),J=ao(a,r,s,u,H),N=ne(S=>{var U;(U=e.onKeydown)==null||U.call(e,S)},S=>{const{currentTarget:U,code:q,target:P}=S;if(U.contains(P),C.tab===q&&S.stopImmediatePropagation(),S.preventDefault(),P!==c(a)||!Xn.includes(q))return;const v=p().filter(g=>!g.disabled).map(g=>g.ref);yo.includes(q)&&v.reverse(),Ve(v)});return{size:o,rovingFocusGroupRootStyle:m,tabIndex:d,dropdownKls:R,role:i,triggerId:l,dropdownListWrapperRef:J,handleKeydown:S=>{N(S),f(S)},onBlur:y,onFocus:I,onMousedown:_}}}),ft=["role","aria-labelledby"];function vt(e,n,t,o,s,f){return T(),Z("ul",{ref:e.dropdownListWrapperRef,class:K(e.dropdownKls),style:Ie(e.rovingFocusGroupRootStyle),tabindex:-1,role:e.role,"aria-labelledby":e.triggerId,onBlur:n[0]||(n[0]=(...a)=>e.onBlur&&e.onBlur(...a)),onFocus:n[1]||(n[1]=(...a)=>e.onFocus&&e.onFocus(...a)),onKeydown:n[2]||(n[2]=(...a)=>e.handleKeydown&&e.handleKeydown(...a)),onMousedown:n[3]||(n[3]=(...a)=>e.onMousedown&&e.onMousedown(...a))},[k(e.$slots,"default")],46,ft)}var Eo=V(mt,[["render",vt],["__file","/home/runner/work/element-plus/element-plus/packages/components/dropdown/src/dropdown-menu.vue"]]);const qt=Ee(at,{DropdownItem:wo,DropdownMenu:Eo}),Zt=ue(wo),Qt=ue(Eo);let bt=class{constructor(n,t){this.parent=n,this.domNode=t,this.subIndex=0,this.subIndex=0,this.init()}init(){this.subMenuItems=this.domNode.querySelectorAll("li"),this.addListeners()}gotoSubIndex(n){n===this.subMenuItems.length?n=0:n<0&&(n=this.subMenuItems.length-1),this.subMenuItems[n].focus(),this.subIndex=n}addListeners(){const n=this.parent.domNode;Array.prototype.forEach.call(this.subMenuItems,t=>{t.addEventListener("keydown",o=>{let s=!1;switch(o.code){case C.down:{this.gotoSubIndex(this.subIndex+1),s=!0;break}case C.up:{this.gotoSubIndex(this.subIndex-1),s=!0;break}case C.tab:{ge(n,"mouseleave");break}case C.enter:case C.space:{s=!0,o.currentTarget.click();break}}return s&&(o.preventDefault(),o.stopPropagation()),!1})})}},gt=class{constructor(n,t){this.domNode=n,this.submenu=null,this.submenu=null,this.init(t)}init(n){this.domNode.setAttribute("tabindex","0");const t=this.domNode.querySelector(`.${n}-menu`);t&&(this.submenu=new bt(this,t)),this.addListeners()}addListeners(){this.domNode.addEventListener("keydown",n=>{let t=!1;switch(n.code){case C.down:{ge(n.currentTarget,"mouseenter"),this.submenu&&this.submenu.gotoSubIndex(0),t=!0;break}case C.up:{ge(n.currentTarget,"mouseenter"),this.submenu&&this.submenu.gotoSubIndex(this.submenu.subMenuItems.length-1),t=!0;break}case C.tab:{ge(n.currentTarget,"mouseleave");break}case C.enter:case C.space:{t=!0,n.currentTarget.click();break}}t&&n.preventDefault()})}},ht=class{constructor(n,t){this.domNode=n,this.init(t)}init(n){const t=this.domNode.childNodes;Array.from(t).forEach(o=>{o.nodeType===1&&new gt(o,n)})}};const yt=$({name:"ElMenuCollapseTransition",setup(){const e=G("menu");return{listeners:{onBeforeEnter:t=>t.style.opacity="0.2",onEnter(t,o){be(t,`${e.namespace.value}-opacity-transition`),t.style.opacity="1",o()},onAfterEnter(t){Se(t,`${e.namespace.value}-opacity-transition`),t.style.opacity=""},onBeforeLeave(t){t.dataset||(t.dataset={}),Ao(t,e.m("collapse"))?(Se(t,e.m("collapse")),t.dataset.oldOverflow=t.style.overflow,t.dataset.scrollWidth=t.clientWidth.toString(),be(t,e.m("collapse"))):(be(t,e.m("collapse")),t.dataset.oldOverflow=t.style.overflow,t.dataset.scrollWidth=t.clientWidth.toString(),Se(t,e.m("collapse"))),t.style.width=`${t.scrollWidth}px`,t.style.overflow="hidden"},onLeave(t){be(t,"horizontal-collapse-transition"),t.style.width=`${t.dataset.scrollWidth}px`}}}}});function It(e,n,t,o,s,f){return T(),te(lo,ie({mode:"out-in"},e.listeners),{default:O(()=>[k(e.$slots,"default")]),_:3},16)}var wt=V(yt,[["render",It],["__file","/home/runner/work/element-plus/element-plus/packages/components/menu/src/menu-collapse-transition.vue"]]);function Co(e,n){const t=h(()=>{let s=e.parent;const f=[n.value];for(;s.type.name!=="ElMenu";)s.props.index&&f.unshift(s.props.index),s=s.parent;return f});return{parentMenu:h(()=>{let s=e.parent;for(;s&&!["ElMenu","ElSubMenu"].includes(s.type.name);)s=s.parent;return s}),indexPath:t}}function Et(e){return h(()=>{const t=e.backgroundColor;return t?new Jo(t).shade(20).toString():""})}const _o=(e,n)=>{const t=G("menu");return h(()=>t.cssVarBlock({"text-color":e.textColor||"","hover-text-color":e.textColor||"","bg-color":e.backgroundColor||"","hover-bg-color":Et(e).value||"","active-color":e.activeTextColor||"",level:`${n}`}))},Ct=ee({index:{type:String,required:!0},showTimeout:{type:Number,default:300},hideTimeout:{type:Number,default:300},popperClass:String,disabled:Boolean,popperAppendToBody:{type:Boolean,default:void 0},popperOffset:{type:Number,default:6},expandCloseIcon:{type:de},expandOpenIcon:{type:de},collapseCloseIcon:{type:de},collapseOpenIcon:{type:de}}),ke="ElSubMenu";var He=$({name:ke,props:Ct,setup(e,{slots:n,expose:t}){const o=ve(),{indexPath:s,parentMenu:f}=Co(o,h(()=>e.index)),a=G("menu"),i=G("sub-menu"),l=B("rootMenu");l||we(ke,"can not inject root menu");const r=B(`subMenu:${f.value.uid}`);r||we(ke,"can not inject sub menu");const p=E({}),u=E({});let m;const d=E(!1),y=E(),I=E(null),_=h(()=>v.value==="horizontal"&&R.value?"bottom-start":"right-start"),H=h(()=>v.value==="horizontal"&&R.value||v.value==="vertical"&&!l.props.collapse?e.expandCloseIcon&&e.expandOpenIcon?S.value?e.expandOpenIcon:e.expandCloseIcon:oo:e.collapseCloseIcon&&e.collapseOpenIcon?S.value?e.collapseOpenIcon:e.collapseCloseIcon:zo),R=h(()=>r.level===0),J=h(()=>e.popperAppendToBody===void 0?R.value:Boolean(e.popperAppendToBody)),N=h(()=>l.props.collapse?`${a.namespace.value}-zoom-in-left`:`${a.namespace.value}-zoom-in-top`),Y=h(()=>v.value==="horizontal"&&R.value?["bottom-start","bottom-end","top-start","top-end","right-start","left-start"]:["right-start","left-start","bottom-start","bottom-end","top-start","top-end"]),S=h(()=>l.openedMenus.includes(e.index)),U=h(()=>{let M=!1;return Object.values(p.value).forEach(A=>{A.active&&(M=!0)}),Object.values(u.value).forEach(A=>{A.active&&(M=!0)}),M}),q=h(()=>l.props.backgroundColor||""),P=h(()=>l.props.activeTextColor||""),b=h(()=>l.props.textColor||""),v=h(()=>l.props.mode),g=_e({index:e.index,indexPath:s,active:U}),z=h(()=>v.value!=="horizontal"?{color:b.value}:{borderBottomColor:U.value?l.props.activeTextColor?P.value:"":"transparent",color:U.value?P.value:b.value}),F=()=>{var M,A,j;return(j=(A=(M=I.value)==null?void 0:M.popperRef)==null?void 0:A.popperInstanceRef)==null?void 0:j.destroy()},w=M=>{M||F()},Q=()=>{l.props.menuTrigger==="hover"&&l.props.mode==="horizontal"||l.props.collapse&&l.props.mode==="vertical"||e.disabled||l.handleSubMenuClick({index:e.index,indexPath:s.value,active:U.value})},X=(M,A=e.showTimeout)=>{var j;M.type!=="focus"&&(l.props.menuTrigger==="click"&&l.props.mode==="horizontal"||!l.props.collapse&&l.props.mode==="vertical"||e.disabled||(r.mouseInChild.value=!0,m==null||m(),{stop:m}=je(()=>{l.openMenu(e.index,s.value)},A),J.value&&((j=f.value.vnode.el)==null||j.dispatchEvent(new MouseEvent("mouseenter")))))},oe=(M=!1)=>{var A,j;l.props.menuTrigger==="click"&&l.props.mode==="horizontal"||!l.props.collapse&&l.props.mode==="vertical"||(m==null||m(),r.mouseInChild.value=!1,{stop:m}=je(()=>!d.value&&l.closeMenu(e.index,s.value),e.hideTimeout),J.value&&M&&((A=o.parent)==null?void 0:A.type.name)==="ElSubMenu"&&((j=r.handleMouseleave)==null||j.call(r,!0)))};re(()=>l.props.collapse,M=>w(Boolean(M)));{const M=j=>{u.value[j.index]=j},A=j=>{delete u.value[j.index]};se(`subMenu:${o.uid}`,{addSubMenu:M,removeSubMenu:A,handleMouseleave:oe,mouseInChild:d,level:r.level+1})}return t({opened:S}),fe(()=>{l.addSubMenu(g),r.addSubMenu(g)}),Ae(()=>{r.removeSubMenu(g),l.removeSubMenu(g)}),()=>{var M;const A=[(M=n.title)==null?void 0:M.call(n),L(me,{class:i.e("icon-arrow"),style:{transform:S.value?e.expandCloseIcon&&e.expandOpenIcon||e.collapseCloseIcon&&e.collapseOpenIcon&&l.props.collapse?"none":"rotateZ(180deg)":"none"}},{default:()=>ae(H.value)?L(o.appContext.components[H.value]):L(H.value)})],j=_o(l.props,r.level+1),$o=l.isMenuPopup?L(De,{ref:I,visible:S.value,effect:"light",pure:!0,offset:e.popperOffset,showArrow:!1,persistent:!0,popperClass:e.popperClass,placement:_.value,teleported:J.value,fallbackPlacements:Y.value,transition:N.value,gpuAcceleration:!1},{content:()=>{var pe;return L("div",{class:[a.m(v.value),a.m("popup-container"),e.popperClass],onMouseenter:$e=>X($e,100),onMouseleave:()=>oe(!0),onFocus:$e=>X($e,100)},[L("ul",{class:[a.b(),a.m("popup"),a.m(`popup-${_.value}`)],style:j.value},[(pe=n.default)==null?void 0:pe.call(n)])])},default:()=>L("div",{class:i.e("title"),style:[z.value,{backgroundColor:q.value}],onClick:Q},A)}):L(Me,{},[L("div",{class:i.e("title"),style:[z.value,{backgroundColor:q.value}],ref:y,onClick:Q},A),L(fo,{},{default:()=>{var pe;return Re(L("ul",{role:"menu",class:[a.b(),a.m("inline")],style:j.value},[(pe=n.default)==null?void 0:pe.call(n)]),[[Ho,S.value]])}})]);return L("li",{class:[i.b(),i.is("active",U.value),i.is("opened",S.value),i.is("disabled",e.disabled)],role:"menuitem",ariaHaspopup:!0,ariaExpanded:S.value,onMouseenter:X,onMouseleave:()=>oe(!0),onFocus:X},[$o])}}});const _t=ee({mode:{type:String,values:["horizontal","vertical"],default:"vertical"},defaultActive:{type:String,default:""},defaultOpeneds:{type:W(Array),default:()=>en([])},uniqueOpened:Boolean,router:Boolean,menuTrigger:{type:String,values:["hover","click"],default:"hover"},collapse:Boolean,backgroundColor:String,textColor:String,activeTextColor:String,collapseTransition:{type:Boolean,default:!0},ellipsis:{type:Boolean,default:!0},popperEffect:{type:String,values:["dark","light"],default:"dark"}}),Oe=e=>Array.isArray(e)&&e.every(n=>ae(n)),Mt={close:(e,n)=>ae(e)&&Oe(n),open:(e,n)=>ae(e)&&Oe(n),select:(e,n,t,o)=>ae(e)&&Oe(n)&&Ko(t)&&(o===void 0||o instanceof Promise)};var Tt=$({name:"ElMenu",props:_t,emits:Mt,setup(e,{emit:n,slots:t,expose:o}){const s=ve(),f=s.appContext.config.globalProperties.$router,a=E(),i=G("menu"),l=G("sub-menu"),r=E(-1),p=E(e.defaultOpeneds&&!e.collapse?e.defaultOpeneds.slice(0):[]),u=E(e.defaultActive),m=E({}),d=E({}),y=h(()=>e.mode==="horizontal"||e.mode==="vertical"&&e.collapse),I=()=>{const b=u.value&&m.value[u.value];if(!b||e.mode==="horizontal"||e.collapse)return;b.indexPath.forEach(g=>{const z=d.value[g];z&&_(g,z.indexPath)})},_=(b,v)=>{p.value.includes(b)||(e.uniqueOpened&&(p.value=p.value.filter(g=>v.includes(g))),p.value.push(b),n("open",b,v))},H=(b,v)=>{const g=p.value.indexOf(b);g!==-1&&p.value.splice(g,1),n("close",b,v)},R=({index:b,indexPath:v})=>{p.value.includes(b)?H(b,v):_(b,v)},J=b=>{(e.mode==="horizontal"||e.collapse)&&(p.value=[]);const{index:v,indexPath:g}=b;if(!(v===void 0||g===void 0))if(e.router&&f){const z=b.route||v,F=f.push(z).then(w=>(w||(u.value=v),w));n("select",v,g,{index:v,indexPath:g,route:z},F)}else u.value=v,n("select",v,g,{index:v,indexPath:g})},N=b=>{const v=m.value,g=v[b]||u.value&&v[u.value]||v[e.defaultActive];g?u.value=g.index:u.value=b},Y=()=>{var b,v;if(!a.value)return-1;const g=Array.from((v=(b=a.value)==null?void 0:b.childNodes)!=null?v:[]).filter(M=>M.nodeName!=="#text"||M.nodeValue),z=64,F=Number.parseInt(getComputedStyle(a.value).paddingLeft,10),w=Number.parseInt(getComputedStyle(a.value).paddingRight,10),Q=a.value.clientWidth-F-w;let X=0,oe=0;return g.forEach((M,A)=>{X+=M.offsetWidth||0,X<=Q-z&&(oe=A+1)}),oe===g.length?-1:oe},S=(b,v=33.34)=>{let g;return()=>{g&&clearTimeout(g),g=setTimeout(()=>{b()},v)}};let U=!0;const q=()=>{const b=()=>{r.value=-1,Ce(()=>{r.value=Y()})};U?b():S(b)(),U=!1};re(()=>e.defaultActive,b=>{m.value[b]||(u.value=""),N(b)}),re(()=>e.collapse,b=>{b&&(p.value=[])}),re(m.value,I);let P;Lo(()=>{e.mode==="horizontal"&&e.ellipsis?P=Uo(a,q).stop:P==null||P()});{const b=F=>{d.value[F.index]=F},v=F=>{delete d.value[F.index]};se("rootMenu",_e({props:e,openedMenus:p,items:m,subMenus:d,activeIndex:u,isMenuPopup:y,addMenuItem:F=>{m.value[F.index]=F},removeMenuItem:F=>{delete m.value[F.index]},addSubMenu:b,removeSubMenu:v,openMenu:_,closeMenu:H,handleMenuItemClick:J,handleSubMenuClick:R})),se(`subMenu:${s.uid}`,{addSubMenu:b,removeSubMenu:v,mouseInChild:E(!1),level:0})}return fe(()=>{e.mode==="horizontal"&&new ht(s.vnode.el,i.namespace.value)}),o({open:v=>{const{indexPath:g}=d.value[v];g.forEach(z=>_(z,g))},close:H,handleResize:q}),()=>{var b,v;let g=(v=(b=t.default)==null?void 0:b.call(t))!=null?v:[];const z=[];if(e.mode==="horizontal"&&a.value){const Q=Xo(g),X=r.value===-1?Q:Q.slice(0,r.value),oe=r.value===-1?[]:Q.slice(r.value);(oe==null?void 0:oe.length)&&e.ellipsis&&(g=X,z.push(L(He,{index:"sub-menu-more",class:l.e("hide-arrow")},{title:()=>L(me,{class:l.e("icon-more")},{default:()=>L(Do)}),default:()=>oe})))}const F=_o(e,0),w=L("ul",{key:String(e.collapse),role:"menubar",ref:a,style:F.value,class:{[i.b()]:!0,[i.m(e.mode)]:!0,[i.m("collapse")]:e.collapse}},[...g,...z]);return e.collapseTransition&&e.mode==="vertical"?L(wt,()=>w):w}}});const $t=ee({index:{type:W([String,null]),default:null},route:{type:W([String,Object])},disabled:Boolean}),St={click:e=>ae(e.index)&&Array.isArray(e.indexPath)},Ne="ElMenuItem",kt=$({name:Ne,components:{ElTooltip:De},props:$t,emits:St,setup(e,{emit:n}){const t=ve(),o=B("rootMenu"),s=G("menu"),f=G("menu-item");o||we(Ne,"can not inject root menu");const{parentMenu:a,indexPath:i}=Co(t,ce(e,"index")),l=B(`subMenu:${a.value.uid}`);l||we(Ne,"can not inject sub menu");const r=h(()=>e.index===o.activeIndex),p=_e({index:e.index,indexPath:i,active:r}),u=()=>{e.disabled||(o.handleMenuItemClick({index:e.index,indexPath:i.value,route:e.route}),n("click",p))};return fe(()=>{l.addSubMenu(p),o.addMenuItem(p)}),Ae(()=>{l.removeSubMenu(p),o.removeMenuItem(p)}),{parentMenu:a,rootMenu:o,active:r,nsMenu:s,nsMenuItem:f,handleClick:u}}});function Ot(e,n,t,o,s,f){const a=D("el-tooltip");return T(),Z("li",{class:K([e.nsMenuItem.b(),e.nsMenuItem.is("active",e.active),e.nsMenuItem.is("disabled",e.disabled)]),role:"menuitem",tabindex:"-1",onClick:n[0]||(n[0]=(...i)=>e.handleClick&&e.handleClick(...i))},[e.parentMenu.type.name==="ElMenu"&&e.rootMenu.props.collapse&&e.$slots.title?(T(),te(a,{key:0,effect:e.rootMenu.props.popperEffect,placement:"right","fallback-placements":["left"],persistent:""},{content:O(()=>[k(e.$slots,"title")]),default:O(()=>[le("div",{class:K(e.nsMenu.be("tooltip","trigger"))},[k(e.$slots,"default")],2)]),_:3},8,["effect"])):(T(),Z(Me,{key:1},[k(e.$slots,"default"),k(e.$slots,"title")],64))],2)}var Mo=V(kt,[["render",Ot],["__file","/home/runner/work/element-plus/element-plus/packages/components/menu/src/menu-item.vue"]]);const Nt={title:String},Bt="ElMenuItemGroup",Rt=$({name:Bt,props:Nt,setup(){return{ns:G("menu-item-group")}}});function Pt(e,n,t,o,s,f){return T(),Z("li",{class:K(e.ns.b())},[le("div",{class:K(e.ns.e("title"))},[e.$slots.title?k(e.$slots,"title",{key:1}):(T(),Z(Me,{key:0},[Pe(Fe(e.title),1)],64))],2),le("ul",null,[k(e.$slots,"default")])],2)}var To=V(Rt,[["render",Pt],["__file","/home/runner/work/element-plus/element-plus/packages/components/menu/src/menu-item-group.vue"]]);const ol=Ee(Tt,{MenuItem:Mo,MenuItemGroup:To,SubMenu:He}),nl=ue(Mo),tl=ue(To);ue(He);export{jt as E,fo as _,Ut as a,Jt as b,qt as c,qn as d,Zt as e,Qt as f,ol as g,nl as h,tl as i,Wt as j,Yt as k};
