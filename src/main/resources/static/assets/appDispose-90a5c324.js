/* empty css             *//* empty css                *//* empty css                     *//* empty css                        *//* empty css                   *//* empty css                  *//* empty css                 *//* empty css                  *//* empty css                           */import{a as B,E as D}from"./index-aeb487f9.js";import{v as O,m as A,k as J,u as I}from"./index-c91c3485.js";import{E as T}from"./index-1f52bcde.js";import{E as F}from"./index-79c455fb.js";import{a as L}from"./index-008ceb8f.js";import{E as P}from"./index-5843c3e2.js";import{E as q}from"./index-ce00a3bf.js";import{E as $}from"./index-2ccb0fbe.js";import{g as z,b as G,o as s,c as h,j as H,a as p,q as r,y,p as i,F as K,D as M,x as Q}from"./runtime-core.esm-bundler-a054f4a5.js";import"./index-6295fd1d.js";import"./index-61f8e38d.js";import"./index-ee0e1e3b.js";import"./index-3df48e54.js";import"./index-76d982ae.js";import"./event-9519ab40.js";import"./_Uint8Array-a52b1d09.js";const R={class:"",style:{height:"97%"}},W={class:"crumbs",style:{"margin-top":"10px","margin-left":"20px","margin-bottom":"10px"}},ke=z({__name:"appDispose",setup(X){const m=G([{name:"昵称",type:"string"},{name:"金手指",type:"bool"},{name:"金手指等级",type:"slider",mix:1,max:10},{name:"性别",type:"select",multiple:!0,options:["男","女"],option_url:"/api"},{name:"玩家信息",type:"object",entity:[{name:"血量",type:"string"},{name:"是否NPC",type:"bool"},{name:"玩家等级",type:"slider",mix:1,max:10},{name:"年龄",type:"select",multiple:!0,options:["10","20"],option_url:"/api"},{name:"装备信息",type:"object",entity:[{name:"装备名称",type:"string"},{name:"是否满级",type:"bool"}]},{name:"背包数据",type:"array",item:{type:"string"}}]},{name:"玩家仓库",type:"array",item:{name:"仓库信息",type:"object",entity:[{name:"仓库名称",type:"string"},{name:"是否公开",type:"bool"}]}}]),g={},w=()=>{console.log(m.value);const l=b(m.value,g);console.log(l)},k={昵称:"张三",金手指:!0,金手指等级:10,性别:"男",玩家信息:{血量:"100",是否NPC:!0,玩家等级:4,年龄:"10",装备信息:{装备名称:"屠龙",是否满级:!0},背包数据:["屠龙刀","倚天剑"]},玩家仓库:[{仓库名称:"张三的仓库",是否公开:!0}]},d=(l,t)=>{l.map(e=>{e.id=t.i++}),l.map(e=>{e.entity!=null&&d(e.entity,t)})},V=l=>{l.entity==null&&(l.entity=[]);const t=JSON.parse(JSON.stringify(l.item));t.name=l.entity.length+1,l.entity.push(t),d(m.value,{i:0})};d(m.value,{i:0}),console.log(m.value);const b=(l,t)=>(l.map(e=>{if(e.value==null)switch(e.type){case"string":{e.value="";break}case"bool":{e.value=!1;break}case"slider":{e.value=e.min;break}case"select":{e.value=null;break}}if(e.entity){let o=null;e.type==="array"?o=[]:o={},t instanceof Array?t.push(o):t[e.name]=o,b(e.entity,o)}else t instanceof Array?t.push(e.value):t[e.name]=e.value}),t),_=(l,t)=>{l.map(e=>{if(e.type==="object"){const o=t[e.name];o!==void 0&&_(e.entity,o)}else if(e.type==="array"){const o=t[e.name];if(o!==void 0&&o.length>0){e.entity=[];const u={};o.map(f=>{const c=JSON.parse(JSON.stringify(e.item));c.name=e.entity.length+1,e.entity.push(c),u[c.name]=f}),_(e.entity,u)}}else e.value=t[e.name]})};return _(m.value,k),(l,t)=>{const e=B,o=D,u=O,f=T,c=F,E=L,x=A,N=J,v=P,C=I,S=q,U=$;return s(),h("div",R,[H("div",W,[p(o,{separator:"/"},{default:r(()=>[p(e,{to:{path:"./appList"}},{default:r(()=>[y("应用列表")]),_:1},8,["to"]),p(e,null,{default:r(()=>[y("应用配置")]),_:1})]),_:1})]),p(U,{style:{"margin-left":"20px",height:"95%"},"body-style":{height:"calc(100% - 40px)"}},{default:r(()=>[(s(),i(S,{key:2},{default:r(()=>[p(C,{data:m.value,style:{width:"100%"},"row-key":"id","tree-props":{children:"entity"},"show-header":!1},{default:r(()=>[p(u,{prop:"name",label:"",key:"1+"}),p(u,{label:"",key:"2+"},{default:r(a=>[a.row.type=="string"?(s(),i(f,{key:0,modelValue:a.row.value,"onUpdate:modelValue":n=>a.row.value=n},null,8,["modelValue","onUpdate:modelValue"])):a.row.type=="bool"?(s(),i(c,{key:1,modelValue:a.row.value,"onUpdate:modelValue":n=>a.row.value=n},null,8,["modelValue","onUpdate:modelValue"])):a.row.type=="slider"?(s(),i(E,{key:2,modelValue:a.row.value,"onUpdate:modelValue":n=>a.row.value=n,min:a.row.mix,max:a.row.max},null,8,["modelValue","onUpdate:modelValue","min","max"])):a.row.type=="select"?(s(),i(N,{key:3,modelValue:a.row.value,"onUpdate:modelValue":n=>a.row.value=n,class:"m-2",placeholder:""},{default:r(()=>[(s(!0),h(K,null,M(a.row.options,n=>(s(),i(x,{key:n,label:n,value:n},null,8,["label","value"]))),128))]),_:2},1032,["modelValue","onUpdate:modelValue"])):a.row.type=="array"?(s(),i(v,{key:4,onClick:n=>V(a.row)},{default:r(()=>[y("增加一条数据")]),_:2},1032,["onClick"])):Q("",!0)]),_:1})]),_:1},8,["data"])]),_:1}))]),_:1},8,["body-style"]),p(v,{onClick:w},{default:r(()=>[y("123")]),_:1})])}}});export{ke as default};
