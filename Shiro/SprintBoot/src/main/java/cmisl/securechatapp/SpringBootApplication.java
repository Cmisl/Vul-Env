package cmisl.securechatapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

@org.springframework.boot.autoconfigure.SpringBootApplication
@MapperScan("cmisl.securechatapp.mapper")
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

}

