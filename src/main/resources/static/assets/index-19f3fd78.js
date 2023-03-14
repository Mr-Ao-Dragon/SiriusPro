import{c as ee,a7 as So,e as ye,R as de,d as W,u as G,g as Qe,E as me,_ as V,w as Ee,r as Ze,f as je,v as Xe,j as ko,C as ue,y as xe,ag as ge,af as Se,H as Oo,B as No,ad as Ue,ax as Bo}from"./index-6295fd1d.js";import{g as $,b as E,h,A as ae,w as re,o as T,c as Q,l as Ie,u as c,p as te,q as O,v as eo,k,n as K,K as B,j as le,t as Re,i as oo,y as Pe,z as Fe,H as Ce,d as fe,L as se,Q as _e,a1 as Ro,E as ie,as as Po,O as Ae,ao as Fo,Y as ce,r as D,a as x,M as Ao,a2 as zo,P as ve,G as Lo,x as Be,F as Me,S as L,e as Do,B as Ko}from"./runtime-core.esm-bundler-a054f4a5.js";import{q as no,d as to,e as Go,T as lo,E as C,c as Vo,t as we,v as Ho,m as jo,f as Uo}from"./index-61f8e38d.js";import{U as ze,C as Yo}from"./event-9519ab40.js";import{c as Le,u as Wo,b as Jo}from"./index-3df48e54.js";import{E as so,T as qo}from"./index-5843c3e2.js";import{e as ne,b as Qo,u as Ye,E as De,O as Zo,w as We,F as Xo}from"./index-ee0e1e3b.js";import{E as xo}from"./index-ce00a3bf.js";import{u as en}from"./index-76d982ae.js";import{k as ao,t as be,f as on}from"./index-3ed31bdd.js";const ro=Symbol("radioGroupKey"),nn=ee({size:{type:[Number,String],values:So,default:"",validator:e=>ye(e)},shape:{type:String,values:["circle","square"],default:"circle"},icon:{type:de},src:{type:String,default:""},alt:String,srcSet:String,fit:{type:W(String),default:"cover"}}),tn={error:e=>e instanceof Event},ln=["src","alt","srcset"],sn=$({name:"ElAvatar"}),an=$({...sn,props:nn,emits:tn,setup(e,{emit:n}){const t=e,o=G("avatar"),s=E(!1),f=h(()=>{const{size:r,icon:p,shape:u}=t,m=[o.b()];return ae(r)&&m.push(o.m(r)),p&&m.push(o.m("icon")),u&&m.push(o.m(u)),m}),a=h(()=>{const{size:r}=t;return ye(r)?o.cssVarBlock({size:Qe(r)||""}):void 0}),i=h(()=>({objectFit:t.fit}));re(()=>t.src,()=>s.value=!1);function l(r){s.value=!0,n("error",r)}return(r,p)=>(T(),Q("span",{class:K(c(f)),style:Ie(c(a))},[(r.src||r.srcSet)&&!s.value?(T(),Q("img",{key:0,src:r.src,alt:r.alt,srcset:r.srcSet,style:Ie(c(i)),onError:l},null,44,ln)):r.icon?(T(),te(c(me),{key:1},{default:O(()=>[(T(),te(eo(r.icon)))]),_:1})):k(r.$slots,"default",{key:2})],6))}});var rn=V(an,[["__file","/home/runner/work/element-plus/element-plus/packages/components/avatar/src/avatar.vue"]]);const Ut=Ee(rn),io=ee({size:Ze,disabled:Boolean,label:{type:[String,Number,Boolean],default:""}}),un=ee({...io,modelValue:{type:[String,Number,Boolean],default:""},name:{type:String,default:""},border:Boolean}),uo={[ze]:e=>ae(e)||ye(e)||je(e),[Yo]:e=>ae(e)||ye(e)||je(e)},co=(e,n)=>{const t=E(),o=B(ro,void 0),s=h(()=>!!o),f=h({get(){return s.value?o.modelValue:e.modelValue},set(p){s.value?o.changeEvent(p):n&&n(ze,p),t.value.checked=e.modelValue===e.label}}),a=Xe(h(()=>o==null?void 0:o.size)),i=ko(h(()=>o==null?void 0:o.disabled)),l=E(!1),r=h(()=>i.value||s.value&&f.value!==e.label?-1:0);return{radioRef:t,isGroup:s,radioGroup:o,focus:l,size:a,disabled:i,tabIndex:r,modelValue:f}},dn=["value","name","disabled"],cn=$({name:"ElRadio"}),pn=$({...cn,props:un,emits:uo,setup(e,{emit:n}){const t=e,o=G("radio"),{radioRef:s,radioGroup:f,focus:a,size:i,disabled:l,modelValue:r}=co(t,n);function p(){Ce(()=>n("change",r.value))}return(u,m)=>{var d;return T(),Q("label",{class:K([c(o).b(),c(o).is("disabled",c(l)),c(o).is("focus",c(a)),c(o).is("bordered",u.border),c(o).is("checked",c(r)===u.label),c(o).m(c(i))])},[le("span",{class:K([c(o).e("input"),c(o).is("disabled",c(l)),c(o).is("checked",c(r)===u.label)])},[Re(le("input",{ref_key:"radioRef",ref:s,"onUpdate:modelValue":m[0]||(m[0]=y=>oo(r)?r.value=y:null),class:K(c(o).e("original")),value:u.label,name:u.name||((d=c(f))==null?void 0:d.name),disabled:c(l),type:"radio",onFocus:m[1]||(m[1]=y=>a.value=!0),onBlur:m[2]||(m[2]=y=>a.value=!1),onChange:p},null,42,dn),[[no,c(r)]]),le("span",{class:K(c(o).e("inner"))},null,2)],2),le("span",{class:K(c(o).e("label")),onKeydown:m[3]||(m[3]=to(()=>{},["stop"]))},[k(u.$slots,"default",{},()=>[Pe(Fe(u.label),1)])],34)],2)}}});var mn=V(pn,[["__file","/home/runner/work/element-plus/element-plus/packages/components/radio/src/radio.vue"]]);const fn=ee({...io,name:{type:String,default:""}}),vn=["value","name","disabled"],gn=$({name:"ElRadioButton"}),bn=$({...gn,props:fn,setup(e){const n=e,t=G("radio"),{radioRef:o,focus:s,size:f,disabled:a,modelValue:i,radioGroup:l}=co(n),r=h(()=>({backgroundColor:(l==null?void 0:l.fill)||"",borderColor:(l==null?void 0:l.fill)||"",boxShadow:l!=null&&l.fill?`-1px 0 0 0 ${l.fill}`:"",color:(l==null?void 0:l.textColor)||""}));return(p,u)=>{var m;return T(),Q("label",{class:K([c(t).b("button"),c(t).is("active",c(i)===p.label),c(t).is("disabled",c(a)),c(t).is("focus",c(s)),c(t).bm("button",c(f))])},[Re(le("input",{ref_key:"radioRef",ref:o,"onUpdate:modelValue":u[0]||(u[0]=d=>oo(i)?i.value=d:null),class:K(c(t).be("button","original-radio")),value:p.label,type:"radio",name:p.name||((m=c(l))==null?void 0:m.name),disabled:c(a),onFocus:u[1]||(u[1]=d=>s.value=!0),onBlur:u[2]||(u[2]=d=>s.value=!1)},null,42,vn),[[no,c(i)]]),le("span",{class:K(c(t).be("button","inner")),style:Ie(c(i)===p.label?c(r):{}),onKeydown:u[3]||(u[3]=to(()=>{},["stop"]))},[k(p.$slots,"default",{},()=>[Pe(Fe(p.label),1)])],38)],2)}}});var po=V(bn,[["__file","/home/runner/work/element-plus/element-plus/packages/components/radio/src/radio-button.vue"]]);const hn=ee({id:{type:String,default:void 0},size:Ze,disabled:Boolean,modelValue:{type:[String,Number,Boolean],default:""},fill:{type:String,default:""},label:{type:String,default:void 0},textColor:{type:String,default:""},name:{type:String,default:void 0},validateEvent:{type:Boolean,default:!0}}),yn=uo,In=["id","aria-label","aria-labelledby"],wn=$({name:"ElRadioGroup"}),En=$({...wn,props:hn,emits:yn,setup(e,{emit:n}){const t=e,o=G("radio"),s=Le(),f=E(),{formItem:a}=Wo(),{inputId:i,isLabeledByFormItem:l}=Jo(t,{formItemContext:a}),r=u=>{n(ze,u),Ce(()=>n("change",u))};fe(()=>{const u=f.value.querySelectorAll("[type=radio]"),m=u[0];!Array.from(u).some(d=>d.checked)&&m&&(m.tabIndex=0)});const p=h(()=>t.name||s.value);return se(ro,_e({...Ro(t),changeEvent:r,name:p})),re(()=>t.modelValue,()=>{t.validateEvent&&(a==null||a.validate("change").catch(u=>Go()))}),(u,m)=>(T(),Q("div",{id:c(i),ref_key:"radioGroupRef",ref:f,class:K(c(o).b("group")),role:"radiogroup","aria-label":c(l)?void 0:u.label||"radio-group","aria-labelledby":c(l)?c(a).labelId:void 0},[k(u.$slots,"default")],10,In))}});var mo=V(En,[["__file","/home/runner/work/element-plus/element-plus/packages/components/radio/src/radio-group.vue"]]);const Yt=Ee(mn,{RadioButton:po,RadioGroup:mo}),Wt=ue(mo),Jt=ue(po),Cn=$({name:"ElCollapseTransition"}),_n=$({...Cn,setup(e){const n=G("collapse-transition"),t={beforeEnter(o){o.dataset||(o.dataset={}),o.dataset.oldPaddingTop=o.style.paddingTop,o.dataset.oldPaddingBottom=o.style.paddingBottom,o.style.maxHeight=0,o.style.paddingTop=0,o.style.paddingBottom=0},enter(o){o.dataset.oldOverflow=o.style.overflow,o.scrollHeight!==0?(o.style.maxHeight=`${o.scrollHeight}px`,o.style.paddingTop=o.dataset.oldPaddingTop,o.style.paddingBottom=o.dataset.oldPaddingBottom):(o.style.maxHeight=0,o.style.paddingTop=o.dataset.oldPaddingTop,o.style.paddingBottom=o.dataset.oldPaddingBottom),o.style.overflow="hidden"},afterEnter(o){o.style.maxHeight="",o.style.overflow=o.dataset.oldOverflow},beforeLeave(o){o.dataset||(o.dataset={}),o.dataset.oldPaddingTop=o.style.paddingTop,o.dataset.oldPaddingBottom=o.style.paddingBottom,o.dataset.oldOverflow=o.style.overflow,o.style.maxHeight=`${o.scrollHeight}px`,o.style.overflow="hidden"},leave(o){o.scrollHeight!==0&&(o.style.maxHeight=0,o.style.paddingTop=0,o.style.paddingBottom=0)},afterLeave(o){o.style.maxHeight="",o.style.overflow=o.dataset.oldOverflow,o.style.paddingTop=o.dataset.oldPaddingTop,o.style.paddingBottom=o.dataset.oldPaddingBottom}};return(o,s)=>(T(),te(lo,ie({name:c(n).b()},Po(t)),{default:O(()=>[k(o.$slots,"default")]),_:3},16,["name"]))}});var he=V(_n,[["__file","/home/runner/work/element-plus/element-plus/packages/components/collapse-transition/src/collapse-transition.vue"]]);he.install=e=>{e.component(he.name,he)};const fo=he,qt=fo,Mn=$({inheritAttrs:!1});function Tn(e,n,t,o,s,f){return k(e.$slots,"default")}var $n=V(Mn,[["render",Tn],["__file","/home/runner/work/element-plus/element-plus/packages/components/collection/src/collection.vue"]]);const Sn=$({name:"ElCollectionItem",inheritAttrs:!1});function kn(e,n,t,o,s,f){return k(e.$slots,"default")}var On=V(Sn,[["render",kn],["__file","/home/runner/work/element-plus/element-plus/packages/components/collection/src/collection-item.vue"]]);const vo="data-el-collection-item",go=e=>{const n=`El${e}Collection`,t=`${n}Item`,o=Symbol(n),s=Symbol(t),f={...$n,name:n,setup(){const i=E(null),l=new Map;se(o,{itemMap:l,getItems:()=>{const p=c(i);if(!p)return[];const u=Array.from(p.querySelectorAll(`[${vo}]`));return[...l.values()].sort((d,y)=>u.indexOf(d.ref)-u.indexOf(y.ref))},collectionRef:i})}},a={...On,name:t,setup(i,{attrs:l}){const r=E(null),p=B(o,void 0);se(s,{collectionItemRef:r}),fe(()=>{const u=c(r);u&&p.itemMap.set(u,{ref:u,...l})}),Ae(()=>{const u=c(r);p.itemMap.delete(u)})}};return{COLLECTION_INJECTION_KEY:o,COLLECTION_ITEM_INJECTION_KEY:s,ElCollection:f,ElCollectionItem:a}},Nn=ee({style:{type:W([String,Array,Object])},currentTabId:{type:W(String)},defaultCurrentTabId:String,loop:Boolean,dir:{type:String,values:["ltr","rtl"],default:"ltr"},orientation:{type:W(String)},onBlur:Function,onFocus:Function,onMousedown:Function}),{ElCollection:Bn,ElCollectionItem:Rn,COLLECTION_INJECTION_KEY:Ke,COLLECTION_ITEM_INJECTION_KEY:Pn}=go("RovingFocusGroup"),Ge=Symbol("elRovingFocusGroup"),bo=Symbol("elRovingFocusGroupItem"),Fn={ArrowLeft:"prev",ArrowUp:"prev",ArrowRight:"next",ArrowDown:"next",PageUp:"first",Home:"first",PageDown:"last",End:"last"},An=(e,n)=>{if(n!=="rtl")return e;switch(e){case C.right:return C.left;case C.left:return C.right;default:return e}},zn=(e,n,t)=>{const o=An(e.key,t);if(!(n==="vertical"&&[C.left,C.right].includes(o))&&!(n==="horizontal"&&[C.up,C.down].includes(o)))return Fn[o]},Ln=(e,n)=>e.map((t,o)=>e[(o+n)%e.length]),Ve=e=>{const{activeElement:n}=document;for(const t of e)if(t===n||(t.focus(),n!==document.activeElement))return},Je="currentTabIdChange",qe="rovingFocusGroup.entryFocus",Dn={bubbles:!1,cancelable:!0},Kn=$({name:"ElRovingFocusGroupImpl",inheritAttrs:!1,props:Nn,emits:[Je,"entryFocus"],setup(e,{emit:n}){var t;const o=E((t=e.currentTabId||e.defaultCurrentTabId)!=null?t:null),s=E(!1),f=E(!1),a=E(null),{getItems:i}=B(Ke,void 0),l=h(()=>[{outline:"none"},e.style]),r=I=>{n(Je,I)},p=()=>{s.value=!0},u=ne(I=>{var _;(_=e.onMousedown)==null||_.call(e,I)},()=>{f.value=!0}),m=ne(I=>{var _;(_=e.onFocus)==null||_.call(e,I)},I=>{const _=!c(f),{target:H,currentTarget:R}=I;if(H===R&&_&&!c(s)){const J=new Event(qe,Dn);if(R==null||R.dispatchEvent(J),!J.defaultPrevented){const N=i().filter(P=>P.focusable),Y=N.find(P=>P.active),S=N.find(P=>P.id===c(o)),q=[Y,S,...N].filter(Boolean).map(P=>P.ref);Ve(q)}}f.value=!1}),d=ne(I=>{var _;(_=e.onBlur)==null||_.call(e,I)},()=>{s.value=!1}),y=(...I)=>{n("entryFocus",...I)};se(Ge,{currentTabbedId:Fo(o),loop:ce(e,"loop"),tabIndex:h(()=>c(s)?-1:0),rovingFocusGroupRef:a,rovingFocusGroupRootStyle:l,orientation:ce(e,"orientation"),dir:ce(e,"dir"),onItemFocus:r,onItemShiftTab:p,onBlur:d,onFocus:m,onMousedown:u}),re(()=>e.currentTabId,I=>{o.value=I??null}),Vo(a,qe,y)}});function Gn(e,n,t,o,s,f){return k(e.$slots,"default")}var Vn=V(Kn,[["render",Gn],["__file","/home/runner/work/element-plus/element-plus/packages/components/roving-focus-group/src/roving-focus-group-impl.vue"]]);const Hn=$({name:"ElRovingFocusGroup",components:{ElFocusGroupCollection:Bn,ElRovingFocusGroupImpl:Vn}});function jn(e,n,t,o,s,f){const a=D("el-roving-focus-group-impl"),i=D("el-focus-group-collection");return T(),te(i,null,{default:O(()=>[x(a,Ao(zo(e.$attrs)),{default:O(()=>[k(e.$slots,"default")]),_:3},16)]),_:3})}var Un=V(Hn,[["render",jn],["__file","/home/runner/work/element-plus/element-plus/packages/components/roving-focus-group/src/roving-focus-group.vue"]]);const Yn=$({components:{ElRovingFocusCollectionItem:Rn},props:{focusable:{type:Boolean,default:!0},active:{type:Boolean,default:!1}},emits:["mousedown","focus","keydown"],setup(e,{emit:n}){const{currentTabbedId:t,loop:o,onItemFocus:s,onItemShiftTab:f}=B(Ge,void 0),{getItems:a}=B(Ke,void 0),i=Le(),l=E(null),r=ne(d=>{n("mousedown",d)},d=>{e.focusable?s(c(i)):d.preventDefault()}),p=ne(d=>{n("focus",d)},()=>{s(c(i))}),u=ne(d=>{n("keydown",d)},d=>{const{key:y,shiftKey:I,target:_,currentTarget:H}=d;if(y===C.tab&&I){f();return}if(_!==H)return;const R=zn(d);if(R){d.preventDefault();let N=a().filter(Y=>Y.focusable).map(Y=>Y.ref);switch(R){case"last":{N.reverse();break}case"prev":case"next":{R==="prev"&&N.reverse();const Y=N.indexOf(H);N=o.value?Ln(N,Y+1):N.slice(Y+1);break}}Ce(()=>{Ve(N)})}}),m=h(()=>t.value===c(i));return se(bo,{rovingFocusGroupItemRef:l,tabIndex:h(()=>c(m)?0:-1),handleMousedown:r,handleFocus:p,handleKeydown:u}),{id:i,handleKeydown:u,handleFocus:p,handleMousedown:r}}});function Wn(e,n,t,o,s,f){const a=D("el-roving-focus-collection-item");return T(),te(a,{id:e.id,focusable:e.focusable,active:e.active},{default:O(()=>[k(e.$slots,"default")]),_:3},8,["id","focusable","active"])}var Jn=V(Yn,[["render",Wn],["__file","/home/runner/work/element-plus/element-plus/packages/components/roving-focus-group/src/roving-focus-item.vue"]]);const qn=ee({trigger:Qo.trigger,effect:{...Ye.effect,default:"light"},type:{type:W(String)},placement:{type:W(String),default:"bottom"},popperOptions:{type:W(Object),default:()=>({})},id:String,size:{type:String,default:""},splitButton:Boolean,hideOnClick:{type:Boolean,default:!0},loop:{type:Boolean,default:!0},showTimeout:{type:Number,default:150},hideTimeout:{type:Number,default:150},tabindex:{type:W([Number,String]),default:0},maxHeight:{type:W([Number,String]),default:""},popperClass:{type:String,default:""},disabled:{type:Boolean,default:!1},role:{type:String,default:"menu"},buttonProps:{type:W(Object)},teleported:Ye.teleported}),ho=ee({command:{type:[Object,String,Number],default:()=>({})},disabled:Boolean,divided:Boolean,textValue:String,icon:{type:de}}),Qn=ee({onKeydown:{type:W(Function)}}),Zn=[C.down,C.pageDown,C.home],yo=[C.up,C.pageUp,C.end],Xn=[...Zn,...yo],{ElCollection:xn,ElCollectionItem:et,COLLECTION_INJECTION_KEY:ot,COLLECTION_ITEM_INJECTION_KEY:nt}=go("Dropdown"),Te=Symbol("elDropdown"),{ButtonGroup:tt}=so,lt=$({name:"ElDropdown",components:{ElButton:so,ElButtonGroup:tt,ElScrollbar:xo,ElDropdownCollection:xn,ElTooltip:De,ElRovingFocusGroup:Un,ElOnlyChild:Zo,ElIcon:me,ArrowDown:xe},props:qn,emits:["visible-change","click","command"],setup(e,{emit:n}){const t=ve(),o=G("dropdown"),{t:s}=en(),f=E(),a=E(),i=E(null),l=E(null),r=E(null),p=E(null),u=E(!1),m=[C.enter,C.space,C.down],d=h(()=>({maxHeight:Qe(e.maxHeight)})),y=h(()=>[o.m(N.value)]),I=Le().value,_=h(()=>e.id||I);function H(){R()}function R(){var w;(w=i.value)==null||w.onClose()}function J(){var w;(w=i.value)==null||w.onOpen()}const N=Xe();function Y(...w){n("command",...w)}function S(){}function j(){const w=c(l);w==null||w.focus(),p.value=null}function q(w){p.value=w}function P(w){u.value||(w.preventDefault(),w.stopImmediatePropagation())}function g(){n("visible-change",!0)}function v(w){(w==null?void 0:w.type)==="keydown"&&l.value.focus()}function b(){n("visible-change",!1)}return se(Te,{contentRef:l,role:h(()=>e.role),triggerId:_,isUsingKeyboard:u,onItemEnter:S,onItemLeave:j}),se("elDropdown",{instance:t,dropdownSize:N,handleClick:H,commandHandler:Y,trigger:ce(e,"trigger"),hideOnClick:ce(e,"hideOnClick")}),{t:s,ns:o,scrollbar:r,wrapStyle:d,dropdownTriggerKls:y,dropdownSize:N,triggerId:_,triggerKeys:m,currentTabId:p,handleCurrentTabIdChange:q,handlerMainButtonClick:w=>{n("click",w)},handleEntryFocus:P,handleClose:R,handleOpen:J,handleBeforeShowTooltip:g,handleShowTooltip:v,handleBeforeHideTooltip:b,onFocusAfterTrapped:w=>{var Z,X;w.preventDefault(),(X=(Z=l.value)==null?void 0:Z.focus)==null||X.call(Z,{preventScroll:!0})},popperRef:i,contentRef:l,triggeringElementRef:f,referenceElementRef:a}}});function st(e,n,t,o,s,f){var a;const i=D("el-dropdown-collection"),l=D("el-roving-focus-group"),r=D("el-scrollbar"),p=D("el-only-child"),u=D("el-tooltip"),m=D("el-button"),d=D("arrow-down"),y=D("el-icon"),I=D("el-button-group");return T(),Q("div",{class:K([e.ns.b(),e.ns.is("disabled",e.disabled)])},[x(u,{ref:"popperRef",role:e.role,effect:e.effect,"fallback-placements":["bottom","top"],"popper-options":e.popperOptions,"gpu-acceleration":!1,"hide-after":e.trigger==="hover"?e.hideTimeout:0,"manual-mode":!0,placement:e.placement,"popper-class":[e.ns.e("popper"),e.popperClass],"reference-element":(a=e.referenceElementRef)==null?void 0:a.$el,trigger:e.trigger,"trigger-keys":e.triggerKeys,"trigger-target-el":e.contentRef,"show-after":e.trigger==="hover"?e.showTimeout:0,"stop-popper-mouse-event":!1,"virtual-ref":e.triggeringElementRef,"virtual-triggering":e.splitButton,disabled:e.disabled,transition:`${e.ns.namespace.value}-zoom-in-top`,teleported:e.teleported,pure:"",persistent:"",onBeforeShow:e.handleBeforeShowTooltip,onShow:e.handleShowTooltip,onBeforeHide:e.handleBeforeHideTooltip},Lo({content:O(()=>[x(r,{ref:"scrollbar","wrap-style":e.wrapStyle,tag:"div","view-class":e.ns.e("list")},{default:O(()=>[x(l,{loop:e.loop,"current-tab-id":e.currentTabId,orientation:"horizontal",onCurrentTabIdChange:e.handleCurrentTabIdChange,onEntryFocus:e.handleEntryFocus},{default:O(()=>[x(i,null,{default:O(()=>[k(e.$slots,"dropdown")]),_:3})]),_:3},8,["loop","current-tab-id","onCurrentTabIdChange","onEntryFocus"])]),_:3},8,["wrap-style","view-class"])]),_:2},[e.splitButton?void 0:{name:"default",fn:O(()=>[x(p,{id:e.triggerId,role:"button",tabindex:e.tabindex},{default:O(()=>[k(e.$slots,"default")]),_:3},8,["id","tabindex"])])}]),1032,["role","effect","popper-options","hide-after","placement","popper-class","reference-element","trigger","trigger-keys","trigger-target-el","show-after","virtual-ref","virtual-triggering","disabled","transition","teleported","onBeforeShow","onShow","onBeforeHide"]),e.splitButton?(T(),te(I,{key:0},{default:O(()=>[x(m,ie({ref:"referenceElementRef"},e.buttonProps,{size:e.dropdownSize,type:e.type,disabled:e.disabled,tabindex:e.tabindex,onClick:e.handlerMainButtonClick}),{default:O(()=>[k(e.$slots,"default")]),_:3},16,["size","type","disabled","tabindex","onClick"]),x(m,ie({id:e.triggerId,ref:"triggeringElementRef"},e.buttonProps,{role:"button",size:e.dropdownSize,type:e.type,class:e.ns.e("caret-button"),disabled:e.disabled,tabindex:e.tabindex,"aria-label":e.t("el.dropdown.toggleDropdown")}),{default:O(()=>[x(y,{class:K(e.ns.e("icon"))},{default:O(()=>[x(d)]),_:1},8,["class"])]),_:1},16,["id","size","type","class","disabled","tabindex","aria-label"])]),_:3})):Be("v-if",!0)],2)}var at=V(lt,[["render",st],["__file","/home/runner/work/element-plus/element-plus/packages/components/dropdown/src/dropdown.vue"]]);const rt=$({name:"DropdownItemImpl",components:{ElIcon:me},props:ho,emits:["pointermove","pointerleave","click","clickimpl"],setup(e,{emit:n}){const t=G("dropdown"),{role:o}=B(Te,void 0),{collectionItemRef:s}=B(nt,void 0),{collectionItemRef:f}=B(Pn,void 0),{rovingFocusGroupItemRef:a,tabIndex:i,handleFocus:l,handleKeydown:r,handleMousedown:p}=B(bo,void 0),u=ao(s,f,a),m=h(()=>o.value==="menu"?"menuitem":o.value==="navigation"?"link":"button"),d=ne(y=>{const{code:I}=y;if(I===C.enter||I===C.space)return y.preventDefault(),y.stopImmediatePropagation(),n("clickimpl",y),!0},r);return{ns:t,itemRef:u,dataset:{[vo]:""},role:m,tabIndex:i,handleFocus:l,handleKeydown:d,handleMousedown:p}}}),it=["aria-disabled","tabindex","role"];function ut(e,n,t,o,s,f){const a=D("el-icon");return T(),Q(Me,null,[e.divided?(T(),Q("li",ie({key:0,role:"separator",class:e.ns.bem("menu","item","divided")},e.$attrs),null,16)):Be("v-if",!0),le("li",ie({ref:e.itemRef},{...e.dataset,...e.$attrs},{"aria-disabled":e.disabled,class:[e.ns.be("menu","item"),e.ns.is("disabled",e.disabled)],tabindex:e.tabIndex,role:e.role,onClick:n[0]||(n[0]=i=>e.$emit("clickimpl",i)),onFocus:n[1]||(n[1]=(...i)=>e.handleFocus&&e.handleFocus(...i)),onKeydown:n[2]||(n[2]=(...i)=>e.handleKeydown&&e.handleKeydown(...i)),onMousedown:n[3]||(n[3]=(...i)=>e.handleMousedown&&e.handleMousedown(...i)),onPointermove:n[4]||(n[4]=i=>e.$emit("pointermove",i)),onPointerleave:n[5]||(n[5]=i=>e.$emit("pointerleave",i))}),[e.icon?(T(),te(a,{key:0},{default:O(()=>[(T(),te(eo(e.icon)))]),_:1})):Be("v-if",!0),k(e.$slots,"default")],16,it)],64)}var dt=V(rt,[["render",ut],["__file","/home/runner/work/element-plus/element-plus/packages/components/dropdown/src/dropdown-item-impl.vue"]]);const Io=()=>{const e=B("elDropdown",{}),n=h(()=>e==null?void 0:e.dropdownSize);return{elDropdown:e,_elDropdownSize:n}},ct=$({name:"ElDropdownItem",components:{ElDropdownCollectionItem:et,ElRovingFocusItem:Jn,ElDropdownItemImpl:dt},inheritAttrs:!1,props:ho,emits:["pointermove","pointerleave","click"],setup(e,{emit:n,attrs:t}){const{elDropdown:o}=Io(),s=ve(),f=E(null),a=h(()=>{var d,y;return(y=(d=c(f))==null?void 0:d.textContent)!=null?y:""}),{onItemEnter:i,onItemLeave:l}=B(Te,void 0),r=ne(d=>(n("pointermove",d),d.defaultPrevented),We(d=>{var y;e.disabled?l(d):(i(d),d.defaultPrevented||(y=d.currentTarget)==null||y.focus())})),p=ne(d=>(n("pointerleave",d),d.defaultPrevented),We(d=>{l(d)})),u=ne(d=>{if(!e.disabled)return n("click",d),d.type!=="keydown"&&d.defaultPrevented},d=>{var y,I,_;if(e.disabled){d.stopImmediatePropagation();return}(y=o==null?void 0:o.hideOnClick)!=null&&y.value&&((I=o.handleClick)==null||I.call(o)),(_=o.commandHandler)==null||_.call(o,e.command,s,d)}),m=h(()=>({...e,...t}));return{handleClick:u,handlePointerMove:r,handlePointerLeave:p,textContent:a,propsAndAttrs:m}}});function pt(e,n,t,o,s,f){var a;const i=D("el-dropdown-item-impl"),l=D("el-roving-focus-item"),r=D("el-dropdown-collection-item");return T(),te(r,{disabled:e.disabled,"text-value":(a=e.textValue)!=null?a:e.textContent},{default:O(()=>[x(l,{focusable:!e.disabled},{default:O(()=>[x(i,ie(e.propsAndAttrs,{onPointerleave:e.handlePointerLeave,onPointermove:e.handlePointerMove,onClickimpl:e.handleClick}),{default:O(()=>[k(e.$slots,"default")]),_:3},16,["onPointerleave","onPointermove","onClickimpl"])]),_:3},8,["focusable"])]),_:3},8,["disabled","text-value"])}var wo=V(ct,[["render",pt],["__file","/home/runner/work/element-plus/element-plus/packages/components/dropdown/src/dropdown-item.vue"]]);const mt=$({name:"ElDropdownMenu",props:Qn,setup(e){const n=G("dropdown"),{_elDropdownSize:t}=Io(),o=t.value,{focusTrapRef:s,onKeydown:f}=B(Xo,void 0),{contentRef:a,role:i,triggerId:l}=B(Te,void 0),{collectionRef:r,getItems:p}=B(ot,void 0),{rovingFocusGroupRef:u,rovingFocusGroupRootStyle:m,tabIndex:d,onBlur:y,onFocus:I,onMousedown:_}=B(Ge,void 0),{collectionRef:H}=B(Ke,void 0),R=h(()=>[n.b("menu"),n.bm("menu",o==null?void 0:o.value)]),J=ao(a,r,s,u,H),N=ne(S=>{var j;(j=e.onKeydown)==null||j.call(e,S)},S=>{const{currentTarget:j,code:q,target:P}=S;if(j.contains(P),C.tab===q&&S.stopImmediatePropagation(),S.preventDefault(),P!==c(a)||!Xn.includes(q))return;const v=p().filter(b=>!b.disabled).map(b=>b.ref);yo.includes(q)&&v.reverse(),Ve(v)});return{size:o,rovingFocusGroupRootStyle:m,tabIndex:d,dropdownKls:R,role:i,triggerId:l,dropdownListWrapperRef:J,handleKeydown:S=>{N(S),f(S)},onBlur:y,onFocus:I,onMousedown:_}}}),ft=["role","aria-labelledby"];function vt(e,n,t,o,s,f){return T(),Q("ul",{ref:e.dropdownListWrapperRef,class:K(e.dropdownKls),style:Ie(e.rovingFocusGroupRootStyle),tabindex:-1,role:e.role,"aria-labelledby":e.triggerId,onBlur:n[0]||(n[0]=(...a)=>e.onBlur&&e.onBlur(...a)),onFocus:n[1]||(n[1]=(...a)=>e.onFocus&&e.onFocus(...a)),onKeydown:n[2]||(n[2]=(...a)=>e.handleKeydown&&e.handleKeydown(...a)),onMousedown:n[3]||(n[3]=(...a)=>e.onMousedown&&e.onMousedown(...a))},[k(e.$slots,"default")],46,ft)}var Eo=V(mt,[["render",vt],["__file","/home/runner/work/element-plus/element-plus/packages/components/dropdown/src/dropdown-menu.vue"]]);const Qt=Ee(at,{DropdownItem:wo,DropdownMenu:Eo}),Zt=ue(wo),Xt=ue(Eo);let gt=class{constructor(n,t){this.parent=n,this.domNode=t,this.subIndex=0,this.subIndex=0,this.init()}init(){this.subMenuItems=this.domNode.querySelectorAll("li"),this.addListeners()}gotoSubIndex(n){n===this.subMenuItems.length?n=0:n<0&&(n=this.subMenuItems.length-1),this.subMenuItems[n].focus(),this.subIndex=n}addListeners(){const n=this.parent.domNode;Array.prototype.forEach.call(this.subMenuItems,t=>{t.addEventListener("keydown",o=>{let s=!1;switch(o.code){case C.down:{this.gotoSubIndex(this.subIndex+1),s=!0;break}case C.up:{this.gotoSubIndex(this.subIndex-1),s=!0;break}case C.tab:{be(n,"mouseleave");break}case C.enter:case C.space:{s=!0,o.currentTarget.click();break}}return s&&(o.preventDefault(),o.stopPropagation()),!1})})}},bt=class{constructor(n,t){this.domNode=n,this.submenu=null,this.submenu=null,this.init(t)}init(n){this.domNode.setAttribute("tabindex","0");const t=this.domNode.querySelector(`.${n}-menu`);t&&(this.submenu=new gt(this,t)),this.addListeners()}addListeners(){this.domNode.addEventListener("keydown",n=>{let t=!1;switch(n.code){case C.down:{be(n.currentTarget,"mouseenter"),this.submenu&&this.submenu.gotoSubIndex(0),t=!0;break}case C.up:{be(n.currentTarget,"mouseenter"),this.submenu&&this.submenu.gotoSubIndex(this.submenu.subMenuItems.length-1),t=!0;break}case C.tab:{be(n.currentTarget,"mouseleave");break}case C.enter:case C.space:{t=!0,n.currentTarget.click();break}}t&&n.preventDefault()})}},ht=class{constructor(n,t){this.domNode=n,this.init(t)}init(n){const t=this.domNode.childNodes;Array.from(t).forEach(o=>{o.nodeType===1&&new bt(o,n)})}};const yt=$({name:"ElMenuCollapseTransition",setup(){const e=G("menu");return{listeners:{onBeforeEnter:t=>t.style.opacity="0.2",onEnter(t,o){ge(t,`${e.namespace.value}-opacity-transition`),t.style.opacity="1",o()},onAfterEnter(t){Se(t,`${e.namespace.value}-opacity-transition`),t.style.opacity=""},onBeforeLeave(t){t.dataset||(t.dataset={}),Oo(t,e.m("collapse"))?(Se(t,e.m("collapse")),t.dataset.oldOverflow=t.style.overflow,t.dataset.scrollWidth=t.clientWidth.toString(),ge(t,e.m("collapse"))):(ge(t,e.m("collapse")),t.dataset.oldOverflow=t.style.overflow,t.dataset.scrollWidth=t.clientWidth.toString(),Se(t,e.m("collapse"))),t.style.width=`${t.scrollWidth}px`,t.style.overflow="hidden"},onLeave(t){ge(t,"horizontal-collapse-transition"),t.style.width=`${t.dataset.scrollWidth}px`}}}}});function It(e,n,t,o,s,f){return T(),te(lo,ie({mode:"out-in"},e.listeners),{default:O(()=>[k(e.$slots,"default")]),_:3},16)}var wt=V(yt,[["render",It],["__file","/home/runner/work/element-plus/element-plus/packages/components/menu/src/menu-collapse-transition.vue"]]);function Co(e,n){const t=h(()=>{let s=e.parent;const f=[n.value];for(;s.type.name!=="ElMenu";)s.props.index&&f.unshift(s.props.index),s=s.parent;return f});return{parentMenu:h(()=>{let s=e.parent;for(;s&&!["ElMenu","ElSubMenu"].includes(s.type.name);)s=s.parent;return s}),indexPath:t}}function Et(e){return h(()=>{const t=e.backgroundColor;return t?new qo(t).shade(20).toString():""})}const _o=(e,n)=>{const t=G("menu");return h(()=>t.cssVarBlock({"text-color":e.textColor||"","hover-text-color":e.textColor||"","bg-color":e.backgroundColor||"","hover-bg-color":Et(e).value||"","active-color":e.activeTextColor||"",level:`${n}`}))},Ct=ee({index:{type:String,required:!0},showTimeout:{type:Number,default:300},hideTimeout:{type:Number,default:300},popperClass:String,disabled:Boolean,popperAppendToBody:{type:Boolean,default:void 0},popperOffset:{type:Number,default:6},expandCloseIcon:{type:de},expandOpenIcon:{type:de},collapseCloseIcon:{type:de},collapseOpenIcon:{type:de}}),ke="ElSubMenu";var He=$({name:ke,props:Ct,setup(e,{slots:n,expose:t}){const o=ve(),{indexPath:s,parentMenu:f}=Co(o,h(()=>e.index)),a=G("menu"),i=G("sub-menu"),l=B("rootMenu");l||we(ke,"can not inject root menu");const r=B(`subMenu:${f.value.uid}`);r||we(ke,"can not inject sub menu");const p=E({}),u=E({});let m;const d=E(!1),y=E(),I=E(null),_=h(()=>v.value==="horizontal"&&R.value?"bottom-start":"right-start"),H=h(()=>v.value==="horizontal"&&R.value||v.value==="vertical"&&!l.props.collapse?e.expandCloseIcon&&e.expandOpenIcon?S.value?e.expandOpenIcon:e.expandCloseIcon:xe:e.collapseCloseIcon&&e.collapseOpenIcon?S.value?e.collapseOpenIcon:e.collapseCloseIcon:No),R=h(()=>r.level===0),J=h(()=>e.popperAppendToBody===void 0?R.value:Boolean(e.popperAppendToBody)),N=h(()=>l.props.collapse?`${a.namespace.value}-zoom-in-left`:`${a.namespace.value}-zoom-in-top`),Y=h(()=>v.value==="horizontal"&&R.value?["bottom-start","bottom-end","top-start","top-end","right-start","left-start"]:["right-start","left-start","bottom-start","bottom-end","top-start","top-end"]),S=h(()=>l.openedMenus.includes(e.index)),j=h(()=>{let M=!1;return Object.values(p.value).forEach(A=>{A.active&&(M=!0)}),Object.values(u.value).forEach(A=>{A.active&&(M=!0)}),M}),q=h(()=>l.props.backgroundColor||""),P=h(()=>l.props.activeTextColor||""),g=h(()=>l.props.textColor||""),v=h(()=>l.props.mode),b=_e({index:e.index,indexPath:s,active:j}),z=h(()=>v.value!=="horizontal"?{color:g.value}:{borderBottomColor:j.value?l.props.activeTextColor?P.value:"":"transparent",color:j.value?P.value:g.value}),F=()=>{var M,A,U;return(U=(A=(M=I.value)==null?void 0:M.popperRef)==null?void 0:A.popperInstanceRef)==null?void 0:U.destroy()},w=M=>{M||F()},Z=()=>{l.props.menuTrigger==="hover"&&l.props.mode==="horizontal"||l.props.collapse&&l.props.mode==="vertical"||e.disabled||l.handleSubMenuClick({index:e.index,indexPath:s.value,active:j.value})},X=(M,A=e.showTimeout)=>{var U;M.type!=="focus"&&(l.props.menuTrigger==="click"&&l.props.mode==="horizontal"||!l.props.collapse&&l.props.mode==="vertical"||e.disabled||(r.mouseInChild.value=!0,m==null||m(),{stop:m}=Ue(()=>{l.openMenu(e.index,s.value)},A),J.value&&((U=f.value.vnode.el)==null||U.dispatchEvent(new MouseEvent("mouseenter")))))},oe=(M=!1)=>{var A,U;l.props.menuTrigger==="click"&&l.props.mode==="horizontal"||!l.props.collapse&&l.props.mode==="vertical"||(m==null||m(),r.mouseInChild.value=!1,{stop:m}=Ue(()=>!d.value&&l.closeMenu(e.index,s.value),e.hideTimeout),J.value&&M&&((A=o.parent)==null?void 0:A.type.name)==="ElSubMenu"&&((U=r.handleMouseleave)==null||U.call(r,!0)))};re(()=>l.props.collapse,M=>w(Boolean(M)));{const M=U=>{u.value[U.index]=U},A=U=>{delete u.value[U.index]};se(`subMenu:${o.uid}`,{addSubMenu:M,removeSubMenu:A,handleMouseleave:oe,mouseInChild:d,level:r.level+1})}return t({opened:S}),fe(()=>{l.addSubMenu(b),r.addSubMenu(b)}),Ae(()=>{r.removeSubMenu(b),l.removeSubMenu(b)}),()=>{var M;const A=[(M=n.title)==null?void 0:M.call(n),L(me,{class:i.e("icon-arrow"),style:{transform:S.value?e.expandCloseIcon&&e.expandOpenIcon||e.collapseCloseIcon&&e.collapseOpenIcon&&l.props.collapse?"none":"rotateZ(180deg)":"none"}},{default:()=>ae(H.value)?L(o.appContext.components[H.value]):L(H.value)})],U=_o(l.props,r.level+1),$o=l.isMenuPopup?L(De,{ref:I,visible:S.value,effect:"light",pure:!0,offset:e.popperOffset,showArrow:!1,persistent:!0,popperClass:e.popperClass,placement:_.value,teleported:J.value,fallbackPlacements:Y.value,transition:N.value,gpuAcceleration:!1},{content:()=>{var pe;return L("div",{class:[a.m(v.value),a.m("popup-container"),e.popperClass],onMouseenter:$e=>X($e,100),onMouseleave:()=>oe(!0),onFocus:$e=>X($e,100)},[L("ul",{class:[a.b(),a.m("popup"),a.m(`popup-${_.value}`)],style:U.value},[(pe=n.default)==null?void 0:pe.call(n)])])},default:()=>L("div",{class:i.e("title"),style:[z.value,{backgroundColor:q.value}],onClick:Z},A)}):L(Me,{},[L("div",{class:i.e("title"),style:[z.value,{backgroundColor:q.value}],ref:y,onClick:Z},A),L(fo,{},{default:()=>{var pe;return Re(L("ul",{role:"menu",class:[a.b(),a.m("inline")],style:U.value},[(pe=n.default)==null?void 0:pe.call(n)]),[[Ho,S.value]])}})]);return L("li",{class:[i.b(),i.is("active",j.value),i.is("opened",S.value),i.is("disabled",e.disabled)],role:"menuitem",ariaHaspopup:!0,ariaExpanded:S.value,onMouseenter:X,onMouseleave:()=>oe(!0),onFocus:X},[$o])}}});const _t=ee({mode:{type:String,values:["horizontal","vertical"],default:"vertical"},defaultActive:{type:String,default:""},defaultOpeneds:{type:W(Array),default:()=>jo([])},uniqueOpened:Boolean,router:Boolean,menuTrigger:{type:String,values:["hover","click"],default:"hover"},collapse:Boolean,backgroundColor:String,textColor:String,activeTextColor:String,collapseTransition:{type:Boolean,default:!0},ellipsis:{type:Boolean,default:!0},popperEffect:{type:String,values:["dark","light"],default:"dark"}}),Oe=e=>Array.isArray(e)&&e.every(n=>ae(n)),Mt={close:(e,n)=>ae(e)&&Oe(n),open:(e,n)=>ae(e)&&Oe(n),select:(e,n,t,o)=>ae(e)&&Oe(n)&&Ko(t)&&(o===void 0||o instanceof Promise)};var Tt=$({name:"ElMenu",props:_t,emits:Mt,setup(e,{emit:n,slots:t,expose:o}){const s=ve(),f=s.appContext.config.globalProperties.$router,a=E(),i=G("menu"),l=G("sub-menu"),r=E(-1),p=E(e.defaultOpeneds&&!e.collapse?e.defaultOpeneds.slice(0):[]),u=E(e.defaultActive),m=E({}),d=E({}),y=h(()=>e.mode==="horizontal"||e.mode==="vertical"&&e.collapse),I=()=>{const g=u.value&&m.value[u.value];if(!g||e.mode==="horizontal"||e.collapse)return;g.indexPath.forEach(b=>{const z=d.value[b];z&&_(b,z.indexPath)})},_=(g,v)=>{p.value.includes(g)||(e.uniqueOpened&&(p.value=p.value.filter(b=>v.includes(b))),p.value.push(g),n("open",g,v))},H=(g,v)=>{const b=p.value.indexOf(g);b!==-1&&p.value.splice(b,1),n("close",g,v)},R=({index:g,indexPath:v})=>{p.value.includes(g)?H(g,v):_(g,v)},J=g=>{(e.mode==="horizontal"||e.collapse)&&(p.value=[]);const{index:v,indexPath:b}=g;if(!(v===void 0||b===void 0))if(e.router&&f){const z=g.route||v,F=f.push(z).then(w=>(w||(u.value=v),w));n("select",v,b,{index:v,indexPath:b,route:z},F)}else u.value=v,n("select",v,b,{index:v,indexPath:b})},N=g=>{const v=m.value,b=v[g]||u.value&&v[u.value]||v[e.defaultActive];b?u.value=b.index:u.value=g},Y=()=>{var g,v;if(!a.value)return-1;const b=Array.from((v=(g=a.value)==null?void 0:g.childNodes)!=null?v:[]).filter(M=>M.nodeName!=="#text"||M.nodeValue),z=64,F=Number.parseInt(getComputedStyle(a.value).paddingLeft,10),w=Number.parseInt(getComputedStyle(a.value).paddingRight,10),Z=a.value.clientWidth-F-w;let X=0,oe=0;return b.forEach((M,A)=>{X+=M.offsetWidth||0,X<=Z-z&&(oe=A+1)}),oe===b.length?-1:oe},S=(g,v=33.34)=>{let b;return()=>{b&&clearTimeout(b),b=setTimeout(()=>{g()},v)}};let j=!0;const q=()=>{const g=()=>{r.value=-1,Ce(()=>{r.value=Y()})};j?g():S(g)(),j=!1};re(()=>e.defaultActive,g=>{m.value[g]||(u.value=""),N(g)}),re(()=>e.collapse,g=>{g&&(p.value=[])}),re(m.value,I);let P;Do(()=>{e.mode==="horizontal"&&e.ellipsis?P=Uo(a,q).stop:P==null||P()});{const g=F=>{d.value[F.index]=F},v=F=>{delete d.value[F.index]};se("rootMenu",_e({props:e,openedMenus:p,items:m,subMenus:d,activeIndex:u,isMenuPopup:y,addMenuItem:F=>{m.value[F.index]=F},removeMenuItem:F=>{delete m.value[F.index]},addSubMenu:g,removeSubMenu:v,openMenu:_,closeMenu:H,handleMenuItemClick:J,handleSubMenuClick:R})),se(`subMenu:${s.uid}`,{addSubMenu:g,removeSubMenu:v,mouseInChild:E(!1),level:0})}return fe(()=>{e.mode==="horizontal"&&new ht(s.vnode.el,i.namespace.value)}),o({open:v=>{const{indexPath:b}=d.value[v];b.forEach(z=>_(z,b))},close:H,handleResize:q}),()=>{var g,v;let b=(v=(g=t.default)==null?void 0:g.call(t))!=null?v:[];const z=[];if(e.mode==="horizontal"&&a.value){const Z=on(b),X=r.value===-1?Z:Z.slice(0,r.value),oe=r.value===-1?[]:Z.slice(r.value);(oe==null?void 0:oe.length)&&e.ellipsis&&(b=X,z.push(L(He,{index:"sub-menu-more",class:l.e("hide-arrow")},{title:()=>L(me,{class:l.e("icon-more")},{default:()=>L(Bo)}),default:()=>oe})))}const F=_o(e,0),w=L("ul",{key:String(e.collapse),role:"menubar",ref:a,style:F.value,class:{[i.b()]:!0,[i.m(e.mode)]:!0,[i.m("collapse")]:e.collapse}},[...b,...z]);return e.collapseTransition&&e.mode==="vertical"?L(wt,()=>w):w}}});const $t=ee({index:{type:W([String,null]),default:null},route:{type:W([String,Object])},disabled:Boolean}),St={click:e=>ae(e.index)&&Array.isArray(e.indexPath)},Ne="ElMenuItem",kt=$({name:Ne,components:{ElTooltip:De},props:$t,emits:St,setup(e,{emit:n}){const t=ve(),o=B("rootMenu"),s=G("menu"),f=G("menu-item");o||we(Ne,"can not inject root menu");const{parentMenu:a,indexPath:i}=Co(t,ce(e,"index")),l=B(`subMenu:${a.value.uid}`);l||we(Ne,"can not inject sub menu");const r=h(()=>e.index===o.activeIndex),p=_e({index:e.index,indexPath:i,active:r}),u=()=>{e.disabled||(o.handleMenuItemClick({index:e.index,indexPath:i.value,route:e.route}),n("click",p))};return fe(()=>{l.addSubMenu(p),o.addMenuItem(p)}),Ae(()=>{l.removeSubMenu(p),o.removeMenuItem(p)}),{parentMenu:a,rootMenu:o,active:r,nsMenu:s,nsMenuItem:f,handleClick:u}}});function Ot(e,n,t,o,s,f){const a=D("el-tooltip");return T(),Q("li",{class:K([e.nsMenuItem.b(),e.nsMenuItem.is("active",e.active),e.nsMenuItem.is("disabled",e.disabled)]),role:"menuitem",tabindex:"-1",onClick:n[0]||(n[0]=(...i)=>e.handleClick&&e.handleClick(...i))},[e.parentMenu.type.name==="ElMenu"&&e.rootMenu.props.collapse&&e.$slots.title?(T(),te(a,{key:0,effect:e.rootMenu.props.popperEffect,placement:"right","fallback-placements":["left"],persistent:""},{content:O(()=>[k(e.$slots,"title")]),default:O(()=>[le("div",{class:K(e.nsMenu.be("tooltip","trigger"))},[k(e.$slots,"default")],2)]),_:3},8,["effect"])):(T(),Q(Me,{key:1},[k(e.$slots,"default"),k(e.$slots,"title")],64))],2)}var Mo=V(kt,[["render",Ot],["__file","/home/runner/work/element-plus/element-plus/packages/components/menu/src/menu-item.vue"]]);const Nt={title:String},Bt="ElMenuItemGroup",Rt=$({name:Bt,props:Nt,setup(){return{ns:G("menu-item-group")}}});function Pt(e,n,t,o,s,f){return T(),Q("li",{class:K(e.ns.b())},[le("div",{class:K(e.ns.e("title"))},[e.$slots.title?k(e.$slots,"title",{key:1}):(T(),Q(Me,{key:0},[Pe(Fe(e.title),1)],64))],2),le("ul",null,[k(e.$slots,"default")])],2)}var To=V(Rt,[["render",Pt],["__file","/home/runner/work/element-plus/element-plus/packages/components/menu/src/menu-item-group.vue"]]);const nl=Ee(Tt,{MenuItem:Mo,MenuItemGroup:To,SubMenu:He}),tl=ue(Mo),ll=ue(To);ue(He);export{Yt as E,fo as _,Ut as a,qt as b,Qt as c,qn as d,Zt as e,Xt as f,nl as g,tl as h,ll as i,Jt as j,Wt as k};
