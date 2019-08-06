/**
 * Created by fenxio on 2016/8/29.
 */
var Content = {
    Albums: {
        init: function() {
            //绑定创建专辑按钮
            $(".albums-new").on("click", function () {
                window.location.href = "/ugc/content/"+ uid + "/albums/add";
            });
            //绑定删除
            $(".albums-delete").each(function() {
                var th = $(this);
                th.on("click", function() {
                    var id = $(this).data("id");
                    bootbox.confirm("确定要删除吗？", function(result){
                        if(result) {
                            Content.Albums.delete(id);
                        }
                    })
                });
            });
        },
        delete: function (id) {
            $.ajax({
                url: "/ugc/content/"+uid+"/albums/"+id,
                type: "delete",
                dataType: "json",
                success: function(result) {
                    if(result.code == 0) {
                        bootbox.alert("删除成功");
                        window.location.reload();
                    } else {
                        bootbox.alert(result.message);
                    }
                }
            });
        }
    },
    AlbumsAdd: {
        init: function() {
            Content.initUploadBackImage('-add');
            //添加albums
            $("#albums-submit").on("click", function() {
                Content.AlbumsAdd.albumsSubmit();
            });

        },
        albumsSubmit: function() {
            if($("input[name=backImageFile]").val() == "") {
                bootbox.alert("请上传专辑封面");
                return false;
            }
            if($("input[name=title]").val() == "") {
                bootbox.alert("请填写专辑标题");
                return false;
            }
            if($("#desc").val() == "") {
                bootbox.alert("请填写专辑描述");
                return false;
            }
            $("#albums-submit").attr("disabled", true);
            $.ajax({
                url: "/ugc/content/"+uid+"/albums",
                type: "post",
                dataType: "json",
                data: $("#albums-form").serialize(),
                success: function(result) {
                    if(result.code == 0) {
                        var albumsId = result.data.id;
                        bootbox.dialog({
                            message: "专辑创建成功但还没通过审核~必须完成以下条件<br>1. 通过<a style='color: #00a8e6' href='/ugc/user/"+uid+"/identity'>身份信息</a>审核<br>2. 上传至少一期节目<br>小编将于2个工作日内给予反馈哦",
                            buttons: {
                                list: {
                                    label: '取消',
                                    className: "btn-cancel",
                                    callback: function() {
                                        window.location.href = "/ugc/content/"+uid+"/albums/"+albumsId+"/programs";
                                    }
                                },
                                upload: {
                                    label: '去上传',
                                    className: "btn-upload",
                                    callback: function() {
                                        window.location.href = "/ugc/content/"+uid+"/albums/"+albumsId+"/mix/upload";
                                    }
                                }
                            }
                        });
                    } else {
                        bootbox.alert(result.message);
                        $("#albums-submit").attr("disabled", false);
                    }
                }
            });
        },
    },
    AlbumsUpdate: {
        init: function(){
            Content.initUploadBackImage('-update');
            //修改albums
            $("#albums-submit").on("click", function() {
                Content.AlbumsUpdate.albumsSubmit();
            });
        },
        albumsSubmit: function() {
            if($("input[name=title]").val() == "") {
                bootbox.alert("请填写专辑标题");
                return false;
            }
            if($("#desc").val() == "") {
                bootbox.alert("请填写专辑描述");
                return false;
            }
            $.ajax({
                url: "/ugc/content/"+uid+"/albums/"+albumsId,
                type: "PATCH",
                dataType: "json",
                data: $("#albums-form").serialize(),
                success: function(result) {
                    if(result.code == 0) {
                        bootbox.alert("修改成功", function(){
                            window.location.reload();
                        });
                    } else {
                        bootbox.alert(result.message);
                    }
                }
            });
        }
    },
    Programs: {
        init: function() {
            Content.Programs.getMixPage(1);
            //删除
            $(".table").on("click", ".delete-mix", function() {
                var id = $(this).data("id");
                Content.Programs.deleteMix(id, $(this));
            });
            //修改
            $(".table").on("click", ".edit-mix", function() {
                var id = $(this).data("id");
                var name = $(this).data("name");
                var introduce = $(this).data("introduce");
                $("#edit-mix-form").find("input[name=id]").val(id);
                $("#edit-mix-form").find("input[name=name]").val(name);
                $("#edit-mix-form").find("textarea[name=introduce]").val(introduce);
                Content.Programs.toggleEditModal();
            });
            //submit 提交
            $("#mix-submit").on("click", function() {
                var id = $("#edit-mix-form").find("input[name=id]").val();
                var name = $("#edit-mix-form").find("input[name=name]").val();
                if(name.trim() == "") {
                    bootbox.alert("名称不能为空");
                    return false;
                }
                Content.Programs.toggleEditModal();
                Content.Programs.updateMix(id, $("#edit-mix-form").serialize());
            });
            //加载更多
            $("#load-btn").on("click", function() {
                Content.Programs.getMixPage($(this).data("next"));
            });
        },
        getMixPage: function(pageNo) {
            $.ajax({
                url: "/ugc/content/"+uid+"/albums/"+albumsId+"/mix",
                type: "get",
                dataType: "json",
                data: {
                    pageNo: pageNo
                },
                success: function(result) {
                    if(result.code == 0) {
                        var page = result.data;
                        // 是否显示更多按钮
                        if(page.hasNext) {
                            $("#load-btn").show();
                            $("#load-btn").attr("data-next", page.nextPage);
                        } else {
                            $("#load-btn").hide();
                        }
                        var template = $("#template").html().toString();
                        var html = "";
                        for(var i = 0; i < page.result.length; i++) {
                            html += "<tr>" + template.replace(/#index#/g, ((pageNo-1) * page.size + i + 1) )
                                    .replace(/#name#/g, page.result[i].name)
                                    .replace(/#addTime#/g, Util.parseDate(page.result[i].addTime, 'Y-m-d H:i'))
                                    .replace(/#auditionCount#/g, page.result[i].auditionCount ? page.result[i].auditionCount : 0)
                                    .replace(/#id#/g, page.result[i].id)
                                    .replace(/#introduce#/g, page.result[i].introduce)
                                    +"</tr>";
                        }
                        $(".table tbody").append(html);
                    } else {
                        bootbox.alert(result.message);
                    }
                }
            });
        },
        deleteMix: function(id, th) {
            bootbox.confirm("确定要删除吗？", function(result){
                if(result) {
                    $.ajax({
                        url: "/ugc/content/"+uid+"/albums/"+albumsId+"/mix/"+id,
                        type: "delete",
                        dataType: "json",
                        success: function (result) {
                            if(result.code == 0) {
                                bootbox.alert("删除成功", function() {
                                    th.parents("tr").remove();
                                });
                            } else {
                                bootbox.alert(result.message);
                            }
                        }
                    });
                }
            });
        },
        updateMix: function(id, data) {
            $.ajax({
                url: "/ugc/content/"+uid+"/albums/"+albumsId+"/mix/"+id,
                type: "patch",
                data: data,
                dataType: "json",
                success: function(result) {
                    if(result.code == 0) {
                        bootbox.alert("修改成功");
                        $("#"+id).text(result.data.name);
                    } else {
                        bootbox.alert(result.message);
                    }
                }
            });
        },
        toggleEditModal: function() {
            $("#edit-mix-modal").modal("toggle");
        }
    },
    initUploadBackImage: function(type) {
        //上传背景图片
        $("#upload-back-image").on("click", function() {
            $("input[type=file]").click();
        });
        $("input[type=file]").on("change", function() {
            var objUrl = Util.getObjectURL($(this).get(0).files[0]);
            if (objUrl) {
                $('#avatar'+type).cropper('replace', objUrl);
            }
            Content.toggleJcropBox();
        });
        //初始化插件
        $('#avatar'+type).cropper({
            scalable: false,
            aspectRatio: 1/1,
            viewMode: 1,
            zoomable: false,
            background: false
        });
        //上传头像
        $("#avatar-submit").on("click", function() {
            Content.toggleJcropBox();
            var result= $('#avatar'+type).cropper("getCroppedCanvas");
            var img = result.toDataURL();
            var imgName = $("input[type=file]").get(0).files[0].name;
            $("#back-image").attr("src", img);
            $("input[name=backImageFile]").val(img);
            $("input[name=backImageName]").val(imgName);
        });
    },
    toggleJcropBox: function() {
        $("#jcrop-avatar").modal("toggle");
    }
};