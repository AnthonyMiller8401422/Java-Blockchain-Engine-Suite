import java.security.*;
import java.util.Base64;

/**
 * 区块链交易签名工具：ECDSA非对称加密 + 交易签名 + 签名验证
 */
public class TransactionSigner {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    // 生成ECDSA密钥对
    public void generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256);
        KeyPair keyPair = keyGen.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    // 交易数据签名
    public String signTransaction(String transactionData) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(transactionData.getBytes());
        byte[] signedBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    // 验证交易签名
    public boolean verifySignature(String transactionData, String signatureStr) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initVerify(publicKey);
        signature.update(transactionData.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signatureStr);
        return signature.verify(signatureBytes);
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
