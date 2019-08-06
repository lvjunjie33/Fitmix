<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/1/29
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>


<style>
    .btn-style:hover {
        text-decoration:underline;
    }
    .underline-cls {
        text-decoration:underline
    }

    .h-center-cls {
        text-align: center;
    }
</style>

<div class="uk-container-center uk-body">
    <table class="uk-table uk-table-hover">
        <tbody>
        <tr>
            <td><label>编号：</label></td>
            <td><label>${runMix.id}</label></td>
            <td><label>添加时间：</label></td>
            <td><label><lch:parse-date time="${runMix.addTime}" pattern="yyyy-MM-dd HH:mm"/></label></td>
        </tr>
        <tr>
            <td><label>标题：</label></td>
            <td>
                <label>
                    <a class="underline-cls" href="#run-mix-modify-title" data-uk-modal>
                        ${runMix.title}
                    </a>
                </label>
            </td>
            <td><label>描述：</label></td>
            <td>
                <label>
                    <a class="underline-cls" href="#run-mix-modify-des" data-uk-modal>
                        ${runMix.des}
                    </a>
                </label>
            </td>
        </tr>
        <tr>
            <td><label>BPM：</label></td>
            <td>
                <label>
                    <a class="underline-cls" href="#run-mix-modify-bpm"  data-uk-modal>
                        ${runMix.bpm}
                    </a>
                    &nbsp;&nbsp;
                    <input disabled="disabled" type="checkbox" name="bpmIsDouble" <c:if test="${runMix.bpmIsDouble eq 1}">checked</c:if> />&nbsp;翻倍
                </label>
            </td>
            <td><label>时长：</label></td>
            <td>
                <label>
                    <a class="underline-cls" href="#run-mix-modify-duration"  data-uk-modal>
                        ${runMix.duration}
                    </a>
                    &nbsp;秒
                </label>
            </td>
        </tr>
        <tr>
            <td><label>状态：</label></td>
            <td>
                <label>
                    <a class="underline-cls" href="#run-mix-modify-status"  data-uk-modal>
                        <c:choose>
                            <c:when test="${runMix.status eq 1}">正常</c:when>
                            <c:otherwise>废弃</c:otherwise>
                        </c:choose>
                    </a>
                </label>
            </td>
            <td><label>发布：</label></td>
            <td>
                <label>
                    <a class="underline-cls" href="#run-mix-modify-watch"  data-uk-modal>
                        <c:choose>
                            <c:when test="${runMix.watch eq 1}">发布</c:when>
                            <c:otherwise>未发布</c:otherwise>
                        </c:choose>
                    </a>
                </label>
            </td>
        </tr>
        <tr>
            <td><label>能量级别：</label></td>
            <td>
                <label>
                    <a class="underline-cls" href="#run-mix-modify-energyLevel"  data-uk-modal>
                        ${runMix.energyLevel}
                    </a>
                </label>
            </td>
            <td><label>曲风：</label></td>
            <td>
                <label>
                    <a class="underline-cls"  href="#run-mix-modify-songStyle"  data-uk-modal>
                        <c:choose>
                            <c:when test="${runMix.songStyle eq 1}">
                                流行
                            </c:when>
                            <c:when test="${runMix.songStyle eq 2}">
                                流行摇滚
                            </c:when>
                            <c:when test="${runMix.songStyle eq 3}">
                                电子
                            </c:when>
                            <c:when test="${runMix.songStyle eq 4}">
                                摇滚
                            </c:when>
                            <c:when test="${runMix.songStyle eq 5}">
                                爵士
                            </c:when>
                            <c:when test="${runMix.songStyle eq 6}">
                                古典
                            </c:when>
                            <c:when test="${runMix.songStyle eq 7}">
                                纯音乐
                            </c:when>
                        </c:choose>
                    </a>
                </label>
            </td>
        </tr>
        <tr>
            <td><label>感情色彩：</label></td>
            <td style="background-color: ${runMix.colorTag}" colspan="3">
                <a class="underline-cls" href="#run-mix-modify-colorTag"  data-uk-modal>
                    <c:choose>
                        <c:when test="${runMix.emotion eq 1}">
                            兴奋
                        </c:when>
                        <c:when test="${runMix.emotion eq 2}">
                            愉快
                        </c:when>
                        <c:when test="${runMix.emotion eq 3}">
                            平和
                        </c:when>
                        <c:when test="${runMix.emotion eq 4}">
                            悲伤
                        </c:when>
                    </c:choose>
                </a>
            </td>
        </tr>
        <tr>
            <td><label>封面：</label></td>
            <td>
                <img src="${runMix.imgLink}" style="width: 300px;"/>
                &nbsp;&nbsp;&nbsp;
                <a  href="#run-mix-img-link-modify"  data-uk-modal>编辑</a>
            </td>
        </tr>
        <tr>
            <td><label>Mix：</label></td>
            <td>
                <c:if test="${!empty runMix.link}">
                    <audio controls>
                        <source src="${runMix.link}" type="audio/mpeg">
                    </audio>
                </c:if>
                &nbsp;&nbsp;&nbsp;
                <a href="#run-mix-link-modify" data-uk-modal>编辑</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
<jsp:include page="./other.jsp"/>