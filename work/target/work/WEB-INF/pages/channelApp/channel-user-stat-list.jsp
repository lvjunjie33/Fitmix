<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/12/13
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>

    #activity-list{
        margin-top: 10px;
    }

    .activity{
        margin-bottom: 100px;;
    }
    .group-fee-span{
        min-width: 410px;
        display: inline-block;
    }

    .group-fee-span1{
        min-width: 150px;
        display: inline-block;
    }

    .color-red {
        color: red;
    }

</style>

<div id="activity-list">
    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/channel-app/channel-user-stat-list.htm" method="post" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>渠道：</label>
                    <label>
                        <select name="channelId">
                            <c:forEach items="${dictionaryList}" var="dic">
                                <option value="${dic.value}" <c:if test="${dic.value == channelId}"> selected = "selected" </c:if> >${dic.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <input type="text"  name="bTime" data-uk-datepicker="{format:'YYYY-MM-DD'}" placeholder="开始日期" notnull value="${bTime}">
                    <input type="text"  name="eTime" data-uk-datepicker="{format:'YYYY-MM-DD'}" placeholder="结束日期" notnull value="${eTime}">&nbsp;&nbsp;
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/channel-app/channel-user-stat-list-export.htm?channelId=${channelId}&bTime=${bTime}&eTime=${eTime}" class="uk-button uk-button-primary">导出</a>
                </div>
            </div>
        </form>
    </div>

    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr>
                <th style="width: 50px;">注册日期</th>
                <th style="width: 200px;">注册用户数</th>
                <th style="width: 100px">运动用户数</th>
                <th style="width: 100px;">下载用户数</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="stat" items="${channelUserStats}">
                <tr>
                    <td>${stat.statDay}</td>
                    <td>${stat.registerNum}</td>
                    <td>${stat.runNum}</td>
                    <td>${stat.downloadMusicNum}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%--<jsp:include page="modify.jsp"/>--%>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>