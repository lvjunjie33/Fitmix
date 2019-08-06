<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<style>
    .comment{margin: 0px 20px;color: #993366;}
</style>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <h2>Club 详情</h2>
    </div>
    <form action="/mix/modify.json" method="post" class="uk-form" enctype="multipart/form-data" isform>
        <table class="uk-table uk-table-hover">
            <tbody>
                <tr>
                    <td><label>封面：</label><label><a class="uk-thumbnail uk-thumbnail-mini"><img src="<lch:build-path url="${club.backImageUrl}"/>"></a></label></td>
                </tr>
                <tr>
                    <td>
                        <a href="#set-max-member-num-tag" data-uk-modal>设置俱乐部人数</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>名称：</label>
                        <label>
                            ${club.name}
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>创建人：</label>
                        <label>
                            ${club.user.name}
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>成员人数：</label>
                        <label>
                            ${club.memberCount}
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>最大人数：</label>
                        <label>
                            ${club.maxMember}
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>描述：</label>
                        <label>
                            ${club.desc}
                        </label>
                    </td>
                </tr>
            </tbody>
        </table>
        <table class="uk-table uk-table-hover">
            <tbody>
                <tr>
                    <td colspan="7"><label>成员列表 ：</label>
                    </td>
                </tr>
                <tr>
                    <td>用户ID</td>
                    <td>用户名</td>
                    <td>状态</td>
                    <td>最后一次运动开始时间</td>
                    <td>最后一次运动结束时间</td>
                    <td>最后一次运动时长</td>
                    <td>最后一次运动步数</td>
                    <td>最后一次运动距离</td>
                </tr>
                <c:forEach var="member" items="${page.result}">
                    <tr>
                        <td>${member.user.id}</td>
                        <td>${member.user.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${member.status == 0}">正常</c:when>
                                <c:otherwise><span style="color: red">退出</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td><lch:parse-date time="${member.user.lastRun.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><lch:parse-date time="${member.user.lastRun.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatNumber value="${member.user.lastRun.runTime/60/1000}" pattern="0.##" ></fmt:formatNumber> 分</td>
                        <td>${member.user.lastRun.step}</td>
                        <td>${member.user.lastRun.distance} m</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <lch:page page="${page}" href="/club/club-info.htm?id=${club.id}"></lch:page>
    </form>
</div>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<div id="set-max-member-num-tag" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>设置俱乐部最大人数</h1>
        <span style="color:red">俱乐部最大人数不能小于真实人数</span>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/club/set-max-member.json" method="post" class="uk-form" id="activity-focus-tag" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="maxMember" placeholder="人数" notnull>
                    <input name="id" type="hidden" value="${club.id}" />
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="activity-focus-tag-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
