/**
 * Created by wjcaozhi1314 on 2016/3/4.
 */
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18

var DataPrototype = {
    FORMATSTR_1 : "yyyy-MM-dd hh:mm:ss",
    FORMATSTR_2 : "yyyy/MM/dd",
    FORMATSTR_3 : "hh:mm",
    FORMATSTR_4:"hh",
    FORMATSTR_5:"mm",
    FORMATSTR_6:"ss",
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
Date.prototype.getFormat = function (fmt) {
    var fmtStr = "";
    if (fmt.indexOf("yyyy-MM-dd") >= 0) {
        fmtStr += this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate();
    }
    if (fmt.indexOf("hh:mm:ss") >= 0) {
        fmtStr += " " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
    }
    return fmtStr;

}
/*
 * 获得时间差,时间格式为 年-月-日 小时:分钟:秒 或者 年/月/日 小时：分钟：秒
 * 其中，年月日为全格式，例如 ： 2010-10-12 01:00:00
 * 返回精度为：秒，分，小时，天
 */
function GetDateDiff(startTime, endTime, diffType) {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式
    startTime = startTime.replace(/\-/g, "/");
    endTime = endTime.replace(/\-/g, "/");
    //将计算间隔类性字符转换为小写
    diffType = diffType.toLowerCase();
    var sTime = new Date(startTime);      //开始时间
    var eTime = new Date(endTime);  //结束时间
    //作为除数的数字
    var divNum = 1;
    switch (diffType) {
        case "second":
            divNum = 10000;
            break;
        case "minute":
            divNum = 1000 * 60;
            break;
        case "hour":
            divNum = 1000 * 3600;
            break;
        case "day":
            divNum = 1000 * 3600 * 24;
            break;
        default:
            break;
    }
    var time = parseInt((eTime.getTime() - sTime.getTime()) / parseInt(divNum));
    if (time.toString().length < 2) {
        time = "0" + time
    } else if (time.toString().length > 2) {
        time = time.toString().substring(0, 3);
    }
    return time;
}
/**
 * 计算两个时间的时间差
 * @param bigDate 开始时间
 * @param endDate 结束时间
 */
function getDateDiff(bigDate, endDate) {
    var h = endDate.getHours() - bigDate.getHours();//小时差
    var m = endDate.getMinutes() - bigDate.getMinutes();//分钟差
    var s = endDate.getSeconds() - bigDate.getSeconds();//分钟差



}

//找出轨迹经纬度的最大值
function getMaxNumber(array, page) {
    var maxNumber = 0;
    for (var i = 0; i < array.length; i++) {
        var number = parseFloat(array[i][page]);
        if (maxNumber < number) {
            maxNumber = number;
        }
    }
    return maxNumber;
}

/***
 * 将userName 设置成符合规定的格式
 * @param userName
 */
function getUserName(userName){
    if(userName == ""){
        return "";
    }else if (isMailbox(userName)){
        var endNumber = userName.indexOf("@");
        return userName.substring(0,endNumber);
    }
    return userName;
}
/***
 * 邮箱地址
 * @param mailboxUrl
 * @returns {boolean}
 */
function isMailbox(mailboxUrl){
    var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(!reg.test(mailboxUrl)) {
        //"请输入有效的邮箱地址！"
        return false;
    }
    return true;
}



//去重
Array.prototype.unique = function(){
    var res = [];
    var json = {};
    for(var i = 0; i < this.length; i++){
        if(!json[this[i]]){
            res.push(this[i]);
            json[this[i]] = 1;
        }
    }
    return res;
}
//var arr = [112,112,34,'你好',112,112,34,'你好','str','str1'];
//alert(arr.unique());


//通过判断浏览器的userAgent，用正则来判断手机是否是ios（苹果）和Android（安卓）客户端
function userArg(){
    var u = navigator.userAgent;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
    //var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    //alert('是否是Android：'+isAndroid);
    //alert('是否是iOS：'+isiOS);
    return isAndroid;
}


//最大值
Array.prototype.max = function() {
    var max = this[0];
    var len = this.length;
    for (var i = 1; i < len; i++){
        if (this[i] > max) {
            max = this[i];
        }
    }
    return max;
}