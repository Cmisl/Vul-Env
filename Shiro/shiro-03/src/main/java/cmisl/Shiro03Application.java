package cmisl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cmisl.mapper")
public class Shiro03Application {

    public static void main(String[] args) {
        SpringApplication.run(Shiro03Application.class, args);
    }

}
