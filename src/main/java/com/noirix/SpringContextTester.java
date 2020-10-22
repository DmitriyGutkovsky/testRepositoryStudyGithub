package com.noirix;

import com.noirix.domain.User;
import com.noirix.util.DatabaseConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextTester {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext classPathXmlApplicationContext =
        new ClassPathXmlApplicationContext("classpath:application-context.xml");

    User user1 = (User) classPathXmlApplicationContext.getBean("user1");
    User user2 = (User) classPathXmlApplicationContext.getBean("user2");

    System.out.println(user1.getId());
    System.out.println(user1.getName());
    System.out.println(user1.getSurname());

    System.out.println(user2.getId());
    System.out.println(user2.getName());
    System.out.println(user2.getSurname());

    System.out.println("*************************************");

    AnnotationConfigApplicationContext annotationConfigApplicationContext = new
            AnnotationConfigApplicationContext("com.noirix");

    DatabaseConfig bean = annotationConfigApplicationContext.getBean(DatabaseConfig.class);
    System.out.println(bean.getDriverName());
    System.out.println(bean.getLogin());
    System.out.println(bean.getPassword());
    System.out.println(bean.getUrl());


  }
}
