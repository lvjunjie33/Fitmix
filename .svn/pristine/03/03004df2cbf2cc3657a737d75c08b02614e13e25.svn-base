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
    <title>社区动态</title>
    <base href="${basePath}" />
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <script src="/static/jquery-3.1.0.min.js"></script>

</head>
<body>
    <jsp:include page="../common/head.jsp"></jsp:include>
    <jsp:include page="../common/sub-nav.jsp"></jsp:include>

    <div class="bbs-container">
        <div class="bbs-left">
            <div class="bbs-row bbs-rank" style="margin-top: 0px;">
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
            <div class="bbs-row bbs-friend-dynamic">
                <ul>
                    <li><span class="bbs-span">跑友动态</span></li>
                    <li>城市深林跑现在开始了！</li>
                    <li><a href="javascript:void(0);">查看</a></li>
                </ul>
            </div>
            <div class="bbs-row bbs-dynamic-box">
                <%--<div class="bbs-row bbs-dynamic-item">--%>
                    <%--<div class="bbs-row bbs-dynamic-user">--%>
                        <%--<img src="/imgs/albums-cover.jpg" />--%>
                        <%--<p>JK123</p>--%>
                    <%--</div>--%>
                    <%--<div class="bbs-row bbs-dynamic-time">--%>
                        <%--发布时间：2046.06.06--%>
                    <%--</div>--%>
                    <%--<div class="bbs-row bbs-dynamic-content">--%>
                        <%--圣诞快乐啊阿拉大多啦啊的开发拉大了--%>
                        <%--<img src="/imgs/detail_02_1.jpg" />--%>
                    <%--</div>--%>
                    <%--<div class="bbs-row bbs-dynamic-menu">--%>
                        <%--<span class="bbs-dynamic-flower"><i class="iconfont">&#xe602;</i> 26132</span>--%>
                        <%--<span class="bbs-dynamic-comment"><i class="iconfont">&#xe601;</i> 230 </span>--%>
                    <%--</div>--%>
                    <%--<div class="bbs-row bbs-dynamic-comment-list">--%>
                        <%--<div class="bbs-row bbs-dynamic-comment-item">--%>
                            <%--<div class="user">--%>
                                <%--<img src="/imgs/albums-cover.jpg"/>--%>
                            <%--</div>--%>
                            <%--<div class="content"><span>JKL &nbsp;:</span>asdfla dfa dfalsd s思考是代理商的是抗生素的我日而阿拉啦海景房的 奥斯卡 老师的爱上的开发fasjdfalsd fjladfjas dlfalsdfladf'ad;flad fald;fja sdfl adsf</div>--%>
                            <%--<div class="date"><span>2046.06.06</span></div>--%>
                        <%--</div>--%>

                        <%--<div class="bbs-row bbs-dynamic-comment-item">--%>
                            <%--<div class="user">--%>
                                <%--<img src="/imgs/albums-cover.jpg"/>--%>
                            <%--</div>--%>
                            <%--<div class="content"><span>JKL &nbsp;:</span>asdfla dfa dfalsd fasjdfalsd fjladfjas dlfalsdfladf'ad;flad fald;fja sdfl adsf</div>--%>
                            <%--<div class="date"><span>2046.06.06</span></div>--%>
                        <%--</div>--%>

                        <%--<div class="bbs-row bbs-dynamic-comment-item">--%>
                            <%--<div class="user">--%>
                                <%--<img src="/imgs/albums-cover.jpg"/>--%>
                            <%--</div>--%>
                            <%--<div class="content"><span>JKL &nbsp;:</span>asdfla dfa dfalsd fasjdfalsd fjladfjas dlfalsdfladf'ad;flad fald;fja sdfl adsf</div>--%>
                            <%--<div class="date"><span>2046.06.06</span></div>--%>
                        <%--</div>--%>

                        <%--<div class="bbs-row bbs-dynamic-comment-item">--%>
                            <%--<div class="input-group">--%>
                                <%--<input type="text" class="form-control">--%>
                            <%--<span class="input-group-btn">--%>
                                <%--<button class="btn bbs-input-btn" type="button">回复</button>--%>
                            <%--</span>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                    <%--</div>--%>

                <%--</div>--%>
            </div>
            <div id="more" class="bbs-row"></div>
        </div>
        <div class="bbs-right">
            <jsp:include page="../common/time.jsp"></jsp:include>
            <%--<div class="bbs-row bbs-ad-box">--%>
                <%--<div class="bbs-ad-content">--%>
                    <%--广告位--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="bbs-row bbs-sport-new">--%>
                <%--<h2>运动早知道</h2>--%>
                <%--<p>如何跑步更持久？</p>--%>
                <%--<p>如何跑步更持久？如何跑步更持久？如何跑步更持久？如何跑步更持久？如何跑步更持久？如何跑步更持久？如何跑步更持久？</p>--%>
                <%--<p>如何跑步更持久？</p>--%>
            <%--</div>--%>
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
    <script src="/js/DateUtil.js"></script>
    <script src="/js/main.js"></script>
    <script>
        BBS.dynamic.init();
    </script>
</body>
</html>
