package Controller;

import DAO.UserDAO;
import Models.ACLInformation;
import Models.Node;
import Service.ACLService;
import Service.OverViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by DZ on 2015/12/25.
 */
@Controller
@RequestMapping(value = "/configController")
public class ConfigController
{
    @Autowired
    private ACLService aclService;

    @Autowired
    private OverViewService overViewService;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping( value = "/ShowAclInformation.do")
    public String showPage(Model model)
    {
        //从服务层中获取所有的权限信息
        List aclInformationList = aclService.getAllAcl();

        //从服务层中获取所有的节点信息，保证左边状态栏节点名正确
        List<Node> nodeList = overViewService.getAllNodeInformation();

        //将所有的权限信息返回页面
        model.addAttribute("aclList" , aclInformationList);

        //将所有的节点信息返回页面
        model.addAttribute("nodes" , nodeList);

        return "config";
    }

    @RequestMapping( value = "/addAcl.do")
    public String addToDataBase(ACLInformation aclInformation , Model model)
    {
        System.out.println("准备提交一条记录：" + aclInformation);
        //这条记录明显还不能进入数据库因为disk_id还没有，虽然有盘符名称，但是没有盘符的id是不行的。所以在服务层要先把盘符id找出来。并且把盘符id给ACLInformation的对象
        //这样才可以完成一个Acl信息的写入，至于怎么防止重复信息的问题，这个放在服务层去解决

        return "redirect:/configController/ShowAclInformation.do";
    }

    //在用户添加配置信息的过程中，在写完目录之后要把目录对应的可用空间获取出来，并反映在“分配空间”这个input里面
    @RequestMapping( value = "/ifNotHave.do")
    @ResponseBody
    public Integer ifNotHave( String dirname )
    {
        aclService.getDiskCap(dirname);
        return aclService.getDiskCap(dirname);
    }

