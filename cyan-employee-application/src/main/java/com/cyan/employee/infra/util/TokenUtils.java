package com.cyan.employee.infra.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 安全Token生成工具：UUID + AES加密 + HMAC签名
 */
@Component
public class TokenUtils {
    // ========== 生产环境建议从配置中心读取 ==========
    // AES密钥（16/24/32位，示例16位）
    private static final String AES_SECRET_KEY = "Cyan@2025#AESKey";
    // HMAC签名密钥（任意长度，示例32位）
    private static final String HMAC_SECRET_KEY = "Cyan@2025#HMACKey12345678";
    // 算法常量
    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String HMAC_ALGORITHM = "HmacSHA256";

    // 生成UUID随机串（基础串）
    private String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    // AES加密（用户ID+时间戳）
    public String aesEncrypt(String content) throws Exception {
        SecretKeySpec aesKey = new SecretKeySpec(AES_SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encryptBytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(encryptBytes);
    }

    // AES解密
    public String aesDecrypt(String encryptContent) throws Exception {
        SecretKeySpec aesKey = new SecretKeySpec(AES_SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decryptBytes = cipher.doFinal(Base64.decodeBase64(encryptContent));
        return new String(decryptBytes, StandardCharsets.UTF_8);
    }

    // HMAC-SHA256签名（防篡改）
    public String hmacSign(String content) throws Exception {
        SecretKeySpec hmacKey = new SecretKeySpec(HMAC_SECRET_KEY.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        mac.init(hmacKey);
        byte[] signBytes = mac.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(signBytes);
    }

    // 验证签名
    public boolean verifySign(String content, String sign) throws Exception {
        return hmacSign(content).equals(sign);
    }

    // ========== 核心：生成安全Token ==========
    public String generateToken(String userId) throws Exception {
        // 1. 构造待加密内容：用户ID + 时间戳（防重放）
        long timestamp = System.currentTimeMillis();
        String rawContent = userId + "," + timestamp;

        // 2. AES加密内容
        String encryptContent = aesEncrypt(rawContent);

        // 3. 生成UUID基础串
        String uuid = generateUUID();

        // 4. 对「加密内容+UUID」签名（防篡改）
        String signContent = encryptContent + uuid;
        String sign = hmacSign(signContent);

        // 5. 最终Token：加密内容.签名.UUID（三段式）
        return encryptContent + "." + sign + "." + uuid;
    }

    /**
     * 解析并校验Token
     */
    public TokenParseResult parseAndVerifyToken(String token) throws Exception {
        TokenParseResult result = new TokenParseResult();

        // 1. 校验Token格式
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            result.setValid(false);
            result.setMsg("Token格式错误");
            return result;
        }
        String encryptContent = parts[0];
        String sign = parts[1];
        String uuid = parts[2];

        // 2. 验证签名（防篡改核心）
        String signContent = encryptContent + uuid;
        if (!verifySign(signContent, sign)) {
            result.setValid(false);
            result.setMsg("Token已被篡改");
            return result;
        }

        // 3. 解密内容，提取用户ID和时间戳
        String rawContent = aesDecrypt(encryptContent);
        String[] contentParts = rawContent.split(",");
        if (contentParts.length != 2) {
            result.setValid(false);
            result.setMsg("Token内容非法");
            return result;
        }
        String userId = contentParts[0];
        long timestamp = Long.parseLong(contentParts[1]);

        // 4. 校验时间戳（防重放，30天内有效）
        long now = System.currentTimeMillis();
        if ((now - timestamp) > 30L * 24 * 60 * 60 * 1000) {
            result.setValid(false);
            result.setMsg("Token时间戳过期（防重放）");
            return result;
        }

        // 5. 校验通过
        result.setValid(true);
        result.setUserId(userId);
        result.setUuid(uuid);
        result.setMsg("Token有效");
        return result;
    }

    /**
     * Token解析结果封装
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Accessors(chain = true)
    public static class TokenParseResult {
        private boolean valid;
        private String userId;
        private String uuid;
        private String msg;

    }
}