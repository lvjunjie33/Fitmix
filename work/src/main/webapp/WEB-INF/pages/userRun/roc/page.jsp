<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/1/4
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<div class="uk-container-center uk-body">

    <form action="/user/roc/run/page.htm" method="POST" class="uk-form">
        <div class="uk-form-row">

            <div class="uk-form-row">
                <label>类型</label>
                <select name="filter[type]">
                    <option value="1" <c:if test="${page.filter.type != 2 and page.filter.type != 3 }">selected</c:if>>跑步</option>
                    <option value="3" <c:if test="${page.filter.type == 3 }">selected</c:if>>手表</option>
                </select>
            </div>

            <div class="uk-form-row">
                <label>时间：</label>
                <label><input type="text" name="filter[bTime]" value="${page.filter.bTime}" placeholder="开始时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
                -
                <label><input type="text" name="filter[eTime]" value="${page.filter.eTime}" placeholder="结束时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
            </div>

            <div class="uk-form-row">
                <input type="hidden" name="filter[uid]" value="${page.filter.uid}" placeholder="用户编号"/>
                <button class="uk-button uk-button-primary">查询</button>
            </div>
        </div>
    </form>

    <c:choose>
        <c:when test = "${page.filter.type == 3}">
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>用户编号</th>
                    <th>时长(毫秒)</th>
                    <th>卡路里(卡)</th>
                    <th>燃脂量</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>保存时间</th>
                    <th>状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="userRun" items="${page.result}">
                    <tr>
                        <td>${userRun.id}</td>
                        <td>${userRun.uid}</td>
                        <td>${userRun.runTime}</td>
                        <td>${userRun.calorie}</td>
                        <td>${userRun.consumeFat}</td>
                        <td><lch:parse-date time="${userRun.startTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td><lch:parse-date time="${userRun.endTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td><lch:parse-date time="${userRun.addTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${userRun.state == 1}">未删除</c:when>
                                <c:otherwise>已删除</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:when test = "${page.filter.type == 2}">
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>用户编号</th>
                    <th>时长(毫秒)</th>
                    <th>跳绳个数</th>
                    <th>卡路里(卡)</th>
                    <th>燃脂量</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>保存时间</th>
                    <th>状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="userRun" items="${page.result}">
                    <tr>
                        <td>${userRun.id}</td>
                        <td>${userRun.uid}</td>
                        <td>${userRun.runTime}</td>
                        <td>${userRun.skipNum}</td>
                        <td>${userRun.calorie}</td>
                        <td>${userRun.consumeFat}</td>
                        <td><lch:parse-date time="${userRun.startTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td><lch:parse-date time="${userRun.endTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td><lch:parse-date time="${userRun.addTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${userRun.state == 1}">未删除</c:when>
                                <c:otherwise>已删除</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>用户编号</th>
                    <th>时长(毫秒)</th>
                    <th>里程(米)</th>
                    <th>卡路里(卡)</th>
                    <th>燃脂量</th>
                    <th>BPM</th>
                    <th>步数(步)</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>保存时间</th>
                    <th>状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="userRun" items="${page.result}">
                    <tr>
                        <td>${userRun.id}</td>
                        <td>${userRun.uid}</td>
                        <td>${userRun.runTime}</td>
                        <td>${userRun.distance}</td>
                        <td>${userRun.calorie}</td>
                        <td>${userRun.consumeFat}</td>
                        <td>${userRun.bpm}</td>
                        <td>${userRun.step}</td>
                        <td><lch:parse-date time="${userRun.startTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td><lch:parse-date time="${userRun.endTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td><lch:parse-date time="${userRun.addTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${userRun.state == 1}">未删除</c:when>
                                <c:otherwise>已删除</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</div>

<%--分页条--%>
<lch:page page="${page}" href="/user/roc/run/page.htm"></lch:page>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>