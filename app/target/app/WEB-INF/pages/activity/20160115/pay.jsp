<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<!DOCTYPE html>
<html>
  <head>
	  <title>感谢一路有你 微信支付</title>

	  <!-- 禁止缩放 -->
	  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <style>
		  body{
			  /*margin: 0px 15px 20px 15px;*/
			  /*text-align: center;*/
			  margin: 0px auto;;
			  max-width: 500px;
			  text-align: center;
		  }

		  .btn {
			  min-width: 60px;
			  display: inline-block;
			  overflow: visible;
			  padding: 0 22px;
			  height: 30px;
			  line-height: 30px;
			  vertical-align: middle;
			  text-align: center;
			  text-decoration: none;
			  border-radius: 3px;
			  -moz-border-radius: 3px;
			  -webkit-border-radius: 3px;
			  font-size: 14px;
			  border-width: 1px;
			  border-style: solid;
			  cursor: pointer;
		  }

		  .btn_primary {
			  background-color: #44b549;
			  background-image: -moz-linear-gradient(top,#44b549 0,#44b549 100%);
			  background-image: -webkit-gradient(linear,0 0,0 100%,from(#44b549),to(#44b549));
			  background-image: -webkit-linear-gradient(top,#44b549 0,#44b549 100%);
			  background-image: -o-linear-gradient(top,#44b549 0,#44b549 100%);
			  background-image: linear-gradient(to bottom,#44b549 0,#44b549 100%);
			  border-color: #44b549;
			  color: #fff;
		  }
	  </style>
  </head>
  
  <body>
	  <div style="text-align: center;position: fixed;top: 10%;left: 0px;right: 0px;">
		  正在支付...
	  </div>

	  <div style="position: fixed;top: 60%;left: 0px;right: 0px;">
		  <button type="button" class="btn btn_primary"  onclick="wxPay()">微信支付</button>
	  </div>
  </body>

  <script type="text/javascript">
	  // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
	  var useragent = navigator.userAgent;
	  if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
		  // 这里警告框会阻塞当前页面继续加载
//        alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
		  $("body").html('<label style="color: #60d295;font-size: 50px"> :-)</label> &nbsp;&nbsp;请用微信打开.');
		  // 以下代码是用javascript强行关闭当前页面
//        var opened = window.open('about:blank', '_self');
//        opened.opener = null;
//        opened.close();
	  }
  </script>

  <script type="text/javascript">

	  function wxPay() {
		  WeixinJSBridge.invoke('getBrandWCPayRequest',{
			  "appId" : "${appId}",
			  "timeStamp" : "${timeStamp}",
			  "nonceStr" : "${nonceStr}",
			  "package" : "${packageValue}",
			  "signType" : "MD5",
			  "paySign" : "${sign}"
		  },function(res){
			  if(res.err_msg == "get_brand_wcpay_request:ok"){
//						alert("微信支付成功!");
				  location.href = "/activity/20160115/success.htm?id=${id}";
			  }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
//				  alert("用户取消支付!");
			  }else{
				  alert("支付失败!");
			  }
		  })
	  }

	  window.onload = function(){
		  window.setTimeout(function(){
			  wxPay();
		  }, 500);
	  };

	  javascript:window.history.forward(1);
  </script>

</html>
