package com.wjx.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimeAop {

    @Around("execution(* com.wjx.controller..*.*(..))")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info(joinPoint.getSignature().getName() + "--->" + (endTime - startTime) + "ms");
        return result;
    }
}


