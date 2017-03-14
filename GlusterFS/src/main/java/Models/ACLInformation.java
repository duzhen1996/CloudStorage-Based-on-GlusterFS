package Models;

/**
 * Created by DZ on 2015/12/24.
 */
public class ACLInformation
{
    String general_user;
    Integer acl_id;
    Long acl_ip_start;
    Long acl_ip_end;
    boolean acl_allowmount;//这儿看是否可以挂载，1是可以，0是不可以
    String disk_dirname;
    String disk_capacity;//这个是容量的值，希望可以自带单位
    String disk_usage;
    Integer disk_id;
    //之所以后三个这个设计就是为了防止Jdbc没办法传自定义对象的问题


    public ACLInformation(String general_user, Integer acl_id, Long acl_ip_start, Long acl_ip_end, boolean acl_allowmount, String disk_dirname, String disk_capacity, String disk_usage, Integer disk_id) {
        this.general_user = general_user;
        this.acl_id = acl_id;
        this.acl_ip_start = acl_ip_start;
        this.acl_ip_end = acl_ip_end;
        this.acl_allowmount = acl_allowmount;
        this.disk_dirname = disk_dirname;
        this.disk_capacity = disk_capacity;
        this.disk_usage = disk_usage;
        this.disk_id = disk_id;
    }

    public String getGeneral_user() {
        return general_user;
    }

    public void setGeneral_user(String general_user) {
        this.general_user = general_user;
    }

    public Integer getAcl_id() {
        return acl_id;
    }

    public void setAcl_id(Integer acl_id) {
        this.acl_id = acl_id;
    }

    public Long getAcl_ip_start() {
        return acl_ip_start;
    }

    public void setAcl_ip_start(Long acl_ip_start) {
        this.acl_ip_start = acl_ip_start;
    }

    public Long getAcl_ip_end() {
        return acl_ip_end;
    }

    public void setAcl_ip_end(Long acl_ip_end) {
        this.acl_ip_end = acl_ip_end;
    }

    public boolean isAcl_allowmount() {
        return acl_allowmount;
    }

    public void setAcl_allowmount(boolean acl_allowmount) {
        this.acl_allowmount = acl_allowmount;
    }

    public String getDisk_dirname() {
        return disk_dirname;
    }

    public void setDisk_dirname(String disk_dirname) {
        this.disk_dirname = disk_dirname;
    }

    public String getDisk_capacity() {
        return disk_capacity;
    }

    public void setDisk_capacity(String disk_capacity) {
        this.disk_capacity = disk_capacity;
    }

    public String getDisk_usage() {
        return disk_usage;
    }

    public void setDisk_usage(String disk_usage) {
        this.disk_usage = disk_usage;
    }

    public Integer getDisk_id() {
        return disk_id;
    }

    public void setDisk_id(Integer disk_id) {
        this.disk_id = disk_id;
    }

    @Override
    public String toString() {
        return "ACLInformation{" +
                "general_user='" + general_user + '\'' +
                ", acl_id=" + acl_id +
                ", acl_ip_start=" + acl_ip_start +
                ", acl_ip_end=" + acl_ip_end +
                ", acl_allowmount=" + acl_allowmount +
                ", disk_dirname='" + disk_dirname + '\'' +
                ", disk_capacity='" + disk_capacity + '\'' +
                ", disk_usage='" + disk_usage + '\'' +
                ", disk_id=" + disk_id +
                '}';
    }
}
