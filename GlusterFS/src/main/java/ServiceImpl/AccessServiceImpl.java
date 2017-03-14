package ServiceImpl;

import DAO.AccessDAO;
import Models.AccessInfo;
import Service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by DZ on 2016/1/22.
 */
@Service( value = "accessService")
public class AccessServiceImpl implements AccessService
{
    @Autowired
    AccessDAO accessDAO;

    //这个方法用来获取在一段时间之间的信息
    public List<AccessInfo> getAccess()
    {
        //首先获取当前时间
        Calendar now = Calendar.getInstance();
        Date date1 = now.getTime();

        //获取距离当前时间一个月的时间
        now.add(Calendar.MONTH , -1);
        Date date2 = now.getTime();

        //这里调整时间格式至年月日
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d");
        String dateStr1 = simpleDateFormat.format(date1);
        String dateStr2 = simpleDateFormat.format(date2);

        List<AccessInfo> result= accessDAO.findNumberByDate(dateStr2 , dateStr1 );

        System.out.println("已经获取到近期的总访问频率");

        return result;
    }
}
