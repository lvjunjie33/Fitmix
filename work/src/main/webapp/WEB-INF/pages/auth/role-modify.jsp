<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/10
  Time: 下午3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="role-modify-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog uk-modal-dialog-large">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Role Add</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-overflow-container">
            <form action="/modify-role.json" method="post" class="uk-form login-form" id="role-modify-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="name" placeholder="name" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="extensionName" placeholder="extensionName" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1" name="des" placeholder="des" style="min-height: 300px;" notnull></textarea>
                </div>
                <input type="hidden" name="id"/>
            </form>
        </div>
        <br/>
        <div class="uk-form-row uk-text-right">
            <button class="uk-button uk-button-primary" submit target="role-modify-form">modify</button>
            <button class="uk-button uk-modal-close">cancel</button>
        </div>
    </div>
</div>
<script type="text/javascript">

    $(function(){

        $("[modify-role]").click(function(){
            var th = $(this);
            var json = th.parent().parent().sequenceJson();
            $("#role-modify-modal").fillNode(json);
            UIkit.modal("#role-modify-modal").show();
        });


        $("#role-modify-modal [submit]").click(function(){
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