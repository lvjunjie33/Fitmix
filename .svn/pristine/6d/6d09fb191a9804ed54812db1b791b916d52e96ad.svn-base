<%@ taglib prefix="lc" uri="http://tag.lch.com/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/4 0004
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<%--<div class="uk-modal uk-open" id="mixBannerModfy">--%>
    <%--<div class="uk-modal-dialog">--%>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>修改封面</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/run-level/run-level-modify.json" method="post" enctype="multipart/form-data" class="uk-form uk-form-horizontal" isform>
                <div class="uk-form-row">
                    <label class="uk-form-label">勋章图片</label>
                    <div class="uk-form-controls">
                        <input class="uk-width-1-1 uk-form-large" type="file" name="file" placeholder="文件">
                        <img src="<lc:build-path url="${runLevel.honorImage}"/>" />
                    </div>

                </div>
                <div class="uk-form-row">
                    <label class="uk-form-label">名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-width-1-1 uk-form-large" type="text" name="name" value="${runLevel.name}" placeholder="标题" notnull>
                    </div>
                </div>


                <div class="uk-form-row uk-text-right">
                    <input type="hidden" value="${runLevel.id}" name="id"/>
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    <%--</div>--%>
<%--</div>--%>




