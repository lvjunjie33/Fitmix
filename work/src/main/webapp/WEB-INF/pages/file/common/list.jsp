<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/11
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
    .file{
        margin-bottom: 20px;;
    }
</style>

<div class="uk-form-row">
    <a href="#file-add" class="uk-button" data-uk-modal>添加文件</a>
</div>

<div>
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <td>名称</td>
            <td>描述</td>
            <td>文件地址</td>
            <td>添加时间</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="file" items="${page.result}">
            <tr>
                <td>${file.title}</td>
                <td>${file.des}</td>
                <td>${file.fileLink}</td>
                <td><lch:parse-date time="${file.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><a href="/file/modify.htm?id=${file.id}">修改</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<%--分页条--%>
<lch:page page="${page}" href="/file/list.htm"></lch:page>

<jsp:include page="./add.jsp"/>