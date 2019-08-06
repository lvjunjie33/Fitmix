<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/10/11
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

    .short {
        width: 75px;
    }

    .time{
        width: 100px;
    }

</style>

<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action="/stat/total-user-run.htm" method="get" class="uk-form">
            <div class="uk-form-row uk-grid">
                <div class="uk-width-3-5">
                    <label>累计运动距离(m)：</label>
                    <label><input type="number" name="filter[minDistance]" value="${page.filter.minDistance}" placeholder="最小里程(m)" /></label>
                    <label><input type="number" name="filter[maxDistance]" value="${page.filter.maxDistance}" placeholder="最大里程(m)" /></label>
                    <button type="submit" class="uk-button uk-button-primary" style="margin-left: 30px">查询</button>
                    <label style="margin-left: 30px;color: red">查询结果：${page.total} 人</label>
                </div>
            </div>
        </form>

        <div class="uk-overflow-container">
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>名称</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>身高(cm)/体重(kg)</th>
                    <th>里程(m)</th>
                    <th>步数</th>
                    <th>卡路里</th>
                    <th>运动时长(分钟)</th>
                    <th>配速</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="stat" items="${page.result}">
                    <tr>
                        <td>${stat.id}</td>
                        <td><a href="/user/user-info.htm?uid=${stat.user.id}">${stat.user.name}</a></td>
                        <td><lch:dic-name type="1" value="${stat.user.gender}"/></td>
                        <td>${stat.user.age}</td>
                        <td>${stat.user.height}/${user.weight}</td>
                        <td>${stat.sumDistance}</td>
                        <td>${stat.sumStep}</td>
                        <td>${stat.sumCalorie}</td>
                        <td>${stat.runTime}</td>
                        <td>${stat.pace}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%--分页条--%>
    <lch:page page="${page}" href="/stat/total-user-run.htm"></lch:page>
</div>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
