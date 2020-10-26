package paser.handlerCore.annotation;


import paser.handlerCore.encryptionStrategy.strategyAnalysis.SmartType;

import java.lang.annotation.*;

/**
 * mybatis dao层拦截(属性上)
 */
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MybatisFieldEncryption {

    SmartType type() default SmartType.MD5;

    String salt() default "";

    int num() default 1;
}
