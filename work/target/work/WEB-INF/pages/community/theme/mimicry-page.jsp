<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/8/1
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-uikit.jsp" %>

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
    .container-div {
        width: 100%;
        height: 100%;
        padding-left: 0px;
        padding-top: 0px;
    }

    .container-div-col {
        border:2px solid #aba3a3;
        border-radius:15px;
        padding: 1px;
        width: 400px;
        float: left;
        margin-right: 0px;
    }

    .col-content {
        border: 2px solid #aba3a3;
        overflow:scroll; /*任何时候都强制显示滚动条*/
        overflow:auto; /*需要的时候会出现滚动条*/
        overflow-x:hidden; /*控制X方向的滚动条*/
        overflow-y:auto; /*控制Y方向的滚动条*/
        height: 700px
    }

    .node-div {
        margin: 0px;
        box-shadow: 0px 0px 10px #9c3010;
    }

    .node-div-radius {
        border-radius:15px;
        height: 30px;
        padding-top: 2px;
    }

    .node-div-radius li {
        height: 25px;
        padding-top: 5px;
    }
    .node-div-radius li span {
        margin-top: -5px;
    }

    .radius-img {
        border-radius: 15px;
        width: 30px;
        height: 30px;
        margin-bottom: 10px;
    }

    .radius-img-2 {
        border-radius: 15px;
        width: 30px;
        height: 30px;
    }

    .label-style {
        font-size: 12px;
    }

    .div3-style {
        margin: 0px;
        padding-top: 2px;
        padding-bottom: 2px;
        padding-left: 3px;
        margin-left: 5px;
    }

    .table-font-9 {
        width: 100%;
        font-size: 9px;
    }

    .table-style {
        width: 100%;
    }

    .table-style-alert {
        list-style-type: none;
        margin: 0px;
        padding: 0px;
        width: 100%;
        min-height: 100px;
    }
    .content-style img{
        width: 100%;
    }
</style>

