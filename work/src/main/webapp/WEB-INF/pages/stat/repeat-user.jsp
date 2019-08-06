<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/1/17
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

</style>

<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action="/stat/repeat/user/list.htm" method="get" class="uk-form">
            <div class="uk-form-row uk-grid">
                <div class="uk-width-2-5">
                    <label>条数：</label>
                    <label><input type="number" name="limit" value="${limit}" placeholder="条数"/></label>
                </div>
                <div class="uk-width-2-5">
                    <label>bId：</label>
                    <label><input type="number" name="bId" value="${bId}" placeholder="bId"/></label>
                </div>
            </div>

            <div class="uk-form-row uk-grid">
                <div class="uk-width-2-5">
                    <label>手机号码：</label>
                    <label><input type="text" name="mobile" value="${mobile}" placeholder="手机号码"/></label>
                </div>
                <div class="uk-width-1-5">
                    <label>邮箱：</label>
                    <label><input type="text" name="email" value="${email}" placeholder="邮箱"/></label>
                </div>
                <button type="submit" class="uk-button uk-button-primary">查询</button>&nbsp;&nbsp;
            </div>
        </form>

        <div class="uk-overflow-container">
            <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
                <thead>
                <tr>
                    <th>忽略</th>
                    <th>编号</th>
                    <th>用户编号</th>
                    <th>手机号码</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="stat" items="${values}">
                    <tr>
                        <td><a href="javaScript:void(0)" onclick="removeRepeat(${stat.id})" class="uk-button uk-button-primary">关闭</a></td>
                        <td>${stat.id}</td>
                        <td><a href="/user-run/list.htm?filter[uid]=${stat.values.uid}" target="_blank">${stat.values.uid}</a></td>
                        <td>${stat.values.mobile}</td>
                        <td>${stat.values.email}</td>
                        <td>
                            <a href="javaScript:void(0)" onclick="deleteRepeat(${stat.id}, ${stat.values.uid}, '${stat.values.mobile}', '${stat.values.email}')" class="uk-button uk-button-primary">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">

    function deleteRepeat(sid, uid, mobile, email) {
        var d = "sid=" + sid + "&uid=" + uid;
        if (mobile == null || mobile == undefined || mobile == '') {

        } else {
            d += "&mobile=" + mobile;
        }
        if (email == null || email == undefined || email == '') {

        } else {
            d += "&email=" + email
        }
        $.ajax({
            type: "POST",
            url: "/modify/repeat/user.json",
            data: d,
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

    function removeRepeat(sid) {
        $.ajax({
            type: "POST",
            url: "/remove/repeat/user.json",
            data: "sid=" + sid,
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

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>