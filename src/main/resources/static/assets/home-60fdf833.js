/* empty css             *//* empty css                *//* empty css                 *//* empty css                     *//* empty css                */import{g as k,a as J}from"./request-486a9ba7.js";import{a as q,b as G,E as H}from"./index-512379a5.js";import{E as K}from"./index-4bf230a6.js";import{E as Q}from"./index-bc0c355f.js";import{a as X,d as Y,E as Z}from"./index-74b391fd.js";import{E as tt}from"./index-0572cb9e.js";import{z as et,h as c,c as w,a,O as l,G as t,o as m,V as n,W as st,a6 as ot,N as T,aj as at,U as it,cw as lt,cx as nt,_ as ct}from"./index-6980f424.js";import"./axios-3b5e605b.js";import"./vue-router-cb191d1c.js";import"./index-93dd7534.js";const rt="/assets/jiqiren-44abcfbf.png",dt="/assets/chajian1-7f810b04.png",_t="/assets/xiaoxi-c754b8e1.png",pt="/assets/shijian-f63a4626.png",ht="/assets/App-5e29c401.png",s=g=>(lt("data-v-66867e84"),g=g(),nt(),g),ut={class:"main"},mt={class:"dataCard",style:{"background-color":"#33CABB"}},gt=s(()=>t("div",{class:"left"},[t("img",{src:rt,alt:"",style:{"margin-left":"30px",width:"50px",height:"50px"}})],-1)),vt={class:"right"},ft=s(()=>t("div",{class:"title"},"机器人总数",-1)),xt={class:"dataNum"},yt={class:"dataCard",style:{"background-color":"#F96868"}},Ct=s(()=>t("div",{class:"left"},[t("img",{src:dt,alt:"",style:{"margin-left":"30px",width:"50px",height:"50px"}})],-1)),bt={class:"right"},wt=s(()=>t("div",{class:"title"},"应用数量",-1)),Nt={class:"dataNum"},Ft={class:"dataCard",style:{"background-color":"#15C377"}},At=s(()=>t("div",{class:"left"},[t("img",{src:_t,alt:"",style:{"margin-left":"30px",width:"50px",height:"50px"}})],-1)),Et={class:"right"},It=s(()=>t("div",{class:"title"},"消息数量",-1)),Bt={class:"dataNum"},Mt={class:"dataCard",style:{"background-color":"#926DDE"}},Rt=s(()=>t("div",{class:"left"},[t("img",{src:pt,alt:"",style:{"margin-left":"30px",width:"50px",height:"50px"}})],-1)),St={class:"right"},Ut=s(()=>t("div",{class:"title"},"运行时间",-1)),Dt={class:"dataNum"},Tt={style:{display:"flex","justify-content":"space-around","flex-wrap":"wrap"}},Lt={class:"dataChart"},Pt=s(()=>t("div",{id:"botNum",style:{width:"200px",height:"200px"}},null,-1)),Ot={class:"chartTitle"},zt={class:"dataChart"},Vt=s(()=>t("div",{id:"onLineBotNum",style:{width:"200px",height:"200px"}},null,-1)),$t={class:"chartTitle"},jt={class:"dataChart"},Wt=s(()=>t("div",{id:"CPUOccupancy",style:{width:"200px",height:"200px"}},null,-1)),kt={class:"chartTitle"},Jt={class:"dataChart"},qt=s(()=>t("div",{id:"RAMOccupancy",style:{width:"200px",height:"200px"}},null,-1)),Gt={class:"chartTitle"},Ht={class:"dataChart"},Kt=s(()=>t("div",{id:"virtualMemoryUsage",style:{width:"200px",height:"200px"}},null,-1)),Qt={class:"chartTitle"},Xt={class:"bottom"},Yt={class:"bLeft"},Zt=s(()=>t("div",{class:"LTop",style:{"margin-top":"20px",overflow:"hidden"}},[t("div",{style:{"margin-top":"20px","margin-bottom":"10px","margin-left":"20px",color:"#31394D","font-size":"18px","font-weight":"700"}}," 热门APP")],-1)),te={class:"appItem"},ee=s(()=>t("img",{src:ht,alt:"",style:{width:"40px",height:"40px","border-radius":"20px","margin-left":"20px"}},null,-1)),se={class:"APPName",style:{"margin-left":"10px","margin-bottom":"3px",color:"#31394D","font-size":"12px","font-weight":"600"}},oe={class:"APPIntroduce",style:{"margin-left":"10px",color:"#748AA1","font-size":"12px","font-weight":"600"}},ae={style:{"margin-left":"20px","margin-top":"5px",color:"#748AA1","font-size":"14px"}},ie={style:{float:"right","margin-right":"55px"}},le={class:"bright",style:{width:"100%"}},ne={style:{height:"354px",width:"49%"}},ce={style:{height:"354px",width:"49%"}},re=et({__name:"home",setup(g){let L=(e=>{const i=e+"=";var o=document.cookie.split(";");console.log(document.cookie);for(var r=0;r<o.length;r++){var d=o[r].trim();if(d.indexOf(i)==0)return d.substring(i.length,d.length)}return""})("JSESSIONID");console.log(L,"cookie"),new WebSocket("ws://103.91.209.111:20338/dash-board");const{proxy:P}=at(),N=c(""),F=c(""),A=c(""),E=c(""),I=c(),B=c(),f=c(),x=c(),y=c(),_=c(["","","","",""]),M=c([]),R=()=>{k().then(e=>{N.value=e.data.botNum,F.value=e.data.plugInNUm,A.value=e.data.msgNum,E.value=$(e.data.runTime),I.value=Math.floor(e.data.onLineRate),B.value=Math.floor(e.data.messageResponseRate),f.value=Math.floor(e.data.cpuUsage),x.value=Math.floor(e.data.memoryUsage),y.value=Math.floor(e.data.virtualMemoryUsage),_.value=e.data.title,p("botNum",I.value,"在线BOT","%",h(0)),p("onLineBotNum",B.value,"消息回应率","%",h(0)),p("CPUOccupancy",f.value,"cpu占用率","%",h(f.value)),p("RAMOccupancy",x.value,"内存占用率","%",h(x.value)),p("virtualMemoryUsage",y.value,"JVM内存占用率","%",h(y.value))}),S()},S=()=>{J().then(e=>{console.log(e),z(e),M.value=e.data})};R(),S(),setInterval(R,1e3);const O=e=>"",p=(e,i,o,r,d)=>{var C=P.$echarts.init(document.getElementById(`${e}`)),u;const b=[{value:i,name:o,title:{offsetCenter:["0%","-30%"]}}];u={series:[{type:"gauge",color:[`${d}`],startAngle:90,endAngle:-270,pointer:{show:!1},progress:{show:!0,overlap:!1,roundCap:!0,clip:!1,itemStyle:{borderWidth:0,borderColor:"#464646"}},axisLine:{lineStyle:{width:10}},splitLine:{show:!1,distance:0,length:10},axisTick:{show:!1},axisLabel:{show:!1,distance:50},data:b,title:{fontSize:14},detail:{width:50,height:0,fontSize:20,color:"inherit",borderRadius:20,borderWidth:0,formatter:`{value}${r}`}}]},u&&C.setOption(u)},z=e=>{if(e.data.length==0)return;let i=e.data[0].responseNum;i==0&&(i=1),e.data.map(o=>{o.fomatResponseNum=o.responseNum/i*100})},V=(e,i)=>e==1?"#FC5B3F":e==2?"#FCA33F":e==3?"#FCDE3F":e>3&&e<=5?"#5FF1E8":e>5&&e<=10?"#C5FC3F":e>10&&e<=15?"#87FC3F":e>15&&e<=20?"#3FFCBF":e>20&&e<=25?"#3FA4FC":e>25&&e<=30?"#3F59FC":"#7E3FFC",$=e=>{const i=parseInt(e%864e5/36e5),o=parseInt(e%(1e3*60*60)/(1e3*60)),r=Math.floor(e%(1e3*60)/1e3);return`${i}时${o}分${r}秒`},h=e=>e<60?"#48B0F7":e>60&&e<80?"#FAA64B":"#F96868";return(e,i)=>{const o=q,r=G,d=K,C=H,u=Q,b=X,U=tt,j=Y,W=Z;return m(),w("div",ut,[a(r,{gutter:20,style:{"margin-left":"20px"}},{default:l(()=>[a(o,{span:6},{default:l(()=>[t("div",mt,[gt,t("div",vt,[ft,t("div",xt,n(N.value),1)])])]),_:1}),a(o,{span:6},{default:l(()=>[t("div",yt,[Ct,t("div",bt,[wt,t("div",Nt,n(F.value),1)])])]),_:1}),a(o,{span:6},{default:l(()=>[t("div",Ft,[At,t("div",Et,[It,t("div",Bt,n(A.value),1)])])]),_:1}),a(o,{span:6},{default:l(()=>[t("div",Mt,[Rt,t("div",St,[Ut,t("div",Dt,n(E.value),1)])])]),_:1})]),_:1}),a(d,{class:"box-card",style:{"margin-left":"20px","margin-top":"20px"}},{default:l(()=>[t("div",Tt,[t("div",Lt,[Pt,t("div",Ot,n(_.value[0]),1)]),t("div",zt,[Vt,t("div",$t,n(_.value[1]),1)]),t("div",jt,[Wt,t("div",kt,n(_.value[2]),1)]),t("div",Jt,[qt,t("div",Gt,n(_.value[3]),1)]),t("div",Ht,[Kt,t("div",Qt,n(_.value[4]),1)])])]),_:1}),t("div",Xt,[a(W,null,{default:l(()=>[a(b,{width:"600px"},{default:l(()=>[t("div",Yt,[Zt,a(u,{height:"300px"},{default:l(()=>[(m(!0),w(st,null,ot(M.value,(v,D)=>(m(),w("div",{key:D,style:{"margin-bottom":"10px"}},[t("div",te,[ee,t("div",null,[t("div",se,n(v.basic.appName),1),t("div",oe,n(v.basic.appDesc),1)])]),t("div",null,[a(C,{percentage:v.fomatResponseNum,format:O,color:V(D+1,1)},null,8,["percentage","color"])]),t("div",ae,[it(" 总调用次数："),t("span",ie,n(v.responseNum),1)])]))),128))]),_:1})])]),_:1}),a(j,{style:{padding:"0",margin:"0"}},{default:l(()=>[t("div",le,[t("div",ne,[a(d,{class:"box-card",style:{height:"99%"}},{default:l(()=>[(m(),T(U,{key:0,description:"暂无数据"}))]),_:1})]),t("div",ce,[a(d,{class:"box-card",style:{height:"99%"}},{default:l(()=>[(m(),T(U,{key:0,description:"暂无数据"}))]),_:1})])])]),_:1})]),_:1})])])}}});const Ae=ct(re,[["__scopeId","data-v-66867e84"]]);export{Ae as default};