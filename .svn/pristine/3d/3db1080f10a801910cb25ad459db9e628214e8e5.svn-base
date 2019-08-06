package com.business.live.socket;

import com.alibaba.fastjson.JSONObject;
import com.business.core.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.websocket.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/5/13.
 */
public class SocketClientServer extends Thread {

    private static Logger logger = LoggerFactory.getLogger(SocketClientServer.class);
    public static Map<Integer, SocketClientThread> clients = new LinkedHashMap(0);
    public static SocketClientServer socketClientServer;
    private static ServerSocket serverSocket;
    private int maxConn;

    /**
     * 初始化 socket 服务
     * @param pro 端口
     * @param maxConn 最大连接数
     */
    private SocketClientServer (int pro, int maxConn) {
        try {
        	logger.info("socket port："+pro);
            this.maxConn = maxConn;
            this.serverSocket = new ServerSocket(pro);
            this.start();
        } catch (BindException e) {
            logger.info("socket port Occupancy ... ...");
            logger.info("端口号已被占用，请换一个！");
        } catch (IOException e) {
            logger.info("serverSocket 初始化失败...");
        }
    }

    /**
     * 不停监听连接
     */
    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                if (clients.size() >= maxConn) { // 控制连接人数
                    sendMaxConn(socket);
                    closeSocket(socket);
                    continue;
                }
                SocketClientThread client = new SocketClientThread(socket);
                clients.put(client.initMessage.getUi(), client);
                client.start();
                logger.info("{}开启直播...{}在线", client.initMessage.getUi(), clients.size());
            } catch (Exception e) {
                logger.info("开启直播失败...{}在线", clients.size());
            }
        }
    }

    /**
     * 发送消息
     * @param writer 写io
     * @param msg 消息（json）
     */
    public static void send (PrintWriter writer, String msg) {
        writer.println(msg + "\r\n");
        writer.flush();
        logger.info("send app go : {}", msg);
    }

    /**
     * 发送消息
     * @param writer 写io
     * @param message socket 消息对象
     */
    public static void sendMessage (PrintWriter writer, SocketMessage message) {
        send(writer, JSONObject.toJSONString(message));
    }

    /**
     * 附送服务器人数达到上限
     */
    public static void sendMaxConn (Socket socket) {
        try {
            SocketMessage message = new SocketMessage();
            message.setSna(SocketMessage.SNA_S);
            message.setMsg("服务器在线人数已达上限，请稍后尝试连接！");
            sendMessage(new PrintWriter(socket.getOutputStream()), message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("服务器在线人数已达上限，请稍后尝试连接！{}在线...{}", clients.size(), DateUtil.format(new Date(), "yyyy-MM-dd HH:mm"));
    }


    /**
     * 发送拒绝直播（服务器拒绝直播）
     * @param writer 写io
     */
    public static void sendNotLive (PrintWriter writer) {
        SocketMessage message = new SocketMessage();
        message.setSna(SocketMessage.SNA_S);
        message.setMsg(SocketMessage.MSG_CN);
        send(writer, JSONObject.toJSONString(message));
    }

    /**
     * 关闭 socket
     */
    public static void closeSocket (Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            logger.info("socket 关闭失败...{}", e);
        }
    }

    @SuppressWarnings("deprecation")
    public synchronized static void closeServer () {
        try {
            for (Map.Entry<Integer, SocketClientThread> entry : clients.entrySet()) {
                closeClient(entry.getValue(), false);
            }
            serverSocket.close();
            socketClientServer.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final Integer CONNECTION_TIME = 1000 * 30;
    public static final Integer CONNECTION_NUMBER = 6;
    /**
     * 关闭客户端
     */
    @SuppressWarnings("deprecation")
    public static void closeClient (SocketClientThread client, boolean isConnection) {
        if (isConnection) {
            try {
                List<Session> sessionList = WebSocketServer.onLineClientUserMap.get(client.initMessage.getUi());
                clients.remove(client.initMessage.getUi());
                client.writer.close();
                client.socket.close();

                int call = 0;
                while (true) {
                    /// 网络波动，重连 （reconnection）
                    WebSocketServer.sendAll(sessionList, SocketMessage.MSG_RC, SocketMessage.SNA_S, SocketMessage.MSG_TYPE_CONTROL);
                    logger.info("{} 异常断开，尝试重连...{} 秒", client.initMessage.getUi(), CONNECTION_TIME / 1000);
                    try {
                        Thread.sleep(CONNECTION_TIME / CONNECTION_NUMBER);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (null != clients.get(client.initMessage.getUi())) { // 重连成功
                        logger.info("{} 异常断开，尝试重连... 成功", client.initMessage.getUi());
                        client.stop();
                        break;
                    }
                    if (call >= CONNECTION_TIME / 1000 / CONNECTION_NUMBER) { //重连次数 达到时，通知 websocket 用户，直播断开（网络问题）
                        WebSocketServer.sendAll(sessionList, SocketMessage.MSG_C, SocketMessage.SNA_S, SocketMessage.MSG_TYPE_CONTROL); // 发送断开消息
                        WebSocketServer.close(sessionList); // 关闭session
                        logger.info("{}断开成功...{}在线", client.initMessage.getUi(), clients.size());
                        client.stop();
                        break;
                    }
                    call ++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                clients.remove(client.initMessage.getUi());
                List<Session> sessionList = WebSocketServer.onLineClientUserMap.get(client.initMessage.getUi());
                WebSocketServer.sendAll(sessionList, SocketMessage.MSG_C, SocketMessage.SNA_S, SocketMessage.MSG_TYPE_CONTROL); // 发送断开消息
                WebSocketServer.close(sessionList); // 关闭session
                client.writer.close();
                client.socket.close();
                logger.info("{}断开成功...{}在线", client.initMessage.getUi(), clients.size());
                client.stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void startServer (Integer pro, Integer connection) {
        socketClientServer = new SocketClientServer(pro, connection);
    }


    public static void main (String[] args) throws BindException, InterruptedException {
        startServer(8118, (1000 * 10) + 1);
    }
}
