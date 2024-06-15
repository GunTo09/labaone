package ru.gunto09.java.labaone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
@SpringBootApplication
@EnableTransactionManagement
public class LabaoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabaoneApplication.class, args);
    }

}