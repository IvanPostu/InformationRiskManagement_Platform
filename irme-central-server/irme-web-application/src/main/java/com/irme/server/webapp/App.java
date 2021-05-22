package com.irme.server.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    @Bean
    public Z getZ() {

        return new Z();
    }
}


class Z {
    public Z() {
        String b = com.irme.server.bl.App.businessFunc();
        String s = com.irme.server.dal.App.message();
        System.out.println(s + b);
    }
}
