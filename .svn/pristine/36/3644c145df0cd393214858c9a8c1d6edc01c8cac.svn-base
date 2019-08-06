<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/3/30
  Time: 下午7:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<link href="/static/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="/static/zTree/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script src="/static/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>

<style type="text/css">
    .role-nav label{display: inline-block;width: 90%;cursor: pointer;}
</style>

<script type="text/javascript">
    var setting = {
        check: {
            enable: true
        }
    };

    var zNodes =[
        { id:1, pId:0, name:"随意勾选 1", open:true,checked:true},
        { id:11, pId:1, name:"随意勾选 1-1",
            children: [
                { "id":3, "name":"test3"},
                { "id":4, "name":"test4"},
                { "id":5, "name":"test5"}
            ]
        }
    ];

    var code;

    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>"+str+"</li>");
    }

    $(document).ready(function(){
        // 展示树
        $.fn.zTree.init($("#role-tree"), setting, zNodes);
    });

</script>

<div class="uk-container uk-container-center uk-body">
    <div class="uk-panel uk-panel-box uk-width-medium-2-5">
        <ul id="role-tree" class="ztree"></ul>
    </div>
</div>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
