<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/8/17
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
    .td-text {
        white-space: normal;
        width: 40%;
        word-wrap: break-word;
        word-break: break-all;
        overflow: hidden;
    }

</style>

<div>
    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/char/table/to/compared.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <label>发布状态：</label>
                <label>
                    <select name="table">
                        <option value="1">字典</option>
                        <option value="2">地址</option>
                        <option value="3">错误码</option>
                        <option value="4">训练计划</option>
                        <option value="5">跑步等级</option>
                        <option value="6">金币任务</option>
                    </select>
                </label>
                &nbsp;&nbsp;&nbsp;
                <button type="submit" class="uk-button uk-button-primary">更新</button>
            </div>
        </form>
        <form action="/char/table/manager.htm" method="get" class="uk-form" style="margin-top: 20px">
            <div class="uk-form-row uk-grid">
                <div class="uk-width-1-6">
                    <label>未翻译：</label>
                    <label>
                        <input type="checkbox" name = "filter[isEmpty]" value="1" <c:if test="${page.filter.isEmpty == 1}">checked</c:if> />
                    </label>
                </div>
                <div class="uk-width-1-6">
                    <label>排序方式：</label>
                    <label>
                        <select name="filter[sort]">
                            <option value="1" <c:if test="${page.filter.sort == 1}">selected</c:if>>添加时间</option>
                            <option value="2" <c:if test="${page.filter.sort == 2}">selected</c:if>>名称</option>
                        </select>
                    </label>
                </div>
                <div class="uk-width-3-6">
                    <label>名称：</label>
                    <label class="mix-scene">
                        <input type="text" name="filter[strCN]" value="${page.filter.strCN}" placeholder="中文">
                    </label>
                    &nbsp;&nbsp;&nbsp;
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    &nbsp;&nbsp;
                    <a href="#char-table-add" class="uk-button" data-uk-modal>新增</a>
                </div>
            </div>
        </form>
    </div>

    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr>
                <th style="width: 50px">中文</th>
                <th style="width: 200px;">英文</th>
                <th style="width: 100px;">添加时间</th>
                <th style="width: 100px">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${page.result}">
                <tr>
                    <td class="td-text">${item.strCN}</td>
                    <td class="td-text">${item.strEN}</td>
                    <td><lch:parse-date time="${item.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><a href="#char-table-modify" class="uk-button" data-uk-modal onclick="editEn('${item.strCN}')">编辑</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/char/table/manager.htm"></lch:page>

<div id="char-table-modify" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>翻译</h1>
        <div class="uk-form-row">
            <form action="/char/table/edit/en.json" method="post" class="uk-form" id="char-table-modify-form" isform>
                <div class="uk-form-row uk-grid">
                    <label class="uk-width-1-5" style="font-size: 16px;padding-top: 5px;">中文</label><textarea class="uk-width-4-5 uk-form-large" style="height: 100px"  name = "showCN" disabled ></textarea>
                </div>
                <div class="uk-form-row uk-grid">
                    <label class="uk-width-1-5"  style="font-size: 16px;padding-top: 5px;">英文</label><textarea class="uk-width-4-5 uk-form-large" style="height: 100px"  name="strEN" notnull></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="strCN" >
                    <button class="uk-button uk-button-primary" id="char-table-modify-submit" target="char-table-modify-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="char-table-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>新增翻译</h1>
        <div class="uk-form-row">
            <form action="/char/table/add.json" method="post" class="uk-form" id="char-table-add-form" isform>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="strCN" placeholder="中文" notnull></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="char-table-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<script>
    function editEn(strCN) {
        var from = $("#char-table-modify-form");
        from.find("[name=strCN]").val(strCN);
        from.find("[name=showCN]").val(strCN);
    }
</script>
