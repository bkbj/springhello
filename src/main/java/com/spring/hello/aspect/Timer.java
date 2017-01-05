package com.spring.hello.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 时间切面
 * Created by ustc-lezg on 05/01/2017.
 */
@Aspect
@Component
public class Timer {

    @Pointcut("execution(* com.spring.hello.controller.HomeController.*(..))")
    public void calTime(){} //重用切点表达式

    /*@Before("calTime()")
    public void startTime() {
        System.out.println("StartTime: " + System.currentTimeMillis());
    }

    @After("calTime()")
    public void endTime() {
        System.out.println("EndTime: " +System.currentTimeMillis());
    }*/

    @Around("calTime()")
    public Object aroundMethod(ProceedingJoinPoint pjp) {
        Object result;
        String methodName = pjp.getSignature().getName();
        try {
            System.out.println("The Method : " + methodName);
            long startTime = System.currentTimeMillis();
            result = pjp.proceed();
            System.out.println("Time: " + (System.currentTimeMillis() - startTime) + " ms");
        }catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
