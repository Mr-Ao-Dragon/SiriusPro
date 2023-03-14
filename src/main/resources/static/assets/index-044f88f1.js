import{w as ce,d as pe,e as ve,c as Xe,E as Ue,t as Oe}from"./index-326be066.js";import{E as We}from"./index-bb0329f1.js";import{j as Ne,a as U,v as ue,q as me,n as Ae,u as je,p as He,am as qe,E as Pe,o as Ge,Y as Je}from"./index-c5a764ed.js";import{a1 as Ce,f as ye,m as te,n as Ve,d as ee,Y as Ie,q as V,w as ie,e as Fe,ad as Qe,o as x,c as $,D as Te,u as t,x as X,a as de,C as be,B as Z,F as ne,_ as Be,J as Ee,z as _e,l as se,R as Ze,Q as ge,s as De,T as et,af as ke,t as ae,H as tt,y as le,a0 as lt,U as nt,I as fe,M as we}from"./plugin-vue_export-helper-ac789cde.js";import{C as he,I as D,U as H}from"./event-9519ab40.js";import{u as Ke}from"./index-e1439c20.js";import{u as Re,b as at}from"./index-cee436c5.js";import{d as Ye,E as rt}from"./index-ff2930d8.js";import{d as Le}from"./index-80409ba9.js";const $e=Symbol("sliderContextKey"),ot=100,st=600,Me={beforeMount(e,l){const o=l.value,{interval:a=ot,delay:m=st}=Ce(o)?{}:o;let u,s;const d=()=>Ce(o)?o():o.handler(),y=()=>{s&&(clearTimeout(s),s=void 0),u&&(clearInterval(u),u=void 0)};e.addEventListener("mousedown",f=>{f.button===0&&(y(),d(),document.addEventListener("mouseup",()=>y(),{once:!0}),s=setTimeout(()=>{u=setInterval(()=>{d()},a)},m))})}},ut=ye({id:{type:String,default:void 0},step:{type:Number,default:1},stepStrictly:Boolean,max:{type:Number,default:Number.POSITIVE_INFINITY},min:{type:Number,default:Number.NEGATIVE_INFINITY},modelValue:Number,readonly:Boolean,disabled:Boolean,size:Ne,controls:{type:Boolean,default:!0},controlsPosition:{type:String,default:"",values:["","right"]},valueOnClear:{type:[String,Number,null],validator:e=>e===null||U(e)||["min","max"].includes(e),default:null},name:String,label:String,placeholder:String,precision:{type:Number,validator:e=>e>=0&&e===Number.parseInt(`${e}`,10)},validateEvent:{type:Boolean,default:!0}}),it={[he]:(e,l)=>e!==l,blur:e=>e instanceof FocusEvent,focus:e=>e instanceof FocusEvent,[D]:e=>U(e)||ue(e),[H]:e=>U(e)||ue(e)},dt=["aria-label","onKeydown"],ct=["aria-label","onKeydown"],mt=te({name:"ElInputNumber"}),ft=te({...mt,props:ut,emits:it,setup(e,{expose:l,emit:o}){const a=e,{t:m}=Ke(),u=Ve("input-number"),s=ee(),d=Ie({currentValue:a.modelValue,userInput:null}),{formItem:y}=Re(),f=V(()=>U(a.modelValue)&&a.modelValue<=a.min),h=V(()=>U(a.modelValue)&&a.modelValue>=a.max),i=V(()=>{const n=Y(a.step);return me(a.precision)?Math.max(Y(a.modelValue),n):(n>a.precision,a.precision)}),C=V(()=>a.controls&&a.controlsPosition==="right"),A=Ae(),T=je(),F=V(()=>{if(d.userInput!==null)return d.userInput;let n=d.currentValue;if(ue(n))return"";if(U(n)){if(Number.isNaN(n))return"";me(a.precision)||(n=n.toFixed(a.precision))}return n}),_=(n,r)=>{if(me(r)&&(r=i.value),r===0)return Math.round(n);let b=String(n);const N=b.indexOf(".");if(N===-1||!b.replace(".","").split("")[N+r])return n;const M=b.length;return b.charAt(M-1)==="5"&&(b=`${b.slice(0,Math.max(0,M-1))}6`),Number.parseFloat(Number(b).toFixed(r))},Y=n=>{if(ue(n))return 0;const r=n.toString(),b=r.indexOf(".");let N=0;return b!==-1&&(N=r.length-b-1),N},L=(n,r=1)=>U(n)?_(n+a.step*r):d.currentValue,K=()=>{if(a.readonly||T.value||h.value)return;const n=Number(F.value)||0,r=L(n);w(r),o(D,d.currentValue)},P=()=>{if(a.readonly||T.value||f.value)return;const n=Number(F.value)||0,r=L(n,-1);w(r),o(D,d.currentValue)},p=(n,r)=>{const{max:b,min:N,step:S,precision:B,stepStrictly:M,valueOnClear:z}=a;let k=Number(n);if(ue(n)||Number.isNaN(k))return null;if(n===""){if(z===null)return null;k=Ee(z)?{min:N,max:b}[z]:z}return M&&(k=_(Math.round(k/S)*S,B)),me(B)||(k=_(k,B)),(k>b||k<N)&&(k=k>b?b:N,r&&o(H,k)),k},w=(n,r=!0)=>{var b;const N=d.currentValue,S=p(n);if(N!==S){if(!r){o(H,S);return}d.userInput=null,o(H,S),o(he,S,N),a.validateEvent&&((b=y==null?void 0:y.validate)==null||b.call(y,"change").catch(B=>ve())),d.currentValue=S}},G=n=>{d.userInput=n;const r=n===""?null:Number(n);o(D,r),w(r,!1)},J=n=>{const r=n!==""?Number(n):"";(U(r)&&!Number.isNaN(r)||n==="")&&w(r),d.userInput=null},O=()=>{var n,r;(r=(n=s.value)==null?void 0:n.focus)==null||r.call(n)},q=()=>{var n,r;(r=(n=s.value)==null?void 0:n.blur)==null||r.call(n)},g=n=>{o("focus",n)},E=n=>{var r;o("blur",n),a.validateEvent&&((r=y==null?void 0:y.validate)==null||r.call(y,"blur").catch(b=>ve()))};return ie(()=>a.modelValue,n=>{const r=p(d.userInput),b=p(n,!0);(!r||r!==b)&&(d.currentValue=b,d.userInput=null)},{immediate:!0}),Fe(()=>{var n;const{min:r,max:b,modelValue:N}=a,S=(n=s.value)==null?void 0:n.input;if(S.setAttribute("role","spinbutton"),Number.isFinite(b)?S.setAttribute("aria-valuemax",String(b)):S.removeAttribute("aria-valuemax"),Number.isFinite(r)?S.setAttribute("aria-valuemin",String(r)):S.removeAttribute("aria-valuemin"),S.setAttribute("aria-valuenow",String(d.currentValue)),S.setAttribute("aria-disabled",String(T.value)),!U(N)&&N!=null){let B=Number(N);Number.isNaN(B)&&(B=null),o(H,B)}}),Qe(()=>{var n;const r=(n=s.value)==null?void 0:n.input;r==null||r.setAttribute("aria-valuenow",`${d.currentValue}`)}),l({focus:O,blur:q}),(n,r)=>(x(),$("div",{class:X([t(u).b(),t(u).m(t(A)),t(u).is("disabled",t(T)),t(u).is("without-controls",!n.controls),t(u).is("controls-right",t(C))]),onDragstart:r[0]||(r[0]=pe(()=>{},["prevent"]))},[n.controls?Te((x(),$("span",{key:0,role:"button","aria-label":t(m)("el.inputNumber.decrease"),class:X([t(u).e("decrease"),t(u).is("disabled",t(f))]),onKeydown:ce(P,["enter"])},[de(t(Pe),null,{default:be(()=>[t(C)?(x(),Z(t(He),{key:0})):(x(),Z(t(qe),{key:1}))]),_:1})],42,dt)),[[t(Me),P]]):ne("v-if",!0),n.controls?Te((x(),$("span",{key:1,role:"button","aria-label":t(m)("el.inputNumber.increase"),class:X([t(u).e("increase"),t(u).is("disabled",t(h))]),onKeydown:ce(K,["enter"])},[de(t(Pe),null,{default:be(()=>[t(C)?(x(),Z(t(Ge),{key:0})):(x(),Z(t(Je),{key:1}))]),_:1})],42,ct)),[[t(Me),K]]):ne("v-if",!0),de(t(We),{id:n.id,ref_key:"input",ref:s,type:"number",step:n.step,"model-value":t(F),placeholder:n.placeholder,readonly:n.readonly,disabled:t(T),size:t(A),max:n.max,min:n.min,name:n.name,label:n.label,"validate-event":!1,onKeydown:[ce(pe(K,["prevent"]),["up"]),ce(pe(P,["prevent"]),["down"])],onBlur:E,onFocus:g,onInput:G,onChange:J},null,8,["id","step","model-value","placeholder","readonly","disabled","size","max","min","name","label","onKeydown"])],34))}});var vt=Be(ft,[["__file","/home/runner/work/element-plus/element-plus/packages/components/input-number/src/input-number.vue"]]);const bt=_e(vt),gt=ye({modelValue:{type:se([Number,Array]),default:0},id:{type:String,default:void 0},min:{type:Number,default:0},max:{type:Number,default:100},step:{type:Number,default:1},showInput:Boolean,showInputControls:{type:Boolean,default:!0},size:Ne,inputSize:Ne,showStops:Boolean,showTooltip:{type:Boolean,default:!0},formatTooltip:{type:se(Function),default:void 0},disabled:Boolean,range:Boolean,vertical:Boolean,height:String,debounce:{type:Number,default:300},label:{type:String,default:void 0},rangeStartLabel:{type:String,default:void 0},rangeEndLabel:{type:String,default:void 0},formatValueText:{type:se(Function),default:void 0},tooltipClass:{type:String,default:void 0},placement:{type:String,values:Ye,default:"top"},marks:{type:se(Object)},validateEvent:{type:Boolean,default:!0}}),Se=e=>U(e)||Ze(e)&&e.every(U),yt={[H]:Se,[D]:Se,[he]:Se},Vt=(e,l,o)=>{const a=ee();return Fe(async()=>{e.range?(Array.isArray(e.modelValue)?(l.firstValue=Math.max(e.min,e.modelValue[0]),l.secondValue=Math.min(e.max,e.modelValue[1])):(l.firstValue=e.min,l.secondValue=e.max),l.oldValue=[l.firstValue,l.secondValue]):(typeof e.modelValue!="number"||Number.isNaN(e.modelValue)?l.firstValue=e.min:l.firstValue=Math.min(e.max,Math.max(e.min,e.modelValue)),l.oldValue=l.firstValue),Xe(window,"resize",o),await ge(),o()}),{sliderWrapper:a}},ht=e=>V(()=>e.marks?Object.keys(e.marks).map(Number.parseFloat).sort((o,a)=>o-a).filter(o=>o<=e.max&&o>=e.min).map(o=>({point:o,position:(o-e.min)*100/(e.max-e.min),mark:e.marks[o]})):[]),pt=(e,l,o)=>{const{form:a,formItem:m}=Re(),u=De(),s=ee(),d=ee(),y={firstButton:s,secondButton:d},f=V(()=>e.disabled||(a==null?void 0:a.disabled)||!1),h=V(()=>Math.min(l.firstValue,l.secondValue)),i=V(()=>Math.max(l.firstValue,l.secondValue)),C=V(()=>e.range?`${100*(i.value-h.value)/(e.max-e.min)}%`:`${100*(l.firstValue-e.min)/(e.max-e.min)}%`),A=V(()=>e.range?`${100*(h.value-e.min)/(e.max-e.min)}%`:"0%"),T=V(()=>e.vertical?{height:e.height}:{}),F=V(()=>e.vertical?{height:C.value,bottom:A.value}:{width:C.value,left:A.value}),_=()=>{u.value&&(l.sliderSize=u.value[`client${e.vertical?"Height":"Width"}`])},Y=g=>{const E=e.min+g*(e.max-e.min)/100;if(!e.range)return s;let n;return Math.abs(h.value-E)<Math.abs(i.value-E)?n=l.firstValue<l.secondValue?"firstButton":"secondButton":n=l.firstValue>l.secondValue?"firstButton":"secondButton",y[n]},L=g=>{const E=Y(g);return E.value.setPosition(g),E},K=g=>{l.firstValue=g,p(e.range?[h.value,i.value]:g)},P=g=>{l.secondValue=g,e.range&&p([h.value,i.value])},p=g=>{o(H,g),o(D,g)},w=async()=>{await ge(),o(he,e.range?[h.value,i.value]:e.modelValue)},G=g=>{var E,n,r,b,N,S;if(f.value||l.dragging)return;_();let B=0;if(e.vertical){const M=(r=(n=(E=g.touches)==null?void 0:E.item(0))==null?void 0:n.clientY)!=null?r:g.clientY;B=(u.value.getBoundingClientRect().bottom-M)/l.sliderSize*100}else{const M=(S=(N=(b=g.touches)==null?void 0:b.item(0))==null?void 0:N.clientX)!=null?S:g.clientX,z=u.value.getBoundingClientRect().left;B=(M-z)/l.sliderSize*100}if(!(B<0||B>100))return L(B)};return{elFormItem:m,slider:u,firstButton:s,secondButton:d,sliderDisabled:f,minValue:h,maxValue:i,runwayStyle:T,barStyle:F,resetSize:_,setPosition:L,emitChange:w,onSliderWrapperPrevent:g=>{var E,n;(((E=y.firstButton.value)==null?void 0:E.dragging)||((n=y.secondButton.value)==null?void 0:n.dragging))&&g.preventDefault()},onSliderClick:g=>{G(g)&&w()},onSliderDown:async g=>{const E=G(g);E&&(await ge(),E.value.onButtonDown(g))},setFirstValue:K,setSecondValue:P}},{left:wt,down:St,right:Nt,up:Et,home:kt,end:It,pageUp:Bt,pageDown:xt}=Ue,Pt=(e,l,o)=>{const a=ee(),m=ee(!1),u=V(()=>l.value instanceof Function),s=V(()=>u.value&&l.value(e.modelValue)||e.modelValue),d=Le(()=>{o.value&&(m.value=!0)},50),y=Le(()=>{o.value&&(m.value=!1)},50);return{tooltip:a,tooltipVisible:m,formatValue:s,displayTooltip:d,hideTooltip:y}},Ct=(e,l,o)=>{const{disabled:a,min:m,max:u,step:s,showTooltip:d,precision:y,sliderSize:f,formatTooltip:h,emitChange:i,resetSize:C,updateDragging:A}=et($e),{tooltip:T,tooltipVisible:F,formatValue:_,displayTooltip:Y,hideTooltip:L}=Pt(e,h,d),K=ee(),P=V(()=>`${(e.modelValue-m.value)/(u.value-m.value)*100}%`),p=V(()=>e.vertical?{bottom:P.value}:{left:P.value}),w=()=>{l.hovering=!0,Y()},G=()=>{l.hovering=!1,l.dragging||L()},J=c=>{a.value||(c.preventDefault(),B(c),window.addEventListener("mousemove",M),window.addEventListener("touchmove",M),window.addEventListener("mouseup",z),window.addEventListener("touchend",z),window.addEventListener("contextmenu",z),K.value.focus())},O=c=>{a.value||(l.newPosition=Number.parseFloat(P.value)+c/(u.value-m.value)*100,k(l.newPosition),i())},q=()=>{O(-s.value)},g=()=>{O(s.value)},E=()=>{O(-s.value*4)},n=()=>{O(s.value*4)},r=()=>{a.value||(k(0),i())},b=()=>{a.value||(k(100),i())},N=c=>{let I=!0;[wt,St].includes(c.key)?q():[Nt,Et].includes(c.key)?g():c.key===kt?r():c.key===It?b():c.key===xt?E():c.key===Bt?n():I=!1,I&&c.preventDefault()},S=c=>{let I,W;return c.type.startsWith("touch")?(W=c.touches[0].clientY,I=c.touches[0].clientX):(W=c.clientY,I=c.clientX),{clientX:I,clientY:W}},B=c=>{l.dragging=!0,l.isClick=!0;const{clientX:I,clientY:W}=S(c);e.vertical?l.startY=W:l.startX=I,l.startPosition=Number.parseFloat(P.value),l.newPosition=l.startPosition},M=c=>{if(l.dragging){l.isClick=!1,Y(),C();let I;const{clientX:W,clientY:Q}=S(c);e.vertical?(l.currentY=Q,I=(l.startY-l.currentY)/f.value*100):(l.currentX=W,I=(l.currentX-l.startX)/f.value*100),l.newPosition=l.startPosition+I,k(l.newPosition)}},z=()=>{l.dragging&&(setTimeout(()=>{l.dragging=!1,l.hovering||L(),l.isClick||k(l.newPosition),i()},0),window.removeEventListener("mousemove",M),window.removeEventListener("touchmove",M),window.removeEventListener("mouseup",z),window.removeEventListener("touchend",z),window.removeEventListener("contextmenu",z))},k=async c=>{if(c===null||Number.isNaN(+c))return;c<0?c=0:c>100&&(c=100);const I=100/((u.value-m.value)/s.value);let Q=Math.round(c/I)*I*(u.value-m.value)*.01+m.value;Q=Number.parseFloat(Q.toFixed(y.value)),Q!==e.modelValue&&o(H,Q),!l.dragging&&e.modelValue!==l.oldValue&&(l.oldValue=e.modelValue),await ge(),l.dragging&&Y(),T.value.updatePopper()};return ie(()=>l.dragging,c=>{A(c)}),{disabled:a,button:K,tooltip:T,tooltipVisible:F,showTooltip:d,wrapperStyle:p,formatValue:_,handleMouseEnter:w,handleMouseLeave:G,onButtonDown:J,onKeyDown:N,setPosition:k}},Tt=(e,l,o,a)=>({stops:V(()=>{if(!e.showStops||e.min>e.max)return[];if(e.step===0)return[];const s=(e.max-e.min)/e.step,d=100*e.step/(e.max-e.min),y=Array.from({length:s-1}).map((f,h)=>(h+1)*d);return e.range?y.filter(f=>f<100*(o.value-e.min)/(e.max-e.min)||f>100*(a.value-e.min)/(e.max-e.min)):y.filter(f=>f>100*(l.firstValue-e.min)/(e.max-e.min))}),getStopStyle:s=>e.vertical?{bottom:`${s}%`}:{left:`${s}%`}}),Lt=(e,l,o,a,m,u)=>{const s=f=>{m(H,f),m(D,f)},d=()=>e.range?![o.value,a.value].every((f,h)=>f===l.oldValue[h]):e.modelValue!==l.oldValue,y=()=>{var f,h;if(e.min>e.max){Oe("Slider","min should not be greater than max.");return}const i=e.modelValue;e.range&&Array.isArray(i)?i[1]<e.min?s([e.min,e.min]):i[0]>e.max?s([e.max,e.max]):i[0]<e.min?s([e.min,i[1]]):i[1]>e.max?s([i[0],e.max]):(l.firstValue=i[0],l.secondValue=i[1],d()&&(e.validateEvent&&((f=u==null?void 0:u.validate)==null||f.call(u,"change").catch(C=>ve())),l.oldValue=i.slice())):!e.range&&typeof i=="number"&&!Number.isNaN(i)&&(i<e.min?s(e.min):i>e.max?s(e.max):(l.firstValue=i,d()&&(e.validateEvent&&((h=u==null?void 0:u.validate)==null||h.call(u,"change").catch(C=>ve())),l.oldValue=i)))};y(),ie(()=>l.dragging,f=>{f||y()}),ie(()=>e.modelValue,(f,h)=>{l.dragging||Array.isArray(f)&&Array.isArray(h)&&f.every((i,C)=>i===h[C])&&l.firstValue===f[0]&&l.secondValue===f[1]||y()},{deep:!0}),ie(()=>[e.min,e.max],()=>{y()})},Mt=ye({modelValue:{type:Number,default:0},vertical:Boolean,tooltipClass:String,placement:{type:String,values:Ye,default:"top"}}),zt={[H]:e=>U(e)},At=["tabindex"],Ft=te({name:"ElSliderButton"}),_t=te({...Ft,props:Mt,emits:zt,setup(e,{expose:l,emit:o}){const a=e,m=Ve("slider"),u=Ie({hovering:!1,dragging:!1,isClick:!1,startX:0,currentX:0,startY:0,currentY:0,startPosition:0,newPosition:0,oldValue:a.modelValue}),{disabled:s,button:d,tooltip:y,showTooltip:f,tooltipVisible:h,wrapperStyle:i,formatValue:C,handleMouseEnter:A,handleMouseLeave:T,onButtonDown:F,onKeyDown:_,setPosition:Y}=Ct(a,u,o),{hovering:L,dragging:K}=ke(u);return l({onButtonDown:F,onKeyDown:_,setPosition:Y,hovering:L,dragging:K}),(P,p)=>(x(),$("div",{ref_key:"button",ref:d,class:X([t(m).e("button-wrapper"),{hover:t(L),dragging:t(K)}]),style:le(t(i)),tabindex:t(s)?-1:0,onMouseenter:p[0]||(p[0]=(...w)=>t(A)&&t(A)(...w)),onMouseleave:p[1]||(p[1]=(...w)=>t(T)&&t(T)(...w)),onMousedown:p[2]||(p[2]=(...w)=>t(F)&&t(F)(...w)),onTouchstart:p[3]||(p[3]=(...w)=>t(F)&&t(F)(...w)),onFocus:p[4]||(p[4]=(...w)=>t(A)&&t(A)(...w)),onBlur:p[5]||(p[5]=(...w)=>t(T)&&t(T)(...w)),onKeydown:p[6]||(p[6]=(...w)=>t(_)&&t(_)(...w))},[de(t(rt),{ref_key:"tooltip",ref:y,visible:t(h),placement:P.placement,"fallback-placements":["top","bottom","right","left"],"stop-popper-mouse-event":!1,"popper-class":P.tooltipClass,disabled:!t(f),persistent:""},{content:be(()=>[ae("span",null,tt(t(C)),1)]),default:be(()=>[ae("div",{class:X([t(m).e("button"),{hover:t(L),dragging:t(K)}])},null,2)]),_:1},8,["visible","placement","popper-class","disabled"])],46,At))}});var ze=Be(_t,[["__file","/home/runner/work/element-plus/element-plus/packages/components/slider/src/button.vue"]]);const Kt=ye({mark:{type:se([String,Object]),default:void 0}});var Rt=te({name:"ElSliderMarker",props:Kt,setup(e){const l=Ve("slider"),o=V(()=>Ee(e.mark)?e.mark:e.mark.label),a=V(()=>Ee(e.mark)?void 0:e.mark.style);return()=>lt("div",{class:l.e("marks-text"),style:a.value},o.value)}});const Yt=["id","role","aria-label","aria-labelledby"],$t={key:1},Xt=te({name:"ElSlider"}),Ut=te({...Xt,props:gt,emits:yt,setup(e,{expose:l,emit:o}){const a=e,m=Ve("slider"),{t:u}=Ke(),s=Ie({firstValue:0,secondValue:0,oldValue:0,dragging:!1,sliderSize:1}),{elFormItem:d,slider:y,firstButton:f,secondButton:h,sliderDisabled:i,minValue:C,maxValue:A,runwayStyle:T,barStyle:F,resetSize:_,emitChange:Y,onSliderWrapperPrevent:L,onSliderClick:K,onSliderDown:P,setFirstValue:p,setSecondValue:w}=pt(a,s,o),{stops:G,getStopStyle:J}=Tt(a,s,C,A),{inputId:O,isLabeledByFormItem:q}=at(a,{formItemContext:d}),g=Ae(),E=V(()=>a.inputSize||g.value),n=V(()=>a.label||u("el.slider.defaultLabel",{min:a.min,max:a.max})),r=V(()=>a.range?a.rangeStartLabel||u("el.slider.defaultRangeStartLabel"):n.value),b=V(()=>a.formatValueText?a.formatValueText(c.value):`${c.value}`),N=V(()=>a.rangeEndLabel||u("el.slider.defaultRangeEndLabel")),S=V(()=>a.formatValueText?a.formatValueText(I.value):`${I.value}`),B=V(()=>[m.b(),m.m(g.value),m.is("vertical",a.vertical),{[m.m("with-input")]:a.showInput}]),M=ht(a);Lt(a,s,C,A,o,d);const z=V(()=>{const v=[a.min,a.max,a.step].map(j=>{const re=`${j}`.split(".")[1];return re?re.length:0});return Math.max.apply(null,v)}),{sliderWrapper:k}=Vt(a,s,_),{firstValue:c,secondValue:I,sliderSize:W}=ke(s),Q=v=>{s.dragging=v};return nt($e,{...ke(a),sliderSize:W,disabled:i,precision:z,emitChange:Y,resetSize:_,updateDragging:Q}),l({onSliderClick:K}),(v,j)=>{var re,xe;return x(),$("div",{id:v.range?t(O):void 0,ref_key:"sliderWrapper",ref:k,class:X(t(B)),role:v.range?"group":void 0,"aria-label":v.range&&!t(q)?t(n):void 0,"aria-labelledby":v.range&&t(q)?(re=t(d))==null?void 0:re.labelId:void 0,onTouchstart:j[2]||(j[2]=(...R)=>t(L)&&t(L)(...R)),onTouchmove:j[3]||(j[3]=(...R)=>t(L)&&t(L)(...R))},[ae("div",{ref_key:"slider",ref:y,class:X([t(m).e("runway"),{"show-input":v.showInput&&!v.range},t(m).is("disabled",t(i))]),style:le(t(T)),onMousedown:j[0]||(j[0]=(...R)=>t(P)&&t(P)(...R)),onTouchstart:j[1]||(j[1]=(...R)=>t(P)&&t(P)(...R))},[ae("div",{class:X(t(m).e("bar")),style:le(t(F))},null,6),de(ze,{id:v.range?void 0:t(O),ref_key:"firstButton",ref:f,"model-value":t(c),vertical:v.vertical,"tooltip-class":v.tooltipClass,placement:v.placement,role:"slider","aria-label":v.range||!t(q)?t(r):void 0,"aria-labelledby":!v.range&&t(q)?(xe=t(d))==null?void 0:xe.labelId:void 0,"aria-valuemin":v.min,"aria-valuemax":v.range?t(I):v.max,"aria-valuenow":t(c),"aria-valuetext":t(b),"aria-orientation":v.vertical?"vertical":"horizontal","aria-disabled":t(i),"onUpdate:modelValue":t(p)},null,8,["id","model-value","vertical","tooltip-class","placement","aria-label","aria-labelledby","aria-valuemin","aria-valuemax","aria-valuenow","aria-valuetext","aria-orientation","aria-disabled","onUpdate:modelValue"]),v.range?(x(),Z(ze,{key:0,ref_key:"secondButton",ref:h,"model-value":t(I),vertical:v.vertical,"tooltip-class":v.tooltipClass,placement:v.placement,role:"slider","aria-label":t(N),"aria-valuemin":t(c),"aria-valuemax":v.max,"aria-valuenow":t(I),"aria-valuetext":t(S),"aria-orientation":v.vertical?"vertical":"horizontal","aria-disabled":t(i),"onUpdate:modelValue":t(w)},null,8,["model-value","vertical","tooltip-class","placement","aria-label","aria-valuemin","aria-valuemax","aria-valuenow","aria-valuetext","aria-orientation","aria-disabled","onUpdate:modelValue"])):ne("v-if",!0),v.showStops?(x(),$("div",$t,[(x(!0),$(fe,null,we(t(G),(R,oe)=>(x(),$("div",{key:oe,class:X(t(m).e("stop")),style:le(t(J)(R))},null,6))),128))])):ne("v-if",!0),t(M).length>0?(x(),$(fe,{key:2},[ae("div",null,[(x(!0),$(fe,null,we(t(M),(R,oe)=>(x(),$("div",{key:oe,style:le(t(J)(R.position)),class:X([t(m).e("stop"),t(m).e("marks-stop")])},null,6))),128))]),ae("div",{class:X(t(m).e("marks"))},[(x(!0),$(fe,null,we(t(M),(R,oe)=>(x(),Z(t(Rt),{key:oe,mark:R.mark,style:le(t(J)(R.position))},null,8,["mark","style"]))),128))],2)],64)):ne("v-if",!0)],38),v.showInput&&!v.range?(x(),Z(t(bt),{key:0,ref:"input","model-value":t(c),class:X(t(m).e("input")),step:v.step,disabled:t(i),controls:v.showInputControls,min:v.min,max:v.max,debounce:v.debounce,size:t(E),"onUpdate:modelValue":t(p),onChange:t(Y)},null,8,["model-value","class","step","disabled","controls","min","max","debounce","size","onUpdate:modelValue","onChange"])):ne("v-if",!0)],42,Yt)}}});var Ot=Be(Ut,[["__file","/home/runner/work/element-plus/element-plus/packages/components/slider/src/slider.vue"]]);const el=_e(Ot);export{bt as E,el as a,Me as v};
