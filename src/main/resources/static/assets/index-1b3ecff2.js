/* empty css             *//* empty css                *//* empty css                     *//* empty css                 */import{_ as x}from"./Logo-52c96a9e.js";import{E as g,a as h}from"./index-42ccba31.js";import{a as C,E}from"./index-373b4bc2.js";import{E as N}from"./index-bd0ed55f.js";import{v as U,aA as P,e as c,c as S,E as s,a,L as o,o as j,G as f,Q as p,bG as b,cx as k,cy as A}from"./index-12833456.js";import{_ as I}from"./_plugin-vue_export-helper-c27b6911.js";import"./index-3022843a.js";const B=i=>(k("data-v-92778e0c"),i=i(),A(),i),D={id:"guide"},F={style:{width:"500px",height:"auto"}},T=B(()=>s("div",{class:"top"},[s("img",{src:x,alt:"",style:{width:"50px",height:"50px"}}),s("span",{style:{"font-size":"33px","line-height":"50px",color:"rgba(0, 0, 0, .85)","font-weight":"600"}}," 欢迎使用SiriusPro")],-1)),z={style:{display:"flex","justify-content":"center","align-items":"center"}},G={style:{display:"flex","justify-content":"end"}},L={style:{display:"flex","justify-content":"center","align-items":"center"}},q={style:{display:"flex","justify-content":"end"}},M=U({__name:"index",setup(i){const e=P({databaseDriven:"com.mysql.cj.jdbc.Driver",databaseUrl:"localhost:3306",databaseName:"siriuspro",databaseAccount:"",databasePasswrod:"",adminName:"",adminPassword:""}),m=c("oneStepTrue"),r=c("twoFalse"),y=()=>{e.databaseAccount!=""&&e.databaseDriven!=""&&e.databaseName!=""&&e.databasePasswrod!=""&&e.databaseUrl!=""?(m.value="oneStepFalse",r.value="twoTrue"):b({showClose:!0,message:"请填写完整",type:"error"})},v=()=>{m.value="oneStepTrue",r.value="twoFalse"},w=()=>{e.adminName!=""&&e.adminPassword!=""||b({showClose:!0,message:"请填写完整",type:"error"})};return(Q,t)=>{const d=g,n=C,u=h,_=E,V=N;return j(),S("div",D,[s("div",F,[a(V,{class:"box-card",style:{width:"100%",height:"100%"}},{default:o(()=>[T,s("div",{class:f(m.value)},[s("div",z,[a(_,{"label-position":"top","label-width":"100px",model:e,style:{width:"80%"}},{default:o(()=>[a(n,{label:"数据库驱动"},{default:o(()=>[a(d,{modelValue:e.databaseDriven,"onUpdate:modelValue":t[0]||(t[0]=l=>e.databaseDriven=l)},null,8,["modelValue"])]),_:1}),a(n,{label:"数据地址"},{default:o(()=>[a(d,{modelValue:e.databaseUrl,"onUpdate:modelValue":t[1]||(t[1]=l=>e.databaseUrl=l)},null,8,["modelValue"])]),_:1}),a(n,{label:"数据库名"},{default:o(()=>[a(d,{modelValue:e.databaseName,"onUpdate:modelValue":t[2]||(t[2]=l=>e.databaseName=l)},null,8,["modelValue"])]),_:1}),a(n,{label:"数据库账号"},{default:o(()=>[a(d,{modelValue:e.databaseAccount,"onUpdate:modelValue":t[3]||(t[3]=l=>e.databaseAccount=l)},null,8,["modelValue"])]),_:1}),a(n,{label:"数据库密码"},{default:o(()=>[a(d,{modelValue:e.databasePasswrod,"onUpdate:modelValue":t[4]||(t[4]=l=>e.databasePasswrod=l)},null,8,["modelValue"])]),_:1}),s("div",G,[a(u,{type:"primary",onClick:y},{default:o(()=>[p("下一步")]),_:1})])]),_:1},8,["model"])])],2),s("div",{class:f(r.value)},[s("div",L,[a(_,{"label-position":"top","label-width":"100px",model:e,style:{width:"80%"}},{default:o(()=>[a(n,{label:"管理员账号"},{default:o(()=>[a(d,{modelValue:e.adminName,"onUpdate:modelValue":t[5]||(t[5]=l=>e.adminName=l)},null,8,["modelValue"])]),_:1}),a(n,{label:"管理员密码"},{default:o(()=>[a(d,{modelValue:e.adminPassword,"onUpdate:modelValue":t[6]||(t[6]=l=>e.adminPassword=l)},null,8,["modelValue"])]),_:1}),s("div",q,[a(u,{type:"primary",onClick:v},{default:o(()=>[p("上一步")]),_:1}),a(u,{type:"primary",onClick:w},{default:o(()=>[p("提交")]),_:1})])]),_:1},8,["model"])])],2)]),_:1})])])}}});const ae=I(M,[["__scopeId","data-v-92778e0c"]]);export{ae as default};