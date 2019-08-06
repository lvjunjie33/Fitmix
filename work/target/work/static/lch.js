/**
 * Created by Administrator on 2015/4/29.
 */


(function(window){

    var lch = {} || function() {};

    lch.post = function (url, data, fn) {
        $.ajax({
            url : url,
            type : "POST",
            dataType : "JSON",
            data : data,
            success : function (result) {
                var code = result['code'];
                var boolCode = code == 0;
                if (!boolCode) {
                    UIkit.notify(d["msg"], {status:'danger', pos:'top-right'});
                }
                fn(result, boolCode);
            },
            error : function(e) {
                console.info(e);
                UIkit.notify("呀,出错了.", {status:'danger', pos:'top-right'});
            }
        });
    }

    window.lch = lch;
})(window);
