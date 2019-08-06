<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/3/30
  Time: 下午7:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<div class="uk-container-center uk-body">
    <a href="#role-add-modal" class="uk-button" data-uk-modal>Add</a>
    <table class="uk-table uk-table-hover uk-table-striped">
        <caption>hha</caption>
        <thead>
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>扩展名</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="role" items="${allRole}">
                <tr>
                    <td name="id">${role.id}</td>
                    <td name="name">${role.name}</td>
                    <td name="extensionName">${role.extensionName}</td>
                    <td name="des">${role.des}</td>
                    <td>
                        <%--<a href="javascript:" remove-role data-id="${role.id}">删除</a>--%>
                        <a href="javascript:" modify-role>修改</a>
                        <a href="/role-resource.htm?id=${role.id}" target="_blank">权限</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>


<script type="text/javascript">
    $(function(){
        $("[remove-role]").click(function(){
            var th = $(this);
            var id = th.attr("data-id");
            $.post("/remove_role.json", {"id" : id}, function(result){
                th.parent().parent().hide(300, function(){
                    this.remove();
                });
            });
        });
    });

</script>

<%--  add  --%>
<%@ include file="role-add.jsp"%>
<%--    modify    --%>
<%@ include file="role-modify.jsp"%>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
