/**
 * Created by weird on 2016/8/10.
 */
$(document).ready(function () {
    //初始化页面模块
    $("#user-center").show();
    $("#training-plan").hide();

    $("#person-center").click(function () {
        $("#user-center").show();
        $("#training-plan").hide();
        $("#person-center").addClass("checked");
        $("#my-training-plan").removeClass("checked");
    });
    $("#my-training-plan").click(function () {
        $("#training-plan").show();
        $("#user-center").hide();
        $("#my-training-plan").addClass("checked");
        $("#person-center").removeClass("checked");
    });
    //个性签名模块
    $("#intro").click(function () {
        if ($("#intro input").length <= 0) {
            var tempValue = "";
            if ($("#intro").html() != "编辑个性签名") {
                tempValue = $("#intro").html();
            }
            $("#intro").html("");
            $("#intro").append("<input type= 'text' id='intro-content' value='" + tempValue + "'/>");

        } else {

        }
        $("input").blur(function () {
            var signature = $("#intro-content").val();
            $.ajax({
                type: "POST",
                async: true,
                dateType: "json",
                url: "/web-user/update-signature.json",
                data: {
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk,
                    signature: signature
                },
                success: function (data) {
                    if (data.code == 0) {
                        var temp = $("#intro-content").val();
                        if (temp == "") {
                            $("#intro").html("编辑个性签名");
                        } else {
                            $("#intro").html(temp);
                        }
                    } else {
                        alert(data.msg);
                    }
                }
            })
        });
    }).keydown(function () {
        if (event.keyCode == 13) {
            var signature = $("#intro-content").val();
            $.ajax({
                type: "POST",
                async: true,
                dateType: "json",
                url: "/web-user/update-signature.json",
                data: {
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk,
                    signature: signature
                },
                success: function (data) {
                    if (data.code == 0) {
                        var temp = $("#intro-content").val();
                        if (temp == "") {
                            $("#intro").html("编辑个性签名");
                        } else {
                            $("#intro").html(temp);
                        }
                        intro_state = true;
                    } else {
                        alert(data.msg);
                    }
                }
            })
        }
    }).hover(function () {
        $(this).addClass("input-border");
    }, function () {
        $(this).removeClass("input-border");
    });

    $("#avatar").hover(function () {
        $(".change-avatar").show();
    }, function () {
        $(".change-avatar").hide();
    });

    $(".change-avatar").click(function () {
        $(".filter").show();
        $(".upload-avatar").show();
    });

    $("#upload-close").click(function () {
        $(".filter").hide();
        $(".upload-avatar").hide();
        $(".upload-form").show();
        $(".cutting-area").hide();
    });

    $("#upload-cancel").click(function () {
        $(".filter").hide();
        $(".upload-avatar").hide();
        $(".upload-form").show();
        $(".cutting-area").hide();
    });


    //动态小头像比例计算
    function preview(img, selection) {
        var scaleX = 100 / selection.width;
        var scaleY = 100 / selection.height;
        //动态小头像 获取当前选中框的宽度，高度，左边框，右边框
        $('#pic + div > img').css({
            width: Math.round(scaleX * img.width) + 'px',
            height: Math.round(scaleY * img.height) + 'px',
            marginLeft: '-' + Math.round(scaleX * selection.x1) + 'px',
            marginTop: '-' + Math.round(scaleY * selection.y1) + 'px'
        });
    }

    $("#upload-file").change(function () {
        $("#avatar-form").ajaxSubmit({
            type: 'post',
            url: '/web-user/upload-preview.json',
            success: function (data) {
                if (data.code == 0) {
                    $(".upload-form").hide();
                    $(".cutting-area").show();
                    $(".cutting-area").append("<img class='cutting-avator' id='pic' src='" + data.avatarUrl + "' />");
                    $("#pic").imgAreaSelect({
                        x1: 0, y1: 0, x2: 100, y2: 100,
                        onInit: function(img, selection) {
                            $('#x').val(selection.x1);
                            $('#y').val(selection.y1);
                            $('#w').val(selection.x2 - selection.x1);
                            $('#h').val(selection.y2 - selection.y1);
                            $('#pic-path').val(data.avatarUrl);

                        },
                        onSelectChange: preview,
                        onSelectEnd: function (img, selection) {
                            $('#x').val(selection.x1);
                            $('#y').val(selection.y1);
                            $('#w').val(selection.x2 - selection.x1);
                            $('#h').val(selection.y2 - selection.y1);
                            $('#pic-path').val(data.avatarUrl);
                            /*console.log('x:'+$('#x').val());
                             console.log('y:'+$('#y').val());
                             console.log('w:'+$("#w").val());
                             console.log('h:'+$('#h').val());
                             console.log('pic:'+$('#pic-path').val());*/
                        }
                    });
                    /* $('#pic').imgAreaSelect({aspectRatio: '1:1', onSelectChange: preview});*/
                    $(".cutting-area").append("<div class='avatar-title'><h4>头像预览</h4></div>");
                    $('<div class="avatar-preview"><img class="les-avatar" src="' + $("#pic")[0].src + '" style="position: relative;" /></div>').css({
                        float: 'left',
                        position: 'relative',
                        overflow: 'hidden',
                        width: '100px',
                        height: '100px',
                    }).insertAfter($('#pic'));
                    $(".cutting-area").append("<div class='cutting-area-btn'><button id='upload-cutting-avator'>保存</button><button>重新上传</button></div>");
                    $("#upload-cutting-avator").click(function () {
                        /*                        console.log($("#x").val());
                         console.log($("#y").val());
                         console.log($("#w").val());
                         console.log($("#h").val());
                         console.log($("#pic-path").val());*/
                        $.ajax({
                            type: "POST",
                            async: true,
                            dateType: "json",
                            url: "/web-user/upload-avatar.json",
                            data: {
                                x: $("#x").val(),
                                y: $("#y").val(),
                                w: $("#w").val(),
                                h: $("#h").val(),
                                picPath: $("#pic-path").val(),
                                scaleX: $("#pic").css("width").split("px")[0],
                                scaleY: $("#pic").css("height").split("px")[0],
                                _lan: _lan,
                                _ch: _ch,
                                _v: _v,
                                _nw: _nw,
                                _sdk: _sdk
                            },
                            success: function (data) {
                                if (data.code == 0) {
                                    alert("上传成功!");
                                    window.location.reload();
                                }
                            }
                        })
                    });
                }
            },
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                console.log(XmlHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    });

    $("#detail-plan").click(function () {
        window.location.href = '/run-plan/present-plan.htm';
    })

    $("#create-plan").click(function () {
        window.location.href = "/run-plan/run-plan-presentation.htm";
    });

    /**个人信息编辑**/

    //个人信息编辑
    $("#info-btn").click(function () {
        var name = $(".userName #name").html();
        var gender = $(".gender #gender").html();
        var age = $(".age #age").html();
        var height = $(".height #height").html();
        var weight = $(".weight #weight").html();

        if ($(".userName #name input").length <= 0) {
            $(".userName #name").html("");
            $(".userName #name").append("<input id='name_inp' type='text' value='" + name + "' onclick='clickName(\"name_inp\")' onkeyup='limitName()' />");
        }

        if ($(".gender #gender input").length <= 0) {
            var str = "";
            $(".gender #gender").html("");
            if (gender == '男') {
                str = "<input type='radio' name='gender' value='1' checked/>男<input type='radio' name='gender' value='2'/>女";
            } else {
                str = "<input type='radio' name='gender' value='1' />男<input type='radio' name='gender' value='2' checked/>女";
            }
            $(".gender #gender").append(str);
        }

        if ($(".age #age input").length <= 0) {
            $(".age #age").html("");
            $(".age #age").append("<input id='age_inp' type='text' value='" + age + "' onclick='clickName(\"age_inp\")'/>");
        }

        if ($(".height #height input").length <= 0) {
            $(".height #height").html("");
            $(".height #height").append("<input id='height_inp' type='text' value='" + height + "' onclick='clickName(\"height_inp\")'>");
        }

        if ($(".weight #weight input").length <= 0) {
            $(".weight #weight").html("");
            $(".weight #weight").append("<input id='weight_inp' type='text' value='" + weight + "' onclick='clickName(\"weight_inp\")'>");
        }

        if ($("#info-btn").html() == '编辑') {
            $("#info-btn").hide();
            $("#info-save-btn").show();
        }
    });
    $("#info-save-btn").click(function () {
        var obj = $("#gender input");
        var gender;
        var state = true;
        for (var i = 0; i < obj.length; i++) {
            if (obj[i].checked) {
                gender = (i == 0) ? 1 : 2;
            }
        }

        if ($("#name_inp").val() == "" || $("#name_inp").val() == null) {
            $("#name_inp").css("border-color", "red");
            state = false;
        }

        if ($("#age_inp").val() == "" || $("#age_inp").val() == null) {
            $("#age_inp").css("border-color", "red");
            state = false;
        }

        if ($("#height_inp").val() == "" || $("#height_inp").val() == null) {
            $("#height_inp").css("border-color", "red");
            state = false;
        }

        if ($("#weight_inp").val() == "" || $("#weight_inp").val() == null) {
            $("#weight_inp").css("border-color", "red");
            state = false;
        }

        if (state) {
            $.ajax({
                type: "POST",
                async: true,
                dateType: "json",
                url: "/web-user/web-info-modify.json",
                data: {
                    name: $("#name_inp").val(),
                    gender: gender,
                    age: $("#age_inp").val(),
                    height: $("#height_inp").val(),
                    weight: $("#weight_inp").val(),
                    type: 1,
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk
                },
                success: function (data) {
                    if (data.code == 0) {
                        alert("修改成功");
                        window.location.reload();
                    } else {
                        alert(data.msg);
                    }
                }
            });
        } else {
            alert("请完善信息!");
        }
    });

    //todo 整合方法to weird
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
        var state = true;
        var pattern = /^1[34578]\d{9}$/;
        if (mobile == "") {
            $("#phone-null").show();
            $("#phone-item").css("border-color", "red");
            state = false;
        } else if (!pattern.test(mobile)) {
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

    //手机绑定
    $("#bind-ph-btn").click(function () {
        $(".bind-account").css("display", "block");
        $(".ub-account").css("display", "none");
        $(".bind-title h4").html("手机绑定");
        $("#bind-form h4").html("请绑定手机");
        $("#ph-bind-content").show();
        $("#em-bind-content").hide();
        $("#bindType").val(0);
    });

    //邮箱绑定
    $("#bind-em-btn").click(function () {
        $(".bind-account").css("display", "block");
        $(".ub-account").css("display", "none");
        $(".bind-title h4").html("邮箱绑定");
        $("#bind-form h4").html("请绑定邮箱");
        $("#ph-bind-content").hide();
        $("#em-bind-content").show();
        $("#bindType").val(1);
    });

    //绑定wechat
    $("#bind-wx-btn").click(function(){
        var iTop = (window.screen.availHeight-550)/2;
        var iLeft = (window.screen.availWidth-640)/2;
        var feature = "width=615,height=505,top="+iTop+",left="+iLeft+",menubar=no,toolbar=no,location=no,scrollbars=no,status=no,modal=yes";
        window.open("/web-user/wx-connect-bind.htm","微信绑定",feature);
    });

    //手机解绑
    $("#unbunding-ph-btn").click(function () {
        if ($("#unbunding-em-btn").html() == "" || $("#unbunding-em-btn").html() == null) {
            alert("不可以解绑");
        } else {
            $(".bind-account").css("display", "none");
            $(".ub-account").css("display", "block");
            $("#ph-ub-content").show();
            $("#em-ub-content").hide();
            $("#wx-ub-content").hide();
            $("#wb-ub-content").hide();
            $("#qq-ub-content").hide();
            $(".ub-account .bind-title h4").html("手机解绑");
            $("#ubType").val(5);
        }
    });

    //邮箱解绑
    $("#unbunding-em-btn").click(function () {
        if ($("#unbunding-ph-btn").html() == "" || $("#unbunding-ph-btn").html() == null) {
            alert("不可以解绑");
        } else {
            $(".bind-account").hide();
            $(".ub-account").show();
            $("#ph-ub-content").hide();
            $("#em-ub-content").show();
            $("#wx-ub-content").hide();
            $("#wb-ub-content").hide();
            $("#qq-ub-content").hide();
            $(".ub-account .bind-title h4").html("邮箱解绑");
            $("#ubType").val(1);
        }
    });

    //微信解绑
    $("#unbunding-wx-btn").click(function(){
        if($("#unbunding-ph-btn").html() == "" || $("#unbunding-em-btn").html() == null) {
            alert("不可以解绑");
        } else {
            $(".bind-account").hide();
            $(".ub-account").show();
            $("#ph-ub-content").hide();
            $("#em-ub-content").hide();
            $("#wx-ub-content").show();
            $("#wb-ub-content").hide();
            $("#qq-ub-content").hide();
            $(".ub-account .bind-title h4").html("微信解绑");
            $("#ubType").val(3);
        }
    });

    //解绑界面关闭
    $("#ub-close").click(function () {
        $(".ub-account").hide();
    });

    //绑定界面提交绑定内容
    $("#bind-btn").click(function () {
        var mobile;
        var verifyCode;
        var type;
        var state = true;
        var pattern = /^1[34578]\d{9}$/;
        var pattern_email = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;

        if ($("#bindType").val() == 0) {
            mobile = $("#phone-item").val();
            verifyCode = $("#verify-code").val();
            type = 5;
        } else {
            mobile = $("#em-item").val();
            verifyCode = -1;
            type = 1;
        }
        if (type == 1) {
            if (mobile == "") {
                $("#em-null").show();
                $("#em-item").css("border-color", "red");
                state = false;
            } else if (!pattern_email.test(mobile)) {
                $("#em-error").show();
                $("#em-item").css("border-color", "red");
                state = false;
            }
        } else if (type == 5) {
            if (mobile == "") {
                $("#phone-null").show();
                $("#phone-item").css("border-color", "red");
                state = false;
            } else if (!pattern.test(mobile)) {
                $("#phone-pattern-error").show();
                $("#phone-item").css("border-color", "red");
                state = false;
            }
            if (verifyCode == "") {
                $("#code-null").show();
                $("#verify-code").css("border-color", "#8e8e8e");
                state = false;
            }
        }


        if (state) {
            $.ajax({
                type: "POST",
                async: true,
                dateType: "json",
                url: "/web-user/binding.json",
                data: {
                    bindingContent: mobile,
                    verifyCode: verifyCode,
                    type: type,
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk
                },
                success: function (data) {
                    console.log(data);
                    if (data.code == 0) {
                        alert("绑定成功");
                        window.location.reload();
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }

    });

    //手机解绑页面的验证码生成
    $("#ub-get-code-btn").click(function () {
        //限制按钮并倒计时
        util.hsTime("#ub-get-code-btn");
        $.ajax({
            type: "POST",
            async: true,
            dateType: "json",
            url: "/web-user/user-verify-code.json",
            data: {
                _lan: _lan,
                _ch: _ch,
                _v: _v,
                _nw: _nw,
                _sdk: _sdk
            },
            success: function (data) {
                if (data.code == 0) {
                    alert("验证码已发出");
                }
            },
            error: function () {
                alert("异常");
            }
        })
    });

    //解绑
    $("#ub-btn").click(function () {
        var verifyCode;
        var type;
        var state = true;
        if ($("#ubType").val() == 0) {
            verifyCode = $("#verify-code-ub").val();
            type = 5;
        } else {
            verifyCode = -1;
            type = $("#ubType").val();
        }

        if (verifyCode == '') {
            $("#ub-code-null").show();
            $("#verify-code-ub").css("border-color", "red");
            state = false;
        }

        if (state) {
            $.ajax({
                type: "POST",
                async: true,
                dateType: "json",
                url: "/web-user/unbinding.json",
                data: {
                    verifyCode: verifyCode,
                    type: type,
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk
                },
                success: function (data) {
                    if (data.code == 0) {
                        alert("解绑成功!");
                        window.location.reload();
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }

    });

    $("#phone-item").click(function () {
        $("#phone-item").css("border-color", "#8e8e8e");
        $("#phone-pattern-error").hide();
        $("#phone-null").hide();
    });

    $("#verify-code").click(function () {
        $("#verify-code").css("border-color", "#8e8e8e");
        $("#code-null").hide();
    });

    $("#em-item").click(function () {
        $("#em-item").css("border-color", "#8e8e8e");
        $("#em-null").hide();
        $("#emial-error").hide();
    });

    $("#verify-code-ub").click(function () {
        $("#verify-code-ub").css("border-color", "#8e8e8e");
        $("#ub-code-null").hide();
    });

    $("#bind-close").click(function () {
        $(".bind-account").hide();
    });

    $("#mdf-bar-btn").click(function () {
        $(".modify-psw").show();
    });

    $("#mdf-close").click(function () {
        $(".modify-psw").hide();
    });

    $("#mdf-cancel-btn").click(function () {
        $(".modify-psw").hide();
    });

    $("#mdf-btn").click(function () {
        //todo 补校验
        var oldpsw = $(".oldpsw").val();
        var newpsw = $(".newpsw").val();
        $.ajax({
            type: "POST",
            async: true,
            dataType: "json",
            url: "/web-user/modify-password.json",
            data: {
                oldPwd: oldpsw,
                nowPwd: newpsw,
                _lan: _lan,
                _ch: _ch,
                _v: _v,
                _nw: _nw,
                _sdk: _sdk
            },
            success: function (data) {
                if (data.code == 0) {
                    alert("修改成功");
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        });
    });
});

function clickName(id) {
    document.getElementById(id).style.borderColor = "#8e8e8e";
}

function limitName() {
    var t = $("#name_inp").val();
    if (t.length > 20) {
        /**全部消失**/
        /*t = t.replace(/^.{20,}$/, '');*/
        t = t.substring(0, 20);
        alert("昵称过长，请控制在20字符以内");
        $("#name_inp").val(t);
    }
}
