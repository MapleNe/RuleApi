(window.webpackJsonp=window.webpackJsonp||[]).push([[10],{457:function(e,t,o){"use strict";o.r(t);var l={layout:"layout",head:function(){return{title:"Mysql设置"}},data:function(){return{key:"",form:{dataUrl:"",dataUsername:"",dataPassword:"",dataPrefix:""}}},beforeDestroy:function(){},created:function(){},mounted:function(){var e=this;localStorage.getItem("webkey")?e.key=localStorage.getItem("webkey"):(e.$message({message:"认证失效！",type:"error"}),e.$router.push({path:"/"})),e.getConfig()},methods:{save:function(){var e=this,t=e.form,data={webkey:e.key,params:JSON.stringify(t)},o=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"});e.$axios.$post(e.$api.setupMysql(),this.qs.stringify(data)).then((function(t){o.close(),1==t.code?(e.$message({message:t.msg,type:"success"}),e.getConfig()):e.$message({message:t.msg,type:"error"})})).catch((function(t){o.close(),console.log(t),e.$message({message:"接口请求异常，请检查网络！",type:"error"})}))},getConfig:function(){var e=this,data={webkey:e.key};e.$axios.$post(e.$api.allConfig(),this.qs.stringify(data)).then((function(t){1==t.code&&(e.form.dataUrl=t.data.dataUrl,e.form.dataUsername=t.data.dataUsername,e.form.dataPassword=t.data.dataPassword,e.form.dataPrefix=t.data.dataPrefix)})).catch((function(t){console.log(t),e.$message({message:"接口请求异常，请检查网络！",type:"error"})}))}}},r=o(26),component=Object(r.a)(l,(function(){var e=this,t=e._self._c;return t("div",{staticClass:"page-container"},[t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:24}},[t("div",{staticClass:"data-box"},[t("div",{staticClass:"page-title"},[t("h4",[e._v("Mysql设置")]),e._v(" "),t("p",[e._v("设置Mysql链接和基本信息，修改后需重启接口生效。")])]),e._v(" "),t("div",{staticClass:"page-form"},[t("el-form",{ref:"form",attrs:{model:e.form,"label-position":"top","label-width":"80px"}},[t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("Mysql链接串"),t("span",[e._v("同时包括了地址，端口和数据库名，请注意修改")])]),e._v(" "),t("el-input",{attrs:{placeholder:"请输入Mysql链接串"},model:{value:e.form.dataUrl,callback:function(t){e.$set(e.form,"dataUrl",t)},expression:"form.dataUrl"}})],1),e._v(" "),t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("Mysql用户名")]),e._v(" "),t("el-input",{attrs:{placeholder:"请输入Mysql用户名"},model:{value:e.form.dataUsername,callback:function(t){e.$set(e.form,"dataUsername",t)},expression:"form.dataUsername"}})],1),e._v(" "),t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("Mysql密码")]),e._v(" "),t("el-input",{attrs:{placeholder:"请输入Mysql密码"},model:{value:e.form.dataPassword,callback:function(t){e.$set(e.form,"dataPassword",t)},expression:"form.dataPassword"}})],1),e._v(" "),t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("数据表前缀"),t("span",[e._v("数据表前缀，默认就是typecho")])]),e._v(" "),t("el-input",{attrs:{placeholder:"请输入数据表前缀"},model:{value:e.form.dataPrefix,callback:function(t){e.$set(e.form,"dataPrefix",t)},expression:"form.dataPrefix"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.save()}}},[e._v("保存设置")])],1)],1)],1)])])],1)],1)}),[],!1,null,null,null);t.default=component.exports}}]);