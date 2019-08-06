<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/11
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
    .btn-style:hover {
        text-decoration:underline;
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
            <td>版本</td>
            <td>序列号</td>
            <td>序号</td>
            <td>添加时间</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="file" items="${page.result}">
            <tr <c:if test="${!empty file.other and !empty file.other.isNew}">style="background: #e8f7eb" </c:if> >

                <td>${file.title}</td>
                <td>${file.des}</td>
                <td>${file.fileLink}</td>
                <c:choose>
                    <c:when test="${!empty file.other}">
                        <td>${file.other.version}</td>
                        <td>${file.other.serial}</td>
                        <td>${file.other.number}</td>
                    </c:when>
                    <c:otherwise>
                        <td></td>
                        <td></td>
                        <td></td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <lch:parse-date time="${file.addTime}" pattern="yyyy-MM-dd HH:mm"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${file.status == 1}">有效</c:when>
                        <c:otherwise>无效</c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <a href="/handset/modify.htm?id=${file.id}">修改</a>
                    <c:if test="${empty file.other or empty file.other.isNew}">
                        <form action="/handset/set/new.json" method="post" class="uk-form"  isform style="display: inline;">
                            <input type="hidden" name="id" value="${file.id}">
                            <button class="uk-button uk-button-primary btn-style" target="role-add-form" style="background: #FFF;color: #3d87c5;border: none">置顶</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<%--分页条--%>
<lch:page page="${page}" href="/handset/list.htm"></lch:page>

<jsp:include page="./add.jsp"/>
