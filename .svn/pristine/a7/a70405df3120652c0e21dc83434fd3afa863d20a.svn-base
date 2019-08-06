<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/2/16
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>活动修改</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/speedway/modify.json" method="post" class="uk-form"isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="backgroundImage" title = "赛道背景图片">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" placeholder="活动主题" notnull value="${speedway.title}">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="city" placeholder="城市名称" notnull value="${speedway.city}">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="wayId" placeholder="赛道编号" notnull value="${speedway.wayId}">
                </div>

                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="releaseStatus" placeholder="发布状态"  data-value="${speedway.releaseStatus}">
                        <option value="0">发布</option>
                        <option value="1">待发布</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${speedway.status}">
                        <option value="1">正常</option>
                        <option value="0">无效</option>
                    </select>
                </div>

                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="des" placeholder="描述" notnull>${speedway.des}</textarea>
                </div>

                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="speedwayId" value="${speedway.id}">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">submit</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    // 设置  type status releaseStatus 选项
    $(function(){
        $("[name=status]").val($("[name=status]").attr("data-value"));
        $("[name=releaseStatus]").val($("[name=releaseStatus]").attr("data-value"));
    });

</script>