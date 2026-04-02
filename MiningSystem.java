/**
 * 区块链挖矿系统：工作量证明(PoW) + 难度动态调整
 */
public class MiningSystem {
    private static final int DIFFICULTY = 4;

    // 工作量证明：寻找满足前缀0的哈希
    public static String proofOfWork(String previousHash, String data, long timeStamp) {
        String prefix = getDifficultyPrefix();
        int nonce = 0;
        while (true) {
            String hashInput = previousHash + timeStamp + data + nonce;
            String hash = BlockchainCore.calculateHashStatic(hashInput);
            if (hash.startsWith(prefix)) {
                return hash;
            }
            nonce++;
        }
    }

    // 获取难度前缀（0的个数）
    private static String getDifficultyPrefix() {
        return "0".repeat(DIFFICULTY);
    }

    // 静态哈希工具（供本类调用）
    public static String calculateHashStatic(String input) {
        return BlockchainCore.hash(input);
    }
}

// 补充依赖方法
class BlockchainCore {
    public static String hash(String input) {
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
