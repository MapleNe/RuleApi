(window.webpackJsonp=window.webpackJsonp||[]).push([[4],{451:function(e,t,o){"use strict";o.r(t);var l={layout:"layout",head:function(){return{title:"QQ配置"}},data:function(){return{key:"",form:{qqAppletsAppid:"",qqAppletsSecret:""}}},beforeDestroy:function(){},created:function(){},mounted:function(){var e=this;localStorage.getItem("webkey")?e.key=localStorage.getItem("webkey"):(e.$message({message:"认证失效！",type:"error"}),e.$router.push({path:"/"})),e.getConfig()},methods:{save:function(){var e=this,t=e.form,data={webkey:e.key,params:JSON.stringify(t)},o=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"});e.$axios.$post(e.$api.apiConfigUpdate(),this.qs.stringify(data)).then((function(t){o.close(),1==t.code?(e.$message({message:t.msg,type:"success"}),e.getConfig()):e.$message({message:t.msg,type:"error"})})).catch((function(t){o.close(),console.log(t),e.$message({message:"接口请求异常，请检查网络！",type:"error"})}))},getConfig:function(){var e=this,data={webkey:e.key};e.$axios.$post(e.$api.getApiConfig(),this.qs.stringify(data)).then((function(t){1==t.code&&(e.form.qqAppletsAppid=t.data.qqAppletsAppid,e.form.qqAppletsSecret=t.data.qqAppletsSecret)})).catch((function(t){console.log(t),e.$message({message:"接口请求异常，请检查网络！",type:"error"})}))}}},r=o(26),component=Object(r.a)(l,(function(){var e=this,t=e._self._c;return t("div",{staticClass:"page-container"},[t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:24}},[t("div",{staticClass:"data-box"},[t("div",{staticClass:"page-title"},[t("h4",[e._v("QQ配置")]),e._v(" "),t("p",[e._v("在这里配置QQ登录和小程序相关信息。")])]),e._v(" "),t("div",{staticClass:"page-form"},[t("el-form",{ref:"form",attrs:{model:e.form,"label-position":"top","label-width":"80px"}},[t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("QQ小程序APPID"),t("span",[e._v("可不填，负责QQ小程序登录")])]),e._v(" "),t("el-input",{attrs:{placeholder:"请输入qqAppletsAppid"},model:{value:e.form.qqAppletsAppid,callback:function(t){e.$set(e.form,"qqAppletsAppid",t)},expression:"form.qqAppletsAppid"}})],1),e._v(" "),t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("QQ小程序Secret"),t("span",[e._v("可不填，负责QQ小程序登录")])]),e._v(" "),t("el-input",{attrs:{placeholder:"请输入qqAppletsSecret"},model:{value:e.form.qqAppletsSecret,callback:function(t){e.$set(e.form,"qqAppletsSecret",t)},expression:"form.qqAppletsSecret"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.save()}}},[e._v("保存设置")])],1)],1)],1)])])],1)],1)}),[],!1,null,null,null);t.default=component.exports}}]);