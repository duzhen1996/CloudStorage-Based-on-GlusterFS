import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashMap;

/**
 * Created by DZ on 2016/6/25.
 */

public class NodeServer {

    public static final int PORT = 8081;

    ServerSocket server;

    HashMap<String , String> nodeMap;

    HashMap<String , PrintWriter> nodeTask;//这个map告诉了针对于长连接的下一帧是不是要告诉节点去执行一个

    public NodeServer() {
        System.out.println("服务器已经启动，守护进程已经启动");
        nodeMap = new HashMap<>(20);
        nodeTask = new HashMap<>(20);
        try {
            server = new ServerSocket(PORT);
            while (true){
                ServerThread thread = new ServerThread(server.accept(),this);
                System.out.println("监听到了一个连接请求，并创建了线程");
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


