<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action="/task/statistics.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>时间：</label>
                    <label class="mix-scene"><input type="text" name="startTime" value="${startTime}" data-uk-datepicker="{format:'YYYY-MM-DD'}" placeholder="开始时间"></label>
                    <label>至</label>
                    <label class="mix-scene"><input type="text" name="endTime" value="${endTime}" data-uk-datepicker="{format:'YYYY-MM-DD'}" placeholder="结束时间"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                </div>
            </div>
        </form>
    </div>
    <hr class="uk-article-divider">

    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr>
                <th style="width: 50px;">任务描述</th>
                <th style="width: 100px;">领取人数</th>
                <th style="width: 100px">总计金币</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="taskDetailsInfo" items="${list}">
                <tr>
                    <td><label name="id">${taskDetailsInfo.taskInfo.description}</label></td>
                    <td>${taskDetailsInfo.finishCount}</td>
                    <td>${taskDetailsInfo.finishCount * taskDetailsInfo.taskInfo.coin}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


</div>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<%--  sort  --%>