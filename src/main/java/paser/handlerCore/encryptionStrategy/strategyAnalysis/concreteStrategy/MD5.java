package paser.handlerCore.encryptionStrategy.strategyAnalysis.concreteStrategy;

import org.springframework.util.DigestUtils;
import paser.handlerAbstract.encryption.SmartIntercept;

public class MD5 implements SmartIntercept {


    @Override
    public String proceed(String original, String salt, Integer num) {
        String md5 = original;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(md5).append("/").append(salt);
            md5 = DigestUtils.md5DigestAsHex(stringBuilder.toString().getBytes());
            stringBuilder.setLength(0);//clear
        }
        return md5;
    }

    @Override
    public Boolean verify(String original, String salt, Integer num, String ciphertext) {
        String md5Text = proceed(original, salt, num);
        if (md5Text.equalsIgnoreCase(ciphertext)) {
            return true;
        }
        return false;
    }

}
