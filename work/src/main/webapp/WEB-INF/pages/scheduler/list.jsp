<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/6/22
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">
    .uk-form-row{margin-top: 20px}
    .row-div-node{
        width: 700px;
        padding-left: 30px;
        padding-top: 10px;
    }
    .b-c-EFE8B7 {
        background-color: #EFE8B7;
    }
    .b-c-F9D0D0{
        background-color: #F9D0D0;
    }
    .b-c-659F13{
        background-color: #659F13;
    }
    .b-c-9A9191{
        background-color: #9A9191;
    }
    .b-c-76A3DC{
        background-color: #76A3DC;
    }
    .b-c-E792E8{
        background-color: #E792E8;
    }
    .b-c-E84A1B {
        background-color: #E84A1B;
    }
    .b-c-F9C0C0{
        background-color: #F9C0C0;
    }
    .c-CCB728{
        color: #CCB728;
    }
    .c-E00C0C{
        color: #E00C0C;
    }
    .c-3B9C1D{
        color: #3B9C1D;
    }
</style>

<form action="/scheduler/list.htm" method="get" class="uk-form">
    <div class="uk-form-row uk-grid">

        <div class="uk-form-row uk-width-1-6">
            <label>执行状态：</label>
            <select name="filter[currentStatus]">
                <option value="">请选择</option>
                <option value="1" <c:if test="${page.filter.currentStatus == 1}">selected</c:if>>未启动</option>
                <option value="2" <c:if test="${page.filter.currentStatus == 2}">selected</c:if>>运行中</option>
                <option value="3" <c:if test="${page.filter.currentStatus == 3}">selected</c:if>>异常停止</option>
            </select>
        </div>

        <div class="uk-form-row uk-width-1-6">
            <label>任务状态：</label>
            <select name="filter[status]">
                <option value="">请选择</option>
                <option value="0" <c:if test="${page.filter.status == 0}">selected</c:if>>正常</option>
                <option value="1" <c:if test="${page.filter.status == 1}">selected</c:if>>失效</option>
            </select>
        </div>

        <div class="uk-form-row uk-width-2-10">
            <label>类名</label>
            <input type="text" name="filter[topicName]" value="${page.filter.topicName}" placeholder="消息通道名"/>
        </div>

        <div class="uk-form-row uk-width-2-6">
            <button class="uk-button uk-button-primary">查询</button>&nbsp;&nbsp;&nbsp;
            <a href="#task-add" class="uk-button b-c-F9C0C0" data-uk-modal>添加</a>
        </div>
    </div>
</form>

<table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
    <thead>
    <tr>
        <th>操作</th>
        <th>消息通道名</th>
        <th>cron表达式</th>
        <th>描述</th>

        <th>添加时间</th>
        <th>开始时间/结束时间</th>
        <th>执行状态</th>

        <th>最后执行时间</th>
        <th>花费时间</th>
        <th>任务状态</th>

        <th>任务命令</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.result}" var="item">
        <tr>
            <td><a href="/scheduler/modify.htm?id=${item.id}" class="uk-button b-c-EFE8B7" >修改</a></td>
            <td>${item.topicName}</td>
            <td>${item.cron}</td>
            <td>${item.des}</td>

            <td><lch:parse-date time="${item.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>
                <lch:parse-date time="${item.beginTime}" pattern="yyyy-MM-dd HH:mm"/>
                <br/><br/>
                <lch:parse-date time="${item.endTime}" pattern="yyyy-MM-dd HH:mm"/>
            </td>
            <td>

                <c:choose>
                    <c:when test="${item.currentStatus == 2}"><strong class="c-3B9C1D">运行中</strong></c:when>
                    <c:when test="${item.currentStatus == 3}"><strong class="c-E00C0C">异常停止</strong></c:when>
                    <c:otherwise><strong class="c-CCB728">未启动</strong></c:otherwise>
                </c:choose>

            </td>

            <td><lch:parse-date time="${item.lastTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${item.spendTime / 1000}秒</td>
            <td>
                <c:choose>
                    <c:when test="${item.status == 0}">
                        正常
                    </c:when>
                    <c:when test="${item.status == 1}">
                        无效
                    </c:when>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${item.currentStatus == 2}">
                        <button class="uk-button uk-button-primary b-c-F9D0D0 task-cmd" t-type = "2" t-id = "${item.id}">暂停</button>
                    </c:when>
                    <c:otherwise>
                        <button class="uk-button uk-button-primary b-c-F9D0D0 task-cmd" t-type = "1" t-id = "${item.id}">启动</button>
                    </c:otherwise>
                </c:choose>

                <button class="uk-button uk-button-primary b-c-659F13 task-cmd" t-type = "3" t-id = "${item.id}">立即执行一次</button>
                <br/><br/>

                <button class="uk-button uk-button-primary b-c-E792E8 task-cmd" t-type = "6" t-id = "${item.id}" t-cron = "${item.cron}">重设cron</button>
                <button class="uk-button uk-button-primary b-c-E84A1B task-cmd" t-type = "7" t-id = "${item.id}">恢复数据</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--分页条--%>
<lch:page page="${page}" href="/scheduler/list.htm"></lch:page>

<a href="#task-cron-reset" id = "cron-reset-id" data-uk-modal hidden style="height: 0px;width: 0px">.</a>
<div id="task-cron-reset" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>重置cron表达式</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/scheduler/reset.json" method="post" class="uk-form" id="task-cron-reset-form" isform>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large"  name="newCron" placeholder="新的cron表达式" notnull>
                    <input type="hidden" name = "id" />
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary"  target="role-add-form">save</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<a href="#task-restore-history" id = "restore-history-id" data-uk-modal hidden style="height: 0px;width: 0px">.</a>
<div id="task-restore-history" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>恢复历史数据</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/scheduler/restore-history.json" method="post" class="uk-form" id="restore-history-form" isform>
                <div class="uk-form-row">
                    <input type="hidden" name = "id" />
                    <input type="text" class="uk-width-1-1 uk-form-large" name="bTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="任务开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="eTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="任务结束时间" notnull>
                </div>

                <div class="uk-form-row">
                    <input type="number" class="uk-width-1-1 uk-form-large"  name="offset" placeholder="时间偏移量(秒)" notnull>
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" target="role-add-form">save</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<jsp:include page="add.jsp"/>

<script>
    $(function() {
        $(".task-cmd").click(function(){
            var $this = $(this);
            var type_ = 1 * $this.attr("t-type");
            var id_ = $this.attr("t-id");
            if (!confirm("确认执行>>>" + $this.text())) {
                return;
            }
            var url = "";
            if (type_ == 1 || type_ == 2 || type_ == 3 || type_ == 4) {
                $.ajax({
                    type: "POST",
                    url: "/scheduler/cmd.json",
                    data:{"id" : id_, "type" : type_},
                    dataType:"json",
                    success:function (msg) {
                        UIkit.notify("已经执行!!!", {status:'danger', pos:'top-right'});
                        location = location;
                    },
                    error:function (XMLHttpRequest,textStatus,errorThrom){
                        console.log("不明原因造成数据获取失败... ...");
                    }
                });
            } else if(type_ == 6){
                var cron_ = $this.attr("t-cron");
                $("#task-cron-reset-form").find("[name=id]").val(id_);
                $("#task-cron-reset-form").find("[name=newCron]").val(cron_);
                $("#cron-reset-id").click();
            } else if(type_ == 7) {
                $("#restore-history-form").find("[name=id]").val(id_);
                $("#restore-history-id").click();
            }
        });
    });
</script>
