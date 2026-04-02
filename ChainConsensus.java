import java.util.List;

/**
 * 区块链共识机制实现：最长链规则 + 冲突解决 + 链同步
 */
public class ChainConsensus {
    // 最长链共识：选择最长有效链
    public List<BlockchainCore.Block> resolveConflicts(List<List<BlockchainCore.Block>> chains) {
        List<BlockchainCore.Block> longestChain = null;
        int maxLength = 0;

        for (List<BlockchainCore.Block> chain : chains) {
            if (isChainValid(chain) && chain.size() > maxLength) {
                maxLength = chain.size();
                longestChain = chain;
            }
        }
        return longestChain;
    }

    // 校验外部链合法性
    private boolean isChainValid(List<BlockchainCore.Block> chain) {
        for (int i = 1; i < chain.size(); i++) {
            BlockchainCore.Block current = chain.get(i);
            BlockchainCore.Block previous = chain.get(i - 1);
            if (!current.hash.equals(current.calculateHash())) return false;
            if (!current.previousHash.equals(previous.hash)) return false;
        }
        return true;
    }
}
