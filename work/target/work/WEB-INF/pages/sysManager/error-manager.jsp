<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/4/22
  Time: 14:27
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
</style>

<form action="/sys/error-log-list.htm" method="get" class="uk-form">
    <div class="uk-form-row uk-grid">

        <div class="uk-form-row uk-width-1-6">
            <label>系统：</label>
            <select name="filter[sys]">
                <option value="">请选择</option>
                <option value="1" <c:if test="${page.filter.sys == 1}">selected</c:if>>app</option>
                <option value="2" <c:if test="${page.filter.sys == 2}">selected</c:if>>work</option>
                <option value="3" <c:if test="${page.filter.sys == 3}">selected</c:if>>scheduler</option>
                <option value="4" <c:if test="${page.filter.sys == 4}">selected</c:if>>mq-server</option>
                <option value="5" <c:if test="${page.filter.sys == 5}">selected</c:if>>Android</option>
                <option value="6" <c:if test="${page.filter.sys == 6}">selected</c:if>>ios</option>
            </select>
        </div>

        <div class="uk-form-row uk-width-1-6">
            <label>解决状态：</label>
            <select name="filter[solveStatus]">
                <option value="">请选择</option>
                <option value="0" <c:if test="${page.filter.solveStatus == 0}">selected</c:if>>未解决</option>
                <option value="1" <c:if test="${page.filter.solveStatus == 1}">selected</c:if>>解决</option>
            </select>
        </div>

        <div class="uk-form-row uk-width-2-6">
            <label>时间：</label>
            <label><input type="text" name="filter[beginTime]" value="${page.filter.beginTime}" placeholder="开始时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
            -
            <label><input type="text" name="filter[endTime]" value="${page.filter.endTime}" placeholder="结束时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
        </div>

        <div class="uk-form-row uk-width-2-6">
            <button class="uk-button uk-button-primary">查询</button>
        </div>

        <div class="uk-form-row uk-width-2-10">
            <label>访问ip</label>
            <input type="text" name="filter[ip]" value="${page.filter.ip}" placeholder="来访ip"/>
        </div>

        <div class="uk-form-row uk-width-2-10">
            <label>接口名</label>
            <input type="text" name="filter[matchedPath]" value="${page.filter.matchedPath}" placeholder="接口名"/>
        </div>
    </div>
</form>

<table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
    <thead>
    <tr>
        <th style="width: 300px">
            <label title="全选"><input class = "log-status" id = "all-select" type="checkbox" value="-1" to-checked >&nbsp;&nbsp;全选</label>
            解决状态--系统类型--请求方式--记录时间--匹配地址--访问来源
        </th>
        <th>异常信息</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.result}" var="item">
        <tr>
            <td>
                <div class="row-div-node">
                    <label title="说明:解决状态表示该问题已经被修复了"><input class = "log-status child-node" <c:if test="${item.solveStatus == 1}">checked</c:if> type="checkbox" value="1" to-checked log-id = "${item.id}">&nbsp;&nbsp;解决状态</label>
                    <c:choose>
                            <c:when test="${item.sys == 1}">app</c:when>
                            <c:when test="${item.sys == 2}">work</c:when>
                            <c:when test="${item.sys == 3}">scheduler</c:when>
                            <c:when test="${item.sys == 4}">mq-server</c:when>
                            <c:when test="${item.sys == 5}">Android</c:when>
                            <c:when test="${item.sys == 6}">iOS</c:when>
                    </c:choose>
                    &nbsp;&nbsp;${item.requestMethod}
                    &nbsp;&nbsp;<fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                    &nbsp;&nbsp;${item.matchedPath}
                 </div>
                <div class = "row-div-node" style="white-space:pre-line">${item.ip}&nbsp;&nbsp;${item.ua}</div>
            </td>
            <td>
                <pre style="<c:if test="${item.solveStatus==0}">color:red</c:if>"><lch:html-text-chk text="${item.exception}" /></pre>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--分页条--%>
<lch:page page="${page}" href="/sys/error-log-list.htm"></lch:page>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>


<script type="text/javascript">

    $(function(){
        $(".child-node").click(function(){
            var $this = $(this);
            var isChecked = $this.attr("checked");
            if (isChecked == undefined) {
                isChecked = 1;
                $this.attr("checked", true);
            } else {
                isChecked = 0;
                $this.attr("checked", false);
            }
            $.ajax({
                type: "POST",
                url: "/sys/modify-log-status.json",
                data:{"solveStatus": isChecked, "id": $this.attr("log-id")},
                dataType:"json",
                success:function (msg) {
                    //editor.$txt.html(msg.htmlContent);
                },
                error:function (XMLHttpRequest,textStatus,errorThrom){
                    console.log("不明原因造成数据获取失败... ...");
                }
            });
        });
    });

    $(function(){
        $("#all-select").click(function(){
            var $this = $(this);
            var isChecked = $this.attr("checked");
            if (isChecked == undefined) {
                isChecked = 1;
                $($(".child-node")).each(function(i, v) {
                    var $t = $(this);
                    $t.click();
                })
            } else {
                $($(".log-status")).each(function(i, v) {
                    var $t = $(this);
                    $t.click();
                })
            }
        });
    });

</script>