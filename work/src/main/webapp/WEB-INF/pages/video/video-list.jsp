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
        <form action="/club/club-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>俱乐部ID：</label>
                    <label class="mix-scene"><input type="number" name="filter[id]" value="${page.filter.id}" placeholder="俱乐部Id"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>用户ID：</label>
                    <label class="mix-scene"><input type="number" name="filter[uid]" value="${page.filter.uid}" placeholder="用户Id"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>名称：</label>
                    <label class="mix-scene"><input type="text" name="filter[name]" value="${page.filter.name}" placeholder="俱乐部名称"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/video/video-add.htm" target="_blank" class="uk-button uk-button-primary">添加Video</a>
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
            <th style="width: 100px;">Video ID</th>
            <th style="width: 200px;">视频标题</th>
            <th style="width: 200px;">视频类型</th>
            <th style="width: 100px">时长</th>
            <th style="width: 100px">创建时间</th>
            <th style="width: 120px">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="video" items="${page.result}">
            <tr>
                <td><label name="id">${video.id}</label></td>
                <td><a href="/video/video-info.htm?id=${video.id}" target="_blank">${video.title}</a></td>
                <td>
                    <c:choose>
                        <c:when test="${video.type == 0}">普通视频</c:when>
                        <c:when test="${video.type == 1}">360全景视频</c:when>
                        <c:otherwise>普通视频</c:otherwise>
                    </c:choose>
                </td>
                <td><fmt:parseNumber value="${(video.trackLength - video.trackLength % 60) / 60}"/>.${video.trackLength % 60}</td>
                <td><lch:parse-date time="${video.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>
                    <lch:auth-operation url="/video/video-modify-status.json">
                        <c:choose>
                            <c:when test="${video.status == 0}"><a href="javascript:" no-shelves>下架</a></c:when>
                            <c:when test="${video.status == 1}"><a href="javascript:" shelves>上架</a></c:when>
                        </c:choose>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/video/video-modify-sort.json">
                        <a href="#video-sort-modal" data-uk-modal video-sort>sort</a>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/mix/mix-remove.json">
                        <a href="javascript:" video-remove style="color:#ff4a35;" data-id="${video.id}">删除</a>
                    </lch:auth-operation>
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
                $.post("/video/video-remove.json", {"id":id}, function(result){
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
<%@ include file="video-sort.jsp"%>
