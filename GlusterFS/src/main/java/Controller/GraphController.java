package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by DZ on 2016/6/25.
 * 这个主要用来解决每个节点图像的问题
 */
@Controller
@RequestMapping("/nodeload")
public class GraphController {

    @RequestMapping("/getnodeload/{nodeip}.do")
    @ResponseBody
    public String getnodeload( @PathVariable("nodeip") String nodeip ) throws IOException {
        System.out.println("客户端请求 " + nodeip + " 的cpu负载和带宽信息");
        //首先创建一个socket，用来和本机的守护进程连接
        Socket socket = new Socket(InetAddress.getLocalHost() , 8081);
        PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()) ) ,true);
        BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
        out.println("0||" + nodeip);

        String jsonMessage = in.readLine();
        System.out.println("信息已经收到" + jsonMessage);
        return jsonMessage;
    }
}
