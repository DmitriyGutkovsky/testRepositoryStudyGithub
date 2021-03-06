package com.noirix;

import com.noirix.domain.Car;
import com.noirix.domain.Gender;
import com.noirix.domain.User;
import com.noirix.service.CarService;
import com.noirix.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Log4j
public class SpringContextTester {

//  private static final Logger log = Logger.getLogger(SpringContextTester.class);

  public static void main(String[] args) {
    //    ClassPathXmlApplicationContext classPathXmlApplicationContext =
    //        new ClassPathXmlApplicationContext("classpath:application-context.xml");
    //
    //    User user1 = (User) classPathXmlApplicationContext.getBean("user1");
    //    User user2 = (User) classPathXmlApplicationContext.getBean("user2");
    //
    //    System.out.println(user1.getId());
    //    System.out.println(user1.getName());
    //    System.out.println(user1.getSurname());
    //    System.out.println(user1.getUsercar());
    //
    //
    //    System.out.println(user2.getId());
    //    System.out.println(user2.getName());
    //    System.out.println(user2.getSurname());
    //    System.out.println(user2.getUsercar());

    System.out.println("\n*************************************");

    //    AnnotationConfigApplicationContext annotationConfigApplicationContext = new
    //            AnnotationConfigApplicationContext("com.noirix");
    //
    //    DatabaseConfig bean = annotationConfigApplicationContext.getBean(DatabaseConfig.class);
    //    System.out.println(bean.getDriverName());
    //    System.out.println(bean.getLogin());
    //    System.out.println(bean.getPassword());
    //    System.out.println(bean.getUrl());

    // This is how to check available beans in context
    //    System.out.println("\n********** All available beanDefinition ******");
    //
    //    for (String beanDefinitionName :
    // annotationConfigApplicationContext.getBeanDefinitionNames()) {
    //      System.out.println(beanDefinitionName);
    //    }
    //
    //    System.out.println("\n************* Testing @Bean ***************");
    //
    //    Car beanCar = annotationConfigApplicationContext.getBean(Car.class);
    //    System.out.println(beanCar);
    //
    //    System.out.println("\n********** access to JDBC via Spring *******");
    //
    //    UserRepository userRepository =
    // annotationConfigApplicationContext.getBean(UserRepository.class);
    //
    // System.out.println(userRepository.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));

    AnnotationConfigApplicationContext annotationConfigApplicationContext =
        new AnnotationConfigApplicationContext("com.noirix");

        UserService userService = annotationConfigApplicationContext.getBean(UserService.class);
    //
    //    System.out.println(userService.findAll().stream().map(User::getName)
    //            .collect(Collectors.joining(", ")));
    //
    //    System.out.println(userService.findById(2l));
    //    System.out.println("*********************");
//        System.out.println(userService.search("Slava"));
        log.info(userService.search("Slava"));
    //    System.out.println("*********************");
    //    User userForSave =
    //            User.builder()
    //                    .name("TestForDelete")
    //                    .surname("TestPerson")
    //                    .birthDate(new Date())
    //                    .created(new Timestamp(new Date().getTime()))
    //                    .changed(new Timestamp(new Date().getTime()))
    //                    .gender(Gender.MALE)
    //                    .weight(90f)
    //                    .build();
    //
    //        System.out.println(userService.save(userForSave));
    //    User userForUpdate =
    //            User.builder()
    //                    .name("TestForUpdate")
    //                    .surname("will be deleted")
    //                    .birthDate(new Date())
    //                    .created(new Timestamp(new Date().getTime()))
    //                    .changed(new Timestamp(new Date().getTime()))
    //                    .gender(Gender.MALE)
    //                    .weight(90f)
    //                    .id(13l)
    //                    .build();
    //
    //    System.out.println(userService.update(userForUpdate));
    //
    //    System.out.println(userService.delete(userForUpdate));

    CarService carService = annotationConfigApplicationContext.getBean(CarService.class);

    //        Car carForSave =
    //            Car.builder()
    //                    .model("Ferrari for FindById")
    //                    .creationYear(2019)
    //                    .color("red")
    //                    .price(2000000d)
    //                    .userId(2l)
    //                    .build();

    //        System.out.println(carService.save(carForSave));

    //    System.out.println(carService.findAll());

    //            Car carForUpdate =
    //                Car.builder()
    //                        .model("Ferrari for Update")
    //                        .creationYear(2019)
    //                        .color("red")
    //                        .price(2000000d)
    //                        .userId(2l)
    //                        .id(27l)
    //                        .build();
    //
    //            System.out.println(carService.delete(carForUpdate));

//    System.out.println(carService.search("Ferrari"));

    List<Car> cars = carService.search("Ferrari");
    for (Car car : cars) {
      log.info(car.toString());
    }

  }
}
