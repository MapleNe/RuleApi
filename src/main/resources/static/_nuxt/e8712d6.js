(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{454:function(e,t,o){"use strict";o.r(t);var l={layout:"layout",head:function(){return{title:"CR云控"}},data:function(){return{key:"",form:{cloudUid:"",cloudUrl:""}}},beforeDestroy:function(){},created:function(){},mounted:function(){var e=this;localStorage.getItem("webkey")?e.key=localStorage.getItem("webkey"):(e.$message({message:"认证失效！",type:"error"}),e.$router.push({path:"/"})),e.getConfig()},methods:{save:function(){var e=this,t=e.form,data={webkey:e.key,params:JSON.stringify(t)},o=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"});e.$axios.$post(e.$api.apiConfigUpdate(),this.qs.stringify(data)).then((function(t){o.close(),1==t.code?(e.$message({message:t.msg,type:"success"}),e.getConfig()):e.$message({message:t.msg,type:"error"})})).catch((function(t){o.close(),console.log(t),e.$message({message:"接口请求异常，请检查网络！",type:"error"})}))},getConfig:function(){var e=this,data={webkey:e.key};e.$axios.$post(e.$api.getApiConfig(),this.qs.stringify(data)).then((function(t){1==t.code&&(e.form.cloudUid=t.data.cloudUid,e.form.cloudUrl=t.data.cloudUrl)})).catch((function(t){console.log(t),e.$message({message:"接口请求异常，请检查网络！",type:"error"})}))}}},r=o(26),component=Object(r.a)(l,(function(){var e=this,t=e._self._c;return t("div",{staticClass:"page-container"},[t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:24}},[t("div",{staticClass:"data-box"},[t("div",{staticClass:"page-title"},[t("h4",[e._v("CR云控")]),e._v(" "),t("p",[e._v("CR应用云控平台为群友二开平台，可以通过第三方平台实时管控APP的各项配置，而无需重新打包。云控版本可以提升各方便便捷性，并在云控平台的支持下增加各种DIY功能，但可能导致小程序和应用商城上架受到影响，可以根据需求自行选择。")])]),e._v(" "),t("div",{staticClass:"page-form"},[t("el-form",{ref:"form",attrs:{model:e.form,"label-position":"top","label-width":"80px"}},[t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("云控UID")]),e._v(" "),t("el-input",{attrs:{placeholder:"请输入云控UID"},model:{value:e.form.cloudUid,callback:function(t){e.$set(e.form,"cloudUid",t)},expression:"form.cloudUid"}})],1),e._v(" "),t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("云控Url")]),e._v(" "),t("el-input",{attrs:{placeholder:"请输入云控Url"},model:{value:e.form.cloudUrl,callback:function(t){e.$set(e.form,"cloudUrl",t)},expression:"form.cloudUrl"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.save()}}},[e._v("保存设置")])],1)],1)],1)])])],1)],1)}),[],!1,null,null,null);t.default=component.exports}}]);