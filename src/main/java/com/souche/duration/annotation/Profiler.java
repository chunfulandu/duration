package com.souche.duration.annotation;

import java.lang.annotation.*;

/**
 * @author chengchf
 * @date 2019/4/9 6:03 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Profiler {

    boolean value() default false;
}
