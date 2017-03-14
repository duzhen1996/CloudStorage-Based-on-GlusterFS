package Service;

import Models.User;
import org.springframework.stereotype.Service;

/**
 * Created by DZ on 2015/12/20.
 */
public interface LoginService
{
    public int IfNotLogin( User user );//返回值1就是用户名和密码都有匹配，返回值0就是用户名有但是密码不正确，返回值-1就是用户名都查无此人
}
