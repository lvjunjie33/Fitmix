<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/28
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<div class="uk-form-row">
    <a href="#home-image-add" class="uk-button" data-uk-modal>添加</a>
</div>

<div class="uk-text-muted">
    注意，版本号没设置背景图时，默认第一张背景图（没有设置背景图时，则app默认背景图）
</div>

<br/>

<c:forEach items="${homeImages}" var="homeImage" varStatus="status">

    <div class="uk-grid">
        <div class="uk-width-5-10">
            <form action="/app/set-home-background-image.json" method="post" class="uk-form" enctype="multipart/form-data" home-image-form>
                <div id="upload-drop${status.index}" class="uk-placeholder">
                    <h2>${homeImage.version}</h2>
                    Info background image... <a class="uk-form-file">Select a file<input id="upload-select${status.index}" type="file" name="file"></a>.
                    <br/>
                    <a href="/app/remove-home-background-image.json?version=${homeImage.version}" style="color: #ff4056;">remove background image</a>
                    <input type="hidden" name="version" value="${homeImage.version}"/>
                </div>

                <div id="progressbar${status.index}" class="uk-progress uk-hidden">
                    <div class="uk-progress-bar" style="width: 0%;">...</div>
                </div>
            </form>
        </div>

        <div class="uk-width-5-10">
            <!-- This is an image as a thumbnail -->
            <img class="uk-thumbnail" src="<lch:build-path url="${homeImage.imageUrl}"/>" id="home-image${status.index}"/>
        </div>
    </div>

    <script>

        $(function(){
            var progressbar = $("#progressbar${status.index}"),
                    bar         = progressbar.find('.uk-progress-bar'),
                    settings    = {
                        action: '/app/set-home-background-image.json', // 上传路径 url

                        allow : '*.(jpg|jpeg|gif|png)', // 只允许上传图片

                        param : "file", // 名称

                        params : "", // 名称
                        before : function(settings, files){
                            settings.params = {"version": currentRootEl.find("[name=version]").val()};
                        },

                        loadstart: function() {
                            bar.css("width", "0%").text("0%");
                            progressbar.removeClass("uk-hidden");
                        },

                        progress: function(percent) {
                            percent = Math.ceil(percent);
                            bar.css("width", percent+"%").text(percent+"%");
                        },

                        allcomplete: function(response) {
                            response = $.parseJSON(response);
                            if (!response.code) {
                                $("#home-image${status.index}").attr("src", response.fileUrl);
                            }
                            else {
                                UIkit.notify(response.msg, {pos:'top-right', status:'danger'});
                            }
                            bar.css("width", "100%").text("100%");
                        }
                    };

            UIkit.uploadSelect($("#upload-select${status.index}"), settings);
            UIkit.uploadDrop($("#upload-drop${status.index}"), settings);
        });
    </script>
</c:forEach>

<%@ include file="home-background-image-add.jsp" %>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<script type="text/javascript">

    var currentFormEl;
    var currentRootEl;
    $(function(){
        $(".uk-form-file").click(function(){
            currentFormEl = $(this).parent().parent();
            currentRootEl = currentFormEl.parent().parent();
        });
    });

</script>
