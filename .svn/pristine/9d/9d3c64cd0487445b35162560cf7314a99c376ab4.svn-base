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
    <h2>Keyword Modify</h2>
    <form action="/keyword/keyword-modify.json" method="post" class="uk-form uk-form-horizontal" enctype="multipart/form-data" keyword-modify-form>
        <input type="hidden" name="id" value="${keyword.id}">
        <div class="uk-form-row">
            <label class="uk-form-label">关键字类型：</label>
            <div class="uk-form-controls">
                <select name="type">
                    <option value="1" <c:if test="${keyword.type == 1}"> selected="selected" </c:if> >搜索栏关键字</option>
                    <option value="2" <c:if test="${keyword.type == 2}"> selected="selected" </c:if> >搜索列表关键字</option>
                </select>
            </div>
        </div>

        <div class="uk-form-row">
            <label class="uk-form-label">关键字：</label>
            <div class="uk-form-controls">
                <input type="text" name="name" value="${keyword.name}" class="uk-form-large uk-width-9-10" notnull>
            </div>
        </div>

        <div class="uk-form-row">
            <label class="uk-form-label">排序：</label>
            <div class="uk-form-controls"><input type="text" name="sort" value="${keyword.sort}" class="uk-form-large uk-width-9-10" notnull></div>
        </div>

        <div class="uk-form-row">
            <button type="submit" class="uk-button uk-button-primary" target="keyword-modify-form" style="width: 93%" keyword-modify-submit>modify</button>
        </div>
    </form>
    <br/>
</div>

<script>

(function(){

    $("[keyword-modify-form]").submit(function(){
        var form = $(this);

        form.find("button,input[type=button]").attr("disabled", "true");
        form.ajaxSubmit({
            success: function (d) {
                var code = d['code'];
                if (code == 0) {
                    if (form.attr("not-reload") == "") {
                        UIkit.notify("success", {status:'success', pos:'top-right'});
                        form.find("button,input[type=button]").removeAttr("disabled");
                    }
                    else {
                        UIkit.notify("修改成功", {status:'success', pos:'top-right'});
                        form.find("button,input[type=button]").removeAttr("disabled");
                    }
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

</script>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
