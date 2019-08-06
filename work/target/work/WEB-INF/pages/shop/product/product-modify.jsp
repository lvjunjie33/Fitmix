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
    <h2>修改产品信息</h2>
    <form action="/shop/product/product-modify.json" method="post" class="uk-form  uk-form-horizontal" enctype="multipart/form-data" product-add-form>
        <div class="<%--uk-container--%>">
            <input type="hidden" value="${product.id}" name="id">
            <div class="uk-form-row">
                <label class="uk-form-label">名称：</label>
                <div class="uk-form-controls">
                    <input type="text" name="name" value="${product.name}" class="uk-form-large uk-width-9-10" notnull>
                </div>
            </div>

            <div class="uk-form-row">
                <label class="uk-form-label">金币：</label>
                <div class="uk-form-controls">
                    <input type="text" name="coin" value="${product.coin}" class="uk-form-large uk-width-9-10" notnull>
                </div>
            </div>

            <div class="uk-form-row">
                <label class="uk-form-label">库存：</label>
                <div class="uk-form-controls">
                    <input type="text" name="quantity" value="${product.quantity}" class="uk-form-large uk-width-9-10" notnull>
                </div>
            </div>

            <%--<div class="uk-form-row">--%>
                <%--<label class="uk-form-label">类型：</label>--%>
                <%--<div class="uk-form-controls">--%>
                    <%--<select name="type">--%>
                        <%--<option value="0">流米流量</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>

            <%--<div class="uk-form-row">--%>
                <%--<label class="uk-form-label">套餐类型：</label>--%>
                <%--<div class="uk-form-controls">--%>
                    <%--<select name="virtualKey">--%>
                        <%--<option value="YD10">移动10M</option>--%>
                        <%--<option value="YD30">移动30M</option>--%>
                        <%--<option value="YD70">移动70M</option>--%>
                        <%--<option value="YD150">移动150M</option>--%>
                        <%--<option value="YD500">移动500M</option>--%>
                        <%--<option value="YD1024">移动1024M</option>--%>
                        <%--<option value="DX5">电信5M</option>--%>
                        <%--<option value="DX10">电信10M</option>--%>
                        <%--<option value="DX30">电信30M</option>--%>
                        <%--<option value="DX50">电信50M</option>--%>
                        <%--<option value="DX100">电信100M</option>--%>
                        <%--<option value="DX200">电信200M</option>--%>
                        <%--<option value="DX500">电信500M</option>--%>
                        <%--<option value="DX1024">电信1024M</option>--%>
                        <%--<option value="LT20">联通20M</option>--%>
                        <%--<option value="LT50">联通50M</option>--%>
                        <%--<option value="LT100">联通100M</option>--%>
                        <%--<option value="LT200">联通200M</option>--%>
                        <%--<option value="LT500">联通500M</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>



            <div class="uk-form-row">
                <button type="submit" class="uk-button uk-button-primary" target="product-add-form" style="width: 93%" product-add-submit>add</button>
            </div>

        </div>
    </form>
    <br/>
</div>


<%--  setting file  --%>
<div id="setting-surprise-image" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h3>设置 静态 图片</h3>
        <a href="/surprise/surprise-modify.htm">setting</a>
    </div>
</div>


<style type="text/css">
    .progress{
        background: #ddd;
        min-height: 1px;
        max-height: 1px;
        width: 100%;
        display: none;
    }

    .progress-bar{
        background: #00a8e6;
        min-height: 1px;
        max-height: 1px;
        color: #00a8e6;
        width: 0%;
    }

</style>
<script>

(function(){

    var virtualKey = '${product.virtualKey}';

    $("select[name=virtualKey] option").each(function() {
        if($(this).val() == virtualKey) {
            $(this).attr("selected", "selected");
        }
    });


    $("[product-add-form]").submit(function(){
        var form = $(this);
        form.ajaxSubmit({
            success: function (d) {
                var code = d['code'];
                if (code == 0) {
                    UIkit.notify("success", {status:'success', pos:'top-right'});
                    form.find("button,input[type=button]").removeAttr("disabled");
                }
                else {
                    form.find("button,input[type=button]").removeAttr("disabled");
                    UIkit.notify(code + d["msg"], {status:'danger', pos:'top-right'});
                }
            }
        });
        return false;
    });

})($);


$(function(){
//    $(".mix-scene")

    $("[name=bpm]").change(function(){
        var th = $(this);
        var value = $.trim(th.val());
        if (!/\s/.test(value)) {

            value = parseInt(value);
            console.info(typeof value);
            $(".mix-scene").find(":checkbox").removeAttr("checked");
            if (value >= 180) {
                $(".mix-scene").find("[value=1]").attr("checked", "true");
            }
            else if (value >= 140 && value < 180) {
                $(".mix-scene").find("[value=2]").attr("checked", "true");
            }
            else if (value >= 120 && value < 140) {
                $(".mix-scene").find("[value=3]").attr("checked", "true");
            }
            else if (value < 120) {
                $(".mix-scene").find("[value=4]").attr("checked", "true");
            }
        }
    });
});

</script>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
