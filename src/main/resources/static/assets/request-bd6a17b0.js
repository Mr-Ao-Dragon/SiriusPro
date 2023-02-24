import{r as i,a as d}from"./axios-151dac55.js";import{c as l,b as u}from"./vue-router-cb191d1c.js";import{a as b}from"./index-93dd7534.js";l({history:u(),routes:i});const e=d.create({baseURL:"/api",timeout:1e4,withCredentials:!0,headers:{"Content-Type":"application/x-www-form-urlencoded"}});e.interceptors.request.use(t=>t,t=>Promise.reject(t));e.interceptors.response.use(t=>{if(t.status==200&&t.data.code==0)return t.data;if(t.data.code==90002)console.log("未登录，无权限使用!"),window.location.href="/#/login";else{b({showClose:!0,message:t.data.msg,type:"error"});const o=new Error("接口请求异常...");return Promise.reject(o)}},t=>Promise.reject(t));const B=l({history:u(),routes:i}),s=d.create({baseURL:"/api",timeout:1e4,withCredentials:!0});s.interceptors.request.use(t=>t,t=>Promise.reject(t));s.interceptors.response.use(t=>{if(console.log("响应拦截",t),t.status==200&&t.data.code==0)return t.data;if(t.data.msg=="未登录，无权限使用!")console.log("未登录，无权限使用!"),B.beforeEach((o,n,r)=>{o.name=="login"?r():r("/login")});else{const o=new Error("接口请求异常...");return Promise.reject(o)}},t=>Promise.reject(t));const a={login:"/admin/login",all:"/bot/get-database-all",addBot:"/bot/add",editBot:"/bot/modify",eventBot:"/bot/set-intents",deleteBot:"/bot/del",lookBot:"/bot/get-database-all",loginBot:"/bot/login",logOutBot:"/bot/logout",getHomeData:"/plugin-manage/statistics",AppArr:"/plugin-manage/info-list",sessionId:"/plugin-manage/get-session-id"};localStorage.getItem("token");const f=t=>e({url:`${a.login}`,method:"POST",data:t}),P=t=>e({url:`${a.addBot}`,method:"POST",data:t}),w=t=>e({url:`${a.editBot}`,method:"POST",data:t}),O=t=>s({url:`${a.eventBot}`,method:"POST",data:t}),T=t=>e({url:`${a.deleteBot}`,method:"POST",data:{id:t}}),E=(t,o,n,r,c,g,m)=>e({url:`${a.lookBot}?page=${t}&size=${o}&bot_id=${n}&username=${r}&state=${c}&bot_type=${g}&sand_box=${m}`,method:"GET"}),S=t=>e({url:`${a.loginBot}`,data:{bot_id:t},method:"POST"}),y=t=>e({url:`${a.logOutBot}`,method:"POST",data:{bot_id:t}}),j=()=>e({url:`${a.sessionId}`,method:"GET"});export{P as A,O as E,f as L,S as a,y as b,w as c,T as d,j as g,E as l};
