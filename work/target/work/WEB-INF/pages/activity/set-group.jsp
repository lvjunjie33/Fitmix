<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/4/26
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="activity-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>活动组信息添加</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <div class="uk-form" id="add-group-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="groupName" placeholder="活动组的名称" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="memberNumber" placeholder="活动组人数" value="" onchange="changeGroupNumber(this)" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="groupNeedMoney" placeholder="活动报名金额" value="" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="描述" notnull></textarea>
                </div>
                <div class="uk-form-row" id = "group-names"></div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="group-add-submit" >add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function changeGroupNumber(obj) {
        var $this = $(obj);
        var num = $this.val() * 1;
        if (num == 0) {
            $("#group-names").html();
            return;
        } else {
            var html = "";
            for (var i = 0; i < num; i++) {
                html += "<input type='text' class = 'group-name-cla' style = 'margin-right: 10px;margin-bottom: 5px' name='themeName"+i+"' placeholder='活动组" + (i+1) + "号成员昵称' notnull>";
            }
            $("#group-names").html(html);
        }
    }

    $(function(){
        //action="activity/to-add-group.json" method="post"
        $("#group-add-submit").click(function() {
            var groupName = $("#add-group-form").find("[name=groupName]").val();
            var memberNumber = $("#add-group-form").find("[name=memberNumber]").val();
            var groupNeedMoney = $("#add-group-form").find("[name=groupNeedMoney]").val();
            var desc = $("#add-group-form").find("[name=desc]").val();
            if (groupName == undefined || groupName.trim() == "") {
                UIkit.notify("活动组名称不能为空!!!", {status:'danger', pos:'top-right'});
                return;
            }
            if (memberNumber == undefined || memberNumber <= 0 || memberNames == "") {
                UIkit.notify("活动组人数不能为空!!!", {status:'danger', pos:'top-right'});
                return;
            }
            if (groupNeedMoney == undefined || groupNeedMoney < 0 || groupNeedMoney == "") {
                UIkit.notify("活动收费金额不能为空!!!", {status:'danger', pos:'top-right'});
                return;
            }

            var memberNames = "";
            var nameIsNull = false;
            $($("#group-names").find(".group-name-cla")).each(function(i, v){
                var $this = $(this);
                var name_ = $this.val();
                if (name_ == undefined || name_.trim() == "") {
                    if (nameIsNull == false) {
                        nameIsNull = true;
                    }
                }
                memberNames += $this.val() + "、";
            });
            if (nameIsNull == true) {
                UIkit.notify("组成员名称不能为空!!!", {status:'danger', pos:'top-right'});
                return;
            }
            memberNames = memberNames.substr(0, memberNames.length - 1);

            $.ajax({
                type: "POST",
                url: "/activity/to-add-group.json",
                data:{"activityId" : ${activity.id}, "groupName" : groupName, "memberNumber" : memberNumber,
                    "needMoney" : groupNeedMoney, "desc" : desc, "memberNames" : memberNames},
                dataType:"json",
                success:function (msg) {
                    UIkit.notify("添加成功!!!", {status:'danger', pos:'top-right'});
                    location=location;
                },
                error:function (XMLHttpRequest,textStatus,errorThrom){
                    console.log("不明原因造成数据获取失败... ...");
                }
            });
        });
    });
</script>
