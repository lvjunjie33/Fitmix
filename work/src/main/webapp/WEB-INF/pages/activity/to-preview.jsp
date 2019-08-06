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
        <h1>活动内容预览</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row" id = "previewId">

        </div>
    </div>
</div>

<script>
    $(function(){
        $.ajax({
            type: "POST",
            url: "/activity/get-activity-content.json",
            data:"activityId=${activityId}",
            dataType:"json",
            success:function (msg) {
                $("#previewId").html(msg.htmlContent);
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    });



</script>