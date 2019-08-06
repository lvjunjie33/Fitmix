<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/2/16
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style>

    #speedway-list{
        margin-top: 10px;
    }

    .speedway{
        margin-bottom: 100px;;
    }
    .group-fee-span{
        min-width: 410px;
        display: inline-block;
    }

    .group-fee-span1{
        min-width: 150px;
        display: inline-block;
    }

    .color-red {
        color: red;
    }

</style>

<div class="uk-form-row">
    <a href="#speedway-add" class="uk-button" data-uk-modal>添加</a>
</div>

<div id="speedway-list">
    <c:forEach var="speedway" items="${page.result}">
        <div class="speedway">
            <div style="margin-top: 10px;float:left;margin-right: 100px">
                    ${speedway.title}
                <a href="/speedway/modify.htm?speedwayId=${speedway.id}">修改</a>
                <p>城市：${speedway.city}</p>
                <p>万德赛道编号：${speedway.wayId}</p>
                <p>描述：${speedway.des}</p>
                <p>
                <c:choose>
                    <c:when test="${speedway.status eq 1}">
                        <label style="color:#6de1ff;">正常</label>
                    </c:when>
                    <c:when test="${speedway.status eq 0}">
                        <label style="color:#ff405d;">无效</label>
                    </c:when>
                </c:choose>
                </p>
                <p>
                <c:choose>
                    <c:when test="${speedway.releaseStatus eq 0}">
                        <label style="color:#6de1ff;">发布</label>
                    </c:when>
                    <c:when test="${speedway.releaseStatus eq 1}">
                        <label style="color:#ffb852;">待发布</label>
                    </c:when>
                </c:choose>
                </p>
                <p><h3>=========Banner=======</h3><a style="background-color:#6de1ff;" href="#speedway-add-banner" class="uk-button add-banner-cls" data-uk-modal speedway-id = "${speedway.id}">添加Banner</a></p>
                <c:forEach var = "mixBanner" items="${speedway.mixBanners}" >
                    <p>${mixBanner.title}(${mixBanner.id})&nbsp;&nbsp;&nbsp;&nbsp;<button style="background-color:#A9FF6E;" class="uk-button add-banner-cls remove-banner-cls" speedway-id = "${speedway.id}" banner-id = "${mixBanner.id}">删除</button></p>
                </c:forEach>
            </div>
            <div class="uk-thumbnail uk-thumbnail-large">
                <img src="<lch:build-path url="${speedway.backgroundImage}"/>">
            </div>
            <div style="clear: left;border-bottom: 1px solid silver;height: 1px;padding: 0px;margin: 0px"></div>
        </div>
    </c:forEach>
</div>

<%--分页条--%>
<lch:page page="${page}" href="/wd/list.htm"></lch:page>

<jsp:include page="add.jsp"/>

<script>
    $(function(){
        $(".add-banner-cls").click(function(){
            var $this = $(this);
            $("#banner-add-form").find("[name=speedwayId]").val($this.attr("speedway-id"));
        });

        $(".remove-banner-cls").click(function(){
            var $this = $(this);
            var speedwayId = $this.attr("speedway-id");
            var bannerId = $this.attr("banner-id");
            $.ajax({
                type: "POST",
                url: "/speedway/speedway-modify-banner.json",
                data:{'speedwayId':speedwayId, 'isRemove': 0, 'bannerId':bannerId},
                dataType:"json",
                success:function (msg) {
                    location = location;
                },
                error:function (XMLHttpRequest,textStatus,errorThrom){
                    console.log("不明原因造成数据获取失败... ...");
                }
            });
        });
    });

</script>

<jsp:include page="banner-add.jsp"/>


