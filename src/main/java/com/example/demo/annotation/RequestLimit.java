package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * <p>创建时间: 2018/9/23 9:20 </p>
 *
 * @author shimengqiang
 * @version v1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RequestLimit {

    /**
     *
     * 允许访问的次数，默认值MAX_VALUE
     */
    int count() default Integer.MAX_VALUE;

    /**
     *
     * 时间段，单位为毫秒，默认值一分钟
     */
    long time() default 60000;


}
