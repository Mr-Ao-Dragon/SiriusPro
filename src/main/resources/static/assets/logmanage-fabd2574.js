/* empty css             *//* empty css                *//* empty css                  */import{g as C}from"./request-e5b99d4f.js";import{E as L}from"./index-79c455fb.js";import{E as F}from"./index-2ccb0fbe.js";import{E as M}from"./index-ce00a3bf.js";import{a as N}from"./index-550da0b9.js";import{g as H,b as p,R as B,o as t,c as n,a as m,q as w,j as _,y as k,u as c,F as S,D as E,n as V,aC as O,aD as R}from"./runtime-core.esm-bundler-a054f4a5.js";import{_ as U}from"./_plugin-vue_export-helper-c27b6911.js";import"./axios-7621eeb3.js";import"./vue-router-b16929bb.js";import"./index-61f8e38d.js";import"./index-6295fd1d.js";import"./event-9519ab40.js";import"./index-3df48e54.js";const $=i=>(O("data-v-7d020c82"),i=i(),R(),i),j={class:"213",style:{width:"99%",height:"calc(99% - 20px)"}},z={style:{height:"30px",display:"flex","justify-content":"end","align-items":"center","font-size":"16px"}},J={style:{"margin-left":"20px"}},q={style:{"margin-left":"20px"}},W=["innerHTML","onDblclick"],A=["innerHTML","onDblclick"],G=$(()=>_("div",{style:{height:"40px"}},null,-1)),K=H({__name:"logmanage",setup(i){const r=p([]),v=p(0),h=p();let x="";C().then(s=>{x=s.data,I(x)});let a=null,y=location.origin,D=y.indexOf("/")+2,o=p(0);y.slice(D);const I=s=>{a=new WebSocket("ws://103.91.209.111:16590/dash-board"),a.onopen=function(e){console.log(e),console.log("websocket连接成功"),a.send(`{"event" : 2, "JSESSIONID" : "${s}"}`)},a.onmessage=function(e){JSON.parse(e.data).data.map(f=>{r.value[o.value]=f,o.value++,o.value>=1024&&(o.value=0),v.value=r.value.length}),g.value||setTimeout(()=>{h.value.setScrollTop(999999999)},100)},a.onclose=function(e){console.log(e),console.log("websocket已断开")},a.onerror=function(e){console.log("websocket发生错误")}},b=s=>{console.log(s);const e=s.replace('<span class="colour-time">',"").replace('<span class="colour-level">',"").replace('<span class="colour-pid">',"").replace('<span class="colour-class">',"").replace("</span>","").replace("</span>","").replace("</span>","").replace("</span>","").replace('<span class="thread-name">',"").replace("</span>","").replace('<span class="colour-level-error">',"").replace('<span class="colour-level-warning">',"");navigator.clipboard.writeText(e).then(d=>{N({showClose:!0,message:"复制成功",type:"success"})})},g=p(!1),u=p(!1);return B(()=>{a!=null&&a.close()}),(s,e)=>{const d=L,f=F;return t(),n("div",j,[m(f,{class:"box-card",style:{width:"100%",height:"100%","margin-top":"20px","margin-left":"20px","background-color":"#242424",color:"#D3F2F2"},"body-style":{height:"calc(100% - 70px)"}},{default:w(()=>[_("div",z,[_("span",J,[k("双击可复制    暂停滚动 "),m(d,{modelValue:g.value,"onUpdate:modelValue":e[0]||(e[0]=l=>g.value=l)},null,8,["modelValue"])]),_("span",q,[k("换行显示 "),m(d,{modelValue:u.value,"onUpdate:modelValue":e[1]||(e[1]=l=>u.value=l)},null,8,["modelValue"])])]),m(c(M),{ref_key:"scrollbarRef",ref:h},{default:w(()=>[(t(!0),n(S,null,E(c(v.value)-c(o),l=>(t(),n("p",null,[(t(),n("p",{innerHTML:r.value[l+c(o)-1],class:V(u.value?"":"log-text"),onDblclick:T=>b(r.value[l+c(o)-1]),key:l+c(o)-1},null,42,W))]))),256)),(t(!0),n(S,null,E(c(o),l=>(t(),n("p",null,[(t(),n("p",{innerHTML:r.value[l-1],class:V(u.value?"":"log-text"),onDblclick:T=>b(r.value[l-1]),key:l-1},null,42,A))]))),256)),G]),_:1},512)]),_:1},8,["body-style"])])}}});const ue=U(K,[["__scopeId","data-v-7d020c82"]]);export{ue as default};
