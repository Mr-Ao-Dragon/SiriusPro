import{k as $,z as h,A,d as m,x as B,o as f,c as E,H as O,a as _,O as z,P as U,G as I,I as g,u as n,V as q,X as Z,Y as G,K,L as oe,v as y,ao as ae,w as X,t as re,aL as le,af as ie,g as J,bK as ue,h as k,T as j,j as ce,F as de,az as pe,N as C,J as me,S as w,Q as H,R as fe,W as ge,a9 as ye,Z as ve,br as be,at as Ce,a0 as R,q as Q,au as M,b0 as he,aX as Te,bD as V,bE as Ne}from"./index-6980f424.js";const we=$({value:{type:[String,Number],default:""},max:{type:Number,default:99},isDot:Boolean,hidden:Boolean,type:{type:String,values:["primary","success","warning","info","danger"],default:"danger"}}),ze=["textContent"],Se=h({name:"ElBadge"}),ke=h({...Se,props:we,setup(s,{expose:t}){const e=s,o=A("badge"),a=m(()=>e.isDot?"":B(e.value)&&B(e.max)?e.max<e.value?`${e.max}+`:`${e.value}`:`${e.value}`);return t({content:a}),(l,u)=>(f(),E("div",{class:g(n(o).b())},[O(l.$slots,"default"),_(G,{name:`${n(o).namespace.value}-zoom-in-center`,persisted:""},{default:z(()=>[U(I("sup",{class:g([n(o).e("content"),n(o).em("content",l.type),n(o).is("fixed",!!l.$slots.default),n(o).is("dot",l.isDot)]),textContent:q(n(a))},null,10,ze),[[Z,!l.hidden&&(n(a)||l.isDot)]])]),_:1},8,["name"])],2))}});var Be=K(ke,[["__file","/home/runner/work/element-plus/element-plus/packages/components/badge/src/badge.vue"]]);const Ee=oe(Be),x={},Ie=$({a11y:{type:Boolean,default:!0},locale:{type:y(Object)},size:ae,button:{type:y(Object)},experimentalFeatures:{type:y(Object)},keyboardNavigation:{type:Boolean,default:!0},message:{type:y(Object)},zIndex:Number,namespace:{type:String,default:"el"}}),qe=h({name:"ElConfigProvider",props:Ie,setup(s,{slots:t}){X(()=>s.message,o=>{Object.assign(x,o??{})},{immediate:!0,deep:!0});const e=re(s);return()=>O(t,"default",{config:e==null?void 0:e.value})}}),W=["success","info","warning","error"],i=le({customClass:"",center:!1,dangerouslyUseHTMLString:!1,duration:3e3,icon:void 0,id:"",message:"",onClose:void 0,showClose:!1,type:"info",offset:16,zIndex:0,grouping:!1,repeatNum:1,appendTo:J?document.body:void 0}),Me=$({customClass:{type:String,default:i.customClass},center:{type:Boolean,default:i.center},dangerouslyUseHTMLString:{type:Boolean,default:i.dangerouslyUseHTMLString},duration:{type:Number,default:i.duration},icon:{type:ie,default:i.icon},id:{type:String,default:i.id},message:{type:y([String,Object,Function]),default:i.message},onClose:{type:y(Function),required:!1},showClose:{type:Boolean,default:i.showClose},type:{type:String,values:W,default:i.type},offset:{type:Number,default:i.offset},zIndex:{type:Number,default:i.zIndex},grouping:{type:Boolean,default:i.grouping},repeatNum:{type:Number,default:i.repeatNum}}),xe={destroy:()=>!0},c=ue([]),$e=s=>{const t=c.findIndex(a=>a.id===s),e=c[t];let o;return t>0&&(o=c[t-1]),{current:e,prev:o}},Oe=s=>{const{prev:t}=$e(s);return t?t.vm.exposed.bottom.value:0},_e=["id"],Le=["innerHTML"],De=h({name:"ElMessage"}),Pe=h({...De,props:Me,emits:xe,setup(s,{expose:t}){const e=s,{Close:o}=ve,a=A("message"),l=k(),u=k(!1),d=k(0);let p;const T=m(()=>e.type?e.type==="error"?"danger":e.type:"info"),L=m(()=>{const r=e.type;return{[a.bm("icon",r)]:r&&j[r]}}),b=m(()=>e.icon||j[e.type]||""),ee=m(()=>Oe(e.id)),D=m(()=>e.offset+ee.value),se=m(()=>d.value+D.value),te=m(()=>({top:`${D.value}px`,zIndex:e.zIndex}));function S(){e.duration!==0&&({stop:p}=be(()=>{N()},e.duration))}function P(){p==null||p()}function N(){u.value=!1}function ne({code:r}){r===Ce.esc&&N()}return ce(()=>{S(),u.value=!0}),X(()=>e.repeatNum,()=>{P(),S()}),de(document,"keydown",ne),pe(l,()=>{d.value=l.value.getBoundingClientRect().height}),t({visible:u,bottom:se,close:N}),(r,F)=>(f(),C(G,{name:n(a).b("fade"),onBeforeLeave:r.onClose,onAfterLeave:F[0]||(F[0]=Ae=>r.$emit("destroy")),persisted:""},{default:z(()=>[U(I("div",{id:r.id,ref_key:"messageRef",ref:l,class:g([n(a).b(),{[n(a).m(r.type)]:r.type&&!r.icon},n(a).is("center",r.center),n(a).is("closable",r.showClose),r.customClass]),style:me(n(te)),role:"alert",onMouseenter:P,onMouseleave:S},[r.repeatNum>1?(f(),C(n(Ee),{key:0,value:r.repeatNum,type:n(T),class:g(n(a).e("badge"))},null,8,["value","type","class"])):w("v-if",!0),n(b)?(f(),C(n(H),{key:1,class:g([n(a).e("icon"),n(L)])},{default:z(()=>[(f(),C(fe(n(b))))]),_:1},8,["class"])):w("v-if",!0),O(r.$slots,"default",{},()=>[r.dangerouslyUseHTMLString?(f(),E(ge,{key:1},[w(" Caution here, message could've been compromised, never use user's input as message "),I("p",{class:g(n(a).e("content")),innerHTML:r.message},null,10,Le)],2112)):(f(),E("p",{key:0,class:g(n(a).e("content"))},q(r.message),3))]),r.showClose?(f(),C(n(H),{key:2,class:g(n(a).e("closeBtn")),onClick:ye(N,["stop"])},{default:z(()=>[_(n(o))]),_:1},8,["class","onClick"])):w("v-if",!0)],46,_e),[[Z,u.value]])]),_:3},8,["name","onBeforeLeave"]))}});var Fe=K(Pe,[["__file","/home/runner/work/element-plus/element-plus/packages/components/message/src/message.vue"]]);let je=1;const Y=s=>{const t=!s||R(s)||Q(s)||M(s)?{message:s}:s,e={...i,...t};if(!e.appendTo)e.appendTo=document.body;else if(R(e.appendTo)){let o=document.querySelector(e.appendTo);he(o)||(o=document.body),e.appendTo=o}return e},He=s=>{const t=c.indexOf(s);if(t===-1)return;c.splice(t,1);const{handler:e}=s;e.close()},Re=({appendTo:s,...t},e)=>{const{nextZIndex:o}=Te(),a=`message_${je++}`,l=t.onClose,u=document.createElement("div"),d={...t,zIndex:o()+t.zIndex,id:a,onClose:()=>{l==null||l(),He(b)},onDestroy:()=>{V(null,u)}},p=_(Fe,d,M(d.message)||Q(d.message)?{default:M(d.message)?d.message:()=>d.message}:null);p.appContext=e||v._context,V(p,u),s.appendChild(u.firstElementChild);const T=p.component,b={id:a,vnode:p,vm:T,handler:{close:()=>{T.exposed.visible.value=!1}},props:p.component.props};return b},v=(s={},t)=>{if(!J)return{close:()=>{}};if(B(x.max)&&c.length>=x.max)return{close:()=>{}};const e=Y(s);if(e.grouping&&c.length){const a=c.find(({vnode:l})=>{var u;return((u=l.props)==null?void 0:u.message)===e.message});if(a)return a.props.repeatNum+=1,a.props.type=e.type,a.handler}const o=Re(e,t);return c.push(o),o.handler};W.forEach(s=>{v[s]=(t={},e)=>{const o=Y(t);return v({...o,type:s},e)}});function Ve(s){for(const t of c)(!s||s===t.props.type)&&t.handler.close()}v.closeAll=Ve;v._context=null;const Ze=Ne(v,"$message");export{qe as C,Ee as E,Ze as a};