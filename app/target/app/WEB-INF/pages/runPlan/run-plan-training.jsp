<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/7/20
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>

<html>
<head>
    <title>training</title>
    <jsp:include page="../common/training-path.jsp"/>
    <link rel="stylesheet" href="/static/jquery.mCustomScrollbar.css"/>
    <link rel="stylesheet" id="run_plan_theme">
</head>
<body>
<c:if test="${sessionScope.user.id == null || sessionScope.user.id == '' }"><c:redirect url="/web-user/login-page.htm"/></c:if>
<div id="bg" class="bg">
    <div class="filter"></div>
    <jsp:include page="../common/nav.jsp"/>
    <div id="run_plan_contain" class="run-plan-contain">
        <div class="title-bar">
            <div class="title-control-bar">
                <img id="vectoring" class="img-help" src="/static/run-plan/imgs/help.png">
                <button id="plan-cancel-button" class="plan-button">取消计划</button>
                <button class="plan-button" id="plan-delay-button">计划延期</button>
            </div>
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
                    <div class="title">总跑步距离</div>
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
                    <img class="arrow" src="/static/run-plan/imgs/arrow.png"/>
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
        <div id="stage_content" class="stage-content">
            <c:forEach items="${runPlan.stages}" var="type" varStatus="status">
                <div id="stage_content_${type.key+1}" class="run-plan-content">
                    <div class="<c:choose><c:when test="${fn:length(type.value) <= 7}">stage-name</c:when><c:when test="${fn:length(type.value) > 7}">stage-name-1</c:when></c:choose> stage-name-color-${type.key+1}">${type.value.get(0).stageName}</div>
                <c:forEach items="${type.value}" var="stage" varStatus="stages">
                    <c:set value="${ fn:split(stage.dateTime,'-') }" var="dateTime"></c:set>
                    <c:choose>
                        <c:when test="${stages.count%7 eq 0}">
                            <div name="run_plan_cell" class="run_plan_column_2 column_${type.key+1} <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
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
                            <div  class="run-plan-row<%--<c:choose><c:when test="${stages.first&&status.first}">run-plan-row-first</c:when><c:when test="${!stages.first||!status.first}">run-plan-row</c:when></c:choose>--%>">
                            <div name="run_plan_cell" class="run_plan_column_1 column_${type.key+1} <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
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
                            <c:if test="${stages.last}">
                                </div>
                            </c:if>
                        </c:when>
                        <c:when test="${stages.count%7 eq 2}">
                            <div name="run_plan_cell" class="run_plan_column_2 column_${type.key+1} <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
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
                            <c:if test="${stages.last}">
                                </div>
                            </c:if>
                        </c:when>
                        <c:when test="${stages.count%7 eq 3}">
                            <div name="run_plan_cell" class="run_plan_column_2 column_${type.key+1} <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
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
                            <c:if test="${stages.last}">
                                </div>
                            </c:if>
                        </c:when>
                        <c:when test="${stages.count%7 eq 4}">
                            <div name="run_plan_cell" class="run_plan_column_2 column_${type.key+1} <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
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
                            <c:if test="${stages.last}">
                                </div>
                            </c:if>
                        </c:when>
                        <c:when test="${stages.count%7 eq 5}">
                            <div name="run_plan_cell" class="run_plan_column_2 column_${type.key+1} <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
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
                            <c:if test="${stages.last}">
                                </div>
                            </c:if>
                        </c:when>
                        <c:when test="${stages.count%7 eq 6}">
                            <div name="run_plan_cell" class="run_plan_column_2 column_${type.key+1} <c:choose><c:when test="${(stage.date-runPlan.beginTime)<0}">state_past</c:when><c:when test="${(stage.date-runPlan.beginTime) eq 0}">state_now</c:when></c:choose>"
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
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
                            <c:if test="${stages.last}">
                                </div>
                            </c:if>
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
    <div id="cancel-plan" class="cancel-plan">
        <div class="plan-cancel">
            <img id="cancel-button" src="/static/run-plan/imgs/cancel.png">
        </div>
        <div class="cancel-plan-content">
            <h2>确定取消该计划?</h2>
            <button id="cancel-plan-true" class="uk-button uk-button-primary">是</button>
            <button id="cancel-plan-false" class="uk-button uk-button-primary">否</button>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
<script src="/static/jquery.mCustomScrollbar.concat.min.js"></script>
<script type='text/javascript'>
    (function ($) {
        $(window).on("load", function () {
            //初始化配置
            var stage = $(".stage");
            var id = stage.attr("name").split("_");
            $("#run_plan_theme").attr("href","/static/run-plan/css/theme_"+id[3]+".css");
            var arr = new Array();
            var i=0;


            //实现阶段切换(选项卡)
            $(".stage").each(function(){
                var id = $(this).attr("name").split("_");
                arr[i] = id[3];
                i++;
                $(this).hover(function(){
                    $(this).addClass("hover_"+id[3]);
                },function(){
                    $(this).removeClass("hover_"+id[3]);
                });
                $(this).click(function(){
                    $(".stage").each(function(){
                        var id = $(this).attr("name").split("_");
                        $(this).removeClass("stage_checked_"+id[3]);
                    });
                    var id = $(this).attr("name").split("_");
                    $(this).addClass("stage_checked_"+id[3]);
                    $("#stage_content").mCustomScrollbar("scrollTo","#stage_content_"+id[3]);
                    $("#run_plan_theme").attr("href","/static/run-plan/css/theme_"+id[3]+".css");
                });
            });

            if($("#stage_content_"+arr[arr.length-1]).height()<=532) {
                $("#stage_content_" + arr[arr.length - 1]).append("<div style='height: 300px;'><div>");
            }



            $("#stage_content").mCustomScrollbar({
                axis: "y",
                autoHideScrollbar:true,
                /* theme: "dark"*/
                callbacks: {
                    whileScrolling:function(){

                        var mcsTop = this.mcs.top;
                        for(var j=0;j<arr.length;j++){
                            mcsTop+=$("#stage_content_"+arr[j]).height();
                            if(mcsTop>0){
                                $("#run_plan_theme").attr("href","/static/run-plan/css/theme_"+arr[j]+".css");
                                $("#run_plan_stage_"+arr[j]).addClass("stage_checked_"+arr[j]);
                                $("#run_plan_stage_"+arr[j-1]).removeClass("stage_checked_"+arr[j-1]);
                                $("#run_plan_stage_"+arr[j+1]).removeClass("stage_checked_"+arr[j+1]);
                                break;
                            }
                        }
                    }
                },
            });

            //点击阶段内容跳转
            $(".run-plan-content").click(function(){
                var id = $(this).attr("id");
                $("#stage_content").mCustomScrollbar("scrollTo","#"+id);
            });

        });

        //打开计划取消界面
        $("#plan-cancel-button").click(function(){
            $("#cancel-plan").show();
        });

        //关闭取消计划界面
        $("#cancel-plan-false").click(function(){
           $("#cancel-plan").hide();
        });
        $("#cancel-button").click(function(){
            $("#cancel-plan").hide();
        });

        //打开计划延期操作界面
        $("#plan-delay-button").click(function(){
            $("#delay-plan").show();
        });

        //帮助界面
        $("#vectoring").click(function(){

        }).hover(function(){
                    $(this).attr("src","/static/run-plan/imgs/help_click.png");
                },
                function(){
                    $(this).attr("src","/static/run-plan/imgs/help.png");
                }
        ).mousedown(function(){
            $(this).attr("src","/static/run-plan/imgs/help_click.png");
        }).mouseup(function(){
            $(this).attr("src","/static/run-plan/imgs/help.png");
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