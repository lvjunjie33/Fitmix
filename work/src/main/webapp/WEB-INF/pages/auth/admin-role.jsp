<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/10
  Time: 下午3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="admin-role-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Admin Role</h1>
        <div class="uk-form-row">
            <form action="/admin-role.json" method="post" class="uk-form login-form" id="admin-role-form" isform>
                <h3 id="role-loading">加载中...</h3>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id"/>
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">submit</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $("[admin-role]").click(function(){
            var th = $(this);
            var dataJson = th.parent().parent().sequenceJson();
            var adminRoles = $.strToArray(dataJson["roles"]);
            var adminRolesMap = $.arrayToMapJSON(adminRoles);
            $("#admin-role-modal").fillNode(dataJson);
            UIkit.modal("#admin-role-modal").show();
            $.post("/role.json", {}, function (reslut) {
                var roleHtml = "";
                $.each(reslut["allRole"], function(i, role) {
                    if (adminRolesMap[role["name"]]) {
                        roleHtml += '<div class="uk-form-row"><label><input type="checkbox" checked name="roles" value="{name}" data-id="{id}"/>&nbsp;{name}</label></div>'.format(role);
                    }
                    else {
                        roleHtml += '<div class="uk-form-row"><label><input type="checkbox" name="roles" value="{name}" data-id="{id}"/>&nbsp;{name}</label></div>'.format(role);
                    }
                });
                $("#role-loading").replaceWith(roleHtml);
            });
        });
    });
</script>