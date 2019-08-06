<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/6/1
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">
    html{
        width: 100%;
        margin: 0px;
        padding: 0px;
    }
    body {
        width: 100%;
        margin: 0px;
        padding: 0px;
    }
    .container-div-col {
        border:2px solid #aba3a3;
        border-radius:15px;
        padding: 1px;
        width: 400px;
        float: left;
        margin-right: 15px;
        height: 600px;
    }

    .node-div-radius li {
        height: 25px;
        padding-top: 5px;
    }
    .node-div-radius li span {
        margin-top: -5px;
    }

    .col-content {
        border: 2px solid #aba3a3;
        overflow:scroll; /*任何时候都强制显示滚动条*/
        overflow:auto; /*需要的时候会出现滚动条*/
        overflow-x:hidden; /*控制X方向的滚动条*/
        overflow-y:auto; /*控制Y方向的滚动条*/
        height: 700px
    }
</style>


<div>
    <div>
        <h1>话题编辑</h1>
        <div class="uk-form-row">
            <div class="uk-form-row"><h3>${title}</h3></div>

            <div class="uk-form-row">
                <div class="uk-form-row" style="width: 370px">
                    <form action="/file/add.json" method="post" id = "save-img-from" enctype="multipart/form-data">
                        <input class="uk-width-5-6 uk-form-large" type="file" name="uploadFile" notnull title = "upload file">
                        <a style="margin-left: 10px" onclick="mimicry.insertThemeContentImg('add-answer-table-id', 'save-img-from')">插入</a>
                        <input hidden type="submit" value="上传" />
                    </form>
                </div>
                <div class="uk-form-row" style="width: 370px">
                    <textarea class="uk-width-5-6 uk-form-large" name="des" placeholder="文本内容" notnull id = "textarea-id"></textarea>
                    <a style="margin-left: 10px" onclick="mimicry.insertThemeContentText()">插入</a>
                </div>
            </div>
            <div class="uk-form-row">
                <div class="uk-form-row container-div-col col-content">
                    <table style="width: 100%;margin-top: 10px" id = "add-answer-table-id">
                    </table>
                </div>
            </div>
            <div class="uk-form-row">
                <button class="uk-button uk-button-primary" onclick="mimicry.saveTheme()">保存</button>
            </div>
        </div>
    </div>

    <div id="modify-theme" class="uk-modal uk-open">
        <div class="uk-modal-dialog">
            <button type="button" class="uk-modal-close uk-close"></button>
            <h1 style="text-align: center">回答</h1>
            <%--<h2>Some text above the scrollable box</h2>--%>
            <div class="uk-form-row">
                <form action="/file/add.json" method="post" id = "modify-theme-img-from" enctype="multipart/form-data">
                    <div class="uk-form-row">
                        <input class="uk-width-5-6 uk-form-large" type="file" name="uploadFile" notnull title = "upload file">
                        <a onclick="mimicry.modifyThemeContentImg()" style="margin-left: 20px">插入</a>
                    </div>
                </form>
            </div>
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <textarea class="uk-width-5-6 uk-form-large" name="des" placeholder="文本内容" notnull id = "modify-theme-text-textarea"></textarea>
                    <a onclick="mimicry.modifyThemeContentText()" style="margin-left: 20px">插入</a>
                </div>
            </div>
        </div>
    </div>

<div style="width: 0px;height: 0px;margin: 0px;padding: 0px;display: none" id = "text-to-html-id"></div>

<script src="/static/community/mimicry-js.js"></script>
<script>
    mimicry.loadTheme('${themeId}');
</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>