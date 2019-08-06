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
    <h2>Mix Author Info</h2>

    <div class="uk-form-row">
        <a class="uk-thumbnail" href="javascript:"><img src="${mixAuthor.avatar}" class="avatar"></a>
    </div>
    <div class="uk-form-row">
        <label>名称：</label>
        <label>${mixAuthor.name}</label>
    </div>
    <div class="uk-form-row">
        <label>地区：</label>
        <label>${mixAuthor.international}</label>
    </div>
    <div class="uk-form-row">
        <label>电话：</label>
        <label>${mixAuthor.phone}</label>
    </div>
    <div class="uk-form-row">
        <label>关注：</label>
        <label>${mixAuthor.userCount}</label>
    </div>
    <div class="uk-form-row">
        <label>邮箱：</label>
        <label>${mixAuthor.email}</label>
    </div>
    <div class="uk-form-row">
        <label>网站：</label>
        <label>${mixAuthor.webUrl}</label>
    </div>
    <div class="uk-form-row">
        <label>微信：</label>
        <label>${mixAuthor.weiXin}</label>
    </div>
    <div class="uk-form-row">
        <label>微博：</label>
        <label>${mixAuthor.weiBo}</label>
    </div>
    <div class="uk-form-row">
        <label>时间：</label>
        <label><lch:parse-date time="${mixAuthor.addTime}" pattern="yyyy-MM-dd HH:mm"/></label>
    </div>
    <div class="uk-form-row">
        <label>简介：</label>
        <label>${mixAuthor.introduce}</label>
    </div>
    <div class="uk-form-row">
        <label>详细：</label>
        <label>${mixAuthor.introduceDetail}</label>
    </div>
    <div class="uk-form-row">
        <label>动态：</label>
        <label>${mixAuthor.dynamic}</label>
    </div>
    <div class="uk-form-row">
        <label>备注：</label>
        <label>${mixAuthor.memo}</label>
    </div>
    <div class="uk-form-row">
        <label>相册：</label>
        <div class="uk-panel uk-grid">
            <c:forEach items="${mixAuthor.photos}" var="photo">
                <div class="uk-width-2-10 photos-panel">
                    <a class="uk-thumbnail" href="javascript:">
                        <img src="${photo}" class="photos" data-id="${mixAuthor.id}">
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="uk-form-row">
        <a href="/mix-author/modify-mix-author.htm?id=${mixAuthor.id}" class="uk-button uk-button-danger">修改资料</a>
        <a href="/mix-author/upload-img.htm?id=${mixAuthor.id}" class="uk-button uk-button-danger">修改图片</a>
    </div>
</div>

</div>




<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>