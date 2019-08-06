<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/6/8
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="module-add" class="uk-modal up-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Module Add</h1>
        <div class="uk-form-row">
            <form action="/run-plan/add-modify-content.htm" method="POST" class="uk-form login-form">
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="type">
                        <option value="0">5KM</option>
                        <option value="1">10KM</option>
                        <option value="2">半程马拉松</option>
                        <option value="3">马拉松</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="classify" placeholder="classify" notnull>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="beginTime">
                        <option value="0">星期一</option>
                        <option value="1">星期二</option>
                        <option value="2">星期三</option>
                        <option value="3">星期四</option>
                        <option value="4">星期五</option>
                        <option value="5">星期六</option>
                        <option value="6">星期日</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" id="stage" type="number" name="stage" placeholder="stage">
                </div>
                <div class="uk-form-row" id="stageContent">
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="submit">add</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#stage").change(function(){
        var count = $("#stage").val();
        var content = "";
        for(var i=0;i<count;i++){
            content += "<input class=\"uk-width-1-1 uk-form-large\" name=\"stageCount\" type=\"text\"  placeholder=\"请输入第"+(i+1)+"个阶段的周数\">";
        }
        document.getElementById("stageContent").innerHTML = content;
    });
</script>