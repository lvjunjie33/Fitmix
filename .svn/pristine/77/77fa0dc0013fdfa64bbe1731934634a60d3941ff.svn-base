<%--
  Created by IntelliJ IDEA.
  User: wjcaozhi1314
  Date: 2015/12/17
  Time: 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="screen-orientation" content="portrait">
    <!-- QQ强制竖屏 -->
    <meta name="x5-orientation" content="portrait">
    <!-- UC强制全屏 -->
    <meta name="full-screen" content="yes">
    <!-- QQ强制全屏 -->
    <meta name="x5-fullscreen" content="true">
    <!-- UC应用模式 -->
    <meta name="browsermode" content="application">
    <!-- QQ应用模式 -->
    <meta name="x5-page-mode" content="app">
    <!-- windows phone 点击无高光 -->
    <meta name="msapplication-tap-highlight" content="no">

    <!--高德地图css-->
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
    <%-- bootstrap --%>
    <link rel="stylesheet" href="static/liveCss/bootstrap.min.css">
    <%-- 自定义css --%>
    <link rel="stylesheet" href="static/liveCss/live.css">

    <%-- 百度统计 --%>
    <script>
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?b5bf26e306096c5c177896b7d17f6d74";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

    <%-- 第三方库 --%>
    <script type="text/javascript" src="static/js/jquery-2.1.4.min.js"></script>
    <%--<script src="static/js/jquery.1.11.1.min.js"></script>--%>
    <%--<script src="static/js/jquery.touchSwipe.min.js"></script>--%>
    <%-- 高德地图 --%>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=0e9cc8786d487525f8759b2e3bd9d476"></script>
    <%-- 微信 --%>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <%-- 高德地图功能 --%>
    <script src="static/js/mapUtil.js"></script>

