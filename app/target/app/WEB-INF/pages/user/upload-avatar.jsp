<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/17
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="upload-avatar">
    <div class="upload-title">
        <h4>修改头像</h4>
        <div class="upload-close" id="upload-close"></div>
    </div>
    <div class="upload-body">
        <div class="upload-form">
            <h4>请上传图片:</h4>
            <form id="avatar-form" method="POST" action="#" enctype="multipart/form-data">
                <div class="upload-btn">
                    <button onclick="return false">上传头像</button>
                    <input class="uplodefile" name="userfile" id="upload-file" type="file" accept="image/*">
                    <input type="hidden" name="_lan" value="zh"/>
                    <input type="hidden" name="_ch" value="49"/>
                    <input type="hidden" name="_v" value="38"/>
                    <input type="hidden" name="_nw" value="web"/>
                    <input type="hidden" name="_sdk" value="web"/>
                </div>
            </form>
            <label>jpg 或 png，大小不超过2M</label>
            <div class="upload-cancel">
                <button id="upload-cancel">取消</button>
            </div>
        </div>
        <div class="cutting-area">
            <input type="hidden" id="x">
            <input type="hidden" id="y">
            <input type="hidden" id="w">
            <input type="hidden" id="h">
            <input type="hidden" id="pic-path">
        </div>
    </div>
</div>
