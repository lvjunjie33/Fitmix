<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/5/17
  Time: 11:05
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
    <c:forEach var="sv" items="${page.result}">
        <div class="activity">
            <div class="uk-thumbnail uk-thumbnail-large">
                <img src="<lch:build-path url="${sv.backgroundImg}"/>" width="390" />
            </div>
            <br/>
            <div class="uk-thumbnail uk-thumbnail-large">
                <video src="<lch:build-path url="${sv.video}"/>" controls="controls" width="390"></video>
            </div>
            <div style="margin-top: 10px">
                <strong>
                        ${sv.title}(${sv.id})
                    <a href="/start-video/modify.htm?id=${sv.id}">修改</a>&nbsp;&nbsp;&nbsp;
                </strong>
            </div>
            <p>视频描述：${sv.des}</p>
            <p>添加时间：<lch:parse-date time="${sv.addTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <p>开始时间：<lch:parse-date time="${sv.startTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <p>截止时间：<lch:parse-date time="${sv.deadline}" pattern="yyyy-MM-dd HH:mm"/></p>
            <div>
                <c:choose>
                    <c:when test="${sv.status eq 0}">
                        <label style="color:#6de1ff;">正常</label>
                    </c:when>
                    <c:when test="${sv.status eq 1}">
                        <label style="color:#ff405d;">无效</label>
                    </c:when>
                </c:choose>
            </div>

            <div>
                <c:choose>
                    <c:when test="${sv.releaseStatus eq 0}">
                        <label style="color:#6de1ff;">发布</label>
                    </c:when>
                    <c:when test="${sv.releaseStatus eq 1}">
                        <label style="color:#ffb852;">待发布</label>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/start-video/list.htm"></lch:page>

<jsp:include page="add.jsp"/>

<%--<jsp:include page="modify.jsp"/>--%>



