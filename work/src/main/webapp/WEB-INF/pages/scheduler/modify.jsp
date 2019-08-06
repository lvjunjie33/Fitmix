<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/6/23
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>定时任务修改</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/scheduler/modify.json" method="post" class="uk-form" id="activity-add-form" isform>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="topicName" placeholder="消息通道名"
                           notnull title = "需要重启该定时器" value="${task.topicName}">
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large"  name="cron" placeholder="cron表达式"
                           notnull title = "需要通过重设cron让新的cron生效" value="${task.cron}">
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" placeholder="任务生效时间"
                           data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" notnull title = "需要重启定时器" value="<lch:parse-date time="${task.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" placeholder="任务失效时间"
                           data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" notnull title = "需要重启定时器"value="<lch:parse-date time="${task.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${task.status}">
                        <option value="0">正常</option>
                        <option value="1">无效</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="currentStatus" placeholder="状态" data-value="${task.currentStatus}">
                        <option value="1">未启动</option>
                        <option value="2">运行中</option>
                        <option value="2">异常停止</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="des" placeholder="描述" notnull>${task.des}</textarea>
                </div>

                <div class="uk-form-row uk-text-right">
                    <input value="${task.id}" type="hidden" name = "id">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">save</button>
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
        $("[name=currentStatus]").val($("[name=currentStatus]").attr("data-value"));
    });

</script>