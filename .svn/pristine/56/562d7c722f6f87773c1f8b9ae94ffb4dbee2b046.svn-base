/**
 * 微信功能js
 * Created by wjcaozhi1314 on 2015/12/22.
 */
/***
 * 录音功能
 */
function wx_yuYinClick(event){
    var wx = event.data.wx;

    yuYinPd = yuYinPd ? false : true;
    var socketJson = {};
    var myServerId = "";//微信服务器语音文件id
    var myLocalId = "";//微信录音本地id

    socketJson["na"] = $("#na").text();
    socketJson["nickname"] = live.prototype.wx_userInfo.nickname;
    socketJson["headimgurl"] = live.prototype.wx_userInfo.headimgurl;
    socketJson["msg"] = live.MSG_V;
    socketJson["msgtype"] = 3;

    if(yuYinPd){
        looger.info("调用微信的录音功能");
        $(".yuyinjingzhiJ2").removeClass("hidden");

        wx.startRecord();//开始录音
        wx.onVoiceRecordEnd({
            // 录音时间超过一分钟没有停止的时候会执行 complete 回调
            complete: function (res) {
                myLocalId = res.localId;
                $(".yuyinjingzhiJ2").addClass("hidden");

                //上传语音
                wx.uploadVoice({
                    localId: myLocalId, // 需要上传的音频的本地ID，由stopRecord接口获得
                    isShowProgressTips: 1, // 默认为1，显示进度提示
                    success: function (res) {

                        myServerId = res.serverId; // 返回音频的服务器端ID
                        looger.info("myServerId : " + myServerId);
                        socketJson["serverId"] = myServerId;

                        socketJson["fu"] =
                            live.prototype.socketInputVUrl+
                            live.prototype.wx_pt_access_token+
                            live.prototype.socketInputVUrlMediaId+
                            myServerId;

                        faSocket(liveSocketObj,socketJson);//发送socket
                    }
                });

            }
        });

    }else{
        looger.info("停止微信的录音功能");
        $(".yuyinjingzhiJ2").addClass("hidden");
        wx.stopRecord({
            //停止录音
            success: function (res) {
                myLocalId = res.localId;
                //上传语音
                wx.uploadVoice({
                    localId: myLocalId, // 需要上传的音频的本地ID，由stopRecord接口获得
                    isShowProgressTips: 1, // 默认为1，显示进度提示
                    success: function (res) {

                        myServerId = res.serverId; // 返回音频的服务器端ID
                        looger.info("myServerId : " + myServerId);
                        socketJson["serverId"] = myServerId;

                        socketJson["fu"] =
                            live.prototype.socketInputVUrl+
                            live.prototype.wx_pt_access_token+
                            live.prototype.socketInputVUrlMediaId+
                            myServerId;

                        faSocket(liveSocketObj,socketJson);//发送socket
                    }
                });

            }
        });
    }

    //yuYinPd = yuYinPd ? false : true;//设置当前语音录制状态

}
/***
 * 下载音频并播放音频
 * @param serverId
 */
function downloadVoice(serverId){

    wx.downloadVoice({
        serverId: serverId, // 需要下载的音频的服务器端ID，由uploadVoice接口获得
        isShowProgressTips: 0, // 默认为1，显示进度提示
        success: function (res) {
            var downloaLocalId = res.localId; // 返回音频的本地ID
            wx.playVoice({//播放录音
                localId: downloaLocalId // 需要播放的音频的本地ID，由stopRecord接口获得
            });
        }
    });

    wx.onVoicePlayEnd({//监听语音播放完毕接口
        success: function (res) {
            //var localId = res.localId; // 返回音频的本地ID

        }
    });
}
