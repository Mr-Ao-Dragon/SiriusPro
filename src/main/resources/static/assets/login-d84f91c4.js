/* empty css             *//* empty css                *//* empty css                     *//* empty css                 */import{v as S,e as c,aA as _,f as k,c as V,E as a,a as t,L as l,o as I,a5 as C,Q as F,bG as B,cx as N,cy as $}from"./index-12833456.js";import{_ as z}from"./Logo-52c96a9e.js";import{u as L,e as Q}from"./axios-0cfbca79.js";import{L as R}from"./request-54c3d3bc.js";import{E as q,a as A}from"./index-42ccba31.js";import{a as K,E as M}from"./index-373b4bc2.js";import{E as P}from"./index-bd0ed55f.js";import{_ as T}from"./_plugin-vue_export-helper-c27b6911.js";import"./index-3022843a.js";const g=n=>(N("data-v-0d699082"),n=n(),$(),n),U={id:"login"},D={class:"main"},G=g(()=>a("div",{class:"loginHead"},[a("div",{class:"logo"},[a("img",{src:z,style:{width:"46px",height:"46px"}})]),a("div",{class:"title"},"Sirius Pro")],-1)),H=g(()=>a("div",{class:"blurb"}," QQ频道最具影响力的机器人框架 ",-1)),J=S({__name:"login",setup(n){const i=L(),f=c("default"),y=c(),o=_({name:"",password:""}),w=Q(),m=JSON.parse(localStorage.getItem("token"));console.log(typeof m),console.log(m),m&&i.push("/index/home");const h=_({name:[{required:!0,message:"请输入账号",trigger:"blur"},{min:3,max:11,message:"账户长度为3-11位",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{min:3,max:11,message:"密码长度为3-11位",trigger:"blur"}]});Array.from({length:1e4}).map((s,e)=>({value:`${e+1}`,label:`${e+1}`}));const d=()=>{const s={account:o.name,passwd:o.password};R(s).then(e=>{console.log(e),e.code==0&&(i.push({path:"/index/home"}),B({showClose:!0,message:"登录成功",type:"success"}),console.log(w),localStorage.setItem("token","true"))})},x=()=>{document.onkeydown=s=>{s.defaultPrevented||(document.getElementsByTagName("body")[0],s.keyCode===13&&(console.log("按下了回车键"),d()))}};return k(()=>{x()}),(s,e)=>{const p=q,u=K,v=A,b=M,E=P;return I(),V("div",U,[a("div",D,[t(E,{class:"box-card"},{default:l(()=>[t(b,{ref_key:"ruleFormRef",ref:y,model:o,rules:h,"label-width":"80px",class:"demo-ruleForm",size:f.value,"status-icon":"",style:{height:"240px"}},{default:l(()=>[G,H,t(u,{label:"账号",prop:"name",style:{width:"300px"}},{default:l(()=>[t(p,{modelValue:o.name,"onUpdate:modelValue":e[0]||(e[0]=r=>o.name=r),style:{width:"200px"}},null,8,["modelValue"])]),_:1}),t(u,{label:"密码",prop:"password",style:{width:"300px"}},{default:l(()=>[t(p,{modelValue:o.password,"onUpdate:modelValue":e[1]||(e[1]=r=>o.password=r),style:{width:"200px"},type:"password","show-password":""},null,8,["modelValue"])]),_:1}),t(v,{type:"primary",class:"button",onClick:e[2]||(e[2]=r=>d()),onKeyup:e[3]||(e[3]=C(r=>d(),["enter"]))},{default:l(()=>[F("登录")]),_:1})]),_:1},8,["model","rules","size"])]),_:1})])])}}});const ne=T(J,[["__scopeId","data-v-0d699082"]]);export{ne as default};
