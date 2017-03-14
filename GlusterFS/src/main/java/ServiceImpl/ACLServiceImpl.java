package ServiceImpl;

import DAO.ACLInformationDAO;
import DAO.UserDAO;
import Models.ACLInformation;
import Service.ACLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DZ on 2015/12/25.
 */
@Service(value = "aCLService")
public class ACLServiceImpl implements ACLService
{
    @Autowired
    private ACLInformationDAO aclInformationDAO;

    @Autowired
    private UserDAO userDAO;

    public List getAllAcl()
    {
        List aclInformationList = aclInformationDAO.findAll();
        System.out.println("获得了大量ACl信息");

        return aclInformationList;
    }

    public boolean addACL(ACLInformation aclInformation) {

        return false;
    }

    public Integer getDiskCap(String disk_dirname)
    {
        Integer result = aclInformationDAO.findDiskCapbyName(disk_dirname);
        return result;
    }

    public boolean ifHaveSame(String general_user, Long acl_ip_start, Long acl_ip_end, String disk_dirname )
    {
        //如果在数据库中发现了用户名、路径名,ip一样的记录就返回true
        return aclInformationDAO.findByUserAndDirname( general_user , acl_ip_start , acl_ip_end ,
                aclInformationDAO.findDirIdByDirName(disk_dirname) );
    }

    public String findDiskCap(String disk_dirname)
    {
        System.out.println("在服务层中进行盘符容量的查询");
        return aclInformationDAO.findCapByDirname(disk_dirname);
    }

    public boolean ifHaveUser(String general_user)
    {
        return userDAO.ifnotClientByUserName(general_user);
    }

    public boolean ifHaveDirname(String dirname)
    {
        if ( aclInformationDAO.findDirIdByDirName(dirname) != 0)
        {
            System.out.println("这个路径存在");
            return true;
        }
        return false;
    }

    //使用这个服务添加一个数据，
    public void changeAclInformation(Integer acl_id, String disk_usage, String disk_capacity, boolean acl_allowmount, String general_user, String disk_dirname, Long acl_ip_start, Long acl_ip_end)
    {
        //首先先看一下有没有对应的盘符名，没有对应的盘符就新建一个盘符记录，返回这个盘符的id，如果有对应的，就直接返回id
        Integer dir_id = aclInformationDAO.findDirIdByDirName(disk_dirname);

        if (dir_id != 0)
        {
            //这里修改一条数据，这里修改的是acl表中的一部分，还有可用空间没有修改
            aclInformationDAO.updateCalInformation(acl_id, general_user , dir_id , acl_ip_start , acl_ip_end , acl_allowmount  );
            //下面修改可用空间，和acl表的不在一起
            aclInformationDAO.updateDiskCap( dir_id , disk_capacity );//前面应该是dirid
        }
    }

    public void addAclInformation(String disk_capacity , boolean acl_allowmount , String general_user , String disk_dirname , Long acl_ip_start, Long acl_ip_end )
    {
        //首先先看一下有没有对应的盘符名，没有对应的盘符就新建一个盘符记录，返回这个盘符的id，如果有对应的，就直接返回id
        Integer dir_id = aclInformationDAO.findDirIdByDirName(disk_dirname);
        if (dir_id != 0)
        {
            //如果有对应的盘符名，那就直接使用id进行添加
            System.out.println("找到了对应的盘符名，" + dir_id + " ,正在直接添加");
            aclInformationDAO.AddAclInformation( general_user , dir_id , acl_ip_start , acl_ip_end , acl_allowmount );
            //下面修改可用空间，和acl表的不在一起
            aclInformationDAO.updateDiskCap( dir_id , disk_capacity );
        }
    }

    public void deleteAclInformation(Integer acl_id)
    {
        //这里调用DAO层的服务来删除一个acl节点的信息
        aclInformationDAO.deleteAclByAclId(acl_id);
        System.out.println("已删除一个acl信息");
    }

    //这里来判断一个盘符是不是被使用了，这个在修改acl信息的时候使用，返回true代表这个目录已经被占用
    public boolean ifDirUsedChange(String dirname , Integer acl_id )
    {
        System.out.println("开始看盘符有没有被使用");
        //这里首先通过盘符名来获取一个disk的id，再通过这个id来获取acl_id
        Integer diskId = aclInformationDAO.findDirIdByDirName(dirname);//如果没有找到得到的是0
        System.out.println("获取了diskid = " + diskId);
        if (diskId == 0)
        {
            System.out.println("没有找到对应的dirid");
            return false;
        }
        else
        {
            //如果找到了就查找aclid，通过diskid找aclid。
            System.out.println("开始查找aclid");
            if ( acl_id == aclInformationDAO.findAclidByDirId(diskId) )
            {
                System.out.println("这个盘符被使用了");
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean ifDirUsedAdd(String dirname) {
        System.out.println("开始看盘符有没有被使用");
        //这里首先通过盘符名来获取一个disk的id，再通过这个id来获取acl_id
        Integer diskId = aclInformationDAO.findDirIdByDirName(dirname);//如果没有找到得到的是0
        System.out.println("获取了diskid = " + diskId);
        if (diskId == 0)
        {
            System.out.println("没有找到对应的dirid");
            return false;
        }
        else
        {
            //如果找到了就查找aclid，通过diskid找aclid。
            System.out.println("开始查找aclid");
            if (  aclInformationDAO.findAclidByDirId(diskId) != 0 )
            {
                System.out.println("这个盘符被使用了");
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public void addNewDir(String dirname) {
        //这里调用持久层，添加一个盘符
        aclInformationDAO.InsertDiskbyName(dirname);
    }
}
