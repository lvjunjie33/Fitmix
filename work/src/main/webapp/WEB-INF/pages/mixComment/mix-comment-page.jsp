<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/4 0004
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

    .user-avatar{
        width: 50px;
        min-height: 50px;
    }

    .comment-panel{
        min-height: 60px;
        border-bottom: 1px dotted #ccc;
        display: flex;
        flex-wrap: wrap;
        padding: 15px 10px 8px 10px;;
    }

    .comment-avatar-panel{
        height: 60px;
        width: 60px;;
    }

    .comment-content-panel{
        min-height: 60px;
        margin-left: 15px;
        width: 92%;
    }

    .content-panel-info{
        min-height: 40px;
    }

    .content-panel-bottom{
        font-size: 10px;
        color: #707070;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        line-height: 2;
    }


    /* 回复楼层 */
    .reply-comment{
        font-size: 12px;
        padding: 8px 19px;
        margin-top: 10px;
        line-height: 20px;
        background: #f4f4f4;
        border: 1px solid #dedede;
        position: relative;
        border-radius: 3px;
    }

    .diff{
        color: #f4f4f4;
        font-size: 30px;
        font-weight: bold;
        position: absolute;
        top: -11px;
    }

    .bd{
        color: #dedede;
        font-style: normal;
        font-size: 30px;
        position: absolute;
        top: -1px;
    }

    .bg{
        color: #f4f4f4;
        font-style: normal;
        font-size: 30px;
        position: absolute;
        top: 0px;
    }
</style>

<div class="uk-container-center uk-body">
    <h2>${mix.name}</h2>
    <c:if test="${empty page.result}">
        <h2>暂无评论..</h2>
    </c:if>
    <c:forEach items="${page.result}" var="mixComment">
        <div class="uk-panel comment-panel">
            <div class="comment-avatar-panel">
                <a class="uk-thumbnail" href="javascript:">    <!-- This is an anchor as a thumbnail -->
                    <img src="${mixComment.user.avatar}" class="user-avatar">
                </a>
            </div>
            <div class="comment-content-panel">
                <div class="content-panel-info">
                    <label><a href="javascript:">${mixComment.user.name}</a>：</label>
                    <label>${mixComment.comment}</label>

                    <c:if test="${!empty mixComment.replyMixComment}">
                        <div class="reply-comment">
                            <span class="diff">
                                <i class="bd">◆</i>
                                <i class="bg">◆</i>
                            </span>
                            <label><a href="javascript:">${mixComment.replyMixComment.user.name}</a>：</label>
                            <label>${mixComment.replyMixComment.comment}</label>
                        </div>
                    </c:if>
                </div>
                <div class="content-panel-bottom">
                    <div>
                        <label><lch:parse-date time="${mixComment.addTime}" pattern="yyyy-MM-dd HH:mm"/></label>
                    </div>
                    <div>
                        <a href="javascript:" comment-sort data-id="${mixComment.id}" data-mid="${mixComment.mid}">置顶</a>
                        <a href="javascript:" comment-remove data-id="${mixComment.id}" style="color: #ff2834;">删除</a>
                    </div>
                </div>
            </div>
        </div>

    </c:forEach>

    <lch:page page="${page}" href="/mix-comment/mix-comment-page.htm"/>
</div>

<script type="text/javascript">
    $(function(){
        $("[comment-remove]").click(function(){
            var id = $(this).attr("data-id");
            var th = $(this);
            var parent = th.parent().parent().parent().parent();
            if (window.confirm("确认删除？")) {
                $.post("/mix-comment/remove-mix-comment.json", {"id" : id}, function(){
                    parent.hide(2, function(){
                        parent.remove();
                    });
                });
            }
        });

        /// 置顶

        $("[comment-sort]").click(function(){
            var th = $(this);
            var id = $(this).attr("data-id");
            var mid = $(this).attr("data-mid");
            if (window.confirm("确认置顶？")) {
                $.post("/mix-comment/top-mix-comment.json", {"id" : id, "mid" : mid}, function(){
                    location.reload();
                });
            }
        });
    });

</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>