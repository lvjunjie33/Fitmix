<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>乐享动手表使用手册</title>
<%--<link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css" />--%>
	<style>
		body {
			margin: 0;
			padding: 0;
			background: #201F27;
			color: #F5F4F8;
		}
		p {
			margin: 0;
		}
		.container {
			padding: 0 8px;
		}

		@keyframes rightToleft {
			/*from {*/
			/*transform: translateX(103%);*/
			/*-ms-transform: translateX(103%);*/
			/*-webkit-transform: translateX(103%);*/
			/*-moz-transform: translateX(103%);*/
			/*-o-transform: translateX(103%);*/
			/*}*/
			to {
				-webkit-transform: translateX(0);
				-moz-transform: translateX(0);
				-ms-transform: translateX(0);
				-o-transform: translateX(0);
				transform: translateX(0);
			}
		}
		.item {
			position: relative;
			padding: 10px 0;
			border-bottom: 1px solid #3F3E46;
			overflow-x: hidden;
		}



		.title {
			font-size: 16px;
			margin: 0;
			font-weight: normal;
			transform: translateX(103%);
			transition: transform .5s linear;
		}
		.description {
			display: none;
			font-size: 16px;
			color: #7F7E86;
			margin: 0;
			overflow: hidden;
			-webkit-transition: height 0.4s ease-out;
			-moz-transition: height 0.4s ease-out;
			-o-transition: height 0.4s ease-out;
			transition: height 0.4s ease-out;
		}
		/*.description {*/
			/*display: none;*/
			/*font-size: 16px;*/
			/*color: #7F7E86;*/
			/*margin: 0;*/
			/*overflow: hidden;*/
		/*}*/
		.textIndent {
			text-indent: 28px;
			padding: 1px 0;
		}

		.test {
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translateX(-50%) translateY(-50%);
			background-color: rgba(55,55,55,1);
			padding: 10px 15px;
			border-radius: 5px;
			display: none;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="item">
			<h1 class="title">1、如何将手表与APP配对？</h1>
			<div class="description">初次使用该设备时必须先与自己的智能手机配对，通过扫描手表弹出的二维码可完成手机App下载以及蓝牙配对，从而可以顺利同步运动数据，消息通知，及天气数据和其他设置同步功能。同步步骤：
				<p class="textIndent">①长安LIGHT键开机，</p>
				<p class="textIndent">②下载并安装乐享动App,注册登录后进入设备列表页，选择手表，</p>
				<p class="textIndent">③点击手表进入扫码页，通过扫描手表上的二维码，使手机与智能手表连接</p>
			</div>
		</div>
		<div class="item">
			<h1 class="title">2、为何需要设置个人资料？</h1>
			<p class="description">当你开始运动之前，请先将你的性别、年龄、身高、体重、静息心率输入到手表内，手表将使用这些信息来计算出正确的训练数据。</p>
		</div>
		<div class="item">
			<h1 class="title">3、如何解绑？</h1>
			<p class="description">手表与APP绑定后，进入手表主页，点击设置-解除绑定来解绑手表与APP的连接。</p>
		</div>
		<div class="item">
			<h1 class="title">4、如何升级手表固件？</h1>
			<p class="description">手表与APP连接后，当手表有新固件时，APP首页会弹出固件更新提示“手表固件更新”，点击“现在安装”，则页面跳转至手表版本页，点击“下载并安装”按钮即可，静待手表重启完成更新。</p>
		</div>
		<div class="item">
			<h1 class="title">5、如何更新运动数据？</h1>
			<p class="description">手表与APP连接后，当手表上有新的运动数据时，会通过APP自动上传至后台，或者点击手表主页“同步”按钮，进行数据同步。</p>
		</div>
		<div class="item">
			<h1 class="title">6、如何更换表盘？</h1>
			<div class="description">
				一、可以打开APP连接手表，进入手表详情页，点击相应的主题表盘，进行切换表盘操作。<br /><br />
				二、可以在手表上长按up键2秒钟进入小工具菜单，然后选择表盘，进行表盘切换。<br /><br />
				三、可以进入手表主页，然后长按start键三秒，进入快捷设置表盘页面，然后点击up或者down来切换表盘，选择完成后点击start，切换成功。
			</div>
		</div>
		<div class="item">
			<h1 class="title">7、轨迹优化方案？</h1>
			<p class="description">您在第一次启动手表后，建议连接APP，进行自动同步星历，并能够每周跑步前都有一次正常连接APP，来保证同步您的手表星历数据为最新，这样才能保证跑步轨迹在正常范围内，否则可能导致轨迹的偏移。</p>
		</div>

		<div class="test" id="test">
			我是测试的
		</div>
	</div>

	<script src="https://cdn.bootcss.com/fastclick/1.0.6/fastclick.js"></script>
	<script>
		FastClick.attach(document.body); // 解决ios点击300ms延迟问题
		function show(str) {
			str && (document.getElementById('test').innerText = str);
			document.getElementById('test').style.display = 'block';
			setTimeout(function () {
				document.getElementById('test').style.display = 'none';
			}, 1000)
		}
		function getHeight(node) {
			// 一次设置完可以减少重绘和重排
			node.style.cssText = '; visibility: hidden; display: block;';
			var _h = getComputedStyle(node, false)['height'];
			node.style.cssText = '; visibility: unset; display: none;';
			return _h;
		}
		var current;
		var aDiv = document.getElementsByClassName('item');
		[].forEach.call(aDiv, function (item) {
			item.onclick = function () {
				var description = this.children[1];
				if(current) {
					current.style.height = '0';
					if(current == description) {
						current = false;
						return;
					}
				}
				var _h;
				if(description.cusHeight) {
					_h = description.cusHeight;
				} else {
					_h = getHeight(description);
				}
				description.style.cssText = '; display: block; height: 0;';
				description.offsetWidth; // 马上重排
				description.style.height = _h;
				description.cusHeight = _h; // 把高度存起来， 下次就不用在计算了， 优化
				current = description;
			}
		});

		var aTiltle = document.getElementsByClassName('title');

		[].forEach.call(aTiltle, function (item, index, arr) {

//			var second = (index + 1) * 0.16 + 's';
//			item.style.animation = 'rightToleft linear ' + second;

			setTimeout(function () {
//				item.style.animation = 'rightToleft linear 0.16s forwards';
				item.style.transform = 'translateX(0)';
			}, 167 * (index + 1))
		})
	</script>
</body>
</html>