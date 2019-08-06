<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/5/20
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<html>
<head>
    <title>SpeedModify</title>
</head>
<body>
    <form action="/run-plan/change-speed.json" id="modifyForm" method="post" enctype="multipart/form-data" class="uk-form" isform>
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
            <thead>
                <tr>
                    <td>编号</td>
                    <td>类型</td>
                    <td>类别</td>
                    <td>年龄段</td>
                    <td>性别</td>
                    <td>所需时间</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><input class="uk-width-1-1 uk-form-large" type="text" name="id" value="${speedModify.id}" readonly/></td>
                    <td>
                        <select class="uk-width-1-1 uk-form-large" placeholder="类型" name="type" id="type">
                            <option value="0" <c:if test="${speedModify.type == 0}">selected</c:if>>5km</option>
                            <option value="1" <c:if test="${speedModify.type == 1}">selected</c:if>>10km</option>
                            <option value="2" <c:if test="${speedModify.type == 2}">selected</c:if>>半程马拉松</option>
                            <option value="3" <c:if test="${speedModify.type == 3}">selected</c:if>>马拉松</option>
                        </select>
                    </td>
                    <td>
                        <select class="uk-width-1-1 uk-form-large" placeholder="类别" name="classify" id="classify">
                            <option value="0" <c:if test="${speedModify.classify == 0}">selected</c:if>>30岁以下</option>
                            <option value="1" <c:if test="${speedModify.classify == 1}">selected</c:if>>30~40岁</option>
                            <option value="2" <c:if test="${speedModify.classify == 2}">selected</c:if>>40岁以上</option>
                        </select>
                    </td>
                    <td>
                        <select class="uk-width-1-1 uk-form-large" placeholder="年龄段" name="ages" id="ages">
                            <option value="0" <c:if test="${speedModify.ages == 0}">selected</c:if>>30岁以下</option>
                            <option value="1" <c:if test="${speedModify.ages == 1}">selected</c:if>>30~40岁</option>
                            <option value="2" <c:if test="${speedModify.ages == 2}">selected</c:if>>40岁以上</option>
                        </select>
                    </td>
                    <td>
                        <select class="uk-width-1-1 uk-form-large" name="gender" placeholder="性别" id="gender">
                            <option value="1" <c:if test="${speedModify.gender == 1}">selected</c:if>>男</option>
                            <option value="2" <c:if test="${speedModify.gender == 2}">selected</c:if>>女</option>
                        </select>
                    </td>
                    <td><input class="uk-width-1-1 uk-form-large" type="text" id="speed" name="speed" value="${speedModify.speed}"></td>
                </tr>
            </tbody>
        </table>
        <div class="uk-form-row uk-text-right">
            <input type="button" class="uk-button" id="modify" value="modify"/>
 <%--           <input type="hidden" id="rid" name="id" value="${stage.id}">--%>
        </div>
    </form>
<script type="text/javascript">
    $("#modify").click(function(){
        $("form").submit();
    })
</script>
</body>
</html>
