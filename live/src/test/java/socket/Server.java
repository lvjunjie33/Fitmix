package socket;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2015/7/2 0002.
 */
public class Server extends Thread {

    public static void main(String args[]) throws IOException {
        //为了简单起见，所有的异常信息都往外抛
        int port = 4700;
        //定义一个ServerSocket监听在端口8899上
        ServerSocket server = new ServerSocket(port);
        //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
        Socket socket = server.accept();
        while (true) {
            Reader reader = new InputStreamReader(socket.getInputStream());
            //跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。
            char chars[] = new char[1024 * 10];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((reader.read(chars)) != -1) {
                System.out.println("from client: " + new String(chars));
            }
//            System.out.println("from client: " + sb);
//            reader.close();
//            socket.close();
//            server.close();
        }

    }


    private int pro;
    private int maxConnect;

    private Server (int pro, int maxConnect) {
        this.pro = pro;
        this.maxConnect = maxConnect;
    }

    @Override
    public void run() {

    }

    public static synchronized void init (int pro, int maxConnect) {
        new Server(pro, maxConnect);
    }
}
