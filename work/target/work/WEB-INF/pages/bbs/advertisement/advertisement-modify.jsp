<%@ taglib prefix="lc" uri="http://tag.lch.com/tags" %>
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
        <h1>修改广告</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/bbs/advertisement/advertisement-modify.json" method="post" enctype="multipart/form-data" class="uk-form uk-form-horizontal" isform>
                <div class="uk-form-row">
                    <label class="uk-form-label">名称</label>
                    <div class="uk-form-controls">
                        <input class="uk-width-1-1 uk-form-large" type="text" name="name" value="${advertisement.name}" placeholder="标题" notnull>
                    </div>
                </div>
                <div class="uk-form-row">
                    <label class="uk-form-label">所属模块</label>
                    <div class="uk-form-controls">
                        <select name="module" class="uk-width-1-1 uk-form-large">
                            <option value="0" <c:if test="${advertisement.module == 0}">selected</c:if> >首页</option>
                            <option value="1" <c:if test="${advertisement.module == 1}">selected</c:if>>文章</option>
                        </select>
                    </div>
                </div>
                <div class="uk-form-row">
                    <label class="uk-form-label">图片</label>
                    <div class="uk-form-controls">
                        <input class="uk-width-1-1 uk-form-large" type="file" name="file" placeholder="文件">
                        <img src="<lc:build-path url="${advertisement.imageUrl}"/>" style="width: 260px;height: 210px;" />
                    </div>

                </div>

                <div class="uk-form-row">
                    <label class="uk-form-label">网址</label>
                    <div class="uk-form-controls">
                        <input class="uk-width-1-1 uk-form-large" type="text" name="url" value="${advertisement.url}" placeholder="网址" notnull>
                    </div>
                </div>

                <div class="uk-form-row">
                    <label class="uk-form-label">排序</label>
                    <div class="uk-form-controls">
                        <input class="uk-width-1-1 uk-form-large" type="number" name="sort" value="${advertisement.sort}" placeholder="顺序" notnull>
                    </div>
                </div>
                <div class="uk-form-row">
                    <label class="uk-form-label">状态</label>
                    <div class="uk-form-controls">
                        <select name="status" class="uk-width-1-1 uk-form-large" data="${advertisement.status}">
                            <option value="0">发布</option>
                            <option value="1">未发布</option>
                        </select>
                    </div>
                </div>
                <%--1、mix 专辑 2、url 网页 3、单曲 4、电台--%>
                <div class="uk-form-row" style="display: none;">
                    <label class="uk-form-label">类型</label>
                    <div class="uk-form-controls">
                        <select name="type" class="uk-width-1-1 uk-form-large" data="${advertisement.type}">
                            <option value="1">图片</option>
                        </select>
                    </div>
                </div>

                <div class="uk-form-row uk-text-right">
                    <input type="hidden" value="${advertisement.id}" name="id"/>
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    <%--</div>--%>
<%--</div>--%>


<script type="text/html" id="TemplateTypeValue">
    <div class="uk-form-row">
        <label class="uk-form-label">类型值</label>
        <div class="uk-form-controls">
            <input class="uk-width-1-1 uk-form-large" type="text" name="typeValue" value="${banner.typeValue}" placeholder="{0}" notnull>
        </div>
    </div>
</script>

<script>
    var changIndex = 0;
    $(function(){

        $("[name=status]").val($("[name=status]").attr("data"));
        $("[name=type]").val($("[name=type]").attr("data"));
        $("[name=channel]").val($("[name=channel]").attr("data"));

//        $("[name=type]").change(function(){
//            var type = $(this).val();
//            var typeEl = $("#type-el");
//            var TemplateTypeValue = $("#TemplateTypeValue").html();
////            1、mix 专辑 2、url 网页 3、单曲 4、电台
//            if (type == 1) {
//                typeEl.html((TemplateTypeValue.format("文章编号")));
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