    //这个是模态框表单提交的时候使用的控制器
    @RequestMapping( value = "/change.do")
    @ResponseBody
    //这个控制器用来查看是否有重复的记录，如果没有重复记录就查用户名是不是已经是注册的（如果有时间，这个可以做额外效果），如果通过路径名找到盘符id，没有路径名那就在diskinfo中新建一条针对这个盘符的记录然后完成上一步,如果直接就找到了路径名那就,直接在acl信息表中根据aclid对相应数据进行修改
    //要注意的是传进来的空间大小是一个字符串，带一个单位（G）在末尾，所以在使用的时候要把G去掉，并且强制转化为
    public int changeAcl( Integer acl_id , String disk_usage , String disk_capacity , boolean acl_allowmount , String general_user , String disk_dirname , String acl_ip_start , String acl_ip_end ) throws IOException {
        System.out.println("进入修改acl控制器");
        if (general_user.equals("") || general_user == null || disk_dirname.equals("") ||
                disk_dirname == null || acl_ip_start == null || acl_ip_start.equals("")
                || disk_capacity.equals("") || disk_capacity == null || acl_ip_end.equals("") || acl_ip_end == null )
        {
            return 3;//返回3意味着必要信息不完整
        }

        //这里判断ip地址格式，如果格式正确才可以继续执行到int的转换，如果格式不正确，错误代号5
        if (Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(acl_ip_start).matches() == false ||
                Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(acl_ip_end).matches() == false )
        {
            System.out.println("ip地址格式不对");
            return 5;
        }

        Long ipStartInt = ipTranslate( acl_ip_start );
        Long ipEndInt = ipTranslate( acl_ip_end );
        System.out.println("转换完成");
        //首先查看是否有重复记录，主要关注于用户名，ip首末和盘符名，以及分配空间是不是全是一样的，如果满足这个条件，那么就可以认为是一样的记录，在此基础上，如果发现dirname
        // 对应分配的空间也一样，就返回0
        if ( aclService.ifHaveSame(general_user , ipStartInt , ipEndInt , disk_dirname) && aclService.findDiskCap( disk_dirname ).compareTo(disk_capacity)== 0)
        {
            System.out.println("已经查到相似信息");
            return 0;
        }

        //这里判断这个人是否注册过，如果没有注册过，那就如实返回给页面，让页面报错
        if ( general_user != null && general_user != ""  && !aclService.ifHaveUser( general_user ) )//如果本来就没有填用户名那就跳过这一步
        {
            System.out.println("这个用户没有被注册");
            return 1;
        }

        //这里判断这个盘符是不是存在，如果不存在，那么就返回错误代码4
        if ( !aclService.ifHaveDirname( disk_dirname ) )
        {
            System.out.println("这个盘符不存在，准备新建一个盘符");
            aclService.addNewDir(disk_dirname);
        }

        //如果这个盘符是存在的，但是已经被其他的人占用，那么这个盘符也不能使用，错误代码6
        //Service中如下实现：首先使用这个dirname得到一个diskid，然后如果可以通过这个diskid去找aclid，如果这个aclid不存在（DAO层设为null），
        // 或者acl不等于当前的acl，那么就说明这个这个盘符被占用。
        if ( aclService.ifDirUsedChange( disk_dirname , acl_id ) )
        {
            return 6;
        }

        if( ipStartInt > ipEndInt )
        {
            return 7;
        }

        //在添加一个记录之前从数据库中找出所有的节点IP，并且随机选一个IP
        List<Node> nodeList = overViewService.getAllNodeInformation();
        Collections.shuffle(nodeList);
        Long ip = nodeList.get(0).getNode_ip();
        //这个地方将IP变为字符串
        Long part1 = (ip % 256);
        ip = (ip - part1) / 256;
        Long part2 = (ip % 256);
        ip = (ip - part2) / 256;
        Long part3 = (ip % 256);
        ip = (ip - part3) / 256;
        Long part4 = (ip % 256);

        String ipString = "" + part4 + "." + part3 + "." + part2 + "." + part1;
        System.out.println("取到随机的IP" + ipString );
        Socket socket = new Socket(InetAddress.getLocalHost() , 8081);
        PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()) ) ,true);
        System.out.println("正在发送：" + "3||" + ipString + "||" + disk_dirname + "||" + disk_capacity);
        out.println("3||" + ipString + "||" + disk_dirname + "||" + disk_capacity);

        //这里开始正式修改一个数据
        System.out.println("开始正常修改一条记录");
        aclService.changeAclInformation(acl_id, disk_usage, disk_capacity , acl_allowmount , general_user , disk_dirname , ipStartInt , ipEndInt);
        return 2;
    }

    @RequestMapping( value = "/addacl.do")
    @ResponseBody
    public int addacl( String disk_dirname , String acl_ip_start , String acl_ip_end , String general_user , Long disk_capacity , boolean acl_allowmount ) throws IOException {
        //在添加acl权限配置的时候，所有信息都必须填写，如果为确切ip地址，上界和下界一样
        if (general_user.equals("") || general_user == null || disk_dirname.equals("") ||
                disk_dirname == null || acl_ip_start == null || acl_ip_start.equals("")
                || disk_capacity == 0 || disk_capacity == null || disk_capacity == null  )
        {
            return 3;//返回3意味着必要信息不完整
        }

        if ( acl_ip_end == "" || acl_ip_end == null )
        {
            acl_ip_end = acl_ip_start;
        }

        //这里判断ip地址格式，如果格式正确才可以继续执行到int的转换，如果格式不正确，错误代号5
        if (Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(acl_ip_start).matches() == false ||
                Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(acl_ip_end).matches() == false )
        {
            System.out.println("ip地址格式不对");
            return 5;
        }

        Long ipStartInt = ipTranslate( acl_ip_start );
        Long ipEndInt = ipTranslate( acl_ip_end );

        System.out.println( general_user  + " " + disk_dirname + " " + acl_ip_start + " " + acl_ip_end + " " + general_user + " " + disk_capacity + " " + acl_allowmount);



        if ( aclService.ifHaveSame(general_user, ipStartInt, ipEndInt, disk_dirname) )
        {
            System.out.println("已经查到相似信息");
            return 0;
        }

        //这里判断这个人是否注册过，如果没有注册过，并且，那就如实返回给页面，让页面报错
        if ( general_user != null && general_user != "" && !aclService.ifHaveUser( general_user ) )
        {
            System.out.println("这个用户没有被注册");
            return 1;
        }

        //这里判断这个盘符是不是存在，如果不存在，那么就返回错误代码4
        if ( !aclService.ifHaveDirname( disk_dirname ) )
        {
            System.out.println("这个盘符不存在，准备新建一个盘符");
            aclService.addNewDir(disk_dirname);
        }

        //如果盘符存在，但是没占用，首先通过dirname找到diskid，然后看diskid在不在acl表里面，如果在，说明这个盘符被占用了，返回错误代码6
        if ( aclService.ifDirUsedAdd( disk_dirname ) && userDAO.findUserByUserName(general_user) != null )
        {
            System.out.println("这个盘符已经被占用");
            return 6;
        }

        //看看是不是ip的上界大于下界，如果不是，那就报错误7
        if( ipStartInt > ipEndInt )
        {
            System.out.println("上下界大小关系不对");
            return 7;
        }

        //在添加一个记录之前从数据库中找出所有的节点IP，并且随机选一个IP
        List<Node> nodeList = overViewService.getAllNodeInformation();
        Collections.shuffle(nodeList);
        Long ip = nodeList.get(0).getNode_ip();
        //这个地方将IP变为字符串
        Long part1 = (ip % 256);
        ip = (ip - part1) / 256;
        Long part2 = (ip % 256);
        ip = (ip - part2) / 256;
        Long part3 = (ip % 256);
        ip = (ip - part3) / 256;
        Long part4 = (ip % 256);

        String ipString = "" + part4 + "." + part3 + "." + part2 + "." + part1;
        System.out.println("取到随机的IP" + ipString );
        Socket socket = new Socket(InetAddress.getLocalHost() , 8081);
        PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()) ) ,true);
        System.out.println("正在发送：" + "3||" + ipString + "||" + disk_dirname + "||" + disk_capacity);
        out.println("3||" + ipString + "||" + disk_dirname + "||" + disk_capacity);

        //这里是正常添加一条记录
        System.out.println("开始正常添加一条记录 disk_cap = " + disk_capacity);
        disk_capacity = disk_capacity * 1024 * 1024 * 1024;
        System.out.println("正在存放信息 ： " + disk_capacity.toString() + " " +acl_allowmount + " " + general_user + " " + disk_dirname + " " + ipStartInt + " " + ipEndInt);
        aclService.addAclInformation( disk_capacity.toString() ,acl_allowmount ,general_user ,disk_dirname , ipStartInt , ipEndInt );
        return 2;
    }

    @RequestMapping(value = "/delete/{acl_id}.do")
    public String deleteAclInformation( @PathVariable Integer acl_id )
    {
        System.out.println("获得了acl_id" + acl_id);

        //下面开始删除对应aclid的东西。
        aclService.deleteAclInformation(acl_id);

        return "redirect:/configController/ShowAclInformation.do";
    }

    //这里有一个方法进行字符串ip地址到int值的转化
    static public Long ipTranslate( String ip )
    {
        String[] strings = ip.split("\\.");

        Long result = Long.parseLong(strings[0])*256*256*256 + Long.parseLong(strings[1])*256*256 + Long.parseLong(strings[2])*256 + Long.parseLong(strings[3]);

        System.out.println("进行转化" + result);
        return result;
    }
}
