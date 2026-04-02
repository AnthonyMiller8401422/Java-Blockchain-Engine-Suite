import java.util.ArrayList;
import java.util.List;

/**
 * 区块链P2P网络模拟：节点管理 + 区块广播 + 交易同步
 */
public class P2PNetwork {
    // 网络节点列表
    private List<String> nodes = new ArrayList<>();

    // 注册节点
    public void registerNode(String nodeAddress) {
        if (!nodes.contains(nodeAddress)) {
            nodes.add(nodeAddress);
            System.out.println("节点已加入P2P网络：" + nodeAddress);
        }
    }

    // 广播新区块
    public void broadcastNewBlock(String blockHash) {
        for (String node : nodes) {
            System.out.println("向节点 " + node + " 广播区块：" + blockHash);
        }
    }

    // 广播新交易
    public void broadcastNewTransaction(String txId) {
        for (String node : nodes) {
            System.out.println("向节点 " + node + " 同步交易：" + txId);
        }
    }

    // 获取所有节点
    public List<String> getNetworkNodes() {
        return nodes;
    }
}
