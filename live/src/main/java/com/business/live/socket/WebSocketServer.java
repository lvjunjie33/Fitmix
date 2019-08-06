package com.business.live.socket;

import java.io.IOException;
import java.util.*;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2015/5/12.
 */
@ServerEndpoint("/socket-server")
public class WebSocketServer {

    protected static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 发送的信息
     */
    public static final String KEY_CLIENT_UID = "i";
    /**
     * 发送的信息
     */
    public static final String KEY_MESSAGE = "m";
    /**
     * 是否重新 build 与 clint 映射关系
     *  y 否 n yes other 不操作
     */
    public static final String KEY_SEND_NUMBER = "sm";


//    public static Map<String, Integer> onLineClientSessionWithUid = new LinkedHashMap<>();

    /**
     *
     */
    public static Map<String, Session> onLineUserMap = new LinkedHashMap<>();
    /**
     * 直播用户和Session
     * key：uid
     * val：sessions
     */
    public static Map<Integer, List<Session>> onLineClientUserMap = new LinkedHashMap<>();

    private SocketMessage currentMessage;

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
    	logger.info("websocekt message text : "+ message);
        SocketMessage socketMessage = JSONObject.parseObject(message, SocketMessage.class);
        
        // 是否新用户进入直播
        if (!onLineUserMap.containsKey(session.getId())) {
            // 判断用户是否在直播
            if (SocketClientServer.clients.containsKey(socketMessage.getUi())) {
                currentMessage = socketMessage;
                onLineUserMap.put(session.getId(), session);
                // 判断用户是否是第一个进入, 第一个用户需要创建一个新的 List 集合
                if (onLineClientUserMap.containsKey(socketMessage.getUi())) {
                    onLineClientUserMap.get(socketMessage.getUi()).add(session);
                }
                else {
                    //用户第一次进入创建一个list
                    List<Session> firstUserSessionList = new ArrayList<>();
                    firstUserSessionList.add(session);
                    onLineClientUserMap.put(socketMessage.getUi(), firstUserSessionList);
                }
                logger.info("新用户加入...{}在线", onLineUserMap.size());
            }
            else { // Error cline 没有直播
                logger.info("直播结束了...{}在线", onLineUserMap.size());
//                session.getBasicRemote().sendText("呀，直播结束了。");
                session.close(); // 强制关闭
            }
        }
        else { // 非首次进入

            // 是否发送给 app
           /* if (SocketMessage.MSG_J.equals(socketMessage.getMsg())
            		|| SocketMessage.MSG_B.equals(socketMessage.getMsg())
                    || SocketMessage.MSG_V.equals(socketMessage.getMsg())
                    || SocketMessage.GJ.equals(socketMessage.getMsg())
                    || SocketMessage.GZ.equals(socketMessage.getMsg())
                    || SocketMessage.DIANJI.equals(socketMessage.getMsg())
                    || SocketMessage.LH.equals(socketMessage.getMsg())) {
            	logger.info("发送给app的message:"+socketMessage);
                sendApp(socketMessage);
            }*/
            logger.info("发送给app的message:"+socketMessage);
            sendApp(socketMessage);
            // 发送 h5 观看用户
            sendAll(onLineClientUserMap.get(currentMessage.getUi()), socketMessage);
            logger.info("用户发送...用户{}发送 - {}在线 消息: {}", currentMessage.getUi(), onLineClientUserMap.get(currentMessage.getUi()).size(), message);
        }
    }

    @OnOpen
    public void onOpen (Session session) throws IOException {
        logger.info("新用户连接...");
    }

    @OnClose
    public void onClose (Session session) throws IOException {
        onLineUserMap.remove(session.getId());
        if (null != currentMessage) {
            onLineClientUserMap.get(currentMessage.getUi()).remove(session);
            logger.info("用户退出...{} - {}在线", onLineUserMap.size(), onLineClientUserMap.get(currentMessage.getUi()).size());
        }
        else {
            logger.info("用户退出...{} 在线", onLineUserMap.size());
        }
    }

    /**
     * 发送 h5 用户
     * @param session 用户 session
     * @param socketMessage 消息bean
     */
    public static void send(Session session, SocketMessage socketMessage) {
        try {
            // Send the first message to the client
            session.getBasicRemote().sendText(JSONObject.toJSONString(socketMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送给所有 h5 用户
     * @param sessions 用户 session
     * @param msg 发送消息
     * @param ua 用户头像
     */
    public static void sendAll (List<Session> sessions, String msg, String ua, int msgtype) {
        if (!CollectionUtils.isEmpty(sessions)) {
            SocketMessage socketMessage = new SocketMessage();
            socketMessage.setMsg(msg);
            socketMessage.setMsgType(msgtype);
            socketMessage.setUa(ua);
            socketMessage.setOnl(sessions.size());
            for (Session session : sessions) {
                send(session, socketMessage);
            }

        }
    }


    /**
     * 发送给所有 h5 用户
     * @param sessions 用户 session
     * @param socketMessage 发送消息
     */
    public static void sendAll (List<Session> sessions, SocketMessage socketMessage) {
        if (!CollectionUtils.isEmpty(sessions)) {
            logger.debug("send H5 All...{}", JSONObject.toJSONString(socketMessage));
            socketMessage.setOnl(sessions.size());
            for (Session session : sessions) {
                send(session, socketMessage);
            }
        }
    }

    /**
     * 向 app 发送消息
     * @param socketMessage 消息
     */
    public void sendApp(SocketMessage socketMessage) {
        try {
            // app 端不需要用户名称
            SocketClientThread socketClientThread = SocketClientServer.clients.get(currentMessage.getUi());
            SocketClientServer.send(socketClientThread.writer, JSONObject.toJSONString(socketMessage));
            logger.debug("send app msg:{} --- {} {}", JSONObject.toJSONString(socketMessage), socketClientThread.initMessage.getUi(), socketClientThread.initMessage.getUa());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void close (List<Session> sessions) {
        // 关闭 h5 session
        if (!CollectionUtils.isEmpty(sessions)) {
            for (Session session : sessions) {
                try {
                    session.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            sessions = Collections.emptyList();
        }
    }
}
