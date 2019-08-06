<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/10
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>

<div class="training-progress">
    <div class="progress-content">
        <c:choose>
            <c:when test="${sessionScope.user_plan.plan_state != null }">
                <div class="training-content">
                    <label class="present-progress">
                        <div class="progress-item">${sessionScope.user_plan.complete_degree}%</div>
                        <div class="progress-btn">
                            <button id="detail-plan">详情</button>
                        </div>
                    </label>
                </div>
                <div class="training-data">
                    <label class="run-item">
                        <div class="item">
                            跑步项目
                        </div>
                        <div class="content">
                            ${sessionScope.info[0]}
                        </div>
                    </label>
                    <label class="predict-time">
                        <div class="item">
                            预计完成时长
                        </div>
                        <div class="content">
                            ${sessionScope.info[1]}
                        </div>
                    </label>
                    <label class="begin-date">
                        <div class="item">
                            开始日期
                        </div>
                        <div class="content">
                            ${sessionScope.info[2]}
                        </div>
                    </label>
                    <label class="competition-date">
                        <div class="item">
                            比赛日期
                        </div>
                        <div class="content">
                            ${sessionScope.info[3]}
                        </div>
                    </label>
                    <label class="run-distance">
                        <div class="item">
                            跑步距离
                        </div>
                        <div class="content">
                            ${sessionScope.info[4]}
                        </div>
                    </label>
                </div>
            </c:when>
            <c:when test="${sessionScope.user_plan.plan_state== null}">
                <div class="create-plan">
                    <div>当前暂无执行计划</div>
                    <div class="create-label">点击前往创建<a id="create-plan">跑步计划</a></div>
                </div>
            </c:when>
        </c:choose>
    </div>
</div>
