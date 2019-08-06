<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/1/15
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="resource-content-modify" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>修改（内容）</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/user-run-share-resource/modify-content.json" method="post" enctype="multipart/form-data" class="uk-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="content" placeholder="content" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input class="uk-width-1-1 uk-form-large" type="hidden" name="id" placeholder="id" notnull>
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(function(){
        $("[content-modify]").click(function(){
            var th = $(this);
            var dataJson = th.parent().parent().sequenceJson();
            $("#resource-content-modify").fillNode(dataJson);
            UIkit.modal("#resource-content-modify").show();
        });
    });

</script>

