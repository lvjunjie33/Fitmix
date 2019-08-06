/**
 * Created by fenxio on 2016/8/25.
 */
var User = {
    infoInit: function() {
        $("select[name='birthday[year]']").find("option").each(function() {
            if($(this).val() == year) {
                $(this).attr("selected", "selected");
            }
        });
        $("select[name='birthday[month]']").find("option").each(function() {
            if($(this).val() == month) {
                $(this).attr("selected", "selected");
            }
        });
        $("select[name='birthday[day]']").find("option").each(function() {
            if($(this).val() == day) {
                $(this).attr("selected", "selected");
            }
        });

        $("#jcrop-avatar").modal({
            keyboard: false,
            backdrop: 'static',
            show: false
        });
        //基础信息
        $("#base-info-submit").on("click", function(){
            User.updateBaseInfo();
        });
        //显示修改密码框
        $("#show-update-password").on("click", function(){
            User.showUpdatePasswordForm();
        });
        //更新密码
        $("#update-password-submit").on("click", function() {
            User.updatePassword();
        });
        //上传头像
        $("#upload-avatar").on("click", function() {
            $("input[type=file]").click();
        });
        $("input[type=file]").on("change", function() {
            var objUrl = User.getObjectURL($(this).get(0).files[0]);
            if (objUrl) {
                $("#avatar").attr("src", objUrl);
            }
            $('#avatar').cropper('replace', objUrl);
            User.toggleJcropBox();
        });
        //初始化插件
        $('#avatar').cropper({
            scalable: false,
            aspectRatio: 1/1,
            viewMode: 1,
            zoomable: false,
            background: false
        });
        //上传头像
        $("#avatar-submit").on("click", function(){
            User.toggleJcropBox();
            $("#loading").showLoading();
            var result= $('#avatar').cropper("getCroppedCanvas");
            var img = result.toDataURL();
            var imgName = $("input[type=file]").get(0).files[0].name;
            $.ajax({
                url: "/ugc/user/"+uid,
                type: "PUT",
                dataType: "json",
                enctype: "multipart/form-data",
                data: {
                    avatarImg: img,
                    avatarImgName: imgName
                },
                success: function(result) {
                    $("#loading").hideLoading();
                    if(result.code == 0) {
                        bootbox.alert("修改成功");
                        $("#current-avatar").attr("src", BASE_URI+"/"+result.data.avatar);
                    } else {
                        bootbox.alert(result.message);
                    }
                }
            });
        });
    },
    identityInit: function() {
        $("#idCardUpload").on("click", function(){
            $("#idCardFile").click();
        });
        $("#radioUpload").on("click", function(){
            $("#radioUploadFile").click();
        });
        $("#followerUpload").on("click", function(){
            $("#followerFile").click();
        });
        //修改手机号
        $("#modify-mobile").on("click", function() {
            var html = "<label>手机号码</label>";
            html += '<input class="form-control" value="'+$(this).data("mobile")+'" name="mobile" type="text" style="width: 150px;"/>';
            html += '<input class="form-control" value="" name="verifyCode" type="text" style="margin-top: 10px;width: 150px;display: inline-block;"/>';
            html += '<button type="button" style="display: inline-block;width:120px;margin-left: 10px;" id="verifyCode" onclick="Admin.verifyCode.getVerifyCode()" class="btn btn-base-default" >获取验证码</button>'
            $(this).parent(".form-group").html(html, 100);
        });
        //提交身份审核信息
        $("#identity-submit").on("click", function(){
            User.updateIdentityInfo();
        });
    },
    updateBaseInfo: function() {
        // 校验主播名称
        var name = $("input[name=name]").val();
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
        //$.ajax({
        //    url: "/ugc/user/"+uid,
        //    type: "POST",
        //    dataType: "json",
        //    data: $("#base-info-form").serialize(),
        //    success: function(result) {
        //        if(result.code == 0) {
        //            bootbox.alert("修改成功");
        //        } else {
        //            bootbox.alert(result.message);
        //        }
        //    }
        //});

        $('#base-info-form').ajaxSubmit({
            type: "POST",
            url: "/ugc/user/"+uid,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function(result) {
                if(result.code == 0) {
                    bootbox.alert("修改成功");
                } else {
                    bootbox.alert(result.message);
                }
            }
        });

    },
    showUpdatePasswordForm: function() {
        $("#show-update-password").hide();
        $("#password-form").show(200);
    },
    updatePassword: function() {

        var password = $("input[name=password]").val();
        var password1 = $("input[name=password1]").val();
        var password2 = $("input[name=password2]").val();

        if(password.length < 6) {
            bootbox.alert('密码长度低于6位');
            return false;
        }
        if (password1 != password2) {
            bootbox.alert('密码不一致');
            return false;
        } else if (password1.length < 6) {
            bootbox.alert('密码长度低于6位');
            return false;
        }

        $.ajax({
            url: "/ugc/user/"+uid+"/password",
            type: "PATCH",
            dataType: "json",
            data: $("#password-form").serialize(),
            success: function(result) {
                if(result.code == 0) {
                    bootbox.alert("修改成功");
                } else {
                    bootbox.alert(result.message);
                }
            }
        });
    },
    toggleJcropBox: function() {
        $("#jcrop-avatar").modal("toggle");
    },
    updateIdentityInfo: function(){

        var realName = $("input[name='userIdentityInfo.realName']").val();
        var idCard = $("input[name='userIdentityInfo.idCard']").val();
        var idCardImg = $("input[name='userIdentityInfo.idCardImg']").val();
        var idCardFile = $("input[name=idCardFile]").val();
        var mobile = $("input[name=mobile]").val();
        var verifyCode = $('input[name=verifyCode]').val();
        if(typeof(mobile) != "undefined") {
            //校验手机
            if (mobile == '') {
                bootbox.alert('手机号码不能为空');
                return false;
            } else if( !VerificationUitl.checkMobile( mobile ) ) {
                bootbox.alert('手机格式不正确');
                return false;
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
        }

        if(realName.trim() == "") {
            bootbox.alert("请填写姓名");
            return false;
        }
        var validIdCard = VerificationUitl.idCardValid(idCard);
        if(!validIdCard.pass) {
            bootbox.alert(validIdCard.tip);
            return false;
        }
        if(idCardImg == "" && idCardFile == ""){
            bootbox.alert("请上传手持身份证照片");
            return false;
        }
        //$('#identity-form').ajaxForm(function() {
        //    $(this).ajaxSubmit();
        //    alert("111");
        //});
        $('#identity-form').ajaxSubmit({
            type: "POST",
            url: "/ugc/user/"+uid,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function(result) {
                if(result.code == 0) {
                    bootbox.alert("修改成功");
                } else {
                    bootbox.alert(result.message);
                }
            }
        });

        //$.ajax({
        //    url: "/ugc/user/"+uid,
        //    type: "POST",
        //    dataType: "json",
        //    data: $("#identity-form").serialize(),
        //    success: function(result) {
        //        if(result.code == 0) {
        //            bootbox.alert("修改成功");
        //        } else {
        //            bootbox.alert("修改失败");
        //        }
        //    }
        //});
    },
    getObjectURL: function(file) {
        var url = null;
        if (window.createObjectURL != undefined) { // basic
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }
};