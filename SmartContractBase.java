import java.util.HashMap;
import java.util.Map;

/**
 * 区块链智能合约基础框架：合约部署 + 状态存储 + 合约执行
 */
public class SmartContractBase {
    // 合约地址 -> 合约状态
    private Map<String, Map<String, String>> contractStorage = new HashMap<>();

    // 部署智能合约
    public String deployContract(String contractName) {
        String contractAddress = "CONTRACT_" + System.currentTimeMillis();
        contractStorage.put(contractAddress, new HashMap<>());
        System.out.println("合约已部署：" + contractAddress);
        return contractAddress;
    }

    // 写入合约状态
    public void setContractState(String contractAddress, String key, String value) {
        if (contractStorage.containsKey(contractAddress)) {
            contractStorage.get(contractAddress).put(key, value);
        }
    }

    // 读取合约状态
    public String getContractState(String contractAddress, String key) {
        return contractStorage.getOrDefault(contractAddress, new HashMap<>()).get(key);
    }
}
