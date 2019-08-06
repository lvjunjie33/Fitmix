<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/5/17
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>启动视频修改</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/start-video/modify.json" method="post" class="uk-form"isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="backgroundImg" title="背景图片">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="video" title="视频文件">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" placeholder="视频主题" value="${sv.title}" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="startTime"  data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}"
                           value="<lch:parse-date time="${sv.startTime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="deadline" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}"
                           value="<lch:parse-date time="${sv.deadline}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="截止时间" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="des" placeholder="描述">${sv.des}</textarea>
                </div>

                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="releaseStatus" placeholder="发布状态"  data-value="${sv.releaseStatus}">
                        <option value="0">发布</option>
                        <option value="1">待发布</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${sv.status}">
                        <option value="0">正常</option>
                        <option value="1">无效</option>
                    </select>
                </div>

                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${sv.id}">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">submit</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>

    // 设置  status releaseStatus 选项
    $(function(){
        $("[name=status]").val($("[name=status]").attr("data-value"));
        $("[name=releaseStatus]").val($("[name=releaseStatus]").attr("data-value"));
    });

</script>