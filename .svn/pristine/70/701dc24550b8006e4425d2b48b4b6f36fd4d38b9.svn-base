<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/4 0004
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<%--<div class="uk-modal uk-open" id="mixBannerModfy">--%>
<%--<div class="uk-modal-dialog">--%>
<%--<button type="button" class="uk-modal-close uk-close"></button>--%>
<h1>Mix Banner Modify</h1>
<%--<h2>Some text above the scrollable box</h2>--%>
<div class="uk-form-row">
    <form action="/mix-banner/modify.json" method="post" class="uk-form login-form" isform>
        <div class="uk-form-row">
            <input class="uk-width-1-1 uk-form-large" type="file" name="file" placeholder="文件">
        </div>
        <div class="uk-form-row">
            <input class="uk-width-1-1 uk-form-large" type="text" name="title" value="${data.title}" placeholder="标题" notnull>
        </div>
        <div class="uk-form-row">
            <input class="uk-width-1-1 uk-form-large" type="number" name="sort" value="${data.sort}" placeholder="顺序" notnull>
        </div>
        <div class="uk-form-row">
            <select name="status" class="uk-width-1-1 uk-form-large" data="${data.status}">
                <option value="0">发布</option>
                <option value="1">未发布</option>
            </select>
        </div>
        <%--1、mix 专辑 2、url 网页 3、单曲 4、电台--%>
        <div class="uk-form-row">
            <select name="type" class="uk-width-1-1 uk-form-large" data="${data.type}">
                <%--<option value="1">专辑</option>
                <option value="2">网页</option>--%>
                <option value="3">单曲</option>
<%--                <option value="4">电台</option>--%>
            </select>
        </div>

        <div id="type-el"  class="uk-form-row">

        </div>

        <div class="uk-form-row">
            <textarea name="desc" class="uk-width-1-1 uk-form-large" placeholder="描述">${data.desc}</textarea>
        </div>
        <div class="uk-form-row uk-text-right">
            <input type="hidden" value="${data.id}" name="bannerId"/>
            <input type="hidden" value="${data.channel}" name="channel"/>
            <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
            <button class="uk-button uk-modal-close">cancel</button>
        </div>
    </form>
</div>
<%--</div>--%>
<%--</div>--%>


<script type="text/html" id="TemplateTypeValue">
    <div class="uk-form-row">
        <input class="uk-width-1-1 uk-form-large" type="text" name="typeValue" value="${data.typeValue}" placeholder="{0}" notnull>
    </div>
</script>

<script>
    var changIndex = 0;
    $(function(){

        $("[name=status]").val($("[name=status]").attr("data"));
        $("[name=type]").val($("[name=type]").attr("data"));

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
            }

            if (changIndex != 0) {
                typeEl.find("input").val("");
            }
            changIndex++;
        });

        $("[name=type]").change();
    })
</script>

