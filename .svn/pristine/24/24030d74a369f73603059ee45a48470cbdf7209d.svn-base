<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/5/30
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-uikit.jsp" %>

<html>
<head>
    <title>runPlan</title>
</head>
<body>
<div class="uk-form uk-margin-bottom uk-container uk-container-center" >
    <div class="uk-container uk-container-center">
        <p>
            <a href="#modal-group-1" class="uk-button" data-uk-modal="">Open</a>
            <%--<a href="#modal-group-2" class="uk-button" data-uk-modal="">Open</a>--%>
        </p>
    </div>
    <form action="/run-plan/create-run-plan.htm" method="post" enctype="multipart/form-data" class="uk-form" >
        <div id="modal-group-1" class="uk-modal" aria-hidden="true" style="display: none; overflow-y: auto;">
            <div class="uk-modal-dialog">
                <button type="button" class="uk-modal-close uk-close"></button>
                <p>您想跑多远的距离?</p>
                <p>
                    <select class="uk-width-1-1 uk-form-large" name="type" id="type">
                        <option>选择跑步距离</option>
                        <option value="0">5km</option>
                        <option value="1">10km</option>
                        <option value="2">半程马拉松</option>
                        <option value="3">马拉松</option>
                    </select>
                </p>
                <p>目前您可以跑哪个项目?</p>
                <p id="abilitySelect">
                    <select class="uk-width-1-1 uk-form-large" name="ability" id="ability">
                        <option value="3">5公里</option>
                        <option value="4">10公里</option>
                        <option value="5">半程马拉松</option>
                        <option value="6">马拉松</option>
                        <option value="0">少于20分钟</option>
                        <option value="1">20-30分钟</option>
                        <option value="2">30-60分钟</option>
                    </select>
                </p>
                <div id="time" style="margin-top: 8px;">
                    <input type="text" id="hour" name="hour" placeholder="时" onkeyup="value=this.value.replace(/\D+/g,'')"/>
                    <input type="text" id="min" name="min" placeholder="分" onkeyup="value=this.value.replace(/\D+/g,'')"/>
                    <input type="text" id="second" name="second" placeholder="秒" onkeyup="value=this.value.replace(/\D+/g,'')"/>
                </div>
                <div class="uk-block uk-block-muted" style="margin-top: 8px;" >
                    <div class="uk-width-1-2" style="display: inline-block;margin-right:-8px;" >
                        <p>您的性别是什么?</p>
                        <div>
                            <p><input type="radio" name="gender" value="1"/><label class="uk-text-right">男</label></p>
                            <p><input type="radio" name="gender" value="2"/><label class="uk-text-right">女</label></p>
                        </div>
                    </div>
                    <div class="uk-width-1-2" style="display: inline-block;vertical-align: top;">
                        <p>您的年龄是?</p>
                        <p>
                            <input class="uk-width-1-3" type="text" name="age" onkeyup="value=this.value.replace(/\D+/g,'')"/>
                        </p>
                    </div>
                </div>
                <%--<p class="uk-text-right"><a href="#modal-group-2" data-uk-modal="">Next</a></p>--%>
                <div class="uk-text-right">
                    <input type="hidden" name="ability_time" id="ability_time"/>
                    <%--<input class="uk-button uk-button-large" type="button" id="submit"  value="获取计划"/>--%>
                    <button class="uk-button uk-button-primary" id="submit">获取计划</button>
                </div>
            </div>
        </div>

        <%--<div id="modal-group-2" class="uk-modal" aria-hidden="true" style="display: none; overflow-y: auto;">
            <div class="uk-modal-dialog">
                <button type="button" class="uk-modal-close uk-close"></button>
                <h1>Part 2</h1>
                <p>
                    <select class="uk-width-1-1 uk-form-large" name="ability" id="ability">

                    </select>
                </p>
                <div class="uk-text-right">
                    <a href="#modal-group-1" data-uk-modal="">Previous</a>
                    <a href="#modal-group-3" data-uk-modal="">Next</a>
                </div>
            </div>
        </div>
        <div id="modal-group-3" class="uk-modal" aria-hidden="true" style="display: none; overflow-y: auto;">
            <div class="uk-modal-dialog">
                <button type="button" class="uk-modal-close uk-close"></button>
                <h1>Part 3</h1>
                <p>您想跑XXX的用时是多少</p>
                <p>
                    <input type="text" name="hour" placeholder="时"/>:
                    <input type="text" name="min" placeholder="分"/>:
                    <input type="text" name="second" placeholder="秒"/>
                </p>
                <p class="uk-text-right">
                    <a href="#modal-group-2" data-uk-modal="">Previous</a>
                    <a href="#modal-group-4" data-uk-modal="">Next</a>
                </p>
            </div>
        </div>
        <div id="modal-group-4" class="uk-modal" aria-hidden="true" style="display: none; overflow-y: auto;">
            <div class="uk-modal-dialog">
                <button type="button" class="uk-modal-close uk-close"></button>
                <h1>Part 4</h1>
                <p>您的年龄是？</p>
                <p>
                    <input type="text" name="age" placeholder="00"/>
                </p>
                <p class="uk-text-right">
                    <a href="#modal-group-3" data-uk-modal="">Previous</a>
                    <a href="#modal-group-5" data-uk-modal="">Next</a>
                </p>
            </div>
        </div>
        <div id="modal-group-5" class="uk-modal" aria-hidden="true" style="display: none; overflow-y: auto;">
            <div class="uk-modal-dialog">
                <button type="button" class="uk-modal-close uk-close"></button>
                <h1>Part 4</h1>
                <p>您是男士还是女士?</p>
                <p>
                    <button class="uk-button-primary">男</button>
                    <button class="uk-button-primary">女</button>
                </p>
                <p class="uk-text-right">
                    <a href="#modal-group-4" data-uk-modal="">Previous</a>
                    <button class="uk-button">check</button>
                </p>
            </div>
        </div>--%>
    </form>
</div>
</body>
<script type="text/javascript">
    $("#abilitySelect select").change(function(){
        if(Number($("#ability").val())<3){
            $("#time").hide();
        }else{
            $("#time").show();
        }
    });
    $("#submit").click(function(){
        if(Number($("#ability").val())<3){
            $("#ability_time").val("00:00:00");
           /* alert($("#ability_time").val());*/
        }else{
            if($("#hour").val()==""){
                $("#ability_time").val("00");
            }else{
                $("#ability_time").val($("#hour").val());
            }
            if($("#min").val()==""){
                $("#ability_time").val($("#ability_time").val()+":00");
            }else{
                $("#ability_time").val($("#ability_time").val()+":"+$("#min").val());
            }
            if($("#second").val()==""){
                $("#ability_time").val($("#ability_time").val()+":00");
            }else{
                $("#ability_time").val($("#ability_time").val()+":"+$("#second").val())
            }
         /*   alert($("#ability_time").val());*/
        }

    });
</script>
</html>
