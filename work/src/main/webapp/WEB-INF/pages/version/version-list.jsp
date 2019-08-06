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
        <a target="_blank" href="${refreshUrls}" class="uk-button" style="margin-bottom: 10px;" >刷新缓存</a>
        <form action="/version/version-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>版本编号：</label>
                    <label class="mix-scene"><input type="number" name="filter[id]" value="${page.filter.id}" placeholder="版本编号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/version/version-add.htm" target="_blank" class="uk-button uk-button-primary">新增版本</a>
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
            <th style="width: 100px;">版本编号</th>
            <th style="width: 200px;">类型</th>
            <th style="width: 100px">版本号</th>
            <th>链接</th>
            <th style="width: 100px">创建时间</th>
            <th style="width: 120px">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="version" items="${page.result}">
            <tr>
                <td><label name="id">${version.id}</label></td>
                <td>
                    <c:choose>
                        <c:when test="${version.type == 1}">
                            Android
                        </c:when>
                        <c:when test="${version.type == 2}">
                            IOS
                        </c:when>
                    </c:choose>
                </td>
                <td>${version.versionCode}</td>
                <td>${version.url}</td>
                <td><lch:parse-date time="${version.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>
                    <lch:auth-operation url="/version/version-modify-state.json">
                        <c:choose>
                            <c:when test="${version.state == 1}"><a href="javascript:" data-type="${version.type}" shelves>上架</a></c:when>
                            <c:when test="${version.state == 2}"><a href="javascript:" data-type="${version.type}" no-shelves>下架</a></c:when>
                        </c:choose>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/version/version-modify.htm">
                        <a href="/version/version-modify.htm?id=${version.id}" target="_blank">编辑</a>
                    </lch:auth-operation>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/version/version-list.htm"></lch:page>
<script>

    $(function(){
        $("[shelves]").on("click", shelves);
        $("[no-shelves]").on("click", onShelves);

        function shelves () {
            if (window.confirm("确认上架？")) {
                var th = $(this);
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["state"] = 2;
                dataJson["type"] = th.data("type");
                $.post("/version/version-modify-state.json", dataJson, function(result){
                    var code = result['code'];
                    if (code == 0) {
                        var html = '<a href="javascript:" data-type="'+th.data("type")+'" no-shelves>下架</a>';
                        th.replaceWith(html);
                        $("[no-shelves]").on("click", onShelves);
                        window.location.reload();
                    }
                    else{
                        UIkit.notify(result['msg'], {status:'danger', pos:'top-right'});
                    }
                });
            }
        }

        function onShelves () {
            if (window.confirm("确认下架？")) {
                var th = $(this);
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["type"] = th.data("type");
                dataJson["state"] = 1;
                $.post("/version/version-modify-state.json", dataJson, function(result){
                    var html = '<a href="javascript:" data-type="'+th.data("type")+'" shelves>上架</a>';
                    th.replaceWith(html);
                    $("[shelves]").on("click", shelves);
                    window.location.reload();
                });
            }
        }
    });


</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
