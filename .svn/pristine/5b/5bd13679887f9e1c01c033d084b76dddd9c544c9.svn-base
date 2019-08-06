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
        <form action="/bbs/category/category-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>分类名称：</label>
                    <label class="mix-scene"><input type="number" name="filter[name]" value="${page.filter.name}" placeholder="渠道Id"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/bbs/category/category-add.htm" target="_blank" class="uk-button uk-button-primary">添加分类信息</a>
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
            <th style="width: 100px;">编号</th>
            <th style="width: 200px;">分类名称</th>
            <th style="width: 100px">创建时间</th>
            <th style="width: 120px">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${page.result}">
            <tr>
                <td><label name="id">${category.id}</label></td>
                <td>${category.name}</td>
                <td><lch:parse-date time="${category.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>
                    <lch:auth-operation url="/bbs/category/category-modify.htm">
                        <a href="/category/category-modify.htm?id=${category.id}" target="_blank">编辑</a>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/bbs/category/category-remove.json">
                        <a href="javascript:" channel-remove style="color:#ff4a35;" data-id="${category.id}">删除</a>
                    </lch:auth-operation>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/bbs/category/category-list.htm"></lch:page>
<script>
    $(function(){
        $("[channel-remove]").click(function(){
            if (window.confirm("是否删除？")) {
                var th = $(this);
                var id = th.attr("data-id");
                $.post("/bbs/category/category-remove.json", {"id":id}, function(result){
                    th.parent().parent().hide(400).remove();
                });
            }
        });
    });




</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
