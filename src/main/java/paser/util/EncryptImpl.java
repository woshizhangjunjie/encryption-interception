package paser.util;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import paser.handlerCore.annotation.InParamEncryption;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.SmartParser;

import java.lang.reflect.Field;

@Component
public class EncryptImpl implements EncryptUtil {


    @Override
    public <T> T encrypt(Field[] declaredFields, T paramsObject) throws IllegalAccessException {
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(InParamEncryption.class)) {
                Assert.isTrue(String.class.equals(field.getType()), "The type of encryption must be String");
                InParamEncryption annotation = field.getAnnotation(InParamEncryption.class);
                SmartParser algorithmParser = new SmartParser();
                String result = algorithmParser.options(annotation.type(), String.valueOf(field.get(paramsObject)), annotation.salt(), annotation.num());
                field.setAccessible(true);
                Object object = field.get(paramsObject);
                if (object instanceof String) {
                    field.set(paramsObject, result);
                }
            }
        }
        return paramsObject;
    }
}