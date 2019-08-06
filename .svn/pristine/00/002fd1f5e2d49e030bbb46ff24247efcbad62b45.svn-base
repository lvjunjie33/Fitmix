<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/25
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>



<div class="uk-form-row">
    <a href="${refreshUrl}" target="_blank" class="uk-button" >刷新缓存</a>
</div>
<br>
<c:forEach var="item" items="${dic}">

    <div class="uk-grid">
        <div class="uk-width-5-10">
            <div id="upload-drop${item.id}" class="uk-placeholder">
                <h2>${item.name}</h2>
                Info background image... <a class="uk-form-file">Select a file<input id="upload-select${item.id}" type="file" name="file"></a>.
                <br/>

                <input type="hidden" name="id" value="${item.id}"/>
                <input type="hidden" name="value" value="${item.value}"/>
                <input type="hidden" name="type" value="${item.type}"/>

                <form class="uk-form" action="/dic/other-info.json" method="post" isform>
                    <div class="uk-form-row">
                        <input class="uk-width-1-4" type="text" name="paceSpeed" value="${item.other.paceSpeed}"  placeholder="配速信息"/>
                        <input class="uk-width-1-4" type="text" name="rhythm" value="${item.other.rhythm}"  placeholder="节拍"/>
                    </div>
                    <div class="uk-form-row">
                        <textarea class="uk-width-2-4" name="desc" placeholder="描述信息">${item.other.desc}</textarea>
                    </div>
                    <div class="uk-form-row">
                        <input type="hidden" name="id" value="${item.id}"/>
                        <button  class="uk-button uk-button-primary">提交</button>
                    </div>
                </form>

            </div>

            <div id="progressbar${imageUrl.value}" class="uk-progress uk-hidden">
                <div class="uk-progress-bar${imageUrl.value}" style="width: 0%;">...</div>
            </div>
        </div>

        <div class="uk-width-5-10">
            <!-- This is an image as a thumbnail -->
            <img class="uk-thumbnail" src="${item.other.image}" id="home-image${item.value}"/>
        </div>
    </div>

    <br>
    <br>
    <script>
        $(function(){
            var progressbar = $("#progressbar${item.value}"),
                bar         = progressbar.find('.uk-progress-bar${imageUrl.value}'),
                settings    = {
                    action: '/dic/other.json', // 上传路径 url

                    allow : '*.(jpg|jpeg|gif|png)', // 只允许上传图片

                    param : "image", // 名称

                    params : "", // 名称
                    before : function(settings, files){
                        console.info({"id": currentRootEl.find("[name=id]").val(), "value": currentRootEl.find("[name=value]").val()});
                        settings.params = {"id": currentRootEl.find("[name=id]").val(), "value": currentRootEl.find("[name=value]").val(), "type": currentRootEl.find("[name=type]").val()};
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
                            $("#home-image${item.value}").attr("src", response.fileUrl);
                        }
                        else {
                            UIkit.notify(response.msg, {pos:'top-right', status:'danger'});
                        }
                        bar.css("width", "100%").text("100%");
                    }
                };

            UIkit.uploadSelect($("#upload-select${item.id}"), settings);
            UIkit.uploadDrop($("#upload-drop${item.id}"), settings);
        });
    </script>
</c:forEach>

<script type="text/javascript">

    var currentFormEl;
    var currentRootEl;
    $(function(){
        $(".uk-form-file").click(function(){
            currentFormEl = $(this).parent();
            currentRootEl = currentFormEl.parent();
        });
    });

</script>

