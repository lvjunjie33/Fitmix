<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/4 0004
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="uk-modal uk-open" id="albumModalModify">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Mix Banner Add</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/mix-album/modify.json" method="post" class="uk-form login-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="file" placeholder="文件">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" value="${data.title}" placeholder="标题" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="mixIds" value="${data.mixIds}" placeholder="歌曲编号多个 , 号隔开" notnull>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="status" data="${data.status}">
                        <option value="0">发布</option>
                        <option value="1">未发布</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <textarea name="desc" class="uk-width-1-1 uk-form-large" placeholder="描述">${data.desc}</textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="albumId" value="${data.id}">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    $(function(){
        $("[name=status]").val($("[name=status]").attr("data"))
    });
</script>


