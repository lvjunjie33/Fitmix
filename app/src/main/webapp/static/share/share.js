/**
 * Created by wjcaozhi1314 on 2016/3/23.
 */
require.config({
    baseUrl: "static",
    paths: {
        "jquery": "jquery-1.11.2.min",
        "audio": "audio/audio",
        "shareObjAll": "share/shareObjAll"
    },
    shim: {
        "shareObjAll": {
            exports: "shareObjAll"
        }
    }
});

require(["jquery", "audio","shareObjAll"], function ($, audio) {
    $(function () {

        $("body").eq(0).css("overflow", "hidden");
        $(".home-div").css({
            "width": $("body").eq(0).css("width"),
            "height": $(window).height() + "px"
        });

        audioObj.elent = document.getElementById("audio");

        audio.click(audioObj.id, audioObj.elent);
        audio.playing(audioObj);
        audio.pause(audioObj);
        audio.stalled();
        audio.error();



    });
});