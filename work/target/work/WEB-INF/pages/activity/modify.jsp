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
            <form action="/activity/modify.json" method="post" class="uk-form"isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="themeImage">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="themeName" placeholder="活动主题" value="${activity.themeName}" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="signUpBeginTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="报名开始时间" value="<lch:parse-date time="${activity.signUpBeginTime}" pattern="yyyy-MM-dd HH:mm"/>">
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="signUpEndTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="报名结束时间" value="<lch:parse-date time="${activity.signUpEndTime}" pattern="yyyy-MM-dd HH:mm"/>">
                </div>

                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="activityBeginTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="活动开始时间" value="<lch:parse-date time="${activity.activityBeginTime}" pattern="yyyy-MM-dd HH:mm"/>">
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="activityEndTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="活动结束时间" value="<lch:parse-date time="${activity.activityEndTime}" pattern="yyyy-MM-dd HH:mm"/>">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="activityMaxNumber" placeholder="活动最大人数" value="${activity.activityMaxNumber}" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="activityFalseNumber" placeholder="虚假人数" value="${activity.activityFalseNumber}" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="描述">${activity.desc}</textarea>
                </div>

                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="sort" placeholder="sort 越大越靠前" value="${activity.sort}" notnull>
                </div>

                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="type" placeholder="活动类型" data-value="${activity.type}">
                        <option value="0">普通赛事</option>
                        <option value="1">积分赛事</option>
                        <option value="2">三方赛事</option>
                        <option value="3">标准赛事</option>
                    </select>
                </div>

                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="releaseStatus" placeholder="状态"  data-value="${activity.releaseStatus}">
                        <option value="0">发布</option>
                        <option value="1">待发布</option>
                        <option value="0">活动结束</option>
                        <option value="1">活动异常</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${activity.status}">
                        <option value="0">正常</option>
                        <option value="1">无效</option>
                    </select>
                </div>

                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="activityId" value="${activity.id}">
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
        var type_ = $("[name=type]").attr("data-value");
        if (type_ == undefined || type_ == null || type_ == "") {
            type_ = 0;
        }
        $("[name=type]").val(type_);
        $("[name=status]").val($("[name=status]").attr("data-value"));
        $("[name=releaseStatus]").val($("[name=releaseStatus]").attr("data-value"));
    });

</script>