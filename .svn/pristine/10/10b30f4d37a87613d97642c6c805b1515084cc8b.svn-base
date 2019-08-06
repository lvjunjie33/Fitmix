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
    <a href="#ShopBannerAdd" class="uk-button" data-uk-modal>添加</a>
</div>



<div id="activity-list">
    <c:forEach var="banner" items="${page.result}">
        <div class="activity">
            <div class="uk-thumbnail uk-thumbnail-large">
                <img src="<lch:build-path url="${banner.backImage}"/>">
            </div>
            <div style="margin-top: 10px">
                <strong>
                    ${banner.title}(${banner.id})
                    <a href="/shop/banner/banner-modify.htm?id=${banner.id}">修改</a>
                </strong>
                <p>${banner.description}</p>
                <c:choose>
                    <c:when test="${banner.type == 0}">网页：${banner.typeValue}</c:when>
                </c:choose>
            </div>
            <p>排序：${banner.sort}</p>
            <p>添加：<lch:parse-date time="${banner.addTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <div>
                <c:choose>
                    <c:when test="${banner.status eq 0}">
                        <label style="color:#6de1ff;">已上架</label>
                    </c:when>
                    <c:when test="${banner.status eq 1}">
                        <label style="color:#ff405d;">未上架</label>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="banner-add.jsp"/>