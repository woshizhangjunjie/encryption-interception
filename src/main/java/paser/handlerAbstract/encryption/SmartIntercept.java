package paser.handlerAbstract.encryption;


public interface SmartIntercept {

    String proceed(String original, String salt,Integer num);

    Boolean verify(String original, String salt,Integer num,String ciphertext);
}
