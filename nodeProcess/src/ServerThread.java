import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DZ on 2016/6/26.
 */
public class ServerThread extends Thread{
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    NodeServer nodeServer;

    public ServerThread(Socket socket , NodeServer nodeServer) throws IOException {
        this.socket = socket;
        this.nodeServer = nodeServer;
        in = new BufferedReader( new InputStreamReader( this.socket.getInputStream() ));
        out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(this.socket.getOutputStream()) ) );
        System.out.println("新线程初始化完毕");
    }

    public void run(){
        try {
            Boolean judge = true;
            while (judge){
                String str = in.readLine();
                //System.out.println(str);
                if (str == null || str.equals("")){
                    //System.out.println("空字符串");
                } else if(str.equals("000000")){
                    System.out.println("线程退出");
                    judge = false;
                } else {
                    //System.out.println("不是空字符串 , " + str);

                    List<String> arrayList = Arrays.asList(str.split("\\|\\|"));
                    //System.out.println(arrayList);
                    //System.out.println(arrayList);
                    //协议的内容是这样的，如果发送的字符串第一位是1，那么就是从节点来的长连接，
                    // 如果第一位是0，那么就是从web服务器来的短链接
                    //如果说是短链接，那么第二位就是一个IP地址，这个IP地址通过查询一个全局的MAP来获得这个IP对应节点的负载信息
                    //长连接的第二位是CPU负载与带宽信息的json数组，ip地址通过其他方式获得
                    //如果是长连接，那么这个循环就会继续(也就是不改变judge的值)，如果是短链接，那么就是要把judge的值改成false
                    //如果第一位是3，那么依旧会是短链接，第二位是节点ip,第3位是目录名，第4位是配置的容量
                    if (arrayList.get(0).equals("1")){
                        //如果说是长连接就使用map来存储各各节点Json数据，只有在短连接的时候再返回一个json，这个json表达了CPU和带宽负载，长连接
                        nodeServer.nodeMap.put(socket.getInetAddress().toString().substring(1) , arrayList.get(1));
                        //将输出流放到map中
                        nodeServer.nodeTask.put(socket.getInetAddress().toString().substring(1) , out);
                        //System.out.println("already update");
                    }else if (arrayList.get(0).equals("0")){
                        //返回Json数组，短链接
                        out.println(nodeServer.nodeMap.get(arrayList.get(1)));
                        out.flush();
                        judge = false;
                    }else {
                        //使用输出流发送一个cpu负载和带宽
                        PrintWriter printWriter = nodeServer.nodeTask.get(arrayList.get(1));
                        System.out.print(arrayList.get(2) + "||" + arrayList.get(3));
                        printWriter.print(arrayList.get(2) + "||" + arrayList.get(3));
                        printWriter.flush();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("出现未知错误");
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
