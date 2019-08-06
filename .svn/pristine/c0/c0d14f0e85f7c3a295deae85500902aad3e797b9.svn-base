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
    .user-identity-img {
        width: 400px !important;
        height: 300px !important;
    }
    .uk-form-row label {
        display: block;
    }
    .uk-form-row label:nth-child(1) {
        line-height: 18px;
        font-size: 18px;
        font-weight: bold;
        margin-top: 10px;
        margin-bottom: 10px;
    }
</style>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <button id="user-success" type="button" class="uk-button uk-button-primary">通过</button>
        <button id="user-fail" data-uk-modal="{target:'#fail'}" type="button" class="uk-button uk-button-primary">驳回</button>
    </div>
    <div class="uk-form-row">
        <c:choose>
            <c:when test="${user.userIdentityInfo.checkStatus == 0}">
                <button class="uk-button-primary">状态:待审核</button>
            </c:when>
            <c:when test="${user.userIdentityInfo.checkStatus == 1}">
                <button class="uk-button-success">状态:通过审核</button>
            </c:when>
            <c:when test="${user.userIdentityInfo.checkStatus == 2}">
                <button class="uk-button-danger">状态:驳回</button>
                <span class="uk-text-danger">原因:${user.userIdentityInfo.checkMessage}</span>
            </c:when>
            <c:otherwise>
                <button class="uk-button-danger">状态:未提交审核信息</button>
            </c:otherwise>
        </c:choose>
    </div>
    <hr class="uk-article-divider">
</div>

<div class="uk-overflow-container">
    <c:if test="${user.userIdentityInfo != null}">
        <div class="uk-form-row">
            <label>真实姓名</label>
            <label>${user.userIdentityInfo.realName}</label>
        </div>
        <div class="uk-form-row">
            <label>身份证号</label>
            <label>${user.userIdentityInfo.idCard}</label>
        </div>
        <div class="uk-form-row">
            <label>身份照片</label>
            <label>
                <%--<img class="user-identity-img" src='<lch:build-path url="${user.userIdentityInfo.idCardImg}"/>'>--%>
                <a href='<lch:build-path url="${user.userIdentityInfo.idCardImg}"/>' data-uk-lightbox="{group:'group1'}" title="身份证照片">
                    <img class="user-identity-img" src='<lch:build-path url="${user.userIdentityInfo.idCardImg}"/>' width="800" height="600" alt="">
                </a>
            </label>
        </div>
        <c:if test="${user.userIdentityInfo.radio != null}">
            <div class="uk-form-row">
                <label>1.您是否是电台主播？（如果是，请填写您所属的电台及节目名称）</label>
                <label>${user.userIdentityInfo.radio}</label>
            </div>
        </c:if>
        <c:if test="${user.userIdentityInfo.radioUploadDesc != null}">
        <div class="uk-form-row">
            <label>2.您现在或曾经是否在其他音频APP平台上上传过自己的节目或专辑？（如有请于下栏填写并上传截图）</label>
            <label>${user.userIdentityInfo.radioUploadDesc}</label>
            <c:if test="${user.userIdentityInfo.radioUploadImg != null}">
                <label>
                    <%--<img class="user-identity-img" src='<lch:build-path url="${user.userIdentityInfo.radioUploadImg}" />' />--%>
                        <a href='<lch:build-path url="${user.userIdentityInfo.radioUploadImg}"/>' data-uk-lightbox="{group:'group1'}" title="主播平台照">
                            <img class="user-identity-img" src='<lch:build-path url="${user.userIdentityInfo.radioUploadImg}"/>' width="800" height="600" alt="">
                        </a>
                </label>
            </c:if>
        </div>
        </c:if>
        <c:if test="${user.userIdentityInfo.follower != null}">
        <div class="uk-form-row">
            <label>3.您是否在各社交平台拥有粉丝？（如有请填写平台及粉丝数并截图验证）</label>
            <label>${user.userIdentityInfo.follower}</label>
            <c:if test="${user.userIdentityInfo.followerImg != null}">
                <label>
                    <%--<img class="user-identity-img" src='<lch:build-path url="${user.userIdentityInfo.followerImg}" />' />--%>
                        <a href='<lch:build-path url="${user.userIdentityInfo.followerImg}"/>' data-uk-lightbox="{group:'group1'}" title="粉丝照">
                            <img class="user-identity-img" src='<lch:build-path url="${user.userIdentityInfo.followerImg}"/>' width="800" height="600" alt="">
                        </a>
                </label>
            </c:if>
        </div>
        </c:if>
    </c:if>
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
            <button id="user-fail-submit" type="button" class="uk-button uk-button-primary">Save</button>
        </div>
    </div>
</div>

<%--分页条--%>
<script>
    var userId = ${user.id};
    $("#user-success").on("click", function(){
        UIkit.modal.confirm("确定要通过吗？", function() {
            $.ajax({
                url: "/ugc/check-user.json",
                data: {
                    checkStatus: 1,
                    id: userId
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

    $("#user-fail-submit").on("click", function() {
        $.ajax({
            url: "/ugc/check-user.json",
            data: {
                checkStatus: 2,
                id: userId,
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
