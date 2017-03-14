package Service;

import Models.ACLInformation;

import java.util.List;

/**
 * Created by DZ on 2015/12/25.
 */
public interface ACLService
{
    public List<ACLInformation> getAllAcl();//把所有的Acl信息从数据库里面拿出来
    public boolean addACL( ACLInformation aclInformation );//这个方法解决写入一个acl的问题，主要的一个困难点在如何把diskid的值写进去
    public Integer getDiskCap( String disk_dirname );
    public boolean ifHaveSame(String general_user, Long acl_ip_start, Long acl_ip_end, String disk_dirname );
    public String findDiskCap( String disk_dirname );//这里通过盘符名来查看盘符容量
    public boolean ifHaveUser( String general_user );
    public boolean ifHaveDirname( String dirname );
    public void changeAclInformation(Integer acl_id, String disk_usage, String disk_capacity, boolean acl_allowmount, String general_user, String disk_dirname, Long acl_ip_start, Long acl_ip_end);
    public void addAclInformation(String disk_capacity , boolean acl_allowmount , String general_user , String disk_dirname , Long acl_ip_start, Long acl_ip_end );
    public void deleteAclInformation( Integer acl_id );
    public boolean ifDirUsedChange( String dirname , Integer acl_id );
    public boolean ifDirUsedAdd( String dirname);
    public void addNewDir(String dirname);

}
