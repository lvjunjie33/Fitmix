/**
 * Created by wjcaozhi1314 on 2015/12/17.
 */
/***
 * 定义一些变量
 * @type {null}
 */
var map = null;//地图对象
var yuYinPd = false;//判断是否正在录制语音  -- true 没有录制语音 false 正在录制语音
var shenxiao = true;
var userOpenId = GetRandomNum(1, (10000 * 10000000)).toString();
var number = 0;//记录未读消息条数
var shenxiaoArray = ["static/music/gz+.mp3", "static/music/gj.mp3", "static/music/lh.mp3", "static/music/dianji.mp3"];
var hudongB = true;
var transformCssStr = "";

/**
 * 终端判断*/
var browser = {
    versions: function () {
        var u = navigator.userAgent, app = navigator.appVersion;
        return { // 移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, // IE内核
            presto: u.indexOf('Presto') > -1, // opera内核
            webKit: u.indexOf('AppleWebKit') > -1, // 苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, // 火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), // 是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), // ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, // android终端或uc浏览器
            iPhone: u.indexOf('iPhone') > -1, // 是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, // 是否iPad
            webApp: u.indexOf('Safari') == -1
            // 是否web应该程序，没有头部与底部
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
}
/***
 * 生成一定方位之类的随机数
 * @param Min
 * @param Max
 * @returns {*}
 * @constructor
 */
function GetRandomNum(Min, Max) {
    var Range = Max - Min;
    var Rand = Math.random();
    return (Min + Math.round(Rand * Range));
}
/***
 * 日志工具
 * @type {{info: looger.info}}
 */
var looger = {
    info: function (text) {
        if (is_weixn()) {
            //alert(text);
        } else {
            console.log(text);
        }
    }
}
/***
 * 判断是否微信客户端
 * @returns {boolean}
 */
function is_weixn() {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == "micromessenger") {
        return true;
    } else {
        return false;
    }
}
/***
 * 控制元素隐藏和显示
 * @param id
 */
function hiddenOrShow(id) {
    looger.info((!$(".recording").is(":hidden")));
    looger.info((!$(id).is(":hidden")));
    if (!$(".recording").is(":hidden") && !$(id).is(":hidden")) {
        hudongB = false;
    } else {
        hudongB = true;
    }
    if (hudongB) {
        $(id).slideToggle("slow");
    }

}
/***
 * socket发送json
 * @param socket
 * @param json
 */
function faSocket(socket, json) {
    json["userOpenId"] = userOpenId;//自动一openid
    looger.info("faSocket : \n " + JSON.stringify(json))
    try {
        socket.send(JSON.stringify(json));
    } catch (e) {
        looger.info("socket发送错误:" + e);
    }
}
/***
 * click事件总入口
 * @param v
 */
function myClick() {

    //创建并初始化socketJson
    var socketJson = {};
    socketJson["na"] = $("#na").text();

    if (is_weixn()) {
        socketJson["nickname"] = live.prototype.wx_userInfo.nickname;
        socketJson["headimgurl"] = live.prototype.wx_userInfo.headimgurl;
    }

    switch ($(this).attr("data-cv")) {
        case "jili":
            hiddenOrShow("#hudong");
            break;
        case "_gz_"://声效 鼓掌
            hiddenOrShow("#hudong");
            socketJson["msg"] = "_gz_";
            socketJson["msgtype"] = 1;
            if (!shenxiao) {//声效已经存在不发送socket消息
                error($(".myAlert"), {zIndex: 10000}, "已经有人抢在你前面了... ...");
            } else {
                faSocket(liveSocketObj, socketJson);//发送socket
            }
            break;
        case "_dianji_"://声效 电击
            hiddenOrShow("#hudong");
            socketJson["msg"] = "_dianji_";
            socketJson["msgtype"] = 1;
            if (!shenxiao) {//声效已经存在不发送socket消息
                error($(".myAlert"), {zIndex: 10000}, "已经有人抢在你前面了... ...");
            } else {
                faSocket(liveSocketObj, socketJson);//发送socket
            }
            break;
        case "_gj_"://声效 狗叫
            hiddenOrShow("#hudong");
            socketJson["msg"] = "_gj_";
            socketJson["msgtype"] = 1;
            if (!shenxiao) {//声效已经存在不发送socket消息
                error($(".myAlert"), {zIndex: 10000}, "已经有人抢在你前面了... ...");
            } else {
                faSocket(liveSocketObj, socketJson);//发送socket
            }
            break;
        case "_lh_"://声效 礼花
            hiddenOrShow("#hudong");
            socketJson["msg"] = "_lh_";
            socketJson["msgtype"] = 1;
            if (!shenxiao) {//声效已经存在不发送socket消息
                    error($(".myAlert"), {zIndex: 10000}, "已经有人抢在你前面了... ...");
            } else {
                faSocket(liveSocketObj, socketJson);//发送socket
            }
            break;
        case "downloadApp":
            hiddenOrShow("#hudong");
            location.href = "#";//取到app下载地址
            break;
        case "liuYanText"://文本
            //发送留言socket
            socketJson["msg"] = $("#" + $(this).attr("data-cv")).val();
            socketJson["msgtype"] = 2;
            if (socketJson["msg"] != ""  &&
                (socketJson["msg"].length > 0 && socketJson["msg"].length < 50)) {
                faSocket(liveSocketObj, socketJson);//发送socket
            }
            $("#" + $(this).attr("data-cv")).val("");
            break;
        case "_jl_":
            looger.info("聊天记录" + $(".recording").is(":hidden") + "  number:" + number);
            if (!$(".recording").is(":hidden")) {
                $("#hudong").slideUp();
                //进行隐藏动画
                $(".recording").animate({
                    bottom: "-180px"
                }, 1000, function () {
                    $(".recording").hide();
                });
            } else {
                $("#hudong").slideDown();
                //hiddenOrShow("#hudong");

                $(".recording").show();
                //进行显示动画
                $(".recording").animate({
                    bottom: "88px"
                }, 1000, function () {
                    number = 0;
                    $(".liaotianNumber").text(number);
                });
            }
            break;
        case "guanbi":
            $(".myAlert").css("zIndex", "-1");
            setTimeout('$(".myAlert").css("zIndex", "1000");',1000 * 30);
            break;
        case "fitmix":
            downloadApp();
            break;
        case "_yy_":
            if(!is_weixn()){
                error($(".myAlert"), {zIndex: 10000}, "请在微信中使用语音聊天功能");
                return false;
            }
            break;
        default :
            break;
    }
}
/***
 * 触摸
 */
function myTouchstart() {
//创建并初始化socketJson
    var socketJson = {};
    socketJson["na"] = $("#na").text();
    if (is_weixn()) {
        socketJson["nickname"] = live.prototype.wx_userInfo.nickname;
        socketJson["headimgurl"] = live.prototype.wx_userInfo.headimgurl;
    }

    switch ($(this).attr("data-cv")) {
        case "_yy_"://语音开始
            looger.info("语音开始");
            wx_yuYinClick();
            break;
        default:
            break;
    }
}
/***
 * 触摸结束
 */
function myTouchend() {

    //创建并初始化socketJson
    var socketJson = {};
    socketJson["na"] = $("#na").text();
    if (is_weixn()) {
        socketJson["nickname"] = live.prototype.wx_userInfo.nickname;
        socketJson["headimgurl"] = live.prototype.wx_userInfo.headimgurl;
    }

    switch ($(this).attr("data-cv")) {
        case "_yy_"://语音结束
            looger.info("语音结束");
            break;
        default:
            break;
    }
}

/***
 * 根据按键值处理事件
 * @param event
 */
function inputMyKeydown(event) {

    var e = event || window.event || arguments.callee.caller.arguments[0];

    looger.info(e.keyCode);

    if (e.keyCode == 13) {
        var socketJson = {};
        socketJson["na"] = $("#na").text();
        if (is_weixn()) {
            socketJson["nickname"] = live.prototype.wx_userInfo.nickname;
            socketJson["headimgurl"] = live.prototype.wx_userInfo.headimgurl;
        }
        socketJson["msg"] = $("#liuYanText").val();
        socketJson["msgtype"] = 2;
        if (socketJson["msg"] != "" ||
            socketJson["msg"].length > 0) {
            faSocket(liveSocketObj, socketJson);//发送socket
        }
        $("#liuYanText").val("");
    }

}
/***
 * socket接受消息处理
 * @param msg
 */
function mySocketMessage(msg){
    var messageJson = eval("(" + msg.data + ")"); //解析json串
    looger.info("liveSocket message : " + JSON.stringify(messageJson));


    //地图定位
    if ((typeof(messageJson.lng) == "number" && typeof(messageJson.lat) == "number") && typeof(map) == "object") { //经度
        //map.setZoom(mapZoom); //设置缩放比例
        drawRoute(messageJson.lng, messageJson.lat);
    }
    //用户跑步信息
    if ((typeof(messageJson.dst) != "undefined")) {//公里
        //var gonglishu = json.dst / 1000;

        var numStr = (messageJson.dst / 1000).toString();
        if (numStr.indexOf(".") > -1) {
            var index = numStr.indexOf(".");
            var maxLength = numStr.length - 1;
            if ((maxLength - index) > 2) {
                numStr = numStr.substring(0, (index + 3));
            }
        }
        looger.info("公里：" + numStr);
        $(".userGL ").text("公里:" + numStr);
    }
    if ((typeof(messageJson.rt) != "undefined")) {//时长
        var mm = messageJson.rt % 60;
        looger.info("时长:" + ((messageJson.rt - mm) / 60));
        $(".userTIme").text("时长:" + (messageJson.rt - mm) / 60 + "\'" + mm + "\"");
    }
    if ((typeof(messageJson.bpm) != "undefined")) {//bpm
        looger.info("bpm:" + messageJson.bpm);
        $(".userBpm").text("bpm:" + messageJson.bpm);
    }

    //地图定位
    if ((typeof(messageJson.lng) == "number" && typeof(messageJson.lat) == "number") && typeof(map) == "object") { //经度
        looger.info(messageJson.lng + " <--> " + messageJson.lat);
        //map.setZoom(mapZoom); //设置缩放比例
        drawRoute(messageJson.lng, messageJson.lat);
    }

    //地图旋转
    if (!isNaN(messageJson.angle) && map != null && messageJson.angle != null) {
        looger.info("messageJson.angle:" + messageJson.angle);
        //map.setRotation(messageJson.angle);//根据输入的角度值选择地图
        //transformCssStr = "rotate(-" + messageJson.angle + "deg)";
    }

    //online
    if ((typeof(messageJson.onl) != "undefined" && $("#onlNumber").text() != messageJson.onl)) {
        $("#onlNumber").text(messageJson.onl);
    }

    //接受文本消息或者是声效激励消息
    if ((typeof(messageJson.msg) != "undefined")) {

        if (messageJson.msg == live.GZ) {//鼓掌
            looger.info("收到鼓掌信息");
            $("#myAudio").attr("src", "static/music/gz+.mp3");
            textMessageInput(messageJson);
        } else if (messageJson.msg == live.DIANJI) {//电击
            looger.info("收到电击信息");
            $("#myAudio").attr("src", "static/music/dianji.mp3");
            textMessageInput(messageJson);
        } else if (messageJson.msg == live.GJ) {//狗叫
            looger.info("收到狗叫信息");
            $("#myAudio").attr("src", "static/music/gj.mp3");
            textMessageInput(messageJson);
        } else if (messageJson.msg == live.LH) {//礼花
            looger.info("收到礼花信息");
            $("#myAudio").attr("src", "static/music/lh.mp3");
            textMessageInput(messageJson);
        } else if (messageJson.msg == live.MSG_RC) {//网络重连
            looger.info("收到网络重连信息");
        } else if (messageJson.msg == live.MSG_V) {//语音
            if (messageJson.serverId != null) {//微信语音
                looger.info("收到微信语音信息");
            } else {//APP语音
                looger.info("收到app语音信息");
            }
            if(is_weixn()){
                textMessageInput(messageJson);
            }
        } else if (messageJson.msg == live.ADD_U) {//添加观众
            looger.info("收到添加观众信息");
        } else if (messageJson.msg == live.ROME_U) {//删除观众
            looger.info("收到删除观众信息");
        } else if (messageJson.msg == live.MSG_C) {
            looger.info("收到服务器断开连接 ... ... ");
        } else {//文本信息
            looger.info("收到文本信息");
            textMessageInput(messageJson);
        }

        if ((typeof(messageJson.onl) != "undefined")) {
            looger.info("收到在线人数:" + messageJson.onl);
        }
        if (number > 999) {
            number = 999;
        }
        if (!$(".recording").is(":hidden")) {//聊天记录框显示出来后不在累计数目
            number = 0;
        }
        looger.info("message number : " + number);
        $(".liaotianNumber").text(number);
    }

}
/***
 * 显示接收到的文本信息或者是声效激励
 * @param message
 *
 */
function textMessageInput(messageJson) {
    var isWx = is_weixn();
    var str = '';
    var pImg = "static/img/login_default.png";

    str += '<div class="list-group">';
    if (typeof(messageJson.headimgurl) != "undefined") {
        pImg = messageJson.headimgurl;
    }
    looger.info("userOPenId == " + userOpenId);
    looger.info("messageJson.userOPenId == " + messageJson.userOpenId);
    looger.info("(userOPenId == messageJson.userOPenId) == " + (userOpenId == messageJson.userOpenId));

    if (messageJson.msg == live.GZ) {
        messageJson.msg = '<img src="static/img/cdr_61761.PNG" data-cv="' + messageJson.msg + '" class="userLTImg userLTImg_l" onclick="serMediaUrl(0)" />';
    } else if (messageJson.msg == live.GJ) {
        messageJson.msg = '<img src="static/img/cdr_61759.PNG" data-cv="' + messageJson.msg + '" class="userLTImg userLTImg_l" onclick="serMediaUrl(1)" />';
    } else if (messageJson.msg == live.LH) {
        messageJson.msg = '<img src="static/img/cdr_61758.PNG" data-cv="' + messageJson.msg + '" class="userLTImg userLTImg_l" onclick="serMediaUrl(2)" />';
    } else if (messageJson.msg == live.DIANJI) {
        messageJson.msg = '<img src="static/img/cdr_61760.PNG" data-cv="' + messageJson.msg + '" class="userLTImg userLTImg_l" onclick="serMediaUrl(3)" />';
    }else if(messageJson.msg == live.MSG_V){
        //{"fu":"http://f.igeekery.com/1005/15480eeda2eb41aea98a16b5e35bd038.amr","msgType":3,"na":"Fitmix","onl":1,"ua":"http://f.igeekery.com/1002/7b974247d0884a86abc98a19062258c7.jpg"}
        var yyImg = "",yyImg2="",media="",type=1;
        var mObj = {};
        if (userOpenId == messageJson.userOpenId){
            yyImg2 = "static/img/yuyin_r.gif";
            yyImg = "static/img/yuyinwo_l.png";

        }else{
            yyImg2 = "static/img/yuyin_l.gif";
            yyImg = "static/img/yuyinta_l.png";
        }

        mObj.img = yyImg2;

        if(messageJson.serverId != null){//h5发送的 微信语音id
            media = messageJson.serverId;
            mObj.serverId = messageJson.serverId;
            mObj.type=2;
        }else{//app发送的语音地址
            media = messageJson.fu;
            mObj.url = messageJson.fu;
            mObj.type=1;
        }

       /* if(typeof(messageJson.ua) != "undefined"){
            yyImg = messageJson.ua;
        }*/

        if(isWx){
            messageJson.msg = '<img src="'+yyImg+'" class="yy userLTImg userLTImg_l" onclick="downloadVoice(\''+messageJson.serverId+'\',this)" />';
        }else{
            messageJson.msg = '<img serverId="no" src="'+yyImg+'" class="yy userLTImg userLTImg_l" onclick="appMediaUrl(\''+messageJson.fu+'\')" />';
        }
    }

    if (userOpenId == messageJson.userOpenId) {
        //自己发送的消息显示在右边
        str += '<p class="list-group-item-text recording-text-right"><span>' + messageJson.msg + '</span>:<img src="' + pImg + '" class="userLTImg userLTImg_l" /></p>';
    } else {
        str += '<p class="list-group-item-text"><img src="' + pImg + '" class="userLTImg userLTImg_r" />:<span>' + messageJson.msg + '</span></p>';
    }

    str += '</div>';
    $(".recording").append(str);
    number++;
    $(".recording").scrollTop($(".recording").height() * $(".list-group-item-text").length);//设置滚动条位置
}
/***
 * 设置播放器路径
 * 可以重复设置
 * @param media 语音路径或者微信服务器语音文件id
 */
function serMediaUrl(media) {
    $("#myAudio").attr("src", shenxiaoArray[media]);
}

function appMediaUrl(msg){
    /*if(obj.type == 1){
        $(this).attr("src",app.img);
        $("#myAudio").attr("src", app.url);
    }else{
        downloadVoice(obj,wx);
    }*/
    $("#yyAudio").attr("src", msg);
    muisYY(document.getElementById("yyAudio"),this);
}

/***
 * 设置播放器状态
 * @constructor
 */
function Muise(event) {

    var audio = event.data.audio;
    if (audio.paused) {//当前正在暂停
        setMuise(audio, live.prototype.mu);
        audio.play();
    } else {//当前正在播放
        audio.pause();
    }
}
/***
 * 设置播放器音频文件地址
 */
function setMuise(audio, src) {
    looger.info("src == [" + audio.src + "]");
    if (audio.src != src) {
        audio.src = src;
    }
}
/***
 * 监听音乐播放器状态
 * @param audio 播放器对象
 */
function muiseZT(audio) {
    //生成随机参数用户刷新图片
    var n = Math.random();
    //监听播放器的一些状态
    audio.onplaying = function () {
        //当媒介已开始播放时运行的脚本。
        looger.info("媒介开始播放");
        $(".muiseImg").attr("src", "static/img/muise.gif?" + n);
    }
    audio.onpause = function () {
        //当媒介被用户或程序暂停时运行的脚本。
        looger.info("媒介暂停");
        $(".muiseImg").attr("src", "static/img/muise-_l.png?" + n);
    }
    audio.onstalled = function () {
        //在浏览器不论何种原因未能取回媒介数据时运行的脚本。
        looger.info("浏览器未能取回媒介数据，原因未知.");
        $(".muiseImg").attr("src", "static/img/muise-_l.png?" + n);
    }
    audio.onerror = function () {
        //当在文件加载期间发生错误时运行的脚本。
        looger.info("音频文件发生错误");
        $(".muiseImg").attr("src", "static/img/muise-_l.png?" + n);
    }
}
/***
 * 监听声效激励播放器
 * @param audio
 */
function muiseZTJL(audio) {
    //生成随机参数用户刷新图片
    var n = Math.random();
    //监听播放器的一些状态
    audio.onplaying = function () {
        shenxiao = false;
    }
    audio.onpause = function () {
        shenxiao = true;
    }
    audio.onstalled = function () {
        shenxiao = true;
    }
    audio.onerror = function () {
        shenxiao = true;
    }
}
/***
 * 定时显示消息提示框
 * @param obj
 * @param zIndex
 * * @param errorText 警告文字
 */
function error(obj, cssJson, errorText) {
    $(".errorText").text(errorText);
    obj.css(cssJson);
    /*if(errorText == live.ERROR_C_){
     liveSocket.init(live.SOCKETHOSE);//重连
     }*/
}
/***
 * 下载
 */
function downloadApp() {
    var downloadUrl = "http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk";//默认为android下载地址
    if (browser.versions.android) {
        _hmt.push(['_trackEvent', "fitmixAndroidApp", "Download"]);//百度统计
    } else if (browser.versions.ios) {
        _hmt.push(['_trackEvent', " ", "Download"]);//百度统计
        downloadUrl = "https://itunes.apple.com/cn/app/le-xiang-dong-jian-shen-jian/id999239164?l=en&mt=8";
    }
    location.href = downloadUrl;//交给应用宝
}
/***
 * 设置人物图片加载后的旋转角度
 */
function renImg(){
    looger.info($("#paorenImg"));
    if ($("#paorenImg")) {
        looger.info("_----- " + transformCssStr);
        $("#paorenImg").css({
            webkitTransform: transformCssStr,
            MozTransform: transformCssStr,
            msTransform: transformCssStr,
            OTransform: transformCssStr,
            transform: transformCssStr
        });
    }
}
//界面显示语音
function inputYY(){

}
/***
 * app语音播放器监听
 * @param audio
 */
function muisYY(audio,ement) {

    var imgSrc = {
        ImgUrlP:null,//播放是的状态图片
        ImgUrlT:null//停止时的播放图片
    };
    if(ement.serverId != "no"){
        //自己
        imgSrc.ImgUrlP = "static/img/yuyinta.gif";
        imgSrc.ImgUrlT = "static/img/yuyinta_l.png";
    }else{
        imgSrc.ImgUrlP = "static/img/yuyinwo.gif";
        imgSrc.ImgUrlT = "static/img/yuyinwo_l.png";
    }

    //监听播放器的一些状态
    audio.onplaying = function () {
        //当媒介已开始播放时运行的脚本。
        looger.info("媒介开始播放");
        ement.src = imgSrc.ImgUrlP;
    }
    audio.onpause = function () {
        //当媒介被用户或程序暂停时运行的脚本。
        looger.info("媒介暂停");
        ement.src = imgSrc.ImgUrlT;
    }
    audio.onstalled = function () {
        //在浏览器不论何种原因未能取回媒介数据时运行的脚本。
        looger.info("浏览器未能取回媒介数据，原因未知.");
        error($(".myAlert"),{zIndex:10000},"浏览器未能取回媒介数据，原因未知.");
        ement.src = imgSrc.ImgUrlT;
    }
    audio.onerror = function () {
        //当在文件加载期间发生错误时运行的脚本。
        looger.info("音频文件发生错误");
        ement.src = imgSrc.ImgUrlT;
    }
}