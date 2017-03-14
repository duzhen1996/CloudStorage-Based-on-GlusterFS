package Models;

import java.util.Date;

/**
 * Created by DZ on 2016/1/22.
 */
public class AccessInfo
{
    Date access_day;
    Integer access_number;

    public AccessInfo(Date access_day, Integer access_number) {
        this.access_day = access_day;
        this.access_number = access_number;
    }

    public AccessInfo() {
    }

    public Date getAccess_day() {
        return access_day;
    }

    public void setAccess_day(Date access_day) {
        this.access_day = access_day;
    }

    public Integer getAccess_number() {
        return access_number;
    }

    public void setAccess_number(Integer access_number) {
        this.access_number = access_number;
    }

    @Override
    public String toString() {
        return "AccessInfo{" +
                "access_day=" + access_day +
                ", access_number=" + access_number +
                '}';
    }
}
