<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/12/8
  Time: 15:48
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

<form action="/renewals-server/manager.htm" method="get" class="uk-form">
    <div class="uk-form-row uk-grid">

        <div class="uk-form-row uk-width-2-10">
            <label>服务名</label>
            <input type="text" name="filter[name]" value="${page.filter.name}" placeholder="服务名"/>
        </div>

        <div class="uk-form-row uk-width-2-6">
            <button class="uk-button uk-button-primary">查询</button>&nbsp;&nbsp;&nbsp;
            <a href="#renewals-add" class="uk-button b-c-F9C0C0" data-uk-modal>添加</a>
        </div>
    </div>
</form>

<table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
    <thead>
    <tr>
        <th>服务名</th>
        <th>检测地址</th>
        <th>检测类型</th>
        <th>位置</th>
        <th>当前失败次数</th>
        <th>状态</th>

        <th>描述</th>
        <th>添加时间</th>
        <th>其他信息</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.result}" var="item">
        <tr>
            <td>${item.serverName}</td>
            <td>${item.requestLink}</td>
            <td>
                <c:choose>
                    <c:when test="${item.type == 0}">Web Server</c:when>
                    <c:when test="${item.type == 1}">Mongo DB</c:when>
                    <c:when test="${item.type == 2}">Redis</c:when>
                    <c:otherwise>三方服务</c:otherwise>
                </c:choose>
            </td>
            <td>${item.positionJsonStr}</td>
            <td>${item.timeOutNum}</td>
            <td>
                <c:choose>
                    <c:when test="${item.enabled == 1}">启用</c:when>
                    <c:otherwise>禁用</c:otherwise>
                </c:choose>
            </td>
            <td>${item.memo}</td>
            <td><lch:parse-date time="${item.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${item.paramJsonStr}</td>
            <td>
                <form action="/renewals-server/remove.json" method="post" class="uk-form" isform>

                    <a href="/target-server/modify.htm?id=${item.id}" target="_blank">修改</a>
                    <input type="hidden" name = "id" value="${item.id}" />
                    <c:choose>
                        <c:when test="${item.enabled == 1}">
                            <input type="hidden" name = "enabled" value="0" />
                            <button class="uk-button uk-button-primary" target="role-add-form">禁用</button>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name = "enabled" value="1" />
                            <button class="uk-button uk-button-primary" target="role-add-form">启用</button>
                        </c:otherwise>
                    </c:choose>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--分页条--%>
<lch:page page="${page}" href="/renewals-server/manager.htm"></lch:page>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
<jsp:include page="add.jsp"/>
