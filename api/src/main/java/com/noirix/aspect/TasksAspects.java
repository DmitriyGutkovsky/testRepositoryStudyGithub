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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Component
@Aspect
public class TasksAspects {

  private static final Logger log = Logger.getLogger(com.noirix.aspect.TasksAspects.class);

  private static final Map<String, Integer> counter = new HashMap<>();

  private static double totalTimePassed = 0.0;

  @Pointcut("execution(* com.noirix.repository.impl.*Impl.*(..))")
  public void aroundTaskAspect() {}

  //First version of the method (with out method args)
  @AfterReturning(pointcut = "aroundTaskAspect()")
  public void methodsCounterOne(JoinPoint jp) {
    String methodName = jp.getSignature().getName();
    if (!counter.containsKey(methodName)) {
      counter.put(methodName, 1);
    } else {
      counter.put(methodName, counter.get(methodName) + 1);
    }
    log.info(counter.toString());
  }

  //Second ver of the method: testing getArgs()
  @AfterReturning(pointcut = "aroundTaskAspect()")
  public void methodsCounterTwo(JoinPoint jp) {
    String args = Arrays.stream(jp.getArgs()).map(a -> a.toString()).collect(Collectors.joining(","));
    String methodFullName = jp.getSignature().getName() + "(" + args + ")";
    if (!counter.containsKey(methodFullName)) {
      counter.put(methodFullName, 1);
    } else {
      counter.put(methodFullName, counter.get(methodFullName) + 1);
    }
    log.info(counter.toString());

  }

  // first version for this method
  @Around("aroundTaskAspect()")
  public Object timeCounterOne(ProceedingJoinPoint jp) throws Throwable {
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

  //second version: used method prettyPrint() - Generate a string with a table describing all tasks performed
  @Around("aroundTaskAspect()")
  public Object timeCounterTwo(ProceedingJoinPoint jp) throws Throwable {
    StopWatch watch = new StopWatch();
    try{
      watch.start(jp.toShortString());
      return jp.proceed();
    } finally{
      watch.stop();
      log.info(watch.prettyPrint());
    }
  }


}
