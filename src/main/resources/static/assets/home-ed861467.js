/* empty css             *//* empty css                *//* empty css                 *//* empty css                     *//* empty css                */import{g as J}from"./request-bd6a17b0.js";import{a as q,b as G,E as H}from"./index-512379a5.js";import{E as K}from"./index-4bf230a6.js";import{E as Q}from"./index-bc0c355f.js";import{a as X,d as Y,E as Z}from"./index-74b391fd.js";import{E as k}from"./index-0572cb9e.js";import{z as tt,h as c,aD as et,c as N,a as i,O as l,G as t,aj as st,o as g,V as n,W as ot,a6 as at,N as T,U as it,cw as lt,cx as nt,_ as ct}from"./index-6980f424.js";import"./axios-151dac55.js";import"./vue-router-cb191d1c.js";import"./index-93dd7534.js";const rt="/assets/jiqiren-44abcfbf.png",dt="/assets/chajian1-7f810b04.png",_t="/assets/xiaoxi-c754b8e1.png",pt="/assets/shijian-f63a4626.png",ht="/assets/App-5e29c401.png",o=f=>(lt("data-v-096d234c"),f=f(),nt(),f),ut={class:"main"},mt={class:"dataCard",style:{"background-color":"#33CABB"}},gt=o(()=>t("div",{class:"left"},[t("img",{src:rt,alt:"",style:{"margin-left":"30px",width:"50px",height:"50px"}})],-1)),ft={class:"right"},vt=o(()=>t("div",{class:"title"},"机器人总数",-1)),xt={class:"dataNum"},yt={class:"dataCard",style:{"background-color":"#F96868"}},Ct=o(()=>t("div",{class:"left"},[t("img",{src:dt,alt:"",style:{"margin-left":"30px",width:"50px",height:"50px"}})],-1)),wt={class:"right"},bt=o(()=>t("div",{class:"title"},"应用数量",-1)),Nt={class:"dataNum"},Ft={class:"dataCard",style:{"background-color":"#15C377"}},Et=o(()=>t("div",{class:"left"},[t("img",{src:_t,alt:"",style:{"margin-left":"30px",width:"50px",height:"50px"}})],-1)),At={class:"right"},It=o(()=>t("div",{class:"title"},"消息数量",-1)),St={class:"dataNum"},Bt={class:"dataCard",style:{"background-color":"#926DDE"}},Mt=o(()=>t("div",{class:"left"},[t("img",{src:pt,alt:"",style:{"margin-left":"30px",width:"50px",height:"50px"}})],-1)),Ut={class:"right"},Rt=o(()=>t("div",{class:"title"},"运行时间",-1)),Dt={class:"dataNum"},Tt={style:{display:"flex","justify-content":"space-around","flex-wrap":"wrap"}},Lt={class:"dataChart"},Pt=o(()=>t("div",{id:"botNum",style:{width:"200px",height:"200px"}},null,-1)),Ot={class:"chartTitle"},$t={class:"dataChart"},zt=o(()=>t("div",{id:"onLineBotNum",style:{width:"200px",height:"200px"}},null,-1)),Vt={class:"chartTitle"},jt={class:"dataChart"},Wt=o(()=>t("div",{id:"CPUOccupancy",style:{width:"200px",height:"200px"}},null,-1)),Jt={class:"chartTitle"},qt={class:"dataChart"},Gt=o(()=>t("div",{id:"RAMOccupancy",style:{width:"200px",height:"200px"}},null,-1)),Ht={class:"chartTitle"},Kt={class:"dataChart"},Qt=o(()=>t("div",{id:"virtualMemoryUsage",style:{width:"200px",height:"200px"}},null,-1)),Xt={class:"chartTitle"},Yt={class:"bottom"},Zt={class:"bLeft"},kt=o(()=>t("div",{class:"LTop",style:{"margin-top":"20px",overflow:"hidden"}},[t("div",{style:{"margin-top":"20px","margin-bottom":"10px","margin-left":"20px",color:"#31394D","font-size":"18px","font-weight":"700"}}," 热门APP")],-1)),te={class:"appItem"},ee=o(()=>t("img",{src:ht,alt:"",style:{width:"40px",height:"40px","border-radius":"20px","margin-left":"20px"}},null,-1)),se={class:"APPName",style:{"margin-left":"10px","margin-bottom":"3px",color:"#31394D","font-size":"12px","font-weight":"600"}},oe={class:"APPIntroduce",style:{"margin-left":"10px",color:"#748AA1","font-size":"12px","font-weight":"600"}},ae={style:{"margin-left":"20px","margin-top":"5px",color:"#748AA1","font-size":"14px"}},ie={style:{float:"right","margin-right":"55px"}},le={class:"bright",style:{width:"100%"}},ne={style:{height:"354px",width:"49%"}},ce={style:{height:"354px",width:"49%"}},re=tt({__name:"home",setup(f){const{proxy:L}=st(),F=c(""),E=c(""),A=c(""),I=c(""),S=c(),B=c(),x=c(),y=c(),C=c(),d=c(["","","","",""]),M=c([]);let U="";J().then(e=>{U=e.data,P(U)});let r=null;const P=e=>{r=new WebSocket("ws://103.91.209.111:20338/dash-board"),r.onopen=function(a){r.send(`{"event" : 1, "JSESSIONID" : "${e}"}`)},r.onmessage=function(a){let s=JSON.parse(a.data);F.value=s.data.botNum,E.value=s.data.plugInNUm,A.value=s.data.msgNum,I.value=V(s.data.runTime),S.value=Math.floor(s.data.onLineRate),B.value=Math.floor(s.data.messageResponseRate),x.value=Math.floor(s.data.cpuUsage),y.value=Math.floor(s.data.memoryUsage),C.value=Math.floor(s.data.virtualMemoryUsage),d.value=s.data.title,_("botNum",S.value,"在线BOT","%",p(0)),_("onLineBotNum",B.value,"消息回应率","%",p(0)),_("CPUOccupancy",x.value,"cpu占用率","%",p(x.value)),_("RAMOccupancy",y.value,"内存占用率","%",p(y.value)),_("virtualMemoryUsage",C.value,"JVM内存占用率","%",p(C.value)),$(s.extra),M.value=s.extra},r.onclose=function(a){},r.onerror=function(a){}},O=e=>"",_=(e,a,s,h,u)=>{var w=L.$echarts.init(document.getElementById(`${e}`)),m;const b=[{value:a,name:s,title:{offsetCenter:["0%","-30%"]}}];m={series:[{type:"gauge",color:[`${u}`],startAngle:90,endAngle:-270,pointer:{show:!1},progress:{show:!0,overlap:!1,roundCap:!0,clip:!1,itemStyle:{borderWidth:0,borderColor:"#464646"}},axisLine:{lineStyle:{width:10}},splitLine:{show:!1,distance:0,length:10},axisTick:{show:!1},axisLabel:{show:!1,distance:50},data:b,title:{fontSize:14},detail:{width:50,height:0,fontSize:20,color:"inherit",borderRadius:20,borderWidth:0,formatter:`{value}${h}`}}]},m&&w.setOption(m)},$=e=>{if(e.length==0)return;let a=e[0].responseNum;a==0&&(a=1),e.map(s=>{s.fomatResponseNum=s.responseNum/a*100})},z=(e,a)=>e==1?"#FC5B3F":e==2?"#FCA33F":e==3?"#FCDE3F":e>3&&e<=5?"#5FF1E8":e>5&&e<=10?"#C5FC3F":e>10&&e<=15?"#87FC3F":e>15&&e<=20?"#3FFCBF":e>20&&e<=25?"#3FA4FC":e>25&&e<=30?"#3F59FC":"#7E3FFC",V=e=>{const a=parseInt(e%864e5/36e5),s=parseInt(e%(1e3*60*60)/(1e3*60)),h=Math.floor(e%(1e3*60)/1e3);return`${a}时${s}分${h}秒`},p=e=>e<60?"#48B0F7":e>60&&e<80?"#FAA64B":"#F96868";return et(()=>{r!=null&&r.close()}),(e,a)=>{const s=q,h=G,u=K,w=H,m=Q,b=X,R=k,j=Y,W=Z;return g(),N("div",ut,[i(h,{gutter:20,style:{"margin-left":"20px"}},{default:l(()=>[i(s,{span:6},{default:l(()=>[t("div",mt,[gt,t("div",ft,[vt,t("div",xt,n(F.value),1)])])]),_:1}),i(s,{span:6},{default:l(()=>[t("div",yt,[Ct,t("div",wt,[bt,t("div",Nt,n(E.value),1)])])]),_:1}),i(s,{span:6},{default:l(()=>[t("div",Ft,[Et,t("div",At,[It,t("div",St,n(A.value),1)])])]),_:1}),i(s,{span:6},{default:l(()=>[t("div",Bt,[Mt,t("div",Ut,[Rt,t("div",Dt,n(I.value),1)])])]),_:1})]),_:1}),i(u,{class:"box-card",style:{"margin-left":"20px","margin-top":"20px"}},{default:l(()=>[t("div",Tt,[t("div",Lt,[Pt,t("div",Ot,n(d.value[0]),1)]),t("div",$t,[zt,t("div",Vt,n(d.value[1]),1)]),t("div",jt,[Wt,t("div",Jt,n(d.value[2]),1)]),t("div",qt,[Gt,t("div",Ht,n(d.value[3]),1)]),t("div",Kt,[Qt,t("div",Xt,n(d.value[4]),1)])])]),_:1}),t("div",Yt,[i(W,null,{default:l(()=>[i(b,{width:"600px"},{default:l(()=>[t("div",Zt,[kt,i(m,{height:"300px"},{default:l(()=>[(g(!0),N(ot,null,at(M.value,(v,D)=>(g(),N("div",{key:D,style:{"margin-bottom":"10px"}},[t("div",te,[ee,t("div",null,[t("div",se,n(v.basic.appName),1),t("div",oe,n(v.basic.appDesc),1)])]),t("div",null,[i(w,{percentage:v.fomatResponseNum,format:O,color:z(D+1,1)},null,8,["percentage","color"])]),t("div",ae,[it(" 总调用次数："),t("span",ie,n(v.responseNum),1)])]))),128))]),_:1})])]),_:1}),i(j,{style:{padding:"0",margin:"0"}},{default:l(()=>[t("div",le,[t("div",ne,[i(u,{class:"box-card",style:{height:"99%"}},{default:l(()=>[(g(),T(R,{key:0,description:"暂无数据"}))]),_:1})]),t("div",ce,[i(u,{class:"box-card",style:{height:"99%"}},{default:l(()=>[(g(),T(R,{key:0,description:"暂无数据"}))]),_:1})])])]),_:1})]),_:1})])])}}});const Fe=ct(re,[["__scopeId","data-v-096d234c"]]);export{Fe as default};
