package idk;

import idk.controller.ExceptionHandler;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
@Configuration
public class Application implements WebMvcConfigurer {

    @Value("${spring.datasource.url}") String dbUrl;
    @Value("${spring.datasource.username}") String username;
    @Value("${spring.datasource.password}") String password;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        WebMvcConfigurer.super.configureHandlerExceptionResolvers(resolvers);
        resolvers.add(new ExceptionHandler());
        migrate();
    }



    private void migrate() {
        Flyway flyway =  Flyway.configure().dataSource(dbUrl, username, password).load();
        flyway.migrate();
    }
}
