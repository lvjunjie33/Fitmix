<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/6/1
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="classify-add" class="uk-modal up-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Classify Add</h1>
        <div class="uk-form-row">
            <form action="/run-plan/classify-add.json" method="POST" class="uk-form login-form" id="classify-form" isform>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="type">
                        <option value="0">5KM</option>
                        <option value="1">10KM</option>
                        <option value="2">半程马拉松</option>
                        <option value="3">马拉松</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="ability">
                        <option value="0">少于20分钟</option>
                        <option value="1">20-30分钟</option>
                        <option value="2">30-60分钟</option>
                        <option value="3" selected>5KM</option>
                        <option value="4">10KM</option>
                        <option value="5">半程马拉松</option>
                        <option value="6">马拉松</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="classify" placeholder="classify" notnull>
                </div>
                <div id="speed" class="uk-form-row">
                    <input class="uk-width-1-2 uk-form-large" type="number" name="speed_min" style="margin-left:-5px;" placeholder="speed_min" >
                    <input class="uk-width-1-2 uk-form-large" type="number" name="speed_max" placeholder="speed_max" >
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="claasify-add-submit">add</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#classify-form [name=ability]").change(function(){
        var temp = $(this).val();
        if(temp<3){
            $("#speed").hide();
        }else{
            $("#speed").show();
        }
    });
</script>