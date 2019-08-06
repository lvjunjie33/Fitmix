<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/9/21
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.business.core.entity.SysParam" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>
    <jsp:include page="/WEB-INF/pages/common/login-path.jsp"/>
    <script type="text/javascript">
        //切割字符串转换参数表
        function toParamMap(str) {
            var map = {};
            var segs = str.split("&");
            for (var i in segs) {
                var seg = segs[i];
                var idx = seg.indexOf('=');
                if (idx < 0) {
                    continue;
                }
                var name = seg.substring(0, idx);
                var value = seg.substring(idx + 1);
                map[name] = value;
            }
            return map;
        }

        //隐式获取url响应内容(JSONP)
        function openImplict(url) {
            var script = document.createElement('script');
            script.src = url;
            document.body.appendChild(script);
        }

    </script>
</head>
<body>
<!-- 执行脚本 -->
<script type="text/javascript">

    //应用的APPID
    var appID = '<%=SysParam.INSTANCE.WEB_LOGIN_WX_KEY%>';

    //登录授权后的回调地址，设置为当前url
    var redirectURI = '<%=SysParam.INSTANCE.WEB_BIND_WX_REDIRECTURL%>';

    //初始化构造请求
    if (window.location.search.length == 0) {
        var path = 'https://open.weixin.qq.com/connect/qrconnect?';
        var queryParams = ['appid=' + appID,
            'redirect_uri=' + redirectURI,
            'response_type=code',
            'scope=snsapi_login',
            'state=true'];
        var query = queryParams.join('&');
        var url = path + query;
        window.location.href = url;
    }
    //在成功授权后回调时location.search将code发送到后台，获取access_token、openid
    else {
        //获取code
        var code = window.location.search.substring(1);
        var map = toParamMap(code);

        if(map.code == null || map.code == "") {
            window.location.href = '/web-user/login-page.htm';
        } else {
            $.ajax({
                type: "POST",
                async: true,
                dataType: "json",
                url: "/web-user/other-binding.json",
                data: {
                    code: map.code,
                    _lan: _lan,
                    _ch: _ch,
                    _v: _v,
                    _nw: _nw,
                    _sdk: _sdk
                },
                success: function (data) {
                    if (data.code == 0) {
                        alert("绑定成功！");
                        window.opener.location.href = window.opener.location.href;
                        if (window.opener.progressWindow)
                        {
                            window.opener.progressWindow.close();
                        }
                        window.close();
                    } else {
                        alert(data.msg);
                        window.opener.location.href = window.opener.location.href;
                        if (window.opener.progressWindow)
                        {
                            window.opener.progressWindow.close();
                        }
                    }
                }
            });
        }
    }


</script>
</body>
</html>

