package DAO;

import Models.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DZ on 2015/12/23.
 */
@Repository
public class NodeDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



//    public Node getNodeByNodeName( String name )
//    {
//        System.out.println("持久层收到请求 " + name);
//        String sql = "SELECT disk_dirname , disk_capacity , disk_usage FROM gluster_diskinfo WHERE disk_dirname = ?";
//
//        RowMapper<Node> rowMapper = new BeanPropertyRowMapper<Node>(Node.class);
//
//        try {
//            return jdbcTemplate.queryForObject(sql,rowMapper,name);
//        }
//        catch (Exception e)
//        {
//            System.out.println("没找到");
//            e.printStackTrace();
//            return null;
//        }
//    }

    public List<Node> getAllNode()
    {
        System.out.println("准备获取所有的节点");
        String sql = "SELECT node_name , node_capacity , node_usage , node_state , node_ip FROM gluster_nodeinfo";

        RowMapper<Node> rowMapper = new BeanPropertyRowMapper<Node>(Node.class);

        return jdbcTemplate.query(sql , rowMapper);
    }

    public void addNode(String nodename , Long nodeip , String nodecapacity )
    {
        System.out.println("准备插入一个节点数据, ip = " + nodeip);

        try
        {
            String sql = "INSERT INTO gluster_nodeinfo( node_name , node_capacity , node_usage , node_state , node_ip ) VALUES( ? , ? , 0 , 0 , ? )";

            jdbcTemplate.update(sql , nodename , nodecapacity , nodeip);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public String findNodename( String nodename )
    {
        System.out.println("查找节点 " + nodename );

        String sql = "SELECT node_name FROM gluster_nodeinfo WHERE node_name = ?";

        try
        {
            return jdbcTemplate.queryForObject(sql , String.class , nodename);
        }catch (Exception e)
        {
            System.out.println("并没有找到这个节点");
            return null;
        }
    }

    public List findNodenameByIp( Long nodeip )
    {
        System.out.println("开始通过nodeip= " + nodeip + "查找用户名" );

        String sql = "SELECT node_name FROM gluster_nodeinfo WHERE node_ip = ?";

        try {
            List result = jdbcTemplate.queryForList(sql , nodeip);
            System.out.println("找到了");
            return result;
        }
        catch (Exception e)
        {
            System.out.println("并没有找到");
            return null;
        }
    }

    //这个方法用来通过ip来查找节点名
    public Long findIpByNodeName(String nodename )
    {
        String sql = "SELECT node_ip FROM gluster_nodeinfo WHERE node_name = ?";

        try {
            return jdbcTemplate.queryForObject(sql , Long.class , nodename);
        }catch (Exception e)
        {
            System.out.println("数据库中没有这个nodename");
            return null;
        }
    }

    public void deleteByNodename( String nodename )
    {
        System.out.println("删除节点 " + nodename);

        String sql = "DELETE FROM gluster_nodeinfo WHERE node_name = ?";

        jdbcTemplate.update(sql , nodename);
    }

    //从节点运行状态中删除某个节点对应的信息
    public void deleteClientGidByNodename( String nodename )
    {
        System.out.println("从节点用户信息中删除节点");

        String sql = "DELETE FROM gluster_node_userinfo WHERE node_name = ?";

        try {
            jdbcTemplate.update(sql , nodename);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("没有相关节点的节点用户信息");
        }
    }

    public void deleteNodeStatusByNodename(String nodename)
    {
        System.out.println("从节点状态信息中删除相关信息");

        String sql = "DELETE FROM gluster_nodestatus WHERE node_name = ?";

        try {
            jdbcTemplate.update(sql , nodename);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("没有相关节点的节点状态信息");
        }
    }
}
