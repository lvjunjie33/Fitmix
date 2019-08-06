<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/5/18
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<html>
<head>
    <title>runPlanList</title>
</head>
<body>
<div id="content-modify">
    <form action="/run-plan/templet-modify.json" id="modifyForm" method="post" enctype="multipart/form-data" class="uk-form" isform>
        <div class="uk-form-row">
            <input  value="星期一" style="display: inline-block;margin-right: -5px;width:14.28%;" readonly/>
            <input  value="星期二" style="display: inline-block;margin-right: -5px;width:14.28%;" readonly/>
            <input  value="星期三" style="display: inline-block;margin-right: -4px;width:14.28%;" readonly/>
            <input  value="星期四" style="display: inline-block;margin-right: -4px;width:14.28%;" readonly/>
            <input  value="星期五" style="display: inline-block;margin-right: -4px;width:14.28%;" readonly/>
            <input  value="星期六" style="display: inline-block;margin-right: -4px;width:14.28%;" readonly/>
            <input  value="星期日" style="display: inline-block;margin-right: -4px;width:14.28%;" readonly/>
        </div>

        <div id="templet-content" class="uk-form-row">
            <c:forEach items="${stage}" var="entry">
                        <input class="uk-width-1-1 uk-form-large" type="text" value="${entry.value[0].stageName}"/>
                <c:forEach items="${entry.value}" var="content">
                    <input type="text" class="uk-form-large" style="display: inline-block;margin-right: -5px;width:14.28%;" value="${content.stageContent}"/>
                </c:forEach>
            </c:forEach>
        </div>
        <div class="uk-form-row uk-text-right">
            <input type="button" class="uk-button" id="modify" value="modify"/>
            <input type="hidden" id="result" name="result">
            <input type="hidden" id="tid" name="tid" value="${tid}">
            <input type="hidden" id="stageName" name="stageName">
        </div>
    </form>
</div>
<script type="text/javascript">
$(function(){
    $("#modify").click(function(){
        var array = new Array();
        var stageName = new Array();
        var stage = Number(-1);
        var count = Number(0);
        $("#templet-content input:text").each(function(){
            var v = $(this).val();
            if(v.indexOf("阶段")!=-1){
                stage++;
                count= Number(0);
                array[stage] = new Array();
                stageName[stage] = v;
            }else{
                array[stage][count]= v;
                count++;
            }
        });
        $("#result").val(array);
        $("#stageName").val(stageName);
        console.log($("#result").val());
        console.log($("#stageName").val());
        console.log($("#tid").val());
        $("form").submit();
    });
})
</script>
</body>
</html>