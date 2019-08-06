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
        <form action="/shop/product/product-list.htm" method="post" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>产品编号：</label>
                    <label class="mix-scene"><input type="number" name="filter[id]" value="${page.filter.id}" placeholder="产品编号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label class="mix-scene"><input type="text" name="filter[name]" value="${page.filter.name}" placeholder="产品名称"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/shop/product/product-add.htm" target="_blank" class="uk-button uk-button-primary">添加产品</a>
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
            <th style="width: 100px;">产品编号</th>
            <th style="width: 200px;">产品名称</th>
            <th style="width: 100px">所需金币</th>
            <th style="width: 100px">类型</th>
            <th style="width: 100px">库存</th>
            <th style="width: 100px">排序</th>
            <th style="width: 100px">创建时间</th>
            <th style="width: 120px">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${page.result}">
            <tr>
                <td><label name="id">${product.id}</label></td>
                <td>${product.name}</td>
                <td>
                    ${product.coin}
                </td>
                <td>
                    <c:choose>
                        <c:when test="${product.type == 0}">流米流量</c:when>
                    </c:choose>
                </td>
                <td>${product.quantity}</td>
                <td>${product.sort}</td>
                <td><lch:parse-date time="${product.addTime}" pattern="yyyy-MM-dd HH:mm:ss"></lch:parse-date></td>
                <td>
                    <lch:auth-operation url="/mix/mix-sort.json">
                        <a href="#product-sort-modal" data-uk-modal product-sort data-id="${product.id}" data-sort="${product.sort}">sort</a>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/shop/product/product-modify.json">
                        <c:choose>
                            <c:when test="${product.status == 0}"><a data-id="${product.id}" href="javascript:" no-shelves>下架</a></c:when>
                            <c:when test="${product.status == 1}"><a data-id="${product.id}" href="javascript:" shelves>上架</a></c:when>
                        </c:choose>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/product/product-modify.json">
                        <a href="/shop/product/product-modify.htm?id=${product.id}" product-modify style="color:#ff4a35;" data-id="${product.id}">修改</a>
                    </lch:auth-operation>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/shop/product/product-list.htm"></lch:page>
<script>


    $(function(){
        $("[shelves]").on("click", shelves);
        $("[no-shelves]").on("click", onShelves);

        function shelves () {
            if (window.confirm("确认上架？")) {
                var th = $(this);
                $.post("/shop/product/product-modify.json", {id:th.data("id"),status: 0}, function(result){
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
                $.post("/shop/product/product-modify.json", {id:th.data("id"),status: 1}, function(result){
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
<%--  sort  --%>
<%@ include file="product-sort.jsp" %>