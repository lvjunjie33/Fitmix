<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/11
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
    .btn-style:hover {
        text-decoration:underline;
    }
</style>

<div class="uk-form-row" style="margin-bottom: 20px;">
    <form action="/watch/log.htm" method="get" class="uk-form">
        <div class="uk-form-row">
            <label>用户ID：</label>
            <input type="number" name="filter[uid]" value="${page.filter.uid}" placeholder="请输入编号">
            <label>手表chipID：</label>
            <input type="text" name="filter[chipID]" value="${page.filter.chipID}"  placeholder="请输入chipID">
            <label>时间：</label>
            <label><input type="text" name="filter[bTime]"  value="${page.filter.bTime}" placeholder="开始日期" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
            -
            <label><input type="text" name="filter[eTime]"  value="${page.filter.eTime}" placeholder="结束日期" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="uk-button uk-button-primary">查询</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
        </div>
    </form>
</div>

<div>
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <td>用户ID</td>
            <td>名称</td>
            <td>手表SN</td>
            <td>时间</td>
            <td>日志文件</td>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="file" items="${page.result}">
            <tr>
                <td>${file.uid}</td>
                <td>${file.title}</td>
                <td>${file.other.chipID}</td>
                <td>
                    <lch:parse-date time="${file.addTime}" pattern="yyyy-MM-dd HH:mm"/>
                </td>
                <td>
                    <a href="http://yyssb.ifitmix.com/${file.fileLink}" download>下载</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<%--分页条--%>
<lch:page page="${page}" href="/watch/log.htm"></lch:page>

<jsp:include page="./add.jsp"/>
