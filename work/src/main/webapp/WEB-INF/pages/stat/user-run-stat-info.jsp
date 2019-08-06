<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/11/23
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

</style>

<div class="uk-container-center uk-body">
    <div class="uk-form-row">

        <table class="uk-table uk-table-hover">
            <tbody>
                <tr>
                    <td>
                        <label>城市：</label><label>${plan.values.city}</label>
                    </td>
                    <td>
                        <label>里程(大于m)：</label><label>${plan.values.distance}</label>
                    </td>
                    <td>
                        <label>统计日期：</label><label>${plan.values.time}</label>
                    </td>
                    <td>
                        <label><a href="/city/run/stat/info/export.htm?planId=${plan.id}">全部导出</a></label>
                    </td>
                </tr>
            </tbody>
        </table>
        <br/><br/>

        <div class="uk-overflow-container">
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>用户名称/编号</th>
                    <th>总里程</th>
                    <th>总步数</th>
                    <th>总卡路里</th>
                    <th>总燃脂量</th>
                    <th>平均配速</th>
                    <th>总运动次数</th>
                    <th>总运动时间(秒)</th>
                    <th>手机号码</th>
                    <th>邮箱</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="stat" items="${page.result}">
                    <tr>
                        <td>${stat.id}</td>
                        <td><a href="/user/user-info.htm?uid=${stat.values.uid}">${stat.values.name}/${stat.values.uid}</a></td>
                        <td>${stat.values.distanceSum}</td>
                        <td>${stat.values.stepSum}</td>
                        <td>${stat.values.calorieSum}</td>
                        <td>${stat.values.consumeFatSum}</td>
                        <td>${stat.values.pace}</td>
                        <td>${stat.values.rumSum}</td>
                        <td>${stat.values.runTimeSum}</td>
                        <td>${stat.values.mobile}</td>
                        <td>${stat.values.email}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%--分页条--%>
    <lch:page page="${page}" href="/city/run/stat/info.htm"></lch:page>
</div>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>