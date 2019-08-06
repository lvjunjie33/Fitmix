<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/4 0004
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="uk-modal uk-open" id="mixBannerAdd">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Mix Banner Add</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/mix-banner/add.json" method="post" class="uk-form login-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="file" placeholder="文件" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" placeholder="标题" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="sort" placeholder="顺序" notnull>
                </div>
                <div>
                    <select name="channel" class="uk-width-1-1 uk-form-large">
                        <option value="1">平台</option>
                        <option value="2">万德</option>
                    </select>
                </div>
                <%--1、mix 专辑 2、url 网页 3、单曲 4、电台--%>
                <div class="uk-form-row">
                    <select name="type" class="uk-width-1-1 uk-form-large">
                        <option value="1">专辑</option>
                        <option value="2">网页</option>
                        <option value="3">单曲</option>
                        <option value="4">电台</option>
                        <option value="5">三方平台</option>
                    </select>
                </div>

                <div id="type-el"  class="uk-form-row">

                </div>

                <div id="schemes_url"  class="uk-form-row">

                </div>
                <div class="uk-form-row">
                    <textarea name="desc" class="uk-width-1-1 uk-form-large" placeholder="描述"></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/html" id="TemplateTypeValue">
    <div class="uk-form-row">
        <input class="uk-width-1-1 uk-form-large" type="text" name="typeValue" value="" placeholder="{0}" notnull>
    </div>
</script>

<script type="text/html" id="SchemesUrlTemplate">
    <div class="uk-form-row">
        <input class="uk-width-1-1 uk-form-large" type="text" name="iosSchemesUrl" value="" placeholder="IOS启动地址" notnull>
        <input class="uk-width-1-1 uk-form-large" type="text" name="androidSchemesUrl" value="" placeholder="ANDROID启动地址" notnull>
    </div>
</script>
<script>
    var changIndex = 0;
    $(function(){

        $("[name=type]").change(function(){
            var type = $(this).val();
            var typeEl = $("#type-el");
            var TemplateTypeValue = $("#TemplateTypeValue").html();
//            1、mix 专辑 2、url 网页 3、单曲 4、电台
            if (type == 1) {
                typeEl.html((TemplateTypeValue.format("专辑编号")));
            } else if(type == 2) {
                typeEl.html((TemplateTypeValue.format("网页地址")));
            } else if(type == 3) {
                typeEl.html((TemplateTypeValue.format("单曲编号")));
            } else if(type == 4) {
                typeEl.html((TemplateTypeValue.format("电台编号")));
            } else if(type == 5) {
                typeEl.html((TemplateTypeValue.format("网页地址")));
            }


            if(type == 5) {
                $("#schemes_url").html($("#SchemesUrlTemplate").html())
            } else {
                $("#schemes_url").html("");
            }

            if (changIndex != 0) {
                typeEl.find("input").val("");
            }
            changIndex++;
        });

        $("[name=type]").change();
    })
</script>