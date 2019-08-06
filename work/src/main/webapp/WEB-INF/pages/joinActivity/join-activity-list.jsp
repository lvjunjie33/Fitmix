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

</style>


<div id="activity-list">
    <c:forEach var="joinActivity" items="${page.result}">
        <div class="activity">
            <div class="uk-thumbnail uk-thumbnail-large">
                <img src="${joinActivity.themeImage}" />
            </div>
            <div style="margin-top: 10px">
                <strong>
                    ${joinActivity.themeName}(${joinActivity.id})
                    <c:choose>
                        <c:when test="${joinActivity.status eq 0}">
                            <a href="javascript:void (0);" onclick="modifyStatus(${joinActivity.id},1)">加入赛事</a>&nbsp;&nbsp;&nbsp;
                            <a href="javascript:void (0);" onclick="modifyStatus(${joinActivity.id},2)">拒绝</a>
                        </c:when>
                    </c:choose>
                    <a href="${joinActivity.url}" target="_blank">预览</a>
                </strong>
                <p>${joinActivity.desc}</p>
            </div>
            <p>金额：${activity.activityMoney}</p>
            <p>活动开始时间：<lch:parse-date time="${joinActivity.activityBeginTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <p>活动结束时间：<lch:parse-date time="${joinActivity.activityEndTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <p>活动报名开始时间：<lch:parse-date time="${joinActivity.signUpBeginTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <p>活动报名结束时间：<lch:parse-date time="${joinActivity.signUpEndTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <p>添加：<lch:parse-date time="${joinActivity.addTime}" pattern="yyyy-MM-dd HH:mm"/></p>
            <div>
                <c:choose>
                    <c:when test="${joinActivity.status eq 0}">
                        <label style="color:#6de1ff;">待加入赛事</label>
                    </c:when>
                    <c:when test="${joinActivity.status eq 1}">
                        <label style="color:#ff405d;">已加入赛事</label>
                    </c:when>
                    <c:when test="${joinActivity.status eq 2}">
                        <label style="color:#ff405d;">已拒绝</label>
                    </c:when>
                </c:choose>
            </div>

        </div>
    </c:forEach>
</div>


<%--分页条--%>
<lch:page page="${page}" href="/join-activity/list.htm"></lch:page>

<script>
    function modifyStatus(id, status) {
        var info = "";
        if(status == 1) {
            info = "确认加入第三方赛事吗？";
        } else if(status == 2) {
            info = "确认要拒绝第三方赛事吗？";
        }
        if (window.confirm(info)) {
            var th = $(this);
            $.post("/join-activity/join-activity-modify-status.json", {id:id, status:status}, function(result){
                var code = result['code'];
                if (code == 0) {
                    UIkit.notify(result['msg'], {status:'success', pos:'top-right'});
                }
                else{
                    UIkit.notify(result['msg'], {status:'danger', pos:'top-right'});
                }
            });
        }
    }
</script>


