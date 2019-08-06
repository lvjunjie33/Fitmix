<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/6/3
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="classify-modify" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Classify modify</h1>
        <div class="uk-form-row">
            <form action="/run-plan/classify-modify.json" method="post" class="uk-form login-form" id="classify-modify-form" isform>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="type" id="type_select">
                        <option value="0">5KM</option>
                        <option value="1" selected>10KM</option>
                        <option value="2">半程马拉松</option>
                        <option value="3">马拉松</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="ability" id="ability_select">
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
                    <input class="uk-width-1-1 uk-form-large" type="number" name="classify"  placeholder="classify" notnull>
                </div>
                <div id="speed" class="uk-form-row">
                    <input class="uk-width-1-2 uk-form-large" type="number" name="speed_min" style="margin-left:-5px;" placeholder="speed_min" >
                    <input class="uk-width-1-2 uk-form-large" type="number" name="speed_max" placeholder="speed_max" >
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="claasify-modify-submit">modify</button>
                    <input type="hidden" name="cid">
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#classify-modify-form [name=ability]").change(function(){
        var temp = $(this).val();
        if(temp<3){
            $("#classify-modify-form #speed").hide();
        }else{
            $("#classify-modify-form #speed").show();
        }
    });
    $(function(){
        $("[classify-modify]").click(function(){
/*            var th = $(this);
            var dataJson = th.parent().parent().sequenceJson();
            $("#resource-modify-form").fillNode(dataJson);
            $("#resource-modify-form [name=type]").change();
            var th = $(this);
            var dataJson = th.parent().parent().sequenceJson();
            console.log($("#ability_select").get(0).selectedIndex);
            $("#classify-modify-form").fillNode(dataJson);*/
/*            $("#type_select").get(0).selectedIndex=$("#modify [name=type]").val();
            $("#ability_select").get(0).selectedIndex=$("#modify [name=ability]").val();
            console.log($("#classify_content table tbody tr td [name=classify]").val());*/
            //$("#classify-modify-form [name=classify]").val($("#classify_table [name=classify]").val());
            /*$("#classify-modify-form [name=]")*/;
            var th = $(this);
            var dataJson = th.parent().parent().parent().sequenceJson();
            console.log(dataJson.id);
            $("#classify-modify-form [name=cid]").val(dataJson.id);
            console.log($("#classify-modify-form [name=cid]").val());
            $("#type_select").get(0).selectedIndex=dataJson.type;
            $("#ability_select").get(0).selectedIndex=dataJson.aid;
            $("#classify-modify-form [name=classify]").val(dataJson.classify);
            var speedArray = dataJson.speed.split("-");
            $("#classify-modify-form [name=speed_min]").val(speedArray[0]);
            $("#classify-modify-form [name=speed_max]").val(speedArray[1]);
            $("#classify-modify-form [name=ability]").change();
            UIkit.modal("#classify-modify").show();
        });
    });
</script>