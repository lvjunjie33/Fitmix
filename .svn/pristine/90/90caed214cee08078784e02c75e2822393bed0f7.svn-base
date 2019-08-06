<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/4/30
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<div class="uk-container-center uk-body">

    <div class="uk-form-row">
        <a href="http://appt.igeekery.com/refresh-task-info.htm" target="_blank" class="uk-button">刷新缓存</a>
    </div>

    <table class="uk-table uk-table-hover uk-table-striped">
        <thead>
            <tr>
                <th>序号</th>
                <th>任务关键字</th>
                <th>任务类型</th>
                <th>任务描述</th>
                <th>积分</th>
                <th>是否有效</th>
                <th>添加时间</th>
                <th>修改时间</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="taskInfo" items="${list}" varStatus="state">
                <tr>
                    <td>${state.index}</td>
                    <td name="id" style="display: none;">${taskInfo.id}</td>
                    <td name="status" style="display: none;">${taskInfo.status}</td>
                    <td name="name">${taskInfo.taskKey}</td>
                    <td>
                        <c:if test="${taskInfo.taskType == 0}">
                            每日任务
                        </c:if>
                        <c:if test="${taskInfo.taskType == 1}">
                            一次性任务
                        </c:if>
                    </td>
                    <td>${taskInfo.description}</td>
                    <td name="coin">${taskInfo.coin}</td>
                    <td>
                        <c:if test="${taskInfo.status == 0}">
                            有效
                        </c:if>
                        <c:if test="${taskInfo.status == 1}">
                            无效
                        </c:if>
                    </td>
                    <td><lch:parse-date time="${taskInfo.addTime}" pattern="yyyy-MM-dd HH:mm:ss"></lch:parse-date> </td>
                    <td><lch:parse-date time="${taskInfo.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"></lch:parse-date></td>
                    <td><a href="javascript:" task-modify>modify</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>


<%--  modify  --%>
<jsp:include page="modify.jsp"/>

<script type="text/javascript">

    $(function(){
        $("[refresh-buffer]").click(function(){
            var th = $(this);
            var data = th.attr("data");
            var urls = $.strToArray(data);
            $.each(urls, function(index, url){
                var refreshWindow = window.open(url,'refresh buffer',"height=100, width=100, top=0, left=0,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=n o, status=no");
                window.setTimeout(function(){
                    refreshWindow.close();
                }, 4000);
            });
        });

    });

</script>


