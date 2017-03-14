package Controller;

import Models.Node;
import Models.User;
import Service.OverViewService;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by DZ on 2016/1/21.
 */
@Controller
@RequestMapping( value = "/User")
public class UserController
{
    @Autowired
    OverViewService overViewService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/add.do")
    @ResponseBody
    public int add( String username , String password )//使用ajax尝试添加一个用户
    {
        System.out.println(username + " " + password);
        boolean judge = userService.addUser(username , password);

        if ( username == "" || username == null || password == "" || password == null )
        {
            return 2;//返回2说明表单没有填完整
        }

        if (judge)
        {
            //返回值为1的时候代表成功添加了一个用户，这个时候页面要刷新
            return 1;
        }
        else
        {
            return 0;//返回值为0代表用户名已经存在
        }
    }

    @RequestMapping( value = "/jump.do")//用以呈现页面的基本信息，包括：node的名字和个数
    public String show( Model model )
    {
        //从服务层中获取所有的节点信息，保证左边状态栏节点名正确
        List<Node> nodeList = overViewService.getAllNodeInformation();

        //将所有的节点信息返回页面
        model.addAttribute("nodes" , nodeList);

        //获取用户信息列表
        List<User> userList = userService.getAllUser();

        model.addAttribute("userlist" , userList);

        return "user";
    }

    @RequestMapping(value = "/delete/{username}.do")
    public String delete( @PathVariable String username )
    {
        //根据从页面获取来的信息来删除一个用户
        userService.delete(username);

        return "redirect:/User/jump.do";
    }
}
