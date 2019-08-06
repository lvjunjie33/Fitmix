<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<style>
    .comment{margin: 0px 20px;color: #993366;}
</style>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <h2>彩蛋 详情</h2>
    </div>
    <form action="/mix/modify.json" method="post" class="uk-form" enctype="multipart/form-data" isform>
        <table class="uk-table uk-table-hover">
            <tbody>
                <c:if test="${surprise.type == 1}">
                    <tr>
                        <td><label>静态图片：</label><label><a class="uk-thumbnail uk-thumbnail-mini"><img src="<lch:build-path url="${surprise.imgUrl}"/>"></a></label></td>
                    </tr>
                </c:if>
                <tr>
                    <td><label>编号：</label><label>${surprise.id}</label></td>
                </tr>
                <tr>
                    <td><label>名称：</label><label>${surprise.name}</label></td>
                </tr>
                <tr>
                    <td><label>类型：</label>
                        <label>
                            <c:choose>
                                <c:when test="${surprise.type == 0}">
                                    默认图片
                                </c:when>
                                <c:when test="${surprise.type == 1}">
                                    静态图片
                                </c:when>
                                <c:when test="${surprise.type == 2}">
                                    文字
                                </c:when>
                                <c:when test="${surprise.type == 3}">
                                    天气
                                </c:when>
                                <c:otherwise>
                                    默认图片
                                </c:otherwise>
                            </c:choose>
                        </label>
                    </td>
                </tr>
                <c:if test="${surprise.type == 2}">
                    <tr>
                        <td><label>标题：</label><label>${surprise.title}</label></td>
                    </tr>
                    <tr>
                        <td><label>内容：</label><label>${surprise.content}</label></td>
                    </tr>
                </c:if>
                <tr>
                    <td><label>开始时间：</label><label><lch:parse-date time="${surprise.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></label></td>
                </tr>
                <tr>
                    <td><label>结束时间：</label><label><lch:parse-date time="${surprise.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></label></td>
                </tr>

                <tr>
                    <td><label>添加时间：</label><label><lch:parse-date time="${surprise.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></label></td>
                </tr>

            </tbody>
        </table>
        <div class="uk-form-row">
            <a href="/surprise/surprise-modify.htm?id=${surprise.id}" class="uk-button uk-button-danger" surprise-modify>修改</a>
        </div>
    </form>
</div>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
