package DAO;

import Models.MountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.xml.soap.SAAJResult;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by DZ on 2016/1/22.
 */
@Repository
public class MountDAO
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //这个方法通过一个日期来查找，根据时间找到用户还未下线的节点个数
    public Integer getMountCountByDayAndNode( String date1 , String date2 , String nodename)
    {
        System.out.println("开始查询所有节点正在线上的用户数量" + date1 + " " + date2 + " " + nodename );
        //看看不给具体时间只精确到某一天
        String sql = "SELECT COUNT(node_name) FROM gluster_node_userinfo WHERE ( node_name = ? ) AND (node_user_umounttime is NULL) AND (node_user_mounttime Between " +
                " \'" + date1 + "\' AND \'" + date2 +"\')";

        System.out.println(sql);

        return jdbcTemplate.queryForObject(sql , Integer.class , nodename);
    }

    //这里通过一个节点名称查找在一段时间内所有的访问次数
    public Integer getCountByDayAndNode( String date1 , String date2 , String nodename)
    {
        System.out.println("开始查询某一天的访问数量" + date1 + " " + date2 + " " + nodename );
        String sql = "SELECT COUNT(*) FROM gluster_node_userinfo WHERE ( node_name = ? ) AND (node_user_mounttime Between \' " + date1 + "\' AND \'" + date2 + "\')";

        return jdbcTemplate.queryForObject(sql , Integer.class , nodename);
    }

    //这个方法用来查找所有在某个节点上挂载的用户的全部信息
    public List<Map<String,Object>> getAllMountUser( String nodeName )
    {
        System.out.println("准备查找在" + nodeName + "上所有挂载用户的信息" );
        String sql = "SELECT gluster_client_gidinfo.general_user , gluster_client_gidinfo.gidinfo_ip , gluster_diskinfo.disk_capacity , gluster_diskinfo.disk_usage " +
                " FROM gluster_client_gidinfo , gluster_diskinfo , gluster_node_userinfo " +
                " WHERE gluster_node_userinfo.node_user_umounttime is NULL AND gluster_node_userinfo.gidinfo_gid = gluster_client_gidinfo.gidinfo_gid " +
                " AND gluster_client_gidinfo.gidinfo_dirname = gluster_diskinfo.disk_dirname AND gluster_node_userinfo.node_name = ?";

        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql , nodeName);

        System.out.println(result);

        return result;
    }
}
