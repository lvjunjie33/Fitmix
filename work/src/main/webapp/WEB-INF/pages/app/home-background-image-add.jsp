<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/28
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<div id="home-image-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Home Image Add</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/app/add-home-background-image.json" method="post" class="uk-form login-form" id="home-image-add-form">

                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="version" placeholder="version" notnull>
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
