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
        <form action="/mix/mix-list.htm" method="get" class="uk-form">
            <div class="uk-form-row">
                <div class="uk-form-row">
                    <label>状态：</label>
                    <label><input type="radio" name="filter[state]" value="1" <c:if test="${page.filter.state == 1}">checked</c:if>/>上架</label>&nbsp;&nbsp;&nbsp;
                    <label><input type="radio" name="filter[state]" value="2" <c:if test="${page.filter.state == 2}">checked</c:if>/>下架</label>&nbsp;&nbsp;&nbsp;
                </div>
                <div class="uk-form-row">
                    <label>类型：</label>
                    <label class="uk-width-1-6"><input type="radio" name="filter[type]" value="1" <c:if test="${page.filter.type == 1}">checked="checked"</c:if> />&nbsp;运动歌单</label>
                    <label class="uk-width-1-6"><input type="radio" name="filter[type]" value="2" <c:if test="${page.filter.type == 2}">checked="checked"</c:if> />&nbsp;电台</label>
                </div>
                <div class="uk-form-row">
                    <label>场景：</label>


                     <span id="scene"><lch:dic-option type="2" split="&nbsp;&nbsp;&nbsp;" higValue="${page.filter.scene}"
                                    label="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"filter[scene]\" value=\"%s\">&nbsp;%s</label>"
                                    higLabel="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"filter[scene]\" value=\"%s\" checked>&nbsp;%s</label>"/>
                    </span>
                </div>

                <lch:mix-genre-parent higValue="${page.filter['genre']}" split=""
                                      label="<label class=\"uk-width-1-6\"><input type=\"checkbox\" name=\"filter[genre]\" value=\"%s\">&nbsp;%s</label>"
                                      higLabel="<label class=\"uk-width-1-6\"><input type=\"checkbox\" name=\"filter[genre]\" value=\"%s\" checked>&nbsp;%s</label>"/>


                <div class="uk-form-row">
                    <label>编号：</label>
                    <label class="mix-scene"><input type="number" name="filter[customIdentification]" value="${page.filter.customIdentification}" placeholder="歌曲标识"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>ID：</label>
                    <label class="mix-scene"><input type="number" name="filter[id]" value="${page.filter.id}" placeholder="歌曲编号"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>名称：</label>
                    <label class="mix-scene"><input type="text" name="filter[name]" value="${page.filter.name}" placeholder="歌曲名称"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>时长：</label>
                    <label class="mix-scene"><input type="number" name="filter[trackLength]" value="${page.filter.trackLength}" placeholder="歌曲时长"></label>
                </div>
                <div class="uk-form-row">
                    <label>时间：</label>
                    <label class="mix-scene"><input type="text" name="filter[startTime]" value="${page.filter.startTime}" data-uk-datepicker="{format:'YYYY-MM-DD'}" placeholder="上架时间"></label>
                    <label>至</label>
                    <label class="mix-scene"><input type="text" name="filter[endTime]" value="${page.filter.endTime}" data-uk-datepicker="{format:'YYYY-MM-DD'}" placeholder="上架时间"></label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="uk-button uk-button-primary">查询</button>
                    <a href="/mix/mix-add.htm" target="_blank" class="uk-button uk-button-primary">添加Mix</a>
                    <lch:auth-operation url="mix/clear-sort.json">
                        <a clear-sort class="uk-button uk-button-danger">清除所有sort</a>
                    </lch:auth-operation>
                </div>
            </div>
        </form>
    </div>
    <hr class="uk-article-divider">

    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
            <thead>
            <tr>
                <th style="width: 100px;">
                    <c:choose>
                        <c:when test='${page.filter.customIdentificationSort eq ("desc")}'>
                            <a href="/mix/mix-list.htm?filter[customIdentificationSort]=asc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-desc">ID/编号</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/mix/mix-list.htm?filter[customIdentificationSort]=desc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-up">ID/编号</a>
                        </c:otherwise>
                    </c:choose>
                </th>
                <th style="width: 200px;">名称</th>
                <th style="width: 100px">场景</th>
                <th style="width: 100px">分类</th>
                <th style="width: 100px;">曲风</th>
                <th style="width: 50px;">
                    <c:choose>
                        <c:when test='${page.filter.bpmSort eq ("desc")}'>
                            <a href="/mix/mix-list.htm?filter[bpmSort]=asc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-desc">bpm</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/mix/mix-list.htm?filter[bpmSort]=desc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-up">bpm</a>
                        </c:otherwise>
                    </c:choose>
                </th>
                <th style="width: 70px">时长(m)</th>
                <th style="width: 50px">
                    <c:choose>
                        <c:when test='${page.filter.sortSort eq ("desc")}'>
                            <a href="/mix/mix-list.htm?filter[sortSort]=asc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-desc">sort</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/mix/mix-list.htm?filter[sortSort]=desc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-up">sort</a>
                        </c:otherwise>
                    </c:choose>
                </th>
                <th style="width: 50px;">
                    <c:choose>
                        <c:when test='${page.filter.downloadSort eq ("desc")}'>
                            <a href="/mix/mix-list.htm?filter[downloadSort]=asc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-desc">download</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/mix/mix-list.htm?filter[downloadSort]=desc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-up">download</a>
                        </c:otherwise>
                    </c:choose>
                </th>
                <th style="width: 50px;">
                    <c:choose>
                        <c:when test='${page.filter.collectSort eq ("desc")}'>
                            <a href="/mix/mix-list.htm?filter[collectSort]=asc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-desc">收藏</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/mix/mix-list.htm?filter[collectSort]=desc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-up">收藏</a>
                        </c:otherwise>
                    </c:choose>
                </th>
                <th style="width: 130px;">
                    <c:choose>
                        <c:when test='${page.filter.shelvesTimeSort eq ("desc")}'>
                            <a href="/mix/mix-list.htm?filter[shelvesTimeSort]=asc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-desc">上架/下架</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/mix/mix-list.htm?filter[shelvesTimeSort]=desc<lch:build-page-filter page="${page}"/>" class="uk-icon-hover uk-icon-sort-up">上架/下架</a>
                        </c:otherwise>
                    </c:choose>
                </th>
                <th style="width: 120px;">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="mix" items="${page.result}">
                <tr>
                    <td><label name="id">${mix.id}</label>/${mix.customIdentification}</td>
                    <td><a href="/mix/mix-info.htm?id=${mix.id}" target="_blank">${mix.name}</a></td>
                    <c:choose>
                        <c:when test="${mix.type == 2}">
                            <td><lch:dic-name-array type="9" value="${mix.scene}"/></td>
                            <td><lch:dic-name-array type="3" value="${mix.genreParent}"/></td>
                            <td><lch:dic-name-array type="7" value="${mix.genre}"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><lch:dic-name-array type="2" value="${mix.scene}"/></td>
                            <td><lch:dic-name-array type="3" value="${mix.genreParent}"/></td>
                            <td><lch:dic-name-array type="7" value="${mix.genre}"/></td>
                        </c:otherwise>
                    </c:choose>
                    <%--<td><lch:dic-name-array type="2" value="${mix.scene}"/></td>--%>
                    <%--<td><lch:dic-name-array type="3" value="${mix.genreParent}"/></td>--%>
                    <%--<td><lch:dic-name-array type="7" value="${mix.genre}"/></td>--%>
                    <td>${mix.bpm}</td>
                    <td>${mix.trackLength}</td>
                    <td>${mix.sort}</td>
                    <td>${mix.downloadCount}</td>
                    <td>${mix.collectNumber}</td>
                    <td><lch:parse-date time="${mix.shelvesTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a href="#mix-recommend" class="mix-recommend-cls" tx-id = "${mix.id}" data-uk-modal>推荐</a>
                        &nbsp;
                        <lch:auth-operation url="/mix/mix-modify-state.json">
                            <c:choose>
                                <c:when test="${mix.state == 2}"><a href="javascript:" no-shelves>下架</a></c:when>
                                <c:when test="${mix.state == 1}"><a href="javascript:" shelves>上架</a></c:when>
                            </c:choose>
                        </lch:auth-operation>
                        &nbsp;
                        <lch:auth-operation url="/mix/mix-sort.json">
                            <a href="#mix-sort-modal" data-uk-modal mix-sort>sort</a>
                        </lch:auth-operation>
                        &nbsp;
                        <lch:auth-operation url="/mix/mix-remove.json">
                            <a href="javascript:" mix-remove style="color:#ff4a35;" data-id="${mix.id}">删除</a>
                        </lch:auth-operation>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <%--分页条--%>
    <lch:page page="${page}" href="/mix/mix-list.htm"></lch:page>
