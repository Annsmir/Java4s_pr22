package com.example.ex22;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@org.aspectj.lang.annotation.Aspect
public class CallTimeAspect {
    @Around("allServiceMethods()")
    public Object logTimeInCall(ProceedingJoinPoint jp) {
        long t0, t1; 
        String c = jp.getTarget().getClass().getName();
        String m = jp.getSignature().getName();
        long t = Thread.currentThread().getId();

    log.info("Call on thread {} in {} to {} with args: {}", t, c , m , jp.getArgs());
    t0 = System.currentTimeMillis();
        try {
            return jp.proceed();
        } catch (Throwable e) {

            e.printStackTrace();
            return null;
        } finally {

            t1 = System.currentTimeMillis();
            log.info("Returning from call on thread {} in {} to {}, elapsed time millis = {}", t, c, m, t1 - t0);
        }
    }
    @Pointcut("within(com.example.*.*Service*)")
    public void allServiceMethods() {}
   
}
