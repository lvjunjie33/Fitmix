<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/5/13
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

    .short {
        width: 75px;
    }

    .time{
        width: 100px;
    }
    .mobile-cls-1-1{
        width: 25%;
    }

</style>

<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action="/activity/to-find-activity-sign-up-info.htm" method="get" class="uk-form">
            <div class="uk-form-row uk-grid">
                <div class="uk-width-1-5">
                    <label>联系人：</label>
                    <label>
                        <input type="text" name="filter[mobileName]" value="${page.filter.mobileName}" placeholder="联系人名称..."/>
                        <input type="hidden" name="filter[activityId]" value="${page.filter.activityId}" placeholder="活动编号..."/>
                    </label>
                </div>

                <div class="uk-width-1-5 mobile-cls-1-1">
                    <label>手机号码：</label>
                    <label><input type="text" name="filter[mobilePhone]" value="${page.filter.mobilePhone}" placeholder="手机号码..."/></label>
                </div>

                <div class="uk-width-1-3">
                    <label>报名组类型：</label>
                    <select name="filter[groupId]">
                        <option value="">请选择</option>
                        <c:forEach var="group" items="${groups}">
                            <option value="${group.id}" <c:if test="${group.id == page.filter.groupId}">selected</c:if>>${group.groupName}</option>
                        </c:forEach>
                    </select>
                </div>

                <br/><br/>
                <div class="uk-width-2-5 mobile-cls-1-1">
                    <label>时间：</label>
                    <label><input type="text" name="filter[beginTime]" value="${page.filter.beginTime}" placeholder="报名时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
                    -
                    <label><input type="text" name="filter[endTime]" value="${page.filter.endTime}" placeholder="报名时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
                </div>

                <div class="uk-width-1-4">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/activity/sign-up-export.htm?filter[activityId]=${page.filter.activityId}" class="uk-button uk-button-primary">导出报名信息</a>
                    <c:if test="${activityType == 1}">
                        <a href="/activity/integral-export.htm?activityId=${page.filter.activityId}" class="uk-button uk-button-primary">导出昨日排名</a>
                    </c:if>
                </div>
            </div>
        </form>

        <div class="uk-overflow-container">
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                <tr>
                    <th>手机号码</th>
                    <th>联系人名称</th>
                    <th>报名活动组名称</th>
                    <th>报名时间</th>
                    <th>支付状态</th>
                    <th style="width: 30%">报名成员信息</th>
                    <th>参与次数(手机号码)</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="signUp" items="${page.result}">
                    <tr>
                        <td>${signUp.mobilePhone}</td>
                        <td>
                            <c:choose>
                                <c:when test="${activity.type == 2}">
                                    ${signUp.userId}
                                </c:when>
                                <c:otherwise>
                                    ${signUp.mobileName}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${signUp.groupName}</td>
                        <td><lch:parse-date time="${signUp.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${signUp.tradeStatus == 0}">创建订单</c:when>
                                <c:when test="${signUp.tradeStatus == 1}">等待支付</c:when>
                                <c:when test="${signUp.tradeStatus == 2}">支付成功</c:when>
                                <c:when test="${signUp.tradeStatus == 3}">支付异常</c:when>
                                <c:otherwise>
                                    未支付
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="user-channel">
                            <c:forEach var="member" items="${signUp.activitySignUpMembers}">
                                ${member.groupMemberName}：${member.name}
                                <c:if test="${!empty member.idCard}">
                                    -${member.idCard}
                                </c:if>
                                <c:if test="${!empty member.gender}">
                                    -${member.gender}
                                </c:if>
                                <c:if test="${!empty member.clothesSize}">
                                    -${member.clothesSize}
                                </c:if>
                                <c:if test="${!empty member.mobile}">
                                    -${member.mobile}
                                </c:if>
                                <br/>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${activity.type == 2}">
                                    ${signUp.activitySignUpNumber}
                                </c:when>
                                <%--<c:otherwise>
                                    <c:forEach var="member" items="${signUp.activitySignUpMembers}">
                                        ${member.groupMemberName}：${member.name}-${member.idCard}-${member.gender}-${member.clothesSize}<br/>
                                    </c:forEach>
                                </c:otherwise>--%>
                            </c:choose>
                        </td>
                        <td>${signUp.count}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%--分页条--%>
    <lch:page page="${page}" href="/activity/to-find-activity-sign-up-info.htm"></lch:page>
</div>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>