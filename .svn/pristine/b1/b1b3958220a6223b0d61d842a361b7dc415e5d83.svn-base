<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/4 0004
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

    .avatar{
        height: 100px;
        width: 100px;
        border-radius: 50px;;
    }
</style>

<div class="uk-container-center uk-body">
    <h2>Mix Author Add</h2>
    <form action="/mix-author/modify-mix-author.json" method="post" class="uk-form" enctype="multipart/form-data" isform>
        <div class="uk-form-row">
            <!-- This is an anchor as a thumbnail -->
            <a class="uk-thumbnail" href="javascript:"><img src="${mixAuthor.avatar}" class="avatar"></a>
        </div>
        <div class="uk-form-row">
            <label>编号：</label>
            <label>${mixAuthor.id}</label>
            <input type="hidden" name="id" value="${mixAuthor.id}">
        </div>
        <div class="uk-form-row">
            <label>名称：</label>
            <input type="text" name="name" value="${mixAuthor.name}" class="uk-form-large uk-width-9-10" placeholder="author name..." notnull error-text="author name not null">
        </div>
        <div class="uk-form-row">
            <label>地区：</label>
            <input type="text" name="international" value="${mixAuthor.international}" class="uk-form-large uk-width-9-10" placeholder="国家/城市" >
        </div>
        <div class="uk-form-row">
            <label>电话：</label>
            <input type="text" name="phone" value="${mixAuthor.phone}" class="uk-form-large uk-width-9-10" placeholder="phone..." >
        </div>
        <div class="uk-form-row">
            <label>邮件：</label>
            <input type="text" name="email" value="${mixAuthor.email}" class="uk-form-large uk-width-9-10" placeholder="email..." >
        </div>
        <div class="uk-form-row">
            <label>网站：</label>
            <input type="text" name="webUrl" value="${mixAuthor.webUrl}" class="uk-form-large uk-width-9-10" placeholder="webUrl..." >
        </div>
        <div class="uk-form-row">
            <label>微信：</label>
            <input type="text" name="weiXin" value="${mixAuthor.weiXin}" class="uk-form-large uk-width-9-10" placeholder="weiXin..." >
        </div>
        <div class="uk-form-row">
            <label>微博：</label>
            <input type="text" name="weiBo" value="${mixAuthor.weiBo}" class="uk-form-large uk-width-9-10" placeholder="weiBo..." >
        </div>
        <div class="uk-form-row">
            <label>简介：</label>
            <textarea name="introduce" class="uk-form-large uk-width-9-10" style="height: 100px;" placeholder="introduce..." notnull error-text="introduce not null">${mixAuthor.introduce}</textarea>
        </div>
        <div class="uk-form-row">
            <label>详细：</label>
            <textarea name="introduceDetail" class="uk-form-large uk-width-9-10" style="height: 100px;;" placeholder="introduce detail..." notnull error-text="introduce detail not null">${mixAuthor.introduceDetail}</textarea>
        </div>
        <div class="uk-form-row">
            <label>动态：</label>
            <textarea name="dynamic" class="uk-form-large uk-width-9-10" style="height: 100px;;" placeholder="dynamic...">${mixAuthor.dynamic}</textarea>
        </div>
        <div class="uk-form-row">
            <label>备注：</label>
            <textarea name="memo" class="uk-form-large uk-width-9-10" style="height: 100px;;" placeholder="memo...">${mixAuthor.memo}</textarea>
        </div>
        <br/>
        <div class="uk-grid">
            <label>&nbsp;&nbsp;&nbsp;</label>
            <div class="uk-width-9-10">
                <button class="uk-button uk-button-primary">修改资料</button>
                <a href="/mix-author/upload-img.htm?id=${mixAuthor.id}" class="uk-button uk-button-danger">修改相册</a>
            </div>
        </div>
    </form>
</div>




<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>