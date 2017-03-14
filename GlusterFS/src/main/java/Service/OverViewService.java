package Service;

import Models.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DZ on 2015/12/20.
 */
public interface OverViewService
{
    public Map<String,Integer> getMemoryPlace();//返回一个Map，键为储存的类别，就好像“已使用”与“未使用”，值为其所占的比例
    public Map<String,Integer> getNodePlace();//返回一个Map，键为节点名称，值为所占比例。
    public List<Node> getAllNodeInformation();//返回有关节点的所有信息，在Overview页面上显示
}