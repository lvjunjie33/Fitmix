<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/4/13
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>活动内容编辑</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
                <div class="uk-form-row">
                    <span class="uk-width-1-1 uk-form-large" placeholder="活动主题"
                          style = "text-transform: uppercase;font-weight: 700;font-size: 12px;" >&nbsp;&nbsp;&nbsp;${activity.themeName}</span>
                </div>
                <div class="uk-form-row">
                    <div id="div1" style="height:600px;max-height:700px;">
                        <p>请输入内容...</p>
                    </div>
                </div>

                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="activityId" value="${activity.id}">
                    <button class="uk-button uk-button-primary" id="add-event-content-submit" target="role-add-form">submit</button>
                    <!-- 触发模态对话框的按钮 -->
                    <button class="uk-button" data-uk-modal="{target:'#my-id'}" id = "preview-event-content">去预览</button>

                    <!-- 模态对话框 -->
                    <div id="my-id" class="uk-modal">
                        <div class="uk-modal-dialog" id = "preview-view">

                        </div>
                    </div>
                </div>
        </div>
    </div>
</div>

<script>
    $(function(){
        var editor = new wangEditor('div1');

        editor.config.uploadImgUrl = '/activity/upload-img.json';
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

        //初始化编辑区域内容
        $(function(){
            $.ajax({
                type: "POST",
                url: "/activity/get-activity-content.json",
                data:"activityId=${activity.id}",
                dataType:"json",
                success:function (msg) {
                    editor.$txt.html(msg.htmlContent);
                },
                error:function (XMLHttpRequest,textStatus,errorThrom){
                    console.log("不明原因造成数据获取失败... ...");
                }
            });
        });

        //提交保存
        $('#add-event-content-submit').click(function () {
            // 获取编辑器区域完整html代码
            var htmlContent = editor.$txt.html();

            // 获取编辑器纯文本内容
            //var text = editor.$txt.text();

            // 获取格式化后的纯文本
            //var formatText = editor.$txt.formatText();
            //$("#test-div").html(htmlContent);

            var activityId = $("[name=activityId]").val();
            $.ajax({
                type: "POST",
                url: "/activity/add-activity-content.json",
                data:"activityId=" + activityId + "&htmlContent=" + encodeURIComponent(htmlContent),
                dataType:"json",
                success:function (msg) {
                    console.info(msg);
                    if(msg.code == 0) {
                        alert("保存成功");
                    }
                },
                error:function (XMLHttpRequest,textStatus,errorThrom){
                    console.log("不明原因造成数据获取失败... ...");
                }
            });
        });

        $("#preview-event-content").click(function(){
            $("#preview-view").html(editor.$txt.html());
        });
    });

</script>

<script>

    // 设置  status releaseStatus 选项
    $(function(){
        $("[name=status]").val($("[name=status]").attr("data-value"));
        $("[name=releaseStatus]").val($("[name=releaseStatus]").attr("data-value"));
    });

</script>
