/* empty css             *//* empty css                *//* empty css                 *//* empty css                  */import{v as S,e as _,aB as g,f as k,c as V,E as r,a as t,M as l,o as I,a6 as B,R as C,cw as F,cx as N,_ as R}from"./index-2501712c.js";import{_ as $}from"./Logo-52c96a9e.js";import{u as z}from"./axios-b16c6b44.js";import{u as M}from"./vue-router-b22c65b9.js";import{L as q}from"./request-294c7cac.js";import{E as K,e as L,d as P}from"./index-015e0645.js";import{E as Q}from"./index-19c540e2.js";import{E as T}from"./index-2381bf8f.js";import{a as U}from"./index-4cf26bfb.js";const f=n=>(F("data-v-e1326def"),n=n(),N(),n),A={id:"login"},D={class:"main"},H=f(()=>r("div",{class:"loginHead"},[r("div",{class:"logo"},[r("img",{src:$,style:{width:"46px",height:"46px"}})]),r("div",{class:"title"},"Sirius Pro")],-1)),J=f(()=>r("div",{class:"blurb"}," QQ频道最具影响力的机器人框架 ",-1)),O=S({__name:"login",setup(n){const d=M(),w=_("default"),y=_(),o=g({name:"",password:""}),i=z(),p=JSON.parse(localStorage.getItem("token"));console.log(p),p&&d.push("/index/home");const h=g({name:[{required:!0,message:"请输入账号",trigger:"blur"},{min:3,max:11,message:"账户长度为3-11位",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{min:3,max:11,message:"密码长度为3-11位",trigger:"blur"}]});Array.from({length:1e4}).map((s,e)=>({value:`${e+1}`,label:`${e+1}`}));const m=()=>{const s={account:o.name,passwd:o.password};q(s).then(e=>{console.log(e),e.code==0&&(d.push({path:"/index/home"}),U({showClose:!0,message:"登录成功",type:"success"}),i.commit("loginState",!0),console.log(i),localStorage.setItem("token","true"))})},x=()=>{document.onkeydown=s=>{s.defaultPrevented||(document.getElementsByTagName("body")[0],s.keyCode===13&&(console.log("按下了回车键"),m()))}};return k(()=>{x()}),(s,e)=>{const u=K,c=L,v=Q,b=P,E=T;return I(),V("div",A,[r("div",D,[t(E,{class:"box-card"},{default:l(()=>[t(b,{ref_key:"ruleFormRef",ref:y,model:o,rules:h,"label-width":"80px",class:"demo-ruleForm",size:w.value,"status-icon":"",style:{height:"240px"}},{default:l(()=>[H,J,t(c,{label:"账号",prop:"name",style:{width:"300px"}},{default:l(()=>[t(u,{modelValue:o.name,"onUpdate:modelValue":e[0]||(e[0]=a=>o.name=a),style:{width:"200px"}},null,8,["modelValue"])]),_:1}),t(c,{label:"密码",prop:"password",style:{width:"300px"}},{default:l(()=>[t(u,{modelValue:o.password,"onUpdate:modelValue":e[1]||(e[1]=a=>o.password=a),style:{width:"200px"},type:"password","show-password":""},null,8,["modelValue"])]),_:1}),t(v,{type:"primary",class:"button",onClick:e[2]||(e[2]=a=>m()),onKeyup:e[3]||(e[3]=B(a=>m(),["enter"]))},{default:l(()=>[C("登录")]),_:1})]),_:1},8,["model","rules","size"])]),_:1})])])}}});const ne=R(O,[["__scopeId","data-v-e1326def"]]);export{ne as default};
