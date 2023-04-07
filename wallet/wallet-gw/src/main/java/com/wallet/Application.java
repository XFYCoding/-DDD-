package com.wallet;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 小肥瑜
 */
@SpringBootApplication
@MapperScan("com.wallet")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
