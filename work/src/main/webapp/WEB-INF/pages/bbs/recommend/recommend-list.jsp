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
    <c:forEach var="recommend" items="${recommendList}">
        <div class="activity">
            <div class="uk-thumbnail uk-thumbnail-large">
                <img src="<lch:build-path url="${recommend.backImage}"/>">
            </div>
            <div style="margin-top: 10px">
                <strong>
                    ${recommend.title}(${recommend.id})
                    <a href="/bbs/recommend/recommend-modify.htm?id=${recommend.id}">修改</a>
                </strong>
                <p>角标：${recommend.cornerMark}</p>
                <p>简介：${recommend.desc}</p>
                <c:choose>
                    <c:when test="${recommend.type == 1}">文章：${recommend.typeValue}</c:when>
                    <c:when test="${recommend.type == 2}">网页：${recommend.typeValue}</c:when>
                </c:choose>
            </div>
            <p>排序：${recommend.sort}</p>
            <p>添加：<lch:parse-date time="${recommend.addTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <div>
                <c:choose>
                    <c:when test="${recommend.status eq 0}">
                        <label style="color:#6de1ff;">发布</label>
                    </c:when>
                    <c:when test="${recommend.status eq 1}">
                        <label style="color:#ff405d;">未发布</label>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="recommend-add.jsp"/>