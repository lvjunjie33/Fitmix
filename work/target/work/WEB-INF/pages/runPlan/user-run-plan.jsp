<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/5/31
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="uk-container-center uk-body">
        <div class="uk-form uk-margin-top uk-text-nowrap">
            <div class="uk-width-1-3 uk-text-center" style="display: inline-block;margin-right:-6px;border: inset;">
                <h3>您的目标用时</h3>
                <p>${info[0]}</p>
            </div>
            <div class="uk-width-1-3 uk-text-center" style="display: inline-block;margin-right:-8px;border: inset;">
                <h3>您的跑步距离</h3>
                <p>${info[1]}</p>
            </div>
            <div class="uk-width-1-3 uk-text-center" style="display: inline-block;border: inset;">
                <h3>计划完成时间</h3>
                <p>${info[2]}</p>
            </div>
        </div>
        <div style="border:inset;">
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container" style="border:inherit;">
                <thead >
                <tr>
                    <th class="uk-text-center">星期一</th>
                    <th class="uk-text-center">星期二</th>
                    <th class="uk-text-center">星期三</th>
                    <th class="uk-text-center">星期四</th>
                    <th class="uk-text-center">星期五</th>
                    <th class="uk-text-center">星期六</th>
                    <th class="uk-text-center">星期日</th>
                </tr>
                </thead>
                <tbody>
<%--                <c:forEach items="${runPlan.stages}" var="entry">

                </c:forEach>--%>
                <input type="hidden" id="stages" value="${runPlan.stages}">
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
