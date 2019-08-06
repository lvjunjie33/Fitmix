package socket;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * Created by Administrator on 2015/7/2 0002.
 */
public class Client {

    public static void main(String args[]) throws Exception {
        //为了简单起见，所有的异常都直接往外抛
        String host = "127.0.0.1";  //要连接的服务端IP地址
        int port = 4700;   //要连接的服务端对应的监听端口
        //与服务端建立连接
        Socket client = new Socket(host, port);
        while (true) {
            //建立连接后就可以往服务端写数据了
            Writer writer = new OutputStreamWriter(client.getOutputStream());
            writer.write("Hello Server.");
            System.out.println("Hello Server.");
            writer.flush();//写完后要记得flush
//            writer.close();
//        client.close();
            Thread.sleep(2000);
        }

//

    }

}
