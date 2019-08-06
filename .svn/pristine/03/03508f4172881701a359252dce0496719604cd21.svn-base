<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/7/1
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>

<html>
<head>
    <title>训练计划</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <jsp:include page="../common/run-plan-presentation-path.jsp"/>

    <link rel="stylesheet" href="/static/jquery.mCustomScrollbar.css"/>
    <link rel="stylesheet" href="/static/uikit/css/uikit.min.css">
    <%--    <link type="text/css" rel="stylesheet" href="/static/fullpage/jquery.fullpage.min.css">--%>
    <link rel="stylesheet" href="/static/radio/css/jquery-labelauty.css">

    <!--[if !IE]>-->
    <link rel="stylesheet" media="only screen and (min-device-width: 768px) and (max-device-width: 1024px)"
          href="/static/run-plan/css/ipad-bdf2538d50ef722650f53bb866a1541b2ccbcf96a272b21602a0643c0e91fac6.css"/>
    <!--<![endif]-->

    <!--[if IE]>
    <link rel="stylesheet" media="screen"
          href="/static/run-plan/css/ie-4c2322fe7e0ac9277b56cdfb715b026c7ab4959a04a990959529f0f2276db5f6.css"/>
    <![endif]-->
    <!--[if IE 7 ]>
    <link rel="stylesheet" media="screen"
          href="/static/run-plan/css/ie7-deef2f30529e427e8f19271a915043f21696e777463b5bfc7843534e7c3ac243.css"/>
    <![endif]-->
    <!--[if IE 8 ]>
    <link rel="stylesheet" media="screen"
          href="/static/run-plan/css/ie8-3e4bc1dc4c0df726af5b93d6aa0aa86f07e6d8af6b12e78353f78387fcf4b8c2.css"/>
    <![endif]-->
    <!--[if IE 9 ]>
    <link rel="stylesheet" media="screen"
          href="/static/run-plan/css/ie9-8cfa92732184f4d638d4d992f1db47e149b1a4834aa7c425b97c8adba100df82.css"/>
    <![endif]-->


    <link rel="stylesheet" media="screen" href="/static/run-plan/css/facebox.css"/>
    <link rel="stylesheet" media="screen" href="/static/run-plan/css/dropdownbox.css"/>
    <link rel="stylesheet" media="screen" href="/static/run-plan/css/main.css"/>
    <link id="run_plan_theme" rel="stylesheet" href="/static/run-plan/css/theme_1.css">


    <style>
        /* Centered texts in each section
	    * --------------------------------------- */
        .section {
            text-align: center;
        }

        /* Backgrounds will cover all the section
        * --------------------------------------- */
        .section {
            background-size: cover;
        }

        /* Defining each section background and styles
	    * --------------------------------------- */
        #section0 {
            background-image: url(/static/run-plan/imgs/background.jpg);
            padding: 60px 0 200px 0;
            float: left;
            min-width: 100%;
        }

        #section1 {
            background-image: url(/static/run-plan/imgs/background2.jpg);
            padding: 60px 0 200px 0;
            float: left;
            min-width: 100%;
        }

        #section2 {
            background-image: url(/static/run-plan/imgs/background2.jpg);
            padding: 60px 0 200px 0;
            float: left;
            min-width: 100%;
        }

        #section3 {
            background-image: url(/static/run-plan/imgs/background3.jpg);
            padding: 60px 0 200px 0;
            float: left;
            min-width: 100%;
        }

        #fp-nav ul li a span, .fp-slidesNav ul li a span {
            background: #FFF !important;
        }

        h1 {
            color: #FFFFFF;
            font-size: 48px;
            line-height: 48px;
            margin-top: 50px;
            margin-bottom: 30px;
        }

        .section p {
            color: #FFFFFF;
            font-size: 22px;
        }

        .box {
            margin: 15%;
        }

        .box h1 {
            font-size: 96px !important;
            line-height: 96px;
            margin: 5px 0;
        }

        .box p {
            font-size: 24px;
            margin: 20px 0;
        }

        .plan-button {
            margin-top: 40px;
            background-color: #0cb4e8;
            color: #FFFFFF;
            font-size: 18px;
            border: none;
            border-radius: 30px;
            padding: 10px 110px;
            outline: none;
            cursor: pointer;
        }

        .create-plan-dialog {
            background-color: #201f26;
            opacity: 0.9 !important;
            text-align: center;
        }

        .create-plan-form select, .create-plan-form input {
            width: 280px;
            border-radius: 5px;
            background-color: transparent !important;
            color: #FFF;
            font-size: 18px;
            font-family: "Microsoft Yahei", "微软雅黑", "Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-weight: 400px;
            padding: 0;
            margin: 0;
            border-color: #8c8c8c;
        }

        .create-plan-label {
            color: #f2f2f2;
            font-size: 22px;
            font-family: "Microsoft Yahei", "微软雅黑", "Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
            margin-bottom: 15px !important;
        }

        .create-plan-box {
            width: 140px;
            display: inline-block;
            vertical-align: top;
        }

        .gender-label {
            margin-left:-1px;
        }

        .gender-bar {
            margin-left: -1px;
        }

        .age-bar {
            margin-right:-41px;
        }

        ul {
            list-style-type: none;
        }

        li {
            display: inline-block;
        }

        /*        li {
                    margin: 0 5px;
                }*/

        input.labelauty + label {
            font: 12px "Microsoft Yahei";
            padding: 5px !important;
        }

        .dowebok {
            padding: 0 0;
            text-align: center;
        }

        .run-plan-row > div {
            position: relative !important;
        }

        .h1-margin-top {
            margin-top: 100px !important;
        }

        .p-margin-top {
            margin-top: 26px !important;
        }

        @media (min-width: 1200px) {
            .training-plan {
                bottom: 0;
                left: 50%;
                margin-top: 100px;
                z-index: 0;
            }

            .training-plan img {
                width: 750px;
                height: auto;
            }
        }

        @media (min-width: 980px) and (max-width: 1200px) {
            .training-plan {
                position: absolute;
                bottom: 0;
                left: 50%;
                margin-left: -300px;
                z-index: 0;
            }

            .training-plan img {
                width: 600px;
                height: auto;
            }
        }

        .shake-resizable {
            animation: shake-resizable .5s 1;
        }

        .shake-man {
            animation: shake-man .5s 1;
        }

        .shake-noUi-handle {
            /*transition: top .05s;*/
            animation: bounceInDown 1s 1;
        }

        @keyframes shake-resizable {
            /*0% {width: 0px;}*/
            /*16% {width: 6px;}*/
            /*33% {width: 12px;}*/
            /*49% {width: 18px;}*/
            /*66% {width: 24px;}*/
            /*100% {width:28px;}*/
        }

        @keyframes shake-man {
            /*0% {left: 0%;}*/
            /*16% {left: 0.5172%;}*/
            /*33% {left: 1.0344%;}*/
            /*49% {left: 1.5516%;}*/
            /*66% {left: 2.0688%;}*/
            /*100% {left: 2.586%;}*/
        }

        @keyframes bounceInDown {
            0% {
                opacity: 0;
                -webkit-transform: translateY(-2000px);
                transform: translateY(-2000px)
            }
            60% {
                opacity: 1;
                -webkit-transform: translateY(-0px);
                transform: translateY(-0px)
            }
            80% {
                -webkit-transform: translateY(-30px);
                transform: translateY(-30px)
            }
            90% {
                -webkit-transform: translateY(-10px);
                transform: translateY(-10px)
            }
            100% {
                -webkit-transform: translateY(0);
                transform: translateY(0)
            }
        }

        @keyframes shake-noUi-handle {
            0% {
                top: -10em;
                opacity: 0.5;
            }
            10% {
                top: -8em;
                opacity: 1;
            }
            30% {
                top: -6em;
                opacity: 1;
            }
            40% {
                top: -4em;
                opacity: 1;
            }
            50% {
                top: -2em;
                opacity: 1;
            }
            60% {
                top: 0em;
                opacity: 1;
            }
            70% {
                top: -1em;
                opacity: 1;
            }
            80% {
                top: -4em;
                opacity: 1;
            }
            90% {
                top: -2em;
                opacity: 1;
            }
            100% {
                top: -1em;
                opacity: 1;
            }
        }

        .hp2014 td, .hp2014 th {
            color: #333;
        }

        .uk-dropdown {
            width: 250px;
        }

        .runDistance-tip {
            margin: 4px 0 0 142px;
            color: rgba(245, 68, 53, 1);
            font-size: 16px;
            width: 160px;
            background: url("/static/run-plan/imgs/tip.png") no-repeat scroll left center transparent;
        }

        .calendar-tip {
            margin: 4px 0 0 140px;
            color: rgba(245, 68, 53, 1);
            font-size: 16px;
            width: 130px;
            background: url("/static/run-plan/imgs/tip.png") no-repeat scroll left center transparent;
        }

        .setting-tip {
            margin: 4px 0 0 140px;
            color: rgba(245, 68, 53, 1);
            font-size: 16px;
            width: 223px;
            background: url("/static/run-plan/imgs/tip.png") no-repeat scroll left center transparent;
        }

        .age-tip {
            margin-top: 4px;
            color: rgba(245, 68, 53, 1);
            font-size: 16px;
            width: 180px;
            background: url("/static/run-plan/imgs/tip.png") no-repeat scroll left center transparent;
        }

        .age-tip:before {
            background: url("/static/run-plan/imgs/tip.png") no-repeat scroll left center transparent;
        }

        #turn_to_top {
            background-image: url(/static/run-plan/imgs/turn_to_top.png);
            width: 100px;
            height: 100px;
            background-repeat: no-repeat;
            right: 2%;
            bottom: 5%;
            position: fixed;
            float: right;
            z-index: 99999;
            display: none;
        }

        div#cb-compete {
            margin-left: 139px;
            margin-top: -14px;
            font-family: "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
            text-align: left;
            font-size: 15px;
        }

        input#for-event {
            width: 15px;
        }

        #compete-calender {
            margin-top: 12px;
        }

        .selectCalendar {
            margin:0 90px 0;
        }

        .calendar-content {

        }

        input#selectTime {
            border-radius: 5px;
        }

        .cb-label {
            font-size: 15px;
            font-family: "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
            text-align: left;
            margin-left: 50px;
            margin-top: 5px;
        }

        input#unchecked {
            width: 15px;
        }

        #targetTime {
            margin: 12px 90px 0;
        }

        #target-input {
            /*margin-left:42px;*/
        }

        #target-input input {
            width:24%;
            /*margin-left:4px;*/
        }

        #target-uncheck {
            margin-top: 5px;
            font-size: 15px;
            font-family: "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
            text-align: left;
            margin-left: 47px;
        }

        #target-uncheck input {
            width:15px;
        }

        /*.gender-radio {
            width:64px;
        }*/

        label.uk-form-label.create-plan-label.gender-label {
            margin-left: -19px;
        }

        ul.dowebok {
            margin-left: -35px;
        }

        .cal_comp {
            display: none;
        }

        .cal_inp {
            cursor: no-drop !important;
        }

    </style>

