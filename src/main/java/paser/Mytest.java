package paser;

import paser.handlerCore.annotation.InParamEncryption;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.SmartType;

/**
 * 1.0.0 test
 */
public class Mytest {
    @InParamEncryption(type = SmartType.MD5,salt = "salt",num = 3)
    private String mima;
    private String salt;

    public String getSalt() {
        return salt;
    }

    public String getMima() {
        return mima;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    @Override
    public String toString() {
        return "mima:"+mima+",salt:"+salt;
    }
}
