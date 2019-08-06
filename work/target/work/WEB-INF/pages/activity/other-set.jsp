<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/7/5
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="activity-set-city" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>设置赛事城市</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/activity/set-activity-integral-city.json" method="post" class="uk-form" id="activity-link-id" isform>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="cityTarget" placeholder="活动类型" data-value="${activity.status}" title="活动类型">
                        <option value="201">上海市(省份)</option>
                        <option value="202">河南省</option>
                        <option value="1">深圳市</option>
                    </select>
                    <input name="activityId" type="hidden" value="${activity.id}" />
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="activity-link-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="activity-set-thread-link" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>设置三方链接</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/activity/set-thread-link.json" method="post" class="uk-form" id="activity-city-target-id" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="webLink" placeholder="三方外部链接" notnull>
                    <input name="activityId" type="hidden" value="${activity.id}" />
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="activity-city-target-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="activity-set-max-sign-group-num" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>最大的参赛报名组<span style="font-size: 15px">(不填表示不做限制)</span></h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/activity/set-activity-max-group-num.json" method="post" class="uk-form" id="activity-max-sign-group-num-id" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="maxGroupNum" placeholder="报名组数" notnull>
                    <input name="activityId" type="hidden" value="${activity.id}" />
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="activity-max-sign-group-num-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="activity-checked-sex" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>参数性别限制<span style="font-size: 15px">(不填表示不做限制)</span></h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/activity/set-activity-check-sex.json" method="post" class="uk-form" id="activity-checked-sex-ip" isform>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="checkSex" placeholder="活动类型" data-value="${activity.status}" title="活动类型">
                        <option value="">不检查</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                    <input name="activityId" type="hidden" value="${activity.id}" />
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="activity-checked-sex-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="activity-set-activity-focus-tag" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>设置赛事标签</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/activity/set-focus-tag.json" method="post" class="uk-form" id="activity-focus-tag" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="focusTag" placeholder="赛事标签" notnull>
                    <input name="activityId" type="hidden" value="${activity.id}" />
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="activity-focus-tag-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>