</head>
<body scroll=no>

    <%--<input id="ui" type="hidden" value="${socketMessage.ui}">--%>
    <div id="navber_top" class="page-header-my">
        <div class="userImg">
            <img id="userinfoImg" class="img-responsive img-circle center-block" src="${socketMessage.ua}"/>
        </div>
        <div id="na" class="userName">${socketMessage.na}</div>
        <div class="userGL">
            公里:<span>00</span>
        </div>
        <div class="userTIme">
            小时:<span>0'00''</span>
        </div>
        <div class="userBpm">
            bpm:<span>0</span>
        </div>
    </div>

    <div id="map"></div>


    <div id="hudong" class="container hudong">
        <div class="row">
            <div class="col-xs-2">
                <img data-type="click" data-cv="_lh_" src="static/img/cdr_61758.PNG"
                     class="img-responsive img-circle"/>
            </div>
            <div class="col-xs-2">
                <img data-type="click" data-cv="_gj_" src="static/img/cdr_61759.PNG"
                     class="img-responsive img-circle"/>
            </div>
            <div class="col-xs-2">
                <img data-type="click" data-cv="_dianji_" src="static/img/cdr_61760.PNG"
                     class="img-responsive img-circle"/>
            </div>
            <div class="col-xs-2">
                <img data-type="click" data-cv="_gz_" src="static/img/cdr_61761.PNG"
                     class="img-responsive img-circle"/>
            </div>
            <div class="col-xs-2">
                <a class="btn btn-default errorDow" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk" role="button">FITMIX 下载</a>
            </div>
        </div>
    </div>

    <nav id="navber_bottom" class="navbar navbar-default navbar-fixed-bottom">
        <div class="container-fluid">
            <ul class="ul-default ul-default-bottom">
                <li class="btnLi" data-type="click" data-cv="_jl_">
                    <img src="static/img/liuyan.png" class="img-responsive center-block">
                    <span class="liaotianNumber">0</span>
                </li>
                <li id="yy" class="btnLi yyLi" data-type="click" data-cv="_yy_">
                    <img src="static/img/yuyin.png" class="img-responsive center-block">
                </li>
                <li class="botInput">
                    <input id="liuYanText" type="text" maxlength="50" class="form-control" placeholder="吐槽 ... ... ">
                </li>
                <li class="btnLi" data-type="click" data-cv="jili">
                    <img src="static/img/biaoqing.png" class="img-responsive center-block">
                </li>
                <li class="btnLi" style="color:#FFFFFF;" data-type="click" data-cv="liuYanText">发送</li>
            </ul>
        </div>
    </nav>

    <div class="onl ww">在线人数:<span id="onlNumber">0</span></div>

    <div class="recording">
        <%--<div class="list-group">
            <p class="list-group-item-text"><img src="static/img/biaoqing@2x.png" class="userLTImg userLTImg_l" /><span>kljkljkljlkjl</span></p>
        </div>
        <div class="list-group">
            <p class="list-group-item-text recording-text-right"><span>kljkljkljlkjl</span><img src="static/img/biaoqing@2x.png" class="userLTImg userLTImg_r" /></p>
        </div>--%>
    </div>

    <div class="alert alert-danger myAlert" role="alert">
        <p class="errorText"></p>
        <hr />
        <ul class="alert_button_ul">
            <li class="alert_button_ul_li_left" data-type="click" data-cv="guanbi">关闭</li>
            <li>
                <a class="btn btn-default errorDow" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk" role="button">FITMIX 下载</a>
            </li>
        </ul>
    </div>

    <img src="static/img/yuyinjingzhiJ2.gif" class="img-responsive center-block yuyinjingzhiJ2 yuyinImgCenter hidden"/>

    <img src="static/img/muise-_l.png" class="img-responsive muiseImg" />
    <img src="static/img/logo.png" class="img-responsive logo" />

    <audio autoplay="autoplay" id="muiseAudio" autoplay="autoplay"><%-- 音乐播放器 --%></audio>
    <audio autoplay="autoplay" id="myAudio" autoplay="autoplay"><%-- 声效激励或者语音播放器或者其它播放器 --%></audio>
    <audio autoplay="autoplay" id="yyAudio" autoplay="autoplay"><%--语音聊天播放器--%></audio>


    <%-- bootstrap js 组件
    <script type="text/javascript" src="static/js/bootstrap.min.js"></script>--%>

    <%-- 自定义功能实现 --%>
    <script type="text/javascript" src="static/js/liveUtil.js"></script>
    <script type="text/javascript" type="text/javascript" id="socket-start">
        (function (window) {

            var live = function () {
                    } || {};
            /**
             * 直播结束提示
             **/
                //live.LIVE_END_ALERT_MSG = "<div style='height: 30px;width: 100%;text-align:center;position: fixed;top:30%;z-index: 20;color: #FFFFFF;'>亲，你来晚了直播结束了...</div>";
            live.LIVE_END_ALERT_MSG = "亲，你来晚了直播结束了...";
            /**
             * 特殊字符/断开服务
             */
            live.MSG_C = "_c_";
            /**
             * 特殊字符/网络波动，重连 （reconnection）
             */
            live.MSG_RC = "_rc_";
            /**
             * 特殊字符/激励音乐
             */
            live.MSG_J = "_j_";
            /***
             * 鼓掌声效激励
             */
            live.GZ = "_gz_";//{type:"_gz_",value:"static/music/gz+.mp3"};
            /***
             * 电击声效激励
             */
            live.DIANJI = "_dianji_";//{type:"_dianji_",value:"static/music/dianji.mp3"};
            /***
             * 狗叫声效激励
             */
            live.GJ = "_gj_";//{type:"_gj_",value:"static/music/gj.mp3"};
            /***
             * 鲜花声效激励
             */
            live.LH = "_lh_";//{type:"_lh_",value:"static/music/lh.mp3"};
            /**
             * 特殊字符/鞭打声
             */
            live.MSG_B = "_b_";
            /**
             * 特殊字符/直播语音
             */
            live.MSG_V = "_v_";

            /**
             * 消息发送者/系统发送 （system）
             */
            live.SNA_S = "_s_";
            /**
             * 消息发送者/分析用户-直播用户 （share user）
             */
            live.SNA_SU = "_su_";
            /**
             * 消息发送者/观看者 （watch user）
             */
            live.SNA_WU = "_wu_";
            /**
             *
             */
            live.SM_Y = "y";
            /**
             *
             */
            live.SM_N = "n";
            /**
             *添加观众
             */
            live.ADD_U = "add_u";
            /**
             *删除观众
             */
            live.ROME_U = "rome_u";
            /***
             * 微信appid
             */
            live.WXAPPID = "wx7adcfc1457f072b4";
            //socket url
            live.SOCKETHOSE = "ws://120.76.74.92:8069/socket-server";//"${host}";
            //警告提示语
            live.ERROR_C_ = "直播已经结束 ... ...";

            live.prototype = {
                ///
                /// 用户信息  （用户信息字段重复使用， 根据 sna（sender name）发送者类型，来区分）
                /**
                 * uid 编号
                 */
                ui: ${socketMessage.ui},
                /**
                 * name avatar 名称
                 */
                na: "${socketMessage.na}",
                /**
                 * 用户头像
                 */
                ua: "${socketMessage.ua}",
                /**
                 * WebSocketServer 使用
                 * y , n
                 */
                sm: live.SM_Y,
                ///
                /// 发送这信息

                /**
                 * 发送者名称 sender name
                 */
                sna: live.SNA_WU,
                /**
                 * msg 消息 (特殊字符 : _c_(断开服务器) _j_(激励音乐) _b_(鞭打声) _v_(live voice 直播语音,语音地址 fu))
                 */
                msg: "",
                /**
                 * fileUrl 文件地址（语音，视频，等等）
                 */
                fu: "",
                ///
                /// 歌曲信息

                /**
                 * mix 编号
                 */
                mi: ${socketMessage.mi},
                /**
                 * mix 名称
                 */
                mn: "${socketMessage.mn}",
                /**
                 * mix 作者，歌手 (Mix author)
                 */
                ma: "${socketMessage.ma}",
                /**
                 * 歌曲地址
                 */
                mu: "${socketMessage.mu}",
                /**
                 * 歌曲图片地址
                 */
                mau: "${socketMessage.mau}",
                ///
                /// 歌曲信息

                /**
                 * mix 编号
                 */
                mi: ${socketMessage.mi},
                /**
                 * mix 名称
                 */
                mn: "${socketMessage.mn}",
                /**
                 * mix 作者，歌手 (Mix author)
                 */
                ma: "${socketMessage.ma}",
                /**
                 * 歌曲地址
                 */
                mu: "${socketMessage.mu}",
                /**
                 * 歌曲图片地址
                 */
                mau: "${socketMessage.mau}",

                ///
                /// 实时地图信息

                /**
                 * 经度
                 */
                lng: 0,
                /*
                 * 纬度
                 */
                lat: 0,

                ///
                /// 实时运动信息

                /**
                 * 距离 (distance)
                 */
                dst: 0,
                /**
                 * 运动时常
                 */
                rt: 0,
                /**
                 * bpm
                 */
                bpm: 0,
                /**
                 * 配速
                 */
                ps: 0,

                ///
                /// 其他

                /**
                 * 在线人数
                 **/
                onl: 0,
                /***
                 * 跑步模式  1室外 2室内
                 */
                mo: "${mo}",
                /***
                 * 地图选择角度
                 */
                angle: "${angle}",

                /***
                 * 微信用户名
                 */
                nickname: "",
                /***
                 * 微信用户头像
                 */
                headimgurl: "",
                /**
                 * 微信 access_token */
                wx_pt_access_token: ${wx_pt_access_token},
                /***
                 * 微信用户信息
                 */
                wx_userInfo: ${userInfoStr},

                jsapi_ticket:${jsapiTicket},
                /**
                 * 拼接微信媒体下载地址*/
                socketInputVUrl: "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=",
                socketInputVUrlMediaId: "&media_id=",
                /***
                 * 警告定时器
                 */
                errorInterval:null
            };


            /**
             * socket init
             * @type {{init: Function}}
             */
            var liveSocket = function () {} || {};
            liveSocket.socket;
            liveSocket.init = function (url) {
                console.log("webSocketUrl:" + url);
                liveSocket.socket = new WebSocket(url);

                var newlive = new live();
                var json = {};
                /*json["sm"] = newlive.sm;*/
                json["ui"] = live.prototype.ui;// = $("#ui").val();//newlive.ui; // 用户 uid 用户编号

                liveSocket.socket.onopen = function (even) {

                    var socketOpenJson = JSON.stringify(json);
                    console.log("onopen ... ...");
                    console.log("socket open : " + socketOpenJson);
                    liveSocket.socket.send(socketOpenJson);
                    /*if(live.prototype.errorInterval != null){
                        try {
                            clearInterval(live.prototype.errorInterval);//停止警告提示
                            live.prototype.errorInterval = null;
                        }catch (e){}
                    }*/
                };
            };
            //liveSocket.init("ws://192.168.1.152:8069/socket-server"); // 初始化socket
            //liveSocket.init("${host}"); // 初始化socket
            //liveSocket.init(live.SOCKETHOSE);
            liveSocket.init("ws://live.igeekery.com:8069/socket-server"); // 初始化socket
            //liveSocket.init("ws://testlive.igeekery.com:8069/socket-server"); // 初始化socket
            //liveSocket.init("ws://119.29.86.99:8069/socket-server");
            //liveSocket.init("wx://192.168.1.79:8080/live/socket-server");
            live.prototype.sm = live.SM_N;
            window.live = live;
            window.liveSocket = liveSocket;
        })(window);
    </script>
    <script type="text/javascript" src="static/js/live.js"></script>
    <%-- 微信功能实现 --%>
    <script src="../../static/js/sha1.js"></script>
    <script type="text/javascript" src="static/js/wxUtil.js"></script>
    <script type="text/javascript" src="static/js/wxLive.js"></script>
    <script src="../../static/util/delete.js"></script>
    <script id="liveEndScript">
        deleteAdvertising("navber_top","liveEndScript");
    </script>

</body>
</html>
