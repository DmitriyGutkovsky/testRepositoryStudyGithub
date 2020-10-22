package com.noirix.util;

import com.noirix.domain.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeans {

    @Bean
    public Car getCar(){
        return Car.builder()
                .id(20l)
                .model("Tesla Model S")
                .price(110000D)
                .creationYear(2019)
                .build();
    }
}
