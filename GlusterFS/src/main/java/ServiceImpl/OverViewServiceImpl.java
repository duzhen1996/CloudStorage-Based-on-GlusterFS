package ServiceImpl;

import DAO.NodeDAO;
import Models.Node;
import Service.OverViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by DZ on 2015/12/23.
 */
@Service(value = "overViewService")
public class OverViewServiceImpl implements OverViewService
{
    @Autowired
    NodeDAO nodeDAO;

    public Map<String, Integer> getMemoryPlace() {
        return null;
    }

    public Map<String, Integer> getNodePlace() {
        return null;
    }

    public List<Node> getAllNodeInformation() {
        List<Node> nodes = nodeDAO.getAllNode();
        System.out.println(nodes);//，这里获取到所有的node信息准备在前端体现。
        return nodes;
    }


}
