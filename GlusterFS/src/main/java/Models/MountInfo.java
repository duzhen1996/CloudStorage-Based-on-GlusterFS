package Models;

import java.util.Date;

/**
 * Created by DZ on 2016/1/22.
 */
public class MountInfo
{
    Integer gidinfo_gid;
    String node_name;
    Date node_user_mounttime;
    Date node_user_umounttime;

    public MountInfo(Integer gidinfo_gid, String node_name, Date node_user_mounttime, Date node_user_umounttime) {
        this.gidinfo_gid = gidinfo_gid;
        this.node_name = node_name;
        this.node_user_mounttime = node_user_mounttime;
        this.node_user_umounttime = node_user_umounttime;
    }

    public MountInfo() {
    }

    public Integer getGidinfo_gid() {
        return gidinfo_gid;
    }

    public void setGidinfo_gid(Integer gidinfo_gid) {
        this.gidinfo_gid = gidinfo_gid;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public Date getNode_user_mounttime() {
        return node_user_mounttime;
    }

    public void setNode_user_mounttime(Date node_user_mounttime) {
        this.node_user_mounttime = node_user_mounttime;
    }

    public Date getNode_user_umounttime() {
        return node_user_umounttime;
    }

    public void setNode_user_umounttime(Date node_user_umounttime) {
        this.node_user_umounttime = node_user_umounttime;
    }

    @Override
    public String toString() {
        return "MountInfo{" +
                "gidinfo_gid=" + gidinfo_gid +
                ", node_name='" + node_name + '\'' +
                ", node_user_mounttime=" + node_user_mounttime +
                ", node_user_umounttime=" + node_user_umounttime +
                '}';
    }
}
