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
</div>



<div id="activity-list">
    <c:forEach var="runLevel" items="${page.result}">
        <div class="activity">
            <div class="uk-thumbnail uk-thumbnail-large">
                <img src="<lch:build-path url="${runLevel.honorImage}"/>">
            </div>
            <div style="margin-top: 10px">
                <strong>
                    ${runLevel.name}(${runLevel.id})
                    <a href="/run-level/run-level-modify.htm?id=${runLevel.id}">修改</a>
                </strong>
            </div>
            <p>添加：<lch:parse-date time="${runLevel.addTime}" pattern="yyyy-MM-dd HH:mm"/></p>
        </div>
    </c:forEach>
</div>