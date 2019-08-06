<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/8/18
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/head-base-path.jsp"%>
<html>
<head>
    <title>UGC</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet/less" type="text/css" href="../../../css/common/main.less" />
    <script src="../../../static/less/less.min.js"></script>


</head>
<body>
<jsp:include page="../common/head.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="../common/sidebar.jsp"></jsp:include>
        <div class="col-md-12">
            <h4 class="col-md-10 col-md-push-2 page-header"> 数据概览 </h4>
            <div class="col-md-10 col-md-push-2">
                <div class="data-box row">
                    <div class="data-box-title">
                        昨日数据
                    </div>

                    <div class="col-md-7">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <td colspan="2">收听次数</td>
                                    <td colspan="2">收听人数</td>
                                    <td colspan="2">新增人数</td>
                                </tr>
                                <tr>
                                    <td colspan="2" id="yesterday1">14</td>
                                    <td colspan="2" id="yesterday2">14</td>
                                    <td colspan="2" id="yesterday3">14</td>
                                </tr>
                                <tr>
                                    <td colspan="2">次日留存率</td>
                                    <td colspan="4">累计收藏人数</td>
                                </tr>
                                <tr>
                                    <td colspan="2" id="yesterday4">0.0%</td>
                                    <td colspan="4" id="yesterday5">1609</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-5">
                        <div class="data-desc">
                            <p>
                                收听次数：&nbsp;昨日本专辑节目被播放的次数<br>
                                收听人数：&nbsp;昨日收听本专辑的人数<br>
                                新增人数：&nbsp;昨日本专辑新增的听众人数<br>
                                次日留存率：&nbsp;新增听众第二天再次收听本专辑的比例<br>
                                收藏人数：&nbsp;昨日收藏本专辑的人数
                            </p>
                        </div>
                    </div>
                </div>
                <div class="data-box row">
                    <div class="data-box-title">
                        数据趋势
                        <div class="data-box-date" style="margin-top: -3px;">
                            <a href="javascript:void(0)" class="select-date date-active" data-day="7">过去7天</a>
                            <span style="margin: 0 2px; color: gray;font-size: 14px;">|</span>
                            <a href="javascript:void(0)" class="select-date" data-day="30">过去30天</a>
                        </div>
                    </div>
                    <div class="col-md-7 data-box-content">
                        <div class="data-category">
                            <ul class="nav-pills nav-justified nav-category" role="tablist">
                                <li role="presentation" data="0" class="category">
                                    <a class="chart-category active" href="javascript:void(0);">收听次数</a>
                                </li>
                                <li role="presentation" data="1" class="category">
                                    <a class="chart-category" href="javascript:void(0);">收听人数</a></li>
                                <li role="presentation" data="2" class="category">
                                    <a class="chart-category" href="javascript:void(0);">新增人数</a>
                                </li>
                                <li role="presentation" data="3" class="category">
                                    <a class="chart-category" href="javascript:void(0);">次日留存率</a>
                                </li>
                                <li role="presentation" data="4" class="category category-active">
                                    <a class="chart-category" href="javascript:void(0);">收藏人数</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="data-desc">
                            <p>
                                收听次数：&nbsp;昨日本专辑节目被播放的次数<br>
                                收听人数：&nbsp;昨日收听本专辑的人数<br>
                                新增人数：&nbsp;昨日本专辑新增的听众人数<br>
                                次日留存率：&nbsp;新增听众第二天再次收听本专辑的比例<br>
                                收藏人数：&nbsp;昨日收藏本专辑的人数
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<jsp:include page="../common/footer.jsp"></jsp:include>--%>
<script src="../../../static/jquery-3.1.0.min.js"></script>
<script src="../../../static/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../../js/common/sidebar.js"></script>
<script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
<script src="../../../js/common/Util.js"></script>
<script src="../../../js/common/js.cookie.js"></script>
<script>
    //初始化
    var uid = ${user.id};
    Sidebar.init("^/ugc/data/(\\S){1,}/type/(\\S){1,}$");
    //是否提示跳转身份验证框
    var identity = Cookies.get("identity_dialog");
    if(typeof identity == 'undefined') {
        Cookies.set("identity_dialog", new Date().getTime(), { expires: 1});
        Util.identityDialog(${user.userIdentityInfo.checkStatus}, ${user.id});
    }
</script>


</body>
</html>
