<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/1/5
  Time: 10:08
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

<form action="/notice/page.htm" method="get" class="uk-form">
    <div class="uk-form-row uk-grid">
        <div class="uk-width-1-5">
            <label>渠道：</label>
            <select name="filter[channel]">
                <option value=""> - 请选择 - </option>
                <option value="爱思" <c:if test="${page.filter.channel eq '爱思'}">selected</c:if>>爱思</option>
                <option value="苹果助手" <c:if test="${page.filter.channel eq '苹果助手'}">selected</c:if>>苹果助手</option>
                <option value="点入移动" <c:if test="${page.filter.channel eq '点入移动'}">selected</c:if>>点入移动</option>
            </select>
        </div>

        <div class="uk-width-2-5">
            <label>时间：</label>
            <label><input type="text" name="filter[beginTime]" value="${page.filter.beginTime}" placeholder="时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
            <%-----%>
            <%--<label><input type="text" name="filter[endTime]" value="${page.filter.endTime}" placeholder="结束时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>--%>
            <button type="submit" class="uk-button uk-button-primary">查询</button>
            <a href="/notice/export.htm?a=0<lch:build-page-filter page="${page}"/>" class="uk-button uk-button-primary">export</a>
        </div>
    </div>
</form>

<div class="uk-container-center uk-body">
    <table class="uk-table uk-table-hover uk-table-striped">
        <thead>
            <tr>
                <th>编号</th>
                <th>用户编号</th>
                <th>idfa</th>
                <th>渠道</th>
                <th>状态</th>
                <th>添加时间</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${page.result}">
            <tr>
                <td>${item.id}</td>
                <td>${item.uid}</td>
                <td>${item.idfa}</td>
                <td>${item.channel}</td>
                <td>${item.status}</td>
                <td><lch:parse-date time="${item.addTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--分页条--%>
    <lch:page page="${page}" href="/notice/page.htm"></lch:page>
</div>
