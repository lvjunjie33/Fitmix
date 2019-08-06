<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/7/5
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<style>
    .font-size-16{
        font-size: 16px;
    }
    .color-red {
        color: red;
    }
</style>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>
            ${activity.themeName}(${activity.id})
        </h1>
            <div style="margin: 15px 0px;" class="font-size-16">
                <a href="/activity/modify.htm?activityId=${activity.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/activity/to-find-activity-sign-up-info.htm?filter[activityId]=${activity.id}">查看报名信息</a>
                <a href="#activity-set-activity-focus-tag" data-uk-modal>设置赛事标签</a>
            </div>
            <div style="margin: 15px 0px;" class="font-size-16">
                <c:choose>
                    <c:when test="${activity.type == 3}">
                        <span class="color-red">标准赛事：</span>
                        <a href="/activity/add-activity-content.htm?activityId=${activity.id}" target="_blank">编辑活动内容</a>
                        →
                        <a href="/activity/set-other-info.htm?activityId=${activity.id}" target="_blank">设置活动报名组</a>
                        →
                        <a href="/activity/set-user-team-info.htm?activityId=${activity.id}" target="_blank">设置团队信息</a>
                        →
                        <a href="#activity-set-max-sign-group-num"  data-uk-modal>参数组数量限制</a>
                        →
                        <a href="#activity-checked-sex"  data-uk-modal >性别限制</a>
                        →
                        <a href="/activity/to-preview.htm?activityId=${activity.id}" target="_blank">预览活动内容</a>
                    </c:when>
                    <c:when test="${activity.type == 2}">
                        <span class="color-red">三方赛事：</span>
                        <a href="#activity-set-thread-link" data-uk-modal>设置第三方赛事链接</a>
                    </c:when>
                    <c:when test="${activity.type == 1}">
                        <span class="color-red">积分赛事：</span>
                        <a href="/activity/add-activity-content.htm?activityId=${activity.id}" target="_blank">编辑活动内容</a>
                        →
                        <a href="#activity-set-city"  data-uk-modal>设置赛事地区</a>
                        →
                        <a href="/activity/set-other-info.htm?activityId=${activity.id}" target="_blank">设置活动报名组</a>
                        →
                        <a href="/activity/set-sign-up-mode.htm?activityId=${activity.id}" target="_blank">设置用户报名信息</a>
                        →
                        <a href="/activity/to-preview.htm?activityId=${activity.id}" target="_blank">预览活动内容</a>
                    </c:when>
                    <c:otherwise>
                        <span class="color-red">普通赛事：</span>
                        <a href="/activity/add-activity-content.htm?activityId=${activity.id}" target="_blank">编辑活动内容</a>
                        →
                        <a href="/activity/set-other-info.htm?activityId=${activity.id}" target="_blank">设置活动报名组</a>
                        →
                        <a href="/activity/set-sign-up-mode.htm?activityId=${activity.id}" target="_blank">设置用户报名信息</a>
                        →
                        <a href="/activity/to-preview.htm?activityId=${activity.id}" target="_blank">预览活动内容</a>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="activity">
                <div class="uk-thumbnail uk-thumbnail-large">
                    <img src="<lch:build-path url="${activity.themeImage}"/>">
                </div>
                <div style="margin-top: 10px">
                    <strong>
                        <c:choose>
                            <c:when test="${activity.type == 3}">
                                <p>组数限制: ${activity.maxGroupNum}</p>
                                <p>性别限制: ${activity.checkSex}</p>
                            </c:when>
                            <c:when test="${activity.type == 2}">
                                <p>第三方链接地址:
                                    <c:choose>
                                        <c:when test="${empty activity.url}">未设置</c:when>
                                        <c:otherwise><a href="${activity.url}" target="_blank">${activity.url}</a></c:otherwise>
                                    </c:choose>
                                </p>
                            </c:when>
                            <c:when test="${activity.type == 1}">
                                <p>赛事地区:
                                    <c:choose>
                                        <c:when test="${activity.cityTarget == 202}">
                                            河南省
                                        </c:when>
                                        <c:when test="${activity.cityTarget == 201}">
                                            上海市(省份)
                                        </c:when>
                                        <c:when test="${activity.cityTarget == 1}">
                                            深圳市
                                        </c:when>
                                        <c:otherwise>未设置</c:otherwise>
                                    </c:choose>
                                </p>
                            </c:when>
                        </c:choose>
                    </strong>
                    <p>${activity.desc}</p>
                    <p>标签：${activity.focusTag}</p>
                    <p>人数：${activity.activityMaxNumber}</p>
                    <p>人数：${activity.activityFalseNumber}</p>
                    <p>金额：${activity.activityMoney}</p>
                    <p>置顶：${activity.sort}</p>
                    <p>报名：<lch:parse-date time="${activity.signUpBeginTime}" pattern="yyyy-MM-dd HH:mm"/> ~ <lch:parse-date time="${activity.signUpEndTime}" pattern="yyyy-MM-dd HH:mm"/></p>
                    <p>活动：<lch:parse-date time="${activity.activityBeginTime}" pattern="yyyy-MM-dd HH:mm"/> ~ <lch:parse-date time="${activity.activityEndTime}" pattern="yyyy-MM-dd HH:mm"/></p>
                    <p>添加：<lch:parse-date time="${activity.addTime}" pattern="yyyy-MM-dd HH:mm"/></p>
                    <p>浏览量：${activity.viewCount}</p>
                </div>
                <div>
                    <c:choose>
                        <c:when test="${activity.status eq 0}">
                            <label style="color:#6de1ff;">正常</label>
                        </c:when>
                        <c:when test="${activity.status eq 1}">
                            <label style="color:#ff405d;">无效</label>
                        </c:when>
                    </c:choose>
                </div>

                <div>
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
                </div>
            </div>
    </div>
</div>

<jsp:include page="other-set.jsp" />

