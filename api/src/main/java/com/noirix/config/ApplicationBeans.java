package com.noirix.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.sql.DataSource;

@Configuration
public class ApplicationBeans {

//    @Bean
//    @Primary
//    @Scope("singleton")
//    @Scope("prototype")
//    @Scope("session")
//    @Scope("global-session")
//    @Scope("request")
//    public Car car1(){
//        return Car.builder()
//                .id(1L)
//                .model("Tesla Model S")
//                .price(110000D)
//                .creationYear(2019)
//                .build();
//    }
//    @Bean
//    @Primary
//    public Car car2(){
//        return Car.builder()
//                .id(2L)
//                .model("Tesla Model X")
//                .price(150000D)
//                .creationYear(2020)
//                .build();
//    }
//
//    @Bean
//    public User user1(Car car) {
//        return  new User(car);
//    }
//    @Bean
//    public User user2(Car car) {
//        return  new User(car);
//    }

    // Spring creates connection pool for DataBase connection
//    @Bean
//    public DataSource hikariDataSource(DatabaseConfig databaseConfig){
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setJdbcUrl(databaseConfig.getUrl());
//        hikariDataSource.setUsername(databaseConfig.getLogin());
//        hikariDataSource.setPassword(databaseConfig.getPassword());
//        hikariDataSource.setDriverClassName(databaseConfig.getDriverName());
//        hikariDataSource.setMaximumPoolSize(10);
//
//        return hikariDataSource;
//    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource  dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // also Swagger works without this bean....:public Docket api()
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


}
