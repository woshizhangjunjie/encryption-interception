package paser.handlerCore.annotation;

import paser.handlerCore.encryptionStrategy.strategyAnalysis.SmartType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 网络传输层入参拦截
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InParamEncryption {

    SmartType type() default SmartType.MD5;

    String salt() default "";

    int num() default 1;
}
