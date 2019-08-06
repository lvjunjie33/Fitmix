<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/4 0004
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="uk-modal uk-open" id="BbsBannerAdd">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加广告</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/bbs/advertisement/advertisement-add.json" enctype="multipart/form-data" method="post" class="uk-form login-form" isform>
                <div class="uk-form-row">
                    <label class="uk-form-label">名称：</label>
                    <textarea name="name" class="uk-width-1-1 uk-form-large" placeholder="名称"></textarea>
                </div>
                <div class="uk-form-row">
                    <label class="uk-form-label">所属模块：</label>
                    <select name="module" class="uk-width-1-1 uk-form-large">
                        <option value="0">首页</option>
                        <option value="1">文章</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <label class="uk-form-label">图片：</label>
                    <input class="uk-width-1-1 uk-form-large" type="file" name="file" placeholder="文件" notnull>
                </div>
                <div class="uk-form-row">
                    <label class="uk-form-label">网址：</label>
                    <input class="uk-width-1-1 uk-form-large" type="text" name="url" placeholder="网址" notnull>
                </div>
                <div class="uk-form-row">
                    <label class="uk-form-label">顺序：</label>
                    <input class="uk-width-1-1 uk-form-large" type="number" name="sort" placeholder="顺序" notnull>
                </div>
                <%--1、文章 2、url 网页 --%>
                <div class="uk-form-row" style="display: none;">
                    <select name="type" class="uk-width-1-1 uk-form-large">
                        <option value="1">图片</option>
                    </select>
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


<script>
    var changIndex = 0;
    $(function(){

//        $("[name=type]").change(function(){
//            var type = $(this).val();
//            var typeEl = $("#type-el");
//            var TemplateTypeValue = $("#TemplateTypeValue").html();
////            1、mix 专辑 2、url 网页 3、单曲 4、电台
//            if (type == 1) {
//                typeEl.html((TemplateTypeValue.format("编号")));
//            } else if(type == 2) {
//                typeEl.html((TemplateTypeValue.format("网页地址")));
//            }
//
//            if (changIndex != 0) {
//                typeEl.find("input").val("");
//            }
//            changIndex++;
//        });
//
//        $("[name=type]").change();
    })
</script>