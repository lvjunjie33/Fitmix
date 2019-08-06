package com.business.live.socket;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Created by Administrator on 2015/5/13.
 */
public class SocketClientThread extends Thread {

    private static Logger logger = LoggerFactory.getLogger(SocketClientThread.class);
    public Socket socket;
    public SocketMessage initMessage;
    public PrintWriter writer;

    /**
     * socekt 读取超时时间
     */
    public static final Integer SOCKET_READ_TIME_OUT = 1000 * 5;

    public SocketClientThread (Socket socket) {
        try {
            this.socket = socket;
            this.writer = new PrintWriter(socket.getOutputStream());
            this.socket.setSoTimeout(SOCKET_READ_TIME_OUT);
            logger.info(this.writer.toString());
            this.initMessage = readerMessage();

            // 检查多点直播，如果socket存在链接，就先关闭socket链接
            if (SocketClientServer.clients.containsKey(initMessage.getUi())) {
                SocketClientServer.sendNotLive(writer);  // 发送 app 断开 socket 信息
                SocketClientServer.closeClient(this, false); // 关闭链接
            }
            testSend();
        } catch (Exception e) {
            logger.info("直播初始化失败...");
            SocketClientServer.closeClient(this, false); // 关闭链接
        }
    }

    public void testSend () {
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setMsg(SocketMessage.MSG_V);
        socketMessage.setFu("http://fs.igeekery.com/1005/female_your_current.mp3");
        socketMessage.setSna(SocketMessage.SNA_WU);
        SocketClientServer.sendMessage(writer, socketMessage);
    }


    @Override
    public void run() {
        while (true) {
            try {
                SocketMessage socketMessage = readerMessage();
                if (SocketMessage.MSG_C.equals(socketMessage.getMsg())) {
                    logger.info("{} 手动断开连接...", initMessage.getUi());
                    // 发送 app 断开 socket 信息
                    sendCloseMsg();
                    SocketClientServer.closeClient(this, false);
                }
                else {
                    socketMessage.setSna(SocketMessage.SNA_SU);
                    WebSocketServer.sendAll(WebSocketServer.onLineClientUserMap.get(initMessage.getUi()), socketMessage);//发送给h5
                }
            } catch (SocketException e) {
                logger.info("{} 自动断开连接...", initMessage.getUi());
                SocketClientServer.closeClient(this, true);
            } catch (SocketTimeoutException e) {
                logger.info("{} socket read time out...reconnection ", initMessage.getUi());
                SocketClientServer.closeClient(this, true);
            } catch (Exception e) {
                logger.info("{} 直播信息错误,强制断开连接...", initMessage.getUi());
                SocketClientServer.closeClient(this, false);
            }
        }
    }

    public SocketMessage readerMessage() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = reader.readLine();
        logger.debug("app send: {}", str);

        return JSONObject.parseObject(str, SocketMessage.class);
    }

    public void sendCloseMsg() {
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setMsg(SocketMessage.MSG_C);
        socketMessage.setSna(SocketMessage.SNA_S);
        WebSocketServer.sendAll(WebSocketServer.onLineClientUserMap.get(initMessage.getUi()), socketMessage);
    }
}
