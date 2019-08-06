<%--
  Created by IntelliJ IDEA.
  User: wjcaozhi1314
  Date: 2016/1/20
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="igeekery 生活 运动 服务 一体化，我们为您提供最 智能的硬件 软件 服务平台">
    <meta name="description"
          content="igeekery 是深圳第一蓝筹科技有限公司提供的 软硬件（智能服务，智能硬件）服务平台, 我们提供最优质 Mix 运动音乐和最智能的软硬件，让运动更方便，让喜欢运动的人群得到更好的运动体验。">
    <title>2016四季跑Season Run全国站</title>

    <!--高德地图css-->
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
    <link href="/static/event/css/event.css" type="text/css" rel="stylesheet">
    <style>
        .event ul {
            padding-top: 20px;
            padding-left: 10px;
            width:90%;
        }

        .event ul li {
            background-color: inherit;
            float: left;
        }

        .event ul .leftLi {
            width: 30%;
        }

        .event ul .rightLi {
            width: 70%;
        }

        .introduction p {
            position: relative;
            /*width: 90%;*/
            height: auto;
            padding-top: 10px;
            font-size: 12px;
        }

        .introductionImg {
            width: inherit;
            height: auto;
        }

        .textColorRed {
            color: red;
        }

        .event {
            padding: 0px;
            padding-bottom: 20px;
            background-color: #FFFFFF;
        }

        .commonP2 {
            padding-left: 10px;

        }

        .eventInformation {
            padding-left: 10px;
            width:97%;
        }
        .textImg {
            width:97%;
        }
    </style>
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script src="/static/event/js/event.js"></script>
    <%-- 引入去广告js --%>
    <script src="/static/util/delete.js"></script>

</head>
<body>

<div id="event" class="event">

    <img class="eventTitleImg" src="${titleImg}"/>

    <div class="divDfault">
        <p id="eventName" class="commonP2">2016四季跑Season Run全国站</p>
        <p class="commonType">
        </p>
    </div>

    <ul>
        <li class="leftLi">活动时间:</li>
        <li class="rightLi">2016.04—2016.12（各站具体时间待定）</li>
        <li class="leftLi">活动类型:</li>
        <li class="rightLi">彩色跑</li>
        <li class="leftLi">报名费:</li>
        <li class="rightLi">待定</li>
    </ul>

    <div class="eventInformation" style="margin-top: 140px;">活动简介</div>
    <div class="eventInformation introduction" style="margin-top: 10px;">
        <p>中国首个拥有自主IP的原创现象级路跑活动</p>
        <p>专为“70、80、90、00后及可爱的小朋友们”提供的一个分享欢乐、分享爱的快乐运动平台，被誉为“地球上最有爱、最好玩的跑步派对！”</p>
        <p></p>
        <img class="eventTitleImg textImg" src="/static/event/images/seasonRun/1-16012Q330220-L.png" />
        <p>THE SEASON RUN（四季跑）以极致娱乐、纯粹快乐为基因，打造国际最具品质跑步派对，让你和朋友、家人一整天都沉浸在运动的快乐之中。2016年四季跑，玩法变化升级，以“穿越四季，回到那年”为主题，让你在穿越四季的同时，回忆童年那最本真的快乐。</p>
        <p></p>
        <img class="eventTitleImg textImg" src="/static/event/images/seasonRun/imgshow2.png" />
        <p>在四季跑广场，你会玩到“丢沙包”“跳房子”“老鹰捉小鸡”等儿时经典游戏；在跑道上以声、光、物、影制造春夏秋冬四季交替效果，让你忍不住“自拍”、“晒朋友圈”，从而打破以往速度为王的定律，注重体会运动带来的快乐。</p>
        <p>本项赛事全部跑程及设计制作物均符合国际安全高级标准，为你踏出的每一步提供保障。四季跑现场将设立跳蚤市场，为孩子们提供书籍和玩具交换的平台。不用价值来衡量，让书籍和玩具打开沟通的可能，心与心因真诚而更加亲近。</p>
        <p></p>
        <img class="eventTitleImg textImg" src="/static/event/images/seasonRun/imgshow1.png" />
        <p>此外，四季跑更倾注公益元素，在每一站活动中都将设立儿童玩具及书籍捐赠环节，为需要帮助的孩子们送去来自四季跑的温暖与爱，带给他们充满欢声笑语的快乐童年。</p>
        <p>激情、自由、公益，与百万跑友共享快乐, 快来加入我们吧！</p>
        <p></p>
        <img class="eventTitleImg textImg" src="/static/event/images/seasonRun/imgshow123.png" />
        <p>首站：深圳 2016.04.23</p>
        <p>第二站：南京 2016.05.14</p>
        <p>更多城市计划中</p>


    </div>

        <c:choose>
            <c:when test="${apply}">
                <input type="button" id="baoMingBut" class="baoMingBut" value="立即报名" style="margin-top: 10px;"/>
                <input type="hidden" id="activityId" value="${activityId}"/>

                <script>

                    /***
                     * 提供给app调用的方法
                     * @param userId
                     * @constructor
                     */
                    function GetAppUserId(userId) {
                        var httpHost = "http://" + window.location.host;
                        var WIDsubject, activityId, uid;
                        WIDsubject = $("#eventName").text();//订单名称 赛事名称
                        activityId = $("#activityId").attr("value");//赛事活动id
                        uid = userId;//用户id

                        //跳转支付请求
                        location.href = httpHost+"/activity/register.htm?" +
                         "&activityId=" + activityId +
                         "&uid=" + uid;
                    }

                    $(function () {

                        //将报名按钮剧中
                        $("#baoMingBut").css({
                            "marginLeft": (parseInt($("#event").css("width")) - parseInt($("#baoMingBut").css("width"))) / 2
                        });

                        /*//初始化地图对象，加载地图
                         var map = new AMap.Map('map', {
                         zoom: 11,
                         center: [116.397428, 39.90923]
                         });

                         $(".amap-logo").remove();//删除高德地图中一些不需要的元素
                         $(".amap-copyright").remove();//删除高德地图中一些不需要的元素*/


                        //添加报名跳转支付宝url
                        $("#baoMingBut").click(function () {
                            if (${register == "true"}) {
                                alert("已经报名完成，不需要重复报名!");
                            } else {
                                app();
                            }
                        });
                    });
                </script>
            </c:when>
        </c:choose>

</div>
</body>
</html>