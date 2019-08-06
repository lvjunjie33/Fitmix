<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/23
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<a href="#code-add" class="uk-button" data-uk-modal>添加</a>


<table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
    <thead>
        <tr>
            <th>编号</th>
            <th>sys</th>
            <th>code</th>
            <th>ch message</th>
            <th>en message</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${codeMessage}">
            <tr>
                <td name="id">${item.id}</td>
                <td name="sys">${item.sys}</td>
                <td name="code">${item.code}</td>
                <td name="msgChina">${item.msgChina}</td>
                <td name="msgEnglish">${item.msgEnglish}</td>
                <td name="memo">${item.memo}</td>
                <td>
                    <a href="#code-add" style="color:#ff4559;" data-uk-modal>删除</a>
                    <a href="/code-message/modify.htm?id=${item.id}" style="color:#a2b3ff;" click-modify>修改</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%--  add  --%>
<jsp:include page="add.jsp"/>

<%--  modify  --%>
<%--<jsp:include page="modify.jsp"/>--%>
