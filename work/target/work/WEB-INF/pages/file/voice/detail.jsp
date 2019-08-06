<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/12
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
</style>
<div class="file">
    <div style="margin-top: 10px;float:left;margin-right: 100px">
        <h3><a style="margin-right:30px " href="/file/modify.htm?id=${file.id}">修改</a>${file.title}</h3>
        <p>文件类型：语音包</p>
        <p>文件标题：${file.title}</p>
        <p>文件-link：<lch:build-path url="${file.fileLink}"/></p>
        <p>描述：${file.des}</p>
        <p>数据状态：
            <c:choose>
                <c:when test="${file.status eq 1}">
                    <label style="color:#6de1ff;">正常</label>
                </c:when>
                <c:when test="${file.status eq 0}">
                    <label style="color:#ff405d;">无效</label>
                </c:when>
            </c:choose>
        </p>

        <p>目录名：${file.other.dirName}</p>
        <p>版本号：${file.other.version}</p>
        <p>文件大小：${file.other.size}</p>
        <p>支持app版本：${file.other.targetApp}</p>
        <p>标签：${file.other.tags}</p>
        <p>
            <img width="200" src="<lch:build-path url='${file.other.iconLink}'/>" /><br/><br/>
            <label>icon-link：<lch:build-path url="${file.other.iconLink}"/></label>
        </p>
        <p>
            <img width="200" src="<lch:build-path url='${file.other.coverLink}'/>" /><br/><br/>
            <label>封面图-link：<lch:build-path url="${file.other.coverLink}"/></label>
        </p>
        <p>
            <a href="#file-add-cover-img" class="uk-button add-cover-cls" data-uk-modal >设置封面图</a>
            <a href="#file-add-icon-img" class="uk-button add-icon-cls" data-uk-modal >设置Icon</a>
        </p>
    </div>
</div>


<div id="file-add-cover-img" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>设置封面图</h1>
        <div class="uk-form-row">
            <form action="/voice/set/cover/img.json" method="post" class="uk-form" id="cover-img-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="coverImg" notnull title = "封面图">
                </div>

                <div class="uk-form-row uk-text-right">
                    <input class="uk-width-1-1 uk-form-large" type="hidden" name="id" value="${file.id}">
                    <button class="uk-button uk-button-primary" id="cover-img-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="file-add-icon-img" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>设置Icon图</h1>
        <div class="uk-form-row">
            <form action="/voice/set/icon/img.json" method="post" class="uk-form" id="icon-img-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="iconImg" notnull title = "icon图">
                </div>

                <div class="uk-form-row uk-text-right">
                    <input class="uk-width-1-1 uk-form-large" type="hidden" name="id" value="${file.id}">
                    <button class="uk-button uk-button-primary" id="icon-img-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>