<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/9
  Time: 上午11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <a href="#resource-add" class="uk-button" data-uk-modal>api Add</a>
    </div>
    <table class="uk-table uk-table-hover">
        <caption>API</caption>
        <thead>
            <tr>
                <th class="uk-width-1-10">编号</th>
                <th class="uk-width-2-10">名称</th>
                <th class="uk-width-2-10">url</th>
                <th class="uk-width-1-10">sort</th>
                <th class="uk-width-1-10">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="api" items="${apiList}">
                <tr>
                    <td name="id">${api.id}</td>
                    <td name="apiName">${api.apiName}</td>
                    <td name="url">${api.url}</td>
                    <td name="sort">${api.sort}</td>
                    <td>
                        <a href="javascript:" resource-modify>修改</a>
                        <input type="hidden" name="pid" value="${api.pid}"/>
                    </td>
                </tr>
                <c:forEach var="children" items="${api.children}">
                    <tr style="color:#999;">
                        <td name="id">&nbsp;&nbsp;${children.id}</td>
                        <td name="apiName">${children.apiName}</td>
                        <td name="url">${children.url}</td>
                        <td name="sort">${children.sort}</td>
                        <td>
                            <a href="javascript:" resource-modify>修改</a>
                            <input type="hidden" name="pid" value="${children.pid}"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>

</div>

<%--&lt;%&ndash;  add  &ndash;%&gt;--%>
<%--<jsp:include page="resource-add.jsp"/>--%>
<%--&lt;%&ndash;  modify  &ndash;%&gt;--%>
<%--<jsp:include page="resource-modify.jsp"/>--%>
<%--&lt;%&ndash;  operation add  &ndash;%&gt;--%>
<%--<jsp:include page="resource-operation-add.jsp"/>--%>
<%--&lt;%&ndash;  operation modify  &ndash;%&gt;--%>
<%--<jsp:include page="resource-operation-modify.jsp"/>--%>

