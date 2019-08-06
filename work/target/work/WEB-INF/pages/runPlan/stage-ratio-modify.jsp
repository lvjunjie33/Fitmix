<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/5/22
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<html>
<head>
    <title>StageRatioModify</title>
</head>
<body>
<form action="/run-plan/change-stage-ratio.json" id="modifyForm" method="post" enctype="multipart/form-data" class="uk-form"
      isform>
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
        <thead>
        <tr>
            <th>类型</th>
            <th>总比值</th>
            <c:forEach var="item" items="${stageRatio.runStages}">
                <th>
                    ${item.name}
                </th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input class="uk-width-1-1 uk-form-large" name="type" id="type" type="text" value="${stageRatio.type}" readonly></td>
            <td><input class="uk-width-1-1 uk-form-large" name="distance" type="text" value="${stageRatio.distance}"></td>
            <c:forEach var="list" items="${stageRatio.runStages}">
                <td>
                    <c:choose>
                        <c:when test="${fn:length(list.ratio) eq 1}">
                            <input class="uk-width-1-1 uk-form-large" type="text" name="${list.nameEn}" value="${list.ratio[0]}"/>
                        </c:when>
                        <c:when test="${fn:length(list.ratio) eq 2}">
                            <input class="uk-width-1-1 uk-form-large" type="text" name="${list.nameEn}"
                                   value="${list.ratio[0]}-${list.ratio[1]}"/>
                        </c:when>
                    </c:choose>
                </td>
            </c:forEach>
            <%--<td>
                <c:choose>
                    <c:when test="${fn:length(stageRatio.jog) eq 0}">
                        <input class="uk-width-1-1 uk-form-large" name="jog" value="-"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.jog) eq 1}">
                        <input class="uk-width-1-1 uk-form-large" name="jog" value="${stageRatio.jog[0]}"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.jog) eq 2}">
                        <input class="uk-width-1-1 uk-form-large" name="jog"
                               value="${stageRatio.jog[0]}-${stageRatio.jog[1]}"/>
                    </c:when>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${fn:length(stageRatio.comfortable) eq 0}">
                        <input class="uk-width-1-1 uk-form-large" name="comfortable" value="-"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.comfortable) eq 1}">
                        <input class="uk-width-1-1 uk-form-large" name="comfortable"
                               value="${stageRatio.comfortable[0]}"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.comfortable) eq 2}">
                        <input class="uk-width-1-1 uk-form-large" name="comfortable"
                               value="${stageRatio.comfortable[0]}-${stageRatio.comfortable[1]}"/>
                    </c:when>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${fn:length(stageRatio.interval) eq 0}">
                        <input class="uk-width-1-1 uk-form-large" name="interval" value="-"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.interval) eq 1}">
                        <input class="uk-width-1-1 uk-form-large" name="interval" value="${stageRatio.interval[0]}"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.interval) eq 2}">
                        <input class="uk-width-1-1 uk-form-large" name="interval"
                               value="${stageRatio.interval[0]}-${stageRatio.interval[1]}"/>
                    </c:when>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${fn:length(stageRatio.buildUp) eq 0}">
                        <input class="uk-width-1-1 uk-form-large" name="buildUp" value="-"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.buildUp) eq 1}">
                        <input class="uk-width-1-1 uk-form-large" name="buildUp" value="${stageRatio.buildUp[0]}"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.buildUp) eq 2}">
                        <input class="uk-width-1-1 uk-form-large" name="buildUp"
                               value="${stageRatio.buildUp[0]}-${stageRatio.buildUp[1]}"/>
                    </c:when>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${fn:length(stageRatio.fast) eq 0}">
                        <input class="uk-width-1-1 uk-form-large" name="fast" value="-"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.fast) eq 1}">
                        <input class="uk-width-1-1 uk-form-large" name="fast" value="${stageRatio.fast[0]}"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.fast) eq 2}">
                        <input class="uk-width-1-1 uk-form-large" name="fast"
                               value="${stageRatio.fast[0]}-${stageRatio.fast[1]}"/>
                    </c:when>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${fn:length(stageRatio.pace) eq 0}">
                        <input class="uk-width-1-1 uk-form-large" name="pace" value="-"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.pace) eq 1}">
                        <input class="uk-width-1-1 uk-form-large" name="pace" value="${stageRatio.pace[0]}"/>
                    </c:when>
                    <c:when test="${fn:length(stageRatio.pace) eq 2}">
                        <input class="uk-width-1-1 uk-form-large" name="pace"
                               value="${stageRatio.pace[0]}-${stageRatio.pace[1]}"/>
                    </c:when>
                </c:choose>
            </td>--%>
        </tr>
        </tbody>
    </table>
    <div class="uk-form-row uk-text-right">
        <input type="button" class="uk-button" id="modify" value="modify"/>
        <input type="hidden" id="hiddenResult" name="result" />
    </div>
</form>
<script type="text/javascript">
    $("#modify").click(function(){
        var str="";
        $("input:text").each(function(){
            var name = $(this).attr("name");
            var value = $(this).val();
            if(str.length==0){
                str += name+","+value;
            }else{
                str += ","+name+","+value;
            }
        });
        $("#hiddenResult").val(str);
      /*  console.log(str);
        console.log($("#type").val());
        console.log($("#hiddenResult").val())*/
        $("form").submit();
    });

</script>
</body>
</html>
