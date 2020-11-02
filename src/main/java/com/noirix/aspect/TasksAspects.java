package com.noirix.aspect;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@Aspect
public class TasksAspects {

  private static final Logger log = Logger.getLogger(com.noirix.aspect.TasksAspects.class);

  private static final Map<String, Integer> counter = new HashMap<>();

  private static double totalTimePassed = 0.0;

  @Pointcut("execution(* com.noirix.repository.impl.*Impl.*(..))")
  public void aroundTaskAspect() {}

  @AfterReturning(pointcut = "aroundTaskAspect()")
  public void methodsCounter(JoinPoint jp) {
    String methodName = jp.getSignature().getName();
    if (!counter.containsKey(methodName)) {
      counter.put(methodName, 1);
    } else {
      counter.put(methodName, counter.get(methodName) + 1);
    }
    log.info(counter.toString());
  }

  @Around("aroundTaskAspect()")
  public Object timeCounter(ProceedingJoinPoint jp) throws Throwable {
    StopWatch watch = new StopWatch();
    String methodName = jp.getSignature().getName();
    watch.start();
    Object proceed = jp.proceed();
    watch.stop();
    double totalTimeSeconds = watch.getTotalTimeSeconds();
    totalTimePassed += totalTimeSeconds;
    log.info("Method " + methodName +" running time: " + totalTimeSeconds + " seconds" +
            "\n\t Total time passed: " + totalTimePassed);
    return proceed;
  }
}
