/**
 * Created by weird on 2016/8/9.
 */
$(document).ready(function () {
    //切换注册类型
    $("#reg-type-phone").click(function () {
        $("#reg-type-email").removeClass("checked");
        $("#reg-type-phone").addClass("checked");
        $(".reg-phone").show();
        $(".reg-email").hide();
    });
    $("#reg-type-email").click(function () {
        $("#reg-type-phone").removeClass("checked");
        $("#reg-type-email").addClass("checked");
        $(".reg-email").show();
        $(".reg-phone").hide();
    });

    //点击输入手机号码框时显示获取验证码内容
    $("#phone-item").click(function () {
        $(".wap-resend-label").show();
    });

    var util = {
        wait: 60,
        hsTime: function (that) {
            _this = this;
            if (_this.wait == 0) {
                $("#get-code-btn").removeAttr("disabled").html("重新发送");
                _this.wait = 60;
            } else {
                var _this = this;
                $(that).attr("disabled", true).html("重新发送(" + _this.wait + ")");
                _this.wait--;
                setTimeout(function () {
                    _this.hsTime(that);
                }, 1000)
            }
        }
    }

    //发送验证码
    $("#get-code-btn").click(function () {
        var mobile = $("#phone-item").val();
        var pattern = /^1[34578]\d{9}$/;
        var state = true ;
        if(mobile == "") {
            $("#phone-null").show();
            $("#phone-item").css("border-color","red");
            state = false
        }else if(!pattern.test(mobile)) {
            $("#phone-item").css("border-color","red");
            $("#phone-pattern-error").show();
            state = false;
        }
        
        if(state){
            //限制按钮并倒计时
            util.hsTime("#get-code-btn");
            $.ajax({
                type: "POST",
                async: true,
                dateType: "json",
                url: "/user/user-verify-code.json",
                data: {
                    mobile: mobile,
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk
                },
                success: function (data) {
                    console.log(data);
                    if (data.code == 0) {
                        code = data.vcode;
                        console.log(code);
                        alert("验证码已发出");
                    } else {
                        alert("发送失败");
                    }
                },
                error: function () {
                    alert("异常");
                }
            })
        }
    });

    $("#phone-item").click(function(){
        $("#phone-null").hide();
        $("#phone-item").css("border-color","#8e8e8e");
        $("#phone-pattern-error").hide();
    });

    $("#verify-code").click(function(){
       $("#verify-code").css("border-color","#8e8e8e");
       $("#code-null").hide();
    });

    $("#psw-ph").click(function(){
        $("#psw-ph").css("border-color","#8e8e8e");
        $("#c-psw-ph").css("border-color","#8e8e8e");
        $("#psw-null").hide();
        $("#psw-difference").hide();
    });

    $("#c-psw-ph").click(function(){
        $("#psw-ph").css("border-color","#8e8e8e");
        $("#c-psw-ph").css("border-color","#8e8e8e");
        $("#psw-null").hide();
        $("#psw-difference").hide();
    });

    $("#email-item").click(function(){
        $("#em-null").hide();
        $("#email-error").hide();
        $("#email-item").css("border-color","#8e8e8e");
    });

    $("#psw-em").click(function(){
        $("#psw-em").css("border-color","#8e8e8e");
        $("#c-psw-em").css("border-color","#8e8e8e");
        $("#em-psw-null").hide();
        $("#em-psw-difference").hide();
    });

    $("#c-psw-em").click(function(){
        $("#psw-em").css("border-color","#8e8e8e");
        $("#c-psw-em").css("border-color","#8e8e8e");
        $("#em-psw-null").hide();
        $("#em-psw-difference").hide();
    });

    //注册
    $(".ph-em-reg-btn").click(function () {
        var pattern = /^1[34578]\d{9}$/;
        var pattern_email = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        var regType = $(".checked").attr("id") == "reg-type-phone" ? 0 : 1;
        var verifyCode = (regType == 0) ? $("#verify-code").val() : -1;
        var password = (regType == 0) ? $("#psw-ph").val() : $("#psw-em").val();
        var mobile = (regType == 0) ? $("#phone-item").val() : $("#email-item").val();
        var state = true;
        if (mobile == "" || verifyCode == "" || password == "") {
            state = false;
            alert("请输入信息");
            if(regType == 0) {
                if(mobile == "") {
                    $("#phone-item").css("border-color","red");
                    $("#phone-null").show();
                }
                
                if(verifyCode == "") {
                    $("#verify-code").css("border-color","red");
                    $("#code-null").show();
                }

                if(password == "" || $("#c-psw-ph").val == "") {
                    $("#psw-ph").css("border-color","red");
                    $("#c-psw-ph").css("border-color","red");
                    $("#psw-null").show();
                }
            } else {
                if(mobile == "") {
                    $("#email-item").css("border-color","red");
                    $("#em-null").show();
                }

                if(password == "" || $("#c-psw-em").val == "") {
                    $("#psw-em").css("border-color","red");
                    $("#c-psw-em").css("border-color","red");
                    $("#em-psw-null").show();
                }
            }
        }

        if($("#c-psw-ph").val() != password && regType == 0) {
            $("#psw-ph").css("border-color","red");
            $("#c-psw-ph").css("border-color","red");
            $("#psw-difference").show();
            state = false;
        }

        if(!pattern.test(mobile) && regType == 0 && mobile != "") {
            $("#phone-item").css("border-color","red");
            $("#phone-pattern-error").show();
            state = false;
        }

        if($("#c-psw-em").val() != password && regType == 1 ) {
            $("#psw-em").css("border-color","red");
            $("#c-psw-em").css("border-color","red");
            $("#em-psw-difference").show();
            state = false;
        }

        if(!pattern_email.test(mobile) && regType == 1 && mobile != "") {
            $("#email-error").show();
            $("#email-item").css("border-color","red");
            state = false;
        }

        if(state) {
            $.ajax({
                type: "POST",
                async: true,
                dateType: "json",
                url: "/web-user/web-reg.json",
                data: {
                    mobile: mobile,
                    verifyCode: verifyCode,
                    password: password,
                    regType: regType,
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk
                },
                success: function (data) {
                    if (data.code == 0) {
                        alert("注册成功");
                        if(regType == 0) {
                            $("#ph-reg").show();
                            $("#em-reg").hide();
                        } else {
                            $("#ph-reg").hide();
                            $("#em-reg").show();
                        }
                        window.location.href = '/web-user/my-zone.htm';
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }
    });
});