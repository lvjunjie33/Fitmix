<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lc" uri="http://tag.lch.com/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/9/7
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/head-base-path.jsp"></jsp:include>
<html>
<head>
    <title>社区文章列表</title>
    <base href="${basePath}" />
    <link rel="stylesheet" type="text/css" href="/css/main.css">

</head>
<body>
    <jsp:include page="../common/head.jsp"></jsp:include>
    <jsp:include page="../common/sub-nav.jsp"></jsp:include>

    <div class="bbs-container">
        <div class="bbs-left">
            <div class="bbs-row bbs-article" style="margin-top: 0px;">
                <div class="bbs-article-head">
                    <span class="bbs-span">文章热帖</span>
                    <%--<a href="javascript:void(0);">推荐</a>--%>
                    <%--<a class="vl">|</a>--%>
                    <%--<a href="javascript:void(0);">热门</a>--%>
                </div>
                <div class="bbs-article-box">
                    <%--<div class="bbs-article-item" data-article-id="1">--%>
                        <%--<div class="bbs-article-banner">--%>
                            <%--<img src="/imgs/detail_02_1.jpg" />--%>
                        <%--</div>--%>
                        <%--<div class="bbs-article-content">--%>
                            <%--<div class="bbs-article-title">天天跑啊啊啊啊爱的力量上的看法大家快来收的</div>--%>
                            <%--<div class="bbs-article-desc">啥地方开始的家连锁店 就是打开</div>--%>
                            <%--<div class="bbs-article-tag">--%>
                                <%--乐享动咨询|活动时间：2046.06.06--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
            </div>
            <div id="more" class="bbs-row">

            </div>

        </div>
        <div class="bbs-right">
            <jsp:include page="../common/time.jsp"></jsp:include>
            <c:forEach items="${advertisementList}" var="adv">
                <div class="bbs-row bbs-ad-box">
                    <div class="bbs-ad-content">
                        <a href="${adv.url}" target="_blank">
                            <img src="<lc:build-path url="${adv.imageUrl}"/> ">
                        </a>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${sportList != null}">
                <div class="bbs-row bbs-sport-new">
                    <h2>运动早知道</h2>
                    <c:forEach items="${sportList}" var="s">
                        <p><a target="_blank" href="/bbs/article/${s.id}">${s.title}</a></p>
                    </c:forEach>
                </div>
            </c:if>
            <div class="bbs-row bbs-wx">
                微信公众号
            </div>
            <div class="bbs-row bbs-wx-erweima">
                <img src="/imgs/erweima.jpg" />
            </div>
        </div>
    </div>

    <div id="articleItemTemplate" style="display: none;">
        <div class="bbs-article-item" data-article-id="#id#">
            <div class="bbs-article-banner">
                <img src="#coverUrl#" />
            </div>
            <div class="bbs-article-content">
                <div class="bbs-article-title">#title#</div>
                <div class="bbs-article-desc">#desc#</div>
                <div class="bbs-article-tag">
                    #category# | 发布时间：#addTime#
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp"></jsp:include>
    <script src="/static/jquery-3.1.0.min.js"></script>
    <script src="/static/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="/js/DateUtil.js"></script>
    <script src="/js/main.js"></script>
    <script>
        BBS.article.init();
    </script>
</body>
</html>
