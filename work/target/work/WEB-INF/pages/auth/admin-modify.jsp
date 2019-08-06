<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/10
  Time: 下午3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="admin-modify" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Admin Modify</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/modify-admin.json" method="post" class="uk-form login-form" id="admin-modify-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="loginName" placeholder="loginName" notnull disabled>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="name" placeholder="name" notnull>
                </div>
                <div class="uk-form-row">
                    <div class="uk-form-password uk-width-1-1">
                        <input type="password" class="uk-width-1-1 uk-form-large" name="password">
                        <a href="" class="uk-form-password-toggle" data-uk-form-password>show</a>
                    </div>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="activate">
                        <option value="1">激活</option>
                        <option value="2" selected>不激活</option>
                    </select>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id"/>
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $("[admin-modify]").click(function(){
            var th = $(this);
            var dataJson = th.parent().parent().sequenceJson();
            console.info(dataJson);
            $("#admin-modify").fillNode(dataJson);
            UIkit.modal("#admin-modify").show();
        });
    });
</script>