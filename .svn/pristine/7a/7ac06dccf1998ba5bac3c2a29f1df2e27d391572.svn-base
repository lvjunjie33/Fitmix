<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/3/30
  Time: 下午7:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<link rel="stylesheet" href="/static/zTree/css/zTreeStyle/zTreeStyle.css"><%-- TODO to Sin 加上 / 表示从项目的根目录开始匹配，这样如果部署的项目是localhost/work 这样根目录并不是localhost会导致文件访问不到 --%>
<script type="text/javascript" src="/static/zTree/js/jquery.ztree.all-3.5.min.js"></script>

<div class="uk-container uk-container-center uk-body">
    <div class="uk-panel uk-placeholder">
        <div class="uk-panel-title">角色资源分配</div>
        <%--  ztree  --%>
        <div id="role-resource" class="ztree"></div>
    </div>
    <div class="uk-form-row uk-placeholder">
        <button class="uk-button uk-button-primary" submit>提交</button>
        <button class="uk-button uk-button" onclick="window.close()">关闭</button>
    </div>
</div>



<script type="text/javascript">

    var roleId = ${id};
    $(function(){
        $("[submit]").click(function(){ //role-operation
            var nodes = $.fn.zTree.getZTreeObj("role-resource").getCheckedNodes();
            var zArray = "";
            for (var i=0, l = nodes.length; i < l; i++) {
                zArray += nodes[i].id;
                if ((nodes.length - 1) != i) {
                    zArray += ",";
                }
            }
            $.post("/role-resource.json",{id : roleId, resources : zArray}, function (result) {
                var code = result['code'];
                if (code == 0) {
                    UIkit.notify("修改成功", {state:'success', pos:'top-right'});
                }
                else {
                    UIkit.notify(result["msg"], {state:'danger', pos:'top-right'});
                }
            });
        });
    });

 // 初始化 zTree 数据
(function(){
    var menuWithOperationMap = ${menuWithOperationMap};
    var roleResourceMap = ${roleResourceMap};
    var pidMap = ${pidMap};
    var menu_nodes = [];
    var operation_nodes = [];
    $.each(pidMap["null"], function(i, pidObj){
        var childrenRes = pidMap[pidObj["id"]];
        var childrenArray = [];
        if (childrenRes) {
            $.each(childrenRes, function (i, chil) {
                roleResourceMap[chil["id"]] ? childrenArray.push({"id":chil["id"], "name": chil["name"], checked:true})
                        : childrenArray.push({"id":chil["id"], "name": chil["name"], checked:false});
                // operation
                var operationArray = pidMap[chil["id"]];
                if (operationArray) {
                    $.each(operationArray, function (oi, operation) {
                        roleResourceMap[operation["id"]] ? childrenArray.push({"id":operation["id"], "name": chil["name"] + "-->" + operation["name"], checked:true})
                                : childrenArray.push({"id":operation["id"], "name": chil["name"] + "-->" + operation["name"], checked:false});
                    });
                }
            });
        }
        roleResourceMap[pidObj["id"]] ?  menu_nodes.push({id : pidObj["id"], name: pidObj["name"], checked:true, children: childrenArray})
                : menu_nodes.push({id : pidObj["id"], name: pidObj["name"], checked:false, children: childrenArray});
    });

    // 资源树设置
    var resourceTreeSetting = {
        check: {
            enable: true
        }
    };
    // operation
    var operationTreeSetting = {
        check: {
            enable: true,
            chkboxType : { "Y" : "", "N" : "" }
        }
    }
    // 展示树
    $.fn.zTree.init($("#role-resource"), resourceTreeSetting, menu_nodes);
    // 展开
    $.fn.zTree.getZTreeObj("role-resource").expandAll(true);
})();
</script>



<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
