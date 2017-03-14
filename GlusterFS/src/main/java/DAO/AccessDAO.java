package DAO;

import Models.AccessInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * Created by DZ on 2016/1/22.
 */
@Repository
public class AccessDAO
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //这个函数查找在两个时间段中的信息，保险起见
    public List<AccessInfo> findNumberByDate( String date1 , String date2 )
    {
        System.out.println("进入持久层 " + date1 + " " + date2 );
        String sql = "SELECT access_day , access_number FROM gluster_accessinfo WHERE access_day BETWEEN \'" + date1 + "\' AND \'" + date2 + "\' ORDER BY access_day";

        RowMapper<AccessInfo> rowMapper = new BeanPropertyRowMapper<AccessInfo>(AccessInfo.class);

        System.out.println( jdbcTemplate.query( sql , rowMapper ) );

        return jdbcTemplate.query(sql, rowMapper);
    }

//    public static void main(String[] args) {
//        AccessDAO accessDAO = new AccessDAO();
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//
//        Calendar now = Calendar.getInstance();//获取日期对象，这个对象是可以进行时间操作的，默认应该是当前时间
//        Date date1 = now.getTime();
//
//        now.add(Calendar.MONTH , -1);//往前一个月
//        Date date2 = now.getTime();
//
//        System.out.println(accessDAO.findNumberByDate( date1 , date2 ));
//    }
}
