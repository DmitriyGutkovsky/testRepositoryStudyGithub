package com.noirix.aspect;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@Aspect
public class TasksAspects {

  private static final Logger log = Logger.getLogger(com.noirix.aspect.TasksAspects.class);

  private static final Map<String, Integer> counter = new HashMap<>();

  @Pointcut("execution(* com.noirix.repository.impl.*Impl.*(..))")
  public void aroundTaskAspect() {}

  @AfterReturning(pointcut = "aroundTaskAspect()")
  public void methodsCounter(JoinPoint jp) throws Throwable {
    String methodName = jp.getSignature().getName();
    if (!counter.containsKey(methodName)) {
      counter.put(methodName, 1);
    } else {
      counter.put(methodName, counter.get(methodName) + 1);
    }

    System.out.println(counter.toString());
  }
}
