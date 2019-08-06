<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/12/29
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<div class="uk-container-center uk-body">

    <form action="/user/run/rank.htm" method="get" class="uk-form">
        <div class="uk-form-row">
            <div class="uk-form-row">
                <label>类型</label>
                <select name="filter[type]">
                    <option value="1" <c:if test="${page.filter.type == 1 }">selected</c:if>>全部</option>
                    <option value="2" <c:if test="${page.filter.type == 2 }">selected</c:if>>年</option>
                    <option value="3" <c:if test="${page.filter.type == 3 }">selected</c:if>>月</option>
                    <option value="4" <c:if test="${page.filter.type == 4 }">selected</c:if>>周</option>
                </select>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <label>榜单</label>
                <select name="filter[level]">
                    <option value="1" <c:if test="${page.filter.level == 1 }">selected</c:if>>青铜</option>
                    <option value="2" <c:if test="${page.filter.level == 2 }">selected</c:if>>白银</option>
                    <option value="3" <c:if test="${page.filter.level == 3 }">selected</c:if>>黄金</option>
                    <option value="4" <c:if test="${page.filter.level == 4 }">selected</c:if>>铂金</option>
                    <option value="5" <c:if test="${page.filter.level == 5 }">selected</c:if>>钻石</option>
                </select>

                &nbsp;&nbsp;&nbsp;&nbsp;

                <label>时间：</label>
                <label><input type="text" name="filter[time]" value="${page.filter.time}" placeholder="统计日期" data-uk-datepicker="{format:'YYYY-MM-DD'}" notnull/></label>

                &nbsp;&nbsp;&nbsp;&nbsp;
                <button class="uk-button uk-button-primary">查询</button>
            </div>
        </div>
    </form>

    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
        <thead>
        <tr>
            <th>排名</th>
            <th>级别</th>

            <th>头像</th>
            <th>名称 / 编号</th>

            <th>里程(米)</th>
            <th>运动时间(毫秒)</th>
            <th>步数</th>
            <th>卡路里</th>
            <th>配速</th>
            <th>运动次数</th>
            <th>运动天数</th>
            <th>统计日期</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="stat" items="${page.result}">
            <tr>
                <td>${stat.rank}</td>
                <td>${stat.level}</td>

                <td>
                    <img src="${stat.user.avatar}" class="uk-icon-button" style="width: 50px;height: 50px;"/>
                </td>
                <td>${stat.user.name} / ${stat.user.id}</td>

                <td>${stat.sumDistanceValid}</td>
                <td>${stat.runTimeValid}</td>
                <td>${stat.sumStepValid}</td>
                <td>${stat.sumCalorieValid}</td>

                <td>${stat.pace}</td>
                <td>${stat.runNumValid}</td>
                <td>${stat.runDayValid}</td>
                <td>${stat.statTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>