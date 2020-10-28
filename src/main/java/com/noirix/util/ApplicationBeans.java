package com.noirix.util;

import com.noirix.domain.Car;
import com.noirix.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationBeans {

    @Bean
//    @Primary
//    @Scope("singleton")
//    @Scope("prototype")
//    @Scope("session")
//    @Scope("global-session")
//    @Scope("request")
    public Car car1(){
        return Car.builder()
                .id(1L)
                .model("Tesla Model S")
                .price(110000D)
                .creationYear(2019)
                .build();
    }
    @Bean
    @Primary
    public Car car2(){
        return Car.builder()
                .id(2L)
                .model("Tesla Model X")
                .price(150000D)
                .creationYear(2020)
                .build();
    }

    @Bean
    public User user1(Car car) {
        return  new User(car);
    }
    @Bean
    public User user2(Car car) {
        return  new User(car);
    }

}
