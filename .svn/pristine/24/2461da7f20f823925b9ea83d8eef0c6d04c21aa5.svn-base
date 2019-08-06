<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/6/28
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>

    .td-left {
        text-align: left;
        /*border: 1px solid red;*/
    }
    .td-right {
        width: 100px;
        text-align: right;
        /*border: 1px solid red;*/
    }

    .a-red {
        color: red;
        margin-right: 5px;
    }

</style>

<div>
    <div class="uk-form-row">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <tr>
                <td class="td-right">标题：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-input" data-uk-modal onclick="editReportInput('修改标题','title')">*</a>${watch.title}</td>
                <td class="td-right">备注：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改备注','des')">*</a>${watch.des}</td>
            </tr>
            <tr>
                <td class="td-right">测试日期：</td>
                <td class="td-left">
                    <a class="a-red" href="#edit-report-time" data-uk-modal onclick="editReportTime('修改测试日期','testTime')">*</a>
                    <lch:parse-date time="${watch.testTime}" pattern="yyyy-MM-dd"/>
                </td>
                <td class="td-right">添加日期：</td>
                <td class="td-left"><lch:parse-date time="${watch.addTime}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <td class="td-right">应答：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改应答','answer')">*</a>${watch.answer}</td>
                <td class="td-right">手表 ChipId号：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改手表 ChipId号','test1')">*</a>${watch.test1}</td>
            </tr>
            <tr>
                <td class="td-right">FM 屏幕：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 屏幕','test2')">*</a>${watch.test2}</td>
                <td class="td-right">FM 按键：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 按键','test3')">*</a>${watch.test3}</td>
            </tr>
            <tr>
                <td class="td-right">FM 触摸屏：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 触摸屏','test4')">*</a>${watch.test4}</td>
                <td class="td-right">FM 马达：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 马达','test5')">*</a>${watch.test5}</td>
            </tr>
            <tr>
                <td class="td-right">FM 蜂鸣器：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 蜂鸣器','test6')">*</a>${watch.test6}</td>
                <td class="td-right">FM 背光：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 背光','test7')">*</a>${watch.test7}</td>
            </tr>
            <tr>
                <td class="td-right">FM GPS：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM GPS','test8')">*</a>${watch.test8}</td>
                <td class="td-right">FM GSENSOR：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM GSENSOR','test9')">*</a>${watch.test9}</td>
            </tr>
            <tr>
                <td class="td-right">FM 心率：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 心率','test10')">*</a>${watch.test10}</td>
                <td class="td-right">FM 指南针：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 指南针','test11')">*</a>${watch.test11}</td>
            </tr>
            <tr>
                <td class="td-right">FM 气温：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 气温','test12')">*</a>${watch.test12}</td>
                <td class="td-right">FM 气压：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 气压','test13')">*</a>${watch.test13}</td>
            </tr>
            <tr>
                <td class="td-right">FM 蓝牙：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-input" data-uk-modal onclick="editReportTextarea('修改FM 蓝牙','test14')">*</a>${watch.test14}</td>
                <td class="td-right">FM 测试：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 测试','test15')">*</a>${watch.test15}</td>
            </tr>
            <tr>
                <td class="td-right">FM 湿度：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 湿度','test16')">*</a>${watch.test16}</td>
                <td class="td-right">FM 陀螺仪：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改FM 陀螺仪','test17')">*</a>${watch.test17}</td>
            </tr>
            <tr>
                <td class="td-right">注意事项：</td>
                <td class="td-left"><a class="a-red" href="#edit-report-textarea" data-uk-modal onclick="editReportTextarea('修改注意事项','test18')">*</a>${watch.test18}</td>
                <td class="td-right"></td>
                <td class="td-left"></td>
            </tr>
        </table>
    </div>
</div>

<div id="edit-report-input" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加手表测试报告</h1>
        <div class="uk-form-row">
            <form action="/report/watch/modify.json" method="post" class="uk-form" id="edit-report-input-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="val" notnull />
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${watch.id}" />
                    <input type="hidden" name="key" value="">
                    <button class="uk-button uk-button-primary" id="edit-report-input-submit" target="role-add-form">save</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="edit-report-textarea" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加手表测试报告</h1>
        <div class="uk-form-row">
            <form action="/report/watch/modify.json" method="post" class="uk-form" id="edit-report-textarea-form" isform>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="val" placeholder="内容" notnull></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${watch.id}" />
                    <input type="hidden" name="key" value="">
                    <button class="uk-button uk-button-primary" id="edit-report-textarea-submit" target="role-add-form">save</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="edit-report-time" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加手表测试报告</h1>
        <div class="uk-form-row">
            <form action="/report/watch/modify.json" method="post" class="uk-form" id="edit-report-time-form" isform>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="val" data-uk-datepicker="{format:'YYYY-MM-DD'}" placeholder="时间" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${watch.id}" />
                    <input type="hidden" name="key" value="">
                    <button class="uk-button uk-button-primary" id="edit-report-time-submit" target="role-add-form">save</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>

    function editReportInput(title, key) {
        var parent_ = $("#edit-report-input");
        parent_.find("h1").text(title);
        parent_.find("[name=key]").val(key);
    }

    function editReportTextarea(title, key) {
        var parent_ = $("#edit-report-textarea");
        parent_.find("h1").text(title);
        parent_.find("[name=key]").val(key);
    }
    function editReportTime(title, key) {
        var parent_ = $("#edit-report-time");
        parent_.find("h1").text(title);
        parent_.find("[name=key]").val(key);
    }


</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

