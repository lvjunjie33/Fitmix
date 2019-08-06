<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/8/18
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/head-base-path.jsp"%>
<html>
<head>
    <title>UGC</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet/less" type="text/css" href="../../../css/common/main.less" />
    <link rel="stylesheet" type="text/css" href="../../../static/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/cropper/cropper.min.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/loading/showLoading.css" />
    <script src="../../../static/less/less.min.js"></script>


</head>
<body id="loading">
<jsp:include page="../common/head.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="../common/sidebar.jsp"></jsp:include>
        <div class="col-md-12">
            <h4 class="col-md-10 col-md-push-2 page-header"> 基本资料 </h4>
            <div class="col-md-10 col-md-push-2" style="margin-bottom: 100px;">
                <div class="albums-info-box row">
                    <div class="albums-cover">
                        <img id="current-avatar" src="${base_uri}/${user.avatar}">
                        <%--<span class="albums-date">创建时间:2016/08/15</span>--%>
                    </div>
                    <div class="albums-desc">
                        <div class="title">我的头像</div>
                        <div class="content">
                            <p>
                                建议尺寸为800*800<br>
                                支持JPG,PNG等格式<br>
                                图片大小不得超过5M<br>
                            </p>
                        </div>
                        <div class="manage">
                            <a id="upload-avatar" href="javascript:void(0);" class="btn">上传封面</a>
                            <input type="file" name="file" style="display: none;">
                        </div>
                    </div>
                </div>
                <div class="albums-info-form row">
                    <div class="col-md-7">
                        <form id="base-info-form" enctype="multipart/form-data">
                            <input type="hidden" value="${user.id}" name="id" id="id">
                            <div class="form-group">
                                <label for="name">主播名称</label>
                                <input type="text" class="form-control" name="name" value="${user.name}" id="name" placeholder="">
                            </div>
                            <div class="form-group">
                                <label>性别</label>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" id="inlineRadio1" value="1" <c:if test="${user.gender == 1}">checked</c:if> > 男
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" id="inlineRadio2" value="2" <c:if test="${user.gender == 2}">checked</c:if> > 女
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" id="inlineRadio3" value="3" <c:if test="${user.gender == 3}">checked</c:if> > 保密
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>生日</label>
                                <div class="info-birthday">
                                    <select class="form-control" style="width: 100px" name="birthday[year]">
                                        <option value="00">----</option>
                                        <option value="2016">2016</option>
                                        <option value="2015">2015</option>
                                        <option value="2014">2014</option>
                                        <option value="2013">2013</option>
                                        <option value="2012">2012</option>
                                        <option value="2011">2011</option>
                                        <option value="2010">2010</option>
                                        <option value="2009">2009</option>
                                        <option value="2008">2008</option>
                                        <option value="2007">2007</option>
                                        <option value="2006">2006</option>
                                        <option value="2005">2005</option>
                                        <option value="2004">2004</option>
                                        <option value="2003">2003</option>
                                        <option value="2002">2002</option>
                                        <option value="2001">2001</option>
                                        <option value="2000">2000</option>
                                        <option value="1999">1999</option>
                                        <option value="1998">1998</option>
                                        <option value="1997">1997</option>
                                        <option value="1996">1996</option>
                                        <option value="1995">1995</option>
                                        <option value="1994">1994</option>
                                        <option value="1993">1993</option>
                                        <option value="1992">1992</option>
                                        <option value="1991">1991</option>
                                        <option value="1990">1990</option>
                                        <option value="1989">1989</option>
                                        <option value="1988">1988</option>
                                        <option value="1987">1987</option>
                                        <option value="1986">1986</option>
                                        <option value="1985">1985</option>
                                        <option value="1984">1984</option>
                                        <option value="1983">1983</option>
                                        <option value="1982">1982</option>
                                        <option value="1981">1981</option>
                                        <option value="1980">1980</option>
                                        <option value="1979">1979</option>
                                        <option value="1978">1978</option>
                                        <option value="1977">1977</option>
                                        <option value="1976">1976</option>
                                        <option value="1975">1975</option>
                                        <option value="1974">1974</option>
                                        <option value="1973">1973</option>
                                        <option value="1972">1972</option>
                                        <option value="1971">1971</option>
                                        <option value="1970">1970</option>
                                        <option value="1969">1969</option>
                                        <option value="1968">1968</option>
                                        <option value="1967">1967</option>
                                        <option value="1966">1966</option>
                                        <option value="1965">1965</option>
                                        <option value="1964">1964</option>
                                        <option value="1963">1963</option>
                                        <option value="1962">1962</option>
                                        <option value="1961">1961</option>
                                        <option value="1960">1960</option>
                                        <option value="1959">1959</option>
                                        <option value="1958">1958</option>
                                        <option value="1957">1957</option>
                                        <option value="1956">1956</option>
                                        <option value="1955">1955</option>
                                        <option value="1954">1954</option>
                                        <option value="1953">1953</option>
                                        <option value="1952">1952</option>
                                        <option value="1951">1951</option>
                                        <option value="1950">1950</option>
                                        <option value="1949">1949</option>
                                        <option value="1948">1948</option>
                                        <option value="1947">1947</option>
                                        <option value="1946">1946</option>
                                        <option value="1945">1945</option>
                                        <option value="1944">1944</option>
                                        <option value="1943">1943</option>
                                        <option value="1942">1942</option>
                                        <option value="1941">1941</option>
                                        <option value="1940">1940</option>
                                        <option value="1939">1939</option>
                                        <option value="1938">1938</option>
                                        <option value="1937">1937</option>
                                        <option value="1936">1936</option>
                                        <option value="1935">1935</option>
                                        <option value="1934">1934</option>
                                        <option value="1933">1933</option>
                                        <option value="1932">1932</option>
                                        <option value="1931">1931</option>
                                        <option value="1930">1930</option>
                                        <option value="1929">1929</option>
                                        <option value="1928">1928</option>
                                        <option value="1927">1927</option>
                                        <option value="1926">1926</option>
                                        <option value="1925">1925</option>
                                        <option value="1924">1924</option>
                                        <option value="1923">1923</option>
                                        <option value="1922">1922</option>
                                        <option value="1921">1921</option>
                                        <option value="1920">1920</option>
                                        <option value="1919">1919</option>
                                        <option value="1918">1918</option>
                                        <option value="1917">1917</option>
                                    </select>
                                    <select class="form-control" style="width: 70px" name="birthday[month]">
                                        <option value="00">--</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                        <option value="7">7</option>
                                        <option value="8">8</option>
                                        <option value="9">9</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                    </select>
                                    <select class="form-control" style="width: 70px" name="birthday[day]">
                                    <option value="00">--</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                    <option value="26">26</option>
                                    <option value="27">27</option>
                                    <option value="28">28</option>
                                    <option value="29">29</option>
                                    <option value="30">30</option>
                                    <option value="31">31</option>
                                </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="city">城市</label>
                                <input type="text" name="city" class="form-control" value="${user.city}" id="city" placeholder="">
                            </div>
                            <div class="form-group">
                                <label>个性签名</label>
                                <textarea class="form-control" rows="10" name="signature">${user.signature}</textarea>
                            </div>
                            <a id="base-info-submit" href="javascript:void(0);" class="btn btn-base">修改用户信息</a>
                        </form>
                    </div>
                </div>
                <div class="albums-info-form row">
                    <div class="col-md-7">
                        <label>社交账号</label>
                        <div class="info-social">
                            <i class="iconfont icon-qq"></i>
                            <i class="iconfont icon-weixin"></i>
                            <i class="iconfont icon-weibo"></i>
                        </div>
                    </div>
                </div>
                <div class="albums-info-form row">
                    <div class="col-md-7">
                        <label>账号密码</label>
                        <form id="password-form" class="info-update-pwd">
                            <div class="form-group">
                                <label for="oldPwd">旧密码</label>
                                <input type="password" class="form-control" style="width: 300px" name="password" id="oldPwd" placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="newPwd1">新密码</label>
                                <input type="password" class="form-control" style="width: 300px" name="password1" id="newPwd1" placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="newPwd2">确认密码</label>
                                <input type="password" class="form-control" style="width: 300px" name="password2" id="newPwd2" placeholder="">
                            </div>
                            <a id="update-password-submit" href="javascript:void(0);" class="btn btn-base">修改密码</a>
                        </form>
                        <div>
                            <a id="show-update-password" href="javascript:void(0);" class="btn btn-base">修改密码</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 图片裁剪模态框 --%>
