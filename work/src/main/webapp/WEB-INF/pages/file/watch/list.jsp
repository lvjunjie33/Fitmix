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
    .l-50 {
        line-height: 50px;
    }
</style>

<div class="uk-form-row" style="margin-bottom: 20px;">
    <form action="/watch/list.htm" method="get" class="uk-form">
        <div class="uk-form-row">
            <label>类型</label>
            <select name="filter[fileType]">
                <option value="21" <c:if test="${page.filter.fileType eq 21 }">selected</c:if>>手表升级包</option>
                <option value="22" <c:if test="${page.filter.fileType eq 22 }">selected</c:if>>阿波罗升级包</option>
                <option value="23" <c:if test="${page.filter.fileType eq 23 }">selected</c:if>>手表心率升级包</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="uk-button uk-button-primary">查询</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#file-add" class="uk-button" data-uk-modal>添加升级包</a>
        </div>
    </form>
</div>

<div>
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <td>名称</td>
            <td>描述</td>
            <td>英文描述</td>
            <td>文件地址</td>
            <td>版本</td>
            <td>序列号</td>
            <td>外部版本号</td>
            <td>添加时间</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="file" items="${page.result}">
            <tr class="l-50" <c:if test="${!empty file.other and !empty file.other.isNew}">style="background: #e8f7eb" </c:if> >

                <td>${file.title}</td>
                <td>
                    <div style="overflow: auto;height: 50px; ">
                         ${file.des}
                    </div>
                </td>
                <td>
                    <div style="overflow: auto;height: 50px;">
                            ${file.des_en}
                    </div>
                </td>
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
                    <a href="/watch/modify.htm?id=${file.id}">修改</a>
                    <a href="/watch/delete.htm?id=${file.id}&filter[fileType]=${file.fileType}">删除</a>
                    <c:if test="${empty file.other or empty file.other.isNew}">
                        <form action="/watch/set/new.json" method="post" class="uk-form"  isform style="display: inline;">
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
<lch:page page="${page}" href="/watch/list.htm"></lch:page>

<jsp:include page="./add.jsp"/>
