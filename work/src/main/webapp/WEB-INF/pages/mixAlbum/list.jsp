<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/3/7
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<div class="uk-form-row">
    <a href="#mixBannerAdd" class="uk-button" data-uk-modal>添加</a>
</div>



<div class="uk-overflow-container">
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
        <thead>
            <tr>
                <th>编号</th>
                <th>标题</th>
                <th width="200px">描述</th>
                <th>歌曲编号</th>
                <th>状态</th>
                <th>添加时间</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.result}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td><a href="/mix-album/info.htm?albumId=${item.id}">${item.title}</a></td>
                    <td>${item.desc}</td>
                    <td>
                        <c:forEach var="mid" items="${item.mixIds}">
                            ${mid},
                        </c:forEach>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${item.status eq 0}">发布</c:when>
                            <c:when test="${item.status eq 1}"><label style="color:#fe4e40;">未发布</label></c:when>
                        </c:choose>
                    </td>
                    <td>
                        <lch:parse-date time="${item.addTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>



<%--分页条--%>
<lch:page page="${page}" href="/mix-album/list.htm"></lch:page>

<jsp:include page="add.jsp"/>
