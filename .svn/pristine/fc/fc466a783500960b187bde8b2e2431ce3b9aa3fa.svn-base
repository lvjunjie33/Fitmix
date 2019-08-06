<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/6/28
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>

    #activity-list{
        margin-top: 10px;
    }

</style>

<div id="activity-list">
    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/activity/list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <a href="#watch-add" class="uk-button" data-uk-modal>添加</a>
            </div>
        </form>
    </div>

    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover" style="width: 4000px">
            <thead>
            <tr>
                <th style="width: 50px;">标题</th>
                <th style="width: 50px">测试时间/添加时间</th>
                <th style="width: 150px;">备注</th>
                <th style="width: 150px">握手</th>
                <th style="width: 150px;">手表 ChipId号</th>
                <th style="width: 150px;">FM 屏幕</th>
                <th style="width: 150px;">FM 按键</th>
                <th style="width: 150px;">FM 触摸屏</th>
                <th style="width: 150px;">FM 马达</th>
                <th style="width: 150px;">FM 蜂鸣器</th>
                <th style="width: 150px;">FM 背光</th>
                <th style="width: 150px;">FM GPS</th>
                <th style="width: 150px;">FM GSENSOR</th>
                <th style="width: 150px;">FM 心率</th>
                <th style="width: 150px;">FM 指南针</th>
                <th style="width: 150px;">FM 气温</th>
                <th style="width: 150px;">FM 气压</th>
                <th style="width: 150px;">FM 蓝牙</th>
                <th style="width: 150px;">FM 测试</th>
                <th style="width: 150px;">FM 湿度</th>
                <th style="width: 150px;">FM 陀螺仪</th>
                <th style="width: 150px;">注意事项</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="watch" items="${page.result}">
                <tr>
                    <td><a href="/report/watch/modify.htm?id=${watch.id}" target="_blank">${watch.title}</a></td>
                    <td>
                        <lch:parse-date time="${watch.testTime}" pattern="yyyy-MM-dd"/>
                        /
                        <lch:parse-date time="${watch.addTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${watch.des}</td>
                    <td>${watch.answer}</td>
                    <td>${watch.test1}</td>
                    <td>${watch.test2}</td>
                    <td>${watch.test3}</td>
                    <td>${watch.test4}</td>
                    <td>${watch.test5}</td>
                    <td>${watch.test6}</td>
                    <td>${watch.test7}</td>
                    <td>${watch.test8}</td>
                    <td>${watch.test9}</td>
                    <td>${watch.test10}</td>
                    <td>${watch.test11}</td>
                    <td>${watch.test12}</td>
                    <td>${watch.test13}</td>
                    <td>${watch.test14}</td>
                    <td>${watch.test15}</td>
                    <td>${watch.test16}</td>
                    <td>${watch.test17}</td>
                    <td>${watch.test18}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/report/watch/manager.htm"></lch:page>

<jsp:include page="add.jsp"/>

<%--<jsp:include page="modify.jsp"/>--%>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
