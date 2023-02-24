const Be="modulepreload",Ue=function(e){return"/"+e},re={},_=function(t,n,r){if(!n||n.length===0)return t();const s=document.getElementsByTagName("link");return Promise.all(n.map(o=>{if(o=Ue(o),o in re)return;re[o]=!0;const i=o.endsWith(".css"),u=i?'[rel="stylesheet"]':"";if(!!r)for(let l=s.length-1;l>=0;l--){const h=s[l];if(h.href===o&&(!i||h.rel==="stylesheet"))return}else if(document.querySelector(`link[href="${o}"]${u}`))return;const c=document.createElement("link");if(c.rel=i?"stylesheet":Be,i||(c.as="script",c.crossOrigin=""),c.href=o,document.head.appendChild(c),i)return new Promise((l,h)=>{c.addEventListener("load",l),c.addEventListener("error",()=>h(new Error(`Unable to preload CSS for ${o}`)))})})).then(()=>t())},Xt=[{path:"",redirect:"/login"},{path:"/guide",name:"guide",component:()=>_(()=>import("./index-255227bf.js"),["assets/index-255227bf.js","assets/Logo-52c96a9e.js","assets/index-0d134fd7.js","assets/index-6980f424.js","assets/index-b4c0ca5b.js","assets/index-4bf230a6.js","assets/index-93dd7534.js","assets/index-16c322e9.css","assets/base-3111e043.css","assets/el-card-208b2445.css","assets/el-input-f1af85eb.css","assets/el-button-2cb60ae5.css"])},{path:"/login",name:"login",component:()=>_(()=>import("./login-b8293802.js"),["assets/login-b8293802.js","assets/index-6980f424.js","assets/Logo-52c96a9e.js","assets/vue-router-cb191d1c.js","assets/request-486a9ba7.js","assets/index-93dd7534.js","assets/index-0d134fd7.js","assets/index-b4c0ca5b.js","assets/index-4bf230a6.js","assets/login-7299e1d3.css","assets/base-3111e043.css","assets/el-card-208b2445.css","assets/el-input-f1af85eb.css","assets/el-button-2cb60ae5.css"])},{path:"/index",name:"index",component:()=>_(()=>import("./index-c3a7cdc3.js"),["assets/index-c3a7cdc3.js","assets/vue-router-cb191d1c.js","assets/index-6980f424.js","assets/index-6272c432.js","assets/index-b4c0ca5b.js","assets/index-98300a84.js","assets/index-bc0c355f.js","assets/index-74b391fd.js","assets/index-27f024ad.css","assets/base-3111e043.css","assets/el-main-f5c6a819.css","assets/el-button-2cb60ae5.css","assets/el-icon-68666798.css","assets/el-scrollbar-df5aa9a0.css"]),children:[{path:"home",name:"home",component:()=>_(()=>import("./home-60fdf833.js"),["assets/home-60fdf833.js","assets/request-486a9ba7.js","assets/vue-router-cb191d1c.js","assets/index-6980f424.js","assets/index-93dd7534.js","assets/index-512379a5.js","assets/index-4bf230a6.js","assets/index-bc0c355f.js","assets/index-74b391fd.js","assets/index-0572cb9e.js","assets/home-71eb0ad0.css","assets/base-3111e043.css","assets/el-main-f5c6a819.css","assets/el-empty-743b38f5.css","assets/el-scrollbar-df5aa9a0.css","assets/el-card-208b2445.css"])},{path:"botManage",name:"botManage",component:()=>_(()=>import("./botManage-828f876b.js"),["assets/botManage-828f876b.js","assets/request-486a9ba7.js","assets/vue-router-cb191d1c.js","assets/index-6980f424.js","assets/index-93dd7534.js","assets/index-0d134fd7.js","assets/index-b4c0ca5b.js","assets/index-8328b272.js","assets/index-bc0c355f.js","assets/index-98300a84.js","assets/index-0572cb9e.js","assets/botManage-49dc830b.css","assets/base-3111e043.css","assets/el-input-f1af85eb.css","assets/el-scrollbar-df5aa9a0.css","assets/el-icon-68666798.css","assets/el-empty-743b38f5.css","assets/el-button-2cb60ae5.css"])}]}];function pe(e,t){return function(){return e.apply(t,arguments)}}const{toString:me}=Object.prototype,{getPrototypeOf:G}=Object,Q=(e=>t=>{const n=me.call(t);return e[n]||(e[n]=n.slice(8,-1).toLowerCase())})(Object.create(null)),A=e=>(e=e.toLowerCase(),t=>Q(t)===e),H=e=>t=>typeof t===e,{isArray:x}=Array,F=H("undefined");function ke(e){return e!==null&&!F(e)&&e.constructor!==null&&!F(e.constructor)&&g(e.constructor.isBuffer)&&e.constructor.isBuffer(e)}const Ee=A("ArrayBuffer");function je(e){let t;return typeof ArrayBuffer<"u"&&ArrayBuffer.isView?t=ArrayBuffer.isView(e):t=e&&e.buffer&&Ee(e.buffer),t}const Ie=H("string"),g=H("function"),ye=H("number"),Y=e=>e!==null&&typeof e=="object",Me=e=>e===!0||e===!1,U=e=>{if(Q(e)!=="object")return!1;const t=G(e);return(t===null||t===Object.prototype||Object.getPrototypeOf(t)===null)&&!(Symbol.toStringTag in e)&&!(Symbol.iterator in e)},He=A("Date"),qe=A("File"),ze=A("Blob"),Je=A("FileList"),Ve=e=>Y(e)&&g(e.pipe),$e=e=>{const t="[object FormData]";return e&&(typeof FormData=="function"&&e instanceof FormData||me.call(e)===t||g(e.toString)&&e.toString()===t)},We=A("URLSearchParams"),Ke=e=>e.trim?e.trim():e.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,"");function D(e,t,{allOwnKeys:n=!1}={}){if(e===null||typeof e>"u")return;let r,s;if(typeof e!="object"&&(e=[e]),x(e))for(r=0,s=e.length;r<s;r++)t.call(null,e[r],r,e);else{const o=n?Object.getOwnPropertyNames(e):Object.keys(e),i=o.length;let u;for(r=0;r<i;r++)u=o[r],t.call(null,e[u],u,e)}}function we(e,t){t=t.toLowerCase();const n=Object.keys(e);let r=n.length,s;for(;r-- >0;)if(s=n[r],t===s.toLowerCase())return s;return null}const be=(()=>typeof globalThis<"u"?globalThis:typeof self<"u"?self:typeof window<"u"?window:global)(),Re=e=>!F(e)&&e!==be;function W(){const{caseless:e}=Re(this)&&this||{},t={},n=(r,s)=>{const o=e&&we(t,s)||s;U(t[o])&&U(r)?t[o]=W(t[o],r):U(r)?t[o]=W({},r):x(r)?t[o]=r.slice():t[o]=r};for(let r=0,s=arguments.length;r<s;r++)arguments[r]&&D(arguments[r],n);return t}const ve=(e,t,n,{allOwnKeys:r}={})=>(D(t,(s,o)=>{n&&g(s)?e[o]=pe(s,n):e[o]=s},{allOwnKeys:r}),e),Xe=e=>(e.charCodeAt(0)===65279&&(e=e.slice(1)),e),Ge=(e,t,n,r)=>{e.prototype=Object.create(t.prototype,r),e.prototype.constructor=e,Object.defineProperty(e,"super",{value:t.prototype}),n&&Object.assign(e.prototype,n)},Qe=(e,t,n,r)=>{let s,o,i;const u={};if(t=t||{},e==null)return t;do{for(s=Object.getOwnPropertyNames(e),o=s.length;o-- >0;)i=s[o],(!r||r(i,e,t))&&!u[i]&&(t[i]=e[i],u[i]=!0);e=n!==!1&&G(e)}while(e&&(!n||n(e,t))&&e!==Object.prototype);return t},Ye=(e,t,n)=>{e=String(e),(n===void 0||n>e.length)&&(n=e.length),n-=t.length;const r=e.indexOf(t,n);return r!==-1&&r===n},Ze=e=>{if(!e)return null;if(x(e))return e;let t=e.length;if(!ye(t))return null;const n=new Array(t);for(;t-- >0;)n[t]=e[t];return n},et=(e=>t=>e&&t instanceof e)(typeof Uint8Array<"u"&&G(Uint8Array)),tt=(e,t)=>{const r=(e&&e[Symbol.iterator]).call(e);let s;for(;(s=r.next())&&!s.done;){const o=s.value;t.call(e,o[0],o[1])}},nt=(e,t)=>{let n;const r=[];for(;(n=e.exec(t))!==null;)r.push(n);return r},rt=A("HTMLFormElement"),st=e=>e.toLowerCase().replace(/[_-\s]([a-z\d])(\w*)/g,function(n,r,s){return r.toUpperCase()+s}),se=(({hasOwnProperty:e})=>(t,n)=>e.call(t,n))(Object.prototype),ot=A("RegExp"),Se=(e,t)=>{const n=Object.getOwnPropertyDescriptors(e),r={};D(n,(s,o)=>{t(s,o,e)!==!1&&(r[o]=s)}),Object.defineProperties(e,r)},it=e=>{Se(e,(t,n)=>{if(g(e)&&["arguments","caller","callee"].indexOf(n)!==-1)return!1;const r=e[n];if(!!g(r)){if(t.enumerable=!1,"writable"in t){t.writable=!1;return}t.set||(t.set=()=>{throw Error("Can not rewrite read-only method '"+n+"'")})}})},at=(e,t)=>{const n={},r=s=>{s.forEach(o=>{n[o]=!0})};return x(e)?r(e):r(String(e).split(t)),n},ct=()=>{},ut=(e,t)=>(e=+e,Number.isFinite(e)?e:t),lt=e=>{const t=new Array(10),n=(r,s)=>{if(Y(r)){if(t.indexOf(r)>=0)return;if(!("toJSON"in r)){t[s]=r;const o=x(r)?[]:{};return D(r,(i,u)=>{const d=n(i,s+1);!F(d)&&(o[u]=d)}),t[s]=void 0,o}}return r};return n(e,0)},a={isArray:x,isArrayBuffer:Ee,isBuffer:ke,isFormData:$e,isArrayBufferView:je,isString:Ie,isNumber:ye,isBoolean:Me,isObject:Y,isPlainObject:U,isUndefined:F,isDate:He,isFile:qe,isBlob:ze,isRegExp:ot,isFunction:g,isStream:Ve,isURLSearchParams:We,isTypedArray:et,isFileList:Je,forEach:D,merge:W,extend:ve,trim:Ke,stripBOM:Xe,inherits:Ge,toFlatObject:Qe,kindOf:Q,kindOfTest:A,endsWith:Ye,toArray:Ze,forEachEntry:tt,matchAll:nt,isHTMLForm:rt,hasOwnProperty:se,hasOwnProp:se,reduceDescriptors:Se,freezeMethods:it,toObjectSet:at,toCamelCase:st,noop:ct,toFiniteNumber:ut,findKey:we,global:be,isContextDefined:Re,toJSONObject:lt};function m(e,t,n,r,s){Error.call(this),Error.captureStackTrace?Error.captureStackTrace(this,this.constructor):this.stack=new Error().stack,this.message=e,this.name="AxiosError",t&&(this.code=t),n&&(this.config=n),r&&(this.request=r),s&&(this.response=s)}a.inherits(m,Error,{toJSON:function(){return{message:this.message,name:this.name,description:this.description,number:this.number,fileName:this.fileName,lineNumber:this.lineNumber,columnNumber:this.columnNumber,stack:this.stack,config:a.toJSONObject(this.config),code:this.code,status:this.response&&this.response.status?this.response.status:null}}});const Oe=m.prototype,Ae={};["ERR_BAD_OPTION_VALUE","ERR_BAD_OPTION","ECONNABORTED","ETIMEDOUT","ERR_NETWORK","ERR_FR_TOO_MANY_REDIRECTS","ERR_DEPRECATED","ERR_BAD_RESPONSE","ERR_BAD_REQUEST","ERR_CANCELED","ERR_NOT_SUPPORT","ERR_INVALID_URL"].forEach(e=>{Ae[e]={value:e}});Object.defineProperties(m,Ae);Object.defineProperty(Oe,"isAxiosError",{value:!0});m.from=(e,t,n,r,s,o)=>{const i=Object.create(Oe);return a.toFlatObject(e,i,function(d){return d!==Error.prototype},u=>u!=="isAxiosError"),m.call(i,e.message,t,n,r,s),i.cause=e,i.name=e.name,o&&Object.assign(i,o),i};var ft=typeof self=="object"?self.FormData:window.FormData;const dt=ft;function K(e){return a.isPlainObject(e)||a.isArray(e)}function Te(e){return a.endsWith(e,"[]")?e.slice(0,-2):e}function oe(e,t,n){return e?e.concat(t).map(function(s,o){return s=Te(s),!n&&o?"["+s+"]":s}).join(n?".":""):t}function ht(e){return a.isArray(e)&&!e.some(K)}const pt=a.toFlatObject(a,{},null,function(t){return/^is[A-Z]/.test(t)});function mt(e){return e&&a.isFunction(e.append)&&e[Symbol.toStringTag]==="FormData"&&e[Symbol.iterator]}function q(e,t,n){if(!a.isObject(e))throw new TypeError("target must be an object");t=t||new(dt||FormData),n=a.toFlatObject(n,{metaTokens:!0,dots:!1,indexes:!1},!1,function(p,S){return!a.isUndefined(S[p])});const r=n.metaTokens,s=n.visitor||l,o=n.dots,i=n.indexes,d=(n.Blob||typeof Blob<"u"&&Blob)&&mt(t);if(!a.isFunction(s))throw new TypeError("visitor must be a function");function c(f){if(f===null)return"";if(a.isDate(f))return f.toISOString();if(!d&&a.isBlob(f))throw new m("Blob is not supported. Use a Buffer instead.");return a.isArrayBuffer(f)||a.isTypedArray(f)?d&&typeof Blob=="function"?new Blob([f]):Buffer.from(f):f}function l(f,p,S){let b=f;if(f&&!S&&typeof f=="object"){if(a.endsWith(p,"{}"))p=r?p:p.slice(0,-2),f=JSON.stringify(f);else if(a.isArray(f)&&ht(f)||a.isFileList(f)||a.endsWith(p,"[]")&&(b=a.toArray(f)))return p=Te(p),b.forEach(function(B,Le){!(a.isUndefined(B)||B===null)&&t.append(i===!0?oe([p],Le,o):i===null?p:p+"[]",c(B))}),!1}return K(f)?!0:(t.append(oe(S,p,o),c(f)),!1)}const h=[],w=Object.assign(pt,{defaultVisitor:l,convertValue:c,isVisitable:K});function E(f,p){if(!a.isUndefined(f)){if(h.indexOf(f)!==-1)throw Error("Circular reference detected in "+p.join("."));h.push(f),a.forEach(f,function(b,N){(!(a.isUndefined(b)||b===null)&&s.call(t,b,a.isString(N)?N.trim():N,p,w))===!0&&E(b,p?p.concat(N):[N])}),h.pop()}}if(!a.isObject(e))throw new TypeError("data must be an object");return E(e),t}function ie(e){const t={"!":"%21","'":"%27","(":"%28",")":"%29","~":"%7E","%20":"+","%00":"\0"};return encodeURIComponent(e).replace(/[!'()~]|%20|%00/g,function(r){return t[r]})}function Z(e,t){this._pairs=[],e&&q(e,this,t)}const ge=Z.prototype;ge.append=function(t,n){this._pairs.push([t,n])};ge.toString=function(t){const n=t?function(r){return t.call(this,r,ie)}:ie;return this._pairs.map(function(s){return n(s[0])+"="+n(s[1])},"").join("&")};function Et(e){return encodeURIComponent(e).replace(/%3A/gi,":").replace(/%24/g,"$").replace(/%2C/gi,",").replace(/%20/g,"+").replace(/%5B/gi,"[").replace(/%5D/gi,"]")}function Ne(e,t,n){if(!t)return e;const r=n&&n.encode||Et,s=n&&n.serialize;let o;if(s?o=s(t,n):o=a.isURLSearchParams(t)?t.toString():new Z(t,n).toString(r),o){const i=e.indexOf("#");i!==-1&&(e=e.slice(0,i)),e+=(e.indexOf("?")===-1?"?":"&")+o}return e}class yt{constructor(){this.handlers=[]}use(t,n,r){return this.handlers.push({fulfilled:t,rejected:n,synchronous:r?r.synchronous:!1,runWhen:r?r.runWhen:null}),this.handlers.length-1}eject(t){this.handlers[t]&&(this.handlers[t]=null)}clear(){this.handlers&&(this.handlers=[])}forEach(t){a.forEach(this.handlers,function(r){r!==null&&t(r)})}}const ae=yt,Pe={silentJSONParsing:!0,forcedJSONParsing:!0,clarifyTimeoutError:!1},wt=typeof URLSearchParams<"u"?URLSearchParams:Z,bt=FormData,Rt=(()=>{let e;return typeof navigator<"u"&&((e=navigator.product)==="ReactNative"||e==="NativeScript"||e==="NS")?!1:typeof window<"u"&&typeof document<"u"})(),St=(()=>typeof WorkerGlobalScope<"u"&&self instanceof WorkerGlobalScope&&typeof self.importScripts=="function")(),R={isBrowser:!0,classes:{URLSearchParams:wt,FormData:bt,Blob},isStandardBrowserEnv:Rt,isStandardBrowserWebWorkerEnv:St,protocols:["http","https","file","blob","url","data"]};function Ot(e,t){return q(e,new R.classes.URLSearchParams,Object.assign({visitor:function(n,r,s,o){return R.isNode&&a.isBuffer(n)?(this.append(r,n.toString("base64")),!1):o.defaultVisitor.apply(this,arguments)}},t))}function At(e){return a.matchAll(/\w+|\[(\w*)]/g,e).map(t=>t[0]==="[]"?"":t[1]||t[0])}function Tt(e){const t={},n=Object.keys(e);let r;const s=n.length;let o;for(r=0;r<s;r++)o=n[r],t[o]=e[o];return t}function xe(e){function t(n,r,s,o){let i=n[o++];const u=Number.isFinite(+i),d=o>=n.length;return i=!i&&a.isArray(s)?s.length:i,d?(a.hasOwnProp(s,i)?s[i]=[s[i],r]:s[i]=r,!u):((!s[i]||!a.isObject(s[i]))&&(s[i]=[]),t(n,r,s[i],o)&&a.isArray(s[i])&&(s[i]=Tt(s[i])),!u)}if(a.isFormData(e)&&a.isFunction(e.entries)){const n={};return a.forEachEntry(e,(r,s)=>{t(At(r),s,n,0)}),n}return null}const gt={"Content-Type":void 0};function Nt(e,t,n){if(a.isString(e))try{return(t||JSON.parse)(e),a.trim(e)}catch(r){if(r.name!=="SyntaxError")throw r}return(n||JSON.stringify)(e)}const z={transitional:Pe,adapter:["xhr","http"],transformRequest:[function(t,n){const r=n.getContentType()||"",s=r.indexOf("application/json")>-1,o=a.isObject(t);if(o&&a.isHTMLForm(t)&&(t=new FormData(t)),a.isFormData(t))return s&&s?JSON.stringify(xe(t)):t;if(a.isArrayBuffer(t)||a.isBuffer(t)||a.isStream(t)||a.isFile(t)||a.isBlob(t))return t;if(a.isArrayBufferView(t))return t.buffer;if(a.isURLSearchParams(t))return n.setContentType("application/x-www-form-urlencoded;charset=utf-8",!1),t.toString();let u;if(o){if(r.indexOf("application/x-www-form-urlencoded")>-1)return Ot(t,this.formSerializer).toString();if((u=a.isFileList(t))||r.indexOf("multipart/form-data")>-1){const d=this.env&&this.env.FormData;return q(u?{"files[]":t}:t,d&&new d,this.formSerializer)}}return o||s?(n.setContentType("application/json",!1),Nt(t)):t}],transformResponse:[function(t){const n=this.transitional||z.transitional,r=n&&n.forcedJSONParsing,s=this.responseType==="json";if(t&&a.isString(t)&&(r&&!this.responseType||s)){const i=!(n&&n.silentJSONParsing)&&s;try{return JSON.parse(t)}catch(u){if(i)throw u.name==="SyntaxError"?m.from(u,m.ERR_BAD_RESPONSE,this,null,this.response):u}}return t}],timeout:0,xsrfCookieName:"XSRF-TOKEN",xsrfHeaderName:"X-XSRF-TOKEN",maxContentLength:-1,maxBodyLength:-1,env:{FormData:R.classes.FormData,Blob:R.classes.Blob},validateStatus:function(t){return t>=200&&t<300},headers:{common:{Accept:"application/json, text/plain, */*"}}};a.forEach(["delete","get","head"],function(t){z.headers[t]={}});a.forEach(["post","put","patch"],function(t){z.headers[t]=a.merge(gt)});const ee=z,Pt=a.toObjectSet(["age","authorization","content-length","content-type","etag","expires","from","host","if-modified-since","if-unmodified-since","last-modified","location","max-forwards","proxy-authorization","referer","retry-after","user-agent"]),xt=e=>{const t={};let n,r,s;return e&&e.split(`
`).forEach(function(i){s=i.indexOf(":"),n=i.substring(0,s).trim().toLowerCase(),r=i.substring(s+1).trim(),!(!n||t[n]&&Pt[n])&&(n==="set-cookie"?t[n]?t[n].push(r):t[n]=[r]:t[n]=t[n]?t[n]+", "+r:r)}),t},ce=Symbol("internals");function C(e){return e&&String(e).trim().toLowerCase()}function k(e){return e===!1||e==null?e:a.isArray(e)?e.map(k):String(e)}function _t(e){const t=Object.create(null),n=/([^\s,;=]+)\s*(?:=\s*([^,;]+))?/g;let r;for(;r=n.exec(e);)t[r[1]]=r[2];return t}function Ct(e){return/^[-_a-zA-Z]+$/.test(e.trim())}function ue(e,t,n,r){if(a.isFunction(r))return r.call(this,t,n);if(!!a.isString(t)){if(a.isString(r))return t.indexOf(r)!==-1;if(a.isRegExp(r))return r.test(t)}}function Ft(e){return e.trim().toLowerCase().replace(/([a-z\d])(\w*)/g,(t,n,r)=>n.toUpperCase()+r)}function Dt(e,t){const n=a.toCamelCase(" "+t);["get","set","has"].forEach(r=>{Object.defineProperty(e,r+n,{value:function(s,o,i){return this[r].call(this,t,s,o,i)},configurable:!0})})}class J{constructor(t){t&&this.set(t)}set(t,n,r){const s=this;function o(u,d,c){const l=C(d);if(!l)throw new Error("header name must be a non-empty string");const h=a.findKey(s,l);(!h||s[h]===void 0||c===!0||c===void 0&&s[h]!==!1)&&(s[h||d]=k(u))}const i=(u,d)=>a.forEach(u,(c,l)=>o(c,l,d));return a.isPlainObject(t)||t instanceof this.constructor?i(t,n):a.isString(t)&&(t=t.trim())&&!Ct(t)?i(xt(t),n):t!=null&&o(n,t,r),this}get(t,n){if(t=C(t),t){const r=a.findKey(this,t);if(r){const s=this[r];if(!n)return s;if(n===!0)return _t(s);if(a.isFunction(n))return n.call(this,s,r);if(a.isRegExp(n))return n.exec(s);throw new TypeError("parser must be boolean|regexp|function")}}}has(t,n){if(t=C(t),t){const r=a.findKey(this,t);return!!(r&&(!n||ue(this,this[r],r,n)))}return!1}delete(t,n){const r=this;let s=!1;function o(i){if(i=C(i),i){const u=a.findKey(r,i);u&&(!n||ue(r,r[u],u,n))&&(delete r[u],s=!0)}}return a.isArray(t)?t.forEach(o):o(t),s}clear(){return Object.keys(this).forEach(this.delete.bind(this))}normalize(t){const n=this,r={};return a.forEach(this,(s,o)=>{const i=a.findKey(r,o);if(i){n[i]=k(s),delete n[o];return}const u=t?Ft(o):String(o).trim();u!==o&&delete n[o],n[u]=k(s),r[u]=!0}),this}concat(...t){return this.constructor.concat(this,...t)}toJSON(t){const n=Object.create(null);return a.forEach(this,(r,s)=>{r!=null&&r!==!1&&(n[s]=t&&a.isArray(r)?r.join(", "):r)}),n}[Symbol.iterator](){return Object.entries(this.toJSON())[Symbol.iterator]()}toString(){return Object.entries(this.toJSON()).map(([t,n])=>t+": "+n).join(`
`)}get[Symbol.toStringTag](){return"AxiosHeaders"}static from(t){return t instanceof this?t:new this(t)}static concat(t,...n){const r=new this(t);return n.forEach(s=>r.set(s)),r}static accessor(t){const r=(this[ce]=this[ce]={accessors:{}}).accessors,s=this.prototype;function o(i){const u=C(i);r[u]||(Dt(s,i),r[u]=!0)}return a.isArray(t)?t.forEach(o):o(t),this}}J.accessor(["Content-Type","Content-Length","Accept","Accept-Encoding","User-Agent"]);a.freezeMethods(J.prototype);a.freezeMethods(J);const O=J;function V(e,t){const n=this||ee,r=t||n,s=O.from(r.headers);let o=r.data;return a.forEach(e,function(u){o=u.call(n,o,s.normalize(),t?t.status:void 0)}),s.normalize(),o}function _e(e){return!!(e&&e.__CANCEL__)}function L(e,t,n){m.call(this,e??"canceled",m.ERR_CANCELED,t,n),this.name="CanceledError"}a.inherits(L,m,{__CANCEL__:!0});const Lt=null;function Bt(e,t,n){const r=n.config.validateStatus;!n.status||!r||r(n.status)?e(n):t(new m("Request failed with status code "+n.status,[m.ERR_BAD_REQUEST,m.ERR_BAD_RESPONSE][Math.floor(n.status/100)-4],n.config,n.request,n))}const Ut=R.isStandardBrowserEnv?function(){return{write:function(n,r,s,o,i,u){const d=[];d.push(n+"="+encodeURIComponent(r)),a.isNumber(s)&&d.push("expires="+new Date(s).toGMTString()),a.isString(o)&&d.push("path="+o),a.isString(i)&&d.push("domain="+i),u===!0&&d.push("secure"),document.cookie=d.join("; ")},read:function(n){const r=document.cookie.match(new RegExp("(^|;\\s*)("+n+")=([^;]*)"));return r?decodeURIComponent(r[3]):null},remove:function(n){this.write(n,"",Date.now()-864e5)}}}():function(){return{write:function(){},read:function(){return null},remove:function(){}}}();function kt(e){return/^([a-z][a-z\d+\-.]*:)?\/\//i.test(e)}function jt(e,t){return t?e.replace(/\/+$/,"")+"/"+t.replace(/^\/+/,""):e}function Ce(e,t){return e&&!kt(t)?jt(e,t):t}const It=R.isStandardBrowserEnv?function(){const t=/(msie|trident)/i.test(navigator.userAgent),n=document.createElement("a");let r;function s(o){let i=o;return t&&(n.setAttribute("href",i),i=n.href),n.setAttribute("href",i),{href:n.href,protocol:n.protocol?n.protocol.replace(/:$/,""):"",host:n.host,search:n.search?n.search.replace(/^\?/,""):"",hash:n.hash?n.hash.replace(/^#/,""):"",hostname:n.hostname,port:n.port,pathname:n.pathname.charAt(0)==="/"?n.pathname:"/"+n.pathname}}return r=s(window.location.href),function(i){const u=a.isString(i)?s(i):i;return u.protocol===r.protocol&&u.host===r.host}}():function(){return function(){return!0}}();function Mt(e){const t=/^([-+\w]{1,25})(:?\/\/|:)/.exec(e);return t&&t[1]||""}function Ht(e,t){e=e||10;const n=new Array(e),r=new Array(e);let s=0,o=0,i;return t=t!==void 0?t:1e3,function(d){const c=Date.now(),l=r[o];i||(i=c),n[s]=d,r[s]=c;let h=o,w=0;for(;h!==s;)w+=n[h++],h=h%e;if(s=(s+1)%e,s===o&&(o=(o+1)%e),c-i<t)return;const E=l&&c-l;return E?Math.round(w*1e3/E):void 0}}function le(e,t){let n=0;const r=Ht(50,250);return s=>{const o=s.loaded,i=s.lengthComputable?s.total:void 0,u=o-n,d=r(u),c=o<=i;n=o;const l={loaded:o,total:i,progress:i?o/i:void 0,bytes:u,rate:d||void 0,estimated:d&&i&&c?(i-o)/d:void 0,event:s};l[t?"download":"upload"]=!0,e(l)}}const qt=typeof XMLHttpRequest<"u",zt=qt&&function(e){return new Promise(function(n,r){let s=e.data;const o=O.from(e.headers).normalize(),i=e.responseType;let u;function d(){e.cancelToken&&e.cancelToken.unsubscribe(u),e.signal&&e.signal.removeEventListener("abort",u)}a.isFormData(s)&&(R.isStandardBrowserEnv||R.isStandardBrowserWebWorkerEnv)&&o.setContentType(!1);let c=new XMLHttpRequest;if(e.auth){const E=e.auth.username||"",f=e.auth.password?unescape(encodeURIComponent(e.auth.password)):"";o.set("Authorization","Basic "+btoa(E+":"+f))}const l=Ce(e.baseURL,e.url);c.open(e.method.toUpperCase(),Ne(l,e.params,e.paramsSerializer),!0),c.timeout=e.timeout;function h(){if(!c)return;const E=O.from("getAllResponseHeaders"in c&&c.getAllResponseHeaders()),p={data:!i||i==="text"||i==="json"?c.responseText:c.response,status:c.status,statusText:c.statusText,headers:E,config:e,request:c};Bt(function(b){n(b),d()},function(b){r(b),d()},p),c=null}if("onloadend"in c?c.onloadend=h:c.onreadystatechange=function(){!c||c.readyState!==4||c.status===0&&!(c.responseURL&&c.responseURL.indexOf("file:")===0)||setTimeout(h)},c.onabort=function(){!c||(r(new m("Request aborted",m.ECONNABORTED,e,c)),c=null)},c.onerror=function(){r(new m("Network Error",m.ERR_NETWORK,e,c)),c=null},c.ontimeout=function(){let f=e.timeout?"timeout of "+e.timeout+"ms exceeded":"timeout exceeded";const p=e.transitional||Pe;e.timeoutErrorMessage&&(f=e.timeoutErrorMessage),r(new m(f,p.clarifyTimeoutError?m.ETIMEDOUT:m.ECONNABORTED,e,c)),c=null},R.isStandardBrowserEnv){const E=(e.withCredentials||It(l))&&e.xsrfCookieName&&Ut.read(e.xsrfCookieName);E&&o.set(e.xsrfHeaderName,E)}s===void 0&&o.setContentType(null),"setRequestHeader"in c&&a.forEach(o.toJSON(),function(f,p){c.setRequestHeader(p,f)}),a.isUndefined(e.withCredentials)||(c.withCredentials=!!e.withCredentials),i&&i!=="json"&&(c.responseType=e.responseType),typeof e.onDownloadProgress=="function"&&c.addEventListener("progress",le(e.onDownloadProgress,!0)),typeof e.onUploadProgress=="function"&&c.upload&&c.upload.addEventListener("progress",le(e.onUploadProgress)),(e.cancelToken||e.signal)&&(u=E=>{!c||(r(!E||E.type?new L(null,e,c):E),c.abort(),c=null)},e.cancelToken&&e.cancelToken.subscribe(u),e.signal&&(e.signal.aborted?u():e.signal.addEventListener("abort",u)));const w=Mt(l);if(w&&R.protocols.indexOf(w)===-1){r(new m("Unsupported protocol "+w+":",m.ERR_BAD_REQUEST,e));return}c.send(s||null)})},j={http:Lt,xhr:zt};a.forEach(j,(e,t)=>{if(e){try{Object.defineProperty(e,"name",{value:t})}catch{}Object.defineProperty(e,"adapterName",{value:t})}});const Jt={getAdapter:e=>{e=a.isArray(e)?e:[e];const{length:t}=e;let n,r;for(let s=0;s<t&&(n=e[s],!(r=a.isString(n)?j[n.toLowerCase()]:n));s++);if(!r)throw r===!1?new m(`Adapter ${n} is not supported by the environment`,"ERR_NOT_SUPPORT"):new Error(a.hasOwnProp(j,n)?`Adapter '${n}' is not available in the build`:`Unknown adapter '${n}'`);if(!a.isFunction(r))throw new TypeError("adapter is not a function");return r},adapters:j};function $(e){if(e.cancelToken&&e.cancelToken.throwIfRequested(),e.signal&&e.signal.aborted)throw new L(null,e)}function fe(e){return $(e),e.headers=O.from(e.headers),e.data=V.call(e,e.transformRequest),["post","put","patch"].indexOf(e.method)!==-1&&e.headers.setContentType("application/x-www-form-urlencoded",!1),Jt.getAdapter(e.adapter||ee.adapter)(e).then(function(r){return $(e),r.data=V.call(e,e.transformResponse,r),r.headers=O.from(r.headers),r},function(r){return _e(r)||($(e),r&&r.response&&(r.response.data=V.call(e,e.transformResponse,r.response),r.response.headers=O.from(r.response.headers))),Promise.reject(r)})}const de=e=>e instanceof O?e.toJSON():e;function P(e,t){t=t||{};const n={};function r(c,l,h){return a.isPlainObject(c)&&a.isPlainObject(l)?a.merge.call({caseless:h},c,l):a.isPlainObject(l)?a.merge({},l):a.isArray(l)?l.slice():l}function s(c,l,h){if(a.isUndefined(l)){if(!a.isUndefined(c))return r(void 0,c,h)}else return r(c,l,h)}function o(c,l){if(!a.isUndefined(l))return r(void 0,l)}function i(c,l){if(a.isUndefined(l)){if(!a.isUndefined(c))return r(void 0,c)}else return r(void 0,l)}function u(c,l,h){if(h in t)return r(c,l);if(h in e)return r(void 0,c)}const d={url:o,method:o,data:o,baseURL:i,transformRequest:i,transformResponse:i,paramsSerializer:i,timeout:i,timeoutMessage:i,withCredentials:i,adapter:i,responseType:i,xsrfCookieName:i,xsrfHeaderName:i,onUploadProgress:i,onDownloadProgress:i,decompress:i,maxContentLength:i,maxBodyLength:i,beforeRedirect:i,transport:i,httpAgent:i,httpsAgent:i,cancelToken:i,socketPath:i,responseEncoding:i,validateStatus:u,headers:(c,l)=>s(de(c),de(l),!0)};return a.forEach(Object.keys(e).concat(Object.keys(t)),function(l){const h=d[l]||s,w=h(e[l],t[l],l);a.isUndefined(w)&&h!==u||(n[l]=w)}),n}const Fe="1.2.2",te={};["object","boolean","number","function","string","symbol"].forEach((e,t)=>{te[e]=function(r){return typeof r===e||"a"+(t<1?"n ":" ")+e}});const he={};te.transitional=function(t,n,r){function s(o,i){return"[Axios v"+Fe+"] Transitional option '"+o+"'"+i+(r?". "+r:"")}return(o,i,u)=>{if(t===!1)throw new m(s(i," has been removed"+(n?" in "+n:"")),m.ERR_DEPRECATED);return n&&!he[i]&&(he[i]=!0,console.warn(s(i," has been deprecated since v"+n+" and will be removed in the near future"))),t?t(o,i,u):!0}};function Vt(e,t,n){if(typeof e!="object")throw new m("options must be an object",m.ERR_BAD_OPTION_VALUE);const r=Object.keys(e);let s=r.length;for(;s-- >0;){const o=r[s],i=t[o];if(i){const u=e[o],d=u===void 0||i(u,o,e);if(d!==!0)throw new m("option "+o+" must be "+d,m.ERR_BAD_OPTION_VALUE);continue}if(n!==!0)throw new m("Unknown option "+o,m.ERR_BAD_OPTION)}}const v={assertOptions:Vt,validators:te},T=v.validators;class M{constructor(t){this.defaults=t,this.interceptors={request:new ae,response:new ae}}request(t,n){typeof t=="string"?(n=n||{},n.url=t):n=t||{},n=P(this.defaults,n);const{transitional:r,paramsSerializer:s,headers:o}=n;r!==void 0&&v.assertOptions(r,{silentJSONParsing:T.transitional(T.boolean),forcedJSONParsing:T.transitional(T.boolean),clarifyTimeoutError:T.transitional(T.boolean)},!1),s!==void 0&&v.assertOptions(s,{encode:T.function,serialize:T.function},!0),n.method=(n.method||this.defaults.method||"get").toLowerCase();let i;i=o&&a.merge(o.common,o[n.method]),i&&a.forEach(["delete","get","head","post","put","patch","common"],f=>{delete o[f]}),n.headers=O.concat(i,o);const u=[];let d=!0;this.interceptors.request.forEach(function(p){typeof p.runWhen=="function"&&p.runWhen(n)===!1||(d=d&&p.synchronous,u.unshift(p.fulfilled,p.rejected))});const c=[];this.interceptors.response.forEach(function(p){c.push(p.fulfilled,p.rejected)});let l,h=0,w;if(!d){const f=[fe.bind(this),void 0];for(f.unshift.apply(f,u),f.push.apply(f,c),w=f.length,l=Promise.resolve(n);h<w;)l=l.then(f[h++],f[h++]);return l}w=u.length;let E=n;for(h=0;h<w;){const f=u[h++],p=u[h++];try{E=f(E)}catch(S){p.call(this,S);break}}try{l=fe.call(this,E)}catch(f){return Promise.reject(f)}for(h=0,w=c.length;h<w;)l=l.then(c[h++],c[h++]);return l}getUri(t){t=P(this.defaults,t);const n=Ce(t.baseURL,t.url);return Ne(n,t.params,t.paramsSerializer)}}a.forEach(["delete","get","head","options"],function(t){M.prototype[t]=function(n,r){return this.request(P(r||{},{method:t,url:n,data:(r||{}).data}))}});a.forEach(["post","put","patch"],function(t){function n(r){return function(o,i,u){return this.request(P(u||{},{method:t,headers:r?{"Content-Type":"multipart/form-data"}:{},url:o,data:i}))}}M.prototype[t]=n(),M.prototype[t+"Form"]=n(!0)});const I=M;class ne{constructor(t){if(typeof t!="function")throw new TypeError("executor must be a function.");let n;this.promise=new Promise(function(o){n=o});const r=this;this.promise.then(s=>{if(!r._listeners)return;let o=r._listeners.length;for(;o-- >0;)r._listeners[o](s);r._listeners=null}),this.promise.then=s=>{let o;const i=new Promise(u=>{r.subscribe(u),o=u}).then(s);return i.cancel=function(){r.unsubscribe(o)},i},t(function(o,i,u){r.reason||(r.reason=new L(o,i,u),n(r.reason))})}throwIfRequested(){if(this.reason)throw this.reason}subscribe(t){if(this.reason){t(this.reason);return}this._listeners?this._listeners.push(t):this._listeners=[t]}unsubscribe(t){if(!this._listeners)return;const n=this._listeners.indexOf(t);n!==-1&&this._listeners.splice(n,1)}static source(){let t;return{token:new ne(function(s){t=s}),cancel:t}}}const $t=ne;function Wt(e){return function(n){return e.apply(null,n)}}function Kt(e){return a.isObject(e)&&e.isAxiosError===!0}const X={Continue:100,SwitchingProtocols:101,Processing:102,EarlyHints:103,Ok:200,Created:201,Accepted:202,NonAuthoritativeInformation:203,NoContent:204,ResetContent:205,PartialContent:206,MultiStatus:207,AlreadyReported:208,ImUsed:226,MultipleChoices:300,MovedPermanently:301,Found:302,SeeOther:303,NotModified:304,UseProxy:305,Unused:306,TemporaryRedirect:307,PermanentRedirect:308,BadRequest:400,Unauthorized:401,PaymentRequired:402,Forbidden:403,NotFound:404,MethodNotAllowed:405,NotAcceptable:406,ProxyAuthenticationRequired:407,RequestTimeout:408,Conflict:409,Gone:410,LengthRequired:411,PreconditionFailed:412,PayloadTooLarge:413,UriTooLong:414,UnsupportedMediaType:415,RangeNotSatisfiable:416,ExpectationFailed:417,ImATeapot:418,MisdirectedRequest:421,UnprocessableEntity:422,Locked:423,FailedDependency:424,TooEarly:425,UpgradeRequired:426,PreconditionRequired:428,TooManyRequests:429,RequestHeaderFieldsTooLarge:431,UnavailableForLegalReasons:451,InternalServerError:500,NotImplemented:501,BadGateway:502,ServiceUnavailable:503,GatewayTimeout:504,HttpVersionNotSupported:505,VariantAlsoNegotiates:506,InsufficientStorage:507,LoopDetected:508,NotExtended:510,NetworkAuthenticationRequired:511};Object.entries(X).forEach(([e,t])=>{X[t]=e});const vt=X;function De(e){const t=new I(e),n=pe(I.prototype.request,t);return a.extend(n,I.prototype,t,{allOwnKeys:!0}),a.extend(n,t,null,{allOwnKeys:!0}),n.create=function(s){return De(P(e,s))},n}const y=De(ee);y.Axios=I;y.CanceledError=L;y.CancelToken=$t;y.isCancel=_e;y.VERSION=Fe;y.toFormData=q;y.AxiosError=m;y.Cancel=y.CanceledError;y.all=function(t){return Promise.all(t)};y.spread=Wt;y.isAxiosError=Kt;y.mergeConfig=P;y.AxiosHeaders=O;y.formToJSON=e=>xe(a.isHTMLForm(e)?new FormData(e):e);y.HttpStatusCode=vt;y.default=y;const Gt=y;export{Gt as a,Xt as r};
