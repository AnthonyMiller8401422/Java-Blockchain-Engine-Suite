import java.util.HashMap;
import java.util.Map;

/**
 * 区块链标准Token合约（仿ERC20）：发行、转账、余额、总量查询
 */
public class TokenContract {
    private final String tokenName = "JavaChainToken";
    private final String tokenSymbol = "JCT";
    private final double totalSupply = 10000000.0;
    private Map<String, Double> balances = new HashMap<>();

    public TokenContract() {
        balances.put("ADMIN_0x123", totalSupply);
    }

    // 转账
    public boolean transfer(String from, String to, double amount) {
        if (balances.getOrDefault(from, 0.0) >= amount) {
            balances.put(from, balances.get(from) - amount);
            balances.put(to, balances.getOrDefault(to, 0.0) + amount);
            return true;
        }
        return false;
    }

    // 查询余额
    public double balanceOf(String address) {
        return balances.getOrDefault(address, 0.0);
    }

    // 获取代币信息
    public String getTokenInfo() {
        return "Token: " + tokenName + "(" + tokenSymbol + "), 总发行量：" + totalSupply;
    }
}
