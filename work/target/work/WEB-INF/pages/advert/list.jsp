<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/5/16
  Time: 16:15
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
    .group-fee-span{
        min-width: 410px;
        display: inline-block;
    }

    .group-fee-span1{
        min-width: 150px;
        display: inline-block;
    }

</style>

<div class="uk-form-row">
    <a href="#activity-add" class="uk-button" data-uk-modal>添加</a>
</div>

<div id="activity-list">
    <c:forEach var="advert" items="${page.result}">
        <div class="activity">
            <div class="uk-thumbnail uk-thumbnail-large">
                <img src="<lch:build-path url="${advert.advertImg}"/>" />
            </div>
            <div style="margin-top: 10px">
                <strong>
                        ${advert.title}(${advert.id})
                    <a href="/advert/modify.htm?id=${advert.id}">修改</a>&nbsp;&nbsp;&nbsp;
                </strong>
            </div>
            <p>链接地址：<a href="${advert.toUrl}">${advert.toUrl}</a></p>
            <p>广告描述：${advert.des}</p>
            <p>添加时间：<lch:parse-date time="${advert.addTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <p>开始时间：<lch:parse-date time="${advert.startTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <p>截止时间：<lch:parse-date time="${advert.deadline}" pattern="yyyy-MM-dd HH:mm"/></p>

            <div>
                <c:choose>
                    <c:when test="${advert.status eq 0}">
                        <label style="color:#6de1ff;">正常</label>
                    </c:when>
                    <c:when test="${advert.status eq 1}">
                        <label style="color:#ff405d;">无效</label>
                    </c:when>
                </c:choose>
            </div>

            <div>
                <c:choose>
                    <c:when test="${advert.releaseStatus eq 0}">
                        <label style="color:#6de1ff;">发布</label>
                    </c:when>
                    <c:when test="${advert.releaseStatus eq 1}">
                        <label style="color:#ffb852;">待发布</label>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>


<%--分页条--%>
<lch:page page="${page}" href="/advert/list.htm"></lch:page>

<jsp:include page="add.jsp"/>

<%--<jsp:include page="modify.jsp"/>--%>


