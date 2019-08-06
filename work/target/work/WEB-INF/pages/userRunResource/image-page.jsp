<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/1/15
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
    .image{
        width: 200px;
    }

    td{
        line-height: 10;
    }
</style>

<div class="uk-form-row">
    <%--<a href="#resource-content-add" class="uk-button" data-uk-modal>添加 内容</a>--%>
    <a href="#resource-image-add" class="uk-button" data-uk-modal>添加 图片</a>
</div>

<div class="uk-form-row">
    <ul class="uk-tab">
        <li><a href="/user-run-share-resource/page/content.htm">内容</a></li>
        <li class="uk-active"><a href="/user-run-share-resource/page/image.htm">图片</a></li>
    </ul>
</div>


<table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
    <thead>
    <tr>
        <th>编号</th>
        <th>图片</th>
        <th>状态</th>
        <th>添加时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${page.result}">
        <tr>
            <td name="id">${item.id}</td>
            <td>
                <img src="<lch:build-path url="${item.imageUrl}"/>" class="image"/>
            </td>
            <td>
                <c:choose>
                    <c:when test="${item.status eq 0}">
                        上架
                    </c:when>
                    <c:when test="${item.status eq 1}">
                        下架
                    </c:when>
                </c:choose>
            </td>
            <td><lch:parse-date time="${item.addTime}" pattern="yyyy-MM-dd"/></td>
            <td>
                <c:choose>
                    <c:when test="${item.status eq 0}">
                        <a href="javascript:" data="/user-run-share-resource/modify-status.json?id=${item.id}&status=1" down>下架</a>
                    </c:when>
                    <c:when test="${item.status eq 1}">
                        <a href="javascript:" data="/user-run-share-resource/modify-status.json?id=${item.id}&status=0" up>上架</a>
                    </c:when>
                </c:choose>

                <a href="javascript:" image-modify>修改</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--分页条--%>
<lch:page page="${page}" href="/user-run-share-resource/page/image.htm"></lch:page>

<%--<jsp:include page="content-add.jsp"/>--%>
<jsp:include page="image-add.jsp"/>
<jsp:include page="image-modify.jsp"/>


<script>

    $(function(){
        $("[up]").click(function(){
            if (!window.confirm("确认上架 ?")) {
                return;
            }
            $.get($(this).attr("data"), {}, function(result){
                if (!result["core"]) {
                    location.reload();
                }
                else {
                    alert(result["msg"]);
                }
            })
        });

        $("[down]").click(function(){
            if (!window.confirm("确认下架 ?")) {
                return;
            }
            $.get($(this).attr("data"), {}, function(result){
                if (!result["core"]) {
                    location.reload();
                }
                else {
                    alert(result["msg"]);
                }
            })
        });

    });
</script>