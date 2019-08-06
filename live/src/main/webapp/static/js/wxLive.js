/**
 * Created by wjcaozhi1314 on 2015/12/22.
 */
if(is_weixn()){//确保在微信里面
    $(function (){
        //looger.info("weixin ... ... ");
        var fxLocatoinHref = "";//微信分享连接
        var url001 = location.href;
        /*looger.info("url001:\n"+url001);
        looger.info("url001.substring(code) == " + url001.indexOf("code"));
        looger.info((is_weixn() && url001.indexOf("code") < 0));*/
        if(is_weixn() && url001.indexOf("code") < 0){//url001.indexOf("&code") < 0 //
            looger.info("微信鉴权失败,请重新鉴权!");
            var URL = encodeURIComponent(url001);//用户点击分享真实的服务器分享地址
            fxLocatoinHref = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+live.WXAPPID+"&redirect_uri="+URL+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
            location.href = fxLocatoinHref;
        }
        //语音通信
        //$("[data-type='_cm_']").click(wx_yuYinClick);

        var jsapiTicket = live.prototype.jsapi_ticket;
        var mytimestamp = Date.now();
        var userinfo = live.prototype.wx_userInfo;
        /**
         * 微信功能接口
         */
        wx.config({
            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId : 'wx7adcfc1457f072b4', // 必填，公众号的唯一标识
            timestamp : mytimestamp, // 必填，生成签名的时间戳
            nonceStr : 'liveigeekery', // 必填，生成签名的随机串
            signature : calcDigest("jsapi_ticket=" + jsapiTicket.ticket
                + "&noncestr=liveigeekery&timestamp=" + mytimestamp
                + "&url=" + location.href), // 必填，签名，见附录1
            jsApiList : [ "onMenuShareTimeline", "onMenuShareAppMessage",
                "onMenuShareQQ", "onMenuShareWeibo",
                "onMenuShareQZone", "startRecord", "stopRecord",
                "onVoiceRecordEnd", "playVoice", "pauseVoice",
                "stopVoice", "onVoicePlayEnd", "uploadVoice",
                "downloadVoice", "chooseImage", "previewImage",
                "uploadImage", "downloadImage", "translateVoice",
                "getNetworkType", "openLocation", "getLocation",
                "hideOptionMenu", "showOptionMenu", "hideMenuItems",
                "showMenuItems", "hideAllNonBaseMenuItem",
                "showAllNonBaseMenuItem", "closeWindow", "scanQRCode",
                "chooseWXPay", "openProductSpecificView", "addCard",
                "chooseCard", "openCard" ]
            // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.ready(function (){
            $("#yy").click({"wx":wx},wx_yuYinClick);
        });
        wx.error(function(res) {
            // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
        });

    });
}