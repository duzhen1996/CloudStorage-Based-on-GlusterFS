package Controller;

import DAO.NodeDAO;
import Models.Node;
import Service.MountInfoService;
import Service.NodeService;
import Service.OverViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Created by DZ on 2015/12/24.
 */
@Controller
//@Scope("prototype")
@RequestMapping( value = "/nodeController")

public class NodeController
{
    @Autowired
    OverViewService overViewService;

    @Autowired
    MountInfoService mountInfoService;

    @Autowired
    NodeService nodeService;

    @RequestMapping( value = "/getip/{nodeName}.do")
    @ResponseBody
    public String getIp( @PathVariable String nodeName )
    {
        System.out.println( nodeName + " 获取Ip地址" );

        String ip = nodeService.getIpStr(nodeName);

        System.out.println(ip);

        return ip;
    }

    @RequestMapping(value = "/{nodeName}.do")
    public String getNodeInformation(@PathVariable String nodeName , Model model , HttpServletRequest request)
    {
        System.out.println("node控制器收到请求" + nodeName);

        //获取到所有的node信息
        List<Node> nodes = overViewService.getAllNodeInformation();

        model.addAttribute("nodes" , nodes);

        model.addAttribute("currentNodeIp" , nodeService.getIpStr(nodeName));

        Node node = new Node();

        for ( Node i : nodes )
        {
            if ( i.getNode_name().equals(nodeName) )
            {
                node = i;
                break;
            }
        }

        String data =  "[{label:\"已使用\",data:" + node.getNode_usage() + "},{label:\"未使用\",data:" + (node.getNode_capacity() - node.getNode_usage()) + "},]";
        model.addAttribute("data" , data);

        model.addAttribute("cnode" , node);

        //上面处理的是所有的节点信息，下面处理节点某天的访问量信息
        List<List> mountCount = mountInfoService.getDayMount(nodeName);//前面的那个List是时间，后面的List是访问次数

        model.addAttribute("mountCount" , mountCount);

        //这里给node页面的条形图传访问量的信息，直接传Json格式的String
        List<Long> secondList = mountCount.get(2);
        String secondData = "[";

        for ( int i = 0 ; i < secondList.size() ; i++ )
        {
            secondData = secondData + "[" + secondList.get(i)  + "," + mountCount.get(1).get(i) + "],";
        }

        secondData = secondData + "]";

        model.addAttribute("secondData" , secondData);

        //这里处理最下面那个表的问题，返回用户名、ip、已用空间可用空间
        List<Map<String,Object>> gidInfos =  mountInfoService.getAllMount(nodeName);

        model.addAttribute("gidInfos" , gidInfos);

        return "node";
    }



    //关闭浏览器触发的控制器
    @RequestMapping(value = "/close.do")
    public String deleteSocket( HttpServletRequest request )
    {
//        String sessionId = request.getRequestedSessionId();
//
//        //这里关闭所有的Socket和流
//
//        try {
//            bufferedReaderMap.get(sessionId).close();
//            printWriterMap.get(sessionId).close();
//            socketMap.get(sessionId).close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("连接关闭失败");
//        }
//
//        //这里清空Map中所有对象
//        socketMap.remove(sessionId);
//        bufferedReaderMap.remove(sessionId);
//        printWriterMap.remove(sessionId);
//        cpuMessageMap.remove(sessionId);
//        bandwideMessageMap.remove(sessionId);String sessionId = request.getRequestedSessionId();
//
//        //这里关闭所有的Socket和流
//
//        try {
//            bufferedReaderMap.get(sessionId).close();
//            printWriterMap.get(sessionId).close();
//            socketMap.get(sessionId).close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("连接关闭失败");
//        }
//
//        //这里清空Map中所有对象
//        socketMap.remove(sessionId);
//        bufferedReaderMap.remove(sessionId);
//        printWriterMap.remove(sessionId);
//        cpuMessageMap.remove(sessionId);
//        bandwideMessageMap.remove(sessionId);
//        currentNodeMap.remove(sessionId);
//
//        System.out.println(sessionId + "已经退出，正在清除信息");
//        currentNodeMap.remove(sessionId);
//
//        System.out.println(sessionId + "已经退出，正在清除信息");
        return "";
    }
}
