package paser.handlerAbstract.encryption;

import com.sun.istack.internal.NotNull;

public interface SmartIntercept {

    String proceed(@NotNull String original, String salt,Integer num);

    Boolean verify(@NotNull String original, String salt,Integer num,String ciphertext);
}
