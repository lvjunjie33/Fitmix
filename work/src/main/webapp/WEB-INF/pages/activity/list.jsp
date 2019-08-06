<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/2/16
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>

    #activity-list{
        margin-top: 10px;
    }

    .activity{
        margin-bottom: 100px;;
    }
    .group-fee-span{
        min-width: 410px;
        display: inline-block;
    }

    .group-fee-span1{
        min-width: 150px;
        display: inline-block;
    }

    .color-red {
        color: red;
    }

</style>

<div id="activity-list">
    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/activity/list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>发布状态：</label>
                    <label>
                        <select name="filter[releaseStatus]">
                            <option value="" selected>【全部】</option>
                            <option value="0" <c:if test="${page.filter.releaseStatus == 0}">selected</c:if>>发布</option>
                            <option value="1" <c:if test="${page.filter.releaseStatus == 1}">selected</c:if>>待发布</option>
                            <option value="2" <c:if test="${page.filter.releaseStatus == 2}">selected</c:if>>活动结束</option>
                            <option value="3" <c:if test="${page.filter.releaseStatus == 3}">selected</c:if>>活动异常</option>
                        </select>
                    </label>
                    &nbsp;&nbsp;&nbsp;
                    <label>类型：</label>
                    <label class="uk-width-1-6">
                        <select name = "filter[type]">
                            <option value="" selected>【全部】</option>
                            <option value="0" <c:if test="${page.filter.type == 0}">selected</c:if>>普通赛事</option>
                            <option value="1" <c:if test="${page.filter.type == 1}">selected</c:if>>积分赛事</option>
                            <option value="2" <c:if test="${page.filter.type == 2}">selected</c:if>>三方赛事</option>
                            <option value="3" <c:if test="${page.filter.type == 3}">selected</c:if>>标准赛事</option>
                        </select>
                    </label>
                    &nbsp;&nbsp;&nbsp;
                    <label>数据状态：</label>
                    <label class="uk-width-1-6">
                        <select name="filter[status]">
                            <option value="" selected>【全部】</option>
                            <option value="0" <c:if test="${page.filter.status == 0}">selected</c:if>>可用</option>
                            <option value="1" <c:if test="${page.filter.status == 1}">selected</c:if>>废弃</option>
                        </select>
                    </label>
                </div>
                <div class="uk-form-row">

                </div>
                <div class="uk-form-row">
                    <label>编号：</label>
                    <label class="mix-scene">
                        <input type="number" name="filter[id]" value="${page.filter.id}" placeholder="赛事编号">
                    </label>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <label>名称：</label>
                    <label class="mix-scene">
                        <input type="text" name="filter[themeName]" value="${page.filter.themeName}" placeholder="赛事名称">
                    </label>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="uk-button uk-button-primary">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#activity-add" class="uk-button" data-uk-modal>添加</a>
                </div>
            </div>
        </form>
    </div>

    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr>
                <th style="width: 50px;">编号</th>
                <th style="width: 50px">sort</th>
                <th style="width: 200px;">赛事</th>
                <th style="width: 100px">类型</th>
                <th style="width: 100px;">报名时间</th>
                <th style="width: 100px;">活动时间</th>
                <th style="width: 100px;">添加时间</th>
                <th style="width: 50px;">发布状态</th>
                <th style="width: 50px;">数据状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="activity" items="${page.result}">
                <tr>
                    <td>
                        ${activity.id}
                    </td>
                    <td>${activity.sort}</td>
                    <td>
                        <a href="/activity/to-activity.htm?activityId=${activity.id}" target="_blank">${activity.themeName}</a>
                        <br/>
                        <img style="width: 400px;height: 50px" src="<lch:build-path url="${activity.themeImage}"/>">
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${activity.type == 3}">
                                <span class="color-red">标准赛事</span>
                            </c:when>
                            <c:when test="${activity.type == 2}">
                                <span class="color-red">三方赛事</span>
                            </c:when>
                            <c:when test="${activity.type == 1}">
                                <span class="color-red">积分赛事</span>
                            </c:when>
                            <c:when test="${activity.type == 0}">
                                <span class="color-red">普通赛事</span>
                            </c:when>
                            <c:otherwise>
                                <span class="color-red">未设置</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <lch:parse-date time="${activity.signUpBeginTime}" pattern="yyyy-MM-dd HH:mm"/><br/> ~ <br/><lch:parse-date time="${activity.signUpEndTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                    <td>
                        <lch:parse-date time="${activity.activityBeginTime}" pattern="yyyy-MM-dd HH:mm"/><br/> ~ <br/><lch:parse-date time="${activity.activityEndTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                    <td>
                        <lch:parse-date time="${activity.addTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${activity.releaseStatus eq 0}">
                                <label style="color:#6de1ff;">发布</label>
                            </c:when>
                            <c:when test="${activity.releaseStatus eq 1}">
                                <label style="color:#ffb852;">待发布</label>
                            </c:when>
                            <c:when test="${activity.releaseStatus eq 2}">
                                <label style="color:#dd76ff;">活动结束</label>
                            </c:when>
                            <c:when test="${activity.releaseStatus eq 3}">
                                <label style="color:#ff405d;">活动异常</label>
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${activity.status eq 0}">
                                <label style="color:#6de1ff;">正常</label>
                            </c:when>
                            <c:when test="${activity.status eq 1}">
                                <label style="color:#ff405d;">无效</label>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/activity/list.htm"></lch:page>

<jsp:include page="add.jsp"/>

<%--<jsp:include page="modify.jsp"/>--%>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>