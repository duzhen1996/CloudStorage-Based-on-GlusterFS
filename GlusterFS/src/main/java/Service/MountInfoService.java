package Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by DZ on 2016/1/22.
 */
public interface MountInfoService
{
    public Integer getInfoCount( String nodename );
    public List<List> getDayMount( String nodename );
    public List<Map<String,Object>> getAllMount( String nodename );
}
