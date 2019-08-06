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
        <form action="/shop/liumi/liumi-order-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>订单号：</label>
                    <label class="mix-scene"><input type="number" name="filter[orderNo]" value="${page.filter.orderNo}" placeholder="订单号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>外部订单号：</label>
                    <label class="mix-scene"><input type="number" name="filter[liumiOrderNo]" value="${page.filter.liumiOrderNo}" placeholder="外部订单号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>用户ID：</label>
                    <label class="mix-scene"><input type="number" name="filter[uid]" value="${page.filter.uid}" placeholder="用户ID"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>手机号：</label>
                    <label class="mix-scene"><input type="number" name="filter[mobile]" value="${page.filter.mobile}" placeholder="手机号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>下单日期：</label>
                    <label class="mix-scene"><input type="text" data-uk-datepicker="{format:'YYYY-MM-DD'}" name="filter[addTime]" value="${page.filter.addTime}" placeholder="下单日期"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>套餐：</label>
                    <label class="mix-scene">
                        <%--<input type="text" name="filter[postpackage]" value="${page.filter.postpackage}" placeholder="套餐">--%>
                        <select id="postpackage" name="filter[postpackage]">
                            <option value="">全部</option>
                            <option value="YD10">移动10M</option>
                            <option value="YD30">移动30M</option>
                            <option value="YD70">移动70M</option>
                            <option value="YD150">移动150M</option>
                            <option value="YD500">移动500M</option>
                            <option value="YD1024">移动1024M</option>
                            <option value="DX5">电信5M</option>
                            <option value="DX10">电信10M</option>
                            <option value="DX30">电信30M</option>
                            <option value="DX50">电信50M</option>
                            <option value="DX100">电信100M</option>
                            <option value="DX200">电信200M</option>
                            <option value="DX500">电信500M</option>
                            <option value="DX1024">电信1024M</option>
                            <option value="LT20">联通20M</option>
                            <option value="LT50">联通50M</option>
                            <option value="LT100">联通100M</option>
                            <option value="LT200">联通200M</option>
                            <option value="LT500">联通500M</option>
                        </select>
                    </label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="#liumi-export-modal" data-uk-modal class="uk-button uk-button-primary">导出</a>
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
            <th style="width: 100px;">订单号</th>
            <th style="width: 200px;">外部订单号</th>
            <th style="width: 100px">用户名</th>
            <th style="width: 100px">所需金币</th>
            <th style="width: 100px">手机号</th>
            <th style="width: 100px">库存流量包</th>
            <th style="width: 100px">状态</th>
            <th style="width: 100px">创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="liumiOrder" items="${page.result}">
            <tr>
                <td>${liumiOrder.orderNo}</td>
                <td>${liumiOrder.liumiOrderNo}</td>
                <td>
                    ${liumiOrder.uid}
                </td>
                <td>
                    ${liumiOrder.coin}
                </td>
                <td>
                    ${liumiOrder.mobile}
                </td>
                <td>${liumiOrder.postpackage}</td>
                <td>
                    <c:choose>
                        <c:when test="${liumiOrder.status == 'CREATED'}">已下单</c:when>
                        <c:when test="${liumiOrder.status == 'SUCCESS'}">充值成功</c:when>
                        <c:when test="${liumiOrder.status == 'FAIL'}">充值失败</c:when>
                    </c:choose>
                </td>
                <td><lch:parse-date time="${liumiOrder.addTime}" pattern="yyyy-MM-dd HH:mm:ss"></lch:parse-date></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/shop/liumi/liumi-order-list.htm"></lch:page>
<script>
    var postpackage = '${page.filter.postpackage}';
    $("#postpackage option").each(function(){
        if($(this).val() == postpackage) {
            $(this).attr("selected", "selected");
        }
    });

    $(function(){
        $("[shelves]").on("click", shelves);
        $("[no-shelves]").on("click", onShelves);

        function shelves () {
            if (window.confirm("确认上架？")) {
                var th = $(this);
                $.post("/product/product-modify.json", {id:th.data("id"),status: 0}, function(result){
                    var code = result['code'];
                    if (code == 0) {
                        th.replaceWith('<a data-id="'+th.data("id")+'" href="javascript:" no-shelves>下架</a>');
                        $("[no-shelves]").on("click", onShelves);
                    } else{
                        UIkit.notify(result['msg'], {status:'danger', pos:'top-right'});
                    }
                });
            }
        }

        function onShelves () {
            if (window.confirm("确认下架？")) {
                var th = $(this);
                $.post("/product/product-modify.json", {id:th.data("id"),status: 1}, function(result){
                    var code = result['code'];
                    if(code ==0) {
                        th.replaceWith('<a data-id="'+th.data("id")+'" href="javascript:" shelves>上架</a>');
                        $("[shelves]").on("click", shelves);
                    } else {
                        UIkit.notify(result['msg'], {status:'danger', pos:'top-right'});
                    }

                });
            }
        }
    });


</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
<%@ include file="liumi-order-export.jsp" %>
