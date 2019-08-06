<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/10
  Time: 下午3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="operation-modify-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Operation Modify</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/modify-resource.json" method="post" class="uk-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="name" placeholder="name" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="sort" placeholder="sort" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="extensionName" placeholder="extensionName" notnull>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="type">
                        <%--<option value="0">顶级</option>--%>
                        <option value="2" selected>操作</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="pid">
                        <c:forEach var="resource" items="${resourceChildren}">
                            <option value="${resource.id}">${resource.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="handling" placeholder="handling" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id"/>
                    <button class="uk-button uk-button-primary">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">

    $("#operation-resource-modify [name=type]").change(function(e){
        var th = $(this);
        if (th.val() == "0") {
            $("#operation-resource-modify [name=pid],#operation-resource-modify [name=handling]").hide(0);
        }
        else {
            $("#operation-resource-modify [name=pid],#operation-resource-modify [name=handling]").show(0);
        }
    });

    $(function(){
        $("[operation-modify]").click(function(){
            var th = $(this);
            var dataJson = th.parent().parent().sequenceJson();
            $("#operation-modify-modal").fillNode(dataJson);
            $("#operation-modify-modal [name=type]").change();
            UIkit.modal("#operation-modify-modal").show();
        });
    });
</script>