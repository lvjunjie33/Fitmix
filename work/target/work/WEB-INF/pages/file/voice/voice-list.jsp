<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/14
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
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
            <td>添加时间</td>
            <td>标签</td>
            <td>icon</td>
            <td>封面图</td>
            <td>状态</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="voice" items="${voices}">
            <tr>
                <td><a href="/voice/detail.htm?id=${voice.id}">${voice.title}</a></td>
                <td>${voice.des}</td>
                <td><lch:parse-date time="${voice.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>${voice.tags}</td>
                <td>
                    <img width="50" src="<lch:build-path url='${voice.iconLink}'/>" />
                </td>
                <td>
                    <img width="50" src="<lch:build-path url='${voice.coverLink}'/>" />
                </td>
                <td>
                    <c:choose>
                        <c:when test="${voice.status eq 1}">
                            <label style="color:#6de1ff;">正常</label>
                        </c:when>
                        <c:when test="${voice.status eq 0}">
                            <label style="color:#ff405d;">无效</label>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="voice-add.jsp"/>
