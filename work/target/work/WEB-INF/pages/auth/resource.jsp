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
        <a href="#resource-add" class="uk-button" data-uk-modal>Menu Add</a>
        <a href="#resource-operation-add" class="uk-button" data-uk-modal>Operation Add</a>
    </div>
    <table class="uk-table uk-table-hover">
        <caption>菜单</caption>
        <thead>
            <tr>
                <th class="uk-width-1-10">编号</th>
                <th class="uk-width-2-10">名称</th>
                <th class="uk-width-2-10">扩展</th>
                <th class="uk-width-2-10">url</th>
                <th class="uk-width-1-10">sort</th>
                <th class="uk-width-1-10">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="mapResource" items="${menuResourceWithRootMenuMap}">
                <c:set var="parentResource" value="${mapResource.key}"/>
                <tr>
                    <td name="id">${parentResource.id}</td>
                    <td name="name">${parentResource.name}</td>
                    <td name="extensionName">${parentResource.extensionName}</td>
                    <td name="handling">${parentResource.handling}</td>
                    <td name="sort">${parentResource.sort}</td>
                    <td>
                        <a href="javascript:" resource-modify>修改</a>
                        <input type="hidden" name="pid" value="${parentResource.pid}"/>
                        <input type="hidden" name="type" value="${parentResource.type}"/>
                    </td>
                </tr>
                <c:forEach var="resource" items="${mapResource.value}">
                    <tr style="color:#999;">
                        <td name="id">&nbsp;&nbsp;${resource.id}</td>
                        <td name="name">${resource.name}</td>
                        <td name="extensionName">${resource.extensionName}</td>
                        <td name="handling">${resource.handling}</td>
                        <td name="sort">${resource.sort}</td>
                        <td>
                            <a href="javascript:" resource-modify>修改</a>
                            <input type="hidden" name="pid" value="${resource.pid}"/>
                            <input type="hidden" name="type" value="${resource.type}"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>

    <table class="uk-table uk-table-hover">
        <caption>操作</caption>
        <thead>
        <tr>
            <th class="uk-width-1-10">编号</th>
            <th class="uk-width-2-10">名称</th>
            <th class="uk-width-2-10">扩展</th>
            <th class="uk-width-2-10">url</th>
            <th class="uk-width-1-10">sort</th>
            <th class="uk-width-1-10">操作</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="operationMap" items="${operationResourceWithRootMenuMap}">
                <c:set var="operationKey" value="${operationMap.key}"/>
                <tr>
                    <td name="id">${operationKey.id}</td>
                    <td name="name">${operationKey.name}</td>
                    <td name="extensionName">${operationKey.extensionName}</td>
                    <td name="handling">${operationKey.handling}</td>
                    <td name="sort">${operationKey.sort}</td>
                    <td>
                        <a href="javascript:" operation-modify>修改</a>
                        <input type="hidden" name="pid" value="${operationKey.pid}"/>
                        <input type="hidden" name="type" value="${operationKey.type}"/>
                    </td>
                </tr>
                <c:forEach var="resource" items="${operationMap.value}">
                    <tr style="color:#999;">
                        <td name="id">&nbsp;&nbsp;${resource.id}</td>
                        <td name="name">${resource.name}</td>
                        <td name="extensionName">${resource.extensionName}</td>
                        <td name="handling">${resource.handling}</td>
                        <td name="sort">${resource.sort}</td>
                        <td>
                            <a href="javascript:" operation-modify>修改</a>
                            <input type="hidden" name="pid" value="${resource.pid}"/>
                            <input type="hidden" name="type" value="${resource.type}"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>
</div>

<%--  add  --%>
<jsp:include page="resource-add.jsp"/>
<%--  modify  --%>
<jsp:include page="resource-modify.jsp"/>
<%--  operation add  --%>
<jsp:include page="resource-operation-add.jsp"/>
<%--  operation modify  --%>
<jsp:include page="resource-operation-modify.jsp"/>

