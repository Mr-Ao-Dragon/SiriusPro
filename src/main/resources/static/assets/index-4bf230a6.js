import{k as n,v as p,z as d,A as c,o,c as t,I as r,u as s,H as l,U as i,V as u,S as y,G as m,J as f,K as h,L as v}from"./index-6980f424.js";const S=n({header:{type:String,default:""},bodyStyle:{type:p([String,Object,Array]),default:""},shadow:{type:String,values:["always","hover","never"],default:"always"}}),w=d({name:"ElCard"}),_=d({...w,props:S,setup(k){const a=c("card");return(e,C)=>(o(),t("div",{class:r([s(a).b(),s(a).is(`${e.shadow}-shadow`)])},[e.$slots.header||e.header?(o(),t("div",{key:0,class:r(s(a).e("header"))},[l(e.$slots,"header",{},()=>[i(u(e.header),1)])],2)):y("v-if",!0),m("div",{class:r(s(a).e("body")),style:f(e.bodyStyle)},[l(e.$slots,"default")],6)],2))}});var b=h(_,[["__file","/home/runner/work/element-plus/element-plus/packages/components/card/src/card.vue"]]);const E=v(b);export{E};