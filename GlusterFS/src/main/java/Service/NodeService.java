package Service;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by DZ on 2016/1/22.
 */
public interface NodeService
{
    public boolean ifHaveNodename( String Nodename );
    public void addNode(String aclname , Long aclip , String aclcaps );
    public void delete( String node );
    public String getIpStr( String nodename );
    public boolean ifHaveSameIp( Long ip );
}
