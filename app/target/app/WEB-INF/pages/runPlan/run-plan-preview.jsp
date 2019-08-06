<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/6/23
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>
    <title>preview</title>
    <jsp:include page="../common/run-plan-path.jsp"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <link rel="stylesheet" href="/static/jquery.mCustomScrollbar.css"/>
    <link rel="stylesheet" id="run_plan_theme">
</head>
<body style="overflow: auto">
<div id="bg" class="bg">
    <div class="filter"></div>
    <jsp:include page="../common/nav.jsp"/>
    <div id="run_plan_contain" class="run-plan-contain">
        <div class="title-bar">
            <img id="vectoring" class="img-help" src="/static/run-plan/imgs/help.png"/>
            <img id="cancel-button" class="img-cancel uk-modal-close" src="/static/run-plan/imgs/cancel.png"/>
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
                    <div class="content">${info[2]} </div>
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
                            <div name="run_plan_cell"
                                 class="run_plan_column_2"
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div><div class='tip-description'>${stage.contentDescription}</div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content"
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}
                                    <c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 1}">
                            <div class="run-plan-row">
                            <div class="run_plan_column_1 "
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div><div class='tip-description'>${stage.contentDescription}</div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content"
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}
                                    <c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if></div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 2}">
                            <div name="run_plan_cell"
                                 class="run_plan_column_2 column_${type.key+1}"
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div><div class='tip-description'>${stage.contentDescription}</div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content "
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}
                                    <c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 3}">
                            <div name="run_plan_cell"
                                 class="run_plan_column_2 column_${type.key+1} "
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div><div class='tip-description'>${stage.contentDescription}</div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content "
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}
                                    <c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 4}">
                            <div name="run_plan_cell"
                                 class="run_plan_column_2 column_${type.key+1} "
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div><div class='tip-description'>${stage.contentDescription}</div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content "
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}
                                    <c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 5}">
                            <div name="run_plan_cell"
                                 class="run_plan_column_2 column_${type.key+1} "
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div><div class='tip-description'>${stage.contentDescription}</div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content "
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}
                                    <c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${stages.count%7 eq 6}">
                            <div name="run_plan_cell"
                                 class="run_plan_column_2 column_${type.key+1} "
                                 data-uk-tooltip="{pos:'top',cls:'tool-tip-${type.key+1}'}"
                                 title="<div class='tips-contain'><div class='tdate'><div class='tip-month'>${dateTime[1]}月</div><div class='tip-date'>${dateTime[2]}</div></div><div class='tip-detail'><div class='<c:choose><c:when test="${stage.stageContent != '休息'}">tip-detail-content</c:when><c:when test="${stage.stageContent == '休息'}">tip-detail-content-1</c:when></c:choose>'>${stage.stageContent}</div><c:if test="${stage.stageContent != '休息'}"><div class='tip-range-time'>时间范围:${stage.stageTime}</div><div class='tip-range-pace'>配速范围:${stage.stageSpeed}分钟/公里</div></c:if></div><div class='tip-description'>${stage.contentDescription}</div></div>">
                                <div class="date" name="date">
                                        ${date.get(type.key).get(stages.index)}
                                </div>
                                <div class="run_plan_content "
                                     name="stageContent">
                                        ${stage.stageContent}
                                </div>
                                <div class="run_plan_speed" name="stageSpeed">
                                        ${stage.stageSpeed}
                                    <c:if test="${stage.stageContent != '休息'}">分钟/公里</c:if>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
                </div>
            </c:forEach>
        </div>
        <div class="uk-text-center run-plan-t">
            <button class="run-plan-submit uk-button uk-button-primary" id="save-plan">保存计划</button>
        </div>
    </div>
    <div id="help-content" class="help-content">
        <div id="help-1" class="help-1">
            <img src="/static/run-plan/imgs/tips.png">
            <label>下面是您的个人训练计划。基于我们七年的研究以及对数千名跑步者的调查，结合您的个人身体状态，此计划可以有效地帮助您达到训练目标。</label>
            <button>以后再说</button>
            <button id="help-button-1">逐项说明</button>
        </div>
        <div id="help-2" class="help-2">
            <img src="/static/run-plan/imgs/tips.png">
            <label> 该预计完成时间是根据您当前的跑步能力测算出来的。
                与您的预期不符？别担心。如果您的跑步速度比预计进步得快或慢，我们会根据您的情况更新预计完成时间并相应地调整训练计划。但是请不要忘记，追求更快的速度没有终点！</label>
            <button>以后再说</button>
            <button id="help-button-2">逐项说明</button>
        </div>
        <div id="help-3" class="help-3">
            <img src="/static/run-plan/imgs/tips.png">
            <label>您的训练计划共有5个阶段，每个阶段都能够帮助您应对 比赛 跑步对身体各方面的需求。
                预备阶段非常重要，您可以在此阶段让身体做好应对准备。这一阶段以慢跑为主，尽量适应这种速度，不要贸然加速。</label>
            <button>以后再说</button>
            <button id="help-button-3">逐项说明</button>
        </div>
        <div id="help-4" class="help-4">
            <img src="/static/run-plan/imgs/tips.png">
            <label>先从星期四的1公里慢跑开始。跑不了星期四？保存计划后，您可重新安排跑步时间。</label>
            <button id="help-button-4">结束帮助</button>
        </div>
    </div>
    <div id="recreate-plan" class="recreate-plan">
        <div class="plan-cancel">
            <img id="recreate-cancel" src="/static/run-plan/imgs/cancel.png">
        </div>
        <div class="recreate-title">
            <h3 class="recreate-title-head">重新制定</h3>
            <div class="recreate-title-label">
                <label>是否确定重新制定训练计划</label>
            </div>
        </div>
        <div class="check-recreate">
            <button id="recreate-button" class="check-recreate-button">确定</button>
            <button class="recreate-cancel-button">取消</button>
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
            $("#run_plan_theme").attr("href", "/static/run-plan/css/theme_" + id[3] + ".css");
            var arr = new Array();
            var i = 0;

            //实现阶段切换(选项卡)
            $(".stage").each(function () {
                var id = $(this).attr("name").split("_");
                arr[i] = id[3];
                i++;
                $(this).hover(function () {
                    $(this).addClass("hover_" + id[3]);
                }, function () {
                    $(this).removeClass("hover_" + id[3]);
                });
                $(this).click(function () {
                    $(".stage").each(function () {
                        var id = $(this).attr("name").split("_");
                        $(this).removeClass("stage_checked_" + id[3]);
                    });
                    var id = $(this).attr("name").split("_");
                    $(this).addClass("stage_checked_" + id[3]);
                    $("#stage_content").mCustomScrollbar("scrollTo", "#stage_content_" + id[3]);
                    $("#run_plan_theme").attr("href", "/static/run-plan/css/theme_" + id[3] + ".css");
                });
            });

            if ($("#stage_content_" + arr[arr.length - 1]).height() <= 532) {
                $("#stage_content_" + arr[arr.length - 1]).append("<div style='height: 300px;'><div>");
            }

            $("#stage_content").mCustomScrollbar({
                axis: "y",
                autoHideScrollbar: true,
                /* theme: "dark"*/
                callbacks: {
                    whileScrolling: function () {
                        $("#help-content").hide();
                        var mcsTop = this.mcs.top;
                        for (var j = 0; j < arr.length; j++) {
                            mcsTop += $("#stage_content_" + arr[j]).height();
                            if (mcsTop > 0) {
                                $("#run_plan_theme").attr("href", "/static/run-plan/css/theme_" + arr[j] + ".css");
                                $("#run_plan_stage_" + arr[j]).addClass("stage_checked_" + arr[j]);
                                $("#run_plan_stage_" + arr[j - 1]).removeClass("stage_checked_" + arr[j - 1]);
                                $("#run_plan_stage_" + arr[j + 1]).removeClass("stage_checked_" + arr[j + 1]);
                                break;
                            }
                        }
                    }
                },
            });

            //点击阶段内容跳转
            $(".run-plan-content").click(function () {
                var id = $(this).attr("id");
                $("#stage_content").mCustomScrollbar("scrollTo", "#" + id);
            });
        });

        //帮助界面
        $("#vectoring").click(function () {
            $("#help-content").show();
            $("#help-1").show();
            $("#help-2").hide();
            $("#help-3").hide();
            $("#help-4").hide();
        }).hover(function () {
                    $(this).attr("src", "/static/run-plan/imgs/help_click.png");
                },
                function () {
                    $(this).attr("src", "/static/run-plan/imgs/help.png");
                }
        ).mousedown(function () {
            $(this).attr("src", "/static/run-plan/imgs/help_click.png");
        }).mouseup(function () {
            $(this).attr("src", "/static/run-plan/imgs/help.png");
        });

        $("#cancel-button").click(function () {
            $("#recreate-plan").show();
        }).hover(function () {
                    $(this).attr("src", "/static/run-plan/imgs/cancel_click.png");
                },
                function () {
                    $(this).attr("src", "/static/run-plan/imgs/cancel.png");
                }
        ).mousedown(function () {
            $(this).attr("src", "/static/run-plan/imgs/cancel_click.png");
        }).mouseup(function () {
            $(this).attr("src", "/static/run-plan/imgs/cancel.png");
        });

        $(".plan-cancel").click(function(){
            $("#recreate-plan").hide();
        })

        $(".recreate-cancel-button").click(function(){
            $("#recreate-plan").hide();
        });

        $("#recreate-button").click(function(){
           window.location.href= '/run-plan/run-plan-presentation.htm';
        });

        $("#save-plan").click(function () {
            $.ajax({
                type: "POST",
                async: true,
                dataType: "json",
                url: "/run-plan/user-run-plan-save.json",
                data: {
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk
                },
                success: function (data) {
                    if (data.code == 0) {
                        alert("保存成功");
                        window.location.href = "/run-plan/present-plan.htm";
                    } else {
                        if(data.code == 2003) {
                            alert(data.msg);
                            window.location.href = "/web-user/login-page.htm?callback=/run-plan/turnToSave.htm";
                        } else {
                            alert(data.msg);
                        }
                    }
                }
            });
        })
    })(jQuery);
</script>
</body>
</html>
