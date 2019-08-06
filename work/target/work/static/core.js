
///
/// init js

$(function(){
    validate();

    window.nc.template.init();
});


///
/// 重写 String.format


String.prototype.format = function(args) {
    if (arguments.length>0) {
        var result = this;
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                var reg=new RegExp ("({"+key+"})","g");
                result = result.replace(reg, args[key]);
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if(arguments[i]==undefined)
                {
                    return "";
                }
                else
                {
                    var reg=new RegExp ("({["+i+"]})","g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
        return result;
    }
    else {
        return this;
    }
};


///
/// 验证


// error class
$.extend({
    errorAlertClass : "uk-form-danger"
});

// 是否添加 error style
$.fn.extend({
    errorAlert : function (bool) {
        if (bool) {
            $(this).addClass($.errorAlertClass);
        }
        else {
            $(this).removeClass($.errorAlertClass);
        }
        $(this).errorText();
    }
});

// 是否添加 error text 提示
$.fn.extend({
    errorText : function () {
        var th = $(this);
        th.parent().find("[error='']").remove();
        if (th.parent().find("." + $.errorAlertClass).size()) {
            var text = th.attr("error-text");
            if (text) {
                var errorHtml = '<label style="color: #ff5642;" error>{text}</label>'.format({text : text});
                th.parent().append(errorHtml);
            }
        }
    }
});

// 验证 core
var validate = function () {
    var data = [
        {name : "notnull", trigger : ["blur", "keyup"], fn : notnull},
        {name : "isnumber", trigger : ["blur", "keyup"], fn : isnumber}
    ];

    $.each(data, function (i, json) {
        $.each(json['trigger'], function (j, tri) {
            $("[" + json['name'] + "='']").bind(tri, json['fn'])
        });
    });

    validate.trigger = function () {
        $.each(data, function (i, json) {
            $.each(json['trigger'], function (j, tri) {
                $("[" + json['name'] + "='']").trigger(tri);
            });
        });
    };

    function notnull () {
        $(this).errorAlert(/^\s*$/.test($(this).val()));
    }

    function isnumber () {
        $(this).errorAlert(!(/^\d+((\.\d+)|(\d*))$/.test($(this).val())));
    }

    $("[isform]").submit(function(){
        var form = $(this);
        validate.trigger(form);
        var errorEl = $(this).find("." + $.errorAlertClass + ":not(:hidden)");
        if (errorEl.size() > 0) {
            UIkit.notify(data, {status:'danger', pos:'top-right'});
            return false;
        }
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
                        location.reload();
                    }
                }
                else {
                    form.find("button,input[type=button]").removeAttr("disabled");
                    UIkit.notify(d["msg"], {status:'danger', pos:'top-right'});
                }
            }
        });
        return false;
    });
};
window.validate = validate;


///
/// nc 定义


(function(window){
    var nc = {};
    window.nc = nc;
})(window);


///
/// Template 模版控制


(function(window){
    var template = {
        data : {},
        init : function () {
            $("[tem]").each(function (index, tem) {
                tem = $(tem);
                template.data[tem.attr("tem")] = {el : tem, name : tem.attr("tem"), html : tem.text()};
            });
        }
    }
    window.nc.template = template;
})(window);


///
/// 自动 json


$.fn.extend({
    sequenceJson:function(){
        var json={};
        $.each($(this).find("*[name]"),function(i, el){
            if($(this).attr("fill") != "false" && !(/^\s*$/.test($(this).attr("name")))){
                var k = $.trim($(this).attr("name"));
                var v = $.trim($.sequenceRule($(this)[0].tagName, $(this)));
                json[k] = v;
            };
        });
        return json;
    }
});

jQuery.extend({
    sequenceRule:function(k,o){
        switch (k) {
            case "INPUT":
            case "SELECT":
            case "TEXTAREA":
                return o.val();
            case "TR":
            case "TD":
            case "H1":
            case "H2":
            case "H3":
            case "H4":
            case "H5":
            case "H6":
            case "DIV":
            case "SPAN":
            case "LABEL":
            case "LI":
            case "HEAD":
                return o.text();
            case "IMG":
            case "SCRIPT":
                return o.attr("src");
        }
    }
});

$.fn.extend({
    fillNode : function (json) {
        $.each($(this).find("[name]"), function (i, node) {
            var k = $(this).attr("name");
            $.fillRule($(this), json[k]);
        });
    }
});

$.extend({
    fillRule:function(el, v){
        switch (el[0].tagName) {
            case "INPUT":
            case "SELECT":
            case "TEXTAREA":
                return el.val(v);
            case "TR":
            case "TD":
            case "H1":
            case "H2":
            case "H3":
            case "H4":
            case "H5":
            case "H6":
            case "DIV":
            case "SPAN":
            case "LI":
            case "HEAD":
                return el.text(v);
            case "IMG":
            case "SCRIPT":
                return el.attr("src", v);
        }
    }
});

///
/// str 数组转 : 字符串格式的 str [1,2,3] or 1,2,3

$.extend({
    strToArray : function (str) {
        return $.trim(str.replace("[", "").replace("]", "")).split(",");
    }
});


///
/// 数组转 json

$.extend({
    arrayToMapJSON : function (array) {
        var json = {};
            $.each(array, function (i, data) {
                json[$.trim(data)] = $.trim(data);
            });
        return json;
    }
});


