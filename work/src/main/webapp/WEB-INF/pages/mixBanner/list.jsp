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
    <a href="#mixBannerAdd" class="uk-button" data-uk-modal>添加</a>
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
                    <a href="/mix-banner/modify.htm?bannerId=${banner.id}">修改</a>
                </strong>
                <p>${banner.desc}</p>
                <c:choose>
                    <c:when test="${banner.type == 1}">专辑：${banner.typeValue}</c:when>
                    <c:when test="${banner.type == 2}">网页：${banner.typeValue}</c:when>
                    <c:when test="${banner.type == 3}">歌曲：${banner.typeValue}</c:when>
                    <c:when test="${banner.type == 4}">电台：${banner.typeValue}</c:when>
                    <c:when test="${banner.type == 5}">三方平台：${banner.typeValue}</c:when>
                </c:choose>
            </div>
            <p>排序：${banner.sort}</p>
            <p>添加：<lch:parse-date time="${banner.addTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <div>
                <c:choose>
                    <c:when test="${banner.status eq 0}">
                        <label style="color:#6de1ff;">发布</label>
                    </c:when>
                    <c:when test="${banner.status eq 1}">
                        <label style="color:#ff405d;">未发布</label>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>


<%--分页条--%>
<lch:page page="${page}" href="/mix-banner/list.htm"></lch:page>

<jsp:include page="add.jsp"/>
