package com.zemoso.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger myLogger=Logger.getLogger(LoggingAspect.class.getName());


    @Pointcut("execution(* com.zemoso.service.*.*(..))")
    private void pointCutExpression(){}


    @After("pointCutExpression()")
    public void afterFinallyAdvice(JoinPoint joinPoint) {
        String method=joinPoint.getSignature().toShortString();
        myLogger.info("--> executing @After (finally) on method: "+method);

    }

    @AfterThrowing(pointcut = "pointCutExpression()", throwing="exc")
    public void afterThrowingAdvice(JoinPoint joinPoint,Throwable exc)
    {
        String method=joinPoint.getSignature().toShortString();
        myLogger.info("--> executing @AfterThrowing on method: "+method);

        myLogger.info("-->The Exception is : "+exc);

    }

    @Before("pointCutExpression()")
    public void beforeAdvice(JoinPoint joinPoint)
    {
        myLogger.info("-->Executing  before advice method");
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();

        myLogger.info("Method : "+methodSignature);

    }

}
