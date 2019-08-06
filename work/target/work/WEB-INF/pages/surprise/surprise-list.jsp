<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <form action="/surprise/surprise-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>彩蛋编号：</label>
                    <label class="mix-scene"><input type="number" name="filter[id]" value="${page.filter.id}" placeholder="彩蛋编号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/surprise/surprise-add.htm" target="_blank" class="uk-button uk-button-primary">添加彩蛋</a>
                </div>
            </div>
        </form>
    </div>
    <hr class="uk-article-divider">
</div>

<div class="uk-overflow-container">
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
        <thead>
        <tr>
            <th style="width: 100px;">彩蛋编号</th>
            <th style="width: 200px;">彩蛋名称</th>
            <th style="width: 100px">类型</th>
            <th style="width: 100px">开始时间</th>
            <th style="width: 100px">结束时间</th>
            <th style="width: 100px">创建时间</th>
            <th style="width: 120px">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="surprise" items="${page.result}">
            <tr>
                <td><label name="id">${surprise.id}</label></td>
                <td><a href="/surprise/surprise-info.htm?id=${surprise.id}" target="_blank">${surprise.name}</a></td>
                <td>
                    <c:choose>
                        <c:when test="${surprise.type == 0}">
                            默认图片
                        </c:when>
                        <c:when test="${surprise.type == 1}">
                            静态图片
                        </c:when>
                        <c:when test="${surprise.type == 2}">
                            文字
                        </c:when>
                        <c:when test="${surprise.type == 3}">
                            天气
                        </c:when>
                        <c:otherwise>
                            默认图片
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><lch:parse-date time="${surprise.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><lch:parse-date time="${surprise.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><lch:parse-date time="${surprise.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <lch:auth-operation url="/surprise/surprise-modify-state.json">
                        <c:choose>
                            <c:when test="${surprise.state == 2}"><a href="javascript:" no-shelves>下架</a></c:when>
                            <c:when test="${surprise.state == 1}"><a href="javascript:" shelves>上架</a></c:when>
                        </c:choose>
                    </lch:auth-operation>
                    &nbsp;
                    <lch:auth-operation url="/surprise/surprise-remove.json">
                        <a href="javascript:" surprise-remove style="color:#ff4a35;" data-id="${surprise.id}">删除</a>
                    </lch:auth-operation>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/surprise/surprise-list.htm"></lch:page>
<script>
    $(function(){
        $("[surprise-remove]").click(function(){
            if (window.confirm("是否删除？")) {
                var th = $(this);
                var id = th.attr("data-id");
                $.post("/surprise/surprise-remove.json", {"id":id}, function(result){
                    th.parent().parent().hide(400).remove();
                });
            }
        });
    });

    $(function(){
        $("[shelves]").on("click", shelves);
        $("[no-shelves]").on("click", onShelves);

        function shelves () {
            if (window.confirm("确认上架？")) {
                var th = $(this);
                debugger
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["state"] = 2;
                $.post("/surprise/surprise-modify-state.json", dataJson, function(result){
                    var code = result['code'];
                    if (code == 0) {
                        th.replaceWith('<a href="javascript:" no-shelves>下架</a>');
                        $("[no-shelves]").on("click", onShelves);
                    }
                    else{
                        UIkit.notify(result['msg'], {status:'danger', pos:'top-right'});
                    }
                });
            }
        }

        function onShelves () {
            if (window.confirm("确认下架？")) {
                var th = $(this);
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["state"] = 1;
                $.post("/surprise/surprise-modify-state.json", dataJson, function(result){
                    th.replaceWith('<a href="javascript:" shelves>上架</a>');
                    $("[shelves]").on("click", shelves);
                });
            }
        }
    });


</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
