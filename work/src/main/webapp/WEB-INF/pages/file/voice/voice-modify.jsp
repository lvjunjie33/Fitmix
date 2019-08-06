<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/14
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>语音包修改</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/voice/modify.json" method="post" class="uk-form" id="activity-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" placeholder="标题" notnull value="${voice.title}">
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="des" placeholder="文件描述" notnull>${voice.des}</textarea>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="tags" placeholder="标签如:大明星、林俊杰" notnull>${voice.tags}</textarea>
                </div>

                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${voice.status}">
                        <option value="1">正常</option>
                        <option value="0">无效</option>
                    </select>
                </div>

                <div class="uk-form-row uk-text-right">
                    <input class="uk-width-1-1 uk-form-large" type="hidden" name="id" value="${voice.id}">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
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
    });
</script>