<div class="uk-form-row container-div" style="width: 1630px">

    <div class="uk-form-row">
        <%--主页--%>
        <div class="container-div-col">
            <div class="uk-form-row node-div node-div-radius" style="text-align: right">
                <table>
                    <tr>
                        <td style="width: 270px;text-align: center">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            主页
                        </td>
                        <td style="width: 50px;text-align: center">
                            <%--<a>刷新</a>--%>
                        </td>
                        <td>
                            <%--<a>X</a>--%>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="uk-form-row node-div node-div-radius" style="text-align: right;height: 38px">
                <table>
                    <tr>
                        <td colspan="3">
                            <form action="/theme/mimicry.htm" method="get" class="uk-form">
                                <input style="width: 290px;" type="text" name="filter[searchText]" value="${page.filter.searchText}" placeholder="问题名"/>
                                <button class="uk-button uk-button-primary">查询</button>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="uk-form-row col-content">
                <c:forEach var="theme" items="${page.result}">
                    <div class="uk-form-row node-div" id = "node-div-${theme.id}">
                        <div class="uk-form-row div3-style" style="margin-top: 0px">
                            <img class="radius-img" src="${theme.avatar}" />
                            <label class="label-style" style="font-size: 20px;">&nbsp;&nbsp;${theme.uid}, ${theme.name}</label>
                        </div>
                        <div class="uk-form-row div3-style" style="margin-top: 0px">
                            <label class="label-style" style="font-size: 18px;font-weight: 600"><a href = "/theme/to-detail.htm?id=${theme.id}" target="_blank">${theme.title}</a></label>
                        </div>
                        <div class="uk-form-row div3-style" style="margin-top: 0px">
                            <label class="label-style content-style" style="font-size: 14px;font-weight: 300">${theme.selectNodeTheme.content}</label>
                        </div>
                        <div class="uk-form-row div3-style" style="margin-top: 0px">
                            <table class="table-font-9">
                                <tr>
                                    <td style="width: 130px;"><lch:parse-date time="${theme.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td style="width: 50px;text-align: right"></td>
                                    <td style="width: 35px"></td>
                                    <td style="width: 50px;text-align: right"><a href="#add-answer" data-uk-modal onclick="mimicry.selectTheme(${theme.id})">回答</a>&nbsp;&nbsp;</td>
                                    <td style="width: 35px"><a onclick="mimicry.loadAnswer(${theme.id})">${theme.discussNum}</a></td>
                                </tr>
                                <tr>
                                    <td colspan="5" >
                                        <div style="width: 100%;text-align:left;<c:if test="${theme.isSensitive == 0}">background: red</c:if>">
                                            <a onclick="mimicry.themeHandleIsSensitive(${theme.id})">
                                                <c:choose>
                                                    <c:when test="${theme.isSensitive == 1}">屏蔽</c:when>
                                                    <c:otherwise>解禁</c:otherwise>
                                                </c:choose>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="uk-form-row node-div  node-div-radius">
                <lch:page page="${page}" href="/theme/mimicry.htm"></lch:page>
            </div>

        </div>

        <%--问题的回答列表--%>
        <div class="container-div-col">
                <div class="uk-form-row node-div node-div-radius" style="text-align: right">
                    <table>
                        <tr>
                            <td style="width: 270px;text-align: center">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                问题的回答列表
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="uk-form-row col-content" id = "answer-div-id">
                </div>
                <div class="uk-form-row node-div  node-div-radius">
                    <table class="table-style">
                        <tr>
                            <td style="width: 48%;text-align: center"><a onclick="mimicry.prevAnswer()">上一页</a></td>
                            <td style="width: 48%;text-align: center"><a onclick="mimicry.nextAnswer()">下一页</a></td>
                        </tr>
                    </table>
                </div>
            </div>

        <%--回答的讨论列表--%>
        <div class="container-div-col">
            <div class="uk-form-row node-div node-div-radius" style="text-align: right">
                <table>
                    <tr>
                        <td style="width: 270px;text-align: center">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            回答的讨论列表
                        </td>
                    </tr>
                </table>
            </div>

            <div class="uk-form-row col-content" id = "discuss-div-id">

            </div>
            <div class="uk-form-row node-div  node-div-radius" >
                <table class="table-style">
                    <tr>
                        <td style="width: 48%;text-align: center"><a onclick="mimicry.prevDiscuss()">上一页</a></td>
                        <td style="width: 48%;text-align: center"><a onclick="mimicry.nextDiscuss()">下一页</a></td>
                    </tr>
                </table>
            </div>
        </div>

            <%--水军帐号列表--%>
            <div class="container-div-col">
                <div class="uk-form-row node-div node-div-radius" style="text-align: right">
                    <table>
                        <tr>
                            <td style="width: 200px;text-align: center;">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                水军帐号
                            </td>
                            <td style="width: 70px;text-align: center"><a>添加水军</a></td>
                            <td><a>X</a></td>
                        </tr>
                    </table>
                </div>

                <div class="uk-form-row col-content" >
                    <table >
                        <thead>
                        <tr>
                            <th></th>
                            <td>头像</td>
                            <td>名称</td>
                            <td>个人介绍</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>
                                    <input type="radio" name = "uid" value="${user.id}" onclick="mimicry.selectUid(this)" />
                                </td>
                                <td style="padding-top: -5px">
                                    <img class="radius-img-2" src="${user.avatar}" />
                                </td>
                                <td>${user.name}</td>
                                <td>${user.signature}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
    </div>

</div>

<div id="add-answer" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 style="text-align: center">回答</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/file/add.json" method="post" id = "save-img-from" enctype="multipart/form-data">
                <div class="uk-form-row">
                    <input class="uk-width-5-6 uk-form-large" type="file" name="uploadFile" notnull title = "upload file">
                    <a style="margin-left: 30px" onclick="mimicry.insertAnswerContentImg('add-answer-table-id', 'save-img-from')">插入</a>
                    <input hidden type="submit" value="上传" />
                </div>
            </form>
            <div class="uk-form-row">
                <textarea class="uk-width-5-6 uk-form-large" name="des" placeholder="文本内容" notnull id = "textarea-id"></textarea>
                <a style="margin-left: 30px" onclick="mimicry.insertAnswerContentText()">插入</a>
            </div>

            <div class="uk-form-row node-div" style="margin-top: 20px">
                <table class="table-style-alert" id = "add-answer-table-id">
                </table>
            </div>
            <div class="uk-form-row uk-text-right">
                <button class="uk-button uk-button-primary" onclick="mimicry.saveAnswer()">add</button>
            </div>
        </div>
    </div>
</div>

<div id="add-discuss" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 style="text-align: center">讨论</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <div class="uk-form-row">
                <textarea class="uk-width-1-1 uk-form-large" name="content" id = "discuss-textarea-id" placeholder="文本内容" notnull></textarea>
            </div>
            <div class="uk-form-row uk-text-right">
                <button class="uk-button uk-button-primary" onclick="mimicry.saveDiscuss()">add</button>
            </div>
        </div>
        </div>
    </div>
</div>
<div style="width: 0px;height: 0px;margin: 0px;padding: 0px" id = "text-to-html-id"></div>

<script src="/static/community/mimicry-js.js"></script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>