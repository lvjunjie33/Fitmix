<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/12/8
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>监控服务修改</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/target-server/modify.json" method="post" class="uk-form"isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="serverName" placeholder="服务名" value="${targetServer.serverName}" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="requestLink" placeholder="服务链接地址" value="${targetServer.requestLink}" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="positionJsonStr" placeholder="位置" value="${targetServer.positionJsonStr}" notnull>
                </div>

                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="type" placeholder="类型" >
                        <option value="0" <c:if test="${targetServer.type == 0}">selected</c:if>>Web Server</option>
                        <option value="1" <c:if test="${targetServer.type == 1}">selected</c:if>>Mongo DB</option>
                        <option value="2" <c:if test="${targetServer.type == 2}">selected</c:if>>Redis</option>
                        <option value="3" <c:if test="${targetServer.type == 3}">selected</c:if>>三方服务</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="enabled" placeholder="状态" >
                        <option value="1" <c:if test="${targetServer.enabled == 1}">selected</c:if>>启用</option>
                        <option value="0" <c:if test="${targetServer.enabled == 0}">selected</c:if>>禁用</option>
                    </select>
                </div>

                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="paramJsonStr" placeholder="其他信息" >${targetServer.paramJsonStr}</textarea>
                </div>

                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="memo" placeholder="描述" >${targetServer.memo}</textarea>
                </div>

                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${targetServer.id}">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">submit</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
