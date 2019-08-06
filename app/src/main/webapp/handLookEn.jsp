<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>乐享动手表使用手册</title>
<%--<link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css" />--%>
	<style>
		body {
			margin: 0;
			padding: 0;
			background: #201F27;
			color: #F5F4F8;
			/*overflow-x: hidden;*/
		}
		p {
			margin: 0;
		}
		.container {
			padding: 0 8px;
			/*overflow-x: hidden;*/
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
				transform: translateX(0);
				-ms-transform: translateX(0);
				-webkit-transform: translateX(0);
				-moz-transform: translateX(0);
				-o-transform: translateX(0);
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
			transition: height 0.4s linear;
			-webkit-transition: height 0.4s linear;
			-moz-transition: height 0.4s linear;
			-o-transition: height 0.4s linear;
		}
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
			<h1 class="title">1.How do I pair my watch with an app?</h1>
			<div class="description">When you use the device for the first time, you must first pair it with your own smartphone. You can complete the mobile app download and Bluetooth pairing by scanning the QR code popped up on the watch, so that you can smoothly synchronize the motion data, message notification, and weather data with other settings. Features. Synchronization steps:
				<p class="textIndent">1 Press and hold the LIGHT button to turn on the watch, </p>
				<p class="textIndent">2 Download and install the music app, register to enter the device list page, select the watch, </p>
				<p class="textIndent">3 click the watch to enter the scan code page, and scan the QR code on the watch to make the phone Smart watch connection</p>
			</div>
		</div>
		<div class="item">
			<h1 class="title">2.Why do I need to set up my profile?</h1>
			<p class="description">Before you start exercising, please enter your gender, age, height, weight, and resting heart rate into your watch. The watch will use this information to calculate the correct training data.</p>
		</div>
		<div class="item">
			<h1 class="title">3.How to untie?</h1>
			<p class="description">After the watch is bound to the app, go to the watch homepage and click on Set-Unbind to unbind the watch from the app.</p>
		</div>
		<div class="item">
			<h1 class="title">4.How to upgrade the watch firmware?</h1>
			<p class="description"> After the watch is connected to the APP, when the watch has new firmware, the APP home page will pop up the firmware update prompt “Watch firmware update”, click “Install Now”, then the page will jump to the watch version page, click the “Download and Install” button. Just wait for the watch to restart and complete the update.</p>
		</div>
		<div class="item">
			<h1 class="title">5.How to update sports data?</h1>
			<p class="description">After the watch is connected with the APP, when there is new sports data on the watch, it will be automatically uploaded to the background through the APP, or click the “Sync” button on the watch homepage to synchronize the data.</p>
		</div>
		<div class="item">
			<h1 class="title">6.How to change the dial?</h1>
			<div class="description">
				<p>
			First, you can open the APP to connect the watch, enter the watch details page, click the corresponding theme dial to switch the dial operation.
		</p>
			<p>Second, you can press the up button for 2 seconds on the watch to enter the gadget menu, then select the dial to switch the dial. </p>
			<p>Third, you can enter the watch home page, then long press the start button for three seconds, enter the shortcut settings dial page, then click up or down to switch the dial, select the completion and click start, the switch is successful.</p>
			</div>
		</div>
		<div class="item">
			<h1 class="title">7.Trajectory optimization scheme？</h1>
			<p class="description">After you turn on your watch for the first time, it is recommended to connect the app, automatically synchronize the ephemeris, and have a normal connection app before running every week to ensure that your watch's ephemeris data is up to date. The trajectory is within the normal range, otherwise it may cause the trajectory to shift.</p>
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