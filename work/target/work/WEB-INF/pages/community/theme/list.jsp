<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/5/31
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">
    .uk-form-row{margin-top: 20px}
    .b-c-F9C0C0{
        background-color: #F9C0C0;
    }

    .c-E00C0C{
        color: #E00C0C;
    }
    .c-3B9C1D{
        color: #3B9C1D;
    }

    .td-text {
        white-space: normal;
        width: 300px;
        word-wrap: break-word;
        word-break: break-all;
        overflow: hidden;
    }
</style>

<form action="/theme/list.htm" method="get" class="uk-form">
    <div class="uk-form-row uk-grid">
        <div class="uk-form-row uk-width-1-4">
            <label>板块名</label>
            <input type="text" name="filter[searchText]" value="${page.filter.searchText}" placeholder="板块名"/>
        </div>

        <div class="uk-form-row uk-width-1-4">
            <label>排序规则：</label>
            <select name="filter[searchType]">
                <option value="1" <c:if test="${page.filter.searchType == 1}">selected</c:if>>时间</option>
                <option value="2" <c:if test="${page.filter.searchType == 2}">selected</c:if>>回答数</option>
                <option value="3" <c:if test="${page.filter.searchType == 3}">selected</c:if>>点赞数</option>
            </select>
        </div>

        <div class="uk-form-row uk-width-1-3">
            <button class="uk-button uk-button-primary">查询</button>&nbsp;&nbsp;&nbsp;
            <a href="#task-add" class="uk-button b-c-F9C0C0" data-uk-modal>添加</a>
        </div>
    </div>
</form>

<table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
    <thead>
    <tr>
        <th>编号</th>
        <th  style="width: 50px">话题标题</th>
        <th>添加时间</th>

        <th>审核</th>
        <th>允许回复</th>
        <th>敏感</th>

        <th>点赞数</th>
        <th>回答数</th>
        <th>banner位置</th>
        <th>banner封面图</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.result}" var="item">
        <tr>
            <td><a href="/theme/to-detail.htm?id=${item.id}">${item.id}</a></td>
            <td class="td-text">${item.title}</td>
            <td><lch:parse-date time="${item.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>
                <c:choose>
                    <c:when test="${item.isConfirmed == 0}"><strong class="c-3B9C1D">未审核</strong></c:when>
                    <c:when test="${item.isConfirmed == 1}"><strong class="c-E00C0C">通过审核</strong></c:when>
                    <c:otherwise>审核未通过</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${item.isReply == 0}"><strong class="c-3B9C1D">关闭回复</strong></c:when>
                    <c:when test="${item.isReply == 1}"><strong class="c-E00C0C">开启回复</strong></c:when>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${item.isSensitive == 0}"><strong class="c-3B9C1D">屏蔽</strong></c:when>
                    <c:when test="${item.isSensitive == 1}"><strong class="c-E00C0C">正常</strong></c:when>
                </c:choose>
            </td>
            <td>${item.upNum}</td>
            <td>${item.discussNum}</td>
            <td>${item.bannerSort}</td>
            <td>
                <c:if test="${!empty item.backImage}">
                    <img style="width: 150px" src="${item.backImage}" />
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--分页条--%>
<lch:page page="${page}" href="/theme/list.htm"></lch:page>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<jsp:include page="add.jsp"/>