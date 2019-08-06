<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/10/9
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="des-modify" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
    </div>
    <h1>Description modify</h1>
    <div class="uk-form-row">
        <form class="uk-form" action="/run-plan/des-modify.json" method="POST"  id="des-modify-form" isform>
            <div class="uk-form-row">
                <h4>关键词</h4>
                <input class="uk-width-2-3 uk-form-large" type="text" name="key" placeholder="关键词" notnull />
            </div>
            <div class="uk-form-row">
                <h4>描述</h4>
                <textarea name="des" class=" uk-width-2-3 uk-form-large" placeholder="des"></textarea>
            </div>
            <div class="uk-form-row uk-text-right">
                <button class="uk-button uk-button-primary" id="des-modify-submit">modify</button>
            </div>
        </form>
    </div>
</div>
