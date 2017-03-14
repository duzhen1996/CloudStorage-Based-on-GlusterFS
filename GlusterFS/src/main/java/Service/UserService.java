package Service;

import Models.User;

import java.util.List;

/**
 * Created by DZ on 2016/1/21.
 */
public interface UserService
{
    public boolean addUser( String username , String password );

    public List<User> getAllUser();

    public void delete( String username );
}
