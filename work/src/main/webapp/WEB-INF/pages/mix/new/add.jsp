<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/1/29
  Time: 11:37
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

<div id="run-mix-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog" style="width: 650px">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>文件管理</h1>
        <div class="uk-form-row">
            <form action="/run/mix/save.json" id = "run-mix-form-id" method="post" class="uk-form" enctype="multipart/form-data" mix-add-form>
                <div class="<%--uk-container--%>">
                    <div class="uk-form-row">
                        <label>标题：</label>
                        <label><input type="text" name="title" class="uk-form-large uk-width-9-10" notnull></label>
                    </div>

                    <div class="uk-form-row">
                        <label>描述：</label>
                        <label>
                            <textarea class="uk-form-large uk-width-9-10" name="des" placeholder="文件描述" notnull></textarea>
                        </label>
                    </div>
                    <div class="uk-form-row">
                        <label>BPM：</label>
                        <label>
                            <input type="number" name="bpm" class="uk-form-large uk-width-7-10" notnull>
                            <input type="checkbox" name="bpmIsDouble" value="1" />翻倍
                        </label>
                    </div>
                    <div class="uk-form-row">
                        <label>时长：</label>
                        <label><input type="number" name="duration" class="uk-form-large uk-width-9-10" notnull></label>
                    </div>

                    <div class="uk-form-row">
                        <table style="width: 600px;">
                            <tr>
                                <td class="table-tr-td-cls" >
                                    <label  >能量级别：</label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6" ><input type="radio" name="energyLevel" value="1" checked="checked" />1</label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="2"/>2</label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="3"/>3</label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="4"/>4</label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="10"/>10</label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="energyLevel" value="11"/>11</label>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div class="uk-form-row">
                        <table style="width: 600px;">
                            <tr>
                                <td class="table-tr-td-cls" >
                                    <label  >曲风：</label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="songStyle" value="1" checked="checked" /><label class="table-td-cls ">流行</label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="songStyle" value="2"/><label class="table-td-cls ">流行摇滚</label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="songStyle" value="3"/><label class="table-td-cls ">电子</label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="songStyle" value="4"/><label class="table-td-cls ">摇滚</label></label>
                                </td>
                            </tr>
                            <tr>
                                <td class="table-tr-td-cls"  ><label ></label></td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6" ><input type="radio" name="songStyle" value="5"/><label class="table-td-cls ">爵士</label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6" ><input type="radio" name="songStyle" value="6"/><label class="table-td-cls ">古典</label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6" ><input type="radio" name="songStyle" value="7"/><label class="table-td-cls ">纯音乐</label></label>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div class="uk-form-row" >
                        <table style="width: 600px;">
                            <tr>
                                <td class="table-tr-td-cls" ><label >色彩标签：</label></td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#EF4646" checked="checked" /><label class="table-td-cls bgc-EF4646">
                                        G
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#7DC251"/><label class="table-td-cls bgc-7DC251">
                                        D
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FBF1EA"/><label class="table-td-cls bgc-FBF1EA">
                                        C
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#47627F"/><label class="table-td-cls bgc-47627F">
                                        Em
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                            </tr>

                            <tr>
                                <td class="table-tr-td-cls" ><label ></label></td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FF0066"/><label class="table-td-cls bgc-FF0066">
                                        G
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FF6023"/><label class="table-td-cls bgc-FF6023">
                                        #F
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#0FEAFE"/><label class="table-td-cls bgc-0FEAFE">
                                        Gm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#2F635D"/><label class="table-td-cls bgc-2F635D">
                                        Dm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                            </tr>

                            <tr>
                                <td class="table-tr-td-cls" ><label ></label></td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FF0000" /><label class="table-td-cls bgc-FF0000">
                                        A
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FFF212"/><label class="table-td-cls bgc-FFF212">
                                        bA
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#5FCCFF"/><label class="table-td-cls bgc-5FCCFF">
                                        Abm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#5C5140"/><label class="table-td-cls bgc-5C5140">
                                        Dbm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                            </tr>

                            <tr>
                                <td class="table-tr-td-cls" ><label ></label></td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FCAC15" /><label class="table-td-cls bgc-FCAC15">
                                        A
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#DAE034"/><label class="table-td-cls bgc-DAE034">
                                        bEm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FEDE71"/><label class="table-td-cls bgc-FEDE71">
                                        Eb
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#2871A6"/><label class="table-td-cls bgc-2871A6">
                                        Am
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                            </tr>

                            <tr>
                                <td class="table-tr-td-cls" ><label ></label></td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FFDD21"/><label class="table-td-cls bgc-FFDD21">
                                        E
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FFA7A7"/><label class="table-td-cls bgc-FFA7A7">
                                        bB
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FFF1BA"/><label class="table-td-cls bgc-FFF1BA">
                                        Db
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#656C7E"/><label class="table-td-cls bgc-656C7E">
                                        Bm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                            </tr>

                            <tr>
                                <td class="table-tr-td-cls" ><label ></label></td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#FFA7CB"/><label class="table-td-cls bgc-FFA7CB">
                                        F
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#95284D"/><label class="table-td-cls bgc-95284D">
                                        Fm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                            </tr>

                            <tr>
                                <td class="table-tr-td-cls" ><label ></label></td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#A1A7D0"/><label class="table-td-cls bgc-A1A7D0">
                                        B
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#7E2D2E"/><label class="table-td-cls bgc-7E2D2E">
                                        Gbm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                            </tr>

                            <tr>
                                <td class="table-tr-td-cls" ><label ></label></td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#392441"/><label class="table-td-cls bgc-392441">
                                        Cm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                            </tr>

                            <tr>
                                <td class="table-tr-td-cls" ><label ></label></td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"></label>
                                </td>
                                <td class="table-tr-td-cls">
                                    <label class="uk-width-1-6"><input type="radio" name="colorTag" value="#896A32"/><label class="table-td-cls bgc-896A32">
                                        Bbm
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label></label>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div class="uk-form-row" style="margin-top: 50px;">
                        <button type="button" id = "run-mix-btn-id" class="uk-button uk-button-primary" target="role-add-form" style="width: 200px" mix-add-submit>保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    $(function() {
        $("#run-mix-btn-id").click(function() {


            var form_ = $("#run-mix-form-id");
            var title = form_.find("[name=title]").val();
            var bpm = form_.find("[name=bpm]").val();
            var duration = form_.find("[name=duration]").val();

            if (title == undefined || title == null || title == '') {
                alert("标题为空");
                return;
            }
            if (bpm == undefined || bpm == null || bpm == '' || bpm == 0) {
                alert("bpm为空");
                return;
            }
            if (duration == undefined || duration == null || duration == '' || duration == 0) {
                alert("Mix时长为空");
                return;
            }
            $.ajax({
                type: "POST",
                url: "/run/mix/save.json",
                data:$(form_).serialize(),
                dataType:"json",
                success:function (result) {
                    console.info(result)
                    location = location;
                },
                error:function (XMLHttpRequest,textStatus,errorThrom){
                    console.log("不明原因造成数据获取失败... ...");
                }
            });
        });
    });
</script>
