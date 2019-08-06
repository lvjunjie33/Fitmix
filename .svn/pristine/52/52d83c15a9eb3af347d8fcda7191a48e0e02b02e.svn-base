<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/6/23
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/run-plan-path.jsp" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/jquery.mCustomScrollbar.css" />
</head>
<body>
<%--<div class="uk-container uk-container-center">
    <p>
        <a href="#plan-modal" class="uk-button" data-uk-modal="">Oepn</a>
    </p>
</div>--%>
<%--<div id="plan-modal" class="uk-modal" style="display: none; overflow-y: auto;">--%>
    <div id="run_plan_contain" class="run-plan-contain">
        <%--   <button type="button" class="uk-modal-close uk-close"></button>--%>
        <div class="title-bar">
            <img id="vectoring" class="img-help" src="/static/run-plan/imgs/help.png">
            <img class="img-cancel uk-modal-close" src="/static/run-plan/imgs/cancel.png"/>
            <div class="run-plan-title">
                <div class="run-plan-title-item-1">
                    <div class="title">跑步项目</div>
                    <div class="content">马拉松</div>
                </div>
                <div class="run-plan-title-item-2">
                    <div class="title">预计完成时长</div>
                    <div class="content">05:10:23</div>
                </div>
                <div class="run-plan-title-item-2">
                    <div class="title">开始日期</div>
                    <div class="content">16/10/20</div>
                </div>
                <div class="run-plan-title-item-2">
                    <div class="title">比赛日期</div>
                    <div class="content">16/12/20</div>
                </div>
                <div class="run-plan-title-item-3">
                    <div class="title">跑步距离</div>
                    <div class="content">20公里</div>
                </div>
            </div>
        </div>
        <div class="vector_1" style="display: none;">
            <div class="vector_date"></div>
            <div class="vector_arrow"></div>
        </div>
        <div class="run-plan-stage">
                <div id="run_plan_stage_1" name="run_plan_stage_1" class="stage stage-border stage_checked_1">
                    <span>1</span>
                    <img class="arrow" src="/static/run-plan/imgs/arrow.png" />
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
        <div class="run-plan-week week_color">
                <div class="week_1">星期一</div>
                <div class="week_2">星期二</div>
                <div class="week_2">星期三</div>
                <div class="week_2">星期四</div>
                <div class="week_2">星期五</div>
                <div class="week_2">星期六</div>
                <div class="week_3">星期日</div>
            </div>
        <div id="stage_content">
            <div id="stage_content_1" class="run-plan-content">
                <div class="run-plan-row">
                        <div class="run_plan_column_1 state_past">
                            <div class="date">
                                27日
                            </div>
                            <div class="run_plan_content">
                                <s>休息</s>
                            </div>
                            <div class="tooltip">
                            </div>
                        </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            28日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            29日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            30日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
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
                    <div class="run_plan_column_3">
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
                    <div class="run_plan_column_3">
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
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            11日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            12日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            13日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            14日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            15日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            16日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            17日
                        </div>
                        <div class="run_plan_content">
                           5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            18日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            19日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            20日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            21日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            22日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            23日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            24日
                        </div>
                        <div class="run_plan_content">
                            7公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            25日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            26日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            27日
                        </div>
                        <div class="run_plan_content">
                            7公里定速跑
                        </div>
                        <div class="run_plan_speed">
                            7:17分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            28日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            29日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            30日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            31日
                        </div>
                        <div class="run_plan_content">
                            10公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
            </div>
            <div id="stage_content_2" class="run-plan-content" style="display: none;">
                <div class="run-plan-row">
                    <div class="run_plan_column_1 state_past">
                        <div class="date">
                            27日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            28日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            29日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            30日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
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
                    <div class="run_plan_column_3">
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
                    <div id="cell_7" class="run_plan_column_3">
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
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            11日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            12日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            13日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            14日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            15日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            16日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            17日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            11日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            12日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            13日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            14日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            15日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            16日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            17日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
            </div>
            <div id="stage_content_3" class="run-plan-content" style="display: none;">
                <div class="run-plan-row">
                    <div class="run_plan_column_1 state_past">
                        <div class="date">
                            27日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_4 state_past">
                        <div class="date">
                            28日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_4 state_past">
                        <div class="date">
                            29日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_4 state_past">
                        <div class="date">
                            30日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_4 stage_now">
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
                    <div class="run_plan_column_4">
                        <div class="date">
                            2日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_4">
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
            <div id="stage_content_4" class="run-plan-content" style="display: none;">
                <div class="run-plan-row">
                    <div class="run_plan_column_1 state_past">
                        <div class="date">
                            27日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            28日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            29日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            30日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
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
                    <div class="run_plan_column_3">
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
                    <div class="run_plan_column_3">
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
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            11日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            12日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            13日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            14日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            15日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            16日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            17日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            11日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            12日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            13日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            14日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            15日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            16日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            17日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
            </div>
            <div id="stage_content_5" class="run-plan-content" style="display: none;">
                <div class="run-plan-row">
                    <div class="run_plan_column_1 state_past">
                        <div class="date">
                            27日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            28日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            29日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            30日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
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
                    <div class="run_plan_column_3">
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
                    <div class="run_plan_column_3">
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
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            11日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            12日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            13日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            14日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            15日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            16日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            17日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            11日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            12日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            13日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            14日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            15日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            16日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            17日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
            </div>
            <div id="stage_content_6" class="run-plan-content" style="display: none;">
                <div class="run-plan-row">
                    <div class="run_plan_column_1 state_past">
                        <div class="date">
                            27日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            28日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            29日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2 state_past">
                        <div class="date">
                            30日
                        </div>
                        <div class="run_plan_content">
                            <s>休息</s>
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
                    <div class="run_plan_column_3">
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
                    <div class="run_plan_column_3">
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
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            11日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            12日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            13日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            14日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            15日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            16日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            17日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
                <div class="run-plan-row">
                    <div class="run_plan_column_1">
                        <div class="date">
                            11日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            12日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            13日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            14日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            15日
                        </div>
                        <div class="run_plan_content">
                            3公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                    <div class="run_plan_column_2">
                        <div class="date">
                            16日
                        </div>
                        <div class="run_plan_content">
                            休息
                        </div>
                        <div class="run_plan_speed"></div>
                    </div>
                    <div class="run_plan_column_3">
                        <div class="date">
                            17日
                        </div>
                        <div class="run_plan_content">
                            5公里慢跑
                        </div>
                        <div class="run_plan_speed">
                            9:07分钟/公里
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="uk-text-center run-plan-t">
            <button class="run-plan-submit uk-button uk-button-primary">保存计划</button>
        </div>
    </div>
<%--</div>--%>
<%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>--%>
<script src="/static/jquery.mCustomScrollbar.concat.min.js"></script>
<script type='text/javascript'>
    (function($){
        $(window).on("load",function(){
            $("#stage_content_1").mCustomScrollbar({
                    axis: "y",
                    /*theme: "dark"*/
            });
            $("#stage_content_2").mCustomScrollbar({
                axis:"y",
                /*theme: "dark"*/
            });
            $("#stage_content_3").mCustomScrollbar({
                axis:"y",
                /*theme: "dark"*/
            });
            $("#stage_content_4").mCustomScrollbar({
                axis:"y",
                /*theme: "dark"*/
            });
            $("#stage_content_5").mCustomScrollbar({
                axis:"y",
                /*theme: "dark"*/
            });
            $("#stage_content_6").mCustomScrollbar({
                axis:"y",
                /*theme: "dark"*/
            });
        });
    })(jQuery);
</script>
</body>
</html>
