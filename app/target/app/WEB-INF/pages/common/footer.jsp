<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/5
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer id="footer">
    <div id="footer-head" style="color:#ffffff;" class="text-center">
        <span>全面了解蓝筹科技:sales@ifitmix.com</span>
        <%--        <div className="icon-input">
                <img src="/img/email.png" />
                <input className="container-fluid" type="email" placeholder={i18n.knowPlaceholder} />
                <img src="/img/go.png" />
                </div>--%>
    </div>
    <div id="footer-body" class="text-center">
        <div id="footer-wechat">
            <a class="wechat" href="http://www.ifitmix.com"></a>
            <a class="weibo" href="http://www.weibo.com/igeekery?from=myfollow_all&is_host=1"></a>
        </div>
        <div>
            <a href="javascript:" <%--onClick={this._changLanguageEn}--%>><img src="/imgs/language/english.png" width="20" /> English</a>
            &nbsp;&nbsp;&nbsp;<span style="color:#f3f3f3">|</span>&nbsp;&nbsp;&nbsp;
            <a href="javascript:" <%--onClick={this._changLanguageCh}--%>><img src="/imgs/language/china.png"  width="20" /> 中文</a>
        </div>
        <div id="footer-link">
            <a href="http://www.ifitmix.com/jobs">工作机会</a>
            <a href="http://www.ifitmix.com/aboutUs">关于我们</a>
            <a href="http://www.ifitmix.com/contactUs">联系我们</a>
            <%--<Link to="">{i18n.retailers}</Link>
            <Link to="">{i18n.help}</Link>--%>
            <a href="http://www.ifitmix.com/">隐私政策</a>
            <%-- <Link to="">{i18n.tos}</Link>--%>
        </div>
        <a id="footer-copyright" href="http://www.miitbeian.gov.cn/">粤ICP备15030586 深圳第一蓝筹科技有限公司版权所有 @2015-2016</a>
    </div>
</footer>