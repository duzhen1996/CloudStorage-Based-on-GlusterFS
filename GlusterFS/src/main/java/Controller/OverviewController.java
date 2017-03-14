package Controller;

import DAO.AccessDAO;
import Models.AccessInfo;
import Models.Node;
import Service.AccessService;
import Service.MountInfoService;
import Service.OverViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DZ on 2015/12/20.
 */
@Controller
@RequestMapping( value = "/Overview")
public class OverviewController
{
    @Autowired
    OverViewService overViewService;

    @Autowired
    AccessService accessService;

    @Autowired
    MountInfoService mountInfoService;

    @RequestMapping(value = "/Jump.do")
    public String jumpToOverView( Model model )
    {
        System.out.println("从登陆控制器重定向成功");
        //获取到所有的node信息，现在就是要输出
        List<Node> nodes = overViewService.getAllNodeInformation();

        //先计算的是所有的加起来的已使用和未使用变成Data1给饼图
        long haveUsedAll = 0;//这里存的是所有的已使用量
        long allMemory = 0;//这里是所有节点的总容量

        for ( Node node : nodes )
        {
            haveUsedAll = haveUsedAll + node.getNode_usage();
            allMemory = allMemory + node.getNode_capacity();
        }

        //返回第一个所需要的Json
        String data1 =  "[{label:\"已使用\",data:" + haveUsedAll + "},{label:\"未使用\",data:" + (allMemory - haveUsedAll) + "},]";
        model.addAttribute("data1" , data1);

        //返回第二张图需要的json
        String data2 = "[";
        for ( Node node : nodes )
        {
            data2 = data2 + "{label:\"" + node.getNode_name() + "\",data:" + node.getNode_usage() + "},";
        }
        data2 = data2 + "]";
        model.addAttribute("data2" , data2 );

        model.addAttribute( "haveUsedAll" , haveUsedAll);
        model.addAttribute( "allMemory" , allMemory);

        //这里获取近一个月之间的访问频率
        List<AccessInfo> result = accessService.getAccess();
        String data3 = "[";
        for ( int i = 0 ; i < result.size() ; i++ )
        {
            data3 = data3 + "[" + (i+1) + "," + result.get(i).getAccess_number() + "],";
        }
        data3 = data3 + "]";
        model.addAttribute("data3" , data3);

        //在这里获取每一个节点现在挂载的人数，方法如下：首先访问节点用户信息表，要把里面所有的没有卸载的用户信息全部取出来，是一个节点List，进行分类，
        //分类的依据就是，之前取出来的nodes，通过nodes里面每一个节点的节点名把东西取出来放进一个map里面，键是节点名，值是int。为了增加访问Sql的速度
        //只关注最近一天的访问人数
        Map<String,Integer> countResult = new HashMap<String,Integer>();
        //然后为Map添加数据
        for ( Node node : nodes )
        {
            countResult.put(node.getNode_name() , mountInfoService.getInfoCount(node.getNode_name()) );
        }
        System.out.println(countResult);
        //将Map的值传到前台
        model.addAttribute("countResult" , countResult);


        //接下来是传每个节点的信息，干脆就把整个List传给前台，前台各取所需
        model.addAttribute("nodes", nodes);
        System.out.println("传参成功 " + haveUsedAll + " " + (allMemory - haveUsedAll));
        return "overview";
    }
}
