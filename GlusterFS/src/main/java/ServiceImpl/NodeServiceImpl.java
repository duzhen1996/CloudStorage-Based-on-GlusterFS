package ServiceImpl;


import DAO.NodeDAO;
import Service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Created by DZ on 2016/1/22.
 */
@Service( value = "nodeService")
public class NodeServiceImpl implements NodeService
{
    @Autowired
    NodeDAO nodeDAO;

    public boolean ifHaveNodename(String nodename)
    {
        //如果没有找到nodename返回
        if ( nodeDAO.findNodename(nodename) == null )
        {
            System.out.println("没有找到节点名");
            return false;
        }
        else
        {
            return true;
        }
    }

    public void addNode(String aclname, Long aclip, String aclcaps)
    {
        nodeDAO.addNode( aclname , aclip , aclcaps );
    }

    public void delete(String nodename)
    {
        //如果要·删除某个节点，首先要从节点运行状态和节点用户信息这两张表中删除
        nodeDAO.deleteClientGidByNodename(nodename);
        //这里删除节点状态信息表中的节点状态
        nodeDAO.deleteNodeStatusByNodename(nodename);

        nodeDAO.deleteByNodename(nodename);
    }

    public String getIpStr(String nodename) {
        //首先先查表获取ip地址
        Long ip = nodeDAO.findIpByNodeName(nodename);
        //然后进行ip地址的转换
        Long part1 = ip%256;
        ip = (ip - part1)/256;//右移
        Long part2 = ip%256;
        ip = (ip - part2)/256;//右移
        Long part3 = ip%256;
        ip = (ip - part3)/256;//右移
        Long part4 = ip % 256;

        String ipStr = "" + part4 + "." + part3 + "." + part2 + "." + part1;
        System.out.println("通过节点名查找到了ip数据" + ipStr);
        return ipStr;
    }

//    public Socket getSocket(String nodename) throws IOException {
//        //首先先查表获取ip地址
//        Long ip = nodeDAO.findIpByNodeName(nodename);
//        //然后进行ip地址的转换
//        Long part1 = ip%256;
//        ip = (ip - part1)/256;//右移
//        Long part2 = ip%256;
//        ip = (ip - part2)/256;//右移
//        Long part3 = ip%256;
//        ip = (ip - part3)/256;//右移
//        Long part4 = ip % 256;
//
//        String ipStr = "" + part4 + "." + part3 + "." + part2 + "." + part1;
//        System.out.println(ipStr);
//
//        Socket socket = new Socket(ipStr , 9999);
//        return socket;
//    }



    public boolean ifHaveSameIp(Long ip) {
        if (nodeDAO.findNodenameByIp(ip).toString() == "[]")
        {
            return false;//如果没有找到，说明也就没有对应的ip
        }
        else
        {
            return true;
        }
    }
}
