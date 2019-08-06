<%--
  Created by IntelliJ IDEA.
  User: zhangtao
  Date: 2016/4/26
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/api-nav.jsp" %>
<div class="uk-container-center uk-body" data-uk-sticky="{top:0}">
    <h1>乐享动 APP 相关接口</h1>
</div>
<script>
    $(function(){
        $(".uk-nav-sub li").each(function(e){
            var that = $(this);
            that.click(function(){
                $(".uk-body").load("/api/api-details.htm",{apiId:$(this).data("id"),rootUrl:$(this).parent("ul").data("url")});
//                document.documentElement.scrollTop = document.body.scrollTop = 0;
            });
        });
    });
</script>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
