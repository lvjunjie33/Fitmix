<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/1/15
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>悦跑 感谢一路有你 年度分享交生盛会 报名</title>
    <!-- 禁止缩放 -->

    <!--twitter meta-->
    <meta name="twitter:card" content="summary_large_image">
    <meta name="twitter:creator" content="@MAKA">
    <meta name="twitter:title" content="感谢一路有你，悦跑年会开始报名~">
    <meta name="twitter:description" content="悦跑年度盛会 报名">
    <meta name="twitter:image" content="http://img1.maka.im/cover/97399912056990958d132a6.02210370.jpg@159-82-736-736a">

    <title>感谢一路有你，悦跑年会开始报名~</title>
    <meta name="Description" content="悦跑年度盛会 报名">

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/static/jquery.form.js"></script>


    <meta property="og:type" content="website">
    <meta property="og:title" content="感谢一路有你，悦跑年会开始报名~">
    <meta property="og:image" content="http://img1.maka.im/cover/97399912056990958d132a6.02210370.jpg@159-82-736-736a">
    <meta property="og:description" content="悦跑邀请你参加年度盛会……">



    <style type="text/css">

        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        body{
            /*margin: 0px 15px 20px 15px;*/
            /*text-align: center;*/
            max-width: 500px;
            margin: 0px auto;
            text-align: center;
        }

        .image{
            width: 100%;
        }

        #panel-form{
            margin: 10px;
        }

        #desc{
            text-align: left;
            margin: 10px;
            line-height: 2;;
        }
    </style>
</head>
<body>
    <c:if test="${!empty error}">
        <div style="background: #d9534f;text-align: center;color: #FFF;line-height: 3;position: fixed;top: 0px;width: 100%;">
            ${error}
        </div>
    </c:if>

    <img src="imgs/activity/20160115/home_back1.jpg" class="image"/>

    <div>
        <h4> -- 活动介绍 -- </h4>
    </div>

    <div id="desc">
        <p style="text-indent:2em">
            亲爱的小伙伴们，还记得过往我们一起跑过的日子吗？那些留在跑道上的汗水和点点滴滴的记忆。感谢一路有你，每一个在城市里穿行的悦跑人，不管跑过了多少时间，跑过了多少里程，每个人都跑出了一份属于自己的运动习惯和生活方式。这一年里所有励志的，开心的，惊喜的，难忘的，值得骄傲的，全都无所顾忌的跑并快乐着，只为遇见一个又一个更好的自己。我们跑过了四季，又迎来了新的一年，感谢悦跑新老队友一路的付出与努力，支持与相伴，使我们在悦跑的路上悦跑悦好，值此辞旧迎新之际，悦跑年度盛会因你而设，期待你的炫耀登场。
        </p>

        <br>
        <h5>盛会地点：</h5>
        <p>
            时间：2016年1月31日（17：00-23：00）<br>
            地点：华侨城洲国际大酒店 船吧（国内首家以白金五星级标准建造的西班牙主题酒店）<br>
            人物：喜欢跑步的你和他/她<br>
            形式：酒会.自助餐<br>
            内容：美酒、美食、乐队歌手表演、跑友分享、交友联谊<br>
            要求：正装出席，秀出最帅、最美的自己<br>

            活动费用：200/人<br>
            支付方式：微信支付<br>
            报名方式：点击年会报名链接直接报名：姓名+电话+支付，您可以邀请您的家人和朋友一起参加，报名成功后系统将第一时间给您回复确认信息<br>

            年会主旨：轻动运、悦生活，分享健康的生活方式<br>

            <br>
            <h5>温馨提示：</h5>
            1.为了更好的体验，我们会对对参加年会的人数进行限制，名额先到先得，以成功报名的数据为准。<br>
            2.我们为您提供了丰盛的美酒美食。<br>
            3.神秘嘉宾、新年礼物、幸运大抽奖等您分享。<br>
            4.悦跑群内的跑步大咖们现场华山论剑，分享交流跑步技巧和心得。<br>
            5.单身男女联谊派对让你现场邂逅意中人。<br>
            6.丰富多彩的互动游戏节目会让您度过一个难忘的夜晚。<br>
            7.<strong style="color: red;">每个手机号只能注册一个</strong>。<br>
            8.报名后可以点击 <a href="http://appt.igeekery.com/activity/20160115/input.htm">报名查询</a><br>
        </p>
