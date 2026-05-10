package com.wjx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@Slf4j
@EnableAspectJAutoProxy
public class SpringbootJisheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJisheApplication.class, args);
        log.info("项目启动了");
    }
}
