/* empty css             *//* empty css                *//* empty css                  */import{g as C}from"./request-54c3d3bc.js";import{E as M}from"./index-2364a310.js";import{E as N}from"./index-bd0ed55f.js";import{E as B}from"./index-6c539b03.js";import{v as F,e as n,aB as H,c as p,a as _,L as y,o as i,E as r,Q as S,u as c,S as k,a3 as E,G as V,cx as U,cy as $,bG as O}from"./index-12833456.js";import{_ as z}from"./_plugin-vue_export-helper-c27b6911.js";import"./axios-0cfbca79.js";import"./index-3022843a.js";const G=u=>(U("data-v-8cbcfa15"),u=u(),$(),u),J={class:"213",style:{width:"99%",height:"calc(99% - 20px)"}},R={style:{height:"30px",display:"flex","justify-content":"end","align-items":"center","font-size":"16px"}},j={style:{"margin-left":"20px"}},Q={style:{"margin-left":"20px"}},W=["innerHTML","onDblclick"],q=["innerHTML","onDblclick"],A=G(()=>r("div",{style:{height:"40px"}},null,-1)),K=F({__name:"logmanage",setup(u){const t=n([]),v=n(0),h=n();let x="";C().then(s=>{x=s.data,L(x)});let o=null,b=location.origin,I=b.indexOf("/")+2,a=n(0);const T=b.slice(I),L=s=>{o=new WebSocket(`ws://${T}/dash-board`),o.onopen=function(e){console.log(e),console.log("websocket连接成功"),o.send(`{"event" : 2, "JSESSIONID" : "${s}"}`)},o.onmessage=function(e){JSON.parse(e.data).data.map(f=>{t.value[a.value]=f,a.value++,a.value>=1024&&(a.value=0),v.value=t.value.length}),g.value||setTimeout(()=>{h.value.setScrollTop(999999999)},100)},o.onclose=function(e){console.log(e),console.log("websocket已断开")},o.onerror=function(e){console.log("websocket发生错误")}},w=s=>{console.log(s);const e=s.replace('<span class="colour-time">',"").replace('<span class="colour-level">',"").replace('<span class="colour-pid">',"").replace('<span class="colour-class">',"").replace("</span>","").replace("</span>","").replace("</span>","").replace("</span>","").replace('<span class="thread-name">',"").replace("</span>","").replace('<span class="colour-level-error">',"").replace('<span class="colour-level-warning">',"");navigator.clipboard.writeText(e).then(m=>{O({showClose:!0,message:"复制成功",type:"success"})})},g=n(!1),d=n(!1);return H(()=>{o!=null&&o.close()}),(s,e)=>{const m=M,f=N;return i(),p("div",J,[_(f,{class:"box-card",style:{width:"100%",height:"100%","margin-top":"20px","margin-left":"20px","background-color":"#242424",color:"#D3F2F2"},"body-style":{height:"calc(100% - 70px)"}},{default:y(()=>[r("div",R,[r("span",j,[S("双击可复制    暂停滚动 "),_(m,{modelValue:g.value,"onUpdate:modelValue":e[0]||(e[0]=l=>g.value=l)},null,8,["modelValue"])]),r("span",Q,[S("换行显示 "),_(m,{modelValue:d.value,"onUpdate:modelValue":e[1]||(e[1]=l=>d.value=l)},null,8,["modelValue"])])]),_(c(B),{ref_key:"scrollbarRef",ref:h},{default:y(()=>[(i(!0),p(k,null,E(c(v.value)-c(a),l=>(i(),p("p",null,[r("p",{innerHTML:t.value[l+c(a)-1],class:V(d.value?"":"log-text"),onDblclick:D=>w(t.value[l+c(a)-1])},null,42,W)]))),256)),(i(!0),p(k,null,E(c(a),l=>(i(),p("p",null,[r("p",{innerHTML:t.value[l-1],class:V(d.value?"":"log-text"),onDblclick:D=>w(t.value[l-1])},null,42,q)]))),256)),A]),_:1},512)]),_:1},8,["body-style"])])}}});const ce=z(K,[["__scopeId","data-v-8cbcfa15"]]);export{ce as default};
