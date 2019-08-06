/**
 * Created by wjcaozhi1314 on 2016/3/23.
 */
define(["jquery"],function ($) {
    var audio = {
        //播放按钮
        click: function (id,audio) {

            $(id).click(function () {
                if (audio.paused) {
                    audio.play();//开始播放
                } else {
                    audio.pause();//暂停播放
                }
            });
        },
        //当媒介已开始播放时运行的脚本。
        playing:function (audioObj){
            audioObj.elent.onplaying = function (){
                $(audioObj.id).attr("src", audioObj.bigImg);
            }
        },
        //当媒介被用户或程序暂停时运行的脚本。
        pause:function(audioObj){
            audioObj.elent.onpause = function (){
                $(audioObj.id).attr("src", audioObj.endImg);
            }
        },
        //在浏览器不论何种原因未能取回媒介数据时运行的脚本。
        stalled:function(){},
        //当在文件加载期间发生错误时运行的脚本。
        error:function(){}
    };
    return audio;
});