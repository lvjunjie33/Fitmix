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
        <form action="/club/club-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>俱乐部ID：</label>
                    <label class="mix-scene"><input type="number" name="filter[id]" value="${page.filter.id}" placeholder="俱乐部Id"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>用户ID：</label>
                    <label class="mix-scene"><input type="number" name="filter[uid]" value="${page.filter.uid}" placeholder="用户Id"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>名称：</label>
                    <label class="mix-scene"><input type="text" name="filter[name]" value="${page.filter.name}" placeholder="俱乐部名称"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/club/club-export.htm?filter[uid]=${page.filter.uid}&filter[id]=${page.filter.id}&filter[name]=${page.filter.name}&pageNo=${page.pageNo}" class="uk-button uk-button-primary">导出查询结果</a>
                </div>
            </div>
        </form>
    </div>
    <hr class="uk-article-divider">

    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr>
                <th style="width: 100px;">俱乐部ID</th>
                <th style="width: 200px;">俱乐部名称</th>
                <th style="width: 100px">创建时间</th>
                <th style="width: 100px">成员人数</th>
                <th style="width: 100px">最大人数</th>
                <th style="width: 100px">创建人</th>
                <th style="width: 100px">创建人ID</th>
                <th style="width: 100px">状态</th>
                <th style="width: 200px">描述</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="club" items="${page.result}">
                <tr>
                    <td><label name="id">${club.id}</label></td>
                    <td><a href="/club/club-info.htm?id=${club.id}" target="_blank">${club.name}</a></td>
                    <td><lch:parse-date time="${club.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${club.memberCount}</td>
                    <td>${club.maxMember}</td>
                    <td>${club.user.name}</td>
                    <td>${club.user.id}</td>
                    <td>
                        <c:choose>
                            <c:when test="${club.status == 0}">正常</c:when>
                            <c:when test="${club.status == 1}"><span style="color: red">无效</span></c:when>
                        </c:choose>
                    </td>
                    <td>${club.desc}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <%--分页条--%>
    <lch:page page="${page}" href="/club/club-list.htm"></lch:page>
</div>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

