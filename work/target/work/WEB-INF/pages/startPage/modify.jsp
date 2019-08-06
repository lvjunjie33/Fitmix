<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/5/16
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>闪屏修改</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/start-page/modify.json" method="post" class="uk-form"isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="backgroundImg">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" placeholder="闪屏主题" value="${sp.title}" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="countdown" placeholder="闪屏时长" value="${sp.countdown}" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="startTime"  data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}"
                           value="<lch:parse-date time="${sp.startTime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="deadline" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}"
                           value="<lch:parse-date time="${sp.deadline}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="截止时间" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="des" placeholder="描述">${sp.des}</textarea>
                </div>

                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="releaseStatus" placeholder="发布状态"  data-value="${sp.releaseStatus}">
                        <option value="0">发布</option>
                        <option value="1">待发布</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${sp.status}">
                        <option value="0">正常</option>
                        <option value="1">无效</option>
                    </select>
                </div>

                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${sp.id}">
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