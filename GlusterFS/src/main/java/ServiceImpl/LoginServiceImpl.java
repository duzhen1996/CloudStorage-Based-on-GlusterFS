package ServiceImpl;

import DAO.UserDAO;
import Models.User;
import Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by DZ on 2015/12/22.
 */
@Service
public class LoginServiceImpl implements LoginService
{
    @Autowired
    UserDAO userDAO;

    public LoginServiceImpl() {
    }

    public int IfNotLogin( User user )
    {
        System.out.println("进入登录服务" + user);
        User userFromDB = userDAO.findUserByUserName(user.getUserName());
        System.out.println("服务层已经收到持久层反馈"+userFromDB);

        if( userFromDB == null )
        {
            return -1;
        }

        if( user.equals(userFromDB) )
        {
            return 1;
        }
        if( user.getUserName().equals(userFromDB.getUserName()) )
        {
            return 0;
        }

        return -1;
    }
}
