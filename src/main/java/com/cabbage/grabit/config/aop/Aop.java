package com.cabbage.grabit.config.aop;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.Pipe;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Component
@Aspect
public class Aop {

//    @Pointcut("execution(* com.cabbage.grabit.api.product.*(..))")
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object target = joinPoint.proceed();
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());
        return target;
    }


}
