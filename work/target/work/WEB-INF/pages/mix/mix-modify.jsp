<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

    body{
        /*min-width: 1600px;*/
    }
    /*#mix-forms .uk-placeholder {padding: 10px;}*/
    .mix-scene{margin-right: 16px;}
    .mix-scene [type=radio],.mix-scene [type=checkbox]{margin-right: 4px;}

</style>

<style type="text/css">
    .progress{
        background: #00a8e6;
        min-height: 1px;
        max-height: 1px;
        width: 0%;
        display: none;
    }

    .progress-bar{
        display: none;
        color: #00a8e6;
    }

</style>

<div class="uk-container-center uk-body">
    <h2>Mix Modify</h2>
    <form action="/mix/mix-modify.json" method="post" class="uk-form" enctype="multipart/form-data" mix-modify-form>
        <div class="<%--uk-container--%>">

            <div class="uk-form-row">
                <div class="uk-grid">
                    <div class="uk-width-1-2" style="display: inline-block">
                        <label>图片：</label>
                        <div class="uk-form-file">
                            <a href="#image-modify-modal" image-modify data-uk-modal>修改图片</a>
                        </div>
                    </div>
                    <div class="uk-width-1-2" style="display: inline-block">
                        <label>文件：</label>
                        <div class="uk-form-file" id="mix-file">
                            <a href="#file-modify-modal" file-modify data-uk-modal>修改Mix</a>
                        </div>
                        <div class="uk-form-row" style="width: 95%">
                            <div class="progress"></div>
                            <div class="progress-bar">0%</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="uk-form-row">
                <label>歌曲：</label>
                <input type="text" name="name" id="name_update_id" value="${mix.name}" class="uk-width-5-10" style="border: none;background:none;width: 47.5%;" notnull>
                <label>语言：</label>
                <select name="mixDetails[0].name_lan" id="name_lan_update_id">
                    <option value="zh">简体中文</option>
                    <option value="en">英文</option>
                </select>
            </div>

            <div class="uk-form-row">
                <label>场景：</label>
                <c:choose>
                    <c:when test="${mix.type == 2}">
                        <lch:dic-option type="9" higValue="${mix.scene}"
                                        label="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"scene\" value=\"%s\">%s</label>"
                                        higLabel="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"scene\" value=\"%s\" checked>%s</label>"/>
                    </c:when>
                    <c:otherwise>
                        <lch:dic-option type="2" higValue="${mix.scene}"
                                        label="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"scene\" value=\"%s\">%s</label>"
                                        higLabel="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"scene\" value=\"%s\" checked>%s</label>"/>
                    </c:otherwise>
                </c:choose>

            </div>

            <lch:mix-genre-parent split="" higValue="${mix.genre}"
                                  label="<label class=\"uk-width-1-6\"><input type=\"checkbox\" name=\"genre\" value=\"%s\">&nbsp;%s</label>"
                                  higLabel="<label class=\"uk-width-1-6\"><input type=\"checkbox\" name=\"genre\" value=\"%s\" checked>&nbsp;%s</label>"/>


            <div class="uk-form-row uk-grid uk-grid-collapse">
                <div>
                    <label>歌手：</label>
                </div>
                <div class="uk-width-9-10 uk-grid uk-grid-collapse">
                    <c:forEach items="${mixAuthorList}" var="mixAuthor">
                        <label class="uk-width-1-6"><input type="radio" name="maid" value="${mixAuthor.id}" <c:if test="${mixAuthor.id eq mix.maid}">checked</c:if> />&nbsp;${mixAuthor.name}</label>
                    </c:forEach>
                </div>
            </div>

            <div class="uk-form-row">
                <label>标识：</label>
                <label><input type="number" name="customIdentification" value="${mix.customIdentification}" class="uk-form-large uk-width-9-10" notnull></label>
            </div>

            <div class="uk-form-row">
                <label>BPM：</label>
                <label><input type="text" name="bpm" value="${mix.bpm}" class="uk-form-large uk-width-9-10" notnull></label>
            </div>

            <div class="uk-form-row">
                <label>时长：</label>
                <label><input type="text" name="notBuildLength" value="<fmt:parseNumber value="${(mix.trackLength - mix.trackLength % 60) / 60}"/>.${mix.trackLength % 60}" class="uk-form-large uk-width-9-10" isnumber></label>
                <input type="hidden" name="trackLength"/>
            </div>

            <div class="uk-form-row">
                <label>介绍：</label>
                <textarea name="introduce" id="introduce_update_id" class="uk-form-large uk-width-9-10" style="min-height: 100px;width: 80%;" notnull /> ${mix.introduce} </textarea>
                <label>语言：</label>
                <select name="mixDetails[0].introduce_lan" id="introduce_lan_update_id">
                    <option value="zh">简体中文</option>
                    <option value="en">英文</option>
                </select>
            </div>

            <%--  隐藏区， 额外格式化信息  --%>
            <div class="uk-form-row">
                <div id="add-pzh">
                    <input type="hidden" name="mixMusicStr"/>
                    <c:forEach var="mixMusics" items="${mix.mixMusics}">
                        <div class="uk-form-row">
                            <label>配置：</label>
                            <input type="text" class="uk-form-large uk-width-4-10" name="mixMusicsName" value="${mixMusics.name}" placeholder="歌曲名称" notnull="">
                            <input type="text" class="uk-form-large uk-width-4-10" name="mixMusicsPosition" value="<fmt:parseNumber value="${(mixMusics.position - mixMusics.position % 60) / 60}"/>.${mixMusics.position % 60}" placeholder="时间" isnumber="">
                            <a href="javascript:" onclick="$(this).parent().remove();" style="color: #ff4d66;">删除</a>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="uk-form-row">
                <button type="button" class="uk-button uk-form-large" style="width: 93%" add-pzh>添加配置</button>
            </div>

            <div class="uk-form-row">
                <input type="hidden" name="id" id="id_update_id" value="${mix.id}"/>
                <button class="uk-button uk-button-primary" target="role-add-form" mix-modify-submit style="width: 93%" >提交</button>
            </div>
        </div>
    </form>
    <br/>
