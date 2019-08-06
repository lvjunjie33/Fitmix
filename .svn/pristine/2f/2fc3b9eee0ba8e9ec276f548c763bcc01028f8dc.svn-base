<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action="/channel-app/channel-analysis-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">

                    <input type="hidden" name="filter[type]" value="${page.filter.type}">

                    <label>渠道：</label>
                    <label class="mix-scene">
                        <select name="filter[channelId]">
                            <c:forEach items="${dictionaryList}" var="dic">
                                <option value="${dic.value}" <c:if test="${dic.value == page.filter.channelId}"> selected = "selected" </c:if> >${dic.name}</option>
                            </c:forEach>
                        </select>
                        <%--<input type="number" name="filter[channelId]" value="${page.filter.channelId}" placeholder="渠道Id">--%>

                    </label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <label>时间：</label>
                    <label class="mix-scene"><input type="text" name="filter[startTime]" value="${page.filter.startTime}" data-uk-datepicker="{format:'YYYY-MM-DD'}" placeholder="上架时间"></label>
                    <label>至</label>
                    <label class="mix-scene"><input type="text" name="filter[endTime]" value="${page.filter.endTime}" data-uk-datepicker="{format:'YYYY-MM-DD'}" placeholder="上架时间"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/channel-app/channel-app-add.htm" target="_blank" class="uk-button uk-button-primary">绑定渠道App信息</a>
                </div>
            </div>
        </form>
    </div>
    <hr class="uk-article-divider">
</div>

<div class="uk-overflow-container">
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <th style="width: 100px">日期</th>
            <th style="width: 200px;">渠道ID</th>
            <th style="width: 100px">渠道名称</th>
            <th>IOS下载量</th>
            <th>Android下载量</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="channelAnalysis" items="${page.result}">
            <tr>
                <td><lch:parse-date time="${channelAnalysis.date}" pattern="yyyy-MM-dd"/></td>
                <td>${channelAnalysis.channelId}</td>
                <td>${channelAnalysis.channelName}</td>
                <td>${channelAnalysis.iosDownloadCount}</td>
                <td>${channelAnalysis.androidDownloadCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/video/video-list.htm"></lch:page>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
