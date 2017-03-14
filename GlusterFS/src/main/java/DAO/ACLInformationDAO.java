package DAO;

import Models.ACLInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by DZ on 2015/12/24.
 */
@Repository
public class ACLInformationDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    public void InsertDiskbyName(String dirname) {
        System.out.println("准备插入一个新的盘符：" + dirname);
        String sql = "INSERT INTO gluster_diskinfo (disk_dirname , disk_capacity , disk_usage ) VALUES(? , ? , ?)";

        jdbcTemplate.update(sql, new Object[]{dirname, "0", "0"});
    }

    public String findDiskidByName(ACLInformation aclInformation)//要注意万一没有对应的盘符名称怎么办
    {
        String sql = "SELECT disk_id FROM gluster_diskinfo WHERE disk_dirname = ?";//从diskinfo这个表中找到diskid,

        return jdbcTemplate.queryForObject(sql, String.class, aclInformation.getDisk_dirname());//
    }

    public Integer findDiskCapbyName(String diskName) {
        System.out.println("已经到达持久层 " + diskName);

        String sql = "SELECT disk_capacity FROM gluster_diskinfo WHERE disk_dirname =? ";//同diskinfo中找出一个盘符之前定义好的容量

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, diskName);
        } catch (Exception e) {
            System.out.println("并没有找到这个盘符");
            return 0;
        }

    }

    public List findAll() {
        //注意这里要拿出两个表中所有的相关值，创建出一个ACL信息的表
        String sql = "SELECT gluster_aclcontrol.acl_id , gluster_aclcontrol.general_user , gluster_aclcontrol.disk_id , " +
                "gluster_aclcontrol.acl_ip_start , gluster_aclcontrol.acl_ip_end , gluster_aclcontrol.acl_allowmount ,   " +
                "  gluster_diskinfo.disk_dirname , gluster_diskinfo.disk_capacity , gluster_diskinfo.disk_usage FROM gluster_diskinfo , gluster_aclcontrol " +
                " WHERE gluster_aclcontrol.disk_id = gluster_diskinfo.disk_id ";

        //RowMapper<ACLInformation> rowMapper = new BeanPropertyRowMapper<ACLInformation>(ACLInformation.class);

        try {
            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            System.out.println("读取数据库出错");
            e.printStackTrace();
            return null;
        }
    }

    //根据路径名查出有没有路径对应的id，如果有就返回确切的id值，如果没有返回0
    public Integer findDirIdByDirName(String disk_dirname) {
        String sql = "SELECT disk_id FROM gluster_diskinfo WHERE disk_dirname = ?";

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, disk_dirname);
        } catch (Exception e) {
            return 0;
        }
    }

    //根据用户名，ip首末和盘符名来查查aclInfo中是否已经有此条记录，注意这里不是盘符的id，而是盘符名
    public boolean findByUserAndDirname(String general_user, Long acl_ip_start, Long acl_ip_end, Integer disk_id) {
        System.out.println(acl_ip_start + " " + acl_ip_end + " " + disk_id);
        if (general_user != null) {
            System.out.println("正在查找是否有相似记录");
            String sql = "SELECT acl_id FROM  gluster_aclcontrol " +
                    " WHERE ( general_user = ? AND acl_ip_start = ? " +
                    " AND acl_ip_end = ? AND disk_id = ? )";

            try {
                jdbcTemplate.queryForObject(sql, Integer.class, general_user, acl_ip_start, acl_ip_end, disk_id);
                System.out.println("找到了相似记录");
                return true;
            } catch (Exception e) {
                System.out.println("没有找到相似记录");
                return false;
            }
        } else {
            System.out.println("没有用户名，正在查找是否有相似记录");
            String sql = "SELECT acl_id FROM gluster_aclcontrol " +
                    " WHERE ( general_user IS NULL ) AND acl_ip_start = ? AND acl_ip_end = ? AND disk_id = ?";

            RowMapper<Integer> rowMapper = new SingleColumnRowMapper<Integer>(Integer.class);//注意，如果是是初始基本类型类型

            try {
                if (jdbcTemplate.query(sql, rowMapper, acl_ip_start, acl_ip_end, disk_id).size() == 0)//返回了一个数组
                {
                    return false;
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("catch到错误,没有找到相似记录");
                return false;
            }

        }
    }


    public void AddAclInformation(String general_user, Integer disk_id, Long acl_ip_start, Long acl_ip_end, boolean acl_allowmount) {
        String sql = "INSERT INTO gluster_aclcontrol (general_user , disk_id , acl_ip_start , acl_ip_end , acl_allowmount) VALUES( ? , ? , ? , ? , ? )";
        jdbcTemplate.update(sql, general_user, disk_id, acl_ip_start, acl_ip_end, acl_allowmount);
        System.out.println("向数据库中写入数据");
    }

    public void updateCalInformation(Integer acl_id, String general_user, Integer disk_id, Long acl_ip_start, Long acl_ip_end, boolean acl_allowmount) {
        String sql = "UPDATE gluster_aclcontrol SET general_user = ? , disk_id = ? , acl_ip_start = ? , acl_ip_end = ? , acl_allowmount = ? WHERE acl_id = ?";
        jdbcTemplate.update(sql, general_user, disk_id, acl_ip_start, acl_ip_end, acl_allowmount, acl_id);
        System.out.println("在数据库中修改了数据");
    }

    //形参分别是盘符id和要修改成的分配空间
    public void updateDiskCap(Integer disk_id, String disk_cap) {
        System.out.println("进入修改可用空间的方法 , disk_capacity = " + disk_cap + " disk_id = " + disk_id);
        String sql = "UPDATE gluster_diskinfo SET disk_capacity = ? WHERE disk_id = ?";
        jdbcTemplate.update(sql, disk_cap, disk_id);
        System.out.println("修改了diskinfo中的盘符可用空间");
    }

    public void insertDiskInfomation(String disk_dirname, String disk_capacity) {
        String sql = "INSERT INTO gluster_diskinfo ( disk_dirname , disk_capacity , disk_usage ) VALUES ( ? , ? , ? )";
        jdbcTemplate.update(sql, disk_dirname, disk_capacity, disk_capacity);
        System.out.println("添加了一个盘符信息");
    }

    public void deleteAclByAclId(Integer acl_id) {
        String sql = "DELETE FROM gluster_aclcontrol WHERE acl_id = ?";
        jdbcTemplate.update(sql, acl_id);
        System.out.println("删除元素");
    }

    public void deleteAclByUsername(String username) {
        System.out.println(username);
        String sql = "DELETE FROM gluster_aclcontrol WHERE general_user = ?";
        jdbcTemplate.update(sql, username);
        System.out.println("根据用户名删除了对应的acl信息");
    }

    public void insertToGidInfo(String userName, Long ip, String dirName, Integer token) {
        String sql = "SELECT COUNT(*) FROM gluster_client_gidinfo WHERE general_user = ? AND gidinfo_ip = ? AND " +
                "gidinfo_dirname = ?";
        Integer n = jdbcTemplate.queryForObject(sql, new Object[]{userName, ip, dirName}, Integer.class);
        if (n > 0) {
            String sql1 = "UPDATE gluster_client_gidinfo SET gidinfo_token = ? WHERE general_user = ? AND " +
                    "gidinfo_ip = ? AND gidinfo_dirname = ?";
            jdbcTemplate.update(sql1, new Object[]{token, userName, ip, dirName});
        } else {
            String sql2 = "INSERT INTO gluster_client_gidinfo (general_user,gidinfo_ip,gidinfo_dirname,gidinfo_token) VALUES (" +
                    "? , ? , ? , ? )";
            jdbcTemplate.update(sql2, new Object[]{userName, ip, dirName, token});
        }
    }

    public List findByUserNameAndIp(String general_user, Long ip) {
        try {
            if (general_user != null) {
                String sql = "SELECT disk_id,acl_allowmount FROM gluster_aclcontrol WHERE ( general_user = ?" +
                        " AND acl_ip_start <= ? " +
                        " AND acl_ip_end >= ? )";

                List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, general_user, ip, ip);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public String findDirNameById(Integer id) {
        String sql = "SELECT disk_dirname FROM gluster_diskinfo WHERE disk_id = ?";

        String result = jdbcTemplate.queryForObject(sql, String.class, id);
        System.out.println(result);
        return result;
    }

    public String findCapByDirname(String dirname)//这个是已经可以确定数据库中有这个dirname的情况下使用的方法
    {
        String sql = "SELECT disk_capacity FROM gluster_diskinfo WHERE disk_dirname = ?";

        return jdbcTemplate.queryForObject(sql, String.class, dirname);
    }

    //这个方法用来用diskid来所有aclid，如果没有搜到返回0
    public Integer findAclidByDirId(Integer dirId) {
        String sql = "SELECT acl_id FROM gluster_aclcontrol WHERE disk_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, dirId);
        } catch (Exception e) {
            return 0;
        }
    }

    public int getGid(String userName, Long ip, String dirName, Integer token) {
        String sql = "SELECT gidinfo_gid FROM gluster_client_gidinfo WHERE general_user = ? AND gidinfo_ip = ? " +
                "AND gidinfo_dirname = ? AND gidinfo_token = ?";
        Integer gid = jdbcTemplate.queryForObject(sql, new Object[]{userName, ip, dirName, token}, Integer.class);
        return gid;
    }

}
