<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action="/bbs/article/article-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>文章编号：</label>
                    <label class="mix-scene"><input type="number" name="filter[id]" value="${page.filter.id}"
                                                    placeholder="文章"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/bbs/article/article-add.htm" target="_blank" class="uk-button uk-button-primary">添加文章</a>
                </div>
            </div>
        </form>
    </div>
    <hr class="uk-article-divider">
</div>

<div class="uk-overflow-container">
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <th style="width: 100px;">文章编号</th>
            <th style="width: 200px;">标题</th>
            <th style="width: 100px">简介</th>
            <th style="width: 100px">类别</th>
            <th style="width: 100px">创建时间</th>
            <th style="width: 120px">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="article" items="${page.result}">
            <tr>
                <td><label name="id">${article.id}</label></td>
                <td>${article.title}</td>
                <td>${article.desc}</td>
                <td>${article.category}</td>
                <td><lch:parse-date time="${article.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>
                    <lch:auth-operation url="/bbs/article/article-modify.json">
                        <c:choose>
                            <c:when test="${article.status == 0}"><a href="javascript:" no-shelves>失效</a></c:when>
                            <c:when test="${article.status == 1}"><a href="javascript:" shelves>生效</a></c:when>
                        </c:choose>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/bbs/article/article-modify.htm">
                        <a href="/bbs/article/article-modify.htm?id=${article.id}" target="_blank">编辑</a>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/bbs/article/article-remove.json">
                        <a href="javascript:" channel-remove style="color:#ff4a35;" data-id="${article.id}">删除</a>
                    </lch:auth-operation>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/bbs/article/article-list.htm"></lch:page>
<script>
    $(function () {
        $("[channel-remove]").click(function () {
            if (window.confirm("是否删除？")) {
                var th = $(this);
                var id = th.attr("data-id");
                $.post("/bbs/article/article-remove.json", {"id": id}, function (result) {
                    th.parent().parent().hide(400).remove();
                });
            }
        });
    });

    $(function () {
        $("[shelves]").on("click", shelves);
        $("[no-shelves]").on("click", onShelves);

        function shelves() {
            if (window.confirm("确认生效？")) {
                var th = $(this);
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["status"] = 0;
                $.post("/bbs/article/article-modify.json", dataJson, function (result) {
                    var code = result['code'];
                    if (code == 0) {
                        th.replaceWith('<a href="javascript:" no-shelves>失效</a>');
                        $("[no-shelves]").on("click", onShelves);
                    }
                    else {
                        UIkit.notify(result['msg'], {status: 'danger', pos: 'top-right'});
                    }
                });
            }
        }

        function onShelves() {
            if (window.confirm("确认失效？")) {
                var th = $(this);
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["status"] = 1;
                $.post("/article/article-modify.json", dataJson, function (result) {
                    th.replaceWith('<a href="javascript:" shelves>生效</a>');
                    $("[shelves]").on("click", shelves);
                });
            }
        }
    });


</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
