(window.webpackJsonp=window.webpackJsonp||[]).push([[15],{461:function(e,t,l){"use strict";l.r(t);var o={layout:"layout",head:function(){return{title:"支付宝配置"}},data:function(){return{key:"",form:{alipayAppId:"",alipayPrivateKey:"",alipayPublicKey:"",alipayNotifyUrl:""}}},beforeDestroy:function(){},created:function(){},mounted:function(){var e=this;localStorage.getItem("webkey")?e.key=localStorage.getItem("webkey"):(e.$message({message:"认证失效！",type:"error"}),e.$router.push({path:"/"})),e.getConfig()},methods:{save:function(){var e=this,t=e.form,data={webkey:e.key,params:JSON.stringify(t)},l=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"});e.$axios.$post(e.$api.apiConfigUpdate(),this.qs.stringify(data)).then((function(t){l.close(),1==t.code?(e.$message({message:t.msg,type:"success"}),e.getConfig()):e.$message({message:t.msg,type:"error"})})).catch((function(t){l.close(),console.log(t),e.$message({message:"接口请求异常，请检查网络！",type:"error"})}))},getConfig:function(){var e=this,data={webkey:e.key};e.$axios.$post(e.$api.getApiConfig(),this.qs.stringify(data)).then((function(t){1==t.code&&(e.form.alipayAppId=t.data.alipayAppId,e.form.alipayPrivateKey=t.data.alipayPrivateKey,e.form.alipayPublicKey=t.data.alipayPublicKey,e.form.alipayNotifyUrl=t.data.alipayNotifyUrl)})).catch((function(t){console.log(t),e.$message({message:"接口请求异常，请检查网络！",type:"error"})}))}}},r=l(26),component=Object(r.a)(o,(function(){var e=this,t=e._self._c;return t("div",{staticClass:"page-container"},[t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{span:24}},[t("div",{staticClass:"data-box"},[t("div",{staticClass:"page-title"},[t("h4",[e._v("支付宝配置")]),e._v(" "),t("p",[e._v("支付宝当面付是个人可用的在线支付接口，在这里配置支付宝当面付的基本信息，"),t("a",{attrs:{href:"https://opendocs.alipay.com/open/01csp3?ref=api",target:"_blank"}},[e._v("官方文档")])])]),e._v(" "),t("div",{staticClass:"page-form"},[t("el-form",{ref:"form",attrs:{model:e.form,"label-position":"top","label-width":"80px"}},[t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("APP ID")]),e._v(" "),t("el-input",{attrs:{placeholder:"请输入APP ID"},model:{value:e.form.alipayAppId,callback:function(t){e.$set(e.form,"alipayAppId",t)},expression:"form.alipayAppId"}})],1),e._v(" "),t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("应用私钥"),t("span",[e._v("请不要存在换行")])]),e._v(" "),t("el-input",{attrs:{type:"textarea",placeholder:"请输入应用私钥"},model:{value:e.form.alipayPrivateKey,callback:function(t){e.$set(e.form,"alipayPrivateKey",t)},expression:"form.alipayPrivateKey"}})],1),e._v(" "),t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("支付宝公钥"),t("span",[e._v("不等于应用公钥，请不要存在换行")])]),e._v(" "),t("el-input",{attrs:{type:"textarea",placeholder:"请输入支付宝公钥"},model:{value:e.form.alipayPublicKey,callback:function(t){e.$set(e.form,"alipayPublicKey",t)},expression:"form.alipayPublicKey"}})],1),e._v(" "),t("el-form-item",[t("p",{staticClass:"form-label",attrs:{slot:"label"},slot:"label"},[e._v("回调地址"),t("span",[e._v("范例：http://127.0.0.1:8081/pay/notify 请将127.0.0.1:8081部分替换为自己的API域名，需要注意区分https和http")])]),e._v(" "),t("el-input",{attrs:{placeholder:"请输入回调地址"},model:{value:e.form.alipayNotifyUrl,callback:function(t){e.$set(e.form,"alipayNotifyUrl",t)},expression:"form.alipayNotifyUrl"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.save()}}},[e._v("保存设置")])],1)],1)],1)])])],1)],1)}),[],!1,null,null,null);t.default=component.exports}}]);