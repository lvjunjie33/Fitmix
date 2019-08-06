<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/11/28
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">
    .uk-form-row{margin-top: 20px}
    .row-div-node{
        width: 700px;
        padding-left: 30px;
        padding-top: 10px;
    }
    .b-c-EFE8B7 {
        background-color: #EFE8B7;
    }
    .b-c-F9D0D0{
        background-color: #F9D0D0;
    }
    .b-c-659F13{
        background-color: #659F13;
    }
    .b-c-9A9191{
        background-color: #9A9191;
    }
    .b-c-76A3DC{
        background-color: #76A3DC;
    }
    .b-c-E792E8{
        background-color: #E792E8;
    }
    .b-c-E84A1B {
        background-color: #E84A1B;
    }
    .b-c-F9C0C0{
        background-color: #F9C0C0;
    }
    .c-CCB728{
        color: #CCB728;
    }
    .c-E00C0C{
        color: #E00C0C;
    }
    .c-3B9C1D{
        color: #3B9C1D;
    }
</style>

<form action="/renewals-server/manager.htm" method="get" class="uk-form">
    <div class="uk-form-row uk-grid">

        <div class="uk-form-row uk-width-2-10">
            <label>服务名</label>
            <input type="text" name="filter[name]" value="${page.filter.name}" placeholder="服务名"/>
        </div>

        <div class="uk-form-row uk-width-2-6">
            <button class="uk-button uk-button-primary">查询</button>&nbsp;&nbsp;&nbsp;
            <a href="#renewals-add" class="uk-button b-c-F9C0C0" data-uk-modal>添加</a>
        </div>
    </div>
</form>

<table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
    <thead>
    <tr>
        <th>服务名</th>
        <th>链接地址</th>
        <th>描述</th>
        <th>开始时间</th>
        <th>到期时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.result}" var="item">
        <tr <c:if test="${item.isWarn == 0}">style="background-color: #B8F5BF" </c:if> >
            <td>${item.name}</td>
            <td><a href="${item.link}">${item.link}</a></td>
            <td>${item.memo}</td>
            <td><lch:parse-date time="${item.beginTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td><lch:parse-date time="${item.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>

            <td>
                <form action="/renewals-server/remove.json" method="post" class="uk-form" isform>

                    <a href="#modify-renewals-server" data-uk-modal class="uk-button uk-button-primary b-c-E792E8 task-cmd" t-type = "6" t-id = "${item.id}"
                            t-name = "${item.name}" t-link = "${item.link}"
                            t-bTime = "<lch:parse-date time="${item.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                            t-eTime = "<lch:parse-date time="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                            t-memo = "${item.memo}">修改</a>

                    <input type="hidden" name = "id" value="${item.id}" />
                    <button class="uk-button uk-button-primary" target="role-add-form">删除</button>
                </form>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--分页条--%>
<lch:page page="${page}" href="/renewals-server/manager.htm"></lch:page>

<div id="modify-renewals-server" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>修改服务信息</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/renewals-server/modify.json" method="post" class="uk-form" id="modify-renewals-server-form" isform>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large"  name="name" placeholder="服务名称" notnull>
                    <input type="hidden" name = "id" />
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large"  name="link" placeholder="服务链接">
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="bTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="服务开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="eTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="服务到期时间" notnull>
                </div>

                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="memo" placeholder="描述"></textarea>
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" target="role-add-form">save</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
<jsp:include page="add.jsp"/>

<script>
    $(function() {
        $(".task-cmd").click(function(){
            var $this = $(this);
            var _id = $this.attr("t-id");
            var _name = $this.attr("t-name");
            var _bTime = $this.attr("t-bTime");
            var _eTime = $this.attr("t-eTime");
            var _memo = $this.attr("t-memo");
            var _link = $this.attr("t-link");

            var _from = $("#modify-renewals-server-form");
            _from.find("[name=id]").val(_id);
            _from.find("[name=name]").val(_name);
            _from.find("[name=bTime]").val(_bTime);
            _from.find("[name=eTime]").val(_eTime);
            _from.find("[name=link]").val(_link);
            _from.find("[name=memo]").val(_memo);
        });
    });
</script>

