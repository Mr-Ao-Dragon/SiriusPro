/* empty css             *//* empty css                *//* empty css                     *//* empty css                 *//* empty css                        *//* empty css                     *//* empty css                   *//* empty css                 *//* empty css                  *//* empty css                  */import{l as q,A as Le,E as X,d as Me,a as Z,b as ee,e as le}from"./request-da1fb2e3.js";import{E as Ne,as as De,at as Oe,au as Re,av as ze,R as Ge,a6 as qe}from"./index-c5a764ed.js";import{m as Pe,d as i,Y as Qe,o as s,c as p,t as n,a as t,G as d,C as a,I as L,M as D,B as f,u as g,D as He,H as je,F as h,av as Ye,bg as Je,bh as Ke}from"./plugin-vue_export-helper-ac789cde.js";import{E as We}from"./index-bb0329f1.js";import{m as Xe,k as Ze,v as el,g as ll,u as tl}from"./index-80409ba9.js";import{E as ol,a as al}from"./index-2e11e33c.js";import{E as nl}from"./index-ff2930d8.js";import{E as sl}from"./index-5e1b6380.js";import{a as ul,E as il,v as rl,b as dl}from"./index-a016770d.js";import{E as cl}from"./index-ba2ce393.js";import{E as pl}from"./index-14f7352f.js";import{a as ml,E as vl}from"./index-7b34ca08.js";import{l as _l}from"./index-ada52e14.js";import{a as m}from"./index-40b99311.js";import{_ as fl}from"./_plugin-vue_export-helper-c27b6911.js";import"./axios-d1decb8a.js";import"./vue-router-5b41d2eb.js";import"./index-326be066.js";import"./event-9519ab40.js";import"./index-cee436c5.js";import"./index-e1439c20.js";import"./_Uint8Array-a66de112.js";const _=O=>(Je("data-v-56094544"),O=O(),Ke(),O),gl={class:"main"},yl={class:"head",style:{"margin-left":"1%","background-color":"#fff","margin-top":"1%",padding:"0,20px","margin-right":"1%","overflow-x":"auto"}},bl={class:"ipt"},hl=_(()=>n("span",{class:"iptTitle"},"BotID",-1)),xl={class:"ipt"},kl=_(()=>n("span",{class:"iptTitle"},"昵称",-1)),El={class:"ipt"},wl=_(()=>n("span",{class:"iptTitle",style:{float:"left","margin-top":"5px"}},"运行状态",-1)),Vl={class:"ipt"},Cl=_(()=>n("span",{class:"iptTitle",style:{float:"left","margin-top":"5px"}},"Bot类型",-1)),Bl={class:"ipt"},Il=_(()=>n("span",{class:"iptTitle",style:{"padding-right":"5px"}},"沙盒模式",-1)),Sl={style:{width:"150px",height:"50px",float:"left","margin-top":"20px","margin-left":"58px"},class:"button1"},Tl={class:"",style:{"margin-left":"1%","background-color":"#fff","margin-top":"20px",padding:"0,20px","margin-right":"1%",overflow:"auto"}},Al={style:{padding:"20px"}},Ul=_(()=>n("div",{style:{float:"left"}},"机器人列表",-1)),Fl={style:{float:"right"}},$l={key:0,style:{color:"#B7B7B7"}},Ll={key:1},Ml={key:0},Nl=_(()=>n("span",{style:{"background-color":"#B7B7B7","border-radius":"50%",display:"inline-block",width:"8px",height:"8px","margin-bottom":"3px"}},null,-1)),Dl={key:1},Ol=_(()=>n("span",{style:{"background-color":"#FFC577","border-radius":"50%",display:"inline-block",width:"8px",height:"8px","margin-bottom":"3px"}},null,-1)),Rl={key:2},zl=_(()=>n("span",{style:{"background-color":"#77FA7F","border-radius":"50%",display:"inline-block",width:"8px",height:"8px","margin-bottom":"3px"}},null,-1)),Gl=_(()=>n("div",null,[n("span",{style:{"background-color":"#FA8577","border-radius":"50%",display:"inline-block",width:"8px",height:"8px","margin-bottom":"3px"}}),n("span",null," 异常")],-1)),ql={class:"abcde",style:{display:"flex","justify-content":"center","margin-top":"20px"}},Pl={style:{width:"100%",display:"flex","justify-content":"end"}},Ql={style:{"margin-right":"20px","margin-top":"20px","margin-bottom":"20px"}},Hl={class:"m-4"},jl={class:"m-4"},Yl={class:"dialog-footer"},Jl=_(()=>n("div",{style:{height:"20px"}},null,-1)),Kl=Pe({__name:"botManage",setup(O){i(0);let I=i([]),P=i(0);const te=i({});let R="新建机器人";const oe=i(),Q=i([]),ae=o=>{Q.value=o},S=i(!1),x=i(""),k=i(""),E=i(""),w=i(""),V=i(""),r=Qe({name:"",region:"",type:""});let K="";const C=i(!1),M=i(!0),T=i(0),A=i(!0),U=i([]),ne=[{value:"0",label:"未登录"},{value:"1",label:"登录中"},{value:"2",label:"已登录"},{value:"3",label:"异常"}],W=[{value:0,label:"公域"},{value:1,label:"私域"}],se=[{value:!0,label:"沙盒"},{value:!1,label:"正式"}],ue=[{value:"GUILDS",label:"频道事件"},{value:"GUILD_MEMBERS",label:"成员事件"},{value:"GUILD_MESSAGE_REACTIONS",label:"表情表态事件"},{value:"DIRECT_MESSAGE",label:"私信消息事件"},{value:"AUDIO_OR_LIVE_CHANNEL_MEMBER",label:"音视频/直播子频道成员进出事件"},{value:"INTERACTION",label:"互动事件事件"},{value:"MESSAGE_AUDIT",label:"消息审核事件"},{value:"AUDIO_ACTION",label:"音频播放事件"},{value:"OPEN_FORUMS_EVENT",label:"论坛事件(公域)"},{value:"PUBLIC_GUILD_MESSAGES",label:"消息事件(公域)"},{value:"GUILD_MESSAGES",label:"消息事件(私域)"},{value:"FORUMS_EVENT",label:"论坛事件(私域)"}],z=i(1),N=i(10),ie=i(!0),re=o=>{C.value=!0,q(0,o,x.value,V.value,k.value,E.value,w.value).then(l=>{I.value=l.data,C.value=!1})},de=o=>{C.value=!0,q(o-1,N.value,x.value,V.value,k.value,E.value,w.value).then(l=>{I.value=l.data,C.value=!1})},ce=o=>{ye(o)},pe=()=>{q(z.value-1,N.value,x.value,V.value,k.value,E.value,w.value).then(o=>{I.value=o.data,P.value=o.extra.count})},me=()=>{V.value="",x.value="",E.value="",w.value="",k.value=""},ve=o=>{dl.confirm("是否确实要关闭此对话框？").then(()=>{o()}).catch(()=>{})},_e=()=>{if(U.value.length!=0&&r.region!=""&&r.type!=""){const o={bot_type:T.value,sand_box:M.value,bot_id:r.region,token:r.type,auto_login:A.value},l={botId:r.region,intents:Ye(U.value)},v={bot_type:T.value,sand_box:M.value,bot_id:r.region,token:r.type,auto_login:A.value,id:K};R=="新建机器人"?Le(o).then(u=>{X(l).then(B=>{}),y()}):Z(v).then(u=>{m({message:`${u.msg}`,type:"success"}),X(l),y()}),S.value=!1,j()}else m.error("请填写完整.")},fe=()=>{S.value=!0,R="新建机器人",j()};let H=[];const ge=o=>{R="编辑机器人",T.value=o.botType,M.value=o.sandBox,r.region=o.botId,r.type=o.token,A.value=o.autoLogin,o.intents&&(o.intents.map(v=>{H.push(v.intentsName)}),U.value=H),K=o.id,S.value=!0},ye=o=>{Me(o.id).then(l=>{l.code=="0"?(m({message:`${l.msg}`,type:"success"}),y()):m({message:`${l.msg}`,type:"error"})})},j=()=>{r.name="",r.region="",r.type="",U.value=[],A.value=!0,H=[]},be=o=>{Z({id:o.id,auto_login:o.autoLogin}).then(l=>{})},he=()=>{Q.value.map((l,v)=>{ee(l.botId).then(u=>{u.code==0?m({message:`${u.msg}`,type:"success"}):m.error(`${u.msg}`)})}),setTimeout(y,1e3)},xe=()=>{Q.value.map((l,v)=>{le(l.botId).then(u=>{u.code==0?(m({message:`${u.msg}`,type:"success"}),y()):m.error(`${u.msg}`)})})},ke=o=>{ee(o).then(l=>{l.code==0?m({message:`${l.msg}`,type:"success"}):m.error(`${l.msg}`)}),setTimeout(y,1e3)},Ee=o=>{le(o).then(l=>{l.code==0?(m({message:`${l.msg}`,type:"success"}),y()):m.error(`${l.msg}`)})},y=()=>{C.value=!0,q(z.value-1,N.value,x.value,V.value,k.value,E.value,w.value).then(o=>{I.value=o.data,P.value=o.extra.count,C.value=!1})};y();const Y=()=>{setTimeout(()=>{const o=T.value;document.querySelector(".el-select__tags > span").querySelectorAll(".el-tag--info").forEach((u,B)=>{const c=u.querySelector(".el-select__tags-text");(c.innerHTML=="论坛事件(私域)"||c.innerHTML=="消息事件(私域)")&&(o==0?u.style.background="#ffd4d4":u.style.background="#F0F2F5")})},0)};return(o,l)=>{const v=We,u=Xe,B=Ze,c=ol,we=Ne,b=el,Ve=nl,G=ll,J=sl,Ce=ul,Be=al,Ie=cl,Se=tl,Te=pl,Ae=il,F=ml,Ue=vl,Fe=_l,$e=rl;return s(),p("div",gl,[n("div",yl,[n("div",bl,[hl,t(v,{modelValue:x.value,"onUpdate:modelValue":l[0]||(l[0]=e=>x.value=e),placeholder:"请输入QQ机器人ID",clearable:"",style:{width:"300px"}},null,8,["modelValue"])]),n("div",xl,[kl,t(v,{modelValue:V.value,"onUpdate:modelValue":l[1]||(l[1]=e=>V.value=e),placeholder:"请输入QQ机器人昵称",clearable:"",style:{width:"300px"}},null,8,["modelValue"])]),n("div",El,[wl,d(),t(B,{modelValue:k.value,"onUpdate:modelValue":l[2]||(l[2]=e=>k.value=e),class:"m-2",placeholder:"请选择",style:{width:"300px",float:"left"}},{default:a(()=>[(s(),p(L,null,D(ne,e=>t(u,{key:e.value,label:e.label,value:e.value},null,8,["label","value"])),64))]),_:1},8,["modelValue"])]),n("div",Vl,[Cl,d(),t(B,{modelValue:E.value,"onUpdate:modelValue":l[3]||(l[3]=e=>E.value=e),class:"m-2",placeholder:"请选择",style:{width:"300px",float:"left"}},{default:a(()=>[(s(),p(L,null,D(W,e=>t(u,{key:e.value,label:e.label,value:e.value},null,8,["label","value"])),64))]),_:1},8,["modelValue"])]),n("div",Bl,[Il,d(),t(B,{modelValue:w.value,"onUpdate:modelValue":l[4]||(l[4]=e=>w.value=e),class:"m-2",placeholder:"请选择",style:{width:"300px"}},{default:a(()=>[(s(),p(L,null,D(se,e=>t(u,{key:e.value,label:e.label,value:e.value},null,8,["label","value"])),64))]),_:1},8,["modelValue"])]),n("div",Sl,[t(c,{onClick:me},{default:a(()=>[d("重置")]),_:1}),t(c,{type:"primary",onClick:pe},{default:a(()=>[d("查询")]),_:1})])]),n("div",Tl,[(s(),f(Te,{style:{width:"100%"},key:1,height:"650"},{default:a(()=>[n("div",Al,[Ul,n("div",Fl,[t(c,{type:"primary",onClick:l[5]||(l[5]=e=>fe())},{default:a(()=>[t(we,null,{default:a(()=>[t(g(De))]),_:1}),d("  添加")]),_:1})])]),He((s(),f(Se,{ref_key:"multipleTableRef",ref:oe,data:g(I),style:{width:"100%"},onSelectionChange:ae,"empty-text":" ",key:"abc"},{default:a(()=>[g(I).length?(s(),p(L,{key:0},[t(b,{type:"selection"}),t(b,{property:"botId",label:"QQBotID"}),t(b,{"show-overflow-tooltip":"",property:"username",label:"  昵称"},{default:a(e=>[e.row.username==null?(s(),p("div",$l," 未登录 ")):(s(),p("div",Ll,je(e.row.username),1))]),_:1}),t(b,{property:"state",label:"当前状态"},{default:a(e=>[e.row.state=="0"?(s(),p("span",Ml,[Nl,d(" 未登录")])):h("",!0),e.row.state=="1"?(s(),p("span",Dl,[Ol,d(" 登录中")])):h("",!0),e.row.state=="2"?(s(),p("span",Rl,[zl,d(" 已登录")])):h("",!0),e.row.state=="3"?(s(),f(Ve,{key:3,class:"box-item",effect:"dark",content:e.row.errorInfo,placement:"right"},{default:a(()=>[Gl]),_:2},1032,["content"])):h("",!0)]),_:1}),t(b,{property:"sandBox",label:"运行模式"},{default:a(e=>[e.row.sandBox?(s(),f(G,{key:0},{default:a(()=>[d("沙箱")]),_:1})):h("",!0),e.row.sandBox?h("",!0):(s(),f(G,{key:1,class:"ml-2",type:"success"},{default:a(()=>[d("正式")]),_:1}))]),_:1}),t(b,{property:"autoLogin",label:"自动登录"},{default:a(e=>[t(J,{modelValue:e.row.autoLogin,"onUpdate:modelValue":$=>e.row.autoLogin=$,class:"ml-2",style:{"--el-switch-on-color":"#13ce66","--el-switch-off-color":"#F0F2F5"},onClick:$=>be(e.row)},null,8,["modelValue","onUpdate:modelValue","onClick"])]),_:1}),t(b,{property:"botType",label:"服务器"},{default:a(e=>[e.row.botType=="0"?(s(),f(G,{key:0},{default:a(()=>[d("公域")]),_:1})):h("",!0),e.row.botType=="1"?(s(),f(G,{key:1,class:"ml-2",type:"success"},{default:a(()=>[d("私域")]),_:1})):h("",!0)]),_:1}),t(b,{property:"",label:"操作",width:"150"},{default:a(e=>[t(Be,{class:"ml-4"},{default:a(()=>[e.row.state!=2?(s(),f(c,{key:0,type:"success",icon:g(Oe),size:"small",onClick:$=>ke(e.row.botId)},null,8,["icon","onClick"])):(s(),f(c,{key:1,type:"danger",icon:g(Re),color:"#f59d3c",style:{color:"#FFFFFF"},size:"small",onClick:$=>Ee(e.row.botId)},null,8,["icon","onClick"])),t(c,{type:"primary",icon:g(ze),class:"editButton",onClick:$=>ge(e.row),size:"small"},null,8,["icon","onClick"]),t(Ce,{"confirm-button-text":"Yes","cancel-button-text":"No",icon:g(Ge),"icon-color":"#626AEF",title:"确认删除此机器人吗?",onConfirm:$=>ce(e.row),size:"small"},{reference:a(()=>[t(c,{type:"primary",icon:g(qe),class:"deleteButton",size:"small"},null,8,["icon"])]),_:2},1032,["icon","onConfirm"])]),_:2},1024)]),_:1})],64)):(s(),f(Ie,{key:1,description:"暂无数据"}))]),_:1},8,["data"])),[[$e,C.value]])]),_:1})),n("div",ql,[t(Ae,{"current-page":z.value,"onUpdate:current-page":l[6]||(l[6]=e=>z.value=e),"page-size":N.value,"onUpdate:page-size":l[7]||(l[7]=e=>N.value=e),"page-sizes":[10,20,50],background:ie.value,layout:"total, sizes, prev, pager, next, jumper",total:g(P),onSizeChange:re,onCurrentChange:de},null,8,["current-page","page-size","background","total"])]),n("div",Pl,[n("div",Ql,[t(c,{type:"success",onClick:l[8]||(l[8]=e=>he())},{default:a(()=>[d("登录")]),_:1}),t(c,{type:"danger",onClick:l[9]||(l[9]=e=>xe())},{default:a(()=>[d("注销")]),_:1})])])]),t(Fe,{modelValue:S.value,"onUpdate:modelValue":l[18]||(l[18]=e=>S.value=e),title:g(R),width:"500px","before-close":ve,onClose:j},{footer:a(()=>[n("span",Yl,[t(c,{onClick:l[16]||(l[16]=e=>S.value=!1)},{default:a(()=>[d("取消")]),_:1}),t(c,{type:"primary",onClick:l[17]||(l[17]=e=>_e())},{default:a(()=>[d(" 确定 ")]),_:1})])]),default:a(()=>[t(Ue,{"label-position":"top","label-width":"100px",model:r,style:{"max-width":"500px"},inline:""},{default:a(()=>[t(F,{label:"BotId",required:"",style:{float:"left"}},{default:a(()=>[t(v,{modelValue:r.region,"onUpdate:modelValue":l[10]||(l[10]=e=>r.region=e),style:{width:"214px"}},null,8,["modelValue"])]),_:1}),t(F,{label:"Token",required:"",style:{float:"left"}},{default:a(()=>[t(v,{modelValue:r.type,"onUpdate:modelValue":l[11]||(l[11]=e=>r.type=e),style:{width:"214px"}},null,8,["modelValue"])]),_:1}),t(F,{label:"订阅事件",required:""},{default:a(()=>[t(B,{id:"test",modelValue:U.value,"onUpdate:modelValue":l[12]||(l[12]=e=>U.value=e),multiple:"",placeholder:"可多选",style:{width:"460px"},ref_key:"select",ref:te,onChange:Y,onVisibleChange:Y},{default:a(()=>[(s(),p(L,null,D(ue,e=>t(u,{key:e.value,label:e.label,value:e.value},null,8,["label","value"])),64))]),_:1},8,["modelValue"])]),_:1}),t(F,{label:"服务器",required:"",style:{float:"left"}},{default:a(()=>[n("div",Hl,[t(B,{modelValue:T.value,"onUpdate:modelValue":l[13]||(l[13]=e=>T.value=e),placeholder:"必选项",onChange:Y},{default:a(()=>[(s(),p(L,null,D(W,e=>t(u,{key:e.value,label:e.label,value:e.value},null,8,["label","value"])),64))]),_:1},8,["modelValue"])])]),_:1}),t(F,{label:"沙盒模式",required:""},{default:a(()=>[n("div",jl,[t(J,{modelValue:M.value,"onUpdate:modelValue":l[14]||(l[14]=e=>M.value=e),class:"ml-2",style:{"--el-switch-on-color":"#13ce66","--el-switch-off-color":"#ff4949"}},null,8,["modelValue"])])]),_:1}),t(F,{label:"自动登录",required:""},{default:a(()=>[t(J,{modelValue:A.value,"onUpdate:modelValue":l[15]||(l[15]=e=>A.value=e),class:"ml-2",style:{"--el-switch-on-color":"#13ce66","--el-switch-off-color":"#ff4949"}},null,8,["modelValue"])]),_:1})]),_:1},8,["model"])]),_:1},8,["modelValue","title"]),Jl])}}});const St=fl(Kl,[["__scopeId","data-v-56094544"]]);export{St as default};
