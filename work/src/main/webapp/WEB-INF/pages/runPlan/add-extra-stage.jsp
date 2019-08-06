<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/10/8
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<html>
<head>
    <title>ExtraStage</title>
</head>

<body>
<div id="content-modify">
    <form action="/run-plan/add-extra-stage.json"  method="POST" enctype="multipart/form-data" class="uk-form" isform>
        <div class="uk-form-row">
            <div class="uk-width-1-2" style="display: inline-block;margin-right:-8px;">
                <h3>type</h3>
                <input class="uk-width-1-1 uk-form-large" type="number" name="type" placeholder="type" value="${type}" notnull>
            </div>
            <div class="uk-width-1-2" style="display: inline-block;margin-right:-8px;">
                <h3>classify</h3>
                <input class="uk-width-1-1 uk-form-large" type="number" name="classify" placeholder="classify" value="${classify}" notnull>
            </div>
        </div>
        <div id="content-table" class="uk-form-row">
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                <tr>
                    <th>星期一</th>
                    <th>星期二</th>
                    <th>星期三</th>
                    <th>星期四</th>
                    <th>星期五</th>
                    <th>星期六</th>
                    <th>星期日</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="7">
                        <input class="uk-width-1-1 uk-form-large" name="stageName" type="text" placeholder="stageName" notnull/>
                    </td>
                </tr>
                <c:forEach begin="1" end="${stage}">
                    <tr>
                        <c:forEach begin="0" end="6">
                            <td>
                                <input class="uk-width-1-1 uk-form-large" name="stageContent" type="text" placeholder="stageContent" notnull/>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
        <div class="uk-form-row uk-text-right">
            <input type="submit" class="uk-button" id="content-add" value="add"/>
        </div>
    </form>
</div>
<script type="text/javascript">

    /*$(function(){
     $("#type").get(0).selectedIndex=$("#type_hidden").val();
     $("#beginTime").get(0).selectedIndex=$("#beginTime_hidden").val();
     $("#content-add").click(function(){
     var array = new Array();
     var stageName = new Array();
     var stage = Number(-1);
     var count = Number(0);
     $("#content-table input:text").each(function(){
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
     console.log(array);
     });
     });*/
</script>
</body>
</html>