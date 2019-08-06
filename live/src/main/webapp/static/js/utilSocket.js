//var json = {};//发送者json对象
//var map = null;//地图对象
//
//
///****
// * 建立webSocket连接
// */
//function startWebSocket() {
//	console.log("startWebSocket()");
//	// 收到服务器发送的文本消息, event.data表示文本内容
//	window.liveSocket.socket.onmessage = function(evt) { //在这里根据接受到的json串刷新页面
//		try {
//			var messageJson = eval("(" + evt.data + ")"); //解析json串
//			if (messageJson.sna == live.SNA_S && messageJson.msg == live.MSG_C) { // 服务器关闭
//				//$("body").append(live.LIVE_END_ALERT_MSG);
//			}
//			if (messageJson.sna != "_s_") {
//				news(messageJson);
//			}
//
//		} catch (e) {}
//
//	};
//	// 关闭WebSocket的回调
//	window.liveSocket.socket.onclose = function(evt) {
//		//$("body").append(live.LIVE_END_ALERT_MSG);
//	};
//}
///***
// * 发送消息到websocke
// * @param msg	消息 (特殊字符 : _c_(断开服务器) _j_(激励音乐) _b_(鞭打声) _v_(live voice 直播语音,语音地址 fu)) 或者文本内容
// * @param na	发送者的用户名称
// */
//
//function sendWebSocket(msg, na) {
//	json["msg"] = msg;
//	json["na"] = na;
//	/* json["ui"] = live.prototype.ui;*/
//	console.log("发送消息到websocke : " + JSON.stringify(json));
//	liveSocket.socket.send(JSON.stringify(json)); //向服务器发送信息
//}
///***
// * 接受消息处理
// * @param json
// */
//
//function news(json) {
//
//	//音乐信息
//	if ((typeof(json.mn) != "undefined")) { //歌曲名称
//		$(".mixName").text(json.mn);
//	}
//	if ((typeof(json.mu) != "undefined")) { //歌曲地址
//		$(".fitmix").attr("src",json.mu);
//	}
//	if ((typeof(json.mau) != "undefined")) { //歌曲图片地址
//		//$("#audioImg").append('<img src="' + json.mau + '" width="100%" height="100%" />');
//		$(".mixImg").attr("src",json.mau);
//	}
//	//地图定位
//	if ((typeof(json.lng) == "number" && typeof(json.lat) == "number") && typeof(map) == "object") { //经度
//		//map.setZoom(mapZoom); //设置缩放比例
//		drawRoute(json.lng, json.lat);
//	}
//	//用户跑步信息
//	if ((typeof(json.dst) != "undefined")) {
//		$("#juli").text(json.dst + " 公里");
//	}
//	if ((typeof(json.rt) != "undefined")) {
////		var mm = json.rt % 60;
////		$("#shichang").text("时长:" + (json.rt - mm) / 60 + "\'" + mm + "\"");
//	}
//	if ((typeof(json.bpm) != "undefined")) {
//		$("#bpm").text("bpm:" + json.bpm);
//	}
//	if ((typeof(json.ps) != "undefined")) {
//		var mmkm = json.ps % 60;
//		if (json.ps == 2147483647) {
//			$("#peisu").text("0\'00\"");
//		} else {
//			$("#peisu").text((json.ps - mmkm) / 60 + "'" + mmkm + "\"");
//		}
//	}
//	//online
//	if ((typeof(json.onl) != "undefined")) {
//		$(".amap-copyright").text("在线:"+json.onl+"人正在观看此直播");
//	}
//
//	if ((typeof(json.msg) != "undefined")) {
//		if (json.msg == live.MSG_J || json.msg == live.MSG_B) { //声效激励
////			var id = json.msg == live.MSG_J ? "feature2" : "feature3";
////			zhiXingSound(id);
//		} else { //文本信息
//			if (json.msg.length > 0) {
//				addTangmu(json.msg);
//			}
//		}
//	}
//}