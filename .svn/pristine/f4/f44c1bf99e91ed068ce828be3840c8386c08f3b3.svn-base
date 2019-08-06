var BBS ={
    article: {
        init: function() {
            $(".bbs-article-box").on("click", ".bbs-article-item", function() {
                window.location.href = "/bbs/article/" + $(this).data("article-id");
            });
            BBS.article.getArticleList(0, 10);
        },
        getArticleList: function(index, size) {
            $.ajax({
                url: "/bbs/articleList",
                type: "GET",
                dataType: "json",
                data: {
                    index: index,
                    size: size
                },
                success: function(obj) {
                    var html = "";
                    for (var i = 0; i < obj.result.length; i++) {
                        var date = new Date(obj.result[i].addTime);
                        var template = $("#articleItemTemplate").html();
                        template = template.replace(/#id#/g, obj.result[i].id)
                            .replace(/#coverUrl#/g, obj.result[i].coverUrl)
                            .replace(/#title#/g, obj.result[i].title)
                            .replace(/#desc#/g, obj.result[i].desc)
                            .replace(/#category#/g, obj.result[i].category)
                            .replace(/#addTime#/g, date.format("yyyy-MM-dd"));
                        html += template;
                    }
                    $(".bbs-article-box").append(html);
                    if(obj.hasNext) {
                        $("#more").html('<div onclick="BBS.article.getArticleList('+ (index + 1) +' ,10)" class="bbs-row bbs-more">查看更多</div>');
                    } else {
                        $("#more").html('');
                    }
                }
            });
        }
    },
    dynamic: {
        init: function() {
            BBS.dynamic.getDynamicList(0);
        },
        getDynamicList: function (index) {
            $.ajax({
                url: "/bbs/dynamicList",
                type:"GET",
                dataType: "json",
                data: {
                    index: index
                },
                success: function(obj) {
                    var html = "";

                    for (var i = 0; i < obj.result.length; i++) {
                        var date = new Date(obj.result[i].addTime);
                        if(obj.result[i].type == 1) {
                            html += '<div class="bbs-row bbs-dynamic-item">'+
                                        '<div class="bbs-row bbs-dynamic-user">'+
                                            '<img src="'+obj.result[i].user.avatar+'" />'+
                                            '<p>&nbsp;'+obj.result[i].user.name+'</p>'+
                                        '</div>'+
                                        '<div class="bbs-row bbs-dynamic-time">'+
                                            '发布时间:'+date.format("yyyy-MM-dd")+
                                        '</div>'+
                                        '<div class="bbs-row bbs-dynamic-content">'+
                                            obj.result[i].content+
                                            //'<img src="/imgs/detail_02_1.jpg" />'+
                                        '</div>'+
                                        '<div class="bbs-row bbs-dynamic-menu">'+
                                            '<span class="bbs-dynamic-flower"><i class="iconfont">&#xe602;</i> '+obj.result[i].flowerCount+' &nbsp;</span>'+
                                            '<span class="bbs-dynamic-comment"><i class="iconfont">&#xe601;</i> '+obj.result[i].commentCount+' </span>'+
                                        '</div>'+
                                        '<div class="bbs-row bbs-dynamic-comment-list">';
                                        //评论列表
                                        for(var j = 0; j < obj.result[i].commentList.length; j++) {
                                            var time = new Date(obj.result[i].commentList[j].addTime);
                                            if(obj.result[i].commentList[j].toUser == null) {
                                                html += '<div class="bbs-row bbs-dynamic-comment-item">'+
                                                    '<div class="user">'+
                                                    '<img src="'+obj.result[i].commentList[j].fromUser.avatar+'"/>'+
                                                    '</div>'+
                                                    '<div class="content"><span>&nbsp;'+obj.result[i].commentList[j].fromUser.name+' &nbsp;:</span>'+obj.result[i].commentList[j].content+'</div>'+
                                                    '<div class="date"><span>'+time.format("yyyy-MM-dd")+'</span></div>'+
                                                    '</div>';
                                            } else {
                                                html += '<div class="bbs-row bbs-dynamic-comment-item">'+
                                                    '<div class="user">'+
                                                    '<img src="'+obj.result[i].commentList[i].fromUser.avatar+'"/>'+
                                                    '</div>'+
                                                    '<div class="content"><span>&nbsp;'+obj.result[i].commentList[j].fromUser.name+' &nbsp;回复&nbsp;'+'+obj.result[i].commentList[j].toUser.name+'+':</span>'+obj.result[i].commentList[j].content+'</div>'+
                                                    '<div class="date"><span>'+time.format("yyyy-MM-dd")+'</span></div>'+
                                                    '</div>';
                                            }
                                        }
                            // 回复
                            //html += '<div class="bbs-row bbs-dynamic-comment-item">'+
                            //            '<div class="input-group">'+
                            //            '<input type="text" class="form-control">'+
                            //            '<span class="input-group-btn">'+
                            //                '<button class="btn bbs-input-btn" type="button">回复</button>'+
                            //            '</span>'+
                            //            '</div>'+
                            //        '</div>'+
                            html += '</div>'+
                                    '</div>';
                        }
                    }

                    $(".bbs-dynamic-box").append(html);
                    if(obj.hasNext) {
                        $("#more").html('<div onclick="BBS.dynamic.getDynamicList('+ (index + 1) +')" class="bbs-row bbs-more">查看更多</div>');
                    } else {
                        $("#more").html('');
                    }
                }

            });
        }
    }
};