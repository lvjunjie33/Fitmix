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
        <form action="/ugc/user-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>用户ID：</label>
                    <label class="mix-scene"><input type="number" name="filter[uid]" value="${page.filter.uid}" placeholder="用户Id"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>主播名称：</label>
                    <label class="mix-scene"><input type="text" name="filter[name]" value="${page.filter.name}" placeholder="主播名称"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>状态：</label>
                    <label class="mix-scene">
                        <select name="filter[checkStatus]">
                            <option value="-1">全部</option>
                            <option value="0" <c:if test="${page.filter.checkStatus == 0}">selected</c:if> >待审核</option>
                            <option value="1" <c:if test="${page.filter.checkStatus == 1}">selected</c:if> >审核通过</option>
                            <option value="2" <c:if test="${page.filter.checkStatus == 2}">selected</c:if> >审核失败</option>
                        </select>
                    </label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
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
            <th style="width: 100px;">用户编号</th>
            <th style="width: 200px;">主播名称</th>
            <th style="width: 100px">创建时间</th>
            <th style="width: 100px">审核状态</th>
            <th style="width: 120px">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${page.result}">
            <tr>
                <td><label name="id">${user.id}</label></td>
                <td>${user.name}</td>
                <td><lch:parse-date time="${user.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>
                    <c:choose>
                        <c:when test="${user.userIdentityInfo.checkStatus == 0}">
                            待审核
                        </c:when>
                        <c:when test="${user.userIdentityInfo.checkStatus == 1}">
                            审核通过
                        </c:when>
                        <c:when test="${user.userIdentityInfo.checkStatus == 2}">
                            审核未通过
                        </c:when>
                        <c:otherwise>
                            未提交审核信息
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <lch:auth-operation url="/video/video-modify-status.json">
                        <a href="/ugc/check-user.htm?id=${user.id}" target="_blank">审核</a>
                    </lch:auth-operation>
                    &nbsp;
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/video/video-list.htm"></lch:page>
<script>
    $(function(){
        $("[video-remove]").click(function(){
            if (window.confirm("是否删除？")) {
                var th = $(this);
                var id = th.attr("data-id");
                $.post("video/video-remove.json", {"id":id}, function(result){
                    th.parent().parent().hide(400).remove();
                });
            }
        });
    });

    $(function(){
        $("[shelves]").on("click", shelves);
        $("[no-shelves]").on("click", onShelves);

        function shelves () {
            if (window.confirm("确认上架？")) {
                var th = $(this);
                debugger
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["status"] = 0;
                $.post("/video/video-modify-status.json", dataJson, function(result){
                    var code = result['code'];
                    if (code == 0) {
                        th.replaceWith('<a href="javascript:" no-shelves>下架</a>');
                        $("[no-shelves]").on("click", onShelves);
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
                dataJson["status"] = 1;
                $.post("/video/video-modify-status.json", dataJson, function(result){
                    th.replaceWith('<a href="javascript:" shelves>上架</a>');
                    $("[shelves]").on("click", shelves);
                });
            }
        }
    });


</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