</div>
    <%--<a href="http://mp.weixin.qq.com/s?__biz=MjM5MDk2ODA2MA==&mid=401226939&idx=1&sn=ea1a5a1655ee6716cf4bbf793f3902bc&scene=1&srcid=0115PGUxtiizivBTUWLXsQQo#rd"--%>
       <%--style="color: #ff3d25;">点击了解活动详情</a>--%>
    <%--<br>--%>
    <%--<a href="http://appt.igeekery.com/activity/20160115/input.htm" style="color: #ff3d25;">报名查询</a>--%>

    <div style="background: #eaeaea;line-height: 3;font-weight: bold;font-size: 15px;">
        -- 报名信息 --
    </div>

    <div id="panel-form">
        <form action="/activity/20160115/add.htm" method="post" isform>

            <div class="form-group">
                <input class="form-control input-lg" type="text" name="name" placeholder="姓名" notNull>
            </div>

            <div class="form-group">
                <select class="form-control input-lg" name="gender">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>

            <%--<div class="form-group">--%>
                <%--<input class="form-control input-lg" type="number" name="age" placeholder="跑龄" notNull>--%>
            <%--</div>--%>

            <div class="form-group">
                <input class="form-control input-lg" type="number" name="mobile" placeholder="手机号" notNull isMobile>
            </div>

            <div class="form-group">
                <input class="form-control input-lg" type="hidden" name="age" value="-1" placeholder="跑龄" notNull>
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                <button type="submit" class="btn btn-info form-control input-lg">马上参与</button>
            </div>
        </form>
    </div>

    <script type="text/javascript">
        // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
        var useragent = navigator.userAgent;
        if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
            // 这里警告框会阻塞当前页面继续加载
//        alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
            $("body").html('<label style="color: #60d295;font-size: 50px"> :-)</label> &nbsp;&nbsp;请用微信打开.');
            // 以下代码是用javascript强行关闭当前页面
//        var opened = window.open('about:blank', '_self');
//        opened.opener = null;
//        opened.close();
        }
    </script>

<script>

    $(function(){
        $("[isform]").submit(function(){
            var form = $(this);
            checkForm(form);

            if (!checkError(form) || !window.confirm("请核实信息，提交后不能修改。")) {
                return false;
            }

            if (checkError(form)) {
                form.find("[type=submit]").attr("disabled", "disabled").val("正在提交数据...");
                return true;
            }
            return false;
        });


        $("[isform] input").change(function(){
            checkForm($(this).parent());
        });


        function checkError(form) {
            return form.find("[errorEl]").size() == 0;
        }


        function checkForm(form) {
            form.find("[notNull]").each(function(index, element){
                var th = $(this);
                var val = th.val();
                if (/^\s*$/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>不能为空</label>');
                    }
                }
                else {
                    th.parent().removeClass("has-error");
                    th.parent().find("[errorEl]").remove();
                }
            });

            form.find("[isMobile]").each(function(index, element){
                var th = $(this);
                var val = th.val();

                if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>手机号不正确</label>');
                    }
                }
                else {
                    th.parent().removeClass("has-error");
                    th.parent().find("[errorEl]").remove();
                }
            })

            form.find("[userCard]").each(function(index, element){
                var th = $(this);
                var val = th.val();

                if (!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>身份证不正确</label>');
                    }
                }
                else {
                    th.parent().removeClass("has-error");
                    th.parent().find("[errorEl]").remove();
                }
            })

            form.find("[email]").each(function(index, element){
                var th = $(this);
                var val = th.val();

                if (!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>邮箱不正确</label>');
                    }
                }
                else {
                    th.parent().removeClass("has-error");
                    th.parent().find("[errorEl]").remove();
                }
            })
        }

        function appendError(th, val) {
            if (/^\s*$/.test(val)) {
                if (!th.parent().find("[errorEl]")[0]) {
                    th.parent().addClass("has-error");
                    th.parent().append('<label class="control-label" errorEl>不能为空</label>');
                }
            }
            else {
                th.parent().removeClass("has-error");
                th.parent().find("[errorEl]").remove();
            }
        }

    });

</script>

</body>
</html>


