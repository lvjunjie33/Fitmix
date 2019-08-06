<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<style>
    .comment{margin: 0px 20px;color: #993366;}
</style>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <h2>Video 详情</h2>
    </div>
    <form action="/mix/modify.json" method="post" class="uk-form" enctype="multipart/form-data" isform>
        <table class="uk-table uk-table-hover">
            <tbody>
                <tr>
                    <td><label>封面：</label><label><a class="uk-thumbnail uk-thumbnail-mini"><img src="<lch:build-path url="${video.posterUrl}"/>"></a></label></td>
                </tr>
                <tr>
                    <td>
                        <label>视频：</label>
                        <label>
                            <video width="400" height="300" controls>
                                <source src="<lch:build-path url="${video.videoUrl}"/>" type="video/mp4">
                            </video>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><label>编号：</label><label>${video.id}</label></td>
                </tr>
                <tr>
                    <td><label>视频类型：</label>
                        <label>
                            <c:choose>
                                <c:when test="${video.type == 0}">普通视频</c:when>
                                <c:when test="${video.type == 1}">360全景视频</c:when>
                                <c:otherwise>普通视频</c:otherwise>
                            </c:choose>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><label>标题：</label><label>${video.title}</label></td>
                </tr>
                <tr>
                    <td><label>内容：</label><label>${video.content}</label></td>
                </tr>
                <tr>
                    <td><label>视频长度：</label><label><fmt:parseNumber value="${(video.trackLength - video.trackLength % 60) / 60}"/>.${video.trackLength % 60}</label></td>
                </tr>

                <tr>
                    <td><label>添加时间：</label><label><lch:parse-date time="${video.addTime}" pattern="yyyy-MM-dd HH:mm"/></label></td>
                </tr>

            </tbody>
        </table>
        <div class="uk-form-row">
            <a href="/video/video-modify.htm?id=${video.id}" class="uk-button uk-button-danger" video-modify>修改</a>
        </div>
    </form>
</div>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
