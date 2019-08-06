<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/4/13
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<style>
    .uk-form .other-info{
        width:66px;
        margin-left: 30px;;
    }
    .auto-cla{
        min-width: 150px;
        display: inline-block;
    }
</style>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>设置活动报名信息</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <form action="" method="post" class="uk-form" isform>
            <div class="uk-form-row">
                <span class="uk-width-1-1 uk-form-large" placeholder="活动主题"
                      style = "text-transform: uppercase;font-weight: 700;font-size: 16px;padding-left: 10px" >
                    ${activity.themeName}
                    &nbsp;&nbsp;&nbsp;&nbsp;<a href="#activity-add" class="uk-button" data-uk-modal="" style="float: right">添加报名组</a></span>
            </div>
            <c:forEach var="group" items="${activity.groups}">
                <div class="uk-form-row">
                    <div class="uk-form-controls group-info">
                        <label><a href="/activity/remove-activity-group.htm?activityId=${activity.id}&groupId=${group.id}" class="uk-button" style="background-color: #F7BCBC" title="点击将删除该组信息" >删除</a></label>
                        <label class="auto-cla"><strong>组名称：</strong>${group.groupName}</label>
                        <label class="auto-cla" style="min-width: 70px"><strong>人数：</strong>${group.memberNumber}</label>
                        <label class="auto-cla"><strong>组成员昵称：</strong>${group.memberNames}</label>
                        <label class="auto-cla" style="padding-left: 30px;"><strong>描述：</strong>${group.desc}</label>
                        <label style="float: right"><strong>金额</strong><span class="other-info">${group.needMoney}</span></label>
                    </div>
                </div>
            </c:forEach>
        </form>
    </div>
</div>

<jsp:include page="set-group.jsp"/>