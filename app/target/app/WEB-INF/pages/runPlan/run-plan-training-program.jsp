<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/7/8
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<%@ include file="/WEB-INF/pages/common/run-plan-training-program-path.jsp" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/jquery.mCustomScrollbar.css"/>
    <link rel="stylesheet" id="run_plan_theme">
</head>
<body>
<div id="bg" class="bg">
    <div class="filter"></div>
    <div id="run_plan_contain" class="run-plan-contain">
        <div class="title-bar">
            <div class="title-control-bar">
                <img id="vectoring" class="img-help" src="/static/run-plan/imgs/help.png">
                <button class="plan-button">取消计划</button>
                <button class="plan-button" id="plan-delay-button">计划延期</button>
            </div>
            <%--<img class="img-cancel uk-modal-close" src="/static/run-plan/imgs/cancel.png"/>--%>
            <div class="run-plan-title">
                <div class="run-plan-title-item-1">
                    <div class="title">跑步项目</div>
                    <div class="content">${info[0]}</div>
                </div>
                <div class="run-plan-title-item-2">
                    <div class="title">预计完成时长</div>
                    <div class="content">${info[1]}</div>
                </div>
                <div class="run-plan-title-item-2">
                    <div class="title">开始日期</div>
                    <div class="content">${info[2]}</div>
                </div>
                <div class="run-plan-title-item-2">
                    <div class="title">比赛日期</div>
                    <div class="content">${info[3]}</div>
                </div>
                <div class="run-plan-title-item-3">
                    <div class="title">跑步距离</div>
                    <div class="content">${info[4]}</div>
                </div>
            </div>
        </div>
        <div class="vector_1" style="display: none;">
            <div class="vector_date"></div>
            <div class="vector_arrow"></div>
        </div>
        <div class="run-plan-stage">
            <c:forEach items="${runPlan.stages}" var="type" varStatus="status">
                <div id="run_plan_stage_${type.key+1}" name="run_plan_stage_${type.key+1}"
                     class="stage <c:choose><c:when test="${fn:length(runPlan.stages) eq 4}">stage-border-2${" "}</c:when><c:when test="${fn:length(runPlan.stages) eq 5}">stage-border-1${" "}</c:when><c:when test="${fn:length(runPlan.stages) eq 6}">stage-border${" "}</c:when></c:choose><c:if test="${status.first}">stage_checked_${type.key+1}</c:if>">
                    <span>${status.count}</span>
                    <img class="arrow" src="/static/run-plan/imgs/arrow.png"></img>
                    <div class="stage_name">${type.value.get(0).stageName}</div>
                </div>
            </c:forEach>
        </div>
        <div class="run-plan-week week_color">
            <div class="week_1">星期一</div>
            <div class="week_2">星期二</div>
            <div class="week_2">星期三</div>
            <div class="week_2">星期四</div>
            <div class="week_2">星期五</div>
            <div class="week_2">星期六</div>
            <div class="week_2">星期日</div>
        </div>
        <div id="stage_content">
            <c:forEach items="${runPlan.stages}" var="type" varStatus="status">
                <div id="stage_content_${type.key+1}" class="run-plan-content <c:if
                    test="${!status.first}">run-plan-content-hide</c:if>">
                <c:forEach items="${type.value}" var="stage" varStatus="stages">
                    <c:set value="${ fn:split(stage.dateTime,'-') }" var="dateTime"></c:set>
                    <c:choose>
                        <c:when test="${stages.count%7 eq 0}">
                            <div name="run_plan_cell" class="run_plan_column_2 <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'test'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content <c:if test="${(stage.date-runPlan.beginTime)<0}">strickout</c:if>"
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}<c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 1}">
                            <div  class="<c:choose><c:when test="${stages.count eq 1}">run-plan-row-first</c:when><c:when
                                test="${stages.count > 1}">run-plan-row</c:when></c:choose>">
                            <div name="run_plan_cell" class="run_plan_column_1 <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'test'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content <c:if test="${(stage.date-runPlan.beginTime)<0}">strickout</c:if>"
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}<c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 2}">
                            <div name="run_plan_cell" class="run_plan_column_2 <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'test'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content <c:if test="${(stage.date-runPlan.beginTime)<0}">strickout</c:if>"
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}<c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 3}">
                            <div name="run_plan_cell" class="run_plan_column_2 <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'test'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content <c:if test="${(stage.date-runPlan.beginTime)<0}">strickout</c:if>"
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}<c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 4}">
                            <div name="run_plan_cell" class="run_plan_column_2 <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'test'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content <c:if test="${(stage.date-runPlan.beginTime)<0}">strickout</c:if>"
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}<c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 5}">
                            <div name="run_plan_cell" class="run_plan_column_2 <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'test'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content <c:if test="${(stage.date-runPlan.beginTime)<0}">strickout</c:if>"
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}<c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 6}">
                            <div name="run_plan_cell" class="run_plan_column_2 <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'test'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content <c:if test="${(stage.date-runPlan.beginTime)<0}">strickout</c:if>"
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}<c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
                </div>
            </c:forEach>
        </div>
    </div>
    <div id="delay-plan" class="delay-plan">
        <div class="plan-cancel">
            <img id="delay-cancel" src="/static/run-plan/imgs/cancel.png">
        </div>
        <div class="delay-title">
            <h3 class="delay-title-head">计划延期</h3>
            <div class="delay-title-label">
                <label>延期操作只能作用用当前日期，延期天数超过总计划的1/3计划自动取消。若计划尚未开始则视为更改计划开始时间。</label>
            </div>
        </div>
        <div class="delay-control">
            <img id="delay-minus" class="delay-minus" src="/static/run-plan/imgs/minus.png"/>
            <div id="delay-day" class="delay-day">0</div>
            <img id="delay-plus" class="delay-plus" src="/static/run-plan/imgs/plus.png"/>
        </div>
        <div class="check-delay">
            <button id="delay-button" class="check-delay-button">确定延期</button>
        </div>
    </div>
