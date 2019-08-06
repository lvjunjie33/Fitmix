<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/3/7
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>

    #activity-list{
        margin-top: 10px;
    }

    .activity{
        margin-bottom: 100px;;
    }

</style>

<div class="uk-form-row">
    <a href="#BbsBannerAdd" class="uk-button" data-uk-modal>添加</a>
</div>



<div id="activity-list">
    <c:forEach var="adv" items="${advertisementList}">
        <div class="activity">
            <div class="uk-thumbnail">
                <img src="<lch:build-path url="${adv.imageUrl}"/>" style="width: 260px;height: 210px;">
            </div>
            <div style="margin-top: 10px">
                <strong>
                    ${adv.name}(${adv.id})
                    <a href="/bbs/advertisement/advertisement-modify.htm?id=${adv.id}">修改</a>
                </strong>
                <p>${adv.name}</p>
                <c:choose>
                    <c:when test="${adv.type == 1}">图片类型</c:when>
                </c:choose>
            </div>
            <p>
                所属模块：
                <c:if test="${adv.module == 0}">首页</c:if>
                <c:if test="${adv.module == 1}">文章</c:if>
            </p>
            <p>排序：${adv.sort}</p>
            <p>添加：<lch:parse-date time="${adv.addTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <div>
                <c:choose>
                    <c:when test="${adv.status eq 0}">
                        <label style="color:#6de1ff;">发布</label>
                    </c:when>
                    <c:when test="${adv.status eq 1}">
                        <label style="color:#ff405d;">未发布</label>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="advertisement-add.jsp"/>