var Admin = {
    verifyCode: {
        wait:5,
    },
    init: function() {
        //头像上传
        $(".upload").click(function () {
            $('input[type=file]').click();
        });
        $('input[type=file]').on("change", function () {
            $('#loading').showLoading();
            var fd = new FormData();
            var file = $(this)[0].files[0]
            fd.append('file', file);
            var oReq = new XMLHttpRequest();
            oReq.open("POST", "/ugc/admin/upload/avatar", true);
            oReq.onload = function (oEvent) {
                $('#loading').hideLoading();
                debugger
                if (oReq.status == 200) {
                    var url = oReq.body;
                    var result = eval('(' + oReq.responseText + ')');
                    if (result.code === 0) {
                        $('#avatar').attr('src', result.data.url);
                        $('input[name=avatar]').val(result.data.path);
                    } else {
                        bootbox.alert('上传图片失败');
                    }
                } else {
                    bootbox.alert('上传图片失败');
                }
            };
            oReq.send(fd);
        });

        // 校验名称
        $("input[name=name]").on("change", function () {
            if ($(this).val() == '') {
                $('.warning[for=name]').html('主播名不能为空');
            } else if ($(this).val().length > 10) {
                $('.warning[for=name]').html('主播名过长');
            } else if( !VerificationUitl.checkName( $(this).val() ) ) {
                $('.warning[for=name]').html('主播名含非法字符');
            }
        });
        // 校验邮箱
        $("input[name=email]").on("change", function () {
            if ($(this).val() == '') {
                $('.warning[for=email]').html('邮箱不能为空');
            } else if( !VerificationUitl.checkEmail( $(this).val() ) ) {
                $('.warning[for=email]').html('邮箱格式不正确');
            } else {
                $('.warning[for=email]').html('');
                $.ajax({
                    url: "/ugc/admin/check?type=email&key="+$(this).val(),
                    type: "get",
                    dataType: "json",
                    success: function(result) {
                        if(result.code === -1) {
                            $('.warning[for=email]').html('该邮箱已被使用, 若已拥有主播帐号请先<a href="/">登录</a>');
                        } else {
                            $('.warning[for=email]').html('');
                        }
                    }
                });
            }
        });
        // 校验手机
        $("input[name=mobile]").on("change", function () {
            if ($(this).val() == '') {
                $('.warning[for=mobile]').html('手机号码不能为空');
            } else if( !VerificationUitl.checkMobile( $(this).val() ) ) {
                $('.warning[for=mobile]').html('手机格式不正确');
            } else {
                $('.warning[for=mobile]').html('');
                $.ajax({
                    url: "/ugc/admin/check?type=mobile&key="+$(this).val(),
                    type: "get",
                    dataType: "json",
                    success: function(result) {
                        if(result.code === -1) {
                            $('.warning[for=mobile]').html('该手机号已被使用');
                        } else {
                            $('.warning[for=mobile]').html('');
                        }
                    }
                });
            }
        });
        // 密码
        $('input[type=password]').on('change', function() {
            var text1 = $('#password1').val();
            var text2 = $('#password2').val();
            if (text1 != text2) {
                $('.warning[for=password]').html('密码不一致')
            } else if (text1.length < 6) {
                $('.warning[for=password]').html('密码长度低于6位')
            } else {
                $('.warning[for=password]').html('')
            }
        });

        //提交
        $("#submit").click(function() {
            //头像
            var avatar = $('input[name=avatar]').val();
            //名称
            var name = $('input[name=name]').val();
            //邮箱
            var email = $('input[name=email]').val();
            //手机
            var mobile = $('input[name=mobile]').val();
            //验证码
            var verifyCode = $('input[name=verifyCode]').val();
            //密码
            var password1 = $('input[name=password1]').val();
            var password2 = $('input[name=password2]').val();

            //校验名称
            if (name == '') {
                bootbox.alert('主播名不能为空');
                return false;
            } else if (name.length > 10) {
                bootbox.alert('主播名过长');
                return false;
            } else if( !VerificationUitl.checkName( name ) ) {
                bootbox.alert('主播名含非法字符');
                return false;
            }
            //校验邮箱
            if (email == '') {
                bootbox.alert('邮箱不能为空');
                return false;
            } else if( !VerificationUitl.checkEmail( email ) ) {
                bootbox.alert('邮箱格式不正确');
                return false;
            } else {
                $.ajax({
                    url: "/ugc/admin/check?type=email&key="+email,
                    type: "get",
                    async: false,
                    dataType: "json",
                    success: function(result) {
                        if(result.code === -1) {
                            bootbox.alert('该邮箱已被使用');
                            return false;
                        }
                    }
                });
            }
            //校验手机
            if (mobile == '') {
                bootbox.alert('手机号码不能为空');
            } else if( !VerificationUitl.checkMobile( mobile ) ) {
                bootbox.alert('手机格式不正确');
            } else {
                var flag = false;
                $.ajax({
                    url: "/ugc/admin/check?type=mobile&key="+mobile,
                    type: "get",
                    async: false,
                    dataType: "json",
                    success: function(result) {
                        if(result.code === -1) {
                            bootbox.alert('该手机号已被使用');
                            flag = true;
                        }
                    }
                });
                if(flag){
                    return false;
                }
            }
            //校验验证码
            if(verifyCode.trim() == '') {
                bootbox.alert("请输入验证码");
                return false;
            }
            //校验密码
            if (password1 != password2) {
                bootbox.alert('密码不一致');
                return false;
            } else if (password1.length < 6) {
                bootbox.alert('密码长度低于6位');
                return false;
            }
            //阅读协议
            if($("input[name=acceptDeclaration]:checked").length == 0) {
                bootbox.alert('请先阅读播客协议');
                return false;
            }

            if($("input[name=avatar]").val() === ''){
                bootbox.alert('请上传头像');
                return false;
            }

            //注册
            $.ajax({
                url: "/ugc/admin/register",
                type: "post",
                dataType: "json",
                data:{
                    avatar: avatar,
                    name: name,
                    email: email,
                    mobile: mobile,
                    verifyCode: verifyCode,
                    password1: password1,
                    password2: password2
                },
                success: function (result) {
                    if(result.code == 0) {
                        window.location.href = "/ugc/data/" + result.data + "/type/7";
                    } else {
                        bootbox.alert(result.message);
                    }
                }
            });

        });

    },
    forget: {
        init: function() {
            $("#next2").on("click", function(){
                Admin.forget.next2();
            });
            $("#verifyCode").on("click", function() {
                Admin.forget.getVerifyCode();
            });
            $("#next3").on("click", function() {
                var email = $("input[name=email]").val();
                var verifyCode = $("input[name=verifyCode]").val();
                var password = $("input[name=password]").val();
                if(verifyCode.trim() == "") {
                    bootbox.alert("请输入验证码");
                    return false;
                }
                if(password.trim() == "") {
                    bootbox.alert("请输入密码");
                    return false;
                } else if(password.length < 6) {
                    bootbox.alert("密码长度不能小于6");
                    return false;
                }

                $.ajax({
                    url: "/ugc/admin/reset",
                    type: "post",
                    data: {
                        email: email,
                        verifyCode: verifyCode,
                        password: password
                    },
                    dataType: "json",
                    success: function(result) {
                        if(result.code == 0) {
                            bootbox.dialog({
                                message:"修改成功",
                                buttons: {
                                    upload: {
                                        label: "去登录",
                                        callback: function() {
                                            window.location.href = "/ugc/admin/login";
                                        }
                                    }
                                },
                                onEscape: function() {
                                    window.location.href = "/ugc/admin/login";
                                }
                            });
                        } else {
                            bootbox.alert(result.message);
                        }
                    }

                });

            });
        },
        next2: function() {
            var email = $("input[name=email]").val();
            if(email.trim() == "") {
                bootbox.alert("请输入邮箱");
                return false;
            } else if ( !VerificationUitl.checkEmail(email) ) {
                bootbox.alert("邮箱格式不正确");
                return false;
            }
            $.ajax({
                url: "/ugc/admin/check",
                type: "get",
                data: {
                    type: "forget",
                    key: email
                },
                dataType: "json",
                success: function(result) {
                    if(result.code == 0) {
                        $("#step1").hide();
                        $("#email").text(result.data.email);
                        $("#mobile").text("手机号:"+result.data.mobile);
                        $("#step2").show();
                    } else {
                        bootbox.alert(result.message);
                    }
                }
            });
        },
        getVerifyCode: function() {
            var email = $("input[name=email]").val();
            Admin.verifyCode.wait = 60;
            $.ajax({
                url: "/ugc/admin/verifyCode",
                type: "post",
                dataType: "json",
                data: {
                    mobile: "",
                    email: email
                },
                success: function(result) {
                    if(result.code == 0) {
                        Admin.verifyCode.timechange();
                        bootbox.alert('验证码已发送！');
                    } else {
                        bootbox.alert('验证码发送失败，请稍后再试。');
                    }
                },
                error: function() {
                    bootbox.alert('验证码发送失败，请稍后再试。');
                }
            });
        }
    },
    verifyCode: {
        timechange: function() {
            if (Admin.verifyCode.wait == 0) {
                $('#verifyCode').attr('disabled', false);
                $('#verifyCode').html('获取验证码');
                Admin.verifyCode.wait = 60;
            } else {
                $('#verifyCode').attr('disabled', true);
                $('#verifyCode').html(Admin.verifyCode.wait + "秒后重新获取");
                Admin.verifyCode.wait--;
                setTimeout(function () {
                    Admin.verifyCode.timechange();
                }, 1000)
            }
        },
        getVerifyCode: function() {
            var mobile = $("input[name=mobile]").val();
            if (mobile.trim() == '') {
                bootbox.alert('手机号码不能为空');
            } else if( !VerificationUitl.checkMobile( mobile ) ) {
                bootbox.alert('手机格式不正确');
            } else {
                Admin.verifyCode.wait = 60;
                $.ajax({
                    url: "/ugc/admin/verifyCode",
                    type: "post",
                    dataType: "json",
                    data: {
                        mobile:mobile
                    },
                    success: function(result) {
                        if(result.code == 0) {
                            Admin.verifyCode.timechange();
                            bootbox.alert('验证码已发送！');
                        } else {
                            bootbox.alert('验证码发送失败，请稍后再试。');
                        }
                    },
                    error: function() {
                        bootbox.alert('验证码发送失败，请稍后再试。');
                    }
                });

            }
        }
    }
};


