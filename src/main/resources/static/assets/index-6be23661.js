import{m as M,y as m,B as D,C as V,g as r,bd as A,bz as O,ap as Q,aH as U,aQ as H,aw as J,a2 as C,o as l,c,K as i,u as t,I as y,L as f,J as N,X as _,V as I,P as B,Q as K,U as R,S as X,M as j,N as q}from"./index-fe97e1b3.js";const G=M({type:{type:String,default:"line",values:["line","circle","dashboard"]},percentage:{type:Number,default:0,validator:u=>u>=0&&u<=100},status:{type:String,default:"",values:["","success","exception","warning"]},indeterminate:{type:Boolean,default:!1},duration:{type:Number,default:3},strokeWidth:{type:Number,default:6},strokeLinecap:{type:m(String),default:"round"},textInside:{type:Boolean,default:!1},width:{type:Number,default:126},showText:{type:Boolean,default:!0},color:{type:m([String,Array,Function]),default:""},format:{type:m(Function),default:u=>`${u}%`}}),Y=["aria-valuenow"],Z={viewBox:"0 0 100 100"},ee=["d","stroke","stroke-width"],te=["d","stroke","opacity","stroke-linecap","stroke-width"],se={key:0},ae=D({name:"ElProgress"}),re=D({...ae,props:G,setup(u){const s=u,v={success:"#13ce66",exception:"#ff4949",warning:"#e6a23c",default:"#20a0ff"},a=V("progress"),T=r(()=>({width:`${s.percentage}%`,animationDuration:`${s.duration}s`,backgroundColor:P(s.percentage)})),g=r(()=>(s.strokeWidth/s.width*100).toFixed(1)),b=r(()=>["circle","dashboard"].includes(s.type)?Number.parseInt(`${50-Number.parseFloat(g.value)/2}`,10):0),$=r(()=>{const e=b.value,o=s.type==="dashboard";return`
          M 50 50
          m 0 ${o?"":"-"}${e}
          a ${e} ${e} 0 1 1 0 ${o?"-":""}${e*2}
          a ${e} ${e} 0 1 1 0 ${o?"":"-"}${e*2}
          `}),h=r(()=>2*Math.PI*b.value),k=r(()=>s.type==="dashboard"?.75:1),w=r(()=>`${-1*h.value*(1-k.value)/2}px`),x=r(()=>({strokeDasharray:`${h.value*k.value}px, ${h.value}px`,strokeDashoffset:w.value})),z=r(()=>({strokeDasharray:`${h.value*k.value*(s.percentage/100)}px, ${h.value}px`,strokeDashoffset:w.value,transition:"stroke-dasharray 0.6s ease 0s, stroke 0.6s ease, opacity ease 0.6s"})),E=r(()=>{let e;return s.color?e=P(s.percentage):e=v[s.status]||v.default,e}),F=r(()=>s.status==="warning"?A:s.type==="line"?s.status==="success"?O:Q:s.status==="success"?U:H),W=r(()=>s.type==="line"?12+s.strokeWidth*.4:s.width*.111111+2),S=r(()=>s.format(s.percentage));function L(e){const o=100/e.length;return e.map((n,p)=>C(n)?{color:n,percentage:(p+1)*o}:n).sort((n,p)=>n.percentage-p.percentage)}const P=e=>{var o;const{color:d}=s;if(J(d))return d(e);if(C(d))return d;{const n=L(d);for(const p of n)if(p.percentage>e)return p.color;return(o=n[n.length-1])==null?void 0:o.color}};return(e,o)=>(l(),c("div",{class:i([t(a).b(),t(a).m(e.type),t(a).is(e.status),{[t(a).m("without-text")]:!e.showText,[t(a).m("text-inside")]:e.textInside}]),role:"progressbar","aria-valuenow":e.percentage,"aria-valuemin":"0","aria-valuemax":"100"},[e.type==="line"?(l(),c("div",{key:0,class:i(t(a).b("bar"))},[y("div",{class:i(t(a).be("bar","outer")),style:f({height:`${e.strokeWidth}px`})},[y("div",{class:i([t(a).be("bar","inner"),{[t(a).bem("bar","inner","indeterminate")]:e.indeterminate}]),style:f(t(T))},[(e.showText||e.$slots.default)&&e.textInside?(l(),c("div",{key:0,class:i(t(a).be("bar","innerText"))},[N(e.$slots,"default",{percentage:e.percentage},()=>[y("span",null,_(t(S)),1)])],2)):I("v-if",!0)],6)],6)],2)):(l(),c("div",{key:1,class:i(t(a).b("circle")),style:f({height:`${e.width}px`,width:`${e.width}px`})},[(l(),c("svg",Z,[y("path",{class:i(t(a).be("circle","track")),d:t($),stroke:`var(${t(a).cssVarName("fill-color-light")}, #e5e9f2)`,"stroke-width":t(g),fill:"none",style:f(t(x))},null,14,ee),y("path",{class:i(t(a).be("circle","path")),d:t($),stroke:t(E),fill:"none",opacity:e.percentage?1:0,"stroke-linecap":e.strokeLinecap,"stroke-width":t(g),style:f(t(z))},null,14,te)]))],6)),(e.showText||e.$slots.default)&&!e.textInside?(l(),c("div",{key:2,class:i(t(a).e("text")),style:f({fontSize:`${t(W)}px`})},[N(e.$slots,"default",{percentage:e.percentage},()=>[e.status?(l(),B(t(X),{key:1},{default:K(()=>[(l(),B(R(t(F))))]),_:1})):(l(),c("span",se,_(t(S)),1))])],6)):I("v-if",!0)],10,Y))}});var oe=j(re,[["__file","/home/runner/work/element-plus/element-plus/packages/components/progress/src/progress.vue"]]);const le=q(oe);export{le as E};
