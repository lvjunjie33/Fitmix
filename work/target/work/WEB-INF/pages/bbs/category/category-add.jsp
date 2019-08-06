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
    <h2>添加分类信息</h2>
    <form action="/bbs/category/category-add.json" method="post" class="uk-form uk-form-horizontal" channel-app-add-form>

            <div class="uk-form-row">
                <label class="uk-form-label">分类名称：</label>
                <div class="uk-form-controls"><input type="text" name="name" class="uk-form-large uk-width-9-10" notnull></div>
            </div>

            <div class="uk-form-row">
                <button type="submit" class="uk-button uk-button-primary" target="role-add-form" style="width: 93%" channel-app-add-submit>add</button>
            </div>
    </form>
    <br/>
</div>


<script>

(function(){

    $("[channel-app-add-form]").submit(function(){
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
                        UIkit.notify("添加成功", {status:'success', pos:'top-right'});
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
