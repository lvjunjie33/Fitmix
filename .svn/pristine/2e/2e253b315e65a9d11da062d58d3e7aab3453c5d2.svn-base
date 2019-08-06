<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/11/27
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<div class="uk-container-center uk-body">

    <form action="/user/run/time/stat.htm" method="get" class="uk-form">
        <div class="uk-form-row">

            <div class="uk-form-row">
                <label>类型</label>
                <select name="filter[type]">
                    <option value="1" <c:if test="${page.filter.type eq 1 }">selected</c:if>>全部</option>
                    <option value="2" <c:if test="${page.filter.type eq 2 }">selected</c:if>>年</option>
                    <option value="3" <c:if test="${page.filter.type eq 3 }">selected</c:if>>月</option>
                    <option value="4" <c:if test="${page.filter.type eq 4 }">selected</c:if>>周</option>
                </select>
            </div>

            <div class="uk-form-row">
                <input type="hidden" name="filter[uid]" value="${page.filter.uid}" />
                <button class="uk-button uk-button-primary">查询</button>
            </div>
        </div>
    </form>

    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
        <thead>
        <tr>
            <th>编号</th>
            <th>步数</th>
            <th>里程(米)</th>
            <th>卡路里(卡)</th>
            <th>运动次数</th>
            <th>运动天数</th>
            <th>运动时间(秒)</th>
            <th>统计时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="stat" items="${page.result}">
            <tr>
                <td>${stat.id}</td>
                <td>${stat.sumStep}</td>
                <td>${stat.sumDistance}</td>
                <td>${stat.sumCalorie}</td>
                <td>${stat.runNum}</td>
                <td>${stat.runDay}</td>
                <td>${stat.runTime / 1000}</td>
                <td>${stat.statTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<%--分页条--%>
<lch:page page="${page}" href="/user/run/time/stat.htm"></lch:page>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
