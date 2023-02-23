import{r as i,b as l,a as b}from"./axios-f0bb083c.js";import{b as d,cB as u}from"./index-cc72fba1.js";d({history:u(),routes:i});const o=l.create({baseURL:"/api",timeout:1e4,withCredentials:!0,headers:{"Content-Type":"application/x-www-form-urlencoded"}});o.interceptors.request.use(t=>t,t=>Promise.reject(t));o.interceptors.response.use(t=>{if(t.status==200&&t.data.code==0)return t.data;if(t.data.code==90002)console.log("未登录，无权限使用!"),window.location.href="/#/login";else{b({showClose:!0,message:t.data.msg,type:"error"});const e=new Error("接口请求异常...");return Promise.reject(e)}},t=>Promise.reject(t));const B=d({history:u(),routes:i}),s=l.create({baseURL:"/api",timeout:1e4,withCredentials:!0});s.interceptors.request.use(t=>t,t=>Promise.reject(t));s.interceptors.response.use(t=>{if(console.log("响应拦截",t),t.status==200&&t.data.code==0)return t.data;if(t.data.msg=="未登录，无权限使用!")console.log("未登录，无权限使用!"),B.beforeEach((e,n,a)=>{e.name=="login"?a():a("/login")});else{const e=new Error("接口请求异常...");return Promise.reject(e)}},t=>Promise.reject(t));const r={login:"/admin/login",all:"/bot/get-database-all",addBot:"/bot/add",editBot:"/bot/modify",eventBot:"/bot/set-intents",deleteBot:"/bot/del",lookBot:"/bot/get-database-all",loginBot:"/bot/login",logOutBot:"/bot/logout",getHomeData:"/plugin-manage/statistics",AppArr:"/plugin-manage/info-list"};localStorage.getItem("token");const P=t=>o({url:`${r.login}`,method:"POST",data:t}),f=t=>o({url:`${r.addBot}`,method:"POST",data:t}),p=t=>o({url:`${r.editBot}`,method:"POST",data:t}),w=t=>s({url:`${r.eventBot}`,method:"POST",data:t}),O=t=>o({url:`${r.deleteBot}`,method:"POST",data:{id:t}}),T=(t,e,n,a,c,m,g)=>o({url:`${r.lookBot}?page=${t}&size=${e}&bot_id=${n}&username=${a}&state=${c}&bot_type=${m}&sand_box=${g}`,method:"GET"}),E=t=>o({url:`${r.loginBot}`,data:{bot_id:t},method:"POST"}),S=t=>o({url:`${r.logOutBot}`,method:"POST",data:{bot_id:t}});export{f as A,w as E,P as L,E as a,S as b,p as c,O as d,T as l};