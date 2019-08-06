<%--
  Created by IntelliJ IDEA.
  User: Cheri
  Date: 2015/9/7
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<style type="text/css">

    .short {
        width: 75px;
    }

    .time{
        width: 100px;
    }

</style>

<form action="/user-experience/page.htm" method="get" class="uk-form">
  <div class="uk-form-row uk-grid">
      <div class="uk-width-1-5">
          <label>编号：</label>
          <input type="number" name="filter[id]" value="${page.filter.id}" placeholder="用户编号"/>
      </div>

      <div class="uk-width-1-5">
          <label>渠道：</label>
          <fmt:parseNumber value="${page.filter.channel}" var="channelNumber"/>
          <select name="filter[channel]">
              <option value="">请选择</option>
              <lch:dic-option type="8" higValue="${channelNumber}"
                              label="<option value=\"%s\">%s</option>"
                              higLabel="<option value=\"%s\" selected>%s</option>" />
          </select>
      </div>

      <div class="uk-width-1-5">
          <label>版本：</label>
          <input type="text" name="filter[version]" value="${page.filter.version}" placeholder="版本"/>
      </div>
  </div>

  <div class="uk-form-row uk-grid">
      <div class="uk-width-1-5">
          <label>次数：</label>
          <label><input type="number" name="filter[beginExperienceCount]" class="short" value="${page.filter.beginExperienceCount}" placeholder="体验"/></label>
          -
          <label><input type="number" name="filter[endExperienceCount]" class="short" value="${page.filter.endExperienceCount}" placeholder="体验"/></label>
      </div>

      <div class="uk-width-2-5">
          <label>时间：</label>
          <label><input type="text" name="filter[beginRegisterTime]" value="${page.filter.beginRegisterTime}" placeholder="注册时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>
          -
          <label><input type="text" name="filter[endRegisterTime]" value="${page.filter.endRegisterTime}" placeholder="注册时间" data-uk-datepicker="{format:'YYYY-MM-DD'}" class="time"/></label>

          <button class="uk-button uk-button-primary">查询</button>
      </div>
  </div>
</form>

<table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
  <thead>
      <tr>
          <th>编号</th>
          <th>地区</th>
          <th>sdk</th>
          <th>体验次数</th>
          <th>手机型号</th>
          <th>运营商</th>
          <th>渠道信息</th>
          <th>最后登录时间</th>
          <th>用户版本</th>
      </tr>
  </thead>
  <tbody>
      <c:forEach items="${page.result}" var="item">
          <tr>
              <td>${item.id}</td>
              <td>${item.taoBaoIp.area}</td>
              <td>${item.sdk}</td>
              <td>${item.count}</td>
              <td>${item.mobileType}</td>
              <td>${item.operators}</td>
              <td class="channel" data="${item.channel}"></td>
              <td><lch:parse-date time="${item.experienceTime}" pattern="yyyy-MM-dd HH:mm"/></td>
              <td>${item.version}</td>
          </tr>
      </c:forEach>
  </tbody>
</table>

<%--分页条--%>
<lch:page page="${page}" href="/user-experience/page.htm"></lch:page>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>


<script type="text/javascript">
  $(".channel").each(function(index, th){
      $(this).html(dictionaryName($(this).attr("data")))
  })

  function dictionaryName(data) {
    var channelDictionary = ${channelDictionary};
    for (var i = 0; i < channelDictionary.length; i++) {
      var dic = channelDictionary[i];
      if (data == "appStore") { // 处理 中文 渠道
        return "appStore";
      }
      else if ((data == dic.value) || (/\s+/.test(data) && parseInt(data) == dic.value)) {  // app 上传渠道 001 or 1 ，字典 1 处理不兼容
        return dic.name;
      }
    }
    return "<label style='color: #ff567f;'>error({0})</label>".format(data);
  }
</script>