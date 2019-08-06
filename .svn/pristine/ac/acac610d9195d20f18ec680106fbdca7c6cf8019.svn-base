<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/12/8
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="renewals-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加新的服务</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/target-server/add.json" method="post" class="uk-form" id="renewals-add-form" isform>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="serverName" placeholder="服务名" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="requestLink" placeholder="服务链接地址">
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="positionJsonStr" placeholder="位置">
                </div>

                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="type" placeholder="类型">
                        <option value="0">Web Server</option>
                        <option value="1">Mongo DB</option>
                        <option value="2">Redis</option>
                        <option value="3">三方服务</option>
                    </select>
                </div>

                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="paramJsonStr" placeholder="其他信息" ></textarea>
                </div>

                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="memo" placeholder="描述" ></textarea>
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
