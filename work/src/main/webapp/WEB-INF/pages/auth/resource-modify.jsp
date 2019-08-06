<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/10
  Time: 下午3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="resource-modify" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Resource modify</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/modify-resource.json" method="post" class="uk-form login-form" id="resource-modify-form" isform>
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
                        <option value="0">顶级</option>
                        <option value="1" selected>子级</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="pid">
                        <c:forEach var="mapResource" items="${menuResourceWithRootMenuMap}">
                            <c:set var="resource" value="${mapResource.key}"/>
                            <option value="${resource.id}">${resource.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="handling" placeholder="handling" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id"/>
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">

    $("#resource-modify-form [name=type]").change(function(e){
        var th = $(this);
        if (th.val() == "0") {
            $("#resource-modify-form [name=pid],#resource-modify-form [name=handling]").hide(0);
        }
        else {
            $("#resource-modify-form [name=pid],#resource-modify-form [name=handling]").show(0);
        }
    });

    $(function(){
        $("[resource-modify]").click(function(){
            var th = $(this);
            var dataJson = th.parent().parent().sequenceJson();
            console.log(dataJson.type);
            $("#resource-modify-form").fillNode(dataJson);
            $("#resource-modify-form [name=type]").change();
            UIkit.modal("#resource-modify").show();
        });
    });
</script>