<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/11
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
    .btn-style:hover {
        text-decoration:underline;
    }
    .l-50 {
        line-height: 50px;
    }
</style>

<div class="uk-form-row" style="margin-bottom: 20px;">
    <form action="/gw/CommonProblem/problem-list.htm" method="get" class="uk-form">
        <div class="uk-form-row">
            <button class="uk-button uk-button-primary">查询</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#problem-add" class="uk-button" data-uk-modal>添加</a>
        </div>
    </form>
</div>

<div>
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <td>问题编号</td>
            <td>问题标题</td>
            <td>问题描述</td>
            <td>英文问题标题</td>
            <td>英文问题描述</td>
            <td>添加时间</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="commonProblem" items="${page.result}">
            <tr class="l-50" >
                <td>${commonProblem.id}</td>
                <td>
                    <div style="overflow: auto;height: 50px; ">
                            ${commonProblem.problemTitle}
                    </div>
                </td>
                <td>
                    <div style="overflow: auto;height: 50px;">
                            ${commonProblem.problemContent}
                    </div>
                </td>
                <td>
                    <div style="overflow: auto;height: 50px; ">
                            ${commonProblem.problemTitle_en}
                    </div>
                </td>
                <td>
                    <div style="overflow: auto;height: 50px;">
                            ${commonProblem.problemContent_en}
                    </div>
                </td>
                <td>
                    <lch:parse-date time="${commonProblem.addTime}" pattern="yyyy-MM-dd HH:mm"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${commonProblem.status == 1}">有效</c:when>
                        <c:otherwise>无效</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="/gw/CommonProblem/problem-update-view.htm?id=${commonProblem.id}&lan='zh'">修改</a>
                    <a href="/gw/CommonProblem/problem-delete.htm?id=${commonProblem.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<%--分页条--%>
<lch:page page="${page}" href="/gw/CommonProblem/problem-list.htm"></lch:page>

<jsp:include page="./problem-add.jsp"/>
