<%@ taglib prefix="lc" uri="http://tag.lch.com/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>社区文章详情</title>
    <base href="${basePath}" />
    <link rel="stylesheet" type="text/css" href="/css/main.css">

</head>
<body>
    <jsp:include page="../common/head.jsp"></jsp:include>
    <jsp:include page="../common/sub-nav.jsp"></jsp:include>

    <div class="bbs-container">
        <div class="bbs-left">
            <div class="bbs-row bbs-article-info" style="margin-top: 0px;">
                <div class="bbs-article-title">
                    ${article.title}
                    <div class="bbs-row bbs-article-tag">${article.category}<span class="vl">|</span>发布时间:<lc:parse-date time="${article.addTime}" pattern="yyyy-MM-dd"></lc:parse-date><span class="vl">|</span>浏览量:${article.viewCount}</div>
                </div>
                <div class="bbs-article-content">
                    <%--<img src="/imgs/detail_02_1.jpg" />--%>
                    ${article.content}
                </div>
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

    <jsp:include page="../common/footer.jsp"></jsp:include>
    <script src="/static/jquery-3.1.0.min.js"></script>
    <script src="/static/bootstrap/dist/js/bootstrap.min.js"></script>

</body>
</html>
