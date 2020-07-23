package paser.mvcHandler;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;
import paser.handlerCore.annotation.InParamEncryption;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.SmartPaser;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.SmartType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


@Component
public class EncryptionInfoMethodArgumentResolver extends AbstractMessageConverterMethodArgumentResolver {

    public EncryptionInfoMethodArgumentResolver(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //post
        Object obj = this.readWithMessageConverters(nativeWebRequest, methodParameter, methodParameter.getNestedGenericParameterType());
        //get
        //HttpServletRequest nativeRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        //String fieldValue = nativeRequest.getParameter("字段名");

        Class<?> parameterType = methodParameter.getParameterType();
        Field[] declaredFields = parameterType.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> {
            if (field.isAnnotationPresent(InParamEncryption.class)) {
                Assert.isTrue(String.class.equals(field.getType()), "The type of encryption must be String");
                InParamEncryption annotation = field.getAnnotation(InParamEncryption.class);
                char[] uppercase = field.getName().toCharArray();
                //capitalize the first letter
                uppercase[0] -= 32;
                SmartType type = annotation.type();
                SmartPaser algorithmPaser = new SmartPaser();
                field.setAccessible(true);
                //Encryption result
                try {
                    String  result = algorithmPaser.options(type, String.valueOf(field.get(obj)), annotation.salt(), annotation.num());
                    Method declaredMethod = parameterType.getDeclaredMethod("set" + String.valueOf(uppercase), String.class);
                    declaredMethod.invoke(obj, result);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        return obj;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestBody.class);
    }

}
