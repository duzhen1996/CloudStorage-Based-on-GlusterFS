package Controller;

import Models.Node;
import Service.NodeService;
import Service.OverViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by DZ on 2016/1/21.
 */
@Controller
@RequestMapping(value = "/nodeAdd")
public class NodeAddController
{
    @Autowired
    OverViewService overViewService;

    @Autowired
    NodeService nodeService;

    

    @RequestMapping( value = "/jump.do")
    public String ShowNodes( Model model )
    {
        //从服务层中获取所有的节点信息，保证左边状态栏节点名正确
        List<Node> nodeList = overViewService.getAllNodeInformation();
        System.out.println("取出节点" + nodeList);

        //将所有的节点信息返回页面
        model.addAttribute("nodes" , nodeList);

        return "nodeAdd";
    }

    @RequestMapping( value = "/addNode.do")
    @ResponseBody
    public Integer addNodes( String nodename , String nodeip , Long nodecap )
    {
        System.out.println("已经收到表单，容量：" + nodecap + " 节点名" + nodename);
        //先看表单是否完整，如果不完整返回错误代码2
        if ( nodename == "" || nodename == null || nodeip == "" || nodeip == null )
        {
            return 2;
        }

        //然后查看节点名有没有重复
        if ( nodeService.ifHaveNodename( nodename ) )
        {
            return 3;
        }

        //现在检查ip是不是符合格式，如果不符合，错误代码4
        if (Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(nodeip).matches() == false)
        {
            System.out.println("node的ip不符合格式");
            return 4;
        }


        //因为每个节点的ip是不一样的，所以这里要判断ip是不是重复了
        if (nodeService.ifHaveSameIp(ConfigController.ipTranslate(nodeip)))
        {
            System.out.println("发现了重复ip");
            return 5;
        }

        //现在对ip进行转化
        Long ipInt = ConfigController.ipTranslate(nodeip);

        //现在插入一个节点信息
        nodecap = nodecap * 1024 * 1024 * 1024;

        nodeService.addNode( nodename , ipInt , nodecap.toString() );//将第三个参数强制类型转化
        return 1;
    }

    @RequestMapping( value = "/delete/{nodename}.do")
    public String deleteNode( @PathVariable String nodename )
    {
        System.out.println(nodename);

        //这里进行删除操作
        nodeService.delete(nodename);

        return "redirect:/nodeAdd/jump.do";
    }
}
