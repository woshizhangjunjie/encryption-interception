package paser.util;

import org.springframework.util.Assert;
import paser.handlerCore.annotation.InParamEncryption;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.SmartPaser;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.SmartType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class VerifyUtil<T> {

    private T now;
    private T before;

    /**
     * Compare the values of all the annotated fields of two objects
     * Now can either be intercepted encrypted or directly assigned unencrypted
     * Before can be encrypted Dtos extracted from the database or data obtained from other persistent approaches
     *
     * @param now
     * @param before
     */
    public VerifyUtil(T now, T before) {
        this.now = now;
        this.before = before;
    }

    public void replace(T now, T before) {
        this.now = now;
        this.before = before;
    }


    public Boolean interceptCompareAllField(boolean isIntercept) {
        Field[] declaredFields = now.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(InParamEncryption.class)) {
                Assert.isTrue(String.class.equals(field.getType()), "The type of encryption must be String");
                char[] uppercase = field.getName().toCharArray();
                //capitalize the first letter
                uppercase[0] -= 32;
                try {
                    Method declaredMethod1 = now.getClass().getDeclaredMethod("get" + String.valueOf(uppercase));
                    Object invoke1 = declaredMethod1.invoke(now);
                    Method declaredMethod2 = before.getClass().getDeclaredMethod("get" + String.valueOf(uppercase));
                    Object invoke2 = declaredMethod2.invoke(before);
                    if (isIntercept) {
                        if (!String.valueOf(invoke1).equalsIgnoreCase(String.valueOf(invoke2))) {
                            return false;
                        }
                    }
                    if (!isIntercept) {
                        InParamEncryption annotation = field.getAnnotation(InParamEncryption.class);
                        SmartPaser smartPaser = new SmartPaser();
                        String options = smartPaser.options(annotation.type(), String.valueOf(invoke1), annotation.salt(), annotation.num());
                        if (!options.equalsIgnoreCase(String.valueOf(invoke2))) {
                            return false;
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    return false;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return false;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

}
