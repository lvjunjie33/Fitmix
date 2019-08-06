<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lc" uri="http://tag.lch.com/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>社区首页</title>
    <base href="${basePath}" />
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <script src="/static/jquery-3.1.0.min.js"></script>
</head>
<body>

    <jsp:include page="../common/head.jsp"></jsp:include>
    <jsp:include page="../common/sub-nav.jsp"></jsp:include>
    <div class="bbs-container">
        <div class="bbs-left">
            <div class="bbs-row">
                <div id="banner" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <c:forEach items="${bannerList}" var="banner" varStatus="status">
                            <li data-target="#banner" data-slide-to="${status.index}" <c:if test="${status.index == 0}">class="active"</c:if> ></li>
                        </c:forEach>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <c:forEach items="${bannerList}" var="banner" varStatus="status">
                            <div data-type-value='${banner.typeValue}'  <c:if test="${status.index == 0}">class="item active"</c:if> <c:if test="${status.index != 0}">class="item"</c:if> >
                                <img src="<lc:build-path url="${banner.backImage}" />" alt="" >
                                <div data-type-value='${banner.typeValue}' class="carousel-caption">
                                    <a href="${banner.typeValue}" target="_blank">${banner.title}</a>
                                </div>
                            </div>
                        </c:forEach>
                        <%--<div class="item">--%>
                            <%--<img src="/imgs/test.jpg" alt="...">--%>
                            <%--<div class="carousel-caption">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
            <div class="bbs-row bbs-rank">
                <div class="bbs-row bbs-rank-title">
                    <ul>
                        <li><span class="bbs-span">冠军榜</span></li>
                        <li><%--城市深林跑现在开始了！--%></li>
                        <li><%--<a href="javascript:void(0);">查看</a>--%></li>
                    </ul>
                </div>
                <div class="bbs-row bbs-rank-box">
                    <c:forEach items="${rankList}" var="rank" varStatus="status">
                        <div class="bbs-rank-item">
                            <div class="bbs-row bbs-rank-icon">
                                <i class="iconfont">&#xe600;</i>
                            </div>
                            <div class="bbs-row bbs-rank-champion">
                                <c:choose>
                                    <c:when test="${rank.type == 1}">本日冠军</c:when>
                                    <c:when test="${rank.type == 2}">本周冠军</c:when>
                                    <c:when test="${rank.type == 3}">本月冠军</c:when>
                                    <c:otherwise>
                                        <c:if test="${status.index == 0}">本日冠军</c:if>
                                        <c:if test="${status.index == 1}">本周冠军</c:if>
                                        <c:if test="${status.index == 2}">本月冠军</c:if>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="bbs-row bbs-rank-avatar">
                                <c:choose>
                                    <c:when test="${rank.user == null}">
                                        <img src="/imgs/default1.png">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<lc:build-path url="${rank.user.avatar}" />">
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <p class="bbs-rank-name">
                                <c:choose>
                                    <c:when test="${rank.user == null}">
                                        暂无数据
                                    </c:when>
                                    <c:otherwise>
                                        ${rank.user.name}
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <p class="bbs-rank-mile"><span><fmt:formatNumber value="${rank.distance / 1000}" pattern="0.#"></fmt:formatNumber> </span>公里</p>
                        </div>
                    </c:forEach>
                    <div class="bbs-rank-item">
                        <div class="bbs-row bbs-rank-icon">
                            <i class="iconfont">&#xe600;</i>
                        </div>
                        <div class="bbs-row bbs-rank-champion">
                            单曲冠军
                        </div>
                        <div class="bbs-row bbs-rank-avatar">
                            <img src="<lc:build-path url="${mix.albumUrl}" />">
                        </div>
                        <p id="mixRankName" title="${mix.name}" class="bbs-rank-name">${mix.name}</p>
                        <p class="bbs-rank-mile">试听数<span>${mix.auditionCount + mix.baseAuditionCount}</span></p>
                    </div>
                </div>
            </div>
            <c:forEach items="${recommendList}" var="recommend">
                <div class="bbs-row bbs-new">
                    <div class="bbs-new-banner">
                        <a target="_blank"
                           <c:if test="${recommend.type == 1}">href="bbs/article/${recommend.typeValue}"</c:if>
                           <c:if test="${recommend.type == 2}">href="${recommend.typeValue}"</c:if>
                        >
                            <img src="<lc:build-path url="${recommend.backImage}" />" />
                            <span class="bbs-span">${recommend.cornerMark}</span>
                        </a>
                    </div>
                    <div class="bbs-new-content">
                        <div class="bbs-new-title">
                            <a target="_blank"
                              <c:if test="${recommend.type == 1}">href="bbs/article/${recommend.typeValue}"</c:if>
                              <c:if test="${recommend.type == 2}">href="${recommend.typeValue}"</c:if>
                            >
                              ${recommend.title}
                            </a>
                        </div>
                        <div class="bbs-new-tag">发布时间：<lc:parse-date time="${recommend.addTime}" pattern="yyyy-MM-dd"></lc:parse-date></div>
                        <div class="bbs-new-desc">${recommend.desc}</div>
                    </div>
                </div>
            </c:forEach>
            <%--<div class="bbs-row bbs-new">--%>
                <%--<div class="bbs-new-banner">--%>
                    <%--<img src="/imgs/detail_02_1.jpg" />--%>
                    <%--<span class="bbs-span">跑团趣事</span>--%>
                <%--</div>--%>
                <%--<div class="bbs-new-content">--%>
                    <%--<div class="bbs-new-title">天天跑嗷嗷嗷嗷嗷嗷扫地是是电风扇达峰士大夫</div>--%>
                    <%--<div class="bbs-new-tag">乐享动咨询|活动时间：2046.06.06</div>--%>
                    <%--<div class="bbs-new-desc">我们每天是空的上的看法上的看法拉斯的饭卡打对方理解啊代理费就爱看了加拉倒爱对方就安静的发酵法</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="bbs-row bbs-article">
                <div class="bbs-article-head"><span class="bbs-span">文章热帖</span><a href="/bbs/article/page">更多</a> </div>
                <div class="bbs-article-box">
                    <c:forEach items="${articleList}" var="article">
                        <a class="bbs-article-item" href="bbs/article/${article.id}" target="_blank">
                            <div class="bbs-article-banner">
                                <img src="<lc:build-path url="${article.coverUrl}"/>" />
                            </div>
                            <div class="bbs-article-content">
                                <div class="bbs-article-title">${article.title}</div>
                                <div class="bbs-article-tag">
                                    ${article.category} | 发布时间：<lc:parse-date time="${article.addTime}" pattern="yyyy-MM-dd"></lc:parse-date>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
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
    <script src="/static/bootstrap/dist/js/bootstrap.min.js"></script>
    <script>
        $(function(){
            $("#banner").on("click", ".item", function() {
                window.open($(this).data("type-value"));
            });
            if($("#mixRankName").text().length > 11) {
                var name = $("#mixRankName").text().substring(0, 10) + "...";
                $("#mixRankName").text(name);
            };
        });
    </script>
</body>
</html>
