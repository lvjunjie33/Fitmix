<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<style>
    .album-box {
        width: 100%;
    }
    .album-cover, .album-cover img {
        width: 150px;
        height: 150px;
        display: inline-block;
    }
    .album-content {
        vertical-align: top;
        margin-left: 20px;
        display: inline-block;
        height: 150px;
    }
    .album-title {
        height: 30px;
        line-height: 30px;
        font-size: 24px;
        font-weight: bold;
    }
    .album-desc {
        margin-top: 10px;
    }
</style>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <button id="mixAlbum-success" data-id="${mixAlbum.id}" type="button" class="uk-button uk-button-primary">通过</button>
        <button id="mixAlbum-fail" data-uk-modal="{target:'#fail'}" type="button" class="uk-button uk-button-primary">驳回</button>
    </div>
    <div class="uk-form-row">
        <c:choose>
            <c:when test="${mixAlbum.checkStatus == 0}">
                <button class="uk-button-primary">状态:待审核</button>
            </c:when>
            <c:when test="${mixAlbum.checkStatus == 1}">
                <button class="uk-button-success">状态:通过审核</button>
            </c:when>
            <c:when test="${mixAlbum.checkStatus == 2}">
                <button class="uk-button-danger">状态:驳回</button>
                <span class="uk-text-danger">原因:${mixAlbum.checkMessage}</span>
            </c:when>
        </c:choose>
    </div>
    <hr class="uk-article-divider">
</div>

<div class="uk-overflow-container">
    <div class="album-box">
        <div class="album-cover">
            <img src="<lch:build-path url="${mixAlbum.backImage}" />" />
        </div>
        <div class="album-content">
            <div class="album-title">
                ${mixAlbum.title}
            </div>
            <div class="album-desc">
                ${mixAlbum.desc}
            </div>
        </div>
    </div>
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <th style="width: 100px;">节目编号</th>
            <th style="width: 200px;">节目名称</th>
            <th style="width: 200px;">节目简介</th>
            <th style="width: 100px">创建时间</th>
            <th style="width: 100px">链接</th>
            <th style="width: 100px">审核状态</th>
            <th style="width: 120px">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="mix" items="${mixList}">
            <tr>
                <td><label name="id">${mix.id}</label></td>
                <td>${mix.name}</td>
                <td>${mix.introduce} </td>
                <td><lch:parse-date time="${mix.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>
                    <audio controls>
                        <source src="<lch:build-path url="${mix.url}"/>" type="audio/mpeg">
                    </audio>
                </td>
                <td class="checkStatus">
                    <c:choose>
                        <c:when test="${mix.checkStatus == 0}">
                            待审核
                        </c:when>
                        <c:when test="${mix.checkStatus == 1}">
                            审核通过
                        </c:when>
                        <c:when test="${mix.checkStatus == 2}">
                            审核未通过
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <lch:auth-operation url="/ugc/check-mix.json">
                        <a class="mix-success" href="javascript:void (0);" data-id="${mix.id}">通过</a>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/ugc/check-mix.json">
                        <a class="mix-fail" href="javascript:void (0);" data-id="${mix.id}">驳回</a>
                    </lch:auth-operation>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="uk-modal" id="fail">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <div class="uk-modal-header">驳回原因</div>
        <div class="uk-form-row">
            <textarea id="checkMessage" class="uk-width-1-1" rows="10"></textarea>
        </div>
        <div class="uk-modal-footer uk-text-right">
            <button type="button" class="uk-button uk-modal-close">Cancel</button>
            <button id="mixAlbum-fail-submit" type="button" class="uk-button uk-button-primary">Save</button>
        </div>
    </div>
</div>

<%--分页条--%>
<script>
    var albumsId = ${mixAlbum.id};
    $(".uk-table").on("click", ".mix-success", function() {
        var th = $(this);
        UIkit.modal.confirm("确定要通过吗？", function() {
            $.ajax({
                url: "/ugc/check-mix.json",
                data: {
                    checkStatus: 1,
                    id: th.data("id")
                },
                type: "post",
                dataType: "json",
                success: function(result) {
                    if(result.code == 0) {
                        UIkit.modal.alert("修改成功");
                        th.parents("tr").find(".checkStatus").text("审核通过");
                    } else {
                        UIkit.modal.alert("修改失败");
                    }
                }
            });
        });
    });
    $(".uk-table").on("click", ".mix-fail", function(){
        var th = $(this);
        UIkit.modal.confirm("确定要驳回吗？", function() {
            $.ajax({
                url: "/ugc/check-mix.json",
                data: {
                    checkStatus: 2,
                    id: th.data("id")
                },
                type: "post",
                dataType: "json",
                success: function(result) {
                    if(result.code == 0) {
                        UIkit.modal.alert("修改成功");
                        th.parents("tr").find(".checkStatus").text("审核未通过");
                    } else {
                        UIkit.modal.alert("修改失败");
                    }
                }
            });
        });
    });

    $("#mixAlbum-success").on("click", function(){
        UIkit.modal.confirm("确定要通过吗？", function() {
            $.ajax({
                url: "/ugc/check-mixAlbum.json",
                data: {
                    checkStatus: 1,
                    id: $("#mixAlbum-success").data("id")
                },
                type: "post",
                dataType: "json",
                success: function(result) {
                    if(result.code == 0) {
                        alert("修改成功");
                        location.reload();
                    } else {
                        UIkit.modal.alert(result.message);
                    }
                }
            });
        });
    });

    $("#mixAlbum-fail-submit").on("click", function() {
        $.ajax({
            url: "/ugc/check-mixAlbum.json",
            data: {
                checkStatus: 2,
                id: albumsId,
                checkMessage: $("#checkMessage").val()
            },
            type: "post",
            dataType: "json",
            success: function(result) {
                if(result.code == 0) {
                    alert("修改成功");
                    location.reload();
                } else {
                    UIkit.modal.alert("修改失败");
                }
            }
        });
    });

</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
