<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/5/31
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">
    .uk-form-row{margin-top: 20px}
    .row-div-node{
        width: 700px;
        padding-left: 30px;
        padding-top: 10px;
    }
    .b-c-EFE8B7 {
        background-color: #EFE8B7;
    }
    .b-c-F9D0D0{
        background-color: #F9D0D0;
    }
    .b-c-659F13{
        background-color: #659F13;
    }
    .b-c-9A9191{
        background-color: #9A9191;
    }
    .b-c-76A3DC{
        background-color: #76A3DC;
    }
    .b-c-E792E8{
        background-color: #E792E8;
    }
    .b-c-E84A1B {
        background-color: #E84A1B;
    }
    .b-c-F9C0C0{
        background-color: #F9C0C0;
    }
    .c-CCB728{
        color: #CCB728;
    }
    .c-E00C0C{
        color: #E00C0C;
    }
    .c-3B9C1D{
        color: #3B9C1D;
    }
</style>

<form action="/scheduler/list.htm" method="get" class="uk-form">
    <div class="uk-form-row uk-grid">

        <div class="uk-form-row uk-width-1-6">
            <label>状态：</label>
            <select name="filter[status]">
                <option value="">请选择</option>
                <option value="0" <c:if test="${page.filter.status == 1}">selected</c:if>>关闭</option>
                <option value="1" <c:if test="${page.filter.status == 2}">selected</c:if>>开启</option>
            </select>
        </div>

        <div class="uk-form-row uk-width-2-10">
            <label>板块名</label>
            <input type="text" name="filter[title]" value="${page.filter.title}" placeholder="板块名"/>
        </div>

        <div class="uk-form-row uk-width-2-6">
            <button class="uk-button uk-button-primary">查询</button>&nbsp;&nbsp;&nbsp;
            <a href="#task-add" class="uk-button b-c-F9C0C0" data-uk-modal>添加</a>
        </div>
    </div>
</form>

<table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
    <thead>
    <tr>
        <th>操作</th>
        <th>编号</th>
        <th>板块名</th>
        <th>添加时间</th>
        <th>描述</th>
        <th>状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.result}" var="item">
        <tr>
            <td><a href="/scheduler/modify.htm?id=${item.id}" class="uk-button b-c-EFE8B7" >修改</a></td>
            <td>${item.id}</td>
            <td>${item.title}</td>
            <td><lch:parse-date time="${item.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${item.des}</td>
            <td>
                <c:choose>
                    <c:when test="${item.status == 0}"><strong class="c-3B9C1D">关闭</strong></c:when>
                    <c:when test="${item.status == 1}"><strong class="c-E00C0C">开启</strong></c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--分页条--%>
<lch:page page="${page}" href="/scheduler/list.htm"></lch:page>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<jsp:include page="add.jsp"/>