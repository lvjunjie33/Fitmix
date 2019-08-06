<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/11
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
</style>

<div class="uk-form-row" >
    <span style="font-size: 18px;margin-right: 30px">${voice.title}</span><a href="#file-add" class="uk-button" data-uk-modal>添加文件</a>
    <br/><br/>
</div>

<div>
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <td colspan="9">CL-A-版本号、CL-I-版本号、A-版本号、I-版本号</td>
        </tr>
        <tr>
            <td>编号</td>
            <td>描述</td>
            <td>文件地址</td>
            <td>添加时间</td>
            <td>版本号</td>
            <td>支持的app版本号</td>
            <td>目录名称</td>
            <td>文件大小</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="voice" items="${page.result}">
            <tr>
                <td>${voice.id}</td>
                <td>${voice.des}</td>
                <td>${voice.fileLink}</td>
                <td><lch:parse-date time="${voice.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>${voice.other.version}</td>
                <td>${voice.other.targetApp}</td>
                <td>${voice.other.dirName}</td>
                <td>${voice.other.size}</td>
                <td><a href="/voice/packets/modify.htm?id=${voice.id}">修改</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/voice/packets/list.htm"></lch:page>

<jsp:include page="add.jsp"/>
