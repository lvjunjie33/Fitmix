<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/7/6
  Time: 18:24
  To change this template use File | Settings | File Templates.
  标准赛事
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<c:if test="${!empty groups}">
    <div>
        <div style="margin: 20px 20px 5px 20px;">
            <form action="/activity/save-activity-sign-up.json" method="post" isform>
                <div class="form-group">
                    <select class="form-control" name="group">
                        <c:forEach var="group" items="${groups}">
                            <option value="${group.id}">${group.groupName}</option>
                        </c:forEach>
                    </select>
                    <div id = "bao-ming-info" style = "padding-top: 12px;"></div>
                </div>

                <div class="form-group">
                    <strong>通知信息</strong>
                </div>

                <c:choose>
                    <c:when test="${!empty appUserInfo}">
                        <div class="form-group">
                            <input class="form-control" type="text" name="mobileName" maxlength="20" placeholder="联系人姓名" notnull="" value = "${appUserInfo.userRealInfo.name}">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="mobilePhone" placeholder="联系人电话" ismobile="" value="${appUserInfo.userRealInfo.mobilePhone}">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="email" name="email" placeholder="邮箱" email="" value="${appUserInfo.userRealInfo.email}">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="emergencyName" placeholder="紧急联系人姓名" notnull="" value="${appUserInfo.userRealInfo.emergencyName}">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="emergencyPhone" placeholder="紧急联系人电话" ismobile="" value="${appUserInfo.userRealInfo.emergencyPhone}">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <input class="form-control" type="text" name="mobileName" maxlength="20" placeholder="联系人姓名" notnull="" value = "">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="mobilePhone" placeholder="联系人电话" ismobile="" value="">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="email" name="email" placeholder="邮箱" email="" value="">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="emergencyName" placeholder="紧急联系人姓名" notnull="" value="">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="emergencyPhone" placeholder="紧急联系人电话" ismobile="" value="">
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="form-group">
                    <strong>其他信息</strong>
                </div>
                <div class="form-group">
                    <input type="hidden" name="memberJson">
                    <p>参赛金额：<label id="pay-money">0</label></p>
                    <p>支付方式：微信支付、支付宝</p>
                    <label><input id = "agree-clause-id" type="checkbox" checked="checked" name = "agreeClause">&nbsp;&nbsp;我同意并接受 <a href="/activity/to-activity-sm.htm">查看说明</a></label>
                </div>
            </form>
        </div>
    </div>
    <%--  register btn submit  --%>
    <div id="register">
        <button type = "submit" class="btn-radius" id = "save-activity-sign-up-submit">立即报名</button>
    </div>

    <%-- 用户报名信息模版 --%>
    <c:forEach var="group" items="${groups}">
        <div style = "border:1px solid red" hidden id = "group${group.id}" money = "${group.needMoney}">
            <c:forEach var="memberName" items="${group.memberNames}">
                <c:choose>
                    <%-- 单人报名填充报名信息 --%>
                    <c:when test="${fn:length(group.memberNames) == 1 and !empty appUserInfo}">
                        <div class = "group-member" group-id = "${group.id}">
                            <div class="form-group">
                                <strong>${memberName}</strong>
                            </div>
                            <div class="form-group">
                                <input type = "hidden" value="${memberName}" name = "groupMemberName" />
                                <input class="form-control" type="text" name="name" placeholder="姓名" notnull="" value="${appUserInfo.userRealInfo.name}">
                            </div>
                            <div class="form-group">
                                <select class="form-control" name="gender" placeholder="性别" notnull="">
                                    <option value="">--- 选择性别 ---</option>
                                    <option value="男" <c:if test="${appUserInfo.userRealInfo.gender == 1}">selected</c:if>>男</option>
                                    <option value="女" <c:if test="${appUserInfo.userRealInfo.gender == 2}">selected</c:if>>女</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <select class="form-control" name="bloodType" placeholder="血型" notnull="">
                                    <option value="">--- 选择血型 ---</option>
                                    <option value="A" <c:if test="${appUserInfo.userRealInfo.bloodType == 'A'}">selected</c:if>>A型血</option>
                                    <option value="B" <c:if test="${appUserInfo.userRealInfo.bloodType == 'B'}">selected</c:if>>B型血</option>
                                    <option value="AB" <c:if test="${appUserInfo.userRealInfo.bloodType == 'AB'}">selected</c:if>>AB型血</option>
                                    <option value="O" <c:if test="${appUserInfo.userRealInfo.bloodType == 'O'}">selected</c:if>>O型血</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <select class="form-control" name="clothesSize" placeholder="衣服尺码" notnull="">
                                    <option value="">--- 选择尺码 ---</option>
                                    <option value="S" <c:if test="${appUserInfo.userRealInfo.clothesSize == 'S'}">selected</c:if>>S</option>
                                    <option value="M" <c:if test="${appUserInfo.userRealInfo.clothesSize == 'M'}">selected</c:if>>M</option>
                                    <option value="L" <c:if test="${appUserInfo.userRealInfo.clothesSize == 'L'}">selected</c:if>>L</option>
                                    <option value="XL" <c:if test="${appUserInfo.userRealInfo.clothesSize == 'XL'}">selected</c:if>>XL</option>
                                    <option value="2XL" <c:if test="${appUserInfo.userRealInfo.clothesSize == '2XL'}">selected</c:if>>2XL</option>
                                    <option value="3XL" <c:if test="${appUserInfo.userRealInfo.clothesSize == '3XL'}">selected</c:if>>3XL</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" type="number" name="memo" maxlength="3" placeholder="备注"></textarea>
                            </div>

                            <div class="form-group">
                                <strong>证件信息</strong>
                            </div>
                            <div class="form-group">
                                <select class="form-control" name="idCardType" notnull="">
                                    <option value="身份证" selected>身份证</option>
                                    <option value="护照">护照</option>
                                    <option value="回乡证">回乡证</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="text" name="idCard" maxlength="20" placeholder="证件信息" notnull="" value="${appUserInfo.userRealInfo.idCard}" userCard>
                            </div>
                        </div>
                    </c:when>
                    <%-- 团体报名不填充报名信息 --%>
                    <c:otherwise>
                        <div class = "group-member" group-id = "${group.id}">
                            <div class="form-group">
                                <strong>${memberName}</strong>
                            </div>
                            <div class="form-group">
                                <input type = "hidden" value="${memberName}" name = "groupMemberName" />
                                <input class="form-control" type="text" name="name" placeholder="姓名" notnull="" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="text" name="memberMobile" placeholder="手机号码" ismobile="" notnull="" value="">
                            </div>
                            <div class="form-group">
                                <select class="form-control" name="gender" placeholder="性别" notnull="">
                                    <option value="">--- 选择性别 ---</option>
                                    <option value="男" >男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <select class="form-control" name="bloodType" placeholder="血型" notnull="">
                                    <option value="">--- 选择血型 ---</option>
                                    <option value="A">A型血</option>
                                    <option value="B">B型血</option>
                                    <option value="AB">AB型血</option>
                                    <option value="O">O型血</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <select class="form-control" name="clothesSize" placeholder="衣服尺码" notnull="">
                                    <option value="">--- 选择尺码 ---</option>
                                    <option value="S" >S</option>
                                    <option value="M">M</option>
                                    <option value="L">L</option>
                                    <option value="XL">XL</option>
                                    <option value="2XL">2XL</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" type="number" name="memo" maxlength="3" placeholder="备注"></textarea>
                            </div>

                            <div class="form-group">
                                <strong>证件信息</strong>
                            </div>
                            <div class="form-group">
                                <select class="form-control" name="idCardType" notnull="">
                                    <option value="身份证" selected>身份证</option>
                                    <option value="护照">护照</option>
                                    <option value="回乡证">回乡证</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="text" name="idCard" maxlength="20" placeholder="证件信息" notnull="" value="" userCard>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:forEach>
</c:if>
