package ServiceImpl;

import DAO.MountDAO;
import Service.MountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DZ on 2016/1/22.
 */
@Service(value = "mountInfoService")
public class MountInfoSeviceImpl implements MountInfoService
{
    @Autowired
    MountDAO mountDAO;

    public MountInfoSeviceImpl()
    {
        System.out.println("Mountinfo对象初始化");
    }

    public Integer getInfoCount( String nodename)
    {
        //首先进行Date的转化，这个时候就需要加上具体时间
        Calendar now = Calendar.getInstance();//获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d H:mm:ss");//制定时间格式
        String dateStr1 = simpleDateFormat.format( now.getTime() );//获取日期下界

        //获取一天前的时间
        now.add(Calendar.DAY_OF_YEAR,-1);
        String dateStr2 = simpleDateFormat.format( now.getTime() );

        //调用持久层方法获取结果
        Integer result = mountDAO.getMountCountByDayAndNode(dateStr2 , dateStr1 , nodename);

        return result;
    }

    //查找某一个节点最近7天每一天的访问量，从当天到7天前这样子排列，存在一个键值对中
    public List<List> getDayMount( String nodename )
    {
        //首先获取当天时间，只要精确到年月日就可以了
        Calendar now = Calendar.getInstance();//获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d");
        String day;
        String nextday;
        List<String> dayList = new ArrayList<String>();
        List<Integer> countList = new ArrayList<Integer>();
        List<Long> secondList = new ArrayList<Long>();//这里List传所有时间的秒数
        //通过一个循环来查找最近七天节点的访问量
        List<List> result = new ArrayList<List>();
        for( int i = 0 ; i < 9 ; i++ )
        {
            day = simpleDateFormat.format( now.getTime() );//获取指定格式的日期
            now.add(Calendar.DAY_OF_YEAR,1);
            nextday = simpleDateFormat.format(now.getTime());//获取下一天的日期
            secondList.add(now.getTime().getTime()-86400000);
            //将查到的信息放入Map里面

            dayList.add(day);
            countList.add(mountDAO.getCountByDayAndNode(day + " 0:00:00", nextday + " 0:00:00" , nodename));
            //countInformation.put(day, mountDAO.getCountByDayAndNode(day, nextday, nodename));//将从数据库查到的信息放到Map里面
            now.add(Calendar.DAY_OF_YEAR , -2);
        }
        result.add(dayList);
        result.add(countList);
        result.add(secondList);

        System.out.println(result);

        return result;
    }

    public List<Map<String, Object>> getAllMount(String nodename)
    {
        return mountDAO.getAllMountUser(nodename);
    }


}
