<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/25
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
    .file{
        margin-bottom: 20px;;
    }

    .div-a-style {
        margin-right: 20px;
    }
</style>

<div class="uk-form-row" style="margin-bottom: 20px;">
    <a href="#announcement-add-1" class="uk-button div-a-style" data-uk-modal>添加系统公告</a>
    <a href="#announcement-add-2" class="uk-button div-a-style" data-uk-modal>广告外链公告</a>
    <a href="#announcement-add-3" class="uk-button div-a-style" data-uk-modal>话题公告</a>
    <a href="#announcement-add-4" class="uk-button div-a-style" data-uk-modal>电台音乐公告</a>
    <a href="#announcement-add-5" class="uk-button div-a-style" data-uk-modal>赛事公告</a>
</div>

<div>
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <td>编号</td>
            <td>公告图</td>
            <td>次数</td>
            <td>开始时间</td>
            <td>结束时间</td>
            <td>描述</td>
            <td>其他</td>
            <td>添加时间</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="announ" items="${page.result}">
            <tr>
                <td>${announ.id}</td>
                <td><img width="70" src="<lch:build-path url='${announ.imgLink}'/>" /></td>
                <td>${announ.displayNum}</td>
                <td><lch:parse-date time="${announ.bTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><lch:parse-date time="${announ.eTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>${announ.desc}</td>
                <td>
                    <c:choose>
                        <c:when test="${announ.type == 1}"></c:when>
                        <c:when test="${announ.type == 2}"><a href="${announ.body}">外链地址：${announ.body}</a></c:when>
                        <c:when test="${announ.type == 3}"><a href="/theme/to-detail.htm?id=${announ.body}">话题编号：${announ.body}</a></c:when>
                        <c:when test="${announ.type == 4}"><a href="/mix/mix-info.htm?id=${announ.body}">Mix编号：${announ.body}</a></c:when>
                        <c:when test="${announ.type == 5}"><a href="/activity/to-activity.htm?activityId=${announ.body}">赛事编号：${announ.body}</a></c:when>
                    </c:choose>
                </td>
                <td><lch:parse-date time="${announ.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><a href="/announcement/modify.htm?id=${announ.id}" target="_blank" >修改</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<%--分页条--%>
<lch:page page="${page}" href="/announcement/page.htm"></lch:page>

<jsp:include page="./add.jsp"/>
