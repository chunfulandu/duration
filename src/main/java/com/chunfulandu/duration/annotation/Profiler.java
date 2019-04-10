package com.chunfulandu.duration.annotation;

import java.lang.annotation.*;

/**
 * @author chengchf
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Profiler {

    boolean value() default false;
}
