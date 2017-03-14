package Models;

/**
 * Created by DZ on 2015/12/24.
 */
public class Disk
{
    String disk_dirname;
    Long disk_capacity;
    Long disk_usage;

    public Disk(String disk_dirname, Long disk_capacity, Long disk_usage) {
        this.disk_dirname = disk_dirname;
        this.disk_capacity = disk_capacity;
        this.disk_usage = disk_usage;
    }

    public Disk() {
    }

    public String getDisk_dirname() {
        return disk_dirname;
    }

    public void setDisk_dirname(String disk_dirname) {
        this.disk_dirname = disk_dirname;
    }

    public Long  getDisk_capacity() {
        return disk_capacity;
    }

    public void setDisk_capacity(Long  disk_capacity) {
        this.disk_capacity = disk_capacity;
    }

    public Long getDisk_usage() {
        return disk_usage;
    }

    public void setDisk_usage(Long disk_usage) {
        this.disk_usage = disk_usage;
    }

    @Override
    public String toString() {
        return "Disk{" +
                "disk_dirname='" + disk_dirname + '\'' +
                ", disk_capacity=" + disk_capacity +
                ", disk_usage=" + disk_usage +
                '}';
    }
}
