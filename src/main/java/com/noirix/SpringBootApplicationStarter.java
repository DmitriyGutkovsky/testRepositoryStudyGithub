package com.noirix;

import com.noirix.config.ApplicationBeans;
import com.noirix.config.DatabaseConfig;
import com.noirix.config.WebBeansConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.noirix", exclude = DataSourceAutoConfiguration.class)
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({DatabaseConfig.class, ApplicationBeans.class, WebBeansConfig.class})
public class SpringBootApplicationStarter {

  public static void main(String[] args) {
      SpringApplication.run(SpringBootApplicationStarter.class, args);
  }

}
