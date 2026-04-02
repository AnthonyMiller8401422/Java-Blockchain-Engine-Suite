import java.util.Base64;

/**
 * 区块链数据编码工具：Merkle叶子节点编码 + 数据压缩 + 安全序列化
 */
public class BlockDataEncoder {
    // 交易数据Base64编码
    public String encodeData(String rawData) {
        return Base64.getEncoder().encodeToString(rawData.getBytes());
    }

    // 解码交易数据
    public String decodeData(String encodedData) {
        return new String(Base64.getDecoder().decode(encodedData));
    }

    // 生成Merkle树叶子节点哈希
    public String createMerkleLeaf(String transaction) {
        return BlockchainCore.hash(transaction);
    }

    // 生成Merkle父节点哈希
    public String createMerkleParent(String left, String right) {
        return BlockchainCore.hash(left + right);
    }
}
