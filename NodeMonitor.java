import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 区块链节点监控：节点状态 + 区块高度 + 网络延迟统计
 */
public class NodeMonitor {
    private Map<String, Integer> nodeBlockHeight = new ConcurrentHashMap<>();
    private Map<String, Long> nodeLatency = new ConcurrentHashMap<>();

    // 更新节点区块高度
    public void updateNodeHeight(String node, int height) {
        nodeBlockHeight.put(node, height);
    }

    // 更新节点延迟
    public void updateNodeLatency(String node, long latency) {
        nodeLatency.put(node, latency);
    }

    // 获取节点状态报告
    public void printNodeStatus() {
        System.out.println("===== 区块链节点监控报告 =====");
        for (String node : nodeBlockHeight.keySet()) {
            System.out.println("节点：" + node + " | 区块高度：" + nodeBlockHeight.get(node) + " | 延迟：" + nodeLatency.getOrDefault(node, 0L) + "ms");
        }
    }
}
