/**
 * Created by wjcaozhi1314 on 2015/12/17.
 */
var liveSocketObj = null;
var wWidth = wHeight = 0;

$(function (){
    //初始化页面信息
    wWidth = $(window).width();
    wHeight = $(window).height();

    try {
        looger.info("mu == " + live.prototype.mu);
    }catch (e){}

    liveSocketObj = window.liveSocket.socket;//设置liveSocket

    liveSocketObj.onmessage = function (evt){//监听websocket message消息
        mySocketMessage(evt);
    };
    liveSocketObj.onclose = function (evt){//断开连接操作
        looger.info("直播连接已经断开!");
        //looger.info($(".myAlert").css("zIndex"));
       /* $(".myAlert").css({
            zIndex:10000
        });*/
        live.prototype.errorInterval = setInterval(error($(".myAlert"),{zIndex:10000},live.ERROR_C_),(1000*30));
    };
    liveSocketObj.onerror = function (e){
        looger.info("liveSocket error:"+e);
        live.prototype.errorInterval = setInterval(error($(".myAlert"),{zIndex:10000},"直播连接错误:"+ e),(1000*30));
    };

    //$("#map").css("height",wHeight-$("#navber_bottom").height());
    //$(".recording").css("height",(wHeight-80) + "px");

    //初始化地图对象，加载地图
    map = new AMap.Map('map',{
        zoom:17,//缩放级别
        animateEnable:true,
        //mapStyle:"blue_night",//主题
        rotateEnable:true//是否可旋转
    });

    $(".amap-logo").remove();//删除高德地图中一些不需要的元素
    $(".amap-copyright").remove();//删除高德地图中一些不需要的元素
    looger.info("已经删除高德滴入下边描述");

    $(".botInput").css("width",wWidth - ($(".btnLi").width()*4));

    $("[data-type='click']").bind({
        click:myClick
    });

    $("[data-type='_cm_']").click(function (){
        //alert("网页端语音功能正在测试中... \n 敬请期待");
        error($(".myAlert"),{zIndex:10000},"网页端语音功能正在测试中... 敬请期待");
    });

    $("#liuYanText").keydown(inputMyKeydown);//给文本框增加回车发送功能
    //监听播放器状态
    if(live.prototype.mu == "" || live.prototype.mu.length <= 0){//直播用户是否在听音乐
        $(".muiseImg").remove();
    }else{
        var audio = document.getElementById("muiseAudio");
        muiseZT(audio);
        //音乐播放器
        $(".muiseImg").click({"audio":audio},Muise);
    }
    muiseZTJL(document.getElementById("myAudio"));

});
