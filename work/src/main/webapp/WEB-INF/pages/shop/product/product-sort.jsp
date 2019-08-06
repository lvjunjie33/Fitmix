<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="product-sort-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Product Sort</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/shop/product/product-modify.json" method="post" class="uk-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="sort" placeholder="sort" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id"/>
                    <button class="uk-button uk-button-primary">submit</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){

        $("[product-sort]").click(function(){
            var th = $(this);
            $("input[name=sort]").val(th.data("sort"));
            $("input[name=id]").val(th.data("id"));
            UIkit.modal("#product-sort-modal").show();
        });
    });
</script>

