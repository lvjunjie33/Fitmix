<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/1/29
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .table-tr-td-cls {
        width: 50px;
        /*border: 1px solid red;*/
    }
    .table-td-cls {
        padding-right: 20px;
    }

    .bgc-EF4646 {
        background-color: #EF4646;
    }
    .bgc-7DC251 {
        background-color: #7DC251;
    }
    .bgc-FBF1EA {
        background-color: #FBF1EA;
    }
    .bgc-47627F {
        background-color: #47627F;
    }
    .bgc-FF0066 {
        background-color: #FF0066;
    }
    .bgc-FF6023 {
        background-color: #FF6023;
    }
    .bgc-0FEAFE {
        background-color: #0FEAFE;
    }
    .bgc-2F635D {
        background-color: #2F635D;
    }
    .bgc-FF0000 {
        background-color: #FF0000;
    }
    .bgc-FFF212 {
        background-color: #FFF212;
    }
    .bgc-5FCCFF {
        background-color: #5FCCFF;
    }
    .bgc-5C5140 {
        background-color: #5C5140;
    }
    .bgc-FCAC15 {
        background-color: #FCAC15;
    }
    .bgc-DAE034 {
        background-color: #DAE034;
    }
    .bgc-FEDE71 {
        background-color: #FEDE71;
    }
    .bgc-2871A6 {
        background-color: #2871A6;
    }
    .bgc-FFDD21 {
        background-color: #FFDD21;
    }
    .bgc-FFA7A7 {
        background-color: #FFA7A7;
    }
    .bgc-FFF1BA {
        background-color: #FFF1BA;
    }
    .bgc-656C7E {
        background-color: #656C7E;
    }
    .bgc-FFA7CB {
        background-color: #FFA7CB;
    }
    .bgc-95284D {
        background-color: #95284D;
    }
    .bgc-A1A7D0 {
        background-color: #A1A7D0;
    }
    .bgc-7E2D2E {
        background-color: #7E2D2E;
    }
    .bgc-392441 {
        background-color: #392441;
    }
    .bgc-896A32 {
        background-color: #896A32;
    }

</style>

