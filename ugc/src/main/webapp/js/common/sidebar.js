// 侧边栏
var Sidebar = {
    init: function (regex) {
        var patt = new RegExp(regex);
        var menus = $(".sidebar-menu").find("a");
        menus.each(function () {
            if(patt.test($(this).attr("href"))) {
                $(this).addClass("active");
            }
        });
        // 专辑选择
        $(".dropdown-menu li").each(function(){
            var th = $(this);
            th.on("click", function () {
                var id = $(this).data("id");
                var currentId = $(".albums-select .content").data("id");
                if(id != currentId) {
                    $.ajax({
                        url: "/ugc/content/" + uid + "/albums/change",
                        data: {
                            albumsId: id
                        },
                        type: "post",
                        dataType: "json",
                        success: function(result) {
                            if(result.code == 0) {
                                window.location.reload();
                            } else {
                                bootbox.alert(result.message);
                            }
                        }

                    });
                }
            });
        });

    }
};