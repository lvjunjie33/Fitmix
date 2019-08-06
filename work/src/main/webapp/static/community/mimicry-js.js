/**
 * Created by edward on 2017/8/7.
 */
var mimicry = function () {

    var attr = {
        selectThemeId : undefined,
        selectAnswerId : undefined,
        selectUid : undefined,
        selectDiscussId : undefined,
        selectDiscussUid : undefined,
        selectThemeTrTd : undefined,
        bgColor : "rgb(237, 242, 220)",
        level_1 : 1,
        level_2 : 2,
        answerCurrentPageNo:1,
        discussCurrentPageNo:1,
        defaultAvatar : "http://yyssb.ifitmix.com/2014/e52928e9211f4acfba6b3ebc68bf8707.png"
    };

    // 问题/回答
    function theme() {
        this.title;
        this.content;
        this.uid;
        this.uname;
        this.avatar;
        this.discussNum;
        this.taoLunNum;
        this.upNum;
        this.signature;
        this.addTime;
    }

    // 讨论
    function discuss() {
        this.content;
        this.uid;
        this.uname;
        this.avatar;
        this.discussNum;
        this.taoLunNum;
        this.upNum;
        this.signature;
        this.addTime;
    }

    //获取回答模版
    function getAnswerModel() {
        return'<div class="uk-form-row node-div" id = "node-div-discuss-{id}" >'
                    +'<div class="uk-form-row div3-style" style="margin-top: 0px">'
                    +   '<img class="radius-img" src="{avatar}" />'
                    +   '<label class="label-style" style="font-size: 20px">&nbsp;&nbsp;{uid}, {name}</label>'
                    +'</div>'
                    +'<div class="uk-form-row div3-style content-style" style="margin-top: 0px">'
                    +   '<label class="label-style" style="font-size: 14px;font-weight: 300">{content}</label>'
                    +'</div>'
                    +'<div class="uk-form-row div3-style" style="margin-top: 0px">'
                    +   '<table class="table-font-9">'
                    +       '<tr>'
                    +       '<td style="width: 130px;">{addTimeStr}</td>'
                    +       '<td style="width: 50px;text-align: right">点赞</td>'
                    +       '<td style="width: 35px">{upNum}</td>'
                    +       '<td style="width: 50px;text-align: right"><a href="#add-discuss" data-uk-modal onclick="mimicry.selectDiscuss({id})">讨论</a></td>'
                    +       '<td style="width: 35px">&nbsp;&nbsp;<a onclick="mimicry.loadDiscuss({id})">{taoLunNum}</a></td>'
                    +       '</tr>'
                    +       '<tr><td colspan="5">{isSensitiveStr}</td></tr>'
                    +   '</table>'
                    +'</div>'
                +'</div>';
    }

    //获取讨论模版
    function getDiscussModel() {
        return '<div class="uk-form-row node-div" id = "node-div-discuss2{id}" >'
                +   '<div class="uk-form-row div3-style" style="margin-top: 0px">'
                +       '<img class="radius-img" src="{avatar}" />'
                +       '<label class="label-style" style="font-size: 20px">&nbsp;&nbsp;{uid}, {userName}</label>'
                +   '</div>'
                +   '<div class="uk-form-row div3-style" style="margin-top: 0px">'
                +       '<label class="label-style content-style" style="font-size: 14px;font-weight: 300">{content}</label>'
                +   '</div>'
                +   '<div class="uk-form-row div3-style" style="margin-top: 0px">'
                +       '<table class="table-font-9">'
                +           '<tr>'
                +               '<td style="width: 130px;">{addTimeStr}</td>'
                +               '<td style="width: 50px;text-align: right">点赞：</td>'
                +               '<td style="width: 35px">{upNum}</td>'
                +               '<td style="width: 50px;text-align: right"></td>'
                +               '<td style="width: 35px"><a href="#add-discuss" data-uk-modal onclick="mimicry.selectDiscuss2({themeId}, {id}, {uid})">讨论</a></td>'
                +           '</tr>'
                +           '<tr><td colspan="5">{isSensitiveStr}</td></tr>'
                +       '</table>'
                +   '</div>'
                +'</div>';
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //theme

    function loadTheme(themeId) {
        attr.selectThemeId = themeId;
        $.ajax({
            type: "POST",
            url: "/theme/get.json",
            data:{'id': attr.selectThemeId},
            dataType:"json",
            success:function (msg) {
                buildThemeTable(msg.theme)
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }

    function buildThemeTable(theme) {
        var content = theme.content;
        var rowContents = content.split("<br/>");
        $(rowContents).each(function(i, item){
            if (item != undefined && item != "") {
                $("#add-answer-table-id").append(
                    "<tr>"
                    +   "<td>"+item+"</td>"
                    +   "<td  style='width: 30px'><a href='#modify-theme' data-uk-modal onclick='mimicry.selectTableRow(this)'>编辑</a></td>"
                    +   "<td  style='width: 30px'><a onclick='mimicry.moveRow(this)'>移除</a></td>"
                    +"</tr>");
            }
        });
    }


    // 编辑 插入 图片
    function insertThemeContentImg(id, from) {
        var $id = "#" + id;
        var $from = "#" + from;
        $($from).ajaxSubmit(function(obj){
            var link_ = obj.link;
            $($id).append(
                "<tr>"
                +   "<td><img src = '" + link_ + "'/></td>"
                +   "<td style='width: 30px'><a href='#modify-theme' data-uk-modal onclick='mimicry.selectTableRow(this)'>编辑</a></td>"
                +   "<td style='width: 30px'><a onclick='mimicry.moveRow(this)'>移除</a></td>" +
                "</tr>");
        });

        $($from).submit(function(){
            return false;
        });
    }

    //编辑 插入 文本
    function insertThemeContentText() {
        var text = textToHtml($("#textarea-id").val());

        $("#add-answer-table-id").append(
            "<tr>"
            +   "<td>"+text+"</td>"
            +   "<td style='width: 30px'><a href='#modify-theme' data-uk-modal onclick='mimicry.selectTableRow(this)'>编辑</a></td>"
            +   "<td style='width: 30px'><a onclick='mimicry.moveRow(this)'>移除</a></td>"
            +"</tr>");
    }

    //编辑 修改 图片
    function modifyThemeContentImg() {
        var $from = "#modify-theme-img-from";
        $($from).ajaxSubmit(function(obj){
            var link_ = obj.link;
            $(attr.selectThemeTrTd).html("<img src = '" + link_ + "' />");
        });

        $($from).submit(function(){
            return false;
        });
    }

    //编辑 修改 文本
    function modifyThemeContentText() {
        var text = textToHtml($("#modify-theme-text-textarea").val());

        $(attr.selectThemeTrTd).html(text);
    }

    function selectTableRow(obj) {
        attr.selectThemeTrTd = $(obj).parent().parent().find("td")[0];
    }

    //保存 话题内容
    function saveTheme() {
        var trs = $("#add-answer-table-id").find("tr");

        var content = "";

        $(trs).each(function(i, item){
            var td = $(item).find("td")[0];
            if (td != undefined) {
                content += $(td).html() + "<br/>";
            }
        });

        $.ajax({
            type: "POST",
            url: "/theme/modify-content.json",
            data:{'themeId': attr.selectThemeId, 'content': content},
            dataType:"json",
            success:function (msg) {
                if (msg.code == 9999) {
                    alert(msg.msg);
                } else {
                    alert("保存成功");
                }
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }

    function themeHandleIsSensitive(themeId) {
        $.ajax({
            type: "POST",
            url: "/theme/handle/is/sensitive.json",
            data:{'themeId': themeId},
            dataType:"json",
            success:function (msg) {
                if (msg.code == 9999) {
                    alert(msg.msg);
                } else {
                    alert("修改成功");
                }
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 初始化
    function iniAttr(level) {
        if (level == attr.level_1) {
            attr.selectDiscussId = undefined;
            attr.selectDiscussUid = undefined;
            attr.selectAnswerId = undefined;
            attr.answerCurrentPageNo = 1;
            attr.discussCurrentPageNo = 1;
        } else if(level == attr.level_2) {
            attr.selectDiscussId = undefined;
            attr.selectDiscussUid = undefined;
            attr.discussCurrentPageNo = 1;
        }
    }

    // 加载回答
    function loadAnswer(themeId) {
        selectTheme(themeId);
        ajaxAnswer(themeId)
    }

    function ajaxAnswer(themeId) {
        $.ajax({
            type: "POST",
            url: "/theme/answer/list.json",
            data:{'themeId': themeId, 'pageNo': attr.answerCurrentPageNo},
            dataType:"json",
            success:function (msg) {
                var result = msg.page.result;
                buildAnswer(result)
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }

    // 刷新回答
    function refreshAnswer(themeId) {
        alert(themeId);
    }

    //上一页
    function prevAnswer() {
        if (attr.selectThemeId == undefined || attr.selectThemeId == null || attr.selectThemeId == '') {
            return;
        }
        if (attr.answerCurrentPageNo > 1) {
            attr.answerCurrentPageNo -= 1;
            ajaxAnswer(attr.selectThemeId);
        }
    }
    //下一页
    function nextAnswer() {
        if (attr.selectThemeId == undefined || attr.selectThemeId == null || attr.selectThemeId == '') {
            return;
        }
        attr.answerCurrentPageNo += 1;
        ajaxAnswer(attr.selectThemeId)
    }

    // 构建回答内容
    function buildAnswer(obj) {
        var answerModel = getAnswerModel();
        var html = "";
        $(obj).each(function(i, item) {
            var da = new Date();
            da.setTime(item.addTime);
            item.addTimeStr = da.toLocaleString();
            if (item.avatar == undefined || item.avatar == null || item.avatar == '') {
                item.avatar = attr.defaultAvatar;
            }
            if (item.isSensitive == 1) {
                item.isSensitiveStr = '<div style="width: 100%;text-align:left;"><a onclick="mimicry.themeHandleIsSensitive(' + item.id + ')">屏蔽</a></div>';
            } else {
                item.isSensitiveStr = '<div style="width: 100%;text-align:left;background: red"><a onclick="mimicry.themeHandleIsSensitive(' + item.id + ')">解禁</a></div>';
            }
            html += format(answerModel, item);
        });

        $("#answer-div-id").html(html)
    }

    //插入 回答的图片
    function insertAnswerContentImg(id, from) {
        var $id = "#" + id;
        var $from = "#" + from;
        $($from).ajaxSubmit(function(obj){
            var link_ = obj.link;
            $($id).append("<tr><td style='width: 90%'><img src = '" + link_ + "' /></td><td><a onclick='mimicry.moveRow(this)'>移除</a></td></tr>");
        });

        $($from).submit(function(){
            return false;
        });
    }

    //插入 回答的文本
    function insertAnswerContentText() {
        var text = textToHtml($("#textarea-id").val());

        $("#add-answer-table-id").append("<tr><td style='width: 90%'>"+text+"</td><td><a onclick='mimicry.moveRow(this)'>移除</a></td></tr>");
    }

    function selectTheme(id) {
        iniAttr(attr.level_1);
        if (attr.selectThemeId != undefined) {
            var oldDiv = "#node-div-" + attr.selectThemeId;
            $(oldDiv).css({"background": ''})
        }

        attr.selectThemeId = id;
        var div = "#node-div-" + id;
        $(div).css({"background":attr.bgColor})
    }

    //保存回答
    function saveAnswer() {

        if (attr.selectUid == undefined) {
            alert("你想用那个帐号来水贴呢");
            return false;
        }

        var table = $("#add-answer-table-id");
        var content = "";
        $(table.find("tr")).each(function(i, item) {
            var td = $(item).find("td")[0];
            if (td != undefined) {
                content += $(td).html() + "<br/>";
            }
        });

        $.ajax({
            type: "POST",
            url: "/theme/add/answer.json",
            data:{'parentThemeId': attr.selectThemeId, 'content': content, 'uid' : attr.selectUid},
            dataType:"json",
            success:function (msg) {
                if (msg.code == 9999) {
                    alert(msg.msg);
                } else {
                    alert("水贴成功")
                }
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //加载讨论
    function loadDiscuss(answerId) {
        if (attr.selectUid == null || attr.selectUid == undefined || attr.selectUid == '') {
            alert("请选择用户！！！");
            return;
        }
        selectDiscuss(answerId)
        ajaxDiscuss(answerId);
    }

    function ajaxDiscuss(answerId) {
        $.ajax({
            type: "POST",
            url: "/theme/discuss/list.json",
            data:{'themeId': answerId, 'pageNo' : attr.discussCurrentPageNo, uid : attr.selectUid},
            dataType:"json",
            success:function (msg) {
                buildDiscuss(msg.page.result)
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }

    //刷新讨论
    function refreshDiscuss(answerId) {
        alert(answerId);
    }

    //上一页
    function prevDiscuss() {
        if (attr.selectAnswerId == undefined || attr.selectAnswerId == null || attr.selectAnswerId == '') {
            return;
        }
        if (attr.answerCurrentPageNo > 1) {
            attr.discussCurrentPageNo -= 1;
            ajaxDiscuss(attr.selectAnswerId);
        }
    }
    //下一页
    function nextDiscuss() {
        if (attr.selectAnswerId == undefined || attr.selectAnswerId == null || attr.selectAnswerId == '') {
            return;
        }
        attr.discussCurrentPageNo += 1;
        ajaxDiscuss(attr.selectAnswerId)
    }

    // 构建讨论内容
    function buildDiscuss(obj) {
        var discussModel = getDiscussModel();
        var html = "";
        $(obj).each(function(i, item) {
            var da = new Date();
            da.setTime(item.addTime);
            item.addTimeStr = da.toLocaleString();
            if (item.avatar == undefined || item.avatar == null || item.avatar == '') {
                item.avatar = attr.defaultAvatar;
            }

            if (item.isSensitive == 1) {
                item.isSensitiveStr = '<div style="width: 100%;text-align:left;"><a onclick="mimicry.discussHandleIsSensitive(' + item.id + ')">屏蔽</a></div>';
            } else {
                item.isSensitiveStr = '<div style="width: 100%;text-align:left;background: red"><a onclick="mimicry.discussHandleIsSensitive(' + item.id + ')">解禁</a></div>';
            }

            html += format(discussModel, item);
        });

        $("#discuss-div-id").html(html)
    }

    function selectDiscuss(id) {
        iniAttr(attr.level_2);
        if (attr.selectAnswerId != undefined) {
            var oldDiv = "#node-div-discuss-" + attr.selectAnswerId;
            $(oldDiv).css({"background": ''})
        }

        attr.selectAnswerId = id;
        var div = "#node-div-discuss-" + id;
        $(div).css({"background": attr.bgColor})
    }

    function selectDiscuss2(id, discussId, discussUid) {
        iniAttr(attr.level_2);
        if (attr.selectAnswerId != undefined) {
            var oldDiv = "#node-div-discuss2-" + attr.selectAnswerId;
            $(oldDiv).css({"background": ''})
        }

        attr.selectAnswerId = id;
        if (discussId != undefined && discussUid != undefined) {
            attr.selectDiscussId = discussId;
            attr.selectDiscussUid = discussUid;
        }

        var div = "#node-div-discuss2-" + id;
        $(div).css({"background": attr.bgColor})
    }

    // 保存讨论
    function saveDiscuss() {

        if (attr.selectUid == undefined) {
            alert("你想用那个帐号来水贴呢");
            return false;
        }

        var content = $("#discuss-textarea-id").val();
        var discussUid = null;
        var discussId = null;
        if (attr.selectDiscussId != undefined && attr.selectDiscussId != undefined) {
            discussUid = attr.selectDiscussUid;
            discussId = attr.selectDiscussId;
        }

        $.ajax({
            type: "POST",
            url: "/theme/add/discuss.json",
            data:{'themeId': attr.selectAnswerId, 'content': content, 'uid' : attr.selectUid, 'discussUid' : discussUid, 'discussId' : discussId},
            dataType:"json",
            success:function (msg) {
                if (msg.code == 9999) {
                    alert(msg.msg);
                } else {
                    alert("水贴成功")
                }
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }

    function discussHandleIsSensitive(discussId) {
        $.ajax({
            type: "POST",
            url: "/discuss/handle/is/sensitive.json",
            data:{'discussId': discussId},
            dataType:"json",
            success:function (msg) {
                if (msg.code == 9999) {
                    alert(msg.msg);
                } else {
                    alert("修改成功");
                }
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////

    //字符串格式化
    function format(str, args) {
        var result = str;
        var length = arguments.length - 1;
        if (length > 0) {
            if (length == 1 && typeof (args) == "object") {
                for (var key in args) {
                    if(args[key]!=undefined){
                        var reg = new RegExp("({" + key + "})", "g");
                        result = result.replace(reg, args[key]);
                    }
                }
            } else {
                for (var i = 1; i < arguments.length; i++) {
                    if (arguments[i] != undefined) {
                        var reg = new RegExp("({[" + i + "]})", "g");
                        result = result.replace(reg, arguments[i]);
                    }
                }
            }
        }
        return result;
    }

    //带html标签的文本 转义 成web识别的文本
    function textToHtml(str) {
        str = $('#text-to-html-id').text(str).html();
        str = str.replace(/ /g, '&nbsp;');
        str = str.replace(/\n/g, '<br/>');
        return str;
    }

    function htmlToText(str) {
        return $('#text-to-html-id').html(str).text();
    }

    //选择水军帐号
    function selectUid(obj) {
        attr.selectUid = $(obj).val();
    }

    function moveRow(obj) {
        $(obj).parent().parent().remove();
    }

    return {
        loadTheme : loadTheme,
        loadAnswer:loadAnswer,
        loadDiscuss : loadDiscuss,

        refreshAnswer : refreshAnswer,
        refreshDiscuss : refreshDiscuss,

        saveAnswer : saveAnswer,
        saveDiscuss : saveDiscuss,
        saveTheme : saveTheme,


        selectTheme : selectTheme,
        selectDiscuss : selectDiscuss,
        selectDiscuss2 : selectDiscuss2,
        selectUid : selectUid,

        insertAnswerContentImg : insertAnswerContentImg,
        insertAnswerContentText : insertAnswerContentText,
        insertThemeContentImg : insertThemeContentImg,
        insertThemeContentText : insertThemeContentText,

        modifyThemeContentText : modifyThemeContentText,
        modifyThemeContentImg : modifyThemeContentImg,

        selectTableRow : selectTableRow,
        moveRow : moveRow,

        nextAnswer : nextAnswer,
        prevAnswer : prevAnswer,
        prevDiscuss : prevDiscuss,
        nextDiscuss : nextDiscuss,

        discussHandleIsSensitive : discussHandleIsSensitive,
        themeHandleIsSensitive : themeHandleIsSensitive
    };
}();