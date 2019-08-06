<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/1/29
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>



<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action="/run/mix/page.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>ID：</label>
                    <label class="mix-scene"><input type="number" name="filter[id]" value="${page.filter.id}" placeholder="编号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>名称：</label>
                    <label class="mix-scene"><input type="text" name="filter[title]" value="${page.filter.title}" placeholder="名称"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>时长：</label>
                    <label class="mix-scene"><input type="number" name="filter[duration]" value="${page.filter.duration}" placeholder="时长"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>bpm：</label>
                    <label class="mix-scene">
                        <input type="number" name="filter[bBpm]" value="${page.filter.bBpm}" placeholder="bpm">
                        -
                        <input type="number" name="filter[eBpm]" value="${page.filter.eBpm}" placeholder="bpm">
                    </label>
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a style="margin-left: 20px" href="#run-mix-add" class="uk-button" data-uk-modal>新增</a>
                </div>
            </div>
        </form>
    </div>
    <hr class="uk-article-divider">

    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr>
                <th style="width: 50px;">编号</th>
                <th style="width: 50px;">标题</th>
                <th style="width: 50px;">曲风</th>
                <th style="width: 50px;">能量</th>
                <th style="width: 50px;">感情色彩</th>
                <th style="width: 50px;">bpm</th>
                <th style="width: 50px">时长(秒)</th>
                <th style="width: 50px">添加时间</th>
                <th style="width: 50px;">上架/下架</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="mix" items="${page.result}">
                <tr>
                    <td>${mix.id}</td>
                    <td><a href="/run/mix/info.htm?id=${mix.id}" target="_blank">${mix.title}</a></td>
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
                    <td>${mix.energyLevel}</td>
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
                    <td>${mix.duration}</td>
                    <td><lch:parse-date time="${mix.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${mix.watch eq 1}">已上架</c:when>
                            <c:when test="${mix.watch eq 0}">未上架</c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <lch:page page="${page}" href="/run/mix/page.htm"></lch:page>
</div>

<script type="text/javascript">
</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<jsp:include page="./add.jsp"/>