</head>
<body class="hp2014 " data-registration-source="homepage_2014" data-ui-locale="zh-CN" data-base-ui-locale="zh-CN"
      class="language_zh">
<jsp:include page="../common/nav.jsp"/>
<div id="fullpage">
    <div class="section " id="section0">
        <div class="box">
            <h1>训练计划</h1>
            <p>乐享动帮您跑得更有节奏</p>
            <button class="plan-button" data-uk-modal="{target:'#create-plan',center:true,bgclose:false}">创建计划</button>
        </div>
    </div>
    <div class="section" id="section1">
        <h1 class="h1-margin-top">提高训练质量</h1>
        <p class="p-margin-top">乐享动训练计划按照三大原则来提高你的训练质量</p>
        <p>配合激励音乐让你的训练更轻松</p>
        <div class="training-plan">
            <img src="/static/run-plan/imgs/background1_0.png">
        </div>
    </div>
    <div class="section" id="section2">
        <h1 class="h1-margin-top">训练案例</h1>
        <p class="p-margin-top">通过设定的目标，提供专业训练方案，训练方案分为6个阶段</p>
        <p>下图训练方案是一位准备在11月比赛马拉松的用户</p>

        <div class="container align-center" style="margin-top: 350px;">
            <div class="timeline-wrap">
                <div class="athlete kaori" data-athlete-name="kaori">
                    <div class="timeline-bg resizable" style="width: 30px;"></div>
                    <div class="timeline-bg transparent"></div>
                    <div class="timeline"></div>
                    <ul class="tooltips">
                        <li data-triggerat="0" class="active">11</li>
                        <li data-triggerat="16" class="hidden">6月</li>
                        <li data-triggerat="32" class="hidden">
                            <h5>七月耐力训练</h5>
                            <p>最初的指导速度开始跑步。在帮你感到舒适时适当提速，调整状态，进而再次提速并保持。控制好您的速度，避免因速度过快而体力不支，导致最终耗时增加。</p>
                        </li>
                        <li data-triggerat="48" class="hidden">8月</li>
                        <li data-triggerat="64" class="hidden">9月</li>
                        <li data-triggerat="80" class="hidden">10月</li>
                    </ul>

                    <div class="timeline-phase timeline-cal">
                        <div class="phase month june">
                            <p>五月</p>
                            <p>预备</p>
                        </div>
                        <div class="phase month june">
                            <p>六月</p>
                            <p>速度训练</p>
                        </div>
                        <div class="phase month june">
                            <p>七月</p>
                            <p>耐力训练</p>
                        </div>
                        <div class="phase month june">
                            <p>八月</p>
                            <p>测试训练</p>
                        </div>
                        <div class="phase month june">
                            <p>九月</p>
                            <p>备赛训练</p>
                        </div>
                        <div class="phase month june">
                            <p>十月</p>
                            <p>休息</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="section" id="section3">
        <h1 class="h1-margin-top">训练阶段</h1>
        <p class="p-margin-top">通过设定的目标，提供专业训练方案，训练方案分为6个阶段</p>
        <p>下图训练方案是一位准备在11月比赛马拉松的用户</p>
        <div id="run_plan_contain" class="run-plan-contain tab-wrap" style="height: 420px;">
            <div class="run-plan-stage" style="top: 5px;position: relative !important;z-index: 0;">
                <div id="run_plan_stage_1" name="run_plan_stage_1" class="stage stage-border stage_checked_1">
                    <span>1</span>
                    <img class="arrow" src="/static/run-plan/imgs/arrow.png"/>
                    <div class="stage_name">预备</div>
                </div>
                <div id="run_plan_stage_2" name="run_plan_stage_2" class="stage stage-border">
                    <span>2</span>
                    <img class="arrow" src="/static/run-plan/imgs/arrow.png"/>
                    <div class="stage_name">速度训练</div>
                </div>
                <div id="run_plan_stage_3" name="run_plan_stage_3" class="stage stage-border">
                    <span>3</span>
                    <img class="arrow" src="/static/run-plan/imgs/arrow.png"/>
                    <div class="stage_name">耐力训练</div>
                </div>
                <div id="run_plan_stage_4" name="run_plan_stage_4" class="stage stage-border">
                    <span>4</span>
                    <img class="arrow" src="/static/run-plan/imgs/arrow.png"/>
                    <div class="stage_name">测试训练</div>
                </div>
                <div id="run_plan_stage_5" name="run_plan_stage_5" class="stage stage-border">
                    <span>5</span>
                    <img class="arrow" src="/static/run-plan/imgs/arrow.png"/>
                    <div class="stage_name">备赛训练</div>
                </div>
                <div id="run_plan_stage_6" name="run_plan_stage_6" class="stage stage-border">
                    <span>6</span>
                    <img class="arrow" src="/static/run-plan/imgs/arrow.png"/>
                    <div class="stage_name">休息</div>
                </div>
            </div>
            <div class="run-plan-week week_color" style="top: 10px;position: relative !important;">
                <div class="week_1">星期一</div>
                <div class="week_2">星期二</div>
                <div class="week_2">星期三</div>
                <div class="week_2">星期四</div>
                <div class="week_2">星期五</div>
                <div class="week_2">星期六</div>
                <div class="week_2">星期日</div>
            </div>
            <div id="stage_content" style="position: relative !important;">
                <div id="stage_content_1" class="run-plan-content" style="top: 10px;overflow: visible;">
                    <div class="run-plan-row">
                        <div class="run_plan_column_1" data-run-type="rest">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="rest">
                            <div class="date">
                                28日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="rest">
                            <div class="date">
                                29日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="rest">
                            <div class="date">
                                30日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="jogging">
                            <div class="date">
                                7月1日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="rest">
                            <div class="date">
                                2日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="jogging">
                            <div class="date">
                                3日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                    <div class="run-plan-row">
                        <div class="run_plan_column_1" data-run-type="rest">
                            <div class="date">
                                4日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="jogging">
                            <div class="date">
                                5日
                            </div>
                            <div class="run_plan_content">
                                2公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="jogging">
                            <div class="date">
                                6日
                            </div>
                            <div class="run_plan_content">
                                2公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="rest">
                            <div class="date">
                                7日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="jogging">
                            <div class="date">
                                8日
                            </div>
                            <div class="run_plan_content">
                                2公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="rest">
                            <div class="date">
                                9日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2" data-run-type="jogging">
                            <div class="date">
                                10日
                            </div>
                            <div class="run_plan_content">
                                3公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                </div>
                <div id="stage_content_2" class="run-plan-content" style="display: none;top: 10px;">
                    <div class="run-plan-row">
                        <div class="run_plan_column_1 ">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                28日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                29日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                30日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 stage_now">
                            <div class="date">
                                7月1日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                2日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                3日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                    <div class="run-plan-row">
                        <div class="run_plan_column_1">
                            <div class="date">
                                4日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                5日
                            </div>
                            <div class="run_plan_content">
                                2公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                6日
                            </div>
                            <div class="run_plan_content">
                                2公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                7日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                8日
                            </div>
                            <div class="run_plan_content">
                                2公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                9日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                10日
                            </div>
                            <div class="run_plan_content">
                                3公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                </div>
                <div id="stage_content_3" class="run-plan-content" style="display: none;top: 10px;">
                    <div class="run-plan-row">
                        <div class="run_plan_column_1 ">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                28日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                29日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                30日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 stage_now">
                            <div class="date">
                                7月1日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                2日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                3日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                    <div class="run-plan-row">
                        <div class="run_plan_column_1 ">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                28日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                29日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                30日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 stage_now">
                            <div class="date">
                                7月1日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                2日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                3日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                </div>
                <div id="stage_content_4" class="run-plan-content" style="display: none;top: 10px;">
                    <div class="run-plan-row">
                        <div class="run_plan_column_1 ">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                28日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                29日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                30日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 stage_now">
                            <div class="date">
                                7月1日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                2日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                3日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                    <div class="run-plan-row">
                        <div class="run_plan_column_1 ">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                28日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                29日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                30日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 stage_now">
                            <div class="date">
                                7月1日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                2日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                3日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                </div>
                <div id="stage_content_5" class="run-plan-content" style="display: none;top: 10px;">
                    <div class="run-plan-row">
                        <div class="run_plan_column_1 ">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                28日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                29日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                30日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 stage_now">
                            <div class="date">
                                7月1日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                2日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                3日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                    <div class="run-plan-row">
                        <div class="run_plan_column_1 ">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                28日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                29日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                30日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 stage_now">
                            <div class="date">
                                7月1日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                2日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                3日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                </div>
                <div id="stage_content_6" class="run-plan-content" style="display: none;top: 10px;">
                    <div class="run-plan-row">
                        <div class="run_plan_column_1 ">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                28日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                29日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                30日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 stage_now">
                            <div class="date">
                                7月1日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                2日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                3日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                    <div class="run-plan-row">
                        <div class="run_plan_column_1 ">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                28日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                29日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 ">
                            <div class="date">
                                30日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2 stage_now">
                            <div class="date">
                                7月1日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                2日
                            </div>
                            <div class="run_plan_content">
                                休息
                            </div>
                            <div class="run_plan_speed"></div>
                        </div>
                        <div class="run_plan_column_2">
                            <div class="date">
                                3日
                            </div>
                            <div class="run_plan_content">
                                1公里慢跑
                            </div>
                            <div class="run_plan_speed">
                                9:07分钟/公里
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="turn_to_top"></div>
</div>
<!-- 创建计划 -->
<div id="create-plan" class="uk-modal " >
    <div class="uk-modal-dialog create-plan-dialog" style="top: 40px;">
        <a class="uk-modal-close uk-close"></a>
        <form class="uk-form uk-form-stacked create-plan-form" method="POST" action="/run-plan/create-plan.htm"
              enctype="multipart/form-data">
            <div class="uk-form-row">
                <label class="uk-form-label create-plan-label">设定跑步目标</label>
                <div>
                    <select id="runDistance" name="runDistance">
                        <option value="-1">选择跑步目标</option>
                        <option value="3">马拉松</option>
                        <option value="2">半程马拉松</option>
                        <option value="1">10公里</option>
                        <option value="0">5公里</option>
                    </select>
                    <div class="runDistance-tip"></div>
                </div>
            </div>

            <div class="uk-form-row">
                <div id="cb-compete">
                    <input type="checkbox" name="for-event" id="for-event"/>参加该比赛
                </div>
                <div class="cal_comp" id="compete-calender" >
                    <label class="uk-form-label create-plan-label">比赛开始时间</label>
                    <div class="uk-form-row selectCalendar">
                        <div class="uk-form-icon calendar-content">
                            <i class="uk-icon-calendar"></i>
                            <input id="selectTime" type="text" name="competeTime" placeholder="请选择时间"
                                   data-uk-datepicker="{minDate:0,format:'YYYY/MM/DD'}"
                                   readonly>
                        </div>
                        <div class="cb-label">
                            <input type="checkbox" name="unchecked" id="unchecked"/>不确定开始时间
                        </div>
                    </div>
                </div>
                <div id="targetTime" style="display: none;">
                    <label class="uk-form-label create-plan-label">目标用时</label>
                    <div>
                        <div id="target-input">
                            <input type="text" id="target-hour" name="target-hour" placeholder="时">
                            <input type="text" id="target-minute" name="target-minute" placeholder="分">
                            <input type="text" id="target-second" name="target-second" placeholder="秒">
                        </div>
                        <div id="target-uncheck">
                            <input type="checkbox" name="unchecked-target" id="unchecked-target"/>不明确
                        </div>
                    </div>
                </div>
            </div>

            <div class="uk-form-row" id="beginTimeSetting">
                <label class="uk-form-label create-plan-label">计划开始时间</label>
                <div class="uk-form-icon">
                    <i class="uk-icon-calendar"></i>
                    <input id="calendar" type="text" name="planTime" placeholder="计划开始时间"
                           data-uk-datepicker="{<%--minDate:0,--%>format:'YYYY/MM/DD'}"
                           readonly>
                </div>
                <div class="calendar-tip"></div>
            </div>

            <div class="uk-form-row">
                <label class="uk-form-label create-plan-label">目前能跑项目</label>
                <div class="uk-form-controls">
                    <select id="runProject" name="runProject">
                        <option value="perf_full">马拉松</option>
                        <option value="perf_half">半程马拉松</option>
                        <option value="perf_10k">10公里</option>
                        <option value="perf_5000m">5公里</option>
                        <option value="beginner_3">30-60分钟</option>
                        <option value="beginner_2">20-30分钟</option>
                        <option value="beginner_1">少于20分钟</option>
                    </select>
                </div>
            </div>

            <div class="uk-form-row" id="runTimeSetting">
                <label class="uk-form-label create-plan-label">项目所需时间</label>
                <div class="uk-form-controls">
                    <input type="text" id="hour" name="hour" style="width: 90px" placeholder="时">
                    <input type="text" id="minute" name="minute" style="width: 90px" placeholder="分">
                    <input type="text" id="second" name="second" style="width: 90px" placeholder="秒">
                    <div class="setting-tip"></div>
                </div>
            </div>

            <div class="uk-form-row">
                <div class="create-plan-box">
                    <label class="uk-form-label create-plan-label gender-label">性别</label>
                    <div class="uk-form-controls gender-bar">
                        <ul class="dowebok">
                            <li class="gender-radio"><input type="radio" name="gender" value="1" data-labelauty="男" checked="checked"></li>
                            <li class="gender-radio"><input type="radio" name="gender" value="2" data-labelauty="女"></li>
                        </ul>
                    </div>
                </div>
                <div class="create-plan-box">
                    <label class="uk-form-label create-plan-label">年龄</label>
                    <div class="uk-form-controls">
                        <input id="age" type="text" name="age" placeholder="年龄"
                               onkeyup="value=this.value.replace(/\D+/g,'')">
                        <div class="age-tip"></div>
                    </div>
                </div>
            </div>

            <button class="plan-button" id="parameter-submit" style="margin-top: 20px;">创建计划</button>

        </form>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>

<%--<script src="static/jquery-2.2.1.js"></script>--%>
<script src="/static/run-plan/js/head-f142d591b4096b092db1a94184efd150833b4a422ed705666873f964e459f8ef.js"></script>
<%--<script src="/static/fullpage/jquery.fullpage.min.js"></script>--%>
<script src="/static/uikit/js/uikit.min.js"></script>
<script src="/static/radio/js/jquery-labelauty.js"></script>
<script src="/static/run-plan/js/facebox.js"></script>
<script src="/static/run-plan/js/dropdownbox.js"></script>
<script src="/static/run-plan/js/application-eae978a482f7f9e28a445d736de8acf5c61f2de31c6effd0f3e63a9c9f1826aa.js"></script>
<script src="/static/run-plan/js/homepage_2014-d511e8ab13774252cc4af28f0df885d570d4bb2a3bc2e78a1ab4e50be107970b.js"></script>

</body>
</html>
