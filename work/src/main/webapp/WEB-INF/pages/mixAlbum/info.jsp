<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/3/9
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<div class="uk-container-center uk-body">
    <table class="uk-table uk-table-hover">
        <tbody>
            <tr>
                <td>
                    <div class="uk-grid">
                        <div class="uk-width-1-10">
                            <img src="<lch:build-path url="${data.backImage}"/>" style="max-width: 400px;"/>
                        </div>
                        <div class="uk-width-8-10 uk-flex uk-flex-middle">
                            <div>
                                <label style="font-size: 30px;">${user.title}</label>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><label>操作：</label>
                    <a href="#albumModalModify" data-uk-modal>修改</a>
                </td>
            </tr>
            <tr>
                <td><label>标题：</label><label>${data.title}</label></td>
            </tr>
            <tr>
                <td><label>描述：</label><label>${data.desc}</label></td>
            </tr>
            <tr>
                <td><label>排序：</label><label>${data.sort}</label></td>
            </tr>
            <tr>
                <td>
                    <label>状态：</label>
                    <label>
                        <c:choose>
                            <c:when test="${data.status eq 0}">发布</c:when>
                            <c:when test="${data.status eq 1}">未发布</c:when>
                        </c:choose>
                    </label>
                </td>
            </tr>
            <tr>
                <td><label>添加时间：</label><label><lch:parse-date time="${data.addTime}" pattern="yyyy-MM-dd HH:mm"/></label></td>
            </tr>
        </tbody>
    </table>

    <h3>歌曲</h3>
    <table class="uk-table uk-table-hover">
        <thead>
            <tr>
                <td>编号</td>
                <td>名称</td>
                <td>场景</td>
                <td>时长</td>
                <td>bpm</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${data.mixList}" var="mix">
                <tr>
                    <td>${mix.id}</td>
                    <td><a href="/mix/mix-info.htm?id=${mix.id}">${mix.name}</a></td>
                    <td><lch:dic-name-array type="2" value="${mix.scene}"/></td>
                    <td>${mix.trackLength}</td>
                    <td>${mix.bpm}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>


<jsp:include page="modify.jsp"/>