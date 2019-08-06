package live;

/**
 * Created by Administrator on 2015/7/2 0002.
 */
public class ThreadTest extends Thread {


    public static int count = 0;
    private static Object obj = new Object();
    public static void main (String[] args) {
        for (int i = 0;i < 10; i++) {
//            new ThreadTest().start();
            new Thread(new ThreadTest()).start();

        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (ThreadTest.class) {
            count++;
            System.out.println(count);
        }
    }
}
