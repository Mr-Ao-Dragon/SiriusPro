import{r as n,b as d,a as b}from"./axios-a216ff0a.js";import{b as i,cy as c}from"./index-ca28b35f.js";const B=i({history:c(),routes:n}),o=d.create({baseURL:"/api",timeout:1e4,withCredentials:!0,headers:{"Content-Type":"application/x-www-form-urlencoded"}});o.interceptors.request.use(t=>t,t=>Promise.reject(t));o.interceptors.response.use(t=>{if(console.log("响应拦截",t),t.status==200&&t.data.code==0)return t.data;if(t.data.code==90002)console.log("未登录，无权限使用!"),B.replace({path:"/login"});else{b({showClose:!0,message:t.data.msg,type:"error"});const e=new Error("接口请求异常...");return Promise.reject(e)}},t=>Promise.reject(t));const h=i({history:c(),routes:n}),s=d.create({baseURL:"/api",timeout:1e4,withCredentials:!0});s.interceptors.request.use(t=>t,t=>Promise.reject(t));s.interceptors.response.use(t=>{if(console.log("响应拦截",t),t.status==200&&t.data.code==0)return t.data;if(t.data.msg=="未登录，无权限使用!")console.log("未登录，无权限使用!"),h.beforeEach((e,l,a)=>{e.name=="login"?a():a("/login")});else{const e=new Error("接口请求异常...");return Promise.reject(e)}},t=>Promise.reject(t));const r={login:"/admin/login",all:"/bot/get-database-all",addBot:"/bot/add",editBot:"/bot/modify",eventBot:"/bot/set-intents",deleteBot:"/bot/del",lookBot:"/bot/get-database-all",loginBot:"/bot/login",logOutBot:"/bot/logout"};localStorage.getItem("token");const f=t=>o({url:`${r.login}`,method:"POST",data:t}),p=t=>o({url:`${r.addBot}`,method:"POST",data:t}),O=t=>o({url:`${r.editBot}`,method:"POST",data:t}),T=t=>s({url:`${r.eventBot}`,method:"POST",data:t}),w=t=>o({url:`${r.deleteBot}`,method:"POST",data:{id:t}}),y=(t,e,l,a,u,m,g)=>o({url:`${r.lookBot}?page=${t}&size=${e}&bot_id=${l}&username=${a}&state=${u}&bot_type=${m}&sand_box=${g}`,method:"GET"}),E=t=>o({url:`${r.loginBot}`,data:{bot_id:t},method:"POST"}),S=t=>o({url:`${r.logOutBot}`,method:"POST",data:{bot_id:t}});export{p as A,T as E,f as L,E as a,S as b,O as c,w as d,y as l};