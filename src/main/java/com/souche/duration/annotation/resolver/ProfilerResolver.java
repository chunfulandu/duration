package com.souche.duration.annotation.resolver;

import com.souche.duration.DurationProfiler;
import com.souche.duration.annotation.Profiler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author chengchf
 * @date 2019/4/9 6:06 PM
 */
@Aspect
@Component
public class ProfilerResolver {

    @Around("@annotation(com.souche.duration.annotation.Profiler)")
    public Object duration(ProceedingJoinPoint point) {
        MethodSignature joinPointObject = (MethodSignature) point.getSignature();
        String method = joinPointObject.getMethod().getName();

        String mark = point.getTarget().getClass().getName() + "#" + method;
        DurationProfiler.enter(mark);

        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            DurationProfiler.exit();
        }
        return null;
    }

    @Around("@annotation(com.souche.duration.annotation.RootProfiler)")
    public Object rootDuration(ProceedingJoinPoint point) {
        return duration(point);
    }

    @After("@annotation(com.souche.duration.annotation.RootProfiler)")
    public void after() {
        DurationProfiler.over();
    }

    @After("@annotation(com.souche.duration.annotation.Profiler)")
    public void rootAfter(JoinPoint point) {
        MethodSignature joinPointObject = (MethodSignature) point.getSignature();
        Method method = joinPointObject.getMethod();
        Profiler profiler = method.getAnnotation(Profiler.class);
        boolean value = profiler.value();
        if (value) {
            DurationProfiler.over();
        }
    }
}
