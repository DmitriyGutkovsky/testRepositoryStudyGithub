package com.noirix.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger log = Logger.getLogger(LoggingAspect.class);

    @Before("aroundRepositoryPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Method " + joinPoint.getSignature().getName() + " start from before advice");
    }

    @AfterReturning(pointcut = "aroundRepositoryPointcut()")
    public void doAccessCheck(JoinPoint joinPoint) {
        log.info("Method " + joinPoint.getSignature().getName() + " finished from after advice");
    }

    @Pointcut("execution(* com.noirix.repository.impl.*Impl.*(..))")
//    @Pointcut("execution(* com.noirix.repository.impl.UserRepositoryJdbcTemplateImpl.*(..))")
    public void aroundRepositoryPointcut() {
    }

    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method " + joinPoint.getSignature().getName() + " start");
        Object proceed = joinPoint.proceed();
        log.info("Method " + joinPoint.getSignature().getName() + " finished");
        return proceed;
    }

}
