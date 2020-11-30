package com.noirix;

import com.noirix.config.*;
import com.noirix.security.configuration.JwtTokenConfig;
import com.noirix.security.configuration.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.noirix")
//@EnableWebMvc
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({AmazonConfig.class,
        ApplicationBeans.class,
        WebBeansConfig.class,
        GoogleConfig.class,
        WebSecurityConfiguration.class,
        JwtTokenConfig.class,
        PersistenceContextBeansConfiguration.class})
public class SpringBootApplicationStarter {

  public static void main(String[] args) {
      SpringApplication.run(SpringBootApplicationStarter.class, args);
  }

}
