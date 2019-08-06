<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/10
  Time: 下午3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="role-add-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog uk-modal-dialog-large">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Role Add</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-overflow-container">
            <form action="/add-role.json" method="post" class="uk-form login-form" id="role-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="name" placeholder="name" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="extensionName" placeholder="extensionName" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1" name="des" placeholder="des" style="min-height: 300px;" notnull></textarea>
                </div>
            </form>
        </div>
        <br/>
        <div class="uk-form-row uk-text-right">
            <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">add</button>
            <button class="uk-button uk-modal-close">cancel</button>
        </div>
    </div>
</div>
<script type="text/javascript">

    $(function(){
        $("#role-add-submit").click(function(){
            var form = $("#" + $(this).attr("target"));
            var formJson = form.sequenceJson();
            validate.trigger();

            if (!form.find("." + $.errorAlertClass).size()) {
//                $(this).attr("disabled", "true");
                $(this).text("wait...");
                form.submit();
            }
        });
    });
</script>