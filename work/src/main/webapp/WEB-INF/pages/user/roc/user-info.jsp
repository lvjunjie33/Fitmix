<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/3/1
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<style>

    .btn-style:hover {
        text-decoration:underline;
    }
</style>

<div class="uk-container-center uk-body">
    <table class="uk-table uk-table-hover">
        <tbody>
        <tr>
            <td>
                <div class="uk-grid">
                    <div class="uk-width-1-10">
                        <img src="${user.avatar}" class="uk-icon-button" style="width: 100px;height: 100px;"/>
                    </div>
                    <div class="uk-width-8-10 uk-flex uk-flex-middle">
                        <div>
                            <label style="font-size: 30px;">${user.name}</label>
                            <br/><br/>
                            <label style="color: #696969;">${user.taoBaoIp.region}.${user.taoBaoIp.city}.${user.taoBaoIp.county}.${user.taoBaoIp.area} &nbsp;&nbsp; ${user.taoBaoIp.ip}</label>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <a href="/user/roc/run/page.htm?filter[uid]=${user.id}&filter[type]=1" target="_blank">查询用户运动数据</a>
            </td>
        </tr>
        <tr>
            <td><label>编号：</label><label>${user.id}</label></td>
        </tr>
        <tr>
            <td><label>名称：</label><label>${user.name}</label></td>
        </tr>
        <tr>
            <td><label>性别：</label><label><lch:dic-name type="1" value="${user.gender}"/></label></td>
        </tr>
        <tr>
            <td><label>年龄：</label><label>${user.age}</label></td>
        </tr>
        <tr>
            <td><label>身高：</label><label>${user.height}</label></td>
        </tr>
        <tr>
            <td><label>体重：</label><label>${user.weight}</label></td>
        </tr>
        <tr>
            <td>
                <label>类型：</label>
                <c:choose>
                    <c:when test="${user.type eq 1}">
                        <label>kg/cm</label>
                    </c:when>
                    <c:when test="${user.type eq 2}">
                        <label>ib/in</label>
                    </c:when>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td><label>mail：</label><label>${user.email}</label></td>
        </tr>
        <tr>
            <td><label>&nbsp;sdk：</label><label>${user.sdk}</label></td>
        </tr>

        <tr>
            <td>
                <label>终端类型：</label>
                <label>
                    <c:choose>
                        <c:when test="${user.terminal eq 1}">
                            <label>app 登录</label>
                        </c:when>
                        <c:when test="${user.terminal eq 2}">
                            <label>QQ 登录</label>
                        </c:when>
                        <c:when test="${user.terminal eq 3}">
                            <label>微信 登录</label>
                        </c:when>
                        <c:when test="${user.terminal eq 4}">
                            <label>微博 登录</label>
                        </c:when>
                    </c:choose>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <label>手机型号：</label>
                <label>${user.mobileType}</label>
            </td>
        </tr>
        <tr>
            <td><label>最后登录：</label><label><lch:parse-date time="${user.loginTime}" pattern="yyyy-MM-dd HH:mm"/></label></td>
        </tr>
        <tr>
            <td>
                <label>注册方式：</label>
                <label>
                    <c:choose>
                        <c:when test="${user.registerType eq 1}">
                            <label>app 注册</label>
                        </c:when>
                        <c:when test="${user.registerType eq 2}">
                            <label>QQ 注册</label>
                        </c:when>
                        <c:when test="${user.registerType eq 3}">
                            <label>微信 注册</label>
                        </c:when>
                        <c:when test="${user.registerType eq 4}">
                            <label>微博 注册</label>
                        </c:when>
                    </c:choose>
                </label>
            </td>
        </tr>
        <tr>
            <td><label>注册时间：</label><label><lch:parse-date time="${user.addTime}" pattern="yyyy-MM-dd HH:mm"/></label></td>
        </tr>
        <tr>
            <td>
                <label>登陆地区：</label>
                ${user.taoBaoIp.region}.${user.taoBaoIp.city}.${user.taoBaoIp.county}.${user.taoBaoIp.area} &nbsp;&nbsp; ${user.taoBaoIp.ip}
            </td>
        </tr>
        <tr>
            <td>
                <label>注册地区：</label>
                ${user.registerTaoBaoIp.region}.${user.registerTaoBaoIp.city}.${user.registerTaoBaoIp.county}.${user.registerTaoBaoIp.area} &nbsp;&nbsp; ${user.registerTaoBaoIp.ip}
            </td>
        </tr>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<script type="text/javascript">

    $(".registerChannel").html(dictionaryName($(".registerChannel").attr("data")));

    function dictionaryName(data) {
        var channelDictionary = ${channelDictionary};
        for (var i = 0; i < channelDictionary.length; i++) {
            var dic = channelDictionary[i];
            if (data == "appStore") { // 处理 中文 渠道
                return "appStore";
            }
            else if ((data == dic.value) || (/\s+/.test(data) && parseInt(data) == dic.value)) {  // app 上传渠道 001 or 1 ，字典 1 处理不兼容
                return dic.name;
            }
        }
        return "<label style='color: #ff567f;'>error({0})</label>".format(data);
    }

</script>