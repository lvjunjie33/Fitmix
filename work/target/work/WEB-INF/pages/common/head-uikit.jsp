<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/3/30
  Time: 下午7:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ include file="head-taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "s://" + request.getServerName() + "/";
    String basePathDev = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
      // 处理 上线 版本
//    if (request.getServerName().matches("^(\\d.*|localhost)$")) {
//          basePath = basePathDev;
//    }

%>

<!DOCTYPE html>
<html>
    <head>
        <%--<base href="<%=basePathDev%>">--%>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%--4.IE渲染模式--%>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <%--      5.强制双核浏览器以webkit内核打开页面--%>
        <meta name="renderer" content="webkit">
        <title>lch</title>

        <%--  core  --%>
        <link rel="stylesheet" href="/static/uikit/css/uikit.min.css">
        <link rel="stylesheet" href="/static/uikit/css/uikit.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/uikit.almost-flat.min.css">

        <%--  components --%>
        <link rel="stylesheet" href="/static/uikit/css/components/accordion.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/accordion.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/accordion.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/autocomplete.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/autocomplete.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/autocomplete.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/datepicker.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/datepicker.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/datepicker.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/dotnav.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/dotnav.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/dotnav.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/dotnav.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-advanced.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-advanced.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-advanced.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-file.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-file.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-file.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-password.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-password.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-select.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-select.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/form-select.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/htmleditor.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/htmleditor.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/htmleditor.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/nestable.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/nestable.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/nestable.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/notify.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/notify.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/notify.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/placeholder.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/placeholder.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/placeholder.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/progress.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/progress.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/progress.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/search.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/search.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/search.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/slidenav.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/slidenav.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/slidenav.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/slideshow.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/slideshow.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/slideshow.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/sortable.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/sortable.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/sortable.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/sticky.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/sticky.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/sticky.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/tooltip.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/tooltip.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/tooltip.gradient.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/upload.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/upload.almost-flat.min.css">
        <link rel="stylesheet" href="/static/uikit/css/components/upload.gradient.min.css">


        <%--  core  --%>
        <script src="/static/jquery-1.11.2.min.js"></script>
        <script src="/static/uikit/js/uikit.min.js"></script>

        <%--  components  --%>
        <script src="/static/uikit/js/components/accordion.min.js"></script>
        <script src="/static/uikit/js/components/autocomplete.min.js"></script>
        <script src="/static/uikit/js/components/datepicker.min.js"></script>
        <script src="/static/uikit/js/components/form-password.min.js"></script>
        <script src="/static/uikit/js/components/form-select.min.js"></script>
        <script src="/static/uikit/js/components/grid.min.js"></script>
        <script src="/static/uikit/js/components/htmleditor.min.js"></script>
        <script src="/static/uikit/js/components/lightbox.min.js"></script>
        <script src="/static/uikit/js/components/nestable.min.js"></script>
        <script src="/static/uikit/js/components/notify.min.js"></script>
        <script src="/static/uikit/js/components/pagination.min.js"></script>
        <script src="/static/uikit/js/components/search.min.js"></script>
        <script src="/static/uikit/js/components/slideshow.min.js"></script>
        <script src="/static/uikit/js/components/slideshow-fx.min.js"></script>
        <script src="/static/uikit/js/components/sortable.min.js"></script>
        <script src="/static/uikit/js/components/sticky.min.js"></script>
        <script src="/static/uikit/js/components/timepicker.min.js"></script>
        <script src="/static/uikit/js/components/tooltip.min.js"></script>
        <script src="/static/uikit/js/components/upload.min.js"></script>


        <script type="text/javascript" src="/static/core.js"></script>
        <script type="text/javascript" src="/static/lch.js"></script>
        <script type="text/javascript" src="/static/jquery.form.js"></script>


        <%--    popover    --%>
        <link rel="stylesheet" href="/static/popover/popover.css">
        <script type="text/javascript" src="/static/popover/tooltip.js"></script>
        <script type="text/javascript" src="/static/popover/popover.js"></script>

        <script type="text/javascript" src="/static/angular/angular.min.js"></script>

        <%-- wangEditor --%>
        <link rel="stylesheet" href="/static/wangEditor/css/wangEditor.min.css">
        <script type="text/javascript" src="/static/wangEditor/wangEditor.min.js"></script>
    </head>
    <body>


