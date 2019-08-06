<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/10
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>
<div class="account-info">
    <div class="account-content">
        <div class="account-bind">
            <h4>绑定账号</h4>
            <label class="phone-bind-label">
                <c:choose>
                    <c:when test="${sessionScope.user.mobile == '' || sessionScope.user.mobile == null}">
                        <div class="item">
                            手机绑定：未绑定
                        </div>
                        <div class="bind-btn">
                            <button id="bind-ph-btn">绑定</button>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.mobile != '' && sessionScope.user.mobile != null}">
                        <div class="item">
                            手机绑定：${sessionScope.user.mobile}
                        </div>
                        <div class="bind-btn">
                            <button id="unbunding-ph-btn">解绑</button>
                        </div>
                    </c:when>
                </c:choose>
            </label>
            <label class="email-bind-label">
                <c:choose>
                    <c:when test="${sessionScope.user.email == '' || sessionScope.user.email == null }">
                        <div class="item">
                            邮箱绑定：未绑定
                        </div>
                        <div class="bind-btn">
                            <button id="bind-em-btn">绑定</button>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.email != '' && sessionScope.user.email != null }">
                        <div class="item">
                            邮箱绑定：${sessionScope.user.email}
                        </div>
                        <div class="bind-btn">
                            <button id="unbunding-em-btn">解绑</button>
                        </div>
                    </c:when>
                </c:choose>
            </label>
            <label class="wechat-bind-label">
                <c:choose>
                    <c:when test="${sessionScope.user.wxName == '' || sessionScope.user.wxName == null}">
                        <div class="item">
                            微信绑定：未绑定
                        </div>
                        <div class="bind-btn">
                            <button id="bind-wx-btn">绑定</button>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.wxName != '' && sessionScope.user.wxName != null}">
                        <div class="item">
                            微信绑定：${sessionScope.user.wxName}
                        </div>
                        <div class="bind-btn">
                            <button id="unbunding-wx-btn">解绑</button>
                        </div>
                    </c:when>
                </c:choose>
            </label>
            <%--<label class="qq-bind-label">
                <c:choose>
                    <c:when test="${sessionScope.user.qqName == '' || sessionScope.user.qqName == null}">
                        <div class="item">
                            QQ绑定：未绑定
                        </div>
                        <div class="bind-btn">
                            <button>绑定</button>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.qqName != '' && sessionScope.user.qqName != null}">
                        <div class="item">
                            QQ绑定：${sessionScope.user.qqName}
                        </div>
                        <div class="bind-btn">
                            <button>绑定</button>
                        </div>
                    </c:when>
                </c:choose>
            </label>--%>
            <%--<label class="weibo-bind-label">
                <c:choose>
                    <c:when test="${sessionScope.user.wbName == '' || sessionScope.user.wbName == null}">
                        <div class="item">
                            微博绑定：未绑定
                        </div>
                        <div class="bind-btn">
                            <button>绑定</button>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.wbName != '' && sessionScope.user.wbName != null}">
                        <div class="item">
                            微博绑定：${sessionScope.user.wbName}
                        </div>
                    </c:when>
                </c:choose>

            </label>--%>
        </div>
        <div class="account-modify">
            <h4>账号密码</h4>
            <label class="account-modify-label">
                <div class="item">
                    修改密码
                </div>
                <div class="modify-btn">
                    <button id="mdf-bar-btn">修改</button>
                </div>
            </label>
        </div>
    </div>
</div>
