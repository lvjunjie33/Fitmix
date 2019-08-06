<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/10/20
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<link rel="stylesheet" type="text/css" href="/static/wx/layout_head2880f5.css">
<link rel="stylesheet" type="text/css" href="/static/wx/base2a7519.css">
<link rel="stylesheet" type="text/css" href="/static/wx/lib2968da.css">
<link rel="stylesheet" type="text/css" href="/static/wx/index2a7519.css">
<link rel="stylesheet" type="text/css" href="/static/wx/media_dialog27dcd8.css">
<link rel="stylesheet" type="text/css" href="/static/wx/tooltip218878.css">

<style>
    .container_box.cell_layout .col_side, .container_box.cell_layout .col_main {
        display: table-cell;
        vertical-align: top;
        word-wrap: break-word;
        word-break: break-all;
    }
    .inner_container_box {
        background-color: #fff;
        border: 1px solid #e7e7eb;
    }
    .inner_container_box:after {
        content: "\200B";
        display: block;
        height: 0;
        clear: both;

    }
</style>
<style type="text/css">
    html,body{background: url("/imgs/back.png")}

    .home-body{padding:40px 20px;}
    .home-body-head{text-align: right;margin-bottom: 8px;}

    .home-nav{margin-top: 60px;border-right: 1px solid #e2e2e2;}
    .home-nav a{color: #808080;}
    .home-nav a:hover{color: #000;background: #f6f6f6;}
</style>
<div class="menu_setting_area_wrp" id="menu_container" style="">
    <div class="menu_setting_area edit">
        <p class="menu_setting_tips">可创建最多3个一级菜单，每个一级菜单下可创建最多5个二级菜单。</p>
            <div class="inner_container_box side_l cell_layout">
                <div class="inner_side">
                    <div class="bd">


                        <div class="menu_manage">
                            <div class="sub_title_bar light">
                                菜单管理
                                <div class="opr_wrp" style="float: right;">
                                    <a href="javascript:void(0);" id="addBt" class="addBt opr_meta icon16_common add_gray" style="display: inline-block;" root_menu_click>添加</a>
                                </div>
                            </div>

                            <%--  menu list --%>
                            <div class="inner_menu_box gray with_switch ui-sortable ui-sortable-disabled" id="menuList"></div>
                        </div>
                    </div>
                </div>
                <div class="inner_main">

                </div>
                <div class="inner_container_box_bd"></div>
            </div>
        </div>
    </div>
    <div class="tool_bar tc js_totaltool_bar">
        <a href="javascript:void(0);" class="btn btn_primary" id="pubBt">发布</a>
        <a href="javascript:void(0);" class="btn btn_default" id="viewBt">预览</a>
    </div>
</div>

<%--  菜单跟模板  --%>
<script type="text/html" id="menu_root">
    <dl class="inner_menu jsMenu ui-sortable ui-sortable-disabled" id='{2}' data='{1}' menu_row_click>
        <dt class="inner_menu_item jslevel1">
            <a href="javascript:void(0);" class="inner_menu_link"><strong>{0}</strong></a>
            <span class="menu_opr">
                <a href="javascript:void(0);" class="js_addSecondMenu icon14_common add_gray jsAddBt" menu_child_click data='{1}'>添加</a>
                <a href="javascript:void(0);" class="uk-icon-hover uk-icon-sort-asc" data='{1}' sort_click type="asc"></a>
                <a href="javascript:void(0);" class="uk-icon-hover uk-icon-sort-desc" data='{1}' sort_click type="desc"></a>
                <a href="javascript:void(0);" class="uk-icon-hover uk-icon-edit" data='{1}' edit_click></a>
                <a href="javascript:void(0);" class="uk-icon-hover uk-icon-remove" data='{1}' remove_click></a>
            </span>
        </dt>
    </dl>
</script>

<%--  菜单子模板  --%>
<script type="text/html" id="menu_child">
    <dd class="inner_menu_item jslevel2" data='{1}' id='{2}' menu_row_click>
        <i class="icon_dot">●</i>
        <a href="javascript:void(0);" class="inner_menu_link">
            <strong>{0}</strong>
        </a>
        <span class="menu_opr">
             <a href="javascript:void(0);" class="js_addSecondMenu icon14_common add_gray jsAddBt" data='{1}'>删除</a>
             <a href="javascript:void(0);" class="uk-icon-hover uk-icon-sort-asc" data='{1}' sort_click type="asc"></a>
             <a href="javascript:void(0);" class="uk-icon-hover uk-icon-sort-desc" data='{1}' sort_click type="desc"></a>
             <a href="javascript:void(0);" class="uk-icon-hover uk-icon-edit" data='{1}' edit_click></a>
             <a href="javascript:void(0);" class="uk-icon-hover uk-icon-remove" data='{1}' remove_click></a>
        </span>
    </dd>
</script>

<%--  内容模板  --%>
<script type="text/html" id="tem_inner_main">
    <div class="action_content sended jsMain" id="view" style="display: block;">
        <p class="action_tips">{2}</p><%--订阅者点击该子菜单会跳到以下链接--%>
        <div class="msg_wrp" id="viewDiv" style="min-height: 21px;">{0}</div>
        <p class="frm_tips" style="display: block;">{3}<%--来源素材库--%></p>
        <div class="btn_wrp">
            <a href="javascript:void(0);" class="btn btn_default btn_editing" id="changeBt" style="display: inline-block;" data='{1}' onclick="urlUpdateClick(this)">修改</a>
        </div>
    </div>
</script>


<%--  提示框模板  --%>
<script type="text/html" id="tem_dialog">
    <div class="dialog_wrp  ui-draggable" style="width: 720px; margin-left: -360px; margin-top: -185.5px;">
        <div class="dialog" id="wxDialog_2">
            <div class="dialog_hd">
                <h3>{0}</h3>
                <!--#0001#-->
                <a href="javascript:;" class="pop_closed">关闭</a>
                <!--%0001%-->
            </div>
            <div class="dialog_bd">
                <div class="page_msg large simple default ">
                    <div class="inner group">
                        <%--<span class="msg_icon_wrapper"><i class="icon_msg warn "></i></span>--%>
                        <div class="msg_content ">
                            <h4 class="uk-nav-header">{1}</h4>
                            <p>{2}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="dialog_ft">
                <a href="javascript:;" class="btn btn_primary js_btn pop_ok">确定</a>
                <a href="javascript:;" class="btn btn_default js_btn pop_cancel">取消</a>
            </div>
        </div>
    </div>
    <div class="menu_mask"></div>
</script>

<script style="text/javascript">

    (function(window){
        var wx_menu = function(){} || {};

        wx_menu.dialog = function(head, title, content, data, fn){
            // 设置参数
            $("#tem_dialog").attr("data", data);
            var tem = $("#tem_dialog").text();
            // 添加元素
            $("body").append(tem.format(head, title, content));

            // 监听 close
            $(".pop_closed").click(function(){
                fn("close", $("#tem_dialog").attr("data"), $(".dialog_wrp"));
            });
            // 监听 cancel
            $(".pop_cancel").click(function(){
                fn("cancel", $("#tem_dialog").attr("data"), $(".dialog_wrp"));
            });
            // 监听 ok
            $(".pop_ok").click(function(){
                fn("ok", $("#tem_dialog").attr("data"), $(".dialog_wrp"));
            });

            wx_menu.dialog.close = function() {
                $(".dialog_wrp").remove();
                $(".menu_mask").remove();
            }
        }

        window.wx_menu = wx_menu;
    })(window);



    $(function(){
        /// root 菜单
        $("[root_menu_click]").click(rootClick);
        /// 子菜单
        $("[menu_child_click]").unbind().bind("click", childClick);
        /// 排序
        $("[sort_click]").click(sortClick);
        /// 删除
        $("[remove_click]").click(removeClick);
        /// 编辑
        $("[edit_click]").click(editClick);
        /// row click
        $("[menu_row_click]").click(inner_main_click);
    });


    function createRootMenu(name, data, id) {
        var menu_root = $("#menu_root").text().format(name, data, id);
        $("#menuList").append(menu_root);
        /* 绑定在菜单事件 */
        $("[menu_child_click]").unbind().bind("click", childClick);
        /*  点击行时  弹出 inner_main 内容修改 */
//        $("[menu_row_click]").unbind().bind(inner_main_click);
    }

    function createChild(th, name, data, id) {
        var menu_root = $("#menu_child").text().format(name, data, id);
        th.append(menu_root);
    }

    /// main 内容渲染
    function inner_main_click(){
        var th = $(this);
        var baseData = $.parseJSON(th.attr("data"));
        var innerMainHtml;
        if (baseData["type"] == "view") {
            innerMainHtml = $("#tem_inner_main").text().format(baseData["url"], JSON.stringify(baseData), "订阅者点击该子菜单会跳到以下链接", "素材库 或 URL地址");
        }
        else if(baseData["type"] == "click"){
            innerMainHtml = $("#tem_inner_main").text().format(baseData["key"], JSON.stringify(baseData), "订阅者点击该子菜单会Click Key", "点击的 Key 值");
        }
        $(".inner_main").html(innerMainHtml);
        return false;
    }

    /// main 修改 url or key
    function urlUpdateClick(el) {
        var th = $(el);
        var baseData = $.parseJSON(th.attr("data"));
        if (th.attr("type")) {
            try{
                var param = {};
                var url = "";

                /// 处理是 key还是 url地址值，再确定请求地址
                var urlOrKey = $("#viewDiv").find("[name=url]").val();
                if (baseData["type"] == "view") {
                    url = "/wx-menu/update-url.json";
                    param = {"id": baseData["id"], "url": urlOrKey};
                }
                else if(baseData["type"] == "click"){
                    url = "/wx-menu/update-key.json";
                    param = {"id": baseData["id"], "key": urlOrKey};
                }

                /* 后台发送数据 */
                $.post(url, param, function(result){
                    th.text("修改").removeClass("btn_primary").addClass("btn_default").removeAttr("type");
                    $("#viewDiv").css("border-color", "#e7e7eb").html(urlOrKey);
                    UIkit.notify("修改成功", {pos:'top-right'});
                });
            } catch (e) {
                UIkit.notify("创建出错", {pos:'top-right',status:'danger'});
            }
        }
        else {
            th.text("提交").addClass("btn_primary").removeClass("btn_default").attr("type", "sub");
            $("#viewDiv").css("border-color", "#44b549").html('<input type="text" name="url" value="' + $("#viewDiv").text() +'" style="width:99%;border:none;"/>');
        }
    }

    /// root 菜单
    function rootClick() {
        var th =$(this);
        wx_menu.dialog("温馨提示", "", '名称<input type="text" name="menu_name" /><br/><br/><label><input type="radio" name="type" value="click"/>按钮</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                '<label><input type="radio" name="type" value="view"/>跳转</label>', "root", function(even, data, el){
            if (even == "close") {
                wx_menu.dialog.close();
            }
            else if (even == "cancel") {
                wx_menu.dialog.close();
            }
            else if (even == "ok") {
                try{
                    /* 后台发送数据 */
                    $.post("/wx-menu/add-menu.json", {"name": el.find("[name=menu_name]").val(), "parent":"", "url":"", "type":el.find("[name=type]:checked").val()}, function(result){
                        createRootMenu(el.find("[name=menu_name]").val(), "data base sa", "root");
                        wx_menu.dialog.close();
                        location.reload();
                    });
                } catch (e) {
                    UIkit.notify("创建出错", {pos:'top-right',status:'danger'});
                }
            }
        });
    }

    /// 点击 root
    function childClick() {
        var th =$(this);
        var baseData = $.parseJSON(th.attr("data"));
        wx_menu.dialog("温馨提示", "请输入要添加的菜单", '<input type="text" name="menu_name" /><br/><br/><label><input type="radio" name="type" value="click"/>按钮</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                '<label><input type="radio" name="type" value="view"/>跳转</label>', "root", function(even, data, el){
            if (even == "close") {
                wx_menu.dialog.close();
            }
            else if (even == "cancel") {
                wx_menu.dialog.close();
            }
            else if (even == "ok") {
                if (th.parent().parent().parent().find(".inner_menu_item").size() < 6) {
                    /* 后台发送数据 */
                    try {
                        $.post("/wx-menu/add-menu.json", {"name": el.find("[name=menu_name]").val(), "parent":baseData["id"], "url":"", "type": el.find("[name=type]:checked").val()}, function(result){
                            createChild(th.parent().parent().parent(), el.find("[name=menu_name]").val(), "data base sa", "czzxc");
                            wx_menu.dialog.close();
                        });
                    } catch (e) {
                        UIkit.notify("创建出错", {pos:'top-right',status:'danger'});
                    }
                }
                else {
                    UIkit.notify("二级菜单不能超过5个", {pos:'top-right'})
                }
            }
        });
    }


    function sortClick() {
        var th = $(this);
        var type = th.attr("type");
        var data= $.parseJSON(th.attr("data"));
        try {
            $.get("/wx-menu/update-sort.json", {"id":data["id"], "type": type}, function(result){
                location.reload();
            });
        } catch (e) {
            UIkit.notify("移动出错", {pos:'top-right',status:'danger'});
        }
    }

    function removeClick() {
        var th = $(this);
        var baseData = $.parseJSON(th.attr("data"));

        wx_menu.dialog("温馨提示", "确认删除改菜单吗？", '', "root", function(even, data, el){
            if (even == "close") {
                wx_menu.dialog.close();
            }
            else if (even == "cancel") {
                wx_menu.dialog.close();
            }
            else if (even == "ok") {
                try {
                    $.get("/wx-menu/remove-menu.json", {"id":baseData["id"]}, function(result){
                        location.reload();
                    });
                } catch (e) {
                    UIkit.notify("移动出错", {pos:'top-right',status:'danger'});
                }
            }
        });
    }

    function editClick() {
        var th = $(this);
        var baseData = $.parseJSON(th.attr("data"));
        wx_menu.dialog("编辑菜单", "请输入要编辑的内容", '<input type="text" value="' + baseData["name"] + '" name="menu_name"/>', "root", function(even, data, el){
            if (even == "close") {
                wx_menu.dialog.close();
            }
            else if (even == "cancel") {
                wx_menu.dialog.close();
            }
            else if (even == "ok") {
                try {
                    var name = el.find("[name=menu_name]").val();
                    $.post("/wx-menu/update-name.json", {"id":baseData["id"], "name": name}, function(result){
                        location.reload();
                    });
                } catch (e) {
                    UIkit.notify("移动出错", {pos:'top-right',status:'danger'});
                }
            }
        });

    }

    var menu_list = '${wxMenu}';
    menu_list = $.parseJSON(menu_list);
    $.each(menu_list, function(index, menu){
        console.info(menu);
        createRootMenu(menu["name"], JSON.stringify(menu), menu["id"]);
        if (menu["childMenu"]) {
            $.each(menu["childMenu"], function(jdex, childM){
                createChild($("#" + menu["id"]), childM["name"], JSON.stringify(childM), childM["id"]);
            });
        }
    });


/*     发布 or 预览     */
$(function(){
    $("#pubBt").click(function(){
        $.get("/wx-menu/send-wx-server.json", {}, function(result) {
            if (result["code"] == 0) {
                UIkit.notify("提交成功.", {pos:'top-right'});
            }
            else {
                UIkit.notify(result["code"] + "  " + result["msg"], {pos:'top-right',status:'danger'});
            }
        });
    });
});

</script>