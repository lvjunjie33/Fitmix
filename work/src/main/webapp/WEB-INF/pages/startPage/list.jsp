<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/5/16
  Time: 11:37
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
    <c:forEach var="sp" items="${page.result}">
        <div class="activity">
            <div class="uk-thumbnail uk-thumbnail-large">
                <img src="<lch:build-path url="${sp.backgroundImg}"/>" />
            </div>
            <div style="margin-top: 10px">
                <strong>
                        ${sp.title}(${sp.id})
                    <a href="/start-page/modify.htm?id=${sp.id}">修改</a>&nbsp;&nbsp;&nbsp;
                </strong>
            </div>
            <p>闪屏时长：${sp.countdown}</p>
            <p>闪屏描述：${sp.des}</p>
            <p>添加时间：<lch:parse-date time="${sp.addTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <p>开始时间：<lch:parse-date time="${sp.startTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <p>截止时间：<lch:parse-date time="${sp.deadline}" pattern="yyyy-MM-dd HH:mm"/></p>
            <div>
                <c:choose>
                    <c:when test="${sp.status eq 0}">
                        <label style="color:#6de1ff;">正常</label>
                    </c:when>
                    <c:when test="${sp.status eq 1}">
                        <label style="color:#ff405d;">无效</label>
                    </c:when>
                </c:choose>
            </div>

            <div>
                <c:choose>
                    <c:when test="${sp.releaseStatus eq 0}">
                        <label style="color:#6de1ff;">发布</label>
                    </c:when>
                    <c:when test="${sp.releaseStatus eq 1}">
                        <label style="color:#ffb852;">待发布</label>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>


<%--分页条--%>
<lch:page page="${page}" href="/start-page/list.htm"></lch:page>

<jsp:include page="add.jsp"/>

<%--<jsp:include page="modify.jsp"/>--%>


