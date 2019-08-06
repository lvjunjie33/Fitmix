<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/6/1
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div class="uk-container-center uk-body">
    <table class="uk-table uk-table-hover">
        <tbody>
        <tr>
            <td>
                <img src="${theme.avatar}" class="uk-icon-button" style="width: 100px;height: 100px;"/>
            </td>
        </tr>
        <tr>
            <td><label>用户编号：</label><label>${theme.uid}</label></td>
        </tr>
        <tr>
            <td><label>用户名称：</label><label>${theme.name}</label></td>
        </tr>
        <tr>
            <td><label>添加时间：</label><label><lch:parse-date time="${theme.addTime}" pattern="yyyy-MM-dd HH:mm"/></label></td>
        </tr>

        <tr>
            <td><label>精华帖：</label><label>${theme.fine}</label></td>
        </tr>
        <tr>
            <td><label>点赞数：</label><label>${theme.upNum}</label></td>
        </tr>
        <tr>
            <td><label>点击次数：</label><label>${theme.clickNum}</label></td>
        </tr>
        <tr>
            <td><label>banner位置：</label><label>${theme.bannerSort}</label></td>
        </tr>
        <tr>
            <td>
                <label>banner封面图：</label>
                <label>
                <c:if test="${!empty theme.backImage}">
                    <img src="${theme.backImage}" width="150" />
                </c:if>
                </label>
            </td>
        </tr>
        </tbody>
    </table>

    <div class="uk-form-row">
        <div class="uk-form-row">
            <label>标题：</label>
            <label>${theme.title}</label>
        </div>
        <div  class="uk-form-row">
            <a href="#theme-add-fine" data-uk-modal>加精</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#theme-add-banner" data-uk-modal >编辑banner</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="/theme/modify-content.htm?themeId=${theme.id}" target="_blank">编辑活动内容</a>&nbsp;&nbsp;&nbsp;&nbsp;

            <form action="/theme/un-set/banner.json" method="post" class="uk-form" id="xx-add-form" isform style="display: inline">
                <input type="hidden" name = "id" value="${theme.id}" />
                <button class="uk-button uk-button-primary" id="xx-add-submit" target="role-add-form" style="background: #fff;color: #0c7ddc;">关闭banner</button>
            </form>

            <form action="/theme/set/is/confirmed.json" method="post" class="uk-form" id="xx1-add-form" isform style="display: inline">
                <input type="hidden" name = "themeId" value="${theme.id}" />
                <c:choose>
                    <c:when test = "${theme.isConfirmed == 0}">
                        <button class="uk-button uk-button-primary" id="xx1-add-submit" target="role-add-form" style="background: #fff;color: #0c7ddc;">审核通过</button>
                    </c:when>
                    <c:otherwise>
                        <button class="uk-button uk-button-primary" id="xx1-add-submit" target="role-add-form" style="background: #fff;color: red;">审核不通过</button>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
        <div class="uk-form-row" style="box-shadow: 0px 0px 10px #888888;">
            ${theme.content}
        </div>
    </div>
</div>

<div id="theme-add-banner" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h4 style="text-align: center">修改banner</h4>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/theme/set/banner.json" method="post" class="uk-form" id="banner-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="uploadFile" title = "upload file">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="sort" placeholder="位置" value="${theme.bannerSort}" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name = "id" value="${theme.id}" />
                    <button class="uk-button uk-button-primary" id="banner-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="theme-add-fine" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h4 style="text-align: center">加精话题</h4>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/theme/set/fine.json" method="post" class="uk-form" id="fine-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="sort" placeholder="位置" value="${theme.fine}" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name = "id" value="${theme.id}" />
                    <button class="uk-button uk-button-primary" id="fine-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>