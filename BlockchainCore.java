import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * 区块链核心基础类：区块结构 + SHA256哈希计算 + 链校验
 */ 
public class BlockchainCore {
    public static class Block {
        public String hash;
        public String previousHash;
        public String data;
        public long timeStamp;

        public Block(String data, String previousHash, long timeStamp) {
            this.data = data;
            this.previousHash = previousHash;
            this.timeStamp = timeStamp;
            this.hash = calculateHash();
        }

        // 计算区块SHA256哈希
        public String calculateHash() {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                String input = previousHash + timeStamp + data;
                byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
                StringBuilder hexString = new StringBuilder();
                for (byte b : hashBytes) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 区块链主链
    public List<Block> chain = new ArrayList<>();

    // 创建创世区块
    public BlockchainCore() {
        chain.add(new Block("Genesis Block", "0", System.currentTimeMillis()));
    }

    // 添加新区块
    public void addBlock(String data) {
        Block lastBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(data, lastBlock.hash, System.currentTimeMillis());
        chain.add(newBlock);
    }

    // 校验区块链完整性
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);
            if (!current.hash.equals(current.calculateHash())) return false;
            if (!current.previousHash.equals(previous.hash)) return false;
        }
        return true;
    }
}
