/**
 * Created by weird on 2016/8/11.
 */
$(document).ready(function () {

    function toParamMap(str) {
        var map = {};
        var segs = str.split("&");
        for (var i in segs) {
            var seg = segs[i];
            var idx = seg.indexOf('=');
            if (idx < 0) {
                continue;
            }
            var name = seg.substring(0, idx);
            var value = seg.substring(idx + 1);
            map[name] = value;
        }
        return map;
    }

    $("#drag").drag();
    $("#login-btn").click(function () {
        var email = $("#login-account").val();
        var password = $("#psw").val();
        var redirectUrl;

        if (window.location.search.length == 0) {
            redirectUrl == "";
        } else {
            var temp = window.location.search.substring(1);
            redirectUrl = toParamMap(temp).callback;
        }


        if ($("#drag").css("display") == "none") {
            if (email != "" && password != "") {
                $.ajax({
                    type: "POST",
                    async: true,
                    dateType: "json",
                    url: "/user/login.json",
                    data: {
                        email: email,
                        password: password,
                        redirectUrl: redirectUrl
                    },
                    success: function (data) {
                        if (data.code == 0) {
                            window.location.href = data.redirectUrl;
                        } else{
                            $(".error-msg").html(data.msg);
                            $(".error-msg").show();
                            $("input").css("border-color", "red");
                        }
                    },
                    error: function () {
                        alert("异常");
                    }
                })
            } else {
                $(".error-msg").html("请输入账号");
                $(".error-msg").show();
            }
        } else {
            if ($(".drag_text").html() != "验证通过") {
                alert("请拖动滑块验证");
            } else {
                if (email != "" && password != "") {
                    $.ajax({
                        type: "POST",
                        async: true,
                        dateType: "json",
                        url: "/web-user/web-login.json",
                        data: {
                            email: email,
                            password: password,
                            _lan: _lan,
                            _ch: _ch,
                            _v: _v,
                            _nw: _nw,
                            _sdk: _sdk
                        },
                        success: function (data) {
                            console.log(data);
                            if (data.code == 0) {
                                window.location.href = "/web-user/my-zone.htm";
                            } else if (data.code == 2000) {
                                $(".error-msg").html(data.msg);
                                $(".error-msg").show();
                                $("input").css("border-color", "red");
                            } else if (data.code == 2001) {
                                $(".error-msg").html(data.msg);
                                $(".error-msg").show();
                                $("input").css("border-color", "red");
                                if (!data.mult_login) {
                                    $("#drag").css("display", "block");
                                }
                            } else if (data.code == 2002) {
                                $(".error-msg").html(data.msg);
                                $(".error-msg").show();
                                $("input").css("border-color", "red");
                            }
                        },
                        error: function () {
                            alert("异常");
                        }
                    })
                } else {
                    $(".error-msg").html("请输入账号");
                    $(".error-msg").show();
                }
            }
        }

    });

    $("input").click(function () {
        $(".error-msg").hide();
        $("input").css("border-color", "#8e8e8e");
    });

    $(".other-way-wechat").click(function(){
        var iTop = (window.screen.availHeight-550)/2;
        var iLeft = (window.screen.availWidth-640)/2;
        var feature = "width=615,height=505,top="+iTop+",left="+iLeft+",menubar=no,toolbar=no,location=no,scrollbars=no,status=no,modal=yes";
        
        window.open("/web-user/web-connect-wx.htm","微信登录",feature);

    });


});