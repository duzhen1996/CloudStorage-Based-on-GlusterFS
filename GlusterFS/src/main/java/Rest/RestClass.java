package Rest;

import DAO.ACLInformationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by DZ on 2016/1/11.
 */
@Component
@RequestMapping
public class RestClass
{
    @Autowired
    ACLInformationDAO aclInformationDAO;

    @RequestMapping(value = "/{username}_{password}_{ip}.do")//域名最好用do结尾
    @ResponseBody
    public String RestFunction(@PathVariable("username") String username , @PathVariable("password") String password , @PathVariable("ip") Long ip)
    {
        String dirNameAndTokenAndGid = new String();
        int gid;

        //随机生成token
        Random random = new Random();
        Integer token = random.nextInt(100000000) + 1;

        System.out.println("username:" + username + ",ip:" + ip);
        List<Map<String , Object>> result = aclInformationDAO.findByUserNameAndIp( username , ip );
        System.out.println("result:" + result);
        if(result.toString() == "[]")
            return null;
        for (Map map : result)
        {
            //temp.add( aclInformationDAO.findDirNameById( (Integer) map.get("disk_id") ));
            if((Boolean)map.get("acl_allowmount") == true) {
                int disk_id = (Integer) map.get("disk_id");
                String dir = aclInformationDAO.findDirNameById(disk_id);
                aclInformationDAO.insertToGidInfo(username,ip,dir,token);
                gid = aclInformationDAO.getGid(username,ip,dir,token);
                dirNameAndTokenAndGid += gid;
                dirNameAndTokenAndGid += ",";
                dirNameAndTokenAndGid += dir;
                dirNameAndTokenAndGid += ",";
            }
        }
        System.out.println(dirNameAndTokenAndGid);
        if(dirNameAndTokenAndGid.equals("")) {
            return "no dir allowed to mount";
        }
        //aclInformationDAO.insertToGidInfo(username,ip,dirNameAndTokenAndGid,token);
        //gid = aclInformationDAO.getGid(username,ip,dirNameAndTokenAndGid,token);
        dirNameAndTokenAndGid += token;
        //dirNameAndTokenAndGid += ",";
        //dirNameAndTokenAndGid += gid;
        System.out.println(dirNameAndTokenAndGid);
        return dirNameAndTokenAndGid;
    }
}
