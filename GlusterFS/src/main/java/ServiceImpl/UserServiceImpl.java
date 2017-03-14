package ServiceImpl;

import DAO.ACLInformationDAO;
import DAO.UserDAO;
import Models.ACLInformation;
import Models.User;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DZ on 2016/1/21.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    UserDAO userDAO;

    @Autowired
    ACLInformationDAO aclInformationDAO;

    public boolean addUser(String username, String password)
    {
        try {
            System.out.println("添加 " + username + " " + password);
            userDAO.addUser(username , password);
            return true;//如果成功添加了用户，返回true
        }
        catch (Exception e)//如果出现主键冲突那么就会跳入下面这个语句块
        {
            System.out.println("用户名已经存在");
            return false;
        }
    }

    public List<User> getAllUser() {
        System.out.println(userDAO.getAllUser());
        return userDAO.getAllUser();
    }

    public void delete(String username) {
        //在这里删除一个User和他对应的acl信息，首先删除acl对应user的信息，如果没有这个user的信息再说
        aclInformationDAO.deleteAclByUsername(username);

        //首先查看在gidinfo中有没有这个人
        if(userDAO.getGidCountByUser(username) == 0 )
        {
            //如果在gid中没有这个人，那就直接删除这个用户
            //这里在用户表中删除一个信息
            userDAO.deleteByUsername(username);
        }
        else
        {
            //如果有gid，那就先删除gid信息
            userDAO.deleteGidByUser(username);
            userDAO.deleteByUsername(username);
        }
    }
}
