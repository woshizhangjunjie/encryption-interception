package paser.handlerCore.encryptionStrategy.strategyAnalysis.concreteStrategy;

import org.mindrot.jbcrypt.BCrypt;
import paser.handlerAbstract.encryption.SmartIntercept;

public class BCRYPT implements SmartIntercept {


    @Override
    public String proceed(String original, String salt, Integer num) {
        String hashpw = original;
        for (int i = 0; i < num; i++) {
            hashpw = BCrypt.hashpw(hashpw, salt);
        }
        return hashpw;
    }

    @Override
    public Boolean verify(String original, String salt, Integer num, String ciphertext) {
        String hashpw = proceed(original, salt, num);
        return BCrypt.checkpw(ciphertext, hashpw);
    }

}
