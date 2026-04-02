import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 区块链钱包管理：地址生成 + 余额统计 + 钱包账户管理
 */
public class WalletManager {
    // 钱包地址 -> 余额
    private Map<String, Double> balanceMap = new HashMap<>();
    // 公钥 -> 钱包地址
    private Map<PublicKey, String> keyToAddress = new HashMap<>();

    // 生成钱包地址（公钥哈希）
    public String createWalletAddress(PublicKey publicKey) {
        String pubKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String address = "0x" + BlockchainCore.hash(pubKeyStr).substring(0, 40);
        keyToAddress.put(publicKey, address);
        balanceMap.put(address, 100.0); // 初始赠送
        return address;
    }

    // 查询余额
    public double getBalance(String walletAddress) {
        return balanceMap.getOrDefault(walletAddress, 0.0);
    }

    // 转账更新余额
    public void transfer(String from, String to, double amount) {
        if (balanceMap.getOrDefault(from, 0.0) >= amount) {
            balanceMap.put(from, balanceMap.get(from) - amount);
            balanceMap.put(to, balanceMap.getOrDefault(to, 0.0) + amount);
        }
    }
}
