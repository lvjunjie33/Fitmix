<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/6/15
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/6/15
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>

    #activity-list{
        margin-top: 10px;
    }

</style>

<div id="activity-list">
    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/log/stat/ip/user.htm" method="post" class="uk-form">
            <div class="uk-form-row">
                <label>查询方式：</label>
                <label>
                    <select name="type">
                        <option value="1" <c:if test="${empty type || type == 1}">selected</c:if>>本日</option>
                        <option value="2" <c:if test="${type == 2}">selected</c:if>>本周</option>
                        <option value="3" <c:if test="${type == 3}">selected</c:if>>本月</option>
                    </select>
                </label>
                <label>link：</label>
                <label><input type="text" name="ip" value="${ip}" placeholder="101.101.101.101"></label>
                <button type="submit" class="uk-button uk-button-primary">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
        </form>
    </div>

    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr><th colspan="2">ip：${ip}</th></tr>
            <tr>
                <th style="width: 50px;">用户</th>
                <th style="width: 50px">ip访问次数</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.key}</td>
                    <td>${user.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

