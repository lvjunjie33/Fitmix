<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/6/1
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="task-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加话题</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/theme/add.json" method="post" class="uk-form" id="activity-add-form" isform>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="title" placeholder="标题" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="uid" placeholder="用户编号" value="" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form" onclick="submitBtn()">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function submitBtn() {
        var $from = $("#activity-add-form");
        var title = $from.find("[name=title]").val();
        if (title.length > 50) {
            alert("标题太长了");
            return false;
        }
    }
</script>

