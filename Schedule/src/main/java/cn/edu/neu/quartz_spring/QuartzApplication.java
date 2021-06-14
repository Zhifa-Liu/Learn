package cn.edu.neu.quartz_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 32098
 */
@SpringBootApplication
@ComponentScan(
        {"cn.edu.neu.quartz_spring.controller",
        "cn.edu.neu.quartz_spring.service",
        "cn.edu.neu.quartz_spring.schedule"}
)
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

}

