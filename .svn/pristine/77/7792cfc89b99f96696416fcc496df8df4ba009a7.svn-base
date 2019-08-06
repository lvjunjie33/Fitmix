<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/11
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="history-plan">
    <div class="histroy-plan-content">
        <h4>历史计划</h4>
        <div class="plan-item">
            <div class="plan-item-head">
                <label class="time-item-label">时间</label>
                <label class="project-item-label">跑步项目</label>
                <label class="date-item-label">比赛日期</label>
                <label class="distance-item-label">跑步距离</label>
                <label class="degree-item-label">完成度</label>
            </div>
            <%--
            <div class="plan-item-content">
                <label class="time-item">16/10/20</label>
                <label class="project-item">马拉松</label>
                <label class="tag-item">16/12/20</label>
                <label class="distance-item">233公里</label>
                <label class="degree-item">100%</label>
                <div class="plan-item-btn">
                    <button>删除</button>
                </div>
            </div>
            <div class="plan-item-content">
                <label class="time-item">16/10/20</label>
                <label class="project-item">半程马拉松</label>
                <label class="tag-item">16/12/20</label>
                <label class="distance-item">233公里</label>
                <label class="degree-item">100%</label>
                <div class="plan-item-btn">
                    <button>删除</button>
                </div>
            </div>
            <div class="plan-item-content">
                <label class="time-item">16/10/20</label>
                <label class="project-item">10公里</label>
                <label class="tag-item">16/12/20</label>
                <label class="distance-item">233公里</label>
                <label class="degree-item">100%</label>
                <div class="plan-item-btn">
                    <button>删除</button>
                </div>
            </div>
            <div class="plan-item-content">
                <label class="time-item">16/10/20</label>
                <label class="project-item">5公里</label>
                <label class="tag-item">16/12/20</label>
                <label class="distance-item">233公里</label>
                <label class="degree-item">100%</label>
                <div class="plan-item-btn">
                    <button>删除</button>
                </div>
            </div>--%>
            <c:forEach var="stat" items="${sessionScope.user_history_plan}">
                <div class="plan-item-content">
                    <label class="time-item">16/10/20</label>
                    <label class="project-item">
                        <c:choose>
                            <c:when test="${stat.type == 0}">
                                5公里
                            </c:when>
                            <c:when test="${stat.type == 1}">
                                10公里
                            </c:when>
                            <c:when test="${stat.type == 2}">
                                半程马拉松
                            </c:when>
                            <c:when test="${stat.type == 3}">
                                马拉松
                            </c:when>
                        </c:choose>
                    </label>
                    <label class="date-item">16/12/20</label>
                    <label class="distance-item">233公里</label>
                    <label class="degree-item">100%</label>
                    <div class="plan-item-btn">
                        <button>删除</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>