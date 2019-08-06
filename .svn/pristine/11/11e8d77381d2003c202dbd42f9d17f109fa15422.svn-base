<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/8/19
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar">
    <c:if test="${mixAlbumsList != null && fn:length(mixAlbumsList) > 0}">
        <div class="sidebar-title">
            <div style="margin:5px auto; text-align: center;color: #FFF;">
                <div style="display: inline-block;vertical-align: top;;">
                    <span class="glyphicon glyphicon-cd"></span>
                    <span>个人专辑(共1个)</span>
                </div>
            </div>
            <div class="albums-cover">
                <div style="display: inline-block;vertical-align: top;;">
                    <img src="${base_uri}/${currentMixAlbums.backImage}">
                </div>
            </div>
            <!-- Split button -->
            <div class="albums-select">
                <div class="btn-group ">
                    <button type="button" class="btn btn-default dropdown-toggle content" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="overflow: hidden;" data-id="${currentMixAlbums.id}">${currentMixAlbums.title}</button>
                    <button type="button" class="btn btn-default dropdown-toggle drop-btn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu">
                        <c:forEach items="${mixAlbumsList}" var="each">
                            <li data-id="${each.id}" data-title="${each.title}"><a href="javascript:void(0);">${each.title}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </c:if>
    <div class="sidebar-menu">
        <c:if test="${mixAlbumsList != null && fn:length(mixAlbumsList) > 0}">
            <ul>
                <li><span class="glyphicon glyphicon-stats"></span>数据中心</li>
                <li><a href="/ugc/data/${user.id}/type/7">数据概览</a></li>
            </ul>
        </c:if>
        <ul>
            <li><span class="glyphicon glyphicon-list-alt"></span>内容管理</li>
            <c:if test="${mixAlbumsList != null && fn:length(mixAlbumsList) > 0}">
                <li><a href="/ugc/content/${user.id}/albums/${currentMixAlbums.id}/mix/upload">音乐上传</a></li>
                <li><a href="/ugc/content/${user.id}/albums/${currentMixAlbums.id}/programs">音乐列表</a></li>
                <li><a href="/ugc/content/${user.id}/albums/${currentMixAlbums.id}/">专辑信息</a></li>
            </c:if>
            <li><a href="/ugc/content/${user.id}/albums">专辑管理</a></li>
        </ul>
        <ul>
            <li><span class="glyphicon glyphicon-user"></span>账户设置</li>
            <li><a href="/ugc/user/${user.id}/info">基本资料</a></li>
            <li><a href="/ugc/user/${user.id}/identity">身份信息</a></li>
            <li><a href="/ugc/user/${user.id}/enter">入驻审核</a></li>
        </ul>
    </div>
</div>

