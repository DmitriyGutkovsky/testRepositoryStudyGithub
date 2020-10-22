package com.noirix;

import com.noirix.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextTester {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext classPathXmlApplicationContext =
        new ClassPathXmlApplicationContext("classpath:application-context.xml");

    User user1 = (User) classPathXmlApplicationContext.getBean("user1");

    System.out.println(user1.getId());
    System.out.println(user1.getName());
    System.out.println(user1.getSurname());

  }
}
