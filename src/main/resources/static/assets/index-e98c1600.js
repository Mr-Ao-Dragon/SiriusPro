import{B as n,O as w,C as c,g as d,o as i,c as u,J as p,K as _,u as t,M as m,L as h,N as S,am as f}from"./index-cc72fba1.js";const b=n({name:"ElContainer"}),B=n({...b,props:{direction:{type:String}},setup(a){const s=a,e=w(),o=c("container"),r=d(()=>s.direction==="vertical"?!0:s.direction==="horizontal"?!1:e&&e.default?e.default().some(g=>{const k=g.type.name;return k==="ElHeader"||k==="ElFooter"}):!1);return(l,g)=>(i(),u("section",{class:_([t(o).b(),t(o).is("vertical",t(r))])},[p(l.$slots,"default")],2))}});var C=m(B,[["__file","/home/runner/work/element-plus/element-plus/packages/components/container/src/container.vue"]]);const N=n({name:"ElAside"}),F=n({...N,props:{width:{type:String,default:null}},setup(a){const s=a,e=c("aside"),o=d(()=>s.width?e.cssVarBlock({width:s.width}):{});return(r,l)=>(i(),u("aside",{class:_(t(e).b()),style:h(t(o))},[p(r.$slots,"default")],6))}});var v=m(F,[["__file","/home/runner/work/element-plus/element-plus/packages/components/container/src/aside.vue"]]);const H=n({name:"ElFooter"}),M=n({...H,props:{height:{type:String,default:null}},setup(a){const s=a,e=c("footer"),o=d(()=>s.height?e.cssVarBlock({height:s.height}):{});return(r,l)=>(i(),u("footer",{class:_(t(e).b()),style:h(t(o))},[p(r.$slots,"default")],6))}});var E=m(M,[["__file","/home/runner/work/element-plus/element-plus/packages/components/container/src/footer.vue"]]);const V=n({name:"ElHeader"}),z=n({...V,props:{height:{type:String,default:null}},setup(a){const s=a,e=c("header"),o=d(()=>s.height?e.cssVarBlock({height:s.height}):{});return(r,l)=>(i(),u("header",{class:_(t(e).b()),style:h(t(o))},[p(r.$slots,"default")],6))}});var $=m(z,[["__file","/home/runner/work/element-plus/element-plus/packages/components/container/src/header.vue"]]);const A=n({name:"ElMain"}),x=n({...A,setup(a){const s=c("main");return(e,o)=>(i(),u("main",{class:_(t(s).b())},[p(e.$slots,"default")],2))}});var y=m(x,[["__file","/home/runner/work/element-plus/element-plus/packages/components/container/src/main.vue"]]);const J=S(C,{Aside:v,Footer:E,Header:$,Main:y}),K=f(v),L=f(E),O=f($),j=f(y);export{J as E,K as a,L as b,O as c,j as d};
