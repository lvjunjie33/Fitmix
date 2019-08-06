package com.business.live.send;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.user.User;
import com.business.core.operations.user.UserCoreDao;
import com.business.live.base.constants.CodeConstants;
import com.business.live.base.support.BaseControllerSupport;
import com.business.live.socket.SocketClientServer;
import com.business.live.socket.SocketClientThread;
import com.business.live.socket.SocketMessage;
import com.business.live.socket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.util.List;

/**
 * Created by sin on 2015/6/17 0017.
 */
@Controller
@RequestMapping("send")
public class SenderController extends BaseControllerSupport {

    @Autowired
    private UserCoreDao userCoreDao;

    /**
     * 发送 socket 语音消息
     * @param request 语音
     * @param uid 用户编号
     */
    @RequestMapping("send-voice-h5")
    public void sendVoiceH5(Model model, HttpServletRequest request, @RequestParam("uid") Integer uid) {
        User user = userCoreDao.findUserById(uid, "name", "avatar");
        String avatar = null == user.getAvatar() ? "" : FileCenterClient.buildUrl(user.getAvatar());
        List<String> fileUrls = FileCenterClient.uploadApp(request, FileConstants.FILE_TYPE_USER_LIVE_VOICE);
        if (CollectionUtils.isEmpty(fileUrls)) {//集合为空没有语音上传
            tip(model, CodeConstants.USER_LIVE_VOICE_NOT_UPLOAD, "语音没有上传");
        }
        else {
            // 向 h5 发送 socket 信息，告诉语音消息在哪。
            List<Session> sessionList = WebSocketServer.onLineClientUserMap.get(uid);
            SocketMessage socketMessage = new SocketMessage();
            socketMessage.setUa(avatar);
            socketMessage.setNa(user.getName());
            socketMessage.setMsgType(SocketMessage.MSG_TYPE_VOICE);
            socketMessage.setMsg(SocketMessage.MSG_V);
            socketMessage.setFu(FileCenterClient.buildUrl(fileUrls.get(0)));
            socketMessage.setOnl(sessionList.size());
            socketMessage.setFu(FileCenterClient.buildUrl(fileUrls.get(0)));
            WebSocketServer.sendAll(sessionList, socketMessage);
        }
    }

    /**
     * 发送 socket 语音消息
     * @param voice  语音
     * @param uid 用户编号
     */
    @RequestMapping("send-voice-app")
    public void sendVoiceApp(Model model, @RequestParam("voice") MultipartFile voice, @RequestParam("uid") Integer uid) {
        String fileUrls = FileCenterClient.upload(voice, FileConstants.FILE_TYPE_USER_LIVE_VOICE);
        if (StringUtils.isEmpty(fileUrls)) {
            tip(model, CodeConstants.USER_LIVE_VOICE_NOT_UPLOAD, "语音没有上传");
        }
        else {
            // 向 app 发送 socket 信息，告诉语音消息在哪。
            WebSocketServer.sendAll(
                    WebSocketServer.onLineClientUserMap.get(uid),
                    FileCenterClient.buildUrl(fileUrls),
                    SocketMessage.SNA_WU,
                    SocketMessage.MSG_TYPE_VOICE);

            SocketClientThread socketClientThread = SocketClientServer.clients.get(uid);
            SocketMessage message = new SocketMessage();
            message.setSna(SocketMessage.SNA_WU);
            // TODO  发送消息内容需要填写
            SocketClientServer.sendMessage(socketClientThread.writer, message);
        }
    }
}
