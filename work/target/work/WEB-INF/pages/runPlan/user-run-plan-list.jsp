<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/12/26
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<html>
<head>
    <title>UserRunPlanList</title>
</head>
<body>
    <div class="uk-container-center uk-body">
        <div>
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                    <tr>
                        <th>编号</th>
                        <th>用户id</th>
                        <th>计划类型</th>
                        <th>类型名称</th>
                        <th>计划开始时间</th>
                        <th>比赛日期</th>
                        <th>计划结束时间</th>
                        <th>目标速度</th>
                        <th>总距离</th>
                        <th>总运动天数</th>
                        <th>完成度</th>
                        <th>目前执行阶段</th>
                        <th>目前执行位置</th>
                        <th>当前状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${page.result}">
                       <tr>
                           <td><a href="/run-plan/user-run-plan.json?id=${item.id}" target="_blank">${item.id}</a></td>
                           <td>${item.uid}</td>
                           <td>
                               <c:choose>
                                   <c:when test="${item.planType eq 1}">
                                       训练计划
                                   </c:when>
                                   <c:when test="${item.planType eq 2}">
                                       比赛计划
                                   </c:when>
                               </c:choose>
                           </td>
                           <td>
                               <c:choose>
                                   <c:when test="${item.type eq 0}">
                                       5km
                                   </c:when>
                                   <c:when test="${item.type eq 1}">
                                       10km
                                   </c:when>
                                   <c:when test="${item.type eq 2}">
                                       半马
                                   </c:when>
                                   <c:when test="${item.type eq 3}">
                                       全马
                                   </c:when>
                               </c:choose>
                           </td>
                           <td><lch:parse-date time="${item.planTime}" pattern="yyyy-MM-dd"/></td>
                           <td><lch:parse-date time="${item.competitionTime}" pattern="yyyy-MM-dd"/></td>
                           <td><lch:parse-date time="${item.endTime}" pattern="yyyy-MM-dd"/></td>
                           <td>${item.resultTime}</td>
                           <td>${item.distance}</td>
                           <td>${item.runDay}</td>
                           <td>${item.complete_degree}</td>
                           <td>${item.executeStage}</td>
                           <td>${item.executeNo}</td>
                           <td>
                               <c:choose>
                                   <c:when test="${item.plan_state eq 1}">
                                       执行中
                                   </c:when>
                                   <c:when test="${item.plan_state eq 2}">
                                       结束
                                   </c:when>
                               </c:choose>
                           </td>
                           <td>
                               <a href="/run-plan/modify/status.htm?id=${item.id}&status=2">完成计划</a>
                               <a href="/run-plan/modify/status.htm?id=${item.id}&status=0">废弃计划</a>
                           </td>
                       </tr>
                    </c:forEach>
                </tbody>
            </table>
            <%--分页条--%>
            <lch:page page="${page}" href="/run-plan/list.htm"></lch:page>
        </div>
    </div>
</body>
</html>
