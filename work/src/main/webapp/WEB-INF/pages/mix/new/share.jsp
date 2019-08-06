<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/2/6
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head.jsp" %>

<div class="uk-container-center uk-body">

    <div class="uk-form-row">
        <form action="/get/song/list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>BPM：</label>
                    <label class="mix-scene"><input type="number" name="bpm" value="${bpm}" placeholder="BPM"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>时长：</label>
                    <label class="mix-scene"><input type="number" name="duration" value="${duration}" placeholder="时长"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>情绪：</label>
                    <label class="mix-scene"><input type="number" name="emotion" value="${emotion}" placeholder="情绪"></label>
                    <button type="submit" class="uk-button uk-button-primary">获取歌单</button>
                </div>
            </div>
        </form>
    </div>

    <hr class="uk-article-divider">

    <%--<div style="border: 1px solid #D9FFF1;text-align: center">--%>
        <%--全部播放--%>
        <%--<br/>--%>
        <%--<audio controls="controls" src="http://yyssb.ifitmix.com/2112/b17695fbba8c41e3bcd4a9ae5b9b45eb.m4a"></audio>--%>
    <%--</div>--%>
    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr>
                <th style="width: 50px;">编号</th>
                <th style="width: 50px;">曲风</th>
                <th style="width: 50px;">情绪</th>
                <th style="width: 50px;">BPM</th>
                <th style="width: 50px;">时长(秒)</th>
                <th style="width: 50px;">能量级别</th>
                <th style="width: 50px"></th>
            </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${!empty message}">
                        <tr>
                            <td colspan="7" style="text-align: center;background-color: red">${message}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="mix" items="${list}" varStatus="status">
                            <tr>
                                <td>${status.index}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${mix.songStyle eq 1}">
                                            流行
                                        </c:when>
                                        <c:when test="${mix.songStyle eq 2}">
                                            流行摇滚
                                        </c:when>
                                        <c:when test="${mix.songStyle eq 3}">
                                            电子
                                        </c:when>
                                        <c:when test="${mix.songStyle eq 4}">
                                            摇滚
                                        </c:when>
                                        <c:when test="${mix.songStyle eq 5}">
                                            爵士
                                        </c:when>
                                        <c:when test="${mix.songStyle eq 6}">
                                            古典
                                        </c:when>
                                        <c:when test="${mix.songStyle eq 7}">
                                            纯音乐
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td style="background-color: ${mix.colorTag}">
                                    <c:choose>
                                        <c:when test="${mix.emotion eq 1}">
                                            兴奋
                                        </c:when>
                                        <c:when test="${mix.emotion eq 2}">
                                            愉快
                                        </c:when>
                                        <c:when test="${mix.emotion eq 3}">
                                            平和
                                        </c:when>
                                        <c:when test="${mix.emotion eq 4}">
                                            悲伤
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>${mix.bpm}</td>
                                <td>${mix.duration}&nbsp;(秒)</td>
                                <td>${mix.energyLevel}</td>
                                <td>
                                    <audio id = "audio-id-${status.index}" mix-index = "${status.index}" controls="controls" src="http://yyssb.ifitmix.com/${mix.link}" onended="nextMix(this)"></audio>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">

    function nextMix(obj) {
        var this_ = $(obj);
        var index = $(this_).attr("mix-index");
        index++;
        var targetId = "#audio-id-" + index;
        //mix-index
        var targetMix = $(targetId);
        if (targetMix == null) {
            $(this_).waiting();
            return;
        }
        $(targetMix)[0].play();
    }

</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>002+00