</div>


<%--  image file  --%>
<div id="image-modify-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Mix Image Modify</h1>
        <div class="uk-form-row">
            <form action="/mix/mix-image-modify.json" method="post" class="uk-form" isform>
                <div class="uk-form-row">
                    <label>图片：</label>
                    <div class="uk-form-file">
                        <a href="javascript:" style="cursor: pointer;">选择</a>
                        <input type="file" name="image">
                    </div>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="mid" value="${mix.id}"/>
                    <button class="uk-button uk-button-primary" >modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--  mix file  --%>
<div id="file-modify-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Mix File Modify</h1>
        <div class="uk-form-row">
            <form action="/mix/mix-file-modify.json" method="post" class="uk-form" file-modify-form>
                <div class="uk-form-row">
                    <label>mix：</label>
                    <div class="uk-form-file">
                        <a href="javascript:" style="cursor: pointer;">选择</a>
                        <input type="file" name="file">
                    </div>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="mid" value="${mix.id}"/>
                    <button class="uk-button uk-button-primary" mix-file-commit>modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">

    $("#name_lan_update_id").change(function(){
        var opt=$("#name_lan_update_id").val();
        var ids=$("#id_update_id").val();
        $.ajax({
            type: "GET",
            url: "/mix/mix-modify-ajax.json",
            data: {id:ids,lan:opt},
            dataType: "json",
            success: function(data){
                if(data.mix!=undefined){
                    $("#name_update_id").val(data.mix.name);
                    $("#introduce_update_id").val(data.mix.introduce);
                    $("#introduce_lan_update_id").val(opt);
                }

            }
        });
    });

    $("#introduce_lan_update_id").change(function(){
        var opt=$("#introduce_lan_update_id").val();
        var ids=$("#id_update_id").val();
        $.ajax({
            type: "GET",
            url: "/mix/mix-modify-ajax.json",
            data: {id:ids,lan:opt},
            dataType: "json",
            success: function(data){
                if(data.mix!=undefined) {
                    $("#name_update_id").val(data.mix.name);
                    $("#introduce_update_id").val(data.mix.introduce);
                    $("#name_lan_update_id").val(opt);
                }
            }
        });
    });


    $(function(){
        $("[mix-modify-submit]").click(function(){
            var form = $("[mix-modify-form]");
            validate.trigger(form);
            var errorEl = form.find("." + $.errorAlertClass + ":not(:hidden)");
            if (errorEl.size() > 0) {
                UIkit.notify("验证不通过", {status:'danger', pos:'top-right'});
                return false;
            }
        });
    });

    $(function(){
        $("[add-pzh]").click(function () {
            var htmlPz = '<div class="uk-form-row"><label>配置：</label> <input type="text" class="uk-form-large uk-width-4-10" name="mixMusicsName" placeholder="歌曲名称" notnull>' +
                    ' <input type="text" class="uk-form-large uk-width-4-10" name="mixMusicsPosition" placeholder="时间"  isnumber/>' +
                    ' <a href="javascript:" onclick="$(this).parent().remove();" style="color: #ff4d66;">删除</a></div>';
            var th = $(this);
            $("#add-pzh").append(htmlPz);
            console.info(window.validate());
        });


        $("[mix-modify-form]").submit(function(){
            var form = $(this);
            validate.trigger(form);
            var errorEl = $(this).find("." + $.errorAlertClass + ":not(:hidden)");
            if (errorEl.size() > 0) {
                UIkit.notify(data, {status:'danger', pos:'top-right'});
                return false;
            }
            // 处理歌曲时间， 分处理秒
            var notBuildLength = form.find("[name=notBuildLength]").val().trim();
            var notBuildLengthArray = notBuildLength.split(".");
            if (notBuildLengthArray.length == 2) {
                form.find("[name=trackLength]").val(notBuildLengthArray[0] * 60 + parseInt(notBuildLengthArray[1]));
            }
            else {
                form.find("[name=trackLength]").val(notBuildLengthArray[0] * 60);
            }

            /// 认证bpmStr 格式 （99~1009）
            var bpmStr = $.trim(form.find("[name=bpm]").val());
            if (!(/^(\d+\-\d+|\d+)$/.test(bpmStr))) {
                UIkit.notify("bpm格式不正确 ( 22~23 or 22)", {status:'danger', pos:'top-right'});
                return false;
            }

            // mixMusicStr 处理 mix 配置 子音乐时间， 分处理成秒
            var mixMusics = [];
            form.find("#add-pzh .uk-form-row").each(function(i, posi){
                var th = $(this);
                var mixMusicsName = th.find("[name=mixMusicsName]").val().trim();
                var mixMusicsPosition = th.find("[name=mixMusicsPosition]").val().trim();
                var positionArray = mixMusicsPosition.split(".");
                if (positionArray.length == 2) {
                    mixMusics.push({"name" : $.trim(mixMusicsName), "position" : positionArray[0] * 60 + parseInt(positionArray[1])});
                }
                else {
                    mixMusics.push({"name" : $.trim(mixMusicsName), "position" : positionArray[0] * 60});
                }
            });
            $("[name=mixMusicStr]").val(JSON.stringify(mixMusics));

            form.find("button,input[type=button]").attr("disabled", "true");
            form.ajaxSubmit({
                success: function (d) {
                    var code = d['code'];
                    if (code == 0) {
                        if (form.attr("not-reload") == "") {
                            UIkit.notify("success", {status:'success', pos:'top-right'});
                            form.find("button,input[type=button]").removeAttr("disabled");
                        }
                        else {
                            UIkit.notify("修改成功..", {status:'success', pos:'top-right'});
                            form.find("button,input[type=button]").removeAttr("disabled");
                        }
                    }
                    else {
                        form.find("button,input[type=button]").removeAttr("disabled");
                        UIkit.notify(code + d["msg"], {status:'danger', pos:'top-right'});
                    }
                }
            });
            return false;
        });
    });



    $("[type=file]").change(function(e){
        var audioFile = $(this).val();
        var audioName = audioFile.substring(audioFile.lastIndexOf("\\") + 1);
        if (/^\s*$/.test(audioName)) {
            $(this).parent().find("a").text("请选择..");
        }
        else {
            $(this).parent().find("a").text(audioName);
        }
    });



    $(function(){
//    $(".mix-scene")

        $("[name=bpm]").change(function(){
            var th = $(this);
            var value = $.trim(th.val());
            if (!/\s/.test(value)) {

                value = parseInt(value);
                console.info(typeof value);
                $(".mix-scene").find(":checkbox").removeAttr("checked");
                if (value >= 180) {
                    $(".mix-scene").find("[value=1]").attr("checked", "true");
                }
                else if (value >= 140 && value < 180) {
                    $(".mix-scene").find("[value=2]").attr("checked", "true");
                }
                else if (value >= 120 && value < 140) {
                    $(".mix-scene").find("[value=3]").attr("checked", "true");
                }
                else if (value < 120) {
                    $(".mix-scene").find("[value=4]").attr("checked", "true");
                }
            }
        });
    });


    $(function(){
        $("[mix-file-commit]").click(function(){
            $(".progress-bar,.progress").show(0);
//            var time = window.setInterval(function(){
//                $.get("mix/mix-file-modify-rate.json", {}, function(result){
//                    $(".progress").animate({width: result["rate"] + "%"}, 5000);
//                    $(".progress-bar").html(result["rate"] + "%" +  "&nbsp;&nbsp;&nbsp;&nbsp;总大小:" + (result["size"] / 1024 / 1024) + "MB" + "&nbsp;&nbsp;&nbsp;&nbsp;已上传:" + (result["read"] / 1024 / 1024) + "MB" );
//                    if (result["rate"] == 100 || result["rate"] == 100.00) {
//                        clearInterval(time)
//                    }
//                    $("#mix-file").html("<div>" + result["name"] + "</div>");
//                 });
//            }, 4000);

            var modal = UIkit.modal("#file-modify-modal");
            if ( modal.isActive()) {
                modal.hide();
            } else {
                modal.show();
            }
        });
    });
