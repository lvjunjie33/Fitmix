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
    <h2>ChannelApp Add</h2>
    <form action="/channel-app/channel-app-add.json" method="post" class="uk-form uk-form-horizontal" enctype="multipart/form-data" channel-app-add-form>

            <div class="uk-form-row">
                <label class="uk-form-label">渠道ID：</label>
                <div class="uk-form-controls">
                    <select name="channelId" class="uk-form-large uk-width-9-10">
                        <c:forEach var="dictionary" items="${dictionaryList}">
                            <option value="${dictionary.value}">${dictionary.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <%--<div class="uk-form-row">--%>
                <%--<label class="uk-form-label">安卓Apk名称：</label>--%>
                <%--<div class="uk-form-controls"><input type="text" name="androidApkName" class="uk-form-large uk-width-9-10" notnull></div>--%>
            <%--</div>--%>

            <div class="uk-form-row">
                <label class="uk-form-label">苹果链接：</label>
                <div class="uk-form-controls"><input type="text" name="iosUrl" class="uk-form-large uk-width-9-10" notnull></div>
            </div>

            <div class="uk-form-row">
                <button type="submit" class="uk-button uk-button-primary" target="role-add-form" style="width: 93%" channel-app-add-submit>add</button>
            </div>
    </form>
    <br/>
</div>

<%--  setting file  --%>
<div id="setting-channel-app-file" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h3>设置 安卓包 文件</h3>
        <a href="/channel-app/channel-app-modify.htm">setting</a>
    </div>
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

                        var settingModel = $("#setting-channel-app-file");
                        var href = settingModel.find("a").attr("href") + "?id=" + d["channelApp"]["id"];
                        settingModel.find("a").attr("href", href);

                        var modal = UIkit.modal("#setting-channel-app-file");
                        if ( modal.isActive() ) {
                            modal.hide();
                        } else {
                            modal.show();
                        }
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
