<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/10/24
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/8/17
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>
    .td-text {
        white-space: normal;
        width: 40%;
        word-wrap: break-word;
        word-break: break-all;
        overflow: hidden;
    }

</style>

<div>
    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/push/theme.json" method="get" class="uk-form">
            <div class="uk-form-row">
                <span style="font-weight: 700;margin-right: 30px">话题推送</span>
                <label>用户编号：</label>
                <label class="mix-scene">
                    <input type="number" name="targetUid" value="" placeholder="用户编号">
                </label>
                &nbsp;&nbsp;
                <label>话题编号：</label>
                <label class="mix-scene">
                    <input type="number" name="themeId" value="" placeholder="话题编号" notnull>
                </label>

                <button type="submit" class="uk-button uk-button-primary">推送</button>
            </div>
        </form>
    </div>

    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/push/activity.json" method="get" class="uk-form">
            <div class="uk-form-row">
                <span style="font-weight: 700;margin-right: 30px">赛事推送</span>
                <label>用户编号：</label>
                <label class="mix-scene">
                    <input type="number" name="targetUid" value="" placeholder="用户编号">
                </label>
                &nbsp;&nbsp;
                <label>赛事编号：</label>
                <label class="mix-scene">
                    <input type="number" name="activityId" value="" placeholder="标题" notnull>
                </label>

                <button type="submit" class="uk-button uk-button-primary">推送</button>
            </div>
        </form>
    </div>

    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/push/mix.json" method="get" class="uk-form">
            <div class="uk-form-row">
                <span style="font-weight: 700;margin-right: 30px">Mix推送</span>
                <label>用户编号：</label>
                <label class="mix-scene">
                    <input type="number" name="targetUid" value="" placeholder="用户编号">
                </label>
                &nbsp;&nbsp;
                <label>Mix编号：</label>
                <label class="mix-scene">
                    <input type="number" name="mixId" value="" placeholder="Mix编号" notnull>
                </label>

                <button type="submit" class="uk-button uk-button-primary">推送</button>
            </div>
        </form>
    </div>

    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/push/radio.json" method="get" class="uk-form">
            <div class="uk-form-row">
                <span style="font-weight: 700;margin-right: 30px">电台推送</span>
                <label>用户编号：</label>
                <label class="mix-scene">
                    <input type="number" name="targetUid" value="" placeholder="用户编号">
                </label>
                &nbsp;&nbsp;
                <label>电台编号：</label>
                <label class="mix-scene">
                    <input type="number" name="radioId" value="" placeholder="电台编号" notnull>
                </label>

                <button type="submit" class="uk-button uk-button-primary">推送</button>
            </div>
        </form>
    </div>

    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/push/video.json" method="get" class="uk-form">
            <div class="uk-form-row">
                <span style="font-weight: 700;margin-right: 30px">电台推送</span>
                <label>用户编号：</label>
                <label class="mix-scene">
                    <input type="number" name="targetUid" value="" placeholder="用户编号">
                </label>
                &nbsp;&nbsp;
                <label>视频编号：</label>
                <label class="mix-scene">
                    <input type="number" name="videoId" value="" placeholder="视频编号" notnull>
                </label>

                <button type="submit" class="uk-button uk-button-primary">推送</button>
            </div>
        </form>
    </div>

    <div class="uk-form-row" style="padding-bottom: 20px">
        <form action="/push/outer/link.json" method="get" class="uk-form">
            <div class="uk-form-row">
                <span style="font-weight: 700;margin-right: 30px">第三方链接</span>
                <label>用户编号：</label>
                <label class="mix-scene">
                    <input type="number" name="targetUid" value="" placeholder="用户编号">
                </label>
                &nbsp;&nbsp;
                <label>标题：</label>
                <label class="mix-scene">
                    <input type="text" name="title" value="" placeholder="标题" notnull>
                </label>
                &nbsp;&nbsp;
                <label>内容：</label>
                <label class="mix-scene">
                    <input type="text" name="content" value="" placeholder="内容" notnull>
                </label>
                &nbsp;&nbsp;
                <label>链接地址：</label>
                <label class="mix-scene">
                    <input type="text" name="link" value="" placeholder="链接地址" notnull>
                </label>

                <button type="submit" class="uk-button uk-button-primary">推送</button>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<script>



</script>

