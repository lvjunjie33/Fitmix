package live;

import com.alibaba.fastjson.JSONObject;
import com.business.live.socket.SocketMessage;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Administrator on 2015/7/1 0001.
 */
public class ClientLive extends Thread {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private MessageThread messageThread;// 负责接收消息的线程

    private static int in = 0 ;

    public static void main (String[] args) throws InterruptedException {
        for (int i = 0 ;i < (1) + 2; i++) {
//            new ClientLive().start();
           new Thread(new ClientLive()).start();
//            Thread.sleep(10);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void run() {
//        ClientLive clientLive = new ClientLive();
        /*clientLive.*/
        connectServer(8118, "127.0.0.1");
    }

    /**
     * 连接服务器
     *
     * @param port
     * @param hostIp
     */
    public boolean connectServer(int port, String hostIp) {
        // 连接服务器
        try {

            synchronized (ClientLive.class) {
                socket = new Socket(hostIp, port);// 根据端口号和服务器ip建立连接
                writer = new PrintWriter(socket.getOutputStream());
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                sendMessage("{\"mi\":123,\"ui\":"+in+"}");
    //            in++;
                    System.out.println(in);
                    in++;
            }

            int i = 0;
            while (true) {
                SocketMessage socketMessage = new SocketMessage();
                socketMessage.setMsg("加油" + i);
                socketMessage.setUi(4);
                socketMessage.setMi(66);
                socketMessage.setBpm(i * 10);
                socketMessage.setSna(SocketMessage.SNA_SU);
//                socketMessage.setLat((double) (100 / i));
//                socketMessage.setLng((double) (100 * 3 / i ));
                i++;
                Thread.sleep(1000);
                sendMessage(JSONObject.toJSONString(socketMessage));
            }


            // 开启接收消息的线程
//            messageThread = new MessageThread(reader);
//            messageThread.start();
//            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMessage(String message) {
        writer.println(message);
        writer.flush();
    }


    // 不断接收消息的线程
    class MessageThread extends Thread {
        private BufferedReader reader;

        // 接收消息线程的构造方法
        public MessageThread(BufferedReader reader) {
            this.reader = reader;
        }

        // 被动的关闭连接
        public synchronized void closeCon() throws Exception {
            // 被动的关闭连接释放资源
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
        }

        public void run() {
            String message = "";
            while (true) {
                try {
                    message = reader.readLine();
                    System.out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
