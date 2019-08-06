/**
 * Created by weird on 2016/7/13.
 */
$(document).ready(function(){
    //禁止右键
    $("#run_plan_contain").bind("contextmenu",function(e){
        return false;
    });
    
    $("#delay-button").click(function(){
        var delayDay = Number($("#delay-day").html());
        $.ajax({
            type:"POST",
            async : true,
            dateType : "json" ,
            url:"/run-plan/delay-plan.json",
            data:{delayDay:delayDay},
            success:function(data){
                if(data.code == 0){
                    alert("修改成功");
                    window.location.reload();
                }else{
                    alert("修改失败");
                }
            },
            error:function(){
                alert("异常!");
            }
        })
    });
    
    $("#cancel-plan-true").click(function(){
        $.ajax({
            type:"POST",
            async: true,
            dateType : "json" ,
            url:"/run-plan/cancel-plan.json",
            success:function(data){
                if(data.code == 0){
                    alert("取消成功");
                    window.location ="/run-plan/run-plan-presentation.htm";
                }else{
                    alert("取消失败");
                    window.location.reload();
                }
            },
            error:function(){
                alert("异常！！");
            }
        })
    });
});