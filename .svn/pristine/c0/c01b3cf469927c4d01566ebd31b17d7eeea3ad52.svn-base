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

    <form action="/user/run/page.htm" method="get" class="uk-form">
        <div class="uk-form-row">

            <div class="uk-form-row">
                <label>类型</label>
                <select name="filter[type]">
                    <option value="1" <c:if test="${page.filter.type != 2 and page.filter.type != 3 }">selected</c:if>>跑步</option>
                    <option value="2" <c:if test="${page.filter.type == 2 }">selected</c:if>>跳绳</option>
                    <option value="3" <c:if test="${page.filter.type == 3 }">selected</c:if>>手表</option>
                </select>
            </div>

            <div class="uk-form-row">
                <label>用户编号：</label>
                <label><input type="number" name="filter[uid]" value="${page.filter.uid}" placeholder="用户编号"/></label>

                <label>运动编号：</label>
                <label><input type="number" name="filter[id]" value="${page.filter.id}" placeholder="运动编号"/></label>
            </div>

            <div class="uk-form-row">
                <label>时间：</label>
                <label><input type="text" name="filter[bTime]" value="${page.filter.bTime}" placeholder="开始时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
                -
                <label><input type="text" name="filter[eTime]" value="${page.filter.eTime}" placeholder="结束时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
            </div>

            <div class="uk-form-row">
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

                    <th>运动类型</th>
                    <th>时长(毫秒)</th>
                    <th>里程(米)</th>
                    <th>卡路里(卡)</th>
                    <th>燃脂量</th>
                    <th>步数(步)</th>
                    <th>最快步频</th>
                    <th>最高配速</th>
                    <th>最大心率</th>
                    <th>平均心率</th>
                    <th>静息心率</th>
                    <th>下降高度</th>
                    <th>上升高度</th>
                    <th>峰值海拔</th>
                    <th>波谷海拔</th>
                    <th>跑力</th>
                    <th>骑行功率</th>

                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>保存时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="userRun" items="${page.result}">
                    <tr>
                        <td>${userRun.id}</td>
                        <td>${userRun.uid}</td>

                        <td>
                        <c:choose>
                            <c:when test="${userRun.watch.sportType == 0}">室外跑</c:when>
                            <c:when test="${userRun.watch.sportType == 1}">室内跑</c:when>
                            <c:when test="${userRun.watch.sportType == 2}">徒步</c:when>
                            <c:when test="${userRun.watch.sportType == 4}">骑行</c:when>
                            <c:when test="${userRun.watch.sportType == 8}">登山</c:when>
                            <c:when test="${userRun.watch.sportType == 15}">骑行</c:when>
                        </c:choose>
                        </td>
                        <td>${userRun.watch.sportDuration}</td>
                        <td>${userRun.watch.totalDistance}</td>
                        <td>${userRun.watch.totalCalorie}</td>
                        <td>${userRun.watch.fatBurst}</td>
                        <td>${userRun.watch.totalSteps}</td>
                        <td>${userRun.watch.maxFrequency}</td>
                        <td>${userRun.watch.maxPace}</td>
                        <td>${userRun.watch.maxSportHR}</td>
                        <td>${userRun.watch.avgSportHR}</td>
                        <td>${userRun.watch.restHR}</td>
                        <td>${userRun.watch.totalDown}</td>
                        <td>${userRun.watch.totalUp}</td>
                        <td><fmt:formatNumber type="number" value="${userRun.watch.peakAltitude}" pattern="0.00" maxFractionDigits="2"/></td>
                        <td><fmt:formatNumber type="number" value="${userRun.watch.troughAltitude}" pattern="0.00" maxFractionDigits="2"/></td>
                        <td>${userRun.watch.runPower}</td>
                        <td>${userRun.watch.ridePower}</td>
                        <td><lch:parse-date time="${userRun.startTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td><lch:parse-date time="${userRun.endTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td><lch:parse-date time="${userRun.addTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${userRun.state == 1}">未删除</c:when>
                                <c:otherwise>已删除</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${!empty userRun.watchSourceZipFile}">
                                    <a href="http://yyssb.ifitmix.com/${userRun.watchSourceZipFile}" download>下载手表源文件</a>
                                </c:when>
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
<lch:page page="${page}" href="/user/run/page.htm"></lch:page>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>