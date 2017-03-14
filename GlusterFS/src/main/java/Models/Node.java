package Models;

/**
 * Created by DZ on 2015/12/23.
 */
public class Node
{
    private String node_name;
    private Long node_capacity;
    private Long node_usage;
    private boolean node_state;
    private Long node_ip;

    public Node(String node_name, Long node_capacity, Long node_usage, boolean node_state, Long node_ip) {
        this.node_name = node_name;
        this.node_capacity = node_capacity;
        this.node_usage = node_usage;
        this.node_state = node_state;
        this.node_ip = node_ip;
    }

    public Node() {
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public Long getNode_capacity() {
        return node_capacity;
    }

    public void setNode_capacity(Long node_capacity) {
        this.node_capacity = node_capacity;
    }

    public Long getNode_usage() {
        return node_usage;
    }

    public void setNode_usage(Long node_usage) {
        this.node_usage = node_usage;
    }

    public boolean isNode_state() {
        return node_state;
    }

    public void setNode_state(boolean node_state) {
        this.node_state = node_state;
    }

    public Long getNode_ip() {
        return node_ip;
    }

    public void setNode_ip(Long node_ip) {
        this.node_ip = node_ip;
    }

    @Override
    public String toString() {
        return "Node{" +
                "node_name='" + node_name + '\'' +
                ", node_capacity=" + node_capacity +
                ", node_usage=" + node_usage +
                ", node_state=" + node_state +
                ", node_ip=" + node_ip +
                '}';
    }
}
