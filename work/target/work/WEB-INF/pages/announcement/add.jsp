<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/25
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="announcement-add-1" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加系统公告</h1>
        <div class="uk-form-row">
            <form action="/announcement/add.json" method="post" class="uk-form" id="announcement-add-form-1" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="img" notnull title = "upload file" placeholder = "公告图">
                </div>
                <div class="uk-form-row">
                    <input type="number" class="uk-width-1-1 uk-form-large" name="displayNum"  placeholder="显示次数" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="结束时间" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="文件描述" notnull></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name = "type" value="1" />
                    <button class="uk-button uk-button-primary" id="role-add-submit-1" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="announcement-add-2" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>广告外链公告</h1>
        <div class="uk-form-row">
            <form action="/announcement/add.json" method="post" class="uk-form" id="announcement-add-form-2" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="img" notnull title = "upload file" placeholder = "公告图">
                </div>
                <div class="uk-form-row">
                    <input type="number" class="uk-width-1-1 uk-form-large" name="displayNum"  placeholder="显示次数" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="body"  placeholder="链接地址" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="结束时间" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="文件描述" notnull></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name = "type" value="2" />
                    <button class="uk-button uk-button-primary" id="role-add-submit-2" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="announcement-add-3" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>话题公告</h1>
        <div class="uk-form-row">
            <form action="/announcement/add.json" method="post" class="uk-form" id="announcement-add-form-3" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="img" notnull title = "upload file" placeholder = "公告图">
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="body"  placeholder="话题编号" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="number" class="uk-width-1-1 uk-form-large" name="displayNum"  placeholder="显示次数" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="结束时间" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="文件描述" notnull></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name = "type" value="3" />
                    <button class="uk-button uk-button-primary" id="role-add-submit-3" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="announcement-add-4" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>电台音乐公告</h1>
        <div class="uk-form-row">
            <form action="/announcement/add.json" method="post" class="uk-form" id="announcement-add-form-4" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="img" notnull title = "upload file" placeholder = "公告图">
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="body"  placeholder="Mix编号" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="结束时间" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="文件描述" notnull></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name = "type" value="4" />
                    <button class="uk-button uk-button-primary" id="role-add-submit-4" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="announcement-add-5" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>赛事公告</h1>
        <div class="uk-form-row">
            <form action="/announcement/add.json" method="post" class="uk-form" id="announcement-add-form-5" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="img" notnull title = "upload file" placeholder = "公告图">
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="body"  placeholder="赛事编号" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="结束时间" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="文件描述" notnull></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name = "type" value="5" />
                    <button class="uk-button uk-button-primary" id="role-add-submit-5" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>