<div class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" id="jcrop-avatar">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
                <h4 class="modal-title" id="gridSystemModalLabel">裁剪头像</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid" style="display: table;">
                    <div class="jcrop-avatar-box">
                        <img id="avatar" />
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="cancel" type="button" class="btn btn-base btn-base-default" data-dismiss="modal">取消</button>
                <button id="avatar-submit" type="button" class="btn btn-base">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--<jsp:include page="../common/footer.jsp"></jsp:include>--%>
<script src="../../../static/jquery-3.1.0.min.js"></script>
<script src="../../../static/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../../js/common/sidebar.js"></script>
<script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
<script src="../../../js/common/VerificationUtil.js"></script>
<script src="../../../static/cropper/cropper.min.js"></script>
<script src="../../../static/bootstrap/js/modal.js"></script>
<script src="../../../static/loading/jquery.showLoading.min.js"></script>
<script src="../../../static/jquery.form.js"></script>
<script>
    var year = '${user.birthday.year}';
    var month = '${user.birthday.month}';
    var day = '${user.birthday.day}';
    var uid = '${user.id}';
    var BASE_URI = '${base_uri}';
    Sidebar.init("^/ugc/user/(\\S){1,}/info$");
</script>
<script src="../../../js/user/user.js"></script>
<script>
    User.infoInit();
</script>



</body>
</html>
