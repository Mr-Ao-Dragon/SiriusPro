import{g as n,n as p,v as d,x as c,o,c as t,G as r,u as s,F as l,Q as i,R as u,P as y,E as m,H as f,_ as h,I as v}from"./index-12833456.js";const S=n({header:{type:String,default:""},bodyStyle:{type:p([String,Object,Array]),default:""},shadow:{type:String,values:["always","hover","never"],default:"always"}}),_=d({name:"ElCard"}),w=d({..._,props:S,setup(g){const a=c("card");return(e,C)=>(o(),t("div",{class:r([s(a).b(),s(a).is(`${e.shadow}-shadow`)])},[e.$slots.header||e.header?(o(),t("div",{key:0,class:r(s(a).e("header"))},[l(e.$slots,"header",{},()=>[i(u(e.header),1)])],2)):y("v-if",!0),m("div",{class:r(s(a).e("body")),style:f(e.bodyStyle)},[l(e.$slots,"default")],6)],2))}});var b=h(w,[["__file","/home/runner/work/element-plus/element-plus/packages/components/card/src/card.vue"]]);const E=v(b);export{E};
