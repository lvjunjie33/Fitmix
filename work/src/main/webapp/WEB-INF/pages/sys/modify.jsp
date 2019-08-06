<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/10
  Time: 下午3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="sys-modify" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>SysParam Modify</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/sys/modify.json" method="post" class="uk-form login-form" id="sys-modify-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="name" placeholder="name" notnull disabled>
                    <input type="hidden" name="name"/>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" type="text" name="value" placeholder="type" notnull> </textarea>
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
        $("[sys-modify]").click(function(){
            var th = $(this);
            var dataJson = th.parent().parent().sequenceJson();
            $("#sys-modify-form").fillNode(dataJson);
            UIkit.modal("#sys-modify").show();
        });
    });

</script>


