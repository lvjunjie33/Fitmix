/**
 * Created by weird on 2016/8/9.
 */
$(document).ready(function () {
    //获取验证按钮倒计时
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

    //切换忘记密码类型
    $("#forget-type-phone").click(function () {
        $("#forget-type-email").css("background-color", "transparent");
        $("#forget-type-phone").css("background-color", "#0cb4e8");
        $(".forget-phone").show();
        $(".forget-email").hide();
    });
    $("#forget-type-email").click(function () {
        $("#forget-type-phone").css("background-color", "transparent");
        $("#forget-type-email").css("background-color", "#0cb4e8");
        $(".forget-email").show();
        $(".forget-phone").hide();
    });

    //点击输入手机号码框时显示获取验证码内容
    $("#phone-item").click(function () {
        $(".wap-resend-label").show();
    });

    //发送验证码
    $("#get-code-btn").click(function () {
        var mobile = $("#phone-item").val();
        var pattern = /^1[34578]\d{9}$/;
        var state = true;
        if (mobile == "") {
            $("#phone-null").show();
            $("#phone-item").css("border-color", "red");
            state = false
        }
        if (!pattern.test(mobile)) {
            $("#phone-item").css("border-color", "red");
            $("#phone-pattern-error").show();
            state = false;
        }
        if (state) {
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

    $("#phone-item").click(function () {
        $("#phone-null").hide();
        $("#phone-item").css("border-color", "#8e8e8e");
        $("#phone-pattern-error").hide();
    });

    $("#verify-code").click(function () {
        $("#verify-code").css("border-color", "#8e8e8e");
        $("#code-null").hide();
    });

    $("#psw-ph").click(function () {
        $("#psw-ph").css("border-color", "#8e8e8e");
        $("#c-psw-ph").css("border-color", "#8e8e8e");
        $("#psw-null").hide();
        $("#psw-difference").hide();
    });

    $("#c-psw-ph").click(function () {
        $("#psw-ph").css("border-color", "#8e8e8e");
        $("#c-psw-ph").css("border-color", "#8e8e8e");
        $("#psw-null").hide();
        $("#psw-difference").hide();
    });

    $("#email").click(function () {
        $("#email-null").hide();
        $("#email-error").hide();
        $("#email").css("border-color", "#8e8e8e");
    });

    //找回密码(phone)
    $("#find-psw-btn-ph").click(function () {
        var mobile = $("#phone-item").val();
        var verifyCode = $(".code-item").val();
        var password = $("#psw-ph").val();
        var pattern = /^1[34578]\d{9}$/;
        var state = true;

        if (mobile == "" || verifyCode == "" || password == "") {
            state = false;
            alert("请输入信息");
            if (mobile == "") {
                $("#phone-item").css("border-color", "red");
                $("#phone-null").show();
            }

            if (verifyCode == "") {
                $("#verify-code").css("border-color", "red");
                $("#code-null").show();
            }

            if (password == "" || $("#c-psw-ph").val == "") {
                $("#psw-ph").css("border-color", "red");
                $("#c-psw-ph").css("border-color", "red");
                $("#psw-null").show();
            }

        }
        if ($("#c-psw-ph") != password) {
            $("#psw-ph").css("border-color", "red");
            $("#c-psw-ph").css("border-color", "red");
            $("#psw-difference").show();
            state = false;
        }

        if (!pattern.test(mobile)) {
            $("#phone-item").css("border-color", "red");
            $("#phone-pattern-error").show();
            state = false;
        }

        if (state) {
            $.ajax({
                type: "POST",
                async: true,
                dateType: "json",
                url: "/user/mobile-reset-password.json",
                data: {
                    mobile: mobile,
                    verifyCode: verifyCode,
                    password: password,
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk
                },
                success: function (data) {
                    if (data.code == 0) {
                        alert("密码修改成功!");
                    } else {
                        alert(code.msg);
                    }
                }
            })
        }

    });

    //找回密码(email)
    $("#find-psw-btn-email").click(function () {
        var email = $("#email").val();
        var state = true;
        var pattern_email = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;


        if (email == "") {
            $("#email").css("border-color", "red");
            $("#email-null").show();
            state = false;
        } else if (!pattern_email.test(email)) {
            $("#email").css("border-color", "red");
            $("#email-error").show();
            state = false;
        }


        if (state) {
            $.ajax({
                type: "POST",
                async: true,
                dateType: "json",
                url: "/user/email-recovery-password.json",
                data: {
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk,
                    email: email
                },
                success: function (data) {
                    if (data.code == 0) {
                        alert("邮件已发送");
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }

    });

});