</div>

<jsp:include page="mix-other.jsp"/>


<script type="text/javascript">

    $(function(){
        $("[mix-remove]").click(function(){
            if (window.confirm("是否删除？")) {
                var th = $(this);
                var id = th.attr("data-id");
                $.post("/mix/mix-remove.json", {"mid":id}, function(result){
                    th.parent().parent().hide(400).remove();
                });
            }
        });
    });

    $(function(){
        $(".mix-recommend-cls").click(function() {
            var $this = $(this)

            var _id = $this.attr("tx-id");
            $("#mix-recommend-form").find("[name=mid]").val(_id);
        });
    });

    $(function(){
        $("[shelves]").on("click", shelves);
        $("[no-shelves]").on("click", onShelves);

        function shelves () {
            if (window.confirm("确认上架？")) {
                var th = $(this);
                var dataJson = th.parent().parent().sequenceJson();
                dataJson["state"] = 2;
                $.post("/mix/mix-modify-state.json", dataJson, function(result){
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
                $.post("/mix/mix-modify-state.json", dataJson, function(result){
                    th.replaceWith('<a href="javascript:" shelves>上架</a>');
                    $("[shelves]").on("click", shelves);
                });
            }
        }
    });


  $(function(){
      $("[clear-sort]").click(function(){
          if (window.confirm("确认清楚所有sort?")) {
              $.post("/mix/clear-sort.json", {}, function(result){
                  if (result.code == 0) {
                        window.location.reload();
                  }
              });
          }
      });
  });

  $(function(){
      //初始化
      if($("[name='filter[type]']:checked").val() == 2) {
          $("#scene").html('<lch:dic-option type="9" split="&nbsp;&nbsp;&nbsp;" higValue="${page.filter.scene}"
                                    label="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"filter[scene]\" value=\"%s\">&nbsp;%s</label>"
                                    higLabel="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"filter[scene]\" value=\"%s\" checked>&nbsp;%s</label>"/>');
      } else {
          $("#scene").html('<lch:dic-option type="2" split="&nbsp;&nbsp;&nbsp;" higValue="${page.filter.scene}"
                                    label="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"filter[scene]\" value=\"%s\">&nbsp;%s</label>"
                                    higLabel="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"filter[scene]\" value=\"%s\" checked>&nbsp;%s</label>"/>');
      }

      $("[name='filter[type]']").click(function() {
          if($("[name='filter[type]']:checked").val() == 2) {
              $("#scene").html('<lch:dic-option type="9" split="&nbsp;&nbsp;&nbsp;" higValue="0"
                                    label="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"filter[scene]\" value=\"%s\">&nbsp;%s</label>"
                                    higLabel="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"filter[scene]\" value=\"%s\" checked>&nbsp;%s</label>"/>');
          } else {
              $("#scene").html('<lch:dic-option type="2" split="&nbsp;&nbsp;&nbsp;" higValue="0"
                                    label="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"filter[scene]\" value=\"%s\">&nbsp;%s</label>"
                                    higLabel="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"filter[scene]\" value=\"%s\" checked>&nbsp;%s</label>"/>');
          }
      });

  });

</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<%--  sort  --%>
<%@ include file="mix-sort.jsp" %>