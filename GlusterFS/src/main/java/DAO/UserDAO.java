package DAO;

import Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DZ on 2015/12/22.
 */
//管理员的和用户的都会写在这个里面
@Repository
public class UserDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public boolean ifnotClientByUserName( String userName )//这个是用户DAO
    {
        System.out.println("开始查找用户");

        String sql = "SELECT general_user From gluster_user WHERE general_user = ? ";

        RowMapper<String> rowMapper = new BeanPropertyRowMapper<String>(String.class);

        try {
            jdbcTemplate.queryForObject(sql , rowMapper , userName );
            System.out.println("找到了！");
            return true;
        }
        catch (Exception e)
        {
            System.out.println("没找到");
            return false;
        }
    }

    public User findUserByUserName( String userName )//这个是管理员DAO
    {
        System.out.println("进入持久层" + userName);
        String sql = "SELECT admin_name userName , admin_passwd passWord FROM gluster_admin WHERE admin_name = ?";
        //因为用户名是主键，所以只返回一个对象
        //创建一个行映射器来构造对象
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        try {
            //System.out.println(jdbcTemplate.queryForObject(sql,rowMapper,userName));
            return jdbcTemplate.queryForObject(sql,rowMapper,userName);
        }
        catch (Exception e)
        {
            System.out.println("没找到");
            return null;
        }
    }

    public List<User> getAllUser()
    {
        System.out.println("获取所有用户的用户名和密码");
        String sql = "SELECT general_user userName , general_passwd passWord FROM gluster_user";

        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

        return jdbcTemplate.query(sql , rowMapper);
    }

    public void deleteByUsername( String username )
    {
        System.out.println(username);
        String sql = "DELETE FROM gluster_user WHERE general_user = ?";

        jdbcTemplate.update(sql , username);
    }

    public void addUser( String username , String password )
    {
        String sql = "INSERT INTO gluster_user( general_user , general_passwd ) VALUES( ? , ?  )";
        jdbcTemplate.update(sql , username , password);
    }

    //查看在gitinfo中有没有这个用户
    public Integer getGidCountByUser( String username )
    {
        String sql = "SELECT COUNT(*) FROM gluster_client_gidinfo WHERE general_user = ?";
        return jdbcTemplate.queryForObject(sql , Integer.class , username);
    }

    //在gitinfo中删除一个用户
    public void deleteGidByUser( String username )
    {
        String sql = "DELETE FROM gluster_client_gidinfo WHERE general_user = ?";

        try {
            jdbcTemplate.update(sql , username );
            System.out.println("成功从数据库中铲除gid");
        }catch (Exception e)
        {
            System.out.println("在数据库中没有这个用户的gid");
        }

    }
//    public Integer findDiskIdByDirName( String dir_dirname )
//    {
//        System.out.println("开始查询盘符id");
//        String sql = "SELECT disk_id FROM gluster_diskinfo WHERE disk_dirname = ?";
//
//        RowMapper<Integer> rowMapper = new BeanPropertyRowMapper<Integer>(Integer.class);
//
//        try {
//            return jdbcTemplate.queryForObject(sql , rowMapper , dir_dirname);
//        }catch (Exception e )
//        {
//            System.out.println("没有找到");
//            return 0;
//        }
//    }



}
