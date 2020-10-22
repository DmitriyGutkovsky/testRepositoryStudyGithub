package com.noirix.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
//@Component
//@Repository
//@Service
//@Controller
//@RestController
@PropertySource("classpath:database.properties")
//feature bean name will be: databaseConfig
public class DatabaseConfig {

    @Value("${driverName}") // SPEL - Spring Expression Language
    private String driverName;

    @Value("${url}")
    private String url;

    @Value("${login}")
    private String login;

    @Value("${password}")
    private String password;

}