</script>


<script>


    $("[file-modify-form]").submit(function(){
        var form = $(this);
        var mid = form.find("[name=mid]").val();
        var file = form.find("[name=file]");

        var size =  file[0].files[0].size;
        var start = 0;

        var fileSplitSize = 1024 * 1024;　　//文件分段大小1M
        var partCount = 0;
        var uploadForm = function(){
            var data = new FormData();　　//利用FormData对象模拟表单
            data.append("name", file[0].files[0].name);
            data.append("mid", mid);
            data.append("file", file[0].files[0].slice(start, start + fileSplitSize));　　//核心切割file文件，下面详细解释①
            data.append("part", ++partCount);
            data.append("size", size);
            data.append("partSize", fileSplitSize);

            var rate = Math.round(fileSplitSize * (partCount + 1) / size * 100 > 100 ? 100 : fileSplitSize * (partCount + 1) / size * 100); // 进度 百分百

            var xhr = new XMLHttpRequest();　　//XMLHttpRequest 2.0对象
            xhr.open("post", "/mix/mix-file-modify.json?mid=" + mid, true);
            xhr.send(data);
            xhr.onreadystatechange = function(){
                if(xhr.readyState == 4){
                    if(xhr.status == 200){
                        $(".progress").animate({width: rate + "%"}, 2000);
                        $(".progress-bar").html(rate + "%" +  "&nbsp;&nbsp;&nbsp;&nbsp;总大小:" + Math.round(size / 1024 / 1024) + "MB" + "&nbsp;&nbsp;&nbsp;&nbsp;已上传:" + Math.round(fileSplitSize * (partCount + 1) / 1024 / 1024) + "MB" );
                        $("#mix-file").html("<div>" + file[0].files[0].name + "</div>");

                        var result= xhr.responseText;
                        console.info(result);
                        if(start + fileSplitSize >= size){
                            console.info("success...");
                        }
                        else {
                            console.info("upload success...");
                            start += fileSplitSize;
                            uploadForm();
                        }
                    }
                }
            }
        }
        uploadForm();
        return false;
    });

    function checkAgent() {
        var agent = window.navigator.userAgent;
        var browser = {} || function(){};
        if (agent.indexOf("MSIE")>=1){
            browser.ie = true;
        }else if(agent.indexOf("Firefox")>=1){
            browser.firefox = true;
        }else if(agent.indexOf("Chrome")>=1){
            browser.chrome = true;
        }
        return browser;
    }

    function checkFileSize(maxSize, fileEl) {
        var fileSize = getFileSize(fileEl);
        if (maxSize <= fileSize) {
            return true;
        }
        return false;
    }

    function getFileSize(fileEl) {
        var browser = checkAgent();
        var fileSize = 0;
        if(browser.firefox || browser.chrome ){
            fileSize = fileEl.files[0].size;
        }else if(browser.ie){
//            var obj_img = document.getElementById('tempimg');
//            obj_img.dynsrc=fileEl.value;
//            fileSize = obj_img.fileSize;
            // ie 特殊处理， 需要 创建个 img 或者文件 才能获取
        }
        return fileSize;
    }

</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
