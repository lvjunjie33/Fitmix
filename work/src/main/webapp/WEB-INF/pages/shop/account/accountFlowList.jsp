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
        <form action="/shop/account-flow-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>用户编号：</label>
                    <label class="mix-scene"><input type="number" name="filter[uid]" value="${page.filter.uid}" placeholder="用户编号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                </div>
            </div>
        </form>
    </div>
    <hr class="uk-article-divider">

    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr>
                <th style="width: 50px;">编号</th>
                <th style="width: 100px;">用户ID</th>
                <th style="width: 100px">名称</th>
                <th style="width: 100px">金币</th>
                <th style="width: 200px;">描述</th>
                <th style="width: 100px;">浮动</th>
                <th style="width: 120px;">时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="accountFlow" items="${page.result}">
                <tr>
                    <td><label name="id">${accountFlow.id}</label></td>
                    <td>${accountFlow.uid}</td>
                    <td>${accountFlow.user.name}</td>
                    <td>${accountFlow.coin}</td>
                    <td>${accountFlow.description}</td>
                    <td>
                        <c:if test="${accountFlow.flowType == 0}">
                            增加
                        </c:if>
                        <c:if test="${accountFlow.flowType == 1}">
                            减少
                        </c:if>
                    </td>

                    <td>
                        <lch:parse-date time="${accountFlow.addTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <%--分页条--%>
    <lch:page page="${page}" href="/shop/account-flow-list.htm"></lch:page>
</div>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<%--  sort  --%>