<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/5/24
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    h2 {
        font-family: "Microsoft Yahei", "微软雅黑", "Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
        margin: 5px 0;
        padding: 10px;
        background: #7BC0F4;
        border-left: 5px solid #146BBB;
        color: #333;
        font-weight: 700;
        font-size: 16px;
    }
    h3 {
        font-family: "Microsoft Yahei", "微软雅黑", "Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
        margin: 5px 0 5px 5px;
        padding: 5px;
        background: #F5BDB3;
        border-left: 4px solid #ED4D1E;
        color: #333;
        font-size: 14px;
    }
    .desc p, .param, .result p {
        padding: 0 0 0 15px;
    }
</style>
<h2>
    接口路径:${rootUrl}/${apiDetails.url}
</h2>
<h3>
    描述
</h3>
<div class="desc">
    <p>${apiDetails.desc}</p>
</div>
<h3>参数</h3>
<div class="param">
    <table class="uk-table uk-table-striped">
        <thead>
            <tr>
                <th>参数</th>
                <th>必填</th>
                <th>类型</th>
                <th>备注</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${apiDetails.fieldsList}" var="fields">
                <tr>
                    <td>${fields.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${fields.isNeed == 1}">
                                Y
                            </c:when>
                            <c:otherwise>
                                N
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${fields.type}</td>
                    <td>${fields.desc}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<h3>返回结果</h3>
<div class="result">
    <p>${apiDetails.result}</p>
</div>

