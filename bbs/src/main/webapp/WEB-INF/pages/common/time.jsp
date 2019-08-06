<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/9/13
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bbs-row bbs-today">
    <div class="bbs-box">
        <div class="bbs-date">
            <div class="bbs-month">
                ${SYSTEMTIME['MONTH']}
            </div>
            <div class="bbs-day">
                ${SYSTEMTIME['DAY']}
            </div>
        </div>
        <div class="bbs-week">
            <p>${SYSTEMTIME['WEEK_EN']}</p>
            <p>${SYSTEMTIME['WEEK_CN']}</p>
        </div>
    </div>
</div>
