
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/28
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <!-- 禁止缩放 -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title></title>

  <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
  <script src="/static/jquery-1.11.2.min.js"></script>
  <script type="text/javascript" src="/static/jquery.form.js"></script>

  <style type="text/css">

    body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

    body{
      margin: 0px 15px 20px 15px;
      text-align: center;
    }

    .teamName{
      color: #56b6d3
    }
  </style>
</head>
<body>

  <h3 class="teamName">${runTeam.teamName}</h3>

  <form action="/activity/20150910/user/activity/add/data.json" method="post" isform>
      <div class="form-group">
        <input class="form-control" type="text" name="name" placeholder="姓名" notNull>
      </div>
      <div class="form-group">
          <select class="form-control" name="gender">
              <option value="男">男</option>
              <option value="女">女</option>
          </select>
      </div>
      <div class="form-group">
         <input class="form-control" type="number" name="age" maxlength="3" placeholder="年龄" notNull>
      </div>
      <div class="form-group">
         <input class="form-control" type="text" name="mobile" placeholder="手机号" isMobile>
      </div>
      <div class="form-group">
          <select class="form-control" name="bloodType">
              <option value="A型血">A型血</option>
              <option value="B型血">B型血</option>
              <option value="AB型血">AB型血</option>
              <option value="O型血">O型血</option>
          </select>
        <%--<input class="form-control" type="text" name="bloodType" placeholder="血型" notNull>--%>
      </div>
      <div class="form-group">
        <input class="form-control" type="text" name="userCard" placeholder="身份证" userCard>
      </div>
      <div class="form-group">
        <input class="form-control" type="text" name="professional" placeholder="职业" notNull>
      </div>

      <div class="form-group">
        <input class="form-control" type="text" name="communicationAddress" placeholder="通讯地址" notNull>
      </div>
      <div class="form-group">
          <select class="form-control" name="clothesSize" placeholder="衣服尺码">
              <option value="S（155-160cm）">S（155-160cm）尺码</option>
              <option value="M（160-165cm）">M（160-165cm）尺码</option>
              <option value="L（165-170cm）">L（165-170cm）尺码</option>
              <option value="XL（170-175cm）">XL（170-175cm）尺码</option>
              <option value="XXL（175-180cm）">XXL（175-180cm）尺码</option>
              <option value="XXXL（180-185cm）">XXXL（180-185cm）尺码</option>
          </select>
      </div>
      <div class="form-group">
        <input class="form-control" type="text" name="emergencyUserName" placeholder="紧急人联系姓名" notNull>
      </div>
      <div class="form-group">
        <input class="form-control" type="text" name="emergencyUserMobile" placeholder="紧急人联系电话" isMobile>
      </div>
      <div class="form-group">
        <input class="form-control" type="text" name="memo" placeholder="备注">
      </div>


      <input class="form-control" type="hidden" name="teamId" value="${runTeam.id}" >
      <input class="form-control" type="hidden" name="company" value="${runTeam.company}">
      <input type="submit" value="加入团队" class="form-control btn btn-info" placeholder="ID number" aria-describedby="basic-addon1">
  </form>
</body>
</html>
<script type="text/javascript">

    $(function(){
        $("[isform]").submit(function(){
            var form = $(this);
            checkForm(form);

            if (!checkError(form) || !window.confirm("请核实信息哟，咱不支持修改哟。")) {
                return false;
            }

            if (checkError(form)) {
                form.find("[type=submit]").attr("disabled", "disabled").val("正在提交数据...");
                form.ajaxSubmit({
                    success: function (result) {
                        var code = result['code'];
                        if (code == 0) {
                            location.href = "/activity/20150910/run/ream/member.htm?teamId=" + result["teamId"] + "&activityUserId=" + result["userActivityId"];
                        }
                        else {
                            alert(result['msg']);
                        }
                    }
                });
            }
            return false;
        });


        $("[isform] input").change(function(){
            checkForm($(this).parent());
        });


        function checkError(form) {
            return form.find("[errorEl]").size() == 0;
        }


        function checkForm(form) {
            form.find("[notNull]").each(function(index, element){
                var th = $(this);
                var val = th.val();
                if (/^\s*$/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>不能为空</label>');
                    }
                }
                else {
                    th.parent().removeClass("has-error");
                    th.parent().find("[errorEl]").remove();
                }
            });

            form.find("[isMobile]").each(function(index, element){
                var th = $(this);
                var val = th.val();

                if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>手机号不正确</label>');
                    }
                }
                else {
                    th.parent().removeClass("has-error");
                    th.parent().find("[errorEl]").remove();
                }
            })

            form.find("[userCard]").each(function(index, element){
                var th = $(this);
                var val = th.val();

                if (!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>身份证不正确</label>');
                    }
                }
                else {
                    th.parent().removeClass("has-error");
                    th.parent().find("[errorEl]").remove();
                }
            })
        }

        function appendError(th, val) {
            if (/^\s*$/.test(val)) {
                if (!th.parent().find("[errorEl]")[0]) {
                    th.parent().addClass("has-error");
                    th.parent().append('<label class="control-label" errorEl>不能为空</label>');
                }
            }
            else {
                th.parent().removeClass("has-error");
                th.parent().find("[errorEl]").remove();
            }
        }

    });

</script>