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
        <h2>Mix 详情</h2>
    </div>
    <form action="/mix/modify.json" method="post" class="uk-form" enctype="multipart/form-data" isform>
        <table class="uk-table uk-table-hover">
            <tbody>
                <tr>
                    <td><label>图片：</label><label><a class="uk-thumbnail uk-thumbnail-mini"><img src="<lch:build-path url="${mix.albumUrl_2}"/>"></a></label></td>
                </tr>
                <tr>
                    <td>
                        <label>音乐：</label>
                        <label>
                            <audio controls>
                                <source src="<lch:build-path url="${mix.url}"/>" type="audio/mpeg">
                            </audio>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <pre><lch:build-path url="${mix.url}"/><span class="comment">&lt;!-- 音乐地址 --&gt;</span> </pre>
                        <pre><lch:build-path url="${mix.albumUrl}"/><span class="comment">&lt;!-- 图片地址 --&gt;</span> </pre>
                        <pre><lch:build-path url="${mix.albumUrl_2}"/><span class="comment">&lt;!-- 图片地址（小）--&gt;</span> </pre>
                    </td>
                </tr>
                <tr>
                    <td><label>编号：</label><label>${mix.id}</label></td>
                </tr>
                <tr>
                    <td><label>类型：</label><label>
                        <c:choose>
                            <c:when test="${mix.type == 2}">
                                电台
                            </c:when>
                            <c:otherwise>
                                运动歌单
                            </c:otherwise>
                        </c:choose>
                    </label></td>
                </tr>
                <tr>
                    <td><label>名称：</label><label>${mix.name}</label></td>
                </tr>
                <tr>
                    <td><label>作者：</label><label>${mix.mixAuthor.name}</label></td>
                </tr>
                <tr>
                    <td><label>专辑：</label><label>${mix.albumName}</label></td>
                </tr>
                <tr>
                    <td><label>歌长：</label><label>${mix.trackLength / 60}</label></td>
                </tr>

                <tr>
                    <td>
                        <label>bpm：</label>
                        <label>${mix.bpm}</label>
                    </td>
                </tr>
                <tr>
                    <td><label>场景：</label><label><lch:dic-name-array type="2" value="${mix.scene}" split="&nbsp;&nbsp;&nbsp;"/></label></td>
                </tr>
                <tr>
                    <td><label>曲风：</label>
                        <c:if test="${not empty mix.genre}">
                            <lch:build-genre-parent genre="${mix.genre}" split="&nbsp;&nbsp;"
                                                    label="<label class=\"mix-scene\" data=\"%s\">%s</label>"/>
                            (<lch:dic-option type="7" values="${mix.genre}" split=",&nbsp;&nbsp;" label="<label class=\"mix-scene\" data=\"%s\">%s</label>"/>)
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td><label>下载次数：</label><label>${mix.downloadCount}</label></td>
                </tr>
                <tr>
                    <td><label>添加时间：</label><label><lch:parse-date time="${mix.addTime}" pattern="yyyy-MM-dd HH:mm"/></label></td>
                </tr>
                <tr>
                    <td><label>上架时间：</label><label><lch:parse-date time="${mix.shelvesTime}" pattern="yyyy-MM-dd HH:mm"/></label></td>
                </tr>
                <tr>
                    <td><label>歌曲介绍：</label><label>${mix.introduce}</label></td>
                </tr>
                <tr>
                    <td>
                        <label>配置：</label>
                    </td>
                </tr>
                <c:forEach var="mixMusics" items="${mix.mixMusics}">
                    <tr>
                        <td>
                            <div class="uk-grid">
                                <div class="uk-width-1-2">
                                    <label>${mixMusics.name}&nbsp;&nbsp;&nbsp;</label>
                                </div>
                                <div class="uk-width-1-2">
                                    <label>${mixMusics.position / 60}&nbsp;(m)</label>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

                <tr><td></td></tr>

                <tr>
                    <td><label>下载次数：</label><label>${mix.downloadCount}</label></td>
                </tr>
                <tr>
                    <td><a href="#offset-set" data-uk-modal
                           onclick="setIniVal(${mix.id}, 1, '设置下载偏移量')">下载偏移量：</a><label>${mix.dOffsetNum}</label></td>
                </tr>

                <tr><td></td></tr>

                <tr>
                    <td><label>分享次数：</label><label>${mix.shareCount}</label></td>
                </tr>
                <tr>
                    <td><a href="#offset-set" data-uk-modal
                           onclick="setIniVal(${mix.id}, 2, '设置分享偏移量')">分享偏移量：</a><label>${mix.sOffsetNum}</label></td>
                </tr>

                <tr><td></td></tr>

                <tr>
                    <td><label>试听次数：</label><label>${mix.auditionCount}</label></td>
                </tr>
                <tr>
                    <td><a href="#offset-set" data-uk-modal
                           onclick="setIniVal(${mix.id}, 3, '设置试听偏移量')">试听偏移量：</a><label>${mix.aOffsetNum}</label></td>
                </tr>

                <tr><td></td></tr>

                <tr>
                    <td><label>基础试听次数：</label><label>${mix.baseAuditionCount}</label></td>
                </tr>
                <tr>
                    <td><a href="#offset-set" data-uk-modal
                           onclick="setIniVal(${mix.id}, 4, '设置基础试听偏移量')">基础试听偏移量：</a><label>${mix.bOffsetNum}</label></td>
                </tr>

                <tr><td></td></tr>

                <tr>
                    <td><label>收藏次数：</label><label>${mix.collectNumber}</label></td>
                </tr>
                <tr>
                    <td><a href="#offset-set" data-uk-modal
                           onclick="setIniVal(${mix.id}, 5, '设置收藏偏移量')">收藏偏移量：</a><label>${mix.cOffsetNum}</label></td>
                </tr>

            </tbody>
        </table>
        <div class="uk-form-row">
            <a href="/mix/mix-modify.htm?id=${mix.id}" class="uk-button uk-button-danger" mix-modify>修改</a>
            <a href="/mix-comment/mix-comment-page.htm?mid=${mix.id}" class="uk-button uk-button-danger" mix-modify>评论</a>
        </div>
    </form>
</div>

<div id="offset-set" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 id = "id-H1"></h1>
        <div class="uk-form-row">
            <form action="/mix/set/offset.json" method="post" class="uk-form" id="offset-set-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="offsetNum" placeholder="偏移量" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="type" value="">
                    <input type="hidden" name="id" value="">
                    <button class="uk-button uk-button-primary" id="offset-set-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function setIniVal(id, type, title) {
        $("#id-H1").text(title);
        var $from = $("#offset-set-form");
        $from.find("[name=type]").val(type);
        $from.find("[name=id]").val(id);
    }
</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
