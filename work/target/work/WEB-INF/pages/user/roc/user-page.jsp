<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/3/1
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

    .short {
        width: 75px;
    }

    .time{
        width: 100px;
    }

</style>

<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action=" /user/user-page-roc.htm" method="POST" class="uk-form">
            <div class="uk-form-row uk-grid">
                <div class="uk-width-1-5">
                    <label>身高：</label>
                    <label><input type="number" name="filter[beginHeight]" class="short" value="${page.filter.beginHeight}" placeholder="begin"/></label>
                    -
                    <label><input type="number" name="filter[endHeight]" class="short" value="${page.filter.endHeight}" placeholder="end"/></label>
                </div>

                <div class="uk-width-1-5">
                    <label>体重：</label>
                    <label><input type="number" name="filter[beginWeight]" class="short" value="${page.filter.beginWeight}" placeholder="begin"/></label>
                    -
                    <label><input type="number" name="filter[endWeight]" class="short" value="${page.filter.endWeight}" placeholder="end"/></label>
                </div>

                <div class="uk-width-1-5">
                    <label>年龄：</label>
                    <label><input type="number" name="filter[bAge]" class="short" value="${page.filter.bAge}" placeholder="begin"/></label>
                    -
                    <label><input type="number" name="filter[eAge]" class="short" value="${page.filter.eAge}" placeholder="end"/></label>
                </div>

                <%--<div class="uk-width-2-5">--%>
                    <%--<label>时间：</label>--%>
                    <%--<label><input type="text" name="filter[bTime]" value="${page.filter.bTime}" placeholder="注册时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>--%>
                    <%-----%>
                    <%--<label><input type="text" name="filter[eTime]" value="${page.filter.eTime}" placeholder="注册时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>--%>
                <%--</div>--%>
            </div>

            <div class="uk-form-row uk-grid">
                <div class="uk-width-1-5">
                    <label>编号：</label>
                    <label><input type="number" name="filter[id]" value="${page.filter.id}" placeholder="用户编号..."/></label>
                </div>
                <div class="uk-width-1-5">
                    <label>名称：</label>
                    <label><input type="text" name="filter[name]" value="${page.filter.name}" placeholder="用户名称..."/></label>
                </div>
                <%--<div class="uk-width-1-5">--%>
                    <%--<label>号码：</label>--%>
                    <%--<label><input type="text" name="filter[mobile]" value="${page.filter.mobile}" placeholder="用户手机号码..."/></label>--%>
                <%--</div>--%>
                <div class="uk-width-1-5">
                    <label>邮箱：</label>
                    <label><input type="text" name="filter[email]" value="${page.filter.email}" placeholder="邮箱..."/></label>
                </div>

                <button type="submit" class="uk-button uk-button-primary">查询</button>

            </div>
        </form>

        <div class="uk-overflow-container">
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>名称</th>
                    <%--<th>号码</th>--%>
                    <th>性别</th>
                    <th>身高</th>
                    <th>体重</th>
                    <th>年龄</th>
                    <th>城市</th>
                    <th style="width: 120px;">email</th>
                    <th style="width: 120px">注册时间</th>
                    <th>状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${page.result}">
                    <tr>
                        <td><a href="/user/user-info-roc.htm?uid=${user.id}">${user.id}</a></td>
                        <td>${user.name}</td>
                        <%--<td>${user.mobile}</td>--%>
                        <td><lch:dic-name type="1" value="${user.gender}"/></td>
                        <td>${user.height}</td>
                        <td>${user.weight}</td>
                        <td>${user.age}</td>
                        <td>${user.registerTaoBaoIp.city}</td>
                        <td>${user.email}</td>
                        <td><lch:parse-date time="${user.addTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${user.state eq 1}">激活</c:when>
                                <c:when test="${user.state eq 1}"><label style="color: red;">禁用</label></c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%--分页条--%>
    <lch:page page="${page}" href="/user/user-page-roc.htm"></lch:page>
</div>
<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<script type="text/javascript">


    $.each($(".user-channel"), function(index, data){
        $(this).html(dictionaryName($(this).attr("data")))
    });

    $.each($(".user-registerType"), function(index, data){
        $(this).html(registerTypeName($(this).attr("data")))
    });

    function dictionaryName(data) {
        var channelDictionary = ${channelDictionary};
        for (var i = 0; i < channelDictionary.length; i++) {
            var dic = channelDictionary[i];
            console.info(dic, dic.value);
            if (data == "appStore") { // 处理 中文 渠道
                return "appStore";
            }
            else if ((data == dic.value) || (/\s+/.test(data) && parseInt(data) == dic.value)) { // app 上传渠道 001 or 1 ，字典 1 处理不兼容
                return dic.name;
            }
        }
        return "<label style='color: #ff567f;'>error({0})</label>".format(data);
    }


    function registerTypeName(data) {
        var name = "";
        switch (data) {
            case "1":
                name = "app";
                break;
            case "2":
                name = "QQ";
                break;
            case "3":
                name = "微信";
                break;
            case "4":
                name = "微博";
                break;
        }
        return name;
    }
</script>