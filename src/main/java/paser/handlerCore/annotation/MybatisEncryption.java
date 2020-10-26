package paser.handlerCore.annotation;

import java.lang.annotation.*;

/**
 * mybatis dao层拦截(类上)
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MybatisEncryption {
}
