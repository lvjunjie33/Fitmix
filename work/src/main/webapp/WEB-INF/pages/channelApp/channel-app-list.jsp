<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action="/channel-app/channel-app-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>渠道ID：</label>
                    <label class="mix-scene"><input type="number" name="filter[channelId]" value="${page.filter.channelId}" placeholder="渠道Id"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/channel-app/channel-app-add.htm" target="_blank" class="uk-button uk-button-primary">绑定渠道App信息</a>
                </div>
            </div>
        </form>
    </div>
    <hr class="uk-article-divider">
</div>

<div class="uk-overflow-container">
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <th style="width: 100px;">ChannelApp ID</th>
            <th style="width: 200px;">渠道ID</th>
            <th style="width: 100px">渠道名称</th>
            <th>安卓链接</th>
            <th>苹果链接</th>
            <th style="width: 100px">创建时间</th>
            <th style="width: 120px">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="channelApp" items="${page.result}">
            <tr>
                <td><label name="id">${channelApp.id}</label></td>
                <td>${channelApp.channelId}</td>
                <td>${channelApp.channelName}</td>
                <td>${channelApp.androidApkUrl}</td>
                <td>${channelApp.iosUrl}</td>
                <td><lch:parse-date time="${channelApp.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>
                    <lch:auth-operation url="/channel-app/channel-app-modify-status.json">
                        <c:choose>
                            <c:when test="${channelApp.status == 0}"><a href="javascript:" no-shelves>失效</a></c:when>
                            <c:when test="${channelApp.status == 1}"><a href="javascript:" shelves>生效</a></c:when>
                        </c:choose>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/channel-app/channel-app-modify.htm">
                        <a href="/channel-app/channel-app-modify.htm?id=${channelApp.id}" target="_blank">编辑</a>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/channel-app/channel-app-remove.json">
                        <a href="javascript:" channel-remove style="color:#ff4a35;" data-id="${channelApp.id}">删除</a>
                    </lch:auth-operation>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/channel-app/channel-app-list.htm"></lch:page>
<script>
    $(function(){
        $("[channel-remove]").click(function(){
            if (window.confirm("是否删除？")) {
                var th = $(this);
                var id = th.attr("data-id");
                $.post("/channel-app/channel-app-remove.json", {"id":id}, function(result){
                    th.parent().parent().hide(400).remove();
                });
            }
        });
    });

    $(function(){
        $("[shelves]").on("click", shelves);
        $("[no-shelves]").on("click", onShelves);

        function shelves () {
            if (window.confirm("确认生效？")) {
                var th = $(this);
                debugger
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["status"] = 0;
                $.post("/channel-app/channel-app-modify-status.json", dataJson, function(result){
                    var code = result['code'];
                    if (code == 0) {
                        th.replaceWith('<a href="javascript:" no-shelves>失效</a>');
                        $("[no-shelves]").on("click", onShelves);
                    }
                    else{
                        UIkit.notify(result['msg'], {status:'danger', pos:'top-right'});
                    }
                });
            }
        }

        function onShelves () {
            if (window.confirm("确认失效？")) {
                var th = $(this);
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["status"] = 1;
                $.post("/channel-app/channel-app-modify-status.json", dataJson, function(result){
                    th.replaceWith('<a href="javascript:" shelves>生效</a>');
                    $("[shelves]").on("click", shelves);
                });
            }
        }
    });


</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
