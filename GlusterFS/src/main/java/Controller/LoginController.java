package Controller;

import Models.User;
import Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DZ on 2015/12/20.
 */
@Controller
@RequestMapping(value = "/Login")
public class LoginController
{
    @Autowired
    private LoginService loginService;

    @RequestMapping( value = "/IfNotLogin.do")
    public String login(  User user , Model model)
    {
        System.out.println("进入登录控制器 " + user );

        int judge = 1;

        if( user.getPassWord() == "" && user.getUserName() == "")
        {
            judge = -1;
        }
        else
        {
            judge =  loginService.IfNotLogin( user );
            System.out.println("控制层收到服务层反馈  " + judge);
        }

        if (judge == 1)
        {
            return "redirect:/Overview/Jump.do";
        }
        else
        {
            model.addAttribute( "judge" , judge);
            return "login";
        }
    }

    @RequestMapping( value = "/Jump.do" )//首页的跳转控制器，防止给js的值为空导致的js无法运行
    public String jump( Model model)
    {
        System.out.println("进入跳转控制器");
        model.addAttribute("judge" , 10);
        return "login";
    }
}
