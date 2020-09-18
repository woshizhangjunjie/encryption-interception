package paser.util;

import java.lang.reflect.Field;

public interface EncryptUtil {
    
     <T> T encrypt(Field[] declaredFields, T paramsObject) throws IllegalAccessException;
}