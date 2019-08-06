<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/12/1
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<div class="uk-container-center uk-body">

    <h1>Mix 音乐推荐 管理</h1>
    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr>
                <th style="width: 100px;">
                    ID/编号
                </th>
                <th style="width: 200px;">名称</th>
                <th style="width: 100px">场景</th>
                <th style="width: 100px;">曲风</th>
                <th style="width: 100px;">开始时间</th>
                <th style="width: 100px;">结束时间</th>
                <th style="width: 50px;">结束时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="mix" items="${mixs}">
                <tr>
                    <td><label name="id">${mix.id}</label>/${mix.customIdentification}</td>
                    <td><a href="/mix/mix-info.htm?id=${mix.id}" target="_blank">${mix.name}</a></td>
                    <c:choose>
                        <c:when test="${mix.type == 2}">
                            <td><lch:dic-name-array type="9" value="${mix.scene}"/></td>
                            <td><lch:dic-name-array type="7" value="${mix.genre}"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><lch:dic-name-array type="2" value="${mix.scene}"/></td>
                            <td><lch:dic-name-array type="7" value="${mix.genre}"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td><lch:parse-date time="${mix.recommendBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><lch:parse-date time="${mix.recommendEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><a href="#mix-recommend" class="mix-recommend-cls" tx-id = "${mix.id}" data-uk-modal>续期</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<jsp:include page="mix-other.jsp"/>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<script type="text/javascript">

    $(function(){
        $(".mix-recommend-cls").click(function() {
            var $this = $(this)

            var _id = $this.attr("tx-id");
            $("#mix-recommend-form").find("[name=mid]").val(_id);
        });
    });

</script>