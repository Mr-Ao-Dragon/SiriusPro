(self.webpackChunkant_design_pro=self.webpackChunkant_design_pro||[]).push([[3012],{64317:function(U,C,e){"use strict";var i=e(28991),v=e(81253),Z=e(85893),c=e(22270),P=e(67294),j=e(66758),f=e(15406),S=["fieldProps","children","params","proFieldProps","mode","valueEnum","request","showSearch","options"],D=["fieldProps","children","params","proFieldProps","mode","valueEnum","request","options"],F=P.forwardRef(function(a,g){var X=a.fieldProps,N=a.children,T=a.params,H=a.proFieldProps,Y=a.mode,J=a.valueEnum,A=a.request,E=a.showSearch,$=a.options,Q=(0,v.Z)(a,S),b=(0,P.useContext)(j.Z);return(0,Z.jsx)(f.Z,(0,i.Z)((0,i.Z)({valueEnum:(0,c.h)(J),request:A,params:T,valueType:"select",filedConfig:{customLightMode:!0},fieldProps:(0,i.Z)({options:$,mode:Y,showSearch:E,getPopupContainer:b.getPopupContainer},X),ref:g,proFieldProps:H},Q),{},{children:N}))}),L=P.forwardRef(function(a,g){var X=a.fieldProps,N=a.children,T=a.params,H=a.proFieldProps,Y=a.mode,J=a.valueEnum,A=a.request,E=a.options,$=(0,v.Z)(a,D),Q=(0,i.Z)({options:E,mode:Y||"multiple",labelInValue:!0,showSearch:!0,showArrow:!1,autoClearSearchValue:!0,optionLabelProp:"label"},X),b=(0,P.useContext)(j.Z);return(0,Z.jsx)(f.Z,(0,i.Z)((0,i.Z)({valueEnum:(0,c.h)(J),request:A,params:T,valueType:"select",filedConfig:{customLightMode:!0},fieldProps:(0,i.Z)({getPopupContainer:b.getPopupContainer},Q),ref:g,proFieldProps:H},$),{},{children:N}))}),u=F,y=L,x=u;x.SearchSelect=y,x.displayName="ProFormComponent",C.Z=x},90672:function(U,C,e){"use strict";var i=e(28991),v=e(81253),Z=e(85893),c=e(67294),P=e(15406),j=["fieldProps","proFieldProps"],f=function(D,F){var L=D.fieldProps,u=D.proFieldProps,y=(0,v.Z)(D,j);return(0,Z.jsx)(P.Z,(0,i.Z)({ref:F,valueType:"textarea",fieldProps:L,proFieldProps:u},y))};C.Z=c.forwardRef(f)},5966:function(U,C,e){"use strict";var i=e(28991),v=e(81253),Z=e(85893),c=e(15406),P=["fieldProps","proFieldProps"],j=["fieldProps","proFieldProps"],f="text",S=function(u){var y=u.fieldProps,x=u.proFieldProps,a=(0,v.Z)(u,P);return(0,Z.jsx)(c.Z,(0,i.Z)({valueType:f,fieldProps:y,filedConfig:{valueType:f},proFieldProps:x},a))},D=function(u){var y=u.fieldProps,x=u.proFieldProps,a=(0,v.Z)(u,j);return(0,Z.jsx)(c.Z,(0,i.Z)({valueType:"password",fieldProps:y,proFieldProps:x,filedConfig:{valueType:f}},a))},F=S;F.Password=D,F.displayName="ProFormComponent",C.Z=F},7763:function(U){U.exports={standardList:"standardList___pRhsd",headerInfo:"headerInfo___3KrKX",listContent:"listContent___weIUQ",listContentItem:"listContentItem___S_LBX",extraContentSearch:"extraContentSearch___1lMSQ",listCard:"listCard___1aWw-",standardListForm:"standardListForm___1KJ6f",formResult:"formResult___1XdZP"}},29029:function(U,C,e){"use strict";e.r(C),e.d(C,{BasicList:function(){return le},default:function(){return Ie}});var i=e(57663),v=e(71577),Z=e(54421),c=e(38272),P=e(94233),j=e(51890),f=e(58024),S=e(91894),D=e(13062),F=e(71230),L=e(89032),u=e(15746),y=e(59250),x=e(13013),a=e(30887),g=e(28682),X=e(71194),N=e(50146),T=e(2824),H=e(47673),Y=e(4107),J=e(88983),A=e(47933),E=e(67294),$=e(57254),Q=e(49101),b=e(75362),I=e(81910),pe=e(30381),ve=e.n(pe),Xe=e(57106),me=e(99683),m=e(39428),z=e(3182),he=e(37476),Pe=e(5966),ae=e(28991),fe=e(81253),t=e(85893),Fe=e(66758),xe=e(15406),Ee=["fieldProps","proFieldProps"],se="dateTime",Ce=E.forwardRef(function(l,s){var o=l.fieldProps,r=l.proFieldProps,p=(0,fe.Z)(l,Ee),M=(0,E.useContext)(Fe.Z);return(0,t.jsx)(xe.Z,(0,ae.Z)({ref:s,fieldProps:(0,ae.Z)({getPopupContainer:M.getPopupContainer},o),valueType:se,proFieldProps:r,filedConfig:{valueType:se,customLightMode:!0}},p))}),Ze=Ce,je=e(64317),De=e(90672),ye=e(7763),h=e.n(ye),Se=function(s){var o=s.done,r=s.visible,p=s.current,M=s.onDone,V=s.onSubmit,ee=s.children;return r?(0,t.jsx)(he.Y,{visible:r,title:o?null:"\u4EFB\u52A1".concat(p?"\u7F16\u8F91":"\u6DFB\u52A0"),className:h().standardListForm,width:640,onFinish:function(){var B=(0,z.Z)((0,m.Z)().mark(function G(W){return(0,m.Z)().wrap(function(K){for(;;)switch(K.prev=K.next){case 0:V(W);case 1:case"end":return K.stop()}},G)}));return function(G){return B.apply(this,arguments)}}(),initialValues:p,submitter:{render:function(G,W){return o?null:W}},trigger:(0,t.jsx)(t.Fragment,{children:ee}),modalProps:{onCancel:function(){return M()},destroyOnClose:!0,bodyStyle:o?{padding:"72px 0"}:{}},children:o?(0,t.jsx)(me.ZP,{status:"success",title:"\u64CD\u4F5C\u6210\u529F",subTitle:"\u4E00\u7CFB\u5217\u7684\u4FE1\u606F\u63CF\u8FF0\uFF0C\u5F88\u77ED\u540C\u6837\u4E5F\u53EF\u4EE5\u5E26\u6807\u70B9\u3002",extra:(0,t.jsx)(v.Z,{type:"primary",onClick:M,children:"\u77E5\u9053\u4E86"}),className:h().formResult}):(0,t.jsxs)(t.Fragment,{children:[(0,t.jsx)(Pe.Z,{name:"title",label:"\u4EFB\u52A1\u540D\u79F0",rules:[{required:!0,message:"\u8BF7\u8F93\u5165\u4EFB\u52A1\u540D\u79F0"}],placeholder:"\u8BF7\u8F93\u5165"}),(0,t.jsx)(Ze,{name:"createdAt",label:"\u5F00\u59CB\u65F6\u95F4",rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u5F00\u59CB\u65F6\u95F4"}],fieldProps:{style:{width:"100%"}},placeholder:"\u8BF7\u9009\u62E9"}),(0,t.jsx)(je.Z,{name:"owner",label:"\u4EFB\u52A1\u8D1F\u8D23\u4EBA",rules:[{required:!0,message:"\u8BF7\u9009\u62E9\u4EFB\u52A1\u8D1F\u8D23\u4EBA"}],options:[{label:"\u4ED8\u6653\u6653",value:"xiao"},{label:"\u5468\u6BDB\u6BDB",value:"mao"}],placeholder:"\u8BF7\u9009\u62E9\u7BA1\u7406\u5458"}),(0,t.jsx)(De.Z,{name:"subDescription",label:"\u4EA7\u54C1\u63CF\u8FF0",rules:[{message:"\u8BF7\u8F93\u5165\u81F3\u5C11\u4E94\u4E2A\u5B57\u7B26\u7684\u4EA7\u54C1\u63CF\u8FF0\uFF01",min:5}],placeholder:"\u8BF7\u8F93\u5165\u81F3\u5C11\u4E94\u4E2A\u5B57\u7B26"})]})}):null},ge=Se,R=e(11849);function Te(l){return w.apply(this,arguments)}function w(){return w=(0,z.Z)((0,m.Z)().mark(function l(s){return(0,m.Z)().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,I.WY)("/api/get_list",{params:s}));case 1:case"end":return r.stop()}},l)})),w.apply(this,arguments)}function Me(l){return k.apply(this,arguments)}function k(){return k=(0,z.Z)((0,m.Z)().mark(function l(s){return(0,m.Z)().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,I.WY)("/api/post_fake_list",{method:"POST",data:(0,R.Z)((0,R.Z)({},s),{},{method:"delete"})}));case 1:case"end":return r.stop()}},l)})),k.apply(this,arguments)}function Be(l){return q.apply(this,arguments)}function q(){return q=(0,z.Z)((0,m.Z)().mark(function l(s){return(0,m.Z)().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,I.WY)("/api/post_fake_list",{method:"POST",data:(0,R.Z)((0,R.Z)({},s),{},{method:"post"})}));case 1:case"end":return r.stop()}},l)})),q.apply(this,arguments)}function Oe(l){return _.apply(this,arguments)}function _(){return _=(0,z.Z)((0,m.Z)().mark(function l(s){return(0,m.Z)().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,I.WY)("/api/post_fake_list",{method:"POST",data:(0,R.Z)((0,R.Z)({},s),{},{method:"update"})}));case 1:case"end":return r.stop()}},l)})),_.apply(this,arguments)}var He=A.ZP.Button,Je=A.ZP.Group,Le=Y.Z.Search,oe=function(s){var o=s.title,r=s.value,p=s.bordered;return(0,t.jsxs)("div",{className:h().headerInfo,children:[(0,t.jsx)("span",{children:o}),(0,t.jsx)("p",{children:r}),p&&(0,t.jsx)("em",{})]})},Ae=function(s){var o=s.data,r=o.owner,p=o.createdAt,M=o.percent,V=o.status;return(0,t.jsxs)("div",{className:h().listContent,children:[(0,t.jsxs)("div",{className:h().listContentItem,children:[(0,t.jsx)("span",{children:"\u63D0\u4F9B\u8005"}),(0,t.jsx)("p",{children:r})]}),(0,t.jsxs)("div",{className:h().listContentItem,children:[(0,t.jsx)("span",{children:"\u5B89\u88C5\u65F6\u95F4"}),(0,t.jsx)("p",{children:ve()(p).format("YYYY-MM-DD HH:mm")})]})]})},le=function(){var s=(0,E.useState)(!1),o=(0,T.Z)(s,2),r=o[0],p=o[1],M=(0,E.useState)(!1),V=(0,T.Z)(M,2),ee=V[0],B=V[1],G=(0,E.useState)(void 0),W=(0,T.Z)(G,2),ue=W[0],K=W[1],te=(0,I.QT)(function(){return Te({count:50})}),re=te.data,Re=te.loading,We=te.mutate,Ke=(0,I.QT)(function(d,n){return d==="remove"?Me(n):d==="update"?Oe(n):Be(n)},{manual:!0,onSuccess:function(n){We(n)}}),ie=Ke.run,de=(re==null?void 0:re.list)||[],Ue={showSizeChanger:!0,showQuickJumper:!0,pageSize:5,total:de.length},ce=function(n){B(!0),K(n)},Ne=function(n){ie("remove",{id:n})},Ye=function(n,O){n==="edit"?ce(O):n==="delete"&&N.Z.confirm({title:"\u5220\u9664\u4EFB\u52A1",content:"\u786E\u5B9A\u5220\u9664\u8BE5\u4EFB\u52A1\u5417\uFF1F",okText:"\u786E\u8BA4",cancelText:"\u53D6\u6D88",onOk:function(){return Ne(O.id)}})},$e=(0,t.jsx)("div",{className:h().extraContent,children:(0,t.jsx)(Le,{className:h().extraContentSearch,placeholder:"\u8BF7\u8F93\u5165",onSearch:function(){return{}}})}),Qe=function(n){var O=n.item;return(0,t.jsx)(x.Z,{overlay:(0,t.jsxs)(g.Z,{onClick:function(Ve){var Ge=Ve.key;return Ye(Ge,O)},children:[(0,t.jsx)(g.Z.Item,{children:"\u7F16\u8F91"},"edit"),(0,t.jsx)(g.Z.Item,{children:"\u5220\u9664"},"delete")]}),children:(0,t.jsxs)("a",{children:["\u66F4\u591A ",(0,t.jsx)($.Z,{})]})})},be=function(){p(!1),B(!1),K({})},ze=function(n){p(!0);var O=n!=null&&n.id?"update":"add";ie(O,n)};return(0,t.jsxs)("div",{children:[(0,t.jsx)(b.ZP,{children:(0,t.jsxs)("div",{className:h().standardList,children:[(0,t.jsx)(S.Z,{bordered:!1,children:(0,t.jsxs)(F.Z,{children:[(0,t.jsx)(u.Z,{sm:12,xs:24,children:(0,t.jsx)(oe,{title:"\u6211\u7684\u5E94\u7528",value:"8\u4E2A",bordered:!0})}),(0,t.jsx)(u.Z,{sm:12,xs:24,children:(0,t.jsx)(oe,{title:"\u5DF2\u8FD0\u884C",value:"32\u5206\u949F",bordered:!0})})]})}),(0,t.jsx)(S.Z,{className:h().listCard,bordered:!1,title:"\u5E94\u7528\u5217\u8868",style:{marginTop:24},bodyStyle:{padding:"0 32px 40px 32px"},extra:$e,children:(0,t.jsx)(c.ZP,{size:"large",rowKey:"id",loading:Re,pagination:Ue,dataSource:de,renderItem:function(n){return(0,t.jsxs)(c.ZP.Item,{actions:[(0,t.jsx)("a",{onClick:function(ne){ne.preventDefault(),ce(n)},children:"\u914D\u7F6E"},"edit"),(0,t.jsx)(Qe,{item:n},"more")],children:[(0,t.jsx)(c.ZP.Item.Meta,{avatar:(0,t.jsx)(j.C,{src:n.logo,shape:"square",size:"large"}),title:(0,t.jsx)("a",{href:n.href,children:n.title}),description:n.subDescription}),(0,t.jsx)(Ae,{data:n})]})}})})]})}),(0,t.jsxs)(v.Z,{type:"dashed",onClick:function(){B(!0)},style:{width:"100%",marginBottom:8},children:[(0,t.jsx)(Q.Z,{}),"\u6DFB\u52A0"]}),(0,t.jsx)(ge,{done:r,visible:ee,current:ue,onDone:be,onSubmit:ze})]})},Ie=le}}]);
