<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/10
  Time: 下午3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="task-modify" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>SysParam Modify</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/task/task-modify.json" method="post" class="uk-form login-form" id="task-modify-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="name" placeholder="name" notnull disabled>
                    <input type="hidden" name="id"/>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="coin" placeholder="coin" notnull />
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="status">
                        <option value="0">有效</option>
                        <option value="1">无效</option>
                    </select>
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">

    $(function(){
        $("[task-modify]").click(function(){
            var th = $(this);
            var dataJson = th.parent().parent().sequenceJson();
            $("#task-modify-form").fillNode(dataJson);
            UIkit.modal("#task-modify").show();
        });
    });

</script>


