package com.chunfulandu.duration.annotation;

import java.lang.annotation.*;

/**
 * @author chengchf
 * @date 2019/4/9 6:04 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RootProfiler {
}