</div>
<script src="/static/jquery.mCustomScrollbar.concat.min.js"></script>
<script type='text/javascript'>
    (function ($) {
        $(window).on("load", function () {
            $("#stage_content_1").mCustomScrollbar({
                axis: "y",
                /*/!*theme: "dark"*!/*/
            });
            $("#stage_content_2").mCustomScrollbar({
                axis: "y",
                /* /!*theme: "dark"*!/*/
            });
            $("#stage_content_3").mCustomScrollbar({
                axis: "y",
                /* /!*theme: "dark"*!/*/
            });
            $("#stage_content_4").mCustomScrollbar({
                axis: "y",
                /* /!*theme: "dark"*!/*/
            });
            $("#stage_content_5").mCustomScrollbar({
                axis: "y",
                /* /!*theme: "dark"*!/*/
            });
            $("#stage_content_6").mCustomScrollbar({
                axis: "y",
                /*/!*theme: "dark"*!/*/
            });
        });

        //打开计划延期操作界面
        $("#plan-delay-button").click(function(){
           $("#delay-plan").show();
        });

        //关闭计划延期界面及鼠标事件效果
        $("#delay-cancel").click(function(){
           $("#delay-plan").hide();
        }).hover(function(){
                $(this).attr("src","/static/run-plan/imgs/cancel_click.png");
            },
            function(){
                $(this).attr("src","/static/run-plan/imgs/cancel.png");
            }
        ).mousedown(function(){
            $(this).attr("src","/static/run-plan/imgs/cancel_click.png");
        }).mouseup(function(){
            $(this).attr("src","/static/run-plan/imgs/cancel.png");
        });

        //减少计划延期天数各效果
        $("#delay-minus").click(function () {
            if ($("#delay-day").html() > 0) {
                $("#delay-day").html(Number($("#delay-day").html()) - 1);
            }
        }).hover(function(){
                    $(this).attr("src","/static/run-plan/imgs/minus_click.png");
                },
                function(){
                    $(this).attr("src","/static/run-plan/imgs/minus.png");
                }
        ).mousedown(function(){
            $(this).attr("src","/static/run-plan/imgs/minus_click.png");
        }).mouseup(function(){
            $(this).attr("src","/static/run-plan/imgs/minus.png");
        });

        //增加计划延期天数各效果
        $("#delay-plus").click(function () {
            if ($("#delay-day").html() < 7) {
                $("#delay-day").html(Number($("#delay-day").html()) + 1);
            }
        }).hover(function(){
                    $(this).attr("src","/static/run-plan/imgs/plus_click.png");
                },
                function(){
                    $(this).attr("src","/static/run-plan/imgs/plus.png");
                }
        ).mousedown(function(){
            $(this).attr("src","/static/run-plan/imgs/plus_click.png");
        }).mouseup(function(){
            $(this).attr("src","/static/run-plan/imgs/plus.png");
        });
    })(jQuery);
</script>

</body>
</html>