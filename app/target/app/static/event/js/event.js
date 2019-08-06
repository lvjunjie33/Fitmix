/**
 * Created by wjcaozhi1314 on 2016/1/20.
 */

/***
 * 字符串去掉前后空格
 * @returns {string}
 */
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, '');
}

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
 * 通知app 页面需要获取用户数据
 */
function app() {
    var ios, android;
    if (location.href.indexOf("event-inde") >= 1) {//支付
        ios = "Sign Up";
    } else if (location.href.indexOf("list") >= 1) {//首页
        ios = "event list";
    }
    //根据终端设置与app交互的值
    if (browser.versions.ios) {
        window.webkit.messageHandlers.fitmix.postMessage({body: ios});//通知app
    } else if (browser.versions.android) {
        window.fitmix.checkContestantInfo();
    }

}

var winW, winH;
$(function () {
    winW = $(window).width();
    winH = $(window).height();
   $(".event_new").css({
        "width": winW+ "px"
    });
    $(".listType").css({width:(winW - 40) + "px"});
    $(".listType2").css({width:(winW - 50) + "px"});
});