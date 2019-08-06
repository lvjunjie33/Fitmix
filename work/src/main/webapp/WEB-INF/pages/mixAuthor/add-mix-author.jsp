<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/4 0004
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<div class="uk-container-center uk-body">
    <h2>Mix Author Add</h2>
    <form action="/mix-author/add-mix-author.json" method="post" class="uk-form" enctype="multipart/form-data" isform>
        <div class="uk-form-row">
            <label>名称：</label>
            <input type="text" name="name" class="uk-form-large uk-width-9-10" placeholder="author name..." notnull error-text="author name not null">
        </div>
        <div class="uk-form-row">
            <label>简介：</label>
            <textarea name="introduce" class="uk-form-large uk-width-9-10" style="height: 100px;" placeholder="introduce..." notnull error-text="introduce not null"></textarea>
        </div>
        <div class="uk-form-row">
            <label>详细：</label>
            <textarea name="introduceDetail" class="uk-form-large uk-width-9-10" style="height: 200px;;" placeholder="introduce detail..." notnull error-text="introduce detail not null"></textarea>
        </div>
        <br/>
        <div class="uk-grid">
            <label>&nbsp;&nbsp;&nbsp;</label>
            <div class="uk-width-9-10">
                <button class="uk-button uk-button-primary">add</button>
            </div>
        </div>
    </form>
</div>




<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>