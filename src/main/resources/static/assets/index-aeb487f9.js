import{c as f,R as C,u as _,_ as k,d as E,E as P,w,C as S}from"./index-6295fd1d.js";import{g as c,b as y,L as $,d as x,o as s,c as u,k as g,n as t,u as r,K as j,a1 as K,j as N,p as d,q,v as z,z as A,P as D}from"./runtime-core.esm-bundler-a054f4a5.js";const B=Symbol("breadcrumbKey"),R=f({separator:{type:String,default:"/"},separatorIcon:{type:C}}),T=c({name:"ElBreadcrumb"}),L=c({...T,props:R,setup(l){const a=l,o=_("breadcrumb"),n=y();return $(B,a),x(()=>{const e=n.value.querySelectorAll(`.${o.e("item")}`);e.length&&e[e.length-1].setAttribute("aria-current","page")}),(e,m)=>(s(),u("div",{ref_key:"breadcrumb",ref:n,class:t(r(o).b()),"aria-label":"Breadcrumb",role:"navigation"},[g(e.$slots,"default")],2))}});var M=k(L,[["__file","/home/runner/work/element-plus/element-plus/packages/components/breadcrumb/src/breadcrumb.vue"]]);const O=f({to:{type:E([String,Object]),default:""},replace:{type:Boolean,default:!1}}),V=c({name:"ElBreadcrumbItem"}),F=c({...V,props:O,setup(l){const a=l,o=D(),n=j(B,void 0),e=_("breadcrumb"),{separator:m,separatorIcon:i}=K(n),p=o.appContext.config.globalProperties.$router,h=y(),I=()=>{!a.to||!p||(a.replace?p.replace(a.to):p.push(a.to))};return(b,G)=>(s(),u("span",{class:t(r(e).e("item"))},[N("span",{ref_key:"link",ref:h,class:t([r(e).e("inner"),r(e).is("link",!!b.to)]),role:"link",onClick:I},[g(b.$slots,"default")],2),r(i)?(s(),d(r(P),{key:0,class:t(r(e).e("separator"))},{default:q(()=>[(s(),d(z(r(i))))]),_:1},8,["class"])):(s(),u("span",{key:1,class:t(r(e).e("separator")),role:"presentation"},A(r(m)),3))],2))}});var v=k(F,[["__file","/home/runner/work/element-plus/element-plus/packages/components/breadcrumb/src/breadcrumb-item.vue"]]);const Q=w(M,{BreadcrumbItem:v}),U=S(v);export{Q as E,U as a};
