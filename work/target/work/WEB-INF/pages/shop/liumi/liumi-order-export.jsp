<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="liumi-export-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>请选择导出日期</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/shop/liumi/liumi-order-export.json" method="get" class="uk-form">
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" data-uk-datepicker="{format:'YYYY-MM-DD'}" name="startTime" placeholder="开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" data-uk-datepicker="{format:'YYYY-MM-DD'}" name="endTime" placeholder="结束时间" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <a id="submit" class="uk-button uk-button-primary">submit</a>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){

        $("#submit").click(function(){
            window.location.href = "/shop/liumi/liumi-order-export.json?startTime="+$("input[name=startTime]").val()+"&endTime="+$("input[name=endTime]").val();
        });
    });
</script>

