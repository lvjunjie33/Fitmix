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
        <a href="#admin-add" class="uk-button" data-uk-modal>添加</a>
    </div>
    <table class="uk-table uk-table-hover uk-table-striped">
        <caption>hha</caption>
        <thead>
            <tr>
                <th>编号</th>
                <th>账号</th>
                <th>名称</th>
                <th>密码</th>
                <th>状态</th>
                <th>登录次数</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="admin" items="${allAdmin}">
                <tr>
                    <td name="id">${admin.id}</td>
                    <td name="loginName">${admin.loginName}</td>
                    <td name="name">${admin.name}</td>
                    <td name="password">${admin.password}</td>
                    <td>
                        <c:choose>
                            <c:when test="${admin.activate == 1}">激活</c:when>
                            <c:when test="${admin.activate == 2}">未激活</c:when>
                        </c:choose>
                        <input type="hidden" name="activate" value="${admin.activate}"/>
                    </td>
                    <td  name="loginCount">${admin.loginCount}</td>
                    <td>
                        <input type="hidden" name="roles" value="${admin.roles}"/>
                        <a href="javascript:" admin-modify>修改</a>
                        <a href="javascript:" admin-role>role</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%--  add  --%>
<jsp:include page="admin-add.jsp"/>
<%--  modify  --%>
<jsp:include page="admin-modify.jsp"/>
<%--  role  --%>
<jsp:include page="admin-role.jsp"/>