<div id="run-mix-img-link-modify" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 class="h-center-cls">封面图</h1>
        <div class="uk-form-row">
            <form action="/run/mix/img/link/modify.json" method="post" class="uk-form" isform  enctype="multipart/form-data">
                <div class="uk-form-row">
                    <label><input type="file" name="uploadFile" class="uk-form-large uk-width-9-10"  title = "add" ></label>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary" id="role-add-submit"  target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="run-mix-link-modify" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 class="h-center-cls">Mix文件</h1>
        <div class="uk-form-row">
            <form action="/run/mix/link/modify.json" method="post" class="uk-form"  isform  enctype="multipart/form-data">
                <div class="uk-form-row">
                    <label><input type="file" name="uploadFile" class="uk-form-large uk-width-9-10"  title = "add" ></label>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary"  target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="run-mix-modify-title" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 class="h-center-cls">标题</h1>
        <div class="uk-form-row">
            <form action="/run/mix/modify/title.json" method="post" class="uk-form"  isform >
                <div class="uk-form-row">
                    <label><input type="text" name="title" class="uk-form-large uk-width-9-10"  title = "add" ></label>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary" target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="run-mix-modify-des" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 class="h-center-cls">描述</h1>
        <div class="uk-form-row">
            <form action="/run/mix/modify/des.json" method="post" class="uk-form"  isform >
                <div class="uk-form-row">
                    <label><input type="text" name="des" class="uk-form-large uk-width-9-10"  title = "add" ></label>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary" target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="run-mix-modify-bpm" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 class="h-center-cls">bpm</h1>
        <div class="uk-form-row">
            <form action="/run/mix/modify/bpm.json" method="post" class="uk-form"  isform >
                <div class="uk-form-row">
                    <label>
                        <input type="number" name="bpm" class="uk-form-large uk-width-8-10"  title = "bpm" >&nbsp;&nbsp;&nbsp;
                        <input type="checkbox" name="bpmIsDouble" value="1" />&nbsp;翻倍
                    </label>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary" target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="run-mix-modify-duration" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 class="h-center-cls">时长</h1>
        <div class="uk-form-row">
            <form action="/run/mix/modify/duration.json" method="post" class="uk-form"  isform >
                <div class="uk-form-row">
                    <label><input type="text" name="duration" class="uk-form-large uk-width-9-10"  title = "duration"></label>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary" target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="run-mix-modify-status" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 class="h-center-cls">状态</h1>
        <div class="uk-form-row">
            <form action="/run/mix/modify/status.json" method="post" class="uk-form"  isform>
                <div class="uk-form-row">
                    <label>
                        <select name="status" class="uk-width-1-1 uk-form-large">
                            <option value="0">删除</option>
                            <option value="1">正常</option>
                        </select>
                    </label>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary" target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="run-mix-modify-watch" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 class="h-center-cls">上架</h1>
        <div class="uk-form-row">
            <form action="/run/mix/modify/watch.json" method="post" class="uk-form"  isform >
                <div class="uk-form-row">
                    <label>
                        <select name="watch" class="uk-width-1-1 uk-form-large">
                            <option value="1">上架</option>
                            <option value="0">下架</option>
                        </select>
                    </label>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary" target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="run-mix-modify-songStyle" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 class="h-center-cls">曲风</h1>
        <div class="uk-form-row">
            <form action="/run/mix/modify/songStyle.json" method="post" class="uk-form"  isform >
                <div class="uk-form-row">
                    <label class="uk-width-1-6"><input type="radio" name="songStyle" value="1" checked="checked" />&nbsp;流行</label>
                    <label class="uk-width-1-6"><input type="radio" name="songStyle" value="2"/>&nbsp;流行摇滚</label>
                    <label class="uk-width-1-6"><input type="radio" name="songStyle" value="3"/>&nbsp;电子</label>
                    <label class="uk-width-1-6"><input type="radio" name="songStyle" value="4"/>&nbsp;摇滚</label>
                    <label class="uk-width-1-6" ><input type="radio" name="songStyle" value="5"/>&nbsp;爵士</label>
                    <label class="uk-width-1-6" ><input type="radio" name="songStyle" value="6"/>&nbsp;古典</label>
                    <label class="uk-width-1-6" ><input type="radio" name="songStyle" value="7"/>&nbsp;纯音乐</label>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary" target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="run-mix-modify-energyLevel" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1 class="h-center-cls">能量级别</h1>
        <div class="uk-form-row">
            <form action="/run/mix/modify/energyLevel.json" method="post" class="uk-form"  isform >
                <div class="uk-form-row">
                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="1" checked="checked" />&nbsp;1</label>
                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="2"/>&nbsp;2</label>
                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="3"/>&nbsp;3</label>
                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="4"/>&nbsp;4</label>
                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="10"/>&nbsp;10</label>
                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="11"/>&nbsp;11</label>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary" target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="run-mix-modify-colorTag" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1  class="h-center-cls">色彩标签</h1>
        <div class="uk-form-row">
            <form action="/run/mix/modify/colorTag.json" method="post" class="uk-form"  isform >
                <div class="uk-form-row">
                    <table style="width: 600px;">
                        <tr>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#EF4646" checked="checked" />
                                    <label class="table-td-cls bgc-EF4646">
                                        G
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#7DC251"/>
                                    <label class="table-td-cls bgc-7DC251">
                                        D
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FBF1EA"/>
                                    <label class="table-td-cls bgc-FBF1EA">
                                        C
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#47627F"/>
                                    <label class="table-td-cls bgc-47627F">
                                        Em
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FF0066"/>
                                    <label class="table-td-cls bgc-FF0066">
                                        G
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FF6023"/>
                                    <label class="table-td-cls bgc-FF6023">
                                        #F
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#0FEAFE"/>
                                    <label class="table-td-cls bgc-0FEAFE">
                                        Gm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#2F635D"/>
                                    <label class="table-td-cls bgc-2F635D">
                                        Dm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FF0000" />
                                    <label class="table-td-cls bgc-FF0000">
                                        A
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FFF212"/>
                                    <label class="table-td-cls bgc-FFF212">
                                        bA
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#5FCCFF"/>
                                    <label class="table-td-cls bgc-5FCCFF">
                                        Abm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#5C5140"/>
                                    <label class="table-td-cls bgc-5C5140">
                                        Dbm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FCAC15" />
                                    <label class="table-td-cls bgc-FCAC15">
                                        A
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#DAE034"/>
                                    <label class="table-td-cls bgc-DAE034">
                                        bEm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FEDE71"/>
                                    <label class="table-td-cls bgc-FEDE71">
                                        Eb
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#2871A6"/>
                                    <label class="table-td-cls bgc-2871A6">
                                        Am
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FFDD21"/>
                                    <label class="table-td-cls bgc-FFDD21">
                                        E
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FFA7A7"/>
                                    <label class="table-td-cls bgc-FFA7A7">
                                        bB
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FFF1BA"/>
                                    <label class="table-td-cls bgc-FFF1BA">
                                        Db
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#656C7E"/>
                                    <label class="table-td-cls bgc-656C7E">
                                        Bm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6">&nbsp;</label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6">&nbsp;</label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FFA7CB"/>
                                    <label class="table-td-cls bgc-FFA7CB">
                                        F
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#95284D"/>
                                    <label class="table-td-cls bgc-95284D">
                                        Fm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6">&nbsp;</label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6">&nbsp;</label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#A1A7D0"/>
                                    <label class="table-td-cls bgc-A1A7D0">
                                        B
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#7E2D2E"/>
                                    <label class="table-td-cls bgc-7E2D2E">
                                        Gbm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6">&nbsp;</label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6">&nbsp;</label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6">&nbsp;</label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#392441"/>
                                    <label class="table-td-cls bgc-392441">
                                        Cm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                        </tr>

                        <tr>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6">&nbsp;</label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6">&nbsp;</label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6">&nbsp;</label>
                            </td>
                            <td style="width: 50px;">
                                <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#896A32"/>
                                    <label class="table-td-cls bgc-896A32">
                                        Bbm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                </label>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${runMix.id}" />
                    <button class="uk-button uk-button-primary" target="role-add-form">修改</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>