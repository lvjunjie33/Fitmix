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
        <a href="#dictionary-add" class="uk-button" data-uk-modal>添加</a>
        <a href="${refreshUrl}" target="_blank" class="uk-button" >刷新缓存</a>
    </div>
    <table class="uk-table uk-table-hover">
        <thead>
        <tr>
            <th style="width: 70px">是否下发</th>
            <th>id</th>
            <th>type</th>
            <th>value</th>
            <th>Name China</th>
            <th>Name English</th>
            <th>sort</th>
            <th>描述</th>
            <th class="uk-width-1-10">handing</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dic" items="${allDictionary}">
            <c:choose>
                <c:when test="${empty labelType || dic.type != labelType}">
                    <tr>
                        <td colspan="9">${dic.type}</td>
                    </tr>
                </c:when>
            </c:choose>
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${empty dic.appDisplay}">
                            <form action="/dic/modify/app/display.json" method="post" class="uk-form login-form" id="dictionary-modify-add-display-form" isform>
                                <input type="hidden" name="id" value="${dic.id}"/>
                                <input type="hidden" name="appDisplay" value="0" />
                                <button class="uk-button uk-button-primary" target="role-add-form">关闭</button>
                            </form>
                        </c:when>
                        <c:when test="${dic.appDisplay == 0}">
                            <form action="/dic/modify/app/display.json" method="post" class="uk-form login-form" id="dictionary-modify-add-display-form" isform>
                                <input type="hidden" name="id" value="${dic.id}"/>
                                <input type="hidden" name="appDisplay" value="1" />
                                <button class="uk-button uk-button-primary" target="role-add-form">开启</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="/dic/modify/app/display.json" method="post" class="uk-form login-form" id="dictionary-modify-add-display-form" isform>
                                <input type="hidden" name="id" value="${dic.id}"/>
                                <input type="hidden" name="appDisplay" value="0" />
                                <button class="uk-button uk-button-primary" target="role-add-form">关闭</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td name="id">&nbsp;&nbsp;&nbsp;&nbsp;${dic.id}</td>
                <td name="type">${dic.type}</td>
                <td name="value">${dic.value}</td>
                <td name="name">${dic.name}</td>
                <td name="nameEn">${dic.nameEn}</td>
                <td name="sort">${dic.sort}</td>
                <td name="des">${dic.des}</td>
                <td>
                    <a href="javascript:" dic-modify>修改</a>
                    <a href="javascript:" dic-remove>删除</a>
                </td>
            </tr>
            <c:set var="labelType" value="${dic.type}"/>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--  add  --%>
<jsp:include page="dictionary-add.jsp"/>
<%--  modify  --%>
<jsp:include page="dictionary-modify.jsp"/>

<script type="text/javascript">

    $(function () {
        $("[dic-remove]").click(function () {
            var th = $(this);
            if (window.confirm("确定删除?")) {
                var dataJson = th.parent().parent().sequenceJson();
                lch.post("/dic/remove-dictionary.json", {id : dataJson.id}, function (result, bool) {
                    if (bool) {
                        th.parent().parent().hide(100).remove();
                    }
                });
            }
        });
    });
</script>