<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/4 0004
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<div class="uk-container-center uk-body">

    <form action="/mix-author/page-mix-author.htm" method="get" class="uk-form uk-grid">
        <div class="uk-width-2-6">
            <label>编号：</label>
            <label><input type="number" name="filter[id]" value="${page.filter.id}" placeholder="编号.."/></label>
        </div>

        <div class="uk-width-2-6">
            <label>名称：</label>
            <label><input type="text" name="filter[name]" value="${page.filter.name}" placeholder="名称.."/></label>
        </div>

        <div class="uk-width-1-6">
            <button type="submit" class="uk-button uk-button-primary">查询</button>
            <a href="/mix-author/add-mix-author.htm" class="uk-button uk-button-danger">添加</a>
        </div>
    </form>
    <br>
    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
                <tr>
                    <th>编号</th>
                    <th>名称</th>
                    <th>电话</th>
                    <th>邮箱</th>
                    <th>关注</th>
                    <th>添加时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="mixAuthor" items="${page.result}">
                    <tr>
                        <td>${mixAuthor.id}</td>
                        <td><a href="/mix-author/info-mix-author.htm?id=${mixAuthor.id}">${mixAuthor.name}</a></td>
                        <td>${mixAuthor.phone}</td>
                        <td>${mixAuthor.email}</td>
                        <td>${mixAuthor.userCount}</td>
                        <td><lch:parse-date time="${mixAuthor.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>
                            <a href="/mix-author/modify-mix-author.htm?id=${mixAuthor.id}" class="uk-link">修改</a>
                            <a href="javascript:" class="uk-link" style="color: #ff3958;" remove data-id="${mixAuthor.id}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <lch:page page="${page}" href="/mix-author/page-mix-author.htm"/>
    </div>
</div>


    <script type="text/javascript">

        $(function(){
            $("[remove]").click(function(){
                var id = $(this).attr("data-id");
                if (window.confirm("确认删除？")) {
                    $.post("/mix-author/remove-mix-author.json", {"id" : id}, function(){
                        window.location.reload();
                    });
                }
            });
        });

    </script>



<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>