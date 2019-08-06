<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/11/23
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

</style>

<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action="/city/run/stat/manager.htm" method="get" class="uk-form">
            <div class="uk-form-row uk-grid">
                <div class="uk-width-1-5">
                    <label>城市：</label>
                    <label><input type="text" name="filter[city]" value="${page.filter.city}" placeholder="城市"/></label>
                </div>
                <button type="submit" class="uk-button uk-button-primary">查询</button>&nbsp;&nbsp;
                <a href="#plan-add" class="uk-button" data-uk-modal>添加计划</a>
            </div>
        </form>

        <div class="uk-overflow-container">
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>城市</th>
                    <th>统计日期</th>
                    <th>里程(大于m)</th>
                    <th>添加时间</th>
                    <th>执行状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="plan" items="${page.result}">
                    <tr>
                        <td>${plan.id}</td>
                        <td>${plan.values.city}</td>
                        <td>${plan.values.time}</td>
                        <td>${plan.values.distance}</td>
                        <td>${plan.values.city}</td>
                        <td>
                            <c:choose>
                                <c:when test="${plan.values.status eq 'prepare'}">准备</c:when>
                                <c:when test="${plan.values.status eq 'start'}"><span style="color:red">执行中<span></span></c:when>
                                <c:otherwise>完成</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${plan.values.status eq 'finish'}">
                                <a href="/city/run/stat/info.htm?filter[planId]=${plan.id}" class="uk-button uk-button-primary">明细</a>
                            </c:if>
                            <c:if test="${plan.values.status eq 'start' or plan.values.status eq 'finish'}">
                                <a href="javaScript:void(0)" onclick="deletePlan(${plan.id})" class="uk-button uk-button-primary">删除</a>
                            </c:if>
                            <c:if test="${plan.values.status eq 'prepare'}">
                                <a href="javaScript:void(0)" onclick="runPlan(${plan.id})" class="uk-button uk-button-primary">执行</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%--分页条--%>
    <lch:page page="${page}" href="/city/run/stat/manager.htm"></lch:page>
</div>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<div id="plan-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>文件管理</h1>
        <div class="uk-form-row">
            <form action="/file/add.json" method="post" class="uk-form" id="plan-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="city" placeholder="城市" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="time" data-uk-datepicker="{format:'YYYY-MM'}" placeholder="日期" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="number" class="uk-width-1-1 uk-form-large" name="distance" placeholder="距离(大于m)" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <a href="javaScript:void(0)" onclick="addPlan()" class="uk-button uk-button-primary" >add</a>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">

    function deletePlan(planId) {
        $.ajax({
            type: "POST",
            url: "/city/run/stat/delete.json",
            data:"planId=" + planId,
            dataType:"json",
            success:function (vals) {
                if (vals.code == 0) {
                    location=location;
                } else {
                    alert(vals.msg);
                }
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }

    function runPlan(planId) {
        $.ajax({
            type: "POST",
            url: "/city/run/stat/start.json",
            data:"planId=" + planId,
            dataType:"json",
            success:function (vals) {
                if (vals.code == 0) {
                    location=location;
                } else {
                    alert(vals.msg);
                }
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }

    function addPlan() {
        $.ajax({
            type: "POST",
            url: "/city/run/stat/add.json",
            data: $("#plan-add-form").serialize(),
            dataType:"json",
            success:function (vals) {
                if (vals.code == 0) {
                    location=location;
                } else {
                    alert(vals.msg);
                }
            },
            error:function (XMLHttpRequest,textStatus,errorThrom){
                console.log("不明原因造成数据获取失败... ...");
            }
        });
    }

</script>