/**
 * Created by weird on 2016/7/29.
 */
$(document).ready(function () {
    //全屏滚动 初始化
    /*$("#fullpage").fullpage({
        verticalCentered: false,
        navigation: true,
        navigationPosition: 'right',
        loopBottom: true,
        afterLoad: function (anchorLink, index) {
            if (index == 3) {
                if (!$(".noUi-handle").hasClass("shake-noUi-handle")) {
                    $(".noUi-origin").css("left", "2.586%");
                    $(".noUi-handle").addClass("shake-noUi-handle");
                    $(".noUi-handle").css("opacity", "1", 1);
                }
            }
        }
    });*/

    //检测比赛时间以及目标用时模块
    $("#for-event").change(function(){
        if($("#for-event").attr("checked")) {
            $("#compete-calender").show();
            $("#targetTime").show();
        } else {
            $("#compete-calender").hide();
            $("#targetTime").hide();
        }
    });

    $("#unchecked").change(function(){
        if($("#unchecked").attr("checked")) {
            $("#selectTime").attr("disabled","true");
            $("#selectTime").addClass("cal_inp");
            $("#selectTime").removeAttr("placeholder");
        } else {
            $("#selectTime").removeAttr("disabled");
            $("#selectTime").removeClass("cal_inp");
            $("#selectTime").attr("placeholder", "请选择时间");
        }
    });

    $("#unchecked-target").change(function(){
        if($("#unchecked-target").attr("checked")) {
            $("#target-hour").attr("disabled", "true");
            $("#target-hour").addClass("cal_inp");
            $("#target-hour").removeAttr("placeholder");

            $("#target-minute").attr("disabled", "true");
            $("#target-minute").addClass("cal_inp");
            $("#target-minute").removeAttr("placeholder");

            $("#target-second").attr("disabled", "true");
            $("#target-second").addClass("cal_inp");
            $("#target-second").removeAttr("placeholder");
        } else {
            $("#target-hour").removeAttr("disabled");
            $("#target-hour").removeClass("cal_inp");
            $("#target-hour").attr("placeholder", "时");

            $("#target-minute").removeAttr("disabled");
            $("#target-minute").removeClass("cal_inp");
            $("#target-minute").attr("placeholder", "分");

            $("#target-second").removeAttr("disabled");
            $("#target-second").removeClass("cal_inp");
            $("#target-second").attr("placeholder", "秒");
        }
    });

    //单选框 美化
    $(".dowebok").find(":input").labelauty();

    //初始化选项卡配置
    var stage = $(".stage");
    var id = stage.attr("name").split("_");
    $("#run_plan_theme").attr("href", "/static/run-plan/css/theme_" + id[3] + ".css");
    $(".run_plan_column_1").addClass("run_plan_column_1_" + id[3]);
    $(".run_plan_column_2").addClass("run_plan_column_2_" + id[3]);
    //实现阶段切换(选项卡)
    $(".stage").each(function () {
        var id = $(this).attr("name").split("_");
        $(this).hover(function () {
            $(this).addClass("hover_" + id[3]);
        }, function () {
            $(this).removeClass("hover_" + id[3]);
        });
        $(this).click(function () {
            $(".stage").each(function () {
                var id = $(this).attr("name").split("_");
                $(this).removeClass("stage_checked_" + id[3]);
                $(".run_plan_column_1").removeClass("run_plan_column_1_" + id[3]);
                $(".run_plan_column_2").removeClass("run_plan_column_2_" + id[3]);
                $("#stage_content_" + id[3]).hide();
            });
            var id = $(this).attr("name").split("_");
            $(this).addClass("stage_checked_" + id[3]);
            $("#run_plan_theme").attr("href", "/static/run-plan/css/theme_" + id[3] + ".css");
            $("#stage_content_" + id[3]).show();
            $(".run_plan_column_1").addClass("run_plan_column_1_" + id[3]);
            $(".run_plan_column_2").addClass("run_plan_column_2_" + id[3]);
        });
    });

    //创建计划 运动时间 事件
    $("select[name=runProject]").on("change", function () {
        if ($(this).val().indexOf("beginner") != -1) {
            $("#runTimeSetting").css("display", "none");
        } else {
            $("#runTimeSetting").css("display", "");
        }
    });

    $(".run-plan-row > div").each(function () {
        var that = $(this);
        that.click(function () {
            $(".tooltip").remove();
            var val = $(this).attr("data-run-type");
            if (val == "jogging") {
                $(this).append('<div class="tooltip"><div class="tooltip-content"><div class="close-icon">×</div><p><span class="label">慢跑</span><span class="description">这项训练将逐步练习跑步时涉及的肌肉。</span></p></div></div>');
            } else if (val == "rest") {
                $(this).append('<div class="tooltip"><div class="tooltip-content"><div class="close-icon">×</div><p><span class="label">休息日</span><span class="description">休息与您的跑步训练同样重要。休息让您的身体得以恢复并在下一次跑步时变得更加强大。</span></p></div></div>');
            } else if (val == "fast") {
                $(this).append('<div class="tooltip"><div class="tooltip-content"><div class="close-icon">×</div><p><span class="label">快跑</span><span class="description">务必以高于您感觉舒适的速度跑步。您的呼吸应比平时跑步时稍快、稍深一些。</span></p></div></div>');
            } else if (val == "comfortable") {
                $(this).append('<div class="tooltip"><div class="tooltip-content"><div class="close-icon">×</div><p><span class="label">舒适</span><span class="description">舒适的跑步使您的身体在训练中不会负担过重。</span></p></div></div>');
            }

            $(".close-icon").click(function (e) {
                e.stopPropagation();
                $(".tooltip").remove();
            });
            $(".tooltip").addClass("show", 10);

        });
    });

    //提交参数->创建跑步计划
    /*  $("#parameter-submit").click(function () {
     $("#hour").val($("#hour").val() == "" ? 0 : $("#hour").val());
     $("#minute").val($("#minute").val() == "" ? 0 : $("#minute").val());
     $("#second").val($("#second").val() == "" ? 0 : $("#second").val());
     $("#age").val($("#age").val() == "" ? 0 : $("#age").val());
     });*/

    $("#runDistance").change(function () {

        if ($("#runDistance").val() != -1) {
            $(".runDistance-tip").hide();
            $("#runDistance").removeClass("border-danger");
        }
        if ($("#runDistance").val() == -1) {
            $(".runDistance-tip").html("请选择跑步距离!");
            $(".runDistance-tip").show();
        }
    }).mouseleave(function(){
        $("#runDistance").css("color","#FFF");
    }).mouseenter(function(){
        $("#runDistance").css("color","#494F56");
    });

    $("#calendar").click(function () {
        $(".calendar-tip").hide();
        $("#calendar").removeClass("border-danger");
    }).mousedown(function(){
        $(this).css("color","#FFF");
    });

    $("#calendar").mousedown(function () {
        if ($("#calendar").val() == "") {
            var date = new Date();
            $("#calendar").val(date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate());
        }
    });

    $("#runProject").mouseleave(function(){
        $("#runProject").css("color","#FFF");
    }).mouseenter(function(){
        $("#runProject").css("color","#494F56");
    })

    $("#hour").keyup(function () {
        var text = $(this).val();
        text = text.replace(/\D+/g, '');
        $("#hour").css("color","#FFF");
        if (Number(text) <= 23 && Number(text) >= 0) {
            $(".setting-tip").hide();
            $("#hour").removeClass("border-danger");
        }
        if (Number(text) > 23 || Number(text) < 0) {
            text = "";
            $(".setting-tip").html("请输入正确的时间格式!");
            $(".setting-tip").show();
        }
        $(this).val(text);
    });

    $("#minute").keyup(function () {
        var text = $(this).val();
        text = text.replace(/\D+/g, '');
        $("#minute").css("color","#FFF");
        if (Number(text) <= 59 && Number(text) >= 0) {
            $(".setting-tip").hide();
            $("#minute").removeClass("border-danger");
        }
        if (Number(text) > 59 || Number(text) < 0) {
            text = "";
            $(".setting-tip").html("请输入正确的时间格式!");
            $(".setting-tip").show();
        }
        $(this).val(text);
    });

    $("#second").keyup(function () {
        var text = $(this).val();
        text = text.replace(/\D+/g, '');
        $("#second").css("color","#FFF");
        if (Number(text) <= 59 && Number(text) >= 0) {
            $(".setting-tip").hide();
            $("#second").removeClass("border-danger");
        }
        if (Number(text) > 59 || Number(text) < 0) {
            text = "";
            $(".setting-tip").html("请输入正确的时间格式!");
            $(".setting-tip").show();
        }
        $(this).val(text);
    });

    $("#age").keyup(function () {
        var text = $(this).val();
        text = text.replace(/\D+/g, '');
        $("#age").css("color","#FFF");
        if (Number(text) <= 100 && Number(text) >= 0) {
            $(".age-tip").hide();
            $("#age").removeClass("border-danger");
        }
        if (Number(text) > 100 || Number(text) < 0) {
            text = "";
            $(".age-tip").html("年龄区间为0~100");
            $(".age-tip").show();
        }
        $(this).val(text);
    });

    $("#parameter-submit").click(function () {
        var state = true;
        if ($("#runDistance").val() == -1) {
            $(".runDistance-tip").html("请选择跑步距离!");
            $(".runDistance-tip").show();
            $("#runDistance").addClass("border-danger");
            state = false;
        }

        if ($("#calendar").val() == "") {
            $(".calendar-tip").html("请选择日期");
            $(".calendar-tip").show();
            $("#calendar").addClass("border-danger");
        }

        if ($("#runTimeSetting").css("display") == 'block') {
            if ($('#hour').val() == '' && $('#minute').val() == '' && $('#second').val() == '') {
                $(".setting-tip").html("请输入时间!");
                $(".setting-tip").show();
                $("#hour").addClass("border-danger");
                $("#minute").addClass("border-danger");
                $("#second").addClass("border-danger");
                state = false;
            }
        }

        if ($("#age").val() == "") {
            $(".age-tip").html("请输入年龄");
            $(".age-tip").show();
            $("#age").addClass("border-danger");
            state = false;
        }

        if (state) {
            $("#hour").val($("#hour").val() == "" ? 0 : $("#hour").val());
            $("#minute").val($("#minute").val() == "" ? 0 : $("#minute").val());
            $("#second").val($("#second").val() == "" ? 0 : $("#second").val());
            return true;
        } else {
            return false;
        }

    });

    $(window).scroll(function(){  //只要窗口滚动,就触发下面代码
        var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
        if( scrollt > $("#section0").height() ){  //判断滚动后高度超过200px,就显示
            $("#turn_to_top").fadeIn(400); //淡出
        }else{
            $("#turn_to_top").stop().fadeOut(400); //如果返回或者没有超过,就淡入.必须加上stop()停止之前动画,否则会出现闪动
        }
    });
    $("#turn_to_top").click(function(){ //当点击标签的时候,使用animate在200毫秒的时间内,滚到顶部
        $("html,body").animate({scrollTop:"0px"},200);
    });



});