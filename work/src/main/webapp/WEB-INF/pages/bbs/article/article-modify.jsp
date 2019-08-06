<%@ taglib prefix="lc" uri="http://tag.lch.com/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<div class="uk-container-center uk-body">
    <h2>修改文章</h2>
    <form action="/bbs/article/article-modify.json" method="post" class="uk-form uk-form-horizontal"  enctype="multipart/form-data" channel-app-add-form>
        <input type="hidden" value="${article.id}" name="id">
        <div class="uk-form-row">
            <label class="uk-form-label">标题：</label>
            <div class="uk-form-controls"><input type="text" value="${article.title}" name="title" class="uk-form-large uk-width-9-10" notnull></div>
        </div>

        <div class="uk-form-row">
            <label class="uk-form-label">类别：</label>
            <div class="uk-form-controls">
                <select name="category" class="uk-form-large uk-width-9-10">
                    <c:forEach items="${categoryList}" var="category">
                        <option value="${category.name}" <c:if test="${article.category eq category.name}">selected</c:if> >${category.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="uk-form-row">
            <label class="uk-form-label">角标：</label>
            <div class="uk-form-controls"><input type="text" value="${article.cornerMark}" name="cornerMark" class="uk-form-large uk-width-9-10" notnull></div>
        </div>

        <div class="uk-form-row">
            <label class="uk-form-label">封面：</label>
            <div class="uk-form-controls">
                <img src="<lc:build-path url="${article.coverUrl}" />">
            </div>
        </div>

        <div class="uk-form-row">
            <label class="uk-form-label">简介：</label>
            <div class="uk-form-controls">
                <textarea name="desc" class="uk-form-large uk-width-9-10" cols="5">${article.desc}</textarea>
            </div>
        </div>


        <div class="uk-form-row">
            <label class="uk-form-label">内容：</label>
            <div class="uk-form-controls">
                <div id="div1" style="height:600px;max-height:700px;width: 730px;">
                    <p>请输入内容...</p>
                </div>
            </div>
        </div>
        <input type="hidden" id="content" name="content" value='${article.content}' />
        <div class="uk-form-row" style="margin-top: 20px;">
            <div class="uk-form-controls">
                <button type="submit" class="uk-button uk-button-primary" target="role-add-form" style="width: 93%" channel-app-add-submit>modify</button>
            </div>
        </div>
    </form>
    <br/>
</div>


<script>

(function(){

    var editor = new wangEditor('div1');

    editor.config.uploadImgUrl = '/article/upload-img.json';
    editor.config.uploadImgFns.onload = function (resultText, xhr) {
        // resultText 服务器端返回的text
        // xhr 是 xmlHttpRequest 对象，IE8、9中不支持

        // 如果 resultText 是图片的url地址，可以这样插入图片：
        var datas = eval("[" + resultText + "]");
        if (datas[0].code == 0) {
            editor.command(null, 'insertHtml', '<img src="' + datas[0].imgUrl + '" style="max-width:100%;"/>');
            // 如果不想要 img 的 max-width 样式，也可以这样插入：
            // editor.command(null, 'InsertImage', resultText);
        }
    };

    // 表情
    editor.config.emotions = {
        'default': {
            title: '默认',
            data: "/static/wangEditor/emotions.data"
        },
        'weibo': {
            title: '微博表情',
            data: [
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif',
                    value: '[神马]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/fuyun_thumb.gif',
                    value: '[浮云]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c9/geili_thumb.gif',
                    value: '[给力]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f2/wg_thumb.gif',
                    value: '[围观]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/vw_thumb.gif',
                    value: '[威武]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6e/panda_thumb.gif',
                    value: '[熊猫]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/81/rabbit_thumb.gif',
                    value: '[兔子]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/otm_thumb.gif',
                    value: '[奥特曼]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/15/j_thumb.gif',
                    value: '[囧]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/89/hufen_thumb.gif',
                    value: '[互粉]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c4/liwu_thumb.gif',
                    value: '[礼物]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/ac/smilea_thumb.gif',
                    value: '[呵呵]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/0b/tootha_thumb.gif',
                    value: '[哈哈]'
                }
            ]
        }
    };
    editor.create();
    editor.$txt.html($("#content").val());

    $("[channel-app-add-form]").submit(function(){
        var htmlContent = editor.$txt.html();
        $("#content").val(htmlContent);
        var form = $(this);
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
                        UIkit.notify("修改成功", {status:'success', pos:'top-right'});
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

})($);

</script>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
