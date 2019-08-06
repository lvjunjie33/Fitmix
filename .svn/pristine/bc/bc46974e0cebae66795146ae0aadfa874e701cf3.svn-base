/**
 * Created by weird on 2016/7/13.
 */
$(document).ready(function () {
    //禁止右键
    $("#run_plan_contain").bind("contextmenu", function (e) {
        return false;
    });
    //初始化配置
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
    $("#delay-button").click(function () {
        var delayDay = Number($("#delay-day").html());
        $.ajax({
            type: "POST",
            async: true,
            dateType: "json",
            url: "run-plan/delay-plan.json",
            data: {delayDay: delayDay},
            success: function (data) {
                if (data.code == 0) {
                    alert("修改成功");
                    window.location.reload();
                } else {
                    alert("修改失败");
                }
            },
            error: function () {
                alert("异常!");
            }
        })
    });
});