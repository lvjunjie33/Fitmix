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
        <form action="/pay-order/pay-order-list.htm" method="post" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>平台订单号：</label>
                    <label class="mix-scene"><input type="text" name="filter[orderNo]" value="${page.filter.orderNo}" placeholder="平台订单号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <label>第三方订单号：</label>
                    <label class="mix-scene"><input type="text" name="filter[platformNo]" value="${page.filter.platformNo}" placeholder="第三方订单号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
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
            <th style="width: 100px;">编号</th>
            <th style="width: 200px;">平台订单号</th>
            <th style="width: 200px;">第三方订单号</th>
            <th style="width: 100px;">支付平台/支付方式/支付名称</th>
            <th style="width: 100px">支付金额</th>
            <th style="width: 100px">支付状态</th>
            <th style="width: 100px">下单时间</th>
            <th style="width: 100px">支付成功时间</th>
            <th style="width: 100px">创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="payOrder" items="${page.result}">
            <tr>
                <td><label name="id">${payOrder.id}</label></td>
                <td>${payOrder.orderNo}</td>
                <td>${payOrder.platformNo}</td>
                <td>
                        <c:choose>
                            <c:when test="${payOrder.payPlatform == 1}">
                                微信
                            </c:when>
                            <c:when test="${payOrder.payPlatform == 2}">
                                支付宝
                            </c:when>
                        </c:choose>
                    <br/>
                        <c:choose>
                            <c:when test="${payOrder.paymentMethod == 1}">
                                微信支付 - 公众号授权支付
                            </c:when>
                            <c:when test="${payOrder.paymentMethod == 2}">
                                阿里支付 - 移动 h5 支付
                            </c:when>
                        </c:choose>
                    <br/>
                        ${payOrder.payName}
                </td>
                <td>${payOrder.payMoney}</td>
                <td>
                    <c:choose>
                        <c:when test="${payOrder.payState == 0}">
                            创建订单
                        </c:when>
                        <c:when test="${payOrder.payState == 1}">
                            等待支付
                        </c:when>
                        <c:when test="${payOrder.payState == 2}">
                            支付成功
                        </c:when>
                        <c:when test="${payOrder.payState == 3}">
                            支付异常
                        </c:when>
                    </c:choose>
                </td>
                <td><lch:parse-date time="${payOrder.placeOrderTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><lch:parse-date time="${payOrder.successTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><lch:parse-date time="${payOrder.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/pay-order/pay-order-list.htm"></lch:page>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
