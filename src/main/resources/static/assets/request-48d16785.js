import{r as i,u as h,a as d}from"./axios-40c7c303.js";import{c as l,b as c}from"./vue-router-57d62bcf.js";import{b}from"./index-c3904ec7.js";l({history:c(),routes:i});h();const e=d.create({baseURL:"/api",timeout:1e4,withCredentials:!0,headers:{"Content-Type":"application/x-www-form-urlencoded"}});e.interceptors.request.use(t=>t,t=>Promise.reject(t));e.interceptors.response.use(t=>{if(t.status==200&&t.data.code==0)return t.data;if(t.data.code==90002)console.log("未登录，无权限使用!"),localStorage.setItem("token","false"),window.location.href="/#/login";else{b({showClose:!0,message:t.data.msg,type:"error"});const a=new Error("接口请求异常...");return Promise.reject(a)}},t=>Promise.reject(t));const B=l({history:c(),routes:i}),r=d.create({baseURL:"/api",timeout:1e4,withCredentials:!0});r.interceptors.request.use(t=>t,t=>Promise.reject(t));r.interceptors.response.use(t=>{if(console.log("响应拦截",t),t.status==200&&t.data.code==0)return t.data;if(t.data.msg=="未登录，无权限使用!")console.log("未登录，无权限使用!"),B.beforeEach((a,n,s)=>{a.name=="login"?s():s("/login")});else{const a=new Error("接口请求异常...");return Promise.reject(a)}},t=>Promise.reject(t));const o={login:"/admin/login",all:"/bot/get-database-all",addBot:"/bot/add",editBot:"/bot/modify",eventBot:"/bot/set-intents",deleteBot:"/bot/del",lookBot:"/bot/get-database-all",loginBot:"/bot/login",logOutBot:"/bot/logout",getHomeData:"/plugin-manage/statistics",AppArr:"/plugin-manage/info-list",sessionId:"/plugin-manage/get-session-id",changePassword:"/admin/change-password",varyEmo:"/text/escape"};localStorage.getItem("token");const f=t=>e({url:`${o.login}`,method:"POST",data:t}),w=t=>e({url:`${o.addBot}`,method:"POST",data:t}),S=t=>e({url:`${o.editBot}`,method:"POST",data:t}),E=t=>r({url:`${o.eventBot}`,method:"POST",data:t}),O=t=>e({url:`${o.deleteBot}`,method:"POST",data:{id:t}}),T=(t,a,n,s,u,m,g)=>e({url:`${o.lookBot}?page=${t}&size=${a}&bot_id=${n}&username=${s}&state=${u}&bot_type=${m}&sand_box=${g}`,method:"GET"}),y=t=>e({url:`${o.loginBot}`,data:{bot_id:t},method:"POST"}),j=t=>e({url:`${o.logOutBot}`,method:"POST",data:{bot_id:t}}),v=()=>e({url:`${o.sessionId}`,method:"GET"}),I=t=>e({url:`${o.changePassword}`,method:"POST",data:t}),k=t=>e({url:`${o.varyEmo}`,method:"POST",data:t});export{w as A,E,f as L,y as a,j as b,I as c,S as d,O as e,v as g,T as l,k as v};
