(self.webpackChunkant_design_pro=self.webpackChunkant_design_pro||[]).push([[969],{85224:function(ge,te,i){"use strict";var I=i(28991),se=i(84305),O=i(39559),H=i(81253),z=i(85893),re=i(94184),Z=i.n(re),K=i(97435),j=i(67294),N=i(64335),R=i(56264),q=i.n(R),ae=["children","className","extra","style","renderContent"],l=function(D){var W=D.children,Y=D.className,V=D.extra,ve=D.style,k=D.renderContent,de=(0,H.Z)(D,ae),ce=(0,j.useContext)(O.ZP.ConfigContext),fe=ce.getPrefixCls,G=D.prefixCls||fe("pro"),ue="".concat(G,"-footer-bar"),p=(0,j.useContext)(N.Z),ne=(0,j.useMemo)(function(){var J=p.hasSiderMenu,X=p.isMobile,Q=p.siderWidth;if(!!J)return Q?X?"100%":"calc(100% - ".concat(Q,"px)"):"100%"},[p.collapsed,p.hasSiderMenu,p.isMobile,p.siderWidth]),oe=(0,z.jsxs)(z.Fragment,{children:[(0,z.jsx)("div",{className:"".concat(ue,"-left"),children:V}),(0,z.jsx)("div",{className:"".concat(ue,"-right"),children:W})]});return(0,j.useEffect)(function(){return!p||!(p==null?void 0:p.setHasFooterToolbar)?function(){}:(p==null||p.setHasFooterToolbar(!0),function(){var J;p==null||(J=p.setHasFooterToolbar)===null||J===void 0||J.call(p,!1)})},[]),(0,z.jsx)("div",(0,I.Z)((0,I.Z)({className:Z()(Y,"".concat(ue)),style:(0,I.Z)({width:ne},ve)},(0,K.Z)(de,["prefixCls"])),{},{children:k?k((0,I.Z)((0,I.Z)((0,I.Z)({},D),p),{},{leftWidth:ne}),oe):oe}))};te.Z=l},75362:function(ge,te,i){"use strict";i.d(te,{ZP:function(){return Mt}});var I=i(38663),se=i(70883),O=i(22122),H=i(96156),z=i(6610),re=i(5991),Z=i(10379),K=i(60446),j=i(90484),N=i(94184),R=i.n(N),q=i(48717),ae=i(98423),l=i(67294),A=i(53124),D=i(85061),W=i(75164);function Y(n){var e,r=function(o){return function(){e=null,n.apply(void 0,(0,D.Z)(o))}},t=function(){if(e==null){for(var o=arguments.length,s=new Array(o),d=0;d<o;d++)s[d]=arguments[d];e=(0,W.Z)(r(s))}};return t.cancel=function(){W.Z.cancel(e),e=null},t}function V(){return function(e,r,t){var a=t.value,o=!1;return{configurable:!0,get:function(){if(o||this===e.prototype||this.hasOwnProperty(r))return a;var d=Y(a.bind(this));return o=!0,Object.defineProperty(this,r,{value:d,configurable:!0,writable:!0}),o=!1,d}}}}var ve=i(64019);function k(n){return n!==window?n.getBoundingClientRect():{top:0,bottom:window.innerHeight}}function de(n,e,r){if(r!==void 0&&e.top>n.top-r)return r+e.top}function ce(n,e,r){if(r!==void 0&&e.bottom<n.bottom+r){var t=window.innerHeight-e.bottom;return r+t}}var fe=["resize","scroll","touchstart","touchmove","touchend","pageshow","load"],G=[];function ue(){return G}function p(n,e){if(!!n){var r=G.find(function(t){return t.target===n});r?r.affixList.push(e):(r={target:n,affixList:[e],eventHandlers:{}},G.push(r),fe.forEach(function(t){r.eventHandlers[t]=(0,ve.Z)(n,t,function(){r.affixList.forEach(function(a){a.lazyUpdatePosition()})})}))}}function ne(n){var e=G.find(function(r){var t=r.affixList.some(function(a){return a===n});return t&&(r.affixList=r.affixList.filter(function(a){return a!==n})),t});e&&e.affixList.length===0&&(G=G.filter(function(r){return r!==e}),fe.forEach(function(r){var t=e.eventHandlers[r];t&&t.remove&&t.remove()}))}var oe=function(n,e,r,t){var a=arguments.length,o=a<3?e:t===null?t=Object.getOwnPropertyDescriptor(e,r):t,s;if((typeof Reflect=="undefined"?"undefined":(0,j.Z)(Reflect))==="object"&&typeof Reflect.decorate=="function")o=Reflect.decorate(n,e,r,t);else for(var d=n.length-1;d>=0;d--)(s=n[d])&&(o=(a<3?s(o):a>3?s(e,r,o):s(e,r))||o);return a>3&&o&&Object.defineProperty(e,r,o),o};function J(){return typeof window!="undefined"?window:null}var X;(function(n){n[n.None=0]="None",n[n.Prepare=1]="Prepare"})(X||(X={}));var Q=function(n){(0,Z.Z)(r,n);var e=(0,K.Z)(r);function r(){var t;return(0,z.Z)(this,r),t=e.apply(this,arguments),t.state={status:X.None,lastAffix:!1,prevTarget:null},t.getOffsetTop=function(){var a=t.props,o=a.offsetBottom,s=a.offsetTop;return o===void 0&&s===void 0?0:s},t.getOffsetBottom=function(){return t.props.offsetBottom},t.savePlaceholderNode=function(a){t.placeholderNode=a},t.saveFixedNode=function(a){t.fixedNode=a},t.measure=function(){var a=t.state,o=a.status,s=a.lastAffix,d=t.props.onChange,u=t.getTargetFunc();if(!(o!==X.Prepare||!t.fixedNode||!t.placeholderNode||!u)){var v=t.getOffsetTop(),h=t.getOffsetBottom(),g=u();if(!!g){var c={status:X.None},C=k(g),f=k(t.placeholderNode),P=de(f,C,v),E=ce(f,C,h);f.top===0&&f.left===0&&f.width===0&&f.height===0||(P!==void 0?(c.affixStyle={position:"fixed",top:P,width:f.width,height:f.height},c.placeholderStyle={width:f.width,height:f.height}):E!==void 0&&(c.affixStyle={position:"fixed",bottom:E,width:f.width,height:f.height},c.placeholderStyle={width:f.width,height:f.height}),c.lastAffix=!!c.affixStyle,d&&s!==c.lastAffix&&d(c.lastAffix),t.setState(c))}}},t.prepareMeasure=function(){if(t.setState({status:X.Prepare,affixStyle:void 0,placeholderStyle:void 0}),!1)var a},t}return(0,re.Z)(r,[{key:"getTargetFunc",value:function(){var a=this.context.getTargetContainer,o=this.props.target;return o!==void 0?o:a!=null?a:J}},{key:"componentDidMount",value:function(){var a=this,o=this.getTargetFunc();o&&(this.timeout=setTimeout(function(){p(o(),a),a.updatePosition()}))}},{key:"componentDidUpdate",value:function(a){var o=this.state.prevTarget,s=this.getTargetFunc(),d=(s==null?void 0:s())||null;o!==d&&(ne(this),d&&(p(d,this),this.updatePosition()),this.setState({prevTarget:d})),(a.offsetTop!==this.props.offsetTop||a.offsetBottom!==this.props.offsetBottom)&&this.updatePosition(),this.measure()}},{key:"componentWillUnmount",value:function(){clearTimeout(this.timeout),ne(this),this.updatePosition.cancel(),this.lazyUpdatePosition.cancel()}},{key:"updatePosition",value:function(){this.prepareMeasure()}},{key:"lazyUpdatePosition",value:function(){var a=this.getTargetFunc(),o=this.state.affixStyle;if(a&&o){var s=this.getOffsetTop(),d=this.getOffsetBottom(),u=a();if(u&&this.placeholderNode){var v=k(u),h=k(this.placeholderNode),g=de(h,v,s),c=ce(h,v,d);if(g!==void 0&&o.top===g||c!==void 0&&o.bottom===c)return}}this.prepareMeasure()}},{key:"render",value:function(){var a=this,o=this.state,s=o.affixStyle,d=o.placeholderStyle,u=this.props,v=u.affixPrefixCls,h=u.children,g=R()((0,H.Z)({},v,!!s)),c=(0,ae.Z)(this.props,["prefixCls","offsetTop","offsetBottom","target","onChange","affixPrefixCls"]);return l.createElement(q.Z,{onResize:function(){a.updatePosition()}},l.createElement("div",(0,O.Z)({},c,{ref:this.savePlaceholderNode}),s&&l.createElement("div",{style:d,"aria-hidden":"true"}),l.createElement("div",{className:g,ref:this.saveFixedNode,style:s},l.createElement(q.Z,{onResize:function(){a.updatePosition()}},h))))}}]),r}(l.Component);Q.contextType=A.E_,oe([V()],Q.prototype,"updatePosition",null),oe([V()],Q.prototype,"lazyUpdatePosition",null);var Fe=l.forwardRef(function(n,e){var r=n.prefixCls,t=l.useContext(A.E_),a=t.getPrefixCls,o=a("affix",r),s=(0,O.Z)((0,O.Z)({},n),{affixPrefixCls:o});return l.createElement(Q,(0,O.Z)({},s,{ref:e}))}),He=Fe,Xt=i(84305),xe=i(39559),Yt=i(59903),Vt=i(94233),kt=i(81262),Jt=i(59250),Qt=i(30887),_t=i(49111),be=i(28481),m=i(28991),Ue={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M872 474H286.9l350.2-304c5.6-4.9 2.2-14-5.2-14h-88.5c-3.9 0-7.6 1.4-10.5 3.9L155 487.8a31.96 31.96 0 000 48.3L535.1 866c1.5 1.3 3.3 2 5.2 2h91.5c7.4 0 10.8-9.2 5.2-14L286.9 550H872c4.4 0 8-3.6 8-8v-60c0-4.4-3.6-8-8-8z"}}]},name:"arrow-left",theme:"outlined"},ze=Ue,ye=i(27029),Ce=function(e,r){return l.createElement(ye.Z,(0,m.Z)((0,m.Z)({},e),{},{ref:r,icon:ze}))};Ce.displayName="ArrowLeftOutlined";var We=l.forwardRef(Ce),$e={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M869 487.8L491.2 159.9c-2.9-2.5-6.6-3.9-10.5-3.9h-88.5c-7.4 0-10.8 9.2-5.2 14l350.2 304H152c-4.4 0-8 3.6-8 8v60c0 4.4 3.6 8 8 8h585.1L386.9 854c-5.6 4.9-2.2 14 5.2 14h91.5c1.9 0 3.8-.7 5.2-2L869 536.2a32.07 32.07 0 000-48.4z"}}]},name:"arrow-right",theme:"outlined"},Ke=$e,Pe=function(e,r){return l.createElement(ye.Z,(0,m.Z)((0,m.Z)({},e),{},{ref:r,icon:Ke}))};Pe.displayName="ArrowRightOutlined";var Ge=l.forwardRef(Pe),Xe=i(30470),Ye=i(51890),Ve=i(50344),ke=i(28682),Je=i(96159),Qe=i(57254),_e=i(81555),qe=function(n,e){var r={};for(var t in n)Object.prototype.hasOwnProperty.call(n,t)&&e.indexOf(t)<0&&(r[t]=n[t]);if(n!=null&&typeof Object.getOwnPropertySymbols=="function")for(var a=0,t=Object.getOwnPropertySymbols(n);a<t.length;a++)e.indexOf(t[a])<0&&Object.prototype.propertyIsEnumerable.call(n,t[a])&&(r[t[a]]=n[t[a]]);return r},pe=function(e){var r=e.prefixCls,t=e.separator,a=t===void 0?"/":t,o=e.children,s=e.menu,d=e.overlay,u=e.dropdownProps,v=qe(e,["prefixCls","separator","children","menu","overlay","dropdownProps"]),h=l.useContext(A.E_),g=h.getPrefixCls,c=g("breadcrumb",r),C=function(E){return s||d?l.createElement(_e.Z,(0,O.Z)({menu:s,overlay:d,placement:"bottom"},u),l.createElement("span",{className:"".concat(c,"-overlay-link")},E,l.createElement(Qe.Z,null))):E},f;return"href"in v?f=l.createElement("a",(0,O.Z)({className:"".concat(c,"-link")},v),o):f=l.createElement("span",(0,O.Z)({className:"".concat(c,"-link")},v),o),f=C(f),o!=null?l.createElement("li",null,f,a&&l.createElement("span",{className:"".concat(c,"-separator")},a)):null};pe.__ANT_BREADCRUMB_ITEM=!0;var Ee=pe,Ze=function(e){var r=e.children,t=l.useContext(A.E_),a=t.getPrefixCls,o=a("breadcrumb");return l.createElement("span",{className:"".concat(o,"-separator")},r||"/")};Ze.__ANT_BREADCRUMB_SEPARATOR=!0;var et=Ze,tt=function(n,e){var r={};for(var t in n)Object.prototype.hasOwnProperty.call(n,t)&&e.indexOf(t)<0&&(r[t]=n[t]);if(n!=null&&typeof Object.getOwnPropertySymbols=="function")for(var a=0,t=Object.getOwnPropertySymbols(n);a<t.length;a++)e.indexOf(t[a])<0&&Object.prototype.propertyIsEnumerable.call(n,t[a])&&(r[t[a]]=n[t[a]]);return r};function rt(n,e){if(!n.breadcrumbName)return null;var r=Object.keys(e).join("|"),t=n.breadcrumbName.replace(new RegExp(":(".concat(r,")"),"g"),function(a,o){return e[o]||a});return t}function at(n,e,r,t){var a=r.indexOf(n)===r.length-1,o=rt(n,e);return a?l.createElement("span",null,o):l.createElement("a",{href:"#/".concat(t.join("/"))},o)}var Re=function(e,r){return e=(e||"").replace(/^\//,""),Object.keys(r).forEach(function(t){e=e.replace(":".concat(t),r[t])}),e},nt=function(e,r,t){var a=(0,D.Z)(e),o=Re(r||"",t);return o&&a.push(o),a},me=function(e){var r=e.prefixCls,t=e.separator,a=t===void 0?"/":t,o=e.style,s=e.className,d=e.routes,u=e.children,v=e.itemRender,h=v===void 0?at:v,g=e.params,c=g===void 0?{}:g,C=tt(e,["prefixCls","separator","style","className","routes","children","itemRender","params"]),f=l.useContext(A.E_),P=f.getPrefixCls,E=f.direction,x,T=P("breadcrumb",r);if(d&&d.length>0){var B=[];x=d.map(function(b){var w=Re(b.path,c);w&&B.push(w);var L;b.children&&b.children.length&&(L=l.createElement(ke.Z,{items:b.children.map(function(F){return{key:F.path||F.breadcrumbName,label:h(F,c,d,nt(B,F.path,c))}})}));var U={separator:a};return L&&(U.overlay=L),l.createElement(Ee,(0,O.Z)({},U,{key:w||b.breadcrumbName}),h(b,c,d,B))})}else u&&(x=(0,Ve.Z)(u).map(function(b,w){return b&&(0,Je.Tm)(b,{separator:a,key:w})}));var S=R()(T,(0,H.Z)({},"".concat(T,"-rtl"),E==="rtl"),s);return l.createElement("nav",(0,O.Z)({className:S,style:o},C),l.createElement("ol",null,x))};me.Item=Ee,me.Separator=et;var ot=me,it=ot,lt=i(42051),st=i(19650),dt=i(34952),ct=function(e,r,t){return!r||!t?null:l.createElement(lt.Z,{componentName:"PageHeader"},function(a){return l.createElement("div",{className:"".concat(e,"-back")},l.createElement(dt.Z,{onClick:function(s){t==null||t(s)},className:"".concat(e,"-back-button"),"aria-label":a.back},r))})},ft=function(e){return l.createElement(it,(0,O.Z)({},e))},ut=function(e){var r=arguments.length>1&&arguments[1]!==void 0?arguments[1]:"ltr";return e.backIcon!==void 0?e.backIcon:r==="rtl"?l.createElement(Ge,null):l.createElement(We,null)},vt=function(e,r){var t=arguments.length>2&&arguments[2]!==void 0?arguments[2]:"ltr",a=r.title,o=r.avatar,s=r.subTitle,d=r.tags,u=r.extra,v=r.onBack,h="".concat(e,"-heading"),g=a||s||d||u;if(!g)return null;var c=ut(r,t),C=ct(e,c,v),f=C||o||g;return l.createElement("div",{className:h},f&&l.createElement("div",{className:"".concat(h,"-left")},C,o&&l.createElement(Ye.C,(0,O.Z)({},o)),a&&l.createElement("span",{className:"".concat(h,"-title"),title:typeof a=="string"?a:void 0},a),s&&l.createElement("span",{className:"".concat(h,"-sub-title"),title:typeof s=="string"?s:void 0},s),d&&l.createElement("span",{className:"".concat(h,"-tags")},d)),u&&l.createElement("span",{className:"".concat(h,"-extra")},l.createElement(st.Z,null,u)))},mt=function(e,r){return r?l.createElement("div",{className:"".concat(e,"-footer")},r):null},ht=function(e,r){return l.createElement("div",{className:"".concat(e,"-content")},r)},gt=function(e){var r=(0,Xe.Z)(!1),t=(0,be.Z)(r,2),a=t[0],o=t[1],s=function(u){var v=u.width;o(v<768,!0)};return l.createElement(A.C,null,function(d){var u,v=d.getPrefixCls,h=d.pageHeader,g=d.direction,c,C=e.prefixCls,f=e.style,P=e.footer,E=e.children,x=e.breadcrumb,T=e.breadcrumbRender,B=e.className,S=!0;"ghost"in e?S=e.ghost:h&&"ghost"in h&&(S=h.ghost);var b=v("page-header",C),w=function(){return(x==null?void 0:x.routes)?ft(x):null},L=w(),U=x&&"props"in x,F=(c=T==null?void 0:T(e,L))!==null&&c!==void 0?c:L,M=U?x:F,ee=R()(b,B,(u={"has-breadcrumb":!!M,"has-footer":!!P},(0,H.Z)(u,"".concat(b,"-ghost"),S),(0,H.Z)(u,"".concat(b,"-rtl"),g==="rtl"),(0,H.Z)(u,"".concat(b,"-compact"),a),u));return l.createElement(q.Z,{onResize:s},l.createElement("div",{className:ee,style:f},M,vt(b,e,g),E&&ht(b,E),mt(b,P)))})},xt=gt,Oe=i(81253),qt=i(18106),Ne=i(86629),y=i(85893),Te=i(64335),bt=i(85224),yt=i(21349),Ct=i(83832),Pt=function(e){if(!e)return 1;var r=e.backingStorePixelRatio||e.webkitBackingStorePixelRatio||e.mozBackingStorePixelRatio||e.msBackingStorePixelRatio||e.oBackingStorePixelRatio||e.backingStorePixelRatio||1;return(window.devicePixelRatio||1)/r},pt=function(e){var r=e.children,t=e.style,a=e.className,o=e.markStyle,s=e.markClassName,d=e.zIndex,u=d===void 0?9:d,v=e.gapX,h=v===void 0?212:v,g=e.gapY,c=g===void 0?222:g,C=e.width,f=C===void 0?120:C,P=e.height,E=P===void 0?64:P,x=e.rotate,T=x===void 0?-22:x,B=e.image,S=e.content,b=e.offsetLeft,w=e.offsetTop,L=e.fontStyle,U=L===void 0?"normal":L,F=e.fontWeight,M=F===void 0?"normal":F,ee=e.fontColor,he=ee===void 0?"rgba(0,0,0,.15)":ee,Be=e.fontSize,Se=Be===void 0?16:Be,Me=e.fontFamily,je=Me===void 0?"sans-serif":Me,jt=e.prefixCls,At=(0,l.useContext)(xe.ZP.ConfigContext),Dt=At.getPrefixCls,Ae=Dt("pro-layout-watermark",jt),Lt=R()("".concat(Ae,"-wrapper"),a),It=R()(Ae,s),wt=(0,l.useState)(""),De=(0,be.Z)(wt,2),Le=De[0],Ie=De[1];return(0,l.useEffect)(function(){var ie=document.createElement("canvas"),$=ie.getContext("2d"),_=Pt($),Ft="".concat((h+f)*_,"px"),Ht="".concat((c+E)*_,"px"),Ut=b||h/2,zt=w||c/2;if(ie.setAttribute("width",Ft),ie.setAttribute("height",Ht),$){$.translate(Ut*_,zt*_),$.rotate(Math.PI/180*Number(T));var Wt=f*_,we=E*_;if(B){var le=new Image;le.crossOrigin="anonymous",le.referrerPolicy="no-referrer",le.src=B,le.onload=function(){$.drawImage(le,0,0,Wt,we),Ie(ie.toDataURL())}}else if(S){var $t=Number(Se)*_;$.font="".concat(U," normal ").concat(M," ").concat($t,"px/").concat(we,"px ").concat(je),$.fillStyle=he,Array.isArray(S)?S==null||S.forEach(function(Kt,Gt){return $.fillText(Kt,0,Gt*50)}):$.fillText(S,0,0),Ie(ie.toDataURL())}}else console.error("\u5F53\u524D\u73AF\u5883\u4E0D\u652F\u6301Canvas")},[h,c,b,w,T,U,M,f,E,je,he,B,S,Se]),(0,y.jsxs)("div",{style:(0,m.Z)({position:"relative"},t),className:Lt,children:[r,(0,y.jsx)("div",{className:It,style:(0,m.Z)((0,m.Z)({zIndex:u,position:"absolute",left:0,top:0,width:"100%",height:"100%",backgroundSize:"".concat(h+f,"px"),pointerEvents:"none",backgroundRepeat:"repeat"},Le?{backgroundImage:"url('".concat(Le,"')")}:null),o)})]})},Et=pt,er=i(12395),Zt=["title","content","pageHeaderRender","header","prefixedClassName","extraContent","style","prefixCls","breadcrumbRender"],Rt=["children","loading","className","style","footer","affixProps","ghost","fixedHeader","breadcrumbRender"];function Ot(n){return(0,j.Z)(n)==="object"?n:{spinning:n}}var Nt=function(e){var r=e.tabList,t=e.tabActiveKey,a=e.onTabChange,o=e.tabBarExtraContent,s=e.tabProps,d=e.prefixedClassName;return Array.isArray(r)||o?(0,y.jsx)(Ne.Z,(0,m.Z)((0,m.Z)({className:"".concat(d,"-tabs"),activeKey:t,onChange:function(v){a&&a(v)},tabBarExtraContent:o},s),{},{children:r==null?void 0:r.map(function(u,v){return(0,l.createElement)(Ne.Z.TabPane,(0,m.Z)((0,m.Z)({},u),{},{tab:u.tab,key:u.key||v}))})})):null},Tt=function(e,r,t){return!e&&!r?null:(0,y.jsx)("div",{className:"".concat(t,"-detail"),children:(0,y.jsx)("div",{className:"".concat(t,"-main"),children:(0,y.jsxs)("div",{className:"".concat(t,"-row"),children:[e&&(0,y.jsx)("div",{className:"".concat(t,"-content"),children:e}),r&&(0,y.jsx)("div",{className:"".concat(t,"-extraContent"),children:r})]})})})},tr=function(e){var r=useContext(RouteContext);return _jsx("div",{style:{height:"100%",display:"flex",alignItems:"center"},children:_jsx(_Breadcrumb,_objectSpread(_objectSpread(_objectSpread({},r==null?void 0:r.breadcrumb),r==null?void 0:r.breadcrumbProps),e))})},Bt=function(e){var r,t=(0,l.useContext)(Te.Z),a=e.title,o=e.content,s=e.pageHeaderRender,d=e.header,u=e.prefixedClassName,v=e.extraContent,h=e.style,g=e.prefixCls,c=e.breadcrumbRender,C=(0,Oe.Z)(e,Zt),f=(0,l.useMemo)(function(){if(!!c)return c},[c]);if(s===!1)return null;if(s)return(0,y.jsxs)(y.Fragment,{children:[" ",s((0,m.Z)((0,m.Z)({},e),t))]});var P=a;!a&&a!==!1&&(P=t.title);var E=(0,m.Z)((0,m.Z)((0,m.Z)({},t),{},{title:P},C),{},{footer:Nt((0,m.Z)((0,m.Z)({},C),{},{breadcrumbRender:c,prefixedClassName:u}))},d),x=E.breadcrumb,T=(!x||!(x==null?void 0:x.itemRender)&&!(x==null||(r=x.routes)===null||r===void 0?void 0:r.length))&&!c;return["title","subTitle","extra","tags","footer","avatar","backIcon"].every(function(B){return!E[B]})&&T&&!o&&!v?null:(0,y.jsx)("div",{className:"".concat(u,"-warp"),children:(0,y.jsx)(xt,(0,m.Z)((0,m.Z)({},E),{},{breadcrumb:c===!1?void 0:(0,m.Z)((0,m.Z)({},E.breadcrumb),t.breadcrumbProps),breadcrumbRender:f,prefixCls:g,children:(d==null?void 0:d.children)||Tt(o,v,u)}))})},St=function(e){var r,t,a=e.children,o=e.loading,s=o===void 0?!1:o,d=e.className,u=e.style,v=e.footer,h=e.affixProps,g=e.ghost,c=e.fixedHeader,C=e.breadcrumbRender,f=(0,Oe.Z)(e,Rt),P=(0,l.useContext)(Te.Z),E=(0,l.useContext)(xe.ZP.ConfigContext),x=E.getPrefixCls,T=e.prefixCls||x("pro"),B="".concat(T,"-page-container"),S=R()(B,d,(r={},(0,H.Z)(r,"".concat(T,"-page-container-ghost"),g),(0,H.Z)(r,"".concat(T,"-page-container-with-footer"),v),r)),b=(0,l.useMemo)(function(){return a?(0,y.jsxs)(y.Fragment,{children:[(0,y.jsx)("div",{className:"".concat(B,"-children-content"),children:a}),P.hasFooterToolbar&&(0,y.jsx)("div",{style:{height:48,marginTop:24}})]}):null},[a,B,P.hasFooterToolbar]),w=(0,l.useMemo)(function(){var M;return C==!1?!1:C||(f==null||(M=f.header)===null||M===void 0?void 0:M.breadcrumbRender)},[C,f==null||(t=f.header)===null||t===void 0?void 0:t.breadcrumbRender]),L=(0,y.jsx)(Bt,(0,m.Z)((0,m.Z)({},f),{},{breadcrumbRender:w,ghost:g,prefixCls:void 0,prefixedClassName:B})),U=(0,l.useMemo)(function(){if(l.isValidElement(s))return s;if(typeof s=="boolean"&&!s)return null;var M=Ot(s);return M.spinning?(0,y.jsx)(Ct.Z,(0,m.Z)({},M)):null},[s]),F=(0,l.useMemo)(function(){var M=U||b;if(e.waterMarkProps||P.waterMarkProps){var ee=(0,m.Z)((0,m.Z)({},P.waterMarkProps),e.waterMarkProps);return(0,y.jsx)(Et,(0,m.Z)((0,m.Z)({},ee),{},{children:M}))}return M},[e.waterMarkProps,P.waterMarkProps,U,b]);return(0,y.jsxs)("div",{style:u,className:S,children:[c&&L?(0,y.jsx)(He,(0,m.Z)((0,m.Z)({offsetTop:P.hasHeader&&P.fixedHeader?P.headerHeight:0},h),{},{children:L})):L,F&&(0,y.jsx)(yt.Z,{children:F}),v&&(0,y.jsx)(bt.Z,{prefixCls:T,children:v})]})},Mt=St},56264:function(){},12395:function(){},70883:function(){},81262:function(){},59903:function(){},34952:function(ge,te,i){"use strict";var I=i(22122),se=i(15105),O=i(67294),H=function(Z,K){var j={};for(var N in Z)Object.prototype.hasOwnProperty.call(Z,N)&&K.indexOf(N)<0&&(j[N]=Z[N]);if(Z!=null&&typeof Object.getOwnPropertySymbols=="function")for(var R=0,N=Object.getOwnPropertySymbols(Z);R<N.length;R++)K.indexOf(N[R])<0&&Object.prototype.propertyIsEnumerable.call(Z,N[R])&&(j[N[R]]=Z[N[R]]);return j},z={border:0,background:"transparent",padding:0,lineHeight:"inherit",display:"inline-block"},re=O.forwardRef(function(Z,K){var j=function(W){var Y=W.keyCode;Y===se.Z.ENTER&&W.preventDefault()},N=function(W){var Y=W.keyCode,V=Z.onClick;Y===se.Z.ENTER&&V&&V()},R=Z.style,q=Z.noStyle,ae=Z.disabled,l=H(Z,["style","noStyle","disabled"]),A={};return q||(A=(0,I.Z)({},z)),ae&&(A.pointerEvents="none"),A=(0,I.Z)((0,I.Z)({},A),R),O.createElement("div",(0,I.Z)({role:"button",tabIndex:0,ref:K},l,{onKeyDown:j,onKeyUp:N,style:A}))});te.Z=re}}]);