package paser.handlerCore.encryptionStrategy.strategyAnalysis.concreteStrategy;

import paser.handlerAbstract.encryption.SmartIntercept;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 implements SmartIntercept {


    @Override
    public String proceed(String original, String salt, Integer num) {
        MessageDigest messageDigest;
        String encodeStr = original;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < num; i++) {
                stringBuilder.append(encodeStr).append("/").append(salt);
                messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(stringBuilder.toString().getBytes("UTF-8"));
                encodeStr = byte2Hex(messageDigest.digest());
                stringBuilder.setLength(0);//clear
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    @Override
    public Boolean verify(String original, String salt, Integer num, String ciphertext) {
        String sha256Text = proceed(original, salt, num);
        if (sha256Text.equalsIgnoreCase(ciphertext)) {
            return true;
        }
        return false;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
