<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/9/4
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>

<div class="modify-psw">
    <div class="mdf-title">
        <h4>修改密码</h4>
        <div class="mdf-close" id="mdf-close"></div>
    </div>
    <div class="mdf-body">
        <div class="mdf-form" id="mdf-form">
            <div class="mdf-main-content" >
                <div>
                    <label>
                        <h4>原密码</h4>
                        <input class="oldpsw" type="password" placeholder="输入原密码"/>
                    </label>
                    <label class="wap-resend-label">
                        <h4>新密码</h4>
                        <input class="newpsw" type="password" placeholder="输入原密码"/>
                        <input class="newpsw1" type="password" placeholder="重复新密码"/>
                    </label>
                </div>
                <div class="mdf-btn">
                    <button id="mdf-btn">确定</button>
                    <button id="mdf-cancel-btn">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
