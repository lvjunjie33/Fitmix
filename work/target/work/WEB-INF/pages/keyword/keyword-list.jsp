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
        <form action="/keyword/keyword-list.htm" method="post" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>关键字：</label>
                    <label class="mix-scene">
                        <input type="text" name="filter[name]" value="${page.filter.name}" placeholder="关键字">
                    </label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/keyword/keyword-add.htm" target="_blank" class="uk-button uk-button-primary">新增关键字</a>
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
                <th style="width: 100px">ID</th>
                <th style="width: 200px;">关键字</th>
                <th style="width: 100px">类型</th>
                <th style="width: 100px">创建时间</th>
                <th style="width: 100px">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="keyword" items="${page.result}">
                <tr>
                    <td><label name="id">${keyword.id}</label></td>
                    <td>${keyword.name}</td>
                    <td>
                        <c:if test="${keyword.type == 1}">
                            搜索栏关键字
                        </c:if>
                        <c:if test="${keyword.type == 2}">
                            搜索列表关键字
                        </c:if>
                    </td>
                    <td><lch:parse-date time="${keyword.addTime}" pattern="yyyy-MM-dd"/></td>

                    <td>
                        <lch:auth-operation url="/keyword/keyword-modify-state.json">
                            <c:choose>
                                <c:when test="${keyword.state == 2}"><a href="javascript:" no-shelves>下架</a></c:when>
                                <c:when test="${keyword.state == 1}"><a href="javascript:" shelves>上架</a></c:when>
                            </c:choose>
                        </lch:auth-operation>
                        &nbsp;
                        <lch:auth-operation url="/keyword/keyword-modify-sort.json">
                            <a href="#keyword-sort-modal" data-uk-modal keyword-sort>sort</a>
                        </lch:auth-operation>
                        &nbsp;
                        <lch:auth-operation url="/keyword/keyword-modify.htm">
                            <a href="/keyword/keyword-modify.htm?id=${keyword.id}" target="_blank">编辑</a>
                        </lch:auth-operation>
                        &nbsp;
                        <lch:auth-operation url="/keyword/keyword-remove.json">
                            <a href="javascript:" keyword-remove style="color:#ff4a35;" data-id="${keyword.id}">删除</a>
                        </lch:auth-operation>
                    </td>

                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script>

    $(function(){
        $("[keyword-remove]").click(function(){
            if (window.confirm("是否删除？")) {
                var th = $(this);
                var id = th.attr("data-id");
                $.post("/keyword/keyword-remove.json", {"id":id}, function(result){
                    th.parent().parent().hide(400).remove();
                });
            }
        });
    });

    $(function(){
        $("[shelves]").on("click", shelves);
        $("[no-shelves]").on("click", onShelves);

        function shelves () {
            if (window.confirm("确认上架？")) {
                var th = $(this);
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["state"] = 2;
                $.post("/keyword/keyword-modify-state.json", dataJson, function(result){
                    var code = result['code'];
                    if (code == 0) {
                        th.replaceWith('<a href="javascript:" no-shelves>下架</a>');
                        $("[no-shelves]").on("click", onShelves);
                    }
                    else{
                        UIkit.notify(result['msg'], {status:'danger', pos:'top-right'});
                    }
                });
            }
        }

        function onShelves () {
            if (window.confirm("确认下架？")) {
                var th = $(this);
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["state"] = 1;
                $.post("/keyword/keyword-modify-state.json", dataJson, function(result){
                    th.replaceWith('<a href="javascript:" shelves>上架</a>');
                    $("[shelves]").on("click", shelves);
                });
            }
        }
    });
</script>

<%--分页条--%>
<lch:page page="${page}" href="/keyword/keyword-list.htm"></lch:page>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
<%@ include file="keyword-sort.jsp"%>
