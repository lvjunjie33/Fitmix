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
        <form action="/shop/search.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>金币剩余量：</label>
                    <label><input type="text" name="filter[residualCoinMin]" value="${page.filter.residualCoinMin}" /></label>
                    <label><input type="text" name="filter[residualCoinMax]" value="${page.filter.residualCoinMax}"/></label>
                </div>
                <div class="uk-form-row">
                    <label>金币消耗量：</label>
                    <label><input type="number" name="filter[spendCoinMin]" value="${page.filter.spendCoinMin}" /></label>
                    <label><input type="number" name="filter[spendCoinMax]" value="${page.filter.spendCoinMax}" /></label>
                </div>
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
                <th style="width: 200px;">用户ID</th>
                <th style="width: 100px">名称</th>
                <th style="width: 100px">金币剩余量</th>
                <th style="width: 100px;">金币消耗量</th>
                <th style="width: 120px;">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="account" items="${page.result}">
                <tr>
                    <td><label name="id">${account.uid}</label></td>
                    <td>${account.user.name}</td>
                    <td>${account.coin}</td>
                    <td>${account.spendCoin}</td>

                    <td>
                        <a href="javascript:" account-flow style="color:#ff4a35;" data-uid="${account.uid}">详情</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <%--分页条--%>
    <lch:page page="${page}" href="/shop/search.htm"></lch:page>
</div>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<%--  sort  --%>

<script>

    $(function () {
        $("a[account-flow]").on("click", function () {
            location.href = "/shop/account-flow-list.htm?filter%5Buid%5D=" + $(this).data("uid");
        })
    })

</script>