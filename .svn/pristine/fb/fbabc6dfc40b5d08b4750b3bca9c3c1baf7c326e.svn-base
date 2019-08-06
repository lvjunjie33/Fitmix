<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/1/4
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<div class="uk-container-center uk-body">

    <div class="uk-form-row">
        <a href="javascript:" refresh-buffer data="${refreshUrls}" class="uk-button">刷新缓存</a>
    </div>

    <table class="uk-table uk-table-hover uk-table-striped">
        <thead>
        <tr>
            <th>index</th>
            <th>name</th>
            <th style="width: 595px">value</th>
            <th>module</th>
            <th>desc</th>
            <th>handling</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${paramList}" varStatus="state">
            <tr>
                <td>${state.index}</td>
                <td name="name">${list.name}</td>
                <td name="value" style="word-break:break-all">${list.val}</td>
                <td>${list.module}</td>
                <td>${list.desc}</td>
                <td><a href="javascript:" sys-modify>modify</a></td>
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
                var refreshWindow = window.open('http://appt.igeekery.com/refresh-sys-param.htm','refresh buffer',"height=100, width=100, top=0, left=0,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=n o, status=no");
                window.setTimeout(function(){
                    refreshWindow.close();
                }, 4000);
            });
        });
    